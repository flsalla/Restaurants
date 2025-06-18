package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsResponse<T>(
    val data: T? = null,
    val links: Links? = null,
    val meta: Meta? = null
)
