package io.mochadwi.spacenews.data.remote.response

import io.mochadwi.spacenews.domain.model.Report

/**
 * Represents the paginated response structure for Reports from the API.
 */
data class ReportResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Report> = emptyList()
)