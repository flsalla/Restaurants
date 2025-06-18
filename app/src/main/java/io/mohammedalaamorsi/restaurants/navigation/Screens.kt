package io.mohammedalaamorsi.restaurants.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Screens {
    @Parcelize
    @Serializable
    data object RestaurantsList : Screens, Parcelable

    @Parcelize
    @Serializable
    data class RestaurantDetail(
        val id: String
    ) : Screens, Parcelable
}
