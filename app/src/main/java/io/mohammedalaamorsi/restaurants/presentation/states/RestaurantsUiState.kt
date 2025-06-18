package io.mohammedalaamorsi.restaurants.presentation.states

import io.mohammedalaamorsi.restaurants.data.models.Data


sealed class RestaurantsUiState {
    data object Loading : RestaurantsUiState()
    data object Empty : RestaurantsUiState()
    data class Result(val data: List<Data>) : RestaurantsUiState()
    data class Error(val errorMessage: String) : RestaurantsUiState()
}
