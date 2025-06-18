package io.mohammedalaamorsi.restaurants.presentation.states.effects


sealed interface Effect {
    data class ShowSnackbarResource(val messageRes: String): Effect
}
