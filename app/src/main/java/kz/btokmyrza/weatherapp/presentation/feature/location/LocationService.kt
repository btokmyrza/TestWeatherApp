package kz.btokmyrza.weatherapp.presentation.feature.location

import android.app.Service
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.domain.location.LocationTracker
import kz.btokmyrza.weatherapp.util.getCurrentLocation
import kz.btokmyrza.weatherapp.util.getNotificationManager
import org.koin.android.ext.android.inject
import java.util.Locale


internal class LocationService : Service() {

    private val locationServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val locationTracker: LocationTracker by inject()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startService()
            ACTION_STOP -> stopService()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        locationServiceScope.cancel()
    }

    private fun startService() {
        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Location: x, y")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)
        locationTracker
            .getLocationUpdates(intervalMillis = 10000L)
            .catch { exception -> exception.printStackTrace() }
            .onEach { location ->
                val updatedNotification = notification.setContentText(
                    getUpdatedNotificationText(location.latitude, location.longitude),
                )
                getNotificationManager().notify(NOTIFICATION_ID, updatedNotification.build())
            }
            .launchIn(locationServiceScope)

        startForeground(NOTIFICATION_ID, notification.build())
    }

    private fun stopService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_DETACH)
        } else {
            stopForeground(true)
        }
    }

    private fun getUpdatedNotificationText(
        latitude: Double,
        longitude: Double,
    ): String = StringBuilder().apply {
        append("Location: $latitude : $longitude")
        val cityName = getCurrentLocation(latitude, longitude)?.locality
        if (cityName.isNullOrBlank().not()) {
            append("\n$cityName")
        }
        val countryName = getCurrentLocation(latitude, longitude)?.countryName
        if (countryName.isNullOrBlank().not()) {
            append("\n$countryName")
        }
    }.toString()

    companion object {
        private const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL = "location"
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}