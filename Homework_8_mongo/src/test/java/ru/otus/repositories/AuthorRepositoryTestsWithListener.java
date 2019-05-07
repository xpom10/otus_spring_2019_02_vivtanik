package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.config", "ru.otus.repositories", "ru.otus.eventListener"})
@RunWith(SpringRunner.class)
public class AuthorRepositoryTestsWithListener {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testDeleteWithListener() {
        Optional<Author> author = authorRepository.findAuthorByAuthorName("author1");
        author.ifPresent(authorRepository::delete);
        long authorCount = authorRepository.count();
        assertEquals(authorCount, 1);

        long bookCount = bookRepository.count();
        assertEquals(bookCount, 1);
    }
}
