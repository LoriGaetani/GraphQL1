package com.example.bookservice.dto

data class AuthorResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val birthYear: Int?,
    val books: List<BookSummaryResponse>,
)

data class AuthorSummaryResponse(
    val id: Long?,
    val firstName: String,
    val lastName: String,
)
