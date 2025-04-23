package io.mochadwi.spacenews.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for a Blog post from the Spaceflight News API.
 */
@JsonClass(generateAdapter = true)
data class BlogDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "news_site") val newsSite: String?,
    @Json(name = "summary") val summary: String?,
    @Json(name = "published_at") val publishedAt: String?, // Consider converting to Date/DateTime
    @Json(name = "updated_at") val updatedAt: String?, // Consider converting to Date/DateTime
    @Json(name = "launches") val launches: List<LaunchDto>?,
    @Json(name = "events") val events: List<EventDto>?
)
// Note: Reusing LaunchDto and EventDto from ArticleDto.kt
// Ensure these are accessible or defined appropriately if needed in a shared location.