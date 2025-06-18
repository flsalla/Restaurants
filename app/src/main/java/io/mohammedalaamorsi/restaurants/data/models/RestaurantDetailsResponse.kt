package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDetailsResponse<T>(
    val data: T? = null,
    val included: List<Included>? = null
)
