package com.example.bff.client

data class BookServiceAuthorResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val birthYear: Int?,
    val books: List<BookServiceBookSummaryResponse>,
)

data class BookServiceAuthorSummaryResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
)

data class BookServiceBookResponse(
    val id: Long?,
    val title: String,
    val isbn: String,
    val publishedYear: Int?,
    val authors: List<BookServiceAuthorSummaryResponse>,
)

data class BookServiceBookSummaryResponse(
    val id: Long?,
    val title: String,
)
