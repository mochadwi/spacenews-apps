package io.mochadwi.spacenews.domain.model

data class Article(
    val id: Int,
    val title: String? = null,
    val url: String? = null,
    val imageUrl: String? = null,
    val newsSite: String? = null,
    val summary: String? = null,
    val publishedAt: String? = null, // Keep as String for now, format in Presentation
    val updatedAt: String? = null,
    val featured: Boolean = false,
    val launches: List<Launch> = emptyList(),
    val events: List<Event> = emptyList()
)