package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookAuthor;

import java.util.List;

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
        BookAuthor bookAuthor = authorRepository.findBookAuthorById(1);
        assertEquals("Author1", bookAuthor.getAuthorName());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        BookAuthor bookAuthor = authorRepository.findBookAuthorById(10);
        assertNull(bookAuthor);
    }

    @Test
    public void testGetAuthorByName() {
        BookAuthor bookAuthor = authorRepository.findAuthorByAuthorName("Author1");
        assertEquals(1, bookAuthor.getId());
    }

    @Test
    public void testGetAuthorByNameNotFound() {
        BookAuthor bookAuthor = authorRepository.findAuthorByAuthorName("Author10");
        assertNull(bookAuthor);
    }

    @Test
    public void testCreateAuthor() {
        BookAuthor bookAuthor = new BookAuthor("Author4");
        authorRepository.save(bookAuthor);
        assertTrue(bookAuthor.getId() > 0);
        BookAuthor bookAuthorFromDb = authorRepository.findAuthorByAuthorName("Author4");
        assertNotNull(bookAuthorFromDb);
    }

    @Test
    public void testDeleteAuthor() {
        authorRepository.deleteById(3L);
        List<BookAuthor> bookAuthors = authorRepository.findAll();
        assertEquals(2, bookAuthors.size());
    }

}
