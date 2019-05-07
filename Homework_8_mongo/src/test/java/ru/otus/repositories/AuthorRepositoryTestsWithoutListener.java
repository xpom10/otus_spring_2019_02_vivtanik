package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.config", "ru.otus.repositories"})
@RunWith(SpringRunner.class)
public class AuthorRepositoryTestsWithoutListener {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCount() {
        long authors = authorRepository.count();
        assertEquals(authors, 2);
    }

    @Test
    public void testFindAll() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> assertTrue(
                author.getAuthorName().matches("author[12]")
                && author.getId() != null));
    }

    @Test
    public void testDeleteWithoutListener() {
        Optional<Author> author = authorRepository.findAuthorByAuthorName("author1");
        author.ifPresent(authorRepository::delete);
        long authorCount = authorRepository.count();
        assertEquals(authorCount, 1);

        long bookCount = bookRepository.count();
        assertEquals(bookCount, 3);
    }
}
