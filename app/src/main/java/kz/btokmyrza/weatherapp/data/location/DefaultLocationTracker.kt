package kz.btokmyrza.weatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kz.btokmyrza.weatherapp.domain.location.LocationTracker
import kz.btokmyrza.weatherapp.util.hasLocationPermission
import kz.btokmyrza.weatherapp.util.isGpsEnabled
import kotlin.coroutines.resume

@SuppressLint("MissingPermission")
internal class DefaultLocationTracker constructor(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient,
) : LocationTracker {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override suspend fun getCurrentLocation(): Location? {
        if (context.hasLocationPermission().not() || locationManager.isGpsEnabled().not()) {
            return null
        }

        return suspendCancellableCoroutine { cancellableContinuation ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cancellableContinuation.resume(result)
                    } else {
                        cancellableContinuation.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { location ->
                    cancellableContinuation.resume(location)
                }
                addOnFailureListener {
                    cancellableContinuation.resume(null)
                }
                addOnCanceledListener {
                    cancellableContinuation.cancel()
                }
            }
        }
    }

    override fun getLocationUpdates(intervalMillis: Long): Flow<Location> = callbackFlow {
        if (context.hasLocationPermission().not()) {
            throw LocationTracker.LocationException(message = "Missing location permission")
        }
        if (locationManager.isGpsEnabled().not()) {
            throw LocationTracker.LocationException(message = "GPS is disabled")
        }

        val locationRequest = LocationRequest.Builder(intervalMillis).build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let { location ->
                    launch { send(location) }
                }
            }
        }
        locationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper(),
        )
        awaitClose { locationClient.removeLocationUpdates(locationCallback) }
    }
}