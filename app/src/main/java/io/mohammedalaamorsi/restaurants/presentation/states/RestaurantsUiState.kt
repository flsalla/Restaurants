package io.mohammedalaamorsi.restaurants.presentation.states

import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.utils.UiText


sealed class RestaurantsUiState {
    data object Loading : RestaurantsUiState()
    data object Empty : RestaurantsUiState()
    data class Result(val data: List<Data>) : RestaurantsUiState()
    data class Error(val errorMessage: UiText) : RestaurantsUiState()
}

data class UiState(
    val loadedState: RestaurantsUiState = RestaurantsUiState.Empty,
    val isLoading: Boolean = true,
    val restaurantsState: RestaurantsUiState = RestaurantsUiState.Empty,
    val searchQuery: String = "",
    val isSearchEnabled: Boolean = false
) {
    val restaurants: List<Data>
        get() = (restaurantsState as? RestaurantsUiState.Result)?.data ?: emptyList()
}
