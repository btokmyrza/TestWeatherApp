package kz.btokmyrza.weatherapp.domain.preferences

internal interface UserPreferences {

    fun saveCityName(cityName: String)

    fun getCityName(): String

    fun saveLatitude(latitude: String)

    fun getLatitude(): String

    fun saveLongitude(longitude: String)

    fun getLongitude(): String

    companion object {
        const val KEY_CITY_NAME = "KEY_CITY_NAME"
        const val KEY_LATITUDE = "KEY_LATITUDE"
        const val KEY_LONGITUDE = "KEY_LONGITUDE"
    }
}