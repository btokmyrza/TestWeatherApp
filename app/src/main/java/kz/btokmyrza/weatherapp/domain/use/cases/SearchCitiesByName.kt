package kz.btokmyrza.weatherapp.domain.use.cases

import kz.btokmyrza.weatherapp.domain.model.City
import kz.btokmyrza.weatherapp.domain.repository.CityRepository
import kz.btokmyrza.weatherapp.util.Constants

private const val MAX_NUMBER_OF_RESULTS = "5"

internal class SearchCitiesByName(
    private val cityRepository: CityRepository,
) {

    suspend operator fun invoke(
        cityName: String,
        limit: String = MAX_NUMBER_OF_RESULTS,
        apiKey: String = Constants.OPEN_WEATHER_API_KEY,
    ): List<City> = cityRepository.searchCitiesByName(
        cityName = cityName,
        limit = limit,
        apiKey = apiKey,
    )
}