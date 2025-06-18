package io.mohammedalaamorsi.restaurants.domain

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {
    suspend fun fetchRestaurants(regionId: String): Flow<RestaurantsResponse<List<Data>>>
    suspend fun fetchRestaurantDetails(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>>
}
