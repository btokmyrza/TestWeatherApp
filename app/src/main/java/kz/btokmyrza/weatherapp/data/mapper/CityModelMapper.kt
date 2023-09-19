package kz.btokmyrza.weatherapp.data.mapper

import kz.btokmyrza.weatherapp.data.network.model.CityResponse
import kz.btokmyrza.weatherapp.domain.model.City

internal class CityModelMapper {

    fun toCity(from: CityResponse): City = City(
        name = from.name.orEmpty(),
        nameRu = from.localName?.nameRu.orEmpty(),
        latitude = from.latitude.orEmpty(),
        longitude = from.longitude.orEmpty(),
        countryCode = from.countryCode.orEmpty(),
        state = from.state.orEmpty(),
    )
}