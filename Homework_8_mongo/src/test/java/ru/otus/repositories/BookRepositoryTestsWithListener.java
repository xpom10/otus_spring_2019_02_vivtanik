package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.config", "ru.otus.repositories", "ru.otus.eventListener"})
@RunWith(SpringRunner.class)
public class BookRepositoryTestsWithListener {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testAddNewBookWithoutAuthor() {
        long beforeAuthorCount = authorRepository.count();
        long beforeBookCount = bookRepository.count();
        Book newBook = new Book("book4", new Genre("genre3"), new Author("author3"));
        Book book = bookRepository.save(newBook);
        assertNotNull(book.getId());

        long authorCount = authorRepository.count();
        assertEquals(authorCount, beforeAuthorCount + 1);

        long bookCount = bookRepository.count();
        assertEquals(bookCount, beforeBookCount + 1);


    }
}
