package io.mochadwi.spacenews.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Launch(
    val launch_id: String? = null,
    val provider: String? = null
) : Parcelable