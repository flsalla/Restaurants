package io.mohammedalaamorsi.restaurants.presentation.states.events


sealed interface RestaurantsEvent {
    data object FetchRestaurants : RestaurantsEvent
    data class OnSearch(val query: String) : RestaurantsEvent
    data class ShowError(val message: String) : RestaurantsEvent
}
