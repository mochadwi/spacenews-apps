package io.mochadwi.spacenews.data.remote.response

import io.mochadwi.spacenews.domain.model.Blog

/**
 * Represents the paginated response structure for Blogs from the API.
 */
data class BlogResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Blog> = emptyList()
)