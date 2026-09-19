package com.example.bff

import com.example.bff.client.BookServiceAuthorResponse
import com.example.bff.client.BookServiceBookResponse
import com.example.bff.client.BookServiceClient
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class AuthorController(
    private val bookServiceClient: BookServiceClient,
) {

    @QueryMapping
    fun getAllAuthors(): List<BookServiceAuthorResponse> =
        bookServiceClient.findAllAuthors()

    @QueryMapping
    fun getAuthorById(@Argument id: Long): BookServiceAuthorResponse? =
        bookServiceClient.findAuthorById(id)

    @BatchMapping(typeName = "Author", field = "books")
    fun books(authors: List<BookServiceAuthorResponse>): Map<BookServiceAuthorResponse, List<BookServiceBookResponse>> {
        val bookIds = authors
            .flatMap { author -> author.books.mapNotNull { it.id } }
            .distinct()

        val booksById = bookServiceClient.findBooksByIds(bookIds)
            .mapNotNull { book -> book.id?.let { it to book } }
            .toMap()

        return authors.associateWith { author ->
            author.books.mapNotNull { summary ->
                summary.id?.let { booksById[it] }
            }
        }
    }
}
