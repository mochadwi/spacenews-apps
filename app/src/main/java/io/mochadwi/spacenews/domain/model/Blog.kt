package io.mochadwi.spacenews.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Blog(
    val id: Int,
    val title: String? = null,
    val url: String? = null,
    val image_url: String? = null,
    val news_site: String? = null,
    val summary: String? = null,
    val published_at: String? = null,
    val updated_at: String? = null,
    val featured: Boolean? = null,
    val launches: List<Launch>? = emptyList(),
    val events: List<Event>? = emptyList()
) : Parcelable