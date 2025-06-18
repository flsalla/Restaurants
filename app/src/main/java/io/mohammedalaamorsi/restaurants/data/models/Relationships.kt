package io.mohammedalaamorsi.restaurants.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Relationships(
    val experiences: Experiences? = null,
    @SerialName("online_seating_shifts")
    val onlineSeatingShifts: OnlineSeatingShifts? = null,
    @SerialName("promotional_groups")
    val promotionalGroups: PromotionalGroups? = null,
    val rating: Rating? = null,
    val region: Region? = null
)
