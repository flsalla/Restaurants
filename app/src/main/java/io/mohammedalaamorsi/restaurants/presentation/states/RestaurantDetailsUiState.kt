package io.mohammedalaamorsi.restaurants.presentation.states

import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetailsResponse
import io.mohammedalaamorsi.restaurants.utils.UiText


sealed class RestaurantDetailsUiState {
    data object Loading : RestaurantDetailsUiState()
    data class Result(val data: RestaurantDetailsResponse<RestaurantDetails>) : RestaurantDetailsUiState()
    data class Error(val message: UiText) : RestaurantDetailsUiState()
}
