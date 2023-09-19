package kz.btokmyrza.weatherapp.domain.use.cases

import kz.btokmyrza.weatherapp.domain.model.City
import kz.btokmyrza.weatherapp.domain.repository.CityRepository
import kz.btokmyrza.weatherapp.util.Constants

internal class GetCityByCoordinates(
    private val cityRepository: CityRepository,
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        apiKey: String = Constants.OPEN_WEATHER_API_KEY,
    ): City? = cityRepository.searchCitiesByCoordinates(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey,
    ).firstOrNull()
}