package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class PromotionalGroups(
    val data: List<DataType>? = null,
)
