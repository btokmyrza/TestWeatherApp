package kz.btokmyrza.weatherapp.presentation.feature.weather.components.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo
import kz.btokmyrza.weatherapp.presentation.model.WeatherTypeDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.LocalDateTime

@Composable
internal fun HourlyWeatherDisplay(
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    formattedTime: String,
    weatherTypeIconRes: Int,
    temperatureCelsius: Double,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray,
        )
        Image(
            modifier = Modifier.width(40.dp),
            painter = painterResource(id = weatherTypeIconRes),
            contentDescription = null,
        )
        Text(
            text = "${temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun HourlyWeatherDisplayPreview() {
    val stubWeatherDetailDvo = WeatherDvo.WeatherDetailDvo(
        time = LocalDateTime.now(),
        temperatureCelsius = 20.0,
        pressure = 1.0,
        windSpeed = 2.0,
        humidity = 30.0,
        weatherType = WeatherTypeDvo.ClearSky,
    )
    WeatherAppTheme {
        HourlyWeatherDisplay(
            modifier = Modifier.padding(8.dp),
            formattedTime = stubWeatherDetailDvo.getFormattedTime(),
            weatherTypeIconRes = stubWeatherDetailDvo.weatherType.iconRes,
            temperatureCelsius = 20.0,
        )
    }
}