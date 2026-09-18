package com.example.bookservice.service

import com.example.bookservice.dto.AuthorResponse
import com.example.bookservice.dto.BookSummaryResponse
import com.example.bookservice.repository.AuthorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<AuthorResponse> =
        authorRepository.findAll()
            .sortedWith(compareBy({ it.lastName }, { it.firstName }))
            .map { author ->
                AuthorResponse(
                    id = author.id,
                    firstName = author.firstName,
                    lastName = author.lastName,
                    birthYear = author.birthYear,
                    books = author.books
                        .sortedBy { it.title }
                        .map { book ->
                            BookSummaryResponse(
                                id = book.id,
                                title = book.title,
                            )
                        },
                )
            }
}
