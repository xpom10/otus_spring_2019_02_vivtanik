package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;
import ru.otus.repositories.BookRepositoryJpa;

import java.util.List;

import static org.junit.Assert.*;


@DataJpaTest
@Import(BookRepositoryJpa.class)
@RunWith(SpringRunner.class)
public class BookRepositoryTests {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Test
    public void testGetById() {
        Book book = bookRepositoryJpa.getBookById(1);
        assertEquals("Book1", book.getTitle());
        assertEquals(1, book.getGenre().getBookGenreId());
        assertEquals(1, book.getAuthor().getAuthorBookId());
    }

    @Test
    public void testGetByIdNotFound() {
        Book book = bookRepositoryJpa.getBookById(10);
        assertNull(book);
    }

    @Test
    public void testGetByTitle() {
        Book book = bookRepositoryJpa.getBookByTitle("Book2");
        assertEquals(2, book.getId());
        assertEquals(2, book.getGenre().getBookGenreId());
        assertEquals(2, book.getAuthor().getAuthorBookId());
    }

    @Test
    public void testGetByTitleNotFound() {
        Book book = bookRepositoryJpa.getBookByTitle("Book4");
        assertNull(book);
    }

    @Test
    public void testCount() {
        long actualCount = bookRepositoryJpa.count();
        assertEquals(3, actualCount);
    }

    @Test
    public void testList() {
        List<Book> books = bookRepositoryJpa.getBooks();
        assertEquals(3, books.size());
        books.forEach(book -> assertTrue(book.getTitle().matches("^Book[123]$")));
    }

    @Test
    public void testCreateBook() {
        BookAuthor author = new BookAuthor(1, "Author1");
        BookGenre genre = new BookGenre(1, "Genre1");
        Book expectedBook = new Book("Book4", author, genre);
        long id = bookRepositoryJpa.createBook(expectedBook);
        expectedBook.setId(id);
        Book actualBook = bookRepositoryJpa.getBookById(id);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void testDeleteBook() {
        long count = bookRepositoryJpa.deleteBookById(3);
        assertTrue(count > 0);
        long actualCount = bookRepositoryJpa.count();
        assertEquals(2, actualCount);
    }


}
