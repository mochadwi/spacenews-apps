package io.mochadwi.spacenews.domain.usecase

import io.mochadwi.spacenews.domain.model.Article
import io.mochadwi.spacenews.domain.repository.SpaceNewsRepository
import javax.inject.Inject

/**
 * Use case for fetching articles.
 */
class GetArticlesUseCase @Inject constructor(
    private val repository: SpaceNewsRepository
) {
    suspend operator fun invoke(
        limit: Int = 10,
        offset: Int = 0,
        newsSite: String? = null,
        ordering: String? = null,
        search: String? = null
    ): Result<List<Article>> {
        return repository.getArticles(limit, offset, newsSite, ordering, search)
    }
}