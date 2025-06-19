package io.mohammedalaamorsi.restaurants.data.remote

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.ListSerializer

class RemoteRestaurantsDataSource(
    private val ktorHttpClientService: KtorHttpClientService,
    private val urlsProvider: UrlsProvider,
) {

    fun fetchRestaurants(regionId: String, query: String?): Flow<RestaurantsResponse<List<Data>>> {
        return ktorHttpClientService.loadRemoteData<RestaurantsResponse<List<Data>>>(
            apiPath = urlsProvider.fetchRestaurants(regionId, query),
            serializer = RestaurantsResponse.serializer(ListSerializer(Data.serializer()))
        )

    }

    fun fetchRestaurantDetails(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>> {
        return ktorHttpClientService.loadRemoteData<RestaurantDetailsResponse<RestaurantDetails>>(
            apiPath = urlsProvider.fetchRestaurantDetails(id),
            serializer = RestaurantDetailsResponse.serializer(RestaurantDetails.serializer())
        )
    }

}
