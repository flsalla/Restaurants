package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    @SerialName("address_line_1")
    val addressLine1: String? = null,
    val cuisine: String? = null,
    val difficult: Boolean? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val labels: List<String>? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerialName("menu_url")
    val menuUrl: String? = null,
    val name: String? = null,
    val phone: String? = null,
    @SerialName("price_level")
    val priceLevel: Int? = null,
    @SerialName("ratings_average")
    val ratingsAverage: String? = null,
    @SerialName("ratings_count")
    val ratingsCount: Int? = null,
    @SerialName("require_booking_preference_enabled")
    val requireBookingPreferenceEnabled: Boolean? = null
)
