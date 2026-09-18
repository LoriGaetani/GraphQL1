package com.example.bookservice.repository

import com.example.bookservice.entity.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long>
