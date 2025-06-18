package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class Included(
    val attributes: AttributesXX? = null,
    val id: String? = null,
    val type: String? = null
)
