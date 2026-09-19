package com.example.bookservice.service

import com.example.bookservice.dto.AuthorSummaryResponse
import com.example.bookservice.dto.BookResponse
import com.example.bookservice.entity.Book
import com.example.bookservice.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<BookResponse> =
        bookRepository.findAll()
            .sortedBy { it.title }
            .map { it.toResponse() }

    @Transactional(readOnly = true)
    fun findByIds(ids: Collection<Long>): List<BookResponse> =
        bookRepository.findByIdIn(ids)
            .sortedBy { it.title }
            .map { it.toResponse() }

    private fun Book.toResponse(): BookResponse =
        BookResponse(
            id = id,
            title = title,
            isbn = isbn,
            publishedYear = publishedYear,
            authors = authors
                .sortedWith(compareBy({ it.lastName }, { it.firstName }))
                .map { author ->
                    AuthorSummaryResponse(
                        id = author.id,
                        firstName = author.firstName,
                        lastName = author.lastName,
                    )
                },
        )
}
