package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDetailsResponse< T>(
    @Contextual val  data: T? = null,
    val included: List<Included>? = null
)
