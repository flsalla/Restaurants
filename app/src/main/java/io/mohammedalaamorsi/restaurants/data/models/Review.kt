package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("published_date")
    val publishedDate: String? = null,
    val rating: Int? = null,
    @SerialName("rating_image_url")
    val ratingImageUrl: String? = null,
    val text: String? = null,
    val title: String? = null,
    val url: String? = null,
    val user: User? = null
)
