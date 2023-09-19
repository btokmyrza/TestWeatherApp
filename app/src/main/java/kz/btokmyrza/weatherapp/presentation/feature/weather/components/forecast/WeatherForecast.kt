package kz.btokmyrza.weatherapp.presentation.feature.weather.components.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo
import kz.btokmyrza.weatherapp.presentation.model.WeatherTypeDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.LocalDateTime

@Composable
internal fun WeatherForecast(
    modifier: Modifier = Modifier,
    day: String,
    weatherDataPerDay: List<WeatherDvo.WeatherDetailDvo>,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = day,
            fontSize = 20.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(weatherDataPerDay) { weatherData ->
                HourlyWeatherDisplay(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(horizontal = 16.dp),
                    formattedTime = weatherData.getFormattedTime(),
                    weatherTypeIconRes = weatherData.weatherType.iconRes,
                    temperatureCelsius = 20.0,
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherForecastPreview() {
    val stubWeatherDataPerDay = listOf(
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
    )
    WeatherAppTheme {
        WeatherForecast(
            modifier = Modifier.padding(8.dp),
            day = "Today",
            weatherDataPerDay = stubWeatherDataPerDay,
        )
    }
}