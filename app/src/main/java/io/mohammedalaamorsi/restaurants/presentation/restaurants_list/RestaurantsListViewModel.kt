package io.mohammedalaamorsi.restaurants.presentation.restaurants_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mohammedalaamorsi.restaurants.domain.usecase.GetRestaurantsUseCase
import io.mohammedalaamorsi.restaurants.presentation.states.RestaurantsUiState
import io.mohammedalaamorsi.restaurants.presentation.states.UiState
import io.mohammedalaamorsi.restaurants.presentation.states.effects.Effect
import io.mohammedalaamorsi.restaurants.presentation.states.events.RestaurantsEvent
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import io.mohammedalaamorsi.restaurants.utils.UiText
import io.mohammedalaamorsi.resturants.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RestaurantsListViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private val _searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val searchQuery = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null,
        )


    init {
        onEvent(RestaurantsEvent.FetchRestaurants)
        viewModelScope.launch {
            observeSearchQuery()
        }
    }

    fun onEvent(event: RestaurantsEvent) = viewModelScope.launch(dispatchersProvider.io) {
        when (event) {
            is RestaurantsEvent.FetchRestaurants -> {
                getRestaurants()
            }

            is RestaurantsEvent.DoSearch -> {
                _searchQuery.value = event.query
                _state.update {
                    it.copy(searchQuery = event.query)
                }
            }
        }
    }

    private suspend fun getRestaurants() {
        _state.update { it.copy(isLoading = true) }
        doFetch(null)
    }

    private suspend fun observeSearchQuery() {
        searchQuery.filter { null != it }.collect { query ->
            if (query?.isBlank()?.not() == true) {
                viewModelScope.launch {
                    doFetch(query)
                }
            } else {
                if (_state.value.isSearchEnabled) {
                    _state.update {
                        it.copy(
                            restaurantsState = _state.value.loadedState,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private suspend fun doFetch(searchQuery: String?) {
        getRestaurantsUseCase.invoke(
            regionId = "3906535a-d96c-47cf-99b0-009fc9e038e0",
            searchQuery
        )
            .catch { error ->
                _state.update {
                    it.copy(
                        restaurantsState = RestaurantsUiState.Error(
                            UiText.StringResource(
                                R.string.error_fetching_restaurants,
                                listOf(error.message ?: "Unknown error")
                            )
                        ),
                        isLoading = false,
                        isSearchEnabled = true
                    )
                }
            }.collect { result ->
                if (result.data?.isNotEmpty() == true) {
                    _state.update {
                        it.copy(
                            isSearchEnabled = true,
                            loadedState = if (searchQuery == null) RestaurantsUiState.Result(result.data)
                            else _state.value.loadedState,
                            restaurantsState = RestaurantsUiState.Result(result.data),
                            isLoading = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isSearchEnabled = true,
                            restaurantsState = RestaurantsUiState.Empty,
                            isLoading = false
                        )
                    }
                }
            }
    }


} 
