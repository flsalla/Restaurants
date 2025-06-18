package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("current_page")
    val currentPage: Int? = null,
    val limit: Int? = null,
    @SerialName("total_count")
    val totalCount: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null
)
