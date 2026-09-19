package com.example.bff

import com.example.bff.client.BookServiceAuthorResponse
import com.example.bff.client.BookServiceBookResponse
import com.example.bff.client.BookServiceClient
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BookController(
    private val bookServiceClient: BookServiceClient,
) {

    @QueryMapping
    fun getAllBooks(): List<BookServiceBookResponse> =
        bookServiceClient.findAllBooks()

    @QueryMapping
    fun getBookById(@Argument id: Long): BookServiceBookResponse? =
        bookServiceClient.findBookById(id)

    @BatchMapping(typeName = "Book", field = "authors")
    fun authors(books: List<BookServiceBookResponse>): Map<BookServiceBookResponse, List<BookServiceAuthorResponse>> {
        val authorIds = books
            .flatMap { book -> book.authors.mapNotNull { it.id } }
            .distinct()

        val authorsById = bookServiceClient.findAuthorsByIds(authorIds)
            .mapNotNull { author -> author.id?.let { it to author } }
            .toMap()

        return books.associateWith { book ->
            book.authors.mapNotNull { summary ->
                summary.id?.let { authorsById[it] }
            }
        }
    }
}
