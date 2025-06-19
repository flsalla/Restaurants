package io.mohammedalaamorsi.restaurants.domain.usecase

import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.domain.RestaurantsRepository
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetRestaurantDetailsUseCase(
    private val restaurantsRepository: RestaurantsRepository,
    private val dispatchersProvider: DispatchersProvider,
) {
    suspend operator fun invoke(id: String): Flow<RestaurantDetailsResponse<RestaurantDetails>> {
        return restaurantsRepository
            .fetchRestaurantDetails(id)
            .flowOn(dispatchersProvider.io)

    }
}
