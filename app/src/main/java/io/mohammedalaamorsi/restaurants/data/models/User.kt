package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_location")
    val userLocation: UserLocation? = null,
    val username: String? = null
)
