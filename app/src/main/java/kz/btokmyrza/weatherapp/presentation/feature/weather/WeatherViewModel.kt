package kz.btokmyrza.weatherapp.presentation.feature.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.btokmyrza.weatherapp.domain.preferences.UserPreferences
import kz.btokmyrza.weatherapp.domain.use.cases.GetHourlyWeatherData
import kz.btokmyrza.weatherapp.presentation.mapper.WeatherDvoMapper

internal class WeatherViewModel(
    private val getHourlyWeatherData: GetHourlyWeatherData,
    private val userPreferences: UserPreferences,
    private val weatherDvoMapper: WeatherDvoMapper,
) : ViewModel() {

    var uiState by mutableStateOf(WeatherUiState())
        private set

    init {
        initCityInfo()
        loadWeatherInfo()
    }

    private fun initCityInfo() {
        uiState = uiState.copy(
            cityName = getCityName(),
            latitude = getLatitude(),
            longitude = getLongitude(),
        )
    }

    private fun loadWeatherInfo() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                error = null,
            )
            getHourlyWeatherData(
                latitude = getLatitude(),
                longitude = getLongitude(),
            ).let(weatherDvoMapper::toWeatherDvo).also {
                uiState = uiState.copy(
                    weatherInfo = it,
                    isLoading = false,
                    error = null,
                )
            }
        }
    }

    private fun getCityName(): String = userPreferences.getCityName()

    private fun getLatitude(): Double = userPreferences.getLatitude().toDoubleOrNull() ?: 0.0

    private fun getLongitude(): Double = userPreferences.getLongitude().toDoubleOrNull() ?: 0.0
}