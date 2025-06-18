package io.mohammedalaamorsi.restaurants.data.remote

import io.ktor.client.HttpClient

interface HttpClientProvider {
    val httpClientImp: HttpClient
}
