package io.mohammedalaamorsi.restaurants.presentation.restaurant_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mohammedalaamorsi.restaurants.domain.usecase.GetRestaurantDetailsUseCase
import io.mohammedalaamorsi.restaurants.presentation.states.RestaurantDetailsUiState
import io.mohammedalaamorsi.restaurants.presentation.states.effects.Effect
import io.mohammedalaamorsi.restaurants.presentation.states.events.RestaurantDetailsEvent
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class RestaurantDetailsViewModel(
    val id: String,
    private val getRestaurantDetailsUseCase: GetRestaurantDetailsUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    private val _state =
        MutableStateFlow<RestaurantDetailsUiState>(RestaurantDetailsUiState.Loading)
    val state: StateFlow<RestaurantDetailsUiState> = _state

    init {
        onEvent(RestaurantDetailsEvent.GetRestaurantDetails(id))
    }

    fun onEvent(event: RestaurantDetailsEvent) = viewModelScope.launch(dispatchersProvider.io) {
        when (event) {
            is RestaurantDetailsEvent.GetRestaurantDetails -> {
                loadRestaurantDetailsSection(event.id)
            }
        }
    }

    private suspend fun loadRestaurantDetailsSection(id: String) {
        getRestaurantDetailsUseCase.invoke(id).onSuccess {
            it.collect { response ->
                if (null == response.data) {
                    _effects.emit(Effect.ShowSnackbarResource(messageRes = "No restaurant found"))
                } else {
                    _state.value = RestaurantDetailsUiState.Result(response)
                }
            }
        }.onFailure {
            it.message?.let { messageRes ->
                _effects.emit(
                    Effect.ShowSnackbarResource(messageRes = messageRes)
                )
            }
        }
    }

}
