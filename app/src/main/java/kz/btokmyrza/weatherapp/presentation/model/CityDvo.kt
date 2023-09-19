package kz.btokmyrza.weatherapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class CityDvo(
    val name: String = "",
    val nameRu: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val countryCode: String = "",
    val state: String = "",
) : Parcelable