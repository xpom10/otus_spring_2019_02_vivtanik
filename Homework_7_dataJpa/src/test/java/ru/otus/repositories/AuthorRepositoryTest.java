package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookAuthor;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@RunWith(SpringRunner.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testGetList() {
        List<BookAuthor> bookAuthors = authorRepository.getAuthors();
        assertEquals(3, bookAuthors.size());
    }

    @Test
    public void testAuthorNames() {
        List<BookAuthor> bookAuthors = authorRepository.getAuthors();
        bookAuthors.forEach(author -> assertTrue(author.getAuthorName().matches("^Author[123]$")));
    }

    @Test
    public void testGetAuthorById() {
        BookAuthor bookAuthor = authorRepository.getAuthorById(1);
        assertEquals("Author1", bookAuthor.getAuthorName());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        BookAuthor bookAuthor = authorRepository.getAuthorById(10);
        assertNull(bookAuthor);
    }

    @Test
    public void testGetAuthorByName() {
        BookAuthor bookAuthor = authorRepository.getAuthorByName("Author1");
        assertEquals(1, bookAuthor.getAuthorBookId());
    }

    @Test
    public void testGetAuthorByNameNotFound() {
        BookAuthor bookAuthor = authorRepository.getAuthorByName("Author10");
        assertNull(bookAuthor);
    }

    @Test
    public void testCreateAuthor() {
        BookAuthor bookAuthor = new BookAuthor("Author4");
        long id = authorRepository.createAuthor(bookAuthor);
        assertTrue(id > 0);
        BookAuthor bookAuthorFromDb = authorRepository.getAuthorByName("Author4");
        assertNotNull(bookAuthor);
    }

    @Test
    public void testDeleteAuthor() {
        long count = authorRepository.deleteAuthor(3);
        assertEquals(1, count);
        List<BookAuthor> bookAuthors = authorRepository.getAuthors();
        assertEquals(2, bookAuthors.size());
    }

}
