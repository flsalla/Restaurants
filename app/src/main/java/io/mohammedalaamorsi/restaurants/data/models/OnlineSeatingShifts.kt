package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class OnlineSeatingShifts(
    val data: List<DataType>? = null,
)
