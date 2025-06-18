package io.mohammedalaamorsi.restaurants.data.repository

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import io.mohammedalaamorsi.restaurants.data.remote.RemoteRestaurantsDataSource
import io.mohammedalaamorsi.restaurants.domain.RestaurantsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RestaurantsRepositoryImp(
    private val remoteRestaurantsDataSource: RemoteRestaurantsDataSource,
) : RestaurantsRepository {


    override suspend fun fetchRestaurants(regionId: String): Flow<RestaurantsResponse<List<Data>>> {
        return flow {
            remoteRestaurantsDataSource.fetchRestaurants(regionId)
                .collect { restaurantsResponse ->
                    Result.success(emit(restaurantsResponse))
                }
        }
    }

    override suspend fun fetchRestaurantDetails(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>> {
        return flow {
            remoteRestaurantsDataSource.fetchRestaurantDetails(id)
                .collect { restaurantDetailsResponse ->
                    emit(restaurantDetailsResponse)
                }
        }
    }
}
