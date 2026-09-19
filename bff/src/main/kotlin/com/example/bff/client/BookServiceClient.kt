package com.example.bff.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class BookServiceClient(
    @Value("\${services.book-service.base-url}") baseUrl: String,
) {
    private val restClient = RestClient.builder()
        .baseUrl(baseUrl)
        .build()

    fun findAllBooks(): List<BookServiceBookResponse> =
        restClient.get()
            .uri("/api/books")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<BookServiceBookResponse>>() {})
            ?: emptyList()

    fun findAllAuthors(): List<BookServiceAuthorResponse> =
        restClient.get()
            .uri("/api/authors")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<BookServiceAuthorResponse>>() {})
            ?: emptyList()

    fun findBookById(id: Long): BookServiceBookResponse? =
        findBooksByIds(listOf(id)).firstOrNull()

    fun findAuthorById(id: Long): BookServiceAuthorResponse? =
        findAuthorsByIds(listOf(id)).firstOrNull()

    fun findBooksByIds(ids: Collection<Long>): List<BookServiceBookResponse> {
        if (ids.isEmpty()) return emptyList()

        return restClient.get()
            .uri { builder ->
                builder
                    .path("/api/books")
                    .queryParam("ids", *ids.toTypedArray())
                    .build()
            }
            .retrieve()
            .body(object : ParameterizedTypeReference<List<BookServiceBookResponse>>() {})
            ?: emptyList()
    }

    fun findAuthorsByIds(ids: Collection<Long>): List<BookServiceAuthorResponse> {
        if (ids.isEmpty()) return emptyList()

        return restClient.get()
            .uri { builder ->
                builder
                    .path("/api/authors")
                    .queryParam("ids", *ids.toTypedArray())
                    .build()
            }
            .retrieve()
            .body(object : ParameterizedTypeReference<List<BookServiceAuthorResponse>>() {})
            ?: emptyList()
    }
}
