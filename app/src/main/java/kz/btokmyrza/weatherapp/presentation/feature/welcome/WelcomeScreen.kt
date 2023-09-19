package kz.btokmyrza.weatherapp.presentation.feature.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.common.components.ActionButton
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import kz.btokmyrza.weatherapp.util.UiEvent
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun WelcomeScreen(
    city: CityDvo? = null,
    viewModel: WelcomeViewModel = koinViewModel(),
    scaffoldState: ScaffoldState,
    onFindCityClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message.asString(context),
                )
                else -> Unit
            }
        }
    }
    WelcomeScreenContent(
        city = city,
        uiState = viewModel.uiState,
        onGetCurrentLocationClick = { viewModel.onEvent(WelcomeEvent.OnGetCurrentLocationClick) },
        onFindCityClick = onFindCityClick,
        onNextClick = onNextClick,
    )
}

@Composable
private fun WelcomeScreenContent(
    city: CityDvo? = null,
    uiState: WelcomeUiState,
    onGetCurrentLocationClick: () -> Unit,
    onFindCityClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        city?.let {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(
                    id = R.string.your_chosen_city_,
                    formatArgs = arrayOf(it.name),
                ),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
            )
        }
        AnimatedVisibility(
            visible = uiState.isLocationLoading.not() && uiState.chosenCity == null,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(
                    id = R.string.your_location_,
                    formatArgs = arrayOf(uiState.currentCity?.name.orEmpty()),
                ),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
            )
        }
        AnimatedVisibility(visible = uiState.isLocationLoading) {
            CircularProgressIndicator()
        }
        ActionButton(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.load_your_location),
            onClick = onGetCurrentLocationClick,
        )
        ActionButton(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.find_your_city),
            onClick = onFindCityClick,
        )
        ActionButton(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    WeatherAppTheme {
        WelcomeScreenContent(
            uiState = WelcomeUiState(
                currentCity = CityDvo(
                    name = "Astana",
                    nameRu = "Астана",
                    latitude = "51.1282205",
                    longitude = "71.4306682",
                    countryCode = "KZ",
                    state = "Astana",
                ),
            ),
            onGetCurrentLocationClick = {},
            onFindCityClick = {},
            onNextClick = {},
        )
    }
}