package kz.btokmyrza.weatherapp.data.preferences

import android.content.SharedPreferences
import kz.btokmyrza.weatherapp.domain.preferences.UserPreferences

internal class DefaultUserPreferences(
    private val sharedPreferences: SharedPreferences,
) : UserPreferences {

    override fun saveCityName(cityName: String) = sharedPreferences.edit()
        .putString(UserPreferences.KEY_CITY_NAME, cityName)
        .apply()

    override fun getCityName(): String = sharedPreferences
        .getString(UserPreferences.KEY_CITY_NAME, null)
        .orEmpty()

    override fun saveLatitude(latitude: String) = sharedPreferences.edit()
        .putString(UserPreferences.KEY_LATITUDE, latitude)
        .apply()

    override fun getLatitude(): String = sharedPreferences
        .getString(UserPreferences.KEY_LATITUDE, null)
        .orEmpty()

    override fun saveLongitude(longitude: String) = sharedPreferences.edit()
        .putString(UserPreferences.KEY_LONGITUDE, longitude)
        .apply()

    override fun getLongitude(): String = sharedPreferences
        .getString(UserPreferences.KEY_LONGITUDE, null)
        .orEmpty()
}