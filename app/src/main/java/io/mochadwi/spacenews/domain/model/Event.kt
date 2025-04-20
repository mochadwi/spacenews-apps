package io.mochadwi.spacenews.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Event(
    val event_id: Int? = null,
    val provider: String? = null
) : Parcelable