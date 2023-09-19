package kz.btokmyrza.weatherapp.presentation.feature.city.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.feature.city.search.components.CityItem
import kz.btokmyrza.weatherapp.presentation.feature.city.search.components.SearchTextField
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.presentation.navigation.NavArgs
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import kz.btokmyrza.weatherapp.util.UiEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun CitySearchScreen(
    viewModel: CitySearchViewModel = koinViewModel(),
    navController: NavHostController,
    onNavigateUp: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }
    CityScreenContent(
        uiState = viewModel.uiState,
        onQueryChange = { viewModel.onEvent(CitySearchEvent.OnQueryChange(query = it)) },
        onSearch = {
            keyboardController?.hide()
            viewModel.onEvent(CitySearchEvent.OnSearch)
        },
        onSearchFocusChange = {
            viewModel.onEvent(CitySearchEvent.OnSearchFocusChange(isFocused = it.isFocused))
        },
        onCityChosen = {
            navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.set(NavArgs.CITY, it)
            viewModel.onEvent(CitySearchEvent.OnCityClick(city = it))
        },
    )
}

@Composable
private fun CityScreenContent(
    uiState: CitySearchUiState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSearchFocusChange: (FocusState) -> Unit,
    onCityChosen: (CityDvo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(),
            text = stringResource(id = R.string.find_your_city),
            style = MaterialTheme.typography.h4,
        )
        SearchTextField(
            modifier = Modifier.padding(top = 8.dp),
            text = uiState.query,
            shouldShowHint = uiState.isHintVisible,
            onValueChange = onQueryChange,
            onSearch = onSearch,
            onFocusChanged = onSearchFocusChange,
        )
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(uiState.cities) { city ->
                CityItem(
                    modifier = Modifier.fillMaxWidth(),
                    city = city,
                    onClick = { onCityChosen(city) },
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CityScreenPreview() {
    WeatherAppTheme {
        CityScreenContent(
            uiState = CitySearchUiState(
                query = "Астана",
                cities = listOf(
                    CityDvo(
                        name = "Astana",
                        nameRu = "Астана",
                        latitude = "51.1282205",
                        longitude = "71.4306682",
                        countryCode = "KZ",
                        state = "Astana",
                    ),
                    CityDvo(
                        name = "Astana",
                        nameRu = "",
                        latitude = "-6.6836347",
                        longitude = "108.5369601",
                        countryCode = "ID",
                        state = "West Java",
                    ),
                ),
            ),
            onQueryChange = {},
            onSearch = {},
            onSearchFocusChange = {},
            onCityChosen = {},
        )
    }
}