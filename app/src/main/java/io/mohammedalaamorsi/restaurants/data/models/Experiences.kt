package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Experiences(
    val data: List<String>?  = null,
)
