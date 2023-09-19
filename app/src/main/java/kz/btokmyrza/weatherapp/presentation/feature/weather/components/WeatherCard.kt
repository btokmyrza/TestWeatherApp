package kz.btokmyrza.weatherapp.presentation.feature.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo
import kz.btokmyrza.weatherapp.presentation.model.WeatherTypeDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.Purple40
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.LocalDateTime
import kotlin.math.roundToInt

@Composable
internal fun WeatherCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cityInfo: String,
    formattedTime: String,
    weatherTypeIconRes: Int,
    weatherDescription: String,
    temperatureCelsius: Double,
    pressure: Double,
    windSpeed: Double,
    humidity: Double,
) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = cityInfo,
                    color = Color.White,
                )
                Text(
                    text = "Today $formattedTime",
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = Modifier.width(120.dp),
                painter = painterResource(id = weatherTypeIconRes),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${temperatureCelsius}°C",
                fontSize = 35.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = weatherDescription,
                fontSize = 20.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    value = pressure.roundToInt(),
                    unit = "hpa",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                WeatherDataDisplay(
                    value = humidity.roundToInt(),
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
                WeatherDataDisplay(
                    value = windSpeed.roundToInt(),
                    unit = "km/h",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    val stubWeatherDetailDvo = WeatherDvo.WeatherDetailDvo(
        time = LocalDateTime.now(),
        temperatureCelsius = 20.0,
        pressure = 1.0,
        windSpeed = 2.0,
        humidity = 30.0,
        weatherType = WeatherTypeDvo.ClearSky,
    )
    WeatherAppTheme {
        WeatherCard(
            backgroundColor = Purple40,
            cityInfo = "Astana",
            formattedTime = stubWeatherDetailDvo.getFormattedTime(),
            weatherTypeIconRes = stubWeatherDetailDvo.weatherType.iconRes,
            weatherDescription = stubWeatherDetailDvo.weatherType.description,
            temperatureCelsius = stubWeatherDetailDvo.temperatureCelsius,
            pressure = stubWeatherDetailDvo.pressure,
            windSpeed = stubWeatherDetailDvo.windSpeed,
            humidity = stubWeatherDetailDvo.humidity,
        )
    }
}