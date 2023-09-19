package kz.btokmyrza.weatherapp.presentation.feature.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.presentation.feature.weather.components.WeatherCard
import kz.btokmyrza.weatherapp.presentation.feature.weather.components.forecast.WeatherForecast
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo
import kz.btokmyrza.weatherapp.presentation.model.WeatherTypeDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.Purple40
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime

@Composable
internal fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel(),
) {
    WeatherScreenContent(uiState = viewModel.uiState)
}

@Composable
private fun WeatherScreenContent(
    uiState: WeatherUiState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple40)
                .padding(vertical = 8.dp),
        ) {
            WeatherCard(
                formattedTime = uiState.getCurrentFormattedTime(),
                cityInfo = uiState.cityName.orEmpty(),
                weatherTypeIconRes = uiState.getCurrentWeatherTypeIconRes(),
                weatherDescription = uiState.getCurrentWeatherDescription(),
                temperatureCelsius = uiState.getCurrentWeatherTemperature(),
                pressure = uiState.getCurrentWeatherPressure(),
                windSpeed = uiState.getCurrentWeatherWindSpeed(),
                humidity = uiState.getCurrentWeatherHumidity(),
                backgroundColor = Purple40,
            )
            LazyColumn {
                items(uiState.getNextWeekWeatherData()) {
                    WeatherForecast(
                        modifier = Modifier.padding(top = 8.dp),
                        day = it.firstOrNull()?.getCurrentDate().orEmpty(),
                        weatherDataPerDay = it,
                    )
                }
            }
        }
        AnimatedVisibility(visible = uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        uiState.error?.let { error ->
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private val stubWeatherDataPerDay = mapOf(
    0 to listOf(
        WeatherDvo.WeatherDetailDvo(
            time = LocalDateTime.now().minusHours(1),
            temperatureCelsius = 20.0,
            pressure = 1.0,
            windSpeed = 2.0,
            humidity = 30.0,
            weatherType = WeatherTypeDvo.ClearSky,
        ),
        WeatherDvo.WeatherDetailDvo(
            time = LocalDateTime.now(),
            temperatureCelsius = 20.0,
            pressure = 1.0,
            windSpeed = 2.0,
            humidity = 30.0,
            weatherType = WeatherTypeDvo.MainlyClear,
        ),
        WeatherDvo.WeatherDetailDvo(
            time = LocalDateTime.now().plusHours(1),
            temperatureCelsius = 20.0,
            pressure = 1.0,
            windSpeed = 2.0,
            humidity = 30.0,
            weatherType = WeatherTypeDvo.PartlyCloudy,
        ),
        WeatherDvo.WeatherDetailDvo(
            time = LocalDateTime.now().plusHours(2),
            temperatureCelsius = 20.0,
            pressure = 1.0,
            windSpeed = 2.0,
            humidity = 30.0,
            weatherType = WeatherTypeDvo.Overcast,
        ),
        WeatherDvo.WeatherDetailDvo(
            time = LocalDateTime.now().plusHours(3),
            temperatureCelsius = 20.0,
            pressure = 1.0,
            windSpeed = 2.0,
            humidity = 30.0,
            weatherType = WeatherTypeDvo.Foggy,
        ),
    ),
)

private val stubCurrentWeatherData = WeatherDvo.WeatherDetailDvo(
    time = LocalDateTime.now(),
    temperatureCelsius = 20.0,
    pressure = 1.0,
    windSpeed = 2.0,
    humidity = 30.0,
    weatherType = WeatherTypeDvo.ClearSky,
)

@Preview(showSystemUi = true)
@Composable
private fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreenContent(
            uiState = WeatherUiState(
                weatherInfo = WeatherDvo(
                    weatherDataPerDay = stubWeatherDataPerDay,
                    currentWeatherData = stubCurrentWeatherData,
                ),
                cityName = "Astana",
                latitude = 43.3,
                longitude = 32.3,
                isLoading = false,
                error = null,
            ),
        )
    }
}