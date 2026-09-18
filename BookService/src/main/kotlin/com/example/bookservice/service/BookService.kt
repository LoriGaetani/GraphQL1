package com.example.bookservice.service

import com.example.bookservice.dto.AuthorSummaryResponse
import com.example.bookservice.dto.BookResponse
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
            .map { book ->
                BookResponse(
                    id = book.id,
                    title = book.title,
                    isbn = book.isbn,
                    publishedYear = book.publishedYear,
                    authors = book.authors
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
}
