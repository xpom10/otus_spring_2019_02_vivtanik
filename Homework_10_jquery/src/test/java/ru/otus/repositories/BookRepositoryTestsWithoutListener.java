package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.MappingException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.config", "ru.otus.repositories"})
@RunWith(SpringRunner.class)
public class BookRepositoryTestsWithoutListener {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookCount() {
        long count = bookRepository.count();
        assertEquals(3, count);
    }

    @Test
    public void testGetAllGenres() {
        List<Genre> genres = bookRepository.findGenres();
        genres.forEach(genre -> assertTrue(genre.getGenreName().matches("genre[12]")));
    }

    @Test
    public void testAddComment() {
        String title = "book1";
        bookRepository.addComment(title, new Comment("book1comment3"));
        Optional<Book> book = bookRepository.findBookByTitle(title);
        book.ifPresent(actualBook ->
                actualBook.getComments().forEach(comment ->
                        assertTrue(comment.getComment().matches("book1comment[123]"))));

    }

    @Test
    public void testAddNewBookWithoutAuthor() {
        Book newBook = new Book("book4", new Genre("genre3"), new Author("author3"));
        assertThrows(MappingException.class, () -> bookRepository.save(newBook));
    }

}
