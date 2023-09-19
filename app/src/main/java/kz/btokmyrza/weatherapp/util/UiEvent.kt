package kz.btokmyrza.weatherapp.util

internal sealed class UiEvent {
    data object Success : UiEvent()
    data object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}