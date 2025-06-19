package io.mohammedalaamorsi.restaurants.di

import android.app.Application
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.presentation.restaurant_details.RestaurantDetailsViewModel
import io.mohammedalaamorsi.restaurants.presentation.restaurants_list.RestaurantsListViewModel
import io.mohammedalaamorsi.restaurants.utils.DispatchersProvider
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { DispatchersProvider() }
    viewModelOf(::RestaurantsListViewModel)
    viewModel { params ->
        RestaurantDetailsViewModel(
            id = params.get(),
            dispatchersProvider = get(),
            getRestaurantDetailsUseCase = get(),
        )
    }
    single { Application() }
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
            prettyPrint = true
            encodeDefaults = true
            useAlternativeNames = true
            serializersModule = SerializersModule {
                contextual(RestaurantDetails::class, RestaurantDetails.serializer())
            }

        }
    }
}
