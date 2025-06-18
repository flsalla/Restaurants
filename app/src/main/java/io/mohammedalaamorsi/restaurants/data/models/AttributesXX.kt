package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributesXX(
    val average: String? = null,
    @SerialName("days_of_week")
    val daysOfWeek: List<Int>? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("first_seating")
    val firstSeating: Int? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("last_seating")
    val lastSeating: Int? = null,
    val reviews: List<Review>? = null,
    @SerialName("shift_type")
    val shiftType: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    val total: Int? = null
)
