package kz.btokmyrza.weatherapp.presentation.feature.city.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kz.btokmyrza.weatherapp.domain.preferences.UserPreferences
import kz.btokmyrza.weatherapp.domain.use.cases.SearchCitiesByName
import kz.btokmyrza.weatherapp.presentation.mapper.CityDvoMapper
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.util.UiEvent

internal class CitySearchViewModel(
    private val searchCitiesByName: SearchCitiesByName,
    private val cityDvoMapper: CityDvoMapper,
    private val userPreferences: UserPreferences,
) : ViewModel() {

    var uiState by mutableStateOf(CitySearchUiState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CitySearchEvent) = when (event) {
        is CitySearchEvent.OnQueryChange -> onQueryChange(query = event.query)
        is CitySearchEvent.OnSearchFocusChange -> onSearchFocusChange(isFocused = event.isFocused)
        is CitySearchEvent.OnSearch -> onSearch()
        is CitySearchEvent.OnCityClick -> onCityClick(city = event.city)
    }

    private fun onQueryChange(query: String) {
        uiState = uiState.copy(query = query)
        onSearch()
    }

    private fun onSearchFocusChange(isFocused: Boolean) {
        uiState = uiState.copy(isHintVisible = isFocused.not() && uiState.query.isBlank())
    }

    private fun onSearch() {
        viewModelScope.launch {
            uiState = uiState.copy(isSearching = true)
            uiState = uiState.copy(
                cities = searchCitiesByName(cityName = uiState.query).map(cityDvoMapper::toCityDvo),
                isSearching = false,
            )
        }
    }

    private fun onCityClick(city: CityDvo) {
        viewModelScope.launch {
            userPreferences.apply {
                saveCityName(city.name)
                saveLatitude(city.latitude)
                saveLongitude(city.longitude)
            }
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}