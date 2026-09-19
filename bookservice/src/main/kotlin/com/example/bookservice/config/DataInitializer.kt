package com.example.bookservice.config

import com.example.bookservice.entity.Author
import com.example.bookservice.entity.Book
import com.example.bookservice.repository.AuthorRepository
import com.example.bookservice.repository.BookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitializer {
    @Bean
    fun seedLibraryData(
        authorRepository: AuthorRepository,
        bookRepository: BookRepository,
    ) = CommandLineRunner {
        if (bookRepository.count() > 0) {
            return@CommandLineRunner
        }

        val leGuin = Author(firstName = "Ursula K.", lastName = "Le Guin", birthYear = 1929)
        val pratchett = Author(firstName = "Terry", lastName = "Pratchett", birthYear = 1948)
        val gaiman = Author(firstName = "Neil", lastName = "Gaiman", birthYear = 1960)
        val calvino = Author(firstName = "Italo", lastName = "Calvino", birthYear = 1923)
        val asimov = Author(firstName = "Isaac", lastName = "Asimov", birthYear = 1920)

        authorRepository.saveAll(listOf(leGuin, pratchett, gaiman, calvino, asimov))

        val leftHand = Book(
            title = "The Left Hand of Darkness",
            isbn = "9780441478125",
            publishedYear = 1969,
        )
        leftHand.addAuthor(leGuin)

        val goodOmens = Book(
            title = "Good Omens",
            isbn = "9780060853983",
            publishedYear = 1990,
        )
        goodOmens.addAuthor(pratchett)
        goodOmens.addAuthor(gaiman)

        val invisibleCities = Book(
            title = "Le citta invisibili",
            isbn = "9788804668233",
            publishedYear = 1972,
        )
        invisibleCities.addAuthor(calvino)

        val foundation = Book(
            title = "Foundation",
            isbn = "9780553293357",
            publishedYear = 1951,
        )
        foundation.addAuthor(asimov)

        bookRepository.saveAll(listOf(leftHand, goodOmens, invisibleCities, foundation))
    }
}
