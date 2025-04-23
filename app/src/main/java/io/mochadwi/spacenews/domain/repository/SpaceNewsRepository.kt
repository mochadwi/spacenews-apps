package io.mochadwi.spacenews.domain.repository

import io.mochadwi.spacenews.domain.model.Article
import io.mochadwi.spacenews.domain.model.Blog
import io.mochadwi.spacenews.domain.model.Report

/**
 * Interface defining the contract for accessing space news data.
 * This acts as the boundary between the domain and data layers.
 */
interface SpaceNewsRepository {

    suspend fun getArticles(
        limit: Int,
        offset: Int,
        newsSite: String? = null,
        ordering: String? = null,
        search: String? = null
    ): Result<List<Article>> // Use Result for better error handling

    suspend fun getBlogs(
        limit: Int,
        offset: Int,
        newsSite: String? = null,
        ordering: String? = null,
        search: String? = null
    ): Result<List<Blog>>

    suspend fun getReports(
        limit: Int,
        offset: Int,
        newsSite: String? = null,
        ordering: String? = null,
        search: String? = null
    ): Result<List<Report>>

    // TODO: Add methods for local data operations (e.g., saving/retrieving recent searches)
}