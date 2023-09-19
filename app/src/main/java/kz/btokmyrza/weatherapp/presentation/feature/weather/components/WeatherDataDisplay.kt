package kz.btokmyrza.weatherapp.presentation.feature.weather.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
internal fun WeatherDataDisplay(
    modifier: Modifier = Modifier,
    value: Int,
    unit: String,
    icon: ImageVector,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value$unit",
            style = textStyle,
        )
    }
}

@Preview
@Composable
private fun WeatherDataDisplayPreview() {
    WeatherAppTheme {
        WeatherDataDisplay(
            modifier = Modifier.padding(8.dp),
            value = 7,
            unit = "km/h",
            icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
        )
    }
}