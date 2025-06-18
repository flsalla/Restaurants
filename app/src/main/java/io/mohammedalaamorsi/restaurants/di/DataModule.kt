package io.mohammedalaamorsi.restaurants.di


import io.mohammedalaamorsi.restaurants.data.remote.AndroidClientProvider
import io.mohammedalaamorsi.restaurants.data.remote.HttpClientProvider
import io.mohammedalaamorsi.restaurants.data.remote.KtorHttpClientService
import io.mohammedalaamorsi.restaurants.data.remote.RestaurantsUrlProvider
import io.mohammedalaamorsi.restaurants.data.remote.RemoteRestaurantsDataSource
import io.mohammedalaamorsi.restaurants.data.remote.UrlsProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataModule = module {

    singleOf(::RemoteRestaurantsDataSource)
    factory<HttpClientProvider> { AndroidClientProvider() }
    singleOf(::KtorHttpClientService)
    single<UrlsProvider> { RestaurantsUrlProvider() }
}
