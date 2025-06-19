package io.mohammedalaamorsi.restaurants.presentation.states.events


sealed interface RestaurantsEvent {
    data object FetchRestaurants : RestaurantsEvent
    data class DoSearch(val query: String) : RestaurantsEvent
}
