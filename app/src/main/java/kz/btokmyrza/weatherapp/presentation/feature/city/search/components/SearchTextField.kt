package kz.btokmyrza.weatherapp.presentation.feature.city.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
internal fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onFocusChanged: (FocusState) -> Unit,
) {
    Box(modifier = modifier) {
        BasicTextField(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .padding(2.dp)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(16.dp)
                .padding(end = 16.dp)
                .onFocusChanged(onFocusChanged = onFocusChanged),
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
        if (shouldShowHint) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = Color.LightGray,
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = onSearch,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
            )
        }
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    WeatherAppTheme {
        SearchTextField(
            modifier = Modifier.padding(8.dp),
            text = "Astana",
            onValueChange = {},
            onSearch = {},
            onFocusChanged = {},
        )
    }
}