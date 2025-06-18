package io.mohammedalaamorsi.restaurants.presentation.states

import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse


sealed class RestaurantDetailsUiState {
    data object Loading : RestaurantDetailsUiState()
    data class Result(val data: RestaurantDetailsResponse<RestaurantDetails>) : RestaurantDetailsUiState()
    data class Error(val message: String) : RestaurantDetailsUiState()
}
