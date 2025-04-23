package io.mochadwi.spacenews.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Report(
    val id: Int,
    val title: String? = null,
    val url: String? = null,
    val image_url: String? = null,
    val news_site: String? = null,
    val summary: String? = null,
    val published_at: String? = null,
    val updated_at: String? = null
    // Reports might not have launches/events, adjust if API differs
) : Parcelable