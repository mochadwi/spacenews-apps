package io.mochadwi.spacenews.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for an Article from the Spaceflight News API.
 */
@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "news_site") val newsSite: String?,
    @Json(name = "summary") val summary: String?,
    @Json(name = "published_at") val publishedAt: String?, // Consider converting to Date/DateTime
    @Json(name = "updated_at") val updatedAt: String?, // Consider converting to Date/DateTime
    @Json(name = "featured") val featured: Boolean?,
    @Json(name = "launches") val launches: List<LaunchDto>?,
    @Json(name = "events") val events: List<EventDto>?
)

// Placeholder DTOs for nested objects - Refine based on actual API response
@JsonClass(generateAdapter = true)
data class LaunchDto(
    @Json(name = "launch_id") val launchId: String?,
    @Json(name = "provider") val provider: String?
)

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "event_id") val eventId: Int?,
    @Json(name = "provider") val provider: String?
)