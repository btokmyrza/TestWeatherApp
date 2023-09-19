package kz.btokmyrza.weatherapp.presentation.feature.city.search

import kz.btokmyrza.weatherapp.presentation.model.CityDvo

internal sealed class CitySearchEvent {
    data class OnQueryChange(val query: String) : CitySearchEvent()
    data object OnSearch : CitySearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean) : CitySearchEvent()
    data class OnCityClick(val city: CityDvo) : CitySearchEvent()
}