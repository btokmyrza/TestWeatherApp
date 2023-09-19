package kz.btokmyrza.weatherapp.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

internal interface LocationTracker {

    suspend fun getCurrentLocation(): Location?

    fun getLocationUpdates(intervalMillis: Long): Flow<Location>

    class LocationException(message: String): Exception()
}