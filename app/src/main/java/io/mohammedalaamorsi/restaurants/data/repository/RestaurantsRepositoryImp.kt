package io.mohammedalaamorsi.restaurants.data.repository

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import io.mohammedalaamorsi.restaurants.data.remote.RemoteRestaurantsDataSource
import io.mohammedalaamorsi.restaurants.domain.RestaurantsRepository
import kotlinx.coroutines.flow.Flow

class RestaurantsRepositoryImp(
    private val remoteRestaurantsDataSource: RemoteRestaurantsDataSource,
) : RestaurantsRepository {


    override suspend fun fetchRestaurants(
        regionId: String,
        query: String?
    ): Flow<RestaurantsResponse<List<Data>>> {
        return remoteRestaurantsDataSource.fetchRestaurants(regionId, query)

    }

    override suspend fun fetchRestaurantDetails(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>> {
        return remoteRestaurantsDataSource.fetchRestaurantDetails(id)
    }
}
