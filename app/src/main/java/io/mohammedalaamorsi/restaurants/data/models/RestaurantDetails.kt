package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDetails(
    @SerialName("address_line_1")
    val addressLine1: String? = null,
    @SerialName("address_line_2")
    val addressLine2: String?  = null,
    val alcohol: Boolean? = null,
    val city: String? = null,
    val cuisine: String? = null,
    @SerialName("custom_confirmation_comments")
    val customConfirmationComments: String? = null,
    val deal: String? = null,
    val description: String? = null,
    val difficult: Boolean? = null,
    @SerialName("establishment_type")
    val establishmentType: String? = null,
    @SerialName("external_ratings_url")
    val externalRatingsUrl: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("image_urls")
    val imageUrls: List<String>? = null,
    val key: String? = null,
    val labels: List<String>? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerialName("menu_url")
    val menuUrl: String? = null,
    val name: String? = null,
    @SerialName("neighborhood_name")
    val neighborhoodName: String? = null,
    val notice: String? = null,
    @SerialName("operating_hours")
    val operatingHours: String? = null,
    @SerialName("outdoor_seating")
    val outdoorSeating: Boolean? = null,
    val phone: String? = null,
    @SerialName("postal_code")
    val postalCode: String? = null,
    @SerialName("price_level")
    val priceLevel: Int? = null,
    val province: String? = null,
    @SerialName("ratings_average")
    val ratingsAverage: String? = null,
    @SerialName("ratings_count")
    val ratingsCount: Int? = null,
    @SerialName("ratings_img")
    val ratingsImg: String? = null,
    @SerialName("relationship_type")
    val relationshipType: String? = null,
    @SerialName("require_booking_preference_enabled")
    val requireBookingPreferenceEnabled: Boolean? = null,
    @SerialName("reservation_notice_duration")
    val reservationNoticeDuration: Int? = null,
    val slug: String? = null,
    val smoking: Boolean? = null,
    @SerialName("terms_and_conditions")
    val termsAndConditions: String? = null,
    val valet: Boolean? = null
)
