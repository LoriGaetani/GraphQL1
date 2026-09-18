package com.example.bookservice.controller

import com.example.bookservice.dto.AuthorResponse
import com.example.bookservice.dto.BookResponse
import com.example.bookservice.service.AuthorService
import com.example.bookservice.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LibraryController(
    private val bookService: BookService,
    private val authorService: AuthorService,
) {
    @GetMapping("/books")
    fun books(): List<BookResponse> = bookService.findAll()

    @GetMapping("/authors")
    fun authors(): List<AuthorResponse> = authorService.findAll()
}
