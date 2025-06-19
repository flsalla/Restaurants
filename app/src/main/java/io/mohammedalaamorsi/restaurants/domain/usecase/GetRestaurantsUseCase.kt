package io.mohammedalaamorsi.restaurants.domain.usecase

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.RestaurantsResponse
import io.mohammedalaamorsi.restaurants.domain.RestaurantsRepository
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetRestaurantsUseCase(
    private val restaurantsRepository: RestaurantsRepository,
    private val dispatchersProvider: DispatchersProvider,
) {
    suspend operator fun invoke(
        regionId: String,
        query: String?
    ): Flow<RestaurantsResponse<List<Data>>> {
        return restaurantsRepository
            .fetchRestaurants(regionId = regionId, query = query)
            .flowOn(dispatchersProvider.io)
    }
}
