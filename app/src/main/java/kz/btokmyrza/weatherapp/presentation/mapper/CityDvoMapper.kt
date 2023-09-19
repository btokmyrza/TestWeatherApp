package kz.btokmyrza.weatherapp.presentation.mapper

import kz.btokmyrza.weatherapp.domain.model.City
import kz.btokmyrza.weatherapp.presentation.model.CityDvo

internal class CityDvoMapper {
    
    fun toCityDvo(from: City): CityDvo = CityDvo(
        name = from.name,
        nameRu = from.nameRu,
        latitude = from.latitude,
        longitude = from.longitude,
        countryCode = from.countryCode,
        state = from.state,
    )
}