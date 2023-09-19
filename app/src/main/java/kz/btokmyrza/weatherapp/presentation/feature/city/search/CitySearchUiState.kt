package kz.btokmyrza.weatherapp.presentation.feature.city.search

import androidx.compose.runtime.Stable
import kz.btokmyrza.weatherapp.presentation.model.CityDvo

@Stable
internal data class CitySearchUiState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val cities: List<CityDvo> = emptyList(),
    val chosenCityName: String? = null,
    val chosenCityLatitude: Double? = null,
    val chosenCityLongitude: Double? = null,
)
