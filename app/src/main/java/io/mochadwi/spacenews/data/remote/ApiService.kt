package io.mochadwi.spacenews.data.remote

import io.mochadwi.spacenews.data.remote.dto.ArticleDto
import io.mochadwi.spacenews.data.remote.dto.BlogDto
import io.mochadwi.spacenews.data.remote.dto.ReportDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for Spaceflight News API v4.
 * https://api.spaceflightnewsapi.net/v4/docs/
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://api.spaceflightnewsapi.net/v4/"
    }

    // --- Articles --- //
    @GET("articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("news_site") newsSite: String? = null,
        @Query("ordering") ordering: String? = null, // e.g., "published_at" or "-published_at"
        @Query("search") search: String? = null
    ): Response<List<ArticleDto>>

    @GET("articles/count")
    suspend fun getArticlesCount(): Response<Int>

    // --- Blogs --- //
    @GET("blogs")
    suspend fun getBlogs(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("news_site") newsSite: String? = null,
        @Query("ordering") ordering: String? = null,
        @Query("search") search: String? = null
    ): Response<List<BlogDto>>

    @GET("blogs/count")
    suspend fun getBlogsCount(): Response<Int>

    // --- Reports --- //
    @GET("reports")
    suspend fun getReports(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("news_site") newsSite: String? = null,
        @Query("ordering") ordering: String? = null,
        @Query("search") search: String? = null
    ): Response<List<ReportDto>>

    @GET("reports/count")
    suspend fun getReportsCount(): Response<Int>
}