package com.example.bookservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "authors")
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var firstName: String = "",

    @Column(nullable = false)
    var lastName: String = "",

    var birthYear: Int? = null,

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    var books: MutableSet<Book> = mutableSetOf(),
)
