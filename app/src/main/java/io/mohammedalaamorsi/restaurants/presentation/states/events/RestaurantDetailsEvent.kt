package io.mohammedalaamorsi.restaurants.presentation.states.events


sealed interface RestaurantDetailsEvent {
    data class GetRestaurantDetails(val id: String) : RestaurantDetailsEvent
}
