package io.mochadwi.spacenews.data.remote.response

import io.mochadwi.spacenews.domain.model.Article

/**
 * Represents the paginated response structure for Articles from the API.
 */
data class ArticleResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article> = emptyList()
)