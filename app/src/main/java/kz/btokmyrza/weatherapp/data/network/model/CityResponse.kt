package kz.btokmyrza.weatherapp.data.network.model

import com.squareup.moshi.Json

internal class CityResponse(
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "local_names")
    val localName: CityLocalNameResponse? = null,
    @field:Json(name = "lat")
    val latitude: String? = null,
    @field:Json(name = "lon")
    val longitude: String? = null,
    @field:Json(name = "country")
    val countryCode: String? = null,
    @field:Json(name = "state")
    val state: String? = null,
) {

    class CityLocalNameResponse(
        @field:Json(name = "ru")
        val nameRu: String? = null,
    )
}