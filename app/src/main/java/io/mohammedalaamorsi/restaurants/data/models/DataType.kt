package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class DataType(
    val id: String? = null,
    val type: String? = null
)
