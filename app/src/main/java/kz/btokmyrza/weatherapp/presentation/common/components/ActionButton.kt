package kz.btokmyrza.weatherapp.presentation.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
internal fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@Preview
@Composable
private fun ActionButtonEnabledPreview() {
    WeatherAppTheme {
        ActionButton(
            modifier = Modifier.padding(8.dp),
            isEnabled = true,
            text = "Action Button Text",
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun ActionButtonDisabledPreview() {
    WeatherAppTheme {
        ActionButton(
            modifier = Modifier.padding(8.dp),
            text = "Action Button Text",
            isEnabled = false,
            onClick = {},
        )
    }
}