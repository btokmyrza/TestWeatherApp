package kz.btokmyrza.weatherapp.util

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.core.content.ContextCompat
import java.util.Locale

fun Context.hasLocationPermission(): Boolean =
    hasCoarseLocationPermission() && hasFineLocationPermission()

fun Context.hasCoarseLocationPermission(): Boolean = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_COARSE_LOCATION,
) == PackageManager.PERMISSION_GRANTED

fun Context.hasFineLocationPermission(): Boolean = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_FINE_LOCATION,
) == PackageManager.PERMISSION_GRANTED

fun Context.getNotificationManager(): NotificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.getCurrentLocation(latitude: Double, longitude: Double): Address? =
    Geocoder(this, Locale.getDefault())
        .getFromLocation(latitude, longitude, 1)
        ?.getOrNull(0)