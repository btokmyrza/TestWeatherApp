package kz.btokmyrza.weatherapp.presentation.feature.welcome

import androidx.compose.runtime.Stable
import kz.btokmyrza.weatherapp.presentation.model.CityDvo

@Stable
internal data class WelcomeUiState(
    val isLocationLoading: Boolean = false,
    val currentCity: CityDvo? = null,
    val chosenCity: CityDvo? = null,
)