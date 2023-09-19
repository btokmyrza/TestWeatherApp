package kz.btokmyrza.weatherapp.presentation.feature.city.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
internal fun CityItem(
    modifier: Modifier = Modifier,
    city: CityDvo,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
    ) {
        Text(
            text = "${city.name} (${city.nameRu})",
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = "${city.state}, ${city.countryCode}",
            style = MaterialTheme.typography.body2,
        )
        Text(
            text = "x: ${city.latitude}, y: ${city.longitude}",
            style = MaterialTheme.typography.body2,
        )
        Divider(modifier = Modifier.padding(top = 8.dp),)
    }
}

@Preview(showBackground = true)
@Composable
private fun CityItemPreview() {
    WeatherAppTheme {
        CityItem(
            modifier = Modifier.padding(horizontal = 8.dp),
            city = CityDvo(
                name = "Astana",
                nameRu = "Астана",
                latitude = "123.3123",
                longitude = "125.123",
                countryCode = "KZ",
                state = "Aqmola",
            ),
            onClick = {},
        )
    }
}