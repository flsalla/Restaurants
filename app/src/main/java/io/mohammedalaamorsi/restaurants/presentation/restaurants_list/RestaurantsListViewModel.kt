package io.mohammedalaamorsi.restaurants.presentation.restaurants_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mohammedalaamorsi.restaurants.domain.usecase.GetRestaurantsUseCase
import io.mohammedalaamorsi.restaurants.presentation.states.RestaurantsUiState
import io.mohammedalaamorsi.restaurants.presentation.states.effects.Effect
import io.mohammedalaamorsi.restaurants.presentation.states.events.RestaurantsEvent
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class RestaurantsListViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    private val _state = MutableStateFlow<RestaurantsUiState>(RestaurantsUiState.Loading)
    val state: StateFlow<RestaurantsUiState> = _state

    init {
        onEvent(RestaurantsEvent.FetchRestaurants)
    }


    fun onEvent(event: RestaurantsEvent) = viewModelScope.launch(dispatchersProvider.io) {
        when (event) {
            RestaurantsEvent.FetchRestaurants -> {
                getRestaurants()
            }
            is RestaurantsEvent.OnSearch -> {
                // Handle search functionality here
                // For now, just fetch all restaurants
                getRestaurants()
            }
            is RestaurantsEvent.ShowError -> {
                _state.value = RestaurantsUiState.Error(event.message)
            }
        }
    }

    private suspend fun getRestaurants() {
        getRestaurantsUseCase.invoke("3906535a-d96c-47cf-99b0-009fc9e038e0").onSuccess {
            it.collect { response ->
                _state.value = response.data?.let { result ->
                    if (result.isEmpty()) RestaurantsUiState.Empty else RestaurantsUiState.Result(result)
                } ?: RestaurantsUiState.Empty
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
