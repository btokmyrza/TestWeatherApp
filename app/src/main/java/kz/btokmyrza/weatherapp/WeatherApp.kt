package kz.btokmyrza.weatherapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import kz.btokmyrza.weatherapp.di.appDataModule
import kz.btokmyrza.weatherapp.di.appDomainModule
import kz.btokmyrza.weatherapp.di.appPresentationModule
import kz.btokmyrza.weatherapp.presentation.feature.location.LocationService
import kz.btokmyrza.weatherapp.util.getNotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

internal class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initNotifications()
    }

    private fun initKoin() = startKoin {
        androidContext(this@WeatherApp)
        modules(
            appDataModule,
            appDomainModule,
            appPresentationModule,
        )
    }

    private fun initNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                LocationService.NOTIFICATION_CHANNEL,
                LocationService.NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_LOW,
            )
            getNotificationManager().createNotificationChannel(notificationChannel)
        }
    }
}