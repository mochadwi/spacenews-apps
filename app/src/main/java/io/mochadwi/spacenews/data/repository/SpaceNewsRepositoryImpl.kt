package io.mochadwi.spacenews.data.repository

import io.mochadwi.spacenews.data.remote.ApiService
import io.mochadwi.spacenews.domain.model.Article
import io.mochadwi.spacenews.domain.model.Blog
import io.mochadwi.spacenews.domain.model.Event
import io.mochadwi.spacenews.domain.model.Launch
import io.mochadwi.spacenews.domain.model.Report
import io.mochadwi.spacenews.domain.repository.SpaceNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceNewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    // TODO: Inject DAOs for local data sources later (Room/DataStore)
) : SpaceNewsRepository {

    override suspend fun getArticles(
        limit: Int,
        offset: Int,
        newsSite: String?,
        ordering: String?,
        search: String?
    ): Result<List<Article>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getArticles(limit, offset, newsSite, ordering, search)
            val articles = response.body()?.map { dto ->
                Article(
                    id = dto.id,
                    title = dto.title,
                    url = dto.url,
                    imageUrl = dto.imageUrl,
                    newsSite = dto.newsSite,
                    summary = dto.summary,
                    publishedAt = dto.publishedAt,
                    updatedAt = dto.updatedAt,
                    featured = dto.featured ?: false,
                    launches = dto.launches?.map { Launch(launch_id = it.launchId, provider = it.provider) } ?: emptyList(),
                    events = dto.events?.map { Event(event_id = it.eventId, provider = it.provider) } ?: emptyList()
                )
            } ?: emptyList()
            Result.success(articles)
        } catch (e: Exception) {
            // TODO: Add more specific error handling (Network exceptions, HTTP errors etc.)
            Result.failure(e)
        }
    }

    override suspend fun getBlogs(
        limit: Int,
        offset: Int,
        newsSite: String?,
        ordering: String?,
        search: String?
    ): Result<List<Blog>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getBlogs(limit, offset, newsSite, ordering, search)
            val blogs = response.body()?.map { dto ->
                Blog(
                    id = dto.id,
                    title = dto.title,
                    url = dto.url,
                    image_url = dto.imageUrl,
                    news_site = dto.newsSite,
                    summary = dto.summary,
                    published_at = dto.publishedAt,
                    updated_at = dto.updatedAt,
                    featured = false, // BlogDto does not have a 'featured' field in the current API spec
                    launches = dto.launches?.map { Launch(launch_id = it.launchId, provider = it.provider) } ?: emptyList(),
                    events = dto.events?.map { Event(event_id = it.eventId, provider = it.provider) } ?: emptyList()
                )
            } ?: emptyList()
            Result.success(blogs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getReports(
        limit: Int,
        offset: Int,
        newsSite: String?,
        ordering: String?,
        search: String?
    ): Result<List<Report>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getReports(limit, offset, newsSite, ordering, search)
            val reports = response.body()?.map { dto ->
                Report(
                    id = dto.id,
                    title = dto.title,
                    url = dto.url,
                    image_url = dto.imageUrl,
                    news_site = dto.newsSite,
                    summary = dto.summary,
                    published_at = dto.publishedAt,
                    updated_at = dto.updatedAt
                )
            } ?: emptyList()
            Result.success(reports)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // TODO: Implement methods for local data operations
}