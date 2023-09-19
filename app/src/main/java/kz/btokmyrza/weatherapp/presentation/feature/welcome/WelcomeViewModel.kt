package kz.btokmyrza.weatherapp.presentation.feature.welcome

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.domain.location.LocationTracker
import kz.btokmyrza.weatherapp.domain.preferences.UserPreferences
import kz.btokmyrza.weatherapp.domain.use.cases.GetCityByCoordinates
import kz.btokmyrza.weatherapp.presentation.mapper.CityDvoMapper
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.util.UiEvent
import kz.btokmyrza.weatherapp.util.UiText

internal class WelcomeViewModel(
    private val locationTracker: LocationTracker,
    private val getCityByCoordinates: GetCityByCoordinates,
    private val userPreferences: UserPreferences,
    private val cityDvoMapper: CityDvoMapper,
) : ViewModel() {

    var uiState by mutableStateOf(WelcomeUiState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadChosenCityOrNull()
    }

    fun onEvent(event: WelcomeEvent) = when (event) {
        is WelcomeEvent.OnGetCurrentLocationClick -> loadCurrentLocation()
        is WelcomeEvent.OnNextClick -> onNextClick()
    }

    private fun loadChosenCityOrNull() {
        val cityName = userPreferences.getCityName()
        val latitude = userPreferences.getLatitude()
        val longitude = userPreferences.getLongitude()
        if (cityName.isNotBlank() && latitude.isNotBlank() && longitude.isNotBlank()) {
            uiState = uiState.copy(
                currentCity = CityDvo(
                    name = cityName,
                    latitude = latitude,
                    longitude = longitude,
                ),
            )
        }
    }

    private fun loadCurrentLocation() {
        uiState = uiState.copy(isLocationLoading = true)
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { onLocationLoaded(it) }
        }
    }

    private suspend fun onLocationLoaded(location: Location) {
        val currentCity = getCityByCoordinates(
            latitude = location.latitude,
            longitude = location.longitude,
        )?.let(cityDvoMapper::toCityDvo)
        uiState = uiState.copy(
            currentCity = currentCity,
            isLocationLoading = false,
        )
    }

    private fun onNextClick() {
        viewModelScope.launch {
            val currentCity = getCityOrNull() ?: run {
                showErrorMessage()
                return@launch
            }
            saveLocationData(currentCity)
            _uiEvent.send(UiEvent.Success)
        }
    }

    private fun getCityOrNull(): CityDvo? = when {
        uiState.chosenCity != null -> uiState.chosenCity
        uiState.currentCity != null -> uiState.currentCity
        else -> null
    }

    private fun saveLocationData(city: CityDvo?) = city?.let {
        userPreferences.saveCityName(it.name)
        userPreferences.saveLatitude(it.latitude)
        userPreferences.saveLongitude(it.longitude)
    }

    private suspend fun showErrorMessage() = _uiEvent.send(
        UiEvent.ShowSnackbar(message = UiText.StringResource(R.string.error_city_not_chosen)),
    )
}