package kz.btokmyrza.weatherapp.presentation.model

import androidx.annotation.DrawableRes
import kz.btokmyrza.weatherapp.R

internal sealed class WeatherTypeDvo(
    val description: String,
    @DrawableRes
    val iconRes: Int,
) {

    data object ClearSky : WeatherTypeDvo(
        description = "Clear sky",
        iconRes = R.drawable.ic_sunny,
    )

    data object MainlyClear : WeatherTypeDvo(
        description = "Mainly clear",
        iconRes = R.drawable.ic_cloudy,
    )

    data object PartlyCloudy : WeatherTypeDvo(
        description = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy,
    )

    data object Overcast : WeatherTypeDvo(
        description = "Overcast",
        iconRes = R.drawable.ic_cloudy,
    )

    data object Foggy : WeatherTypeDvo(
        description = "Foggy",
        iconRes = R.drawable.ic_very_cloudy,
    )

    data object DepositingRimeFog : WeatherTypeDvo(
        description = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy,
    )

    data object LightDrizzle : WeatherTypeDvo(
        description = "Light drizzle",
        iconRes = R.drawable.ic_rainshower,
    )

    data object ModerateDrizzle : WeatherTypeDvo(
        description = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower,
    )

    data object DenseDrizzle : WeatherTypeDvo(
        description = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower,
    )

    data object LightFreezingDrizzle : WeatherTypeDvo(
        description = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
    )

    data object DenseFreezingDrizzle : WeatherTypeDvo(
        description = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
    )

    data object SlightRain : WeatherTypeDvo(
        description = "Slight rain",
        iconRes = R.drawable.ic_rainy,
    )

    data object ModerateRain : WeatherTypeDvo(
        description = "Rainy",
        iconRes = R.drawable.ic_rainy,
    )

    data object HeavyRain : WeatherTypeDvo(
        description = "Heavy rain",
        iconRes = R.drawable.ic_rainy,
    )

    data object HeavyFreezingRain : WeatherTypeDvo(
        description = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy,
    )

    data object SlightSnowFall : WeatherTypeDvo(
        description = "Slight snow fall",
        iconRes = R.drawable.ic_snowy,
    )

    data object ModerateSnowFall : WeatherTypeDvo(
        description = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow,
    )

    data object HeavySnowFall : WeatherTypeDvo(
        description = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow,
    )

    data object SnowGrains : WeatherTypeDvo(
        description = "Snow grains",
        iconRes = R.drawable.ic_heavysnow,
    )

    data object SlightRainShowers : WeatherTypeDvo(
        description = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower,
    )

    data object ModerateRainShowers : WeatherTypeDvo(
        description = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower,
    )

    data object ViolentRainShowers : WeatherTypeDvo(
        description = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower,
    )

    data object SlightSnowShowers : WeatherTypeDvo(
        description = "Light snow showers",
        iconRes = R.drawable.ic_snowy,
    )

    data object HeavySnowShowers : WeatherTypeDvo(
        description = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy,
    )

    data object ModerateThunderstorm : WeatherTypeDvo(
        description = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder,
    )

    data object SlightHailThunderstorm : WeatherTypeDvo(
        description = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder,
    )

    data object HeavyHailThunderstorm : WeatherTypeDvo(
        description = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder,
    )

    companion object {

        fun toWeatherTypeDvo(code: Int): WeatherTypeDvo {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}