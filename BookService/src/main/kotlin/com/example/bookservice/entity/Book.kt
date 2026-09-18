package com.example.bookservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false, unique = true)
    var isbn: String = "",

    var publishedYear: Int? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_authors",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id")],
    )
    var authors: MutableSet<Author> = mutableSetOf(),
) {
    fun addAuthor(author: Author) {
        authors.add(author)
        author.books.add(this)
    }
}
