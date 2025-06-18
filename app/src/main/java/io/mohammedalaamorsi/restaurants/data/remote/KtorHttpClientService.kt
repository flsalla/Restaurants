package io.mohammedalaamorsi.restaurants.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json


class KtorHttpClientService(
    private val httpClientProvider: HttpClientProvider,
    private val json: Json,
) {

    suspend fun <T> loadRemoteData(
        apiPath: String,
        serializer: KSerializer<T>,
    ): Result<T> {
        try {
            val httpClient = httpClientProvider.httpClientImp

            val response: HttpResponse = httpClient.get(apiPath) {
                contentType(ContentType.Application.Json)
            }

            val responseBody: String = response.body()
            val parsedData: T = json.decodeFromString(serializer, responseBody)
            return Result.success(parsedData)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
