package io.mochadwi.spacenews.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for a Report from the Spaceflight News API.
 */
@JsonClass(generateAdapter = true)
data class ReportDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "news_site") val newsSite: String?,
    @Json(name = "summary") val summary: String?,
    @Json(name = "published_at") val publishedAt: String?, // Consider converting to Date/DateTime
    @Json(name = "updated_at") val updatedAt: String? // Consider converting to Date/DateTime
)
// Note: Reports might have a simpler structure compared to Articles/Blogs.
// Adjust fields based on the actual API response for reports.