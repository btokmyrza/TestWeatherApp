package kz.btokmyrza.weatherapp.util

import android.location.LocationManager

fun LocationManager.isGpsEnabled(): Boolean =
    this.isGpsProviderEnabled() || this.isNetworkProviderEnabled()

fun LocationManager.isNetworkProviderEnabled(): Boolean =
    this.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

fun LocationManager.isGpsProviderEnabled(): Boolean =
    this.isProviderEnabled(LocationManager.GPS_PROVIDER)