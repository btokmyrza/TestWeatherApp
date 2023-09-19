package kz.btokmyrza.weatherapp.presentation.feature.welcome

internal sealed class WelcomeEvent {
    data object OnGetCurrentLocationClick : WelcomeEvent()
    data object OnNextClick : WelcomeEvent()
}
