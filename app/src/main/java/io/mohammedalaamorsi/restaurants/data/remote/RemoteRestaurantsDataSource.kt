package io.mohammedalaamorsi.restaurants.data.remote

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.builtins.ListSerializer

class RemoteRestaurantsDataSource(
    private val ktorHttpClientService: KtorHttpClientService,
    private val urlsProvider: UrlsProvider,
) {

    fun fetchRestaurants(regionId: String): Flow<RestaurantsResponse<List<Data>>> {
        return flow {
            ktorHttpClientService.loadRemoteData(
                apiPath = urlsProvider.fetchRestaurants(regionId),
                serializer = RestaurantsResponse.serializer(ListSerializer(Data.serializer()))
            ).fold(
                onSuccess = { response ->
                    emit(response)
                },
                onFailure = { error ->
                    throw error
                },
            )
        }
    }

    fun fetchRestaurantDetails(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>> {
        return flow {
            ktorHttpClientService.loadRemoteData(
                apiPath = urlsProvider.fetchRestaurantDetails(id),
                serializer = RestaurantDetailsResponse.serializer(RestaurantDetails.serializer())
            ).fold(
                onSuccess = { response ->
                    emit(response)
                },
                onFailure = { error ->
                    throw error
                },
            )
        }
    }

}
