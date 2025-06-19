package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDetails(
    val attributes: AttributesXXX? = null,
    val id: String?= null,
    val relationships: Relationships?= null,
    val type: String?= null
)
