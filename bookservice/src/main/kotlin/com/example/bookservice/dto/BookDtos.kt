package com.example.bookservice.dto

data class BookResponse(
    val id: Long?,
    val title: String,
    val isbn: String,
    val publishedYear: Int?,
    val authors: List<AuthorSummaryResponse>,
)

data class BookSummaryResponse(
    val id: Long?,
    val title: String,
)
