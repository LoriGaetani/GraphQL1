package com.example.bookservice.service

import com.example.bookservice.dto.AuthorResponse
import com.example.bookservice.dto.BookSummaryResponse
import com.example.bookservice.entity.Author
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
            .map { it.toResponse() }

    @Transactional(readOnly = true)
    fun findByIds(ids: Collection<Long>): List<AuthorResponse> =
        authorRepository.findByIdIn(ids)
            .sortedWith(compareBy({ it.lastName }, { it.firstName }))
            .map { it.toResponse() }

    private fun Author.toResponse(): AuthorResponse =
        AuthorResponse(
            id = id,
            firstName = firstName,
            lastName = lastName,
            birthYear = birthYear,
            books = books
                .sortedBy { it.title }
                .map { book ->
                    BookSummaryResponse(
                        id = book.id,
                        title = book.title,
                    )
                },
        )
}
