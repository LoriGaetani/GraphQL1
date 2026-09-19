package com.example.bookservice.repository

import com.example.bookservice.entity.Author
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = ["books"])
    override fun findAll(): MutableList<Author>

    @EntityGraph(attributePaths = ["books"])
    fun findByIdIn(ids: Collection<Long>): List<Author>
}
