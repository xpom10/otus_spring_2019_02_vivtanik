package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookAuthor;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testGetList() {
        List<BookAuthor> bookAuthors = authorRepository.findAll();
        assertEquals(3, bookAuthors.size());
    }

    @Test
    public void testAuthorNames() {
        List<BookAuthor> bookAuthors = authorRepository.findAll();
        bookAuthors.forEach(author -> assertTrue(author.getAuthorName().matches("^Author[123]$")));
    }

    @Test
    public void testGetAuthorById() {
        Optional<BookAuthor> bookAuthor = authorRepository.findBookAuthorById(1);
        assertTrue(bookAuthor.isPresent());
        assertEquals("Author1", bookAuthor.get().getAuthorName());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        Optional<BookAuthor> bookAuthor = authorRepository.findBookAuthorById(10);
        assertFalse(bookAuthor.isPresent());
    }

    @Test
    public void testGetAuthorByName() {
        Optional<BookAuthor> bookAuthor = authorRepository.findAuthorByAuthorName("Author1");
        assertEquals(1, bookAuthor.get().getId());
    }

    @Test
    public void testGetAuthorByNameNotFound() {
        Optional<BookAuthor> bookAuthor = authorRepository.findAuthorByAuthorName("Author10");
        assertFalse(bookAuthor.isPresent());
    }

    @Test
    public void testCreateAuthor() {
        BookAuthor bookAuthor = new BookAuthor("Author4");
        authorRepository.save(bookAuthor);
        assertTrue(bookAuthor.getId() > 0);
        Optional<BookAuthor> bookAuthorFromDb = authorRepository.findAuthorByAuthorName("Author4");
        assertTrue(bookAuthorFromDb.isPresent());
    }

    @Test
    public void testDeleteAuthor() {
        authorRepository.deleteById(3L);
        List<BookAuthor> bookAuthors = authorRepository.findAll();
        assertEquals(2, bookAuthors.size());
    }

}
