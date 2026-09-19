package com.example.bookservice.repository

import com.example.bookservice.entity.Book
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = ["authors"])
    override fun findAll(): MutableList<Book>

    @EntityGraph(attributePaths = ["authors"])
    fun findByIdIn(ids: Collection<Long>): List<Book>
}
