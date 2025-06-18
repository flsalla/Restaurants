package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val first: String? = null,
    val last: String? = null,
    val next: String? = null,
    val prev: String? = null
)
