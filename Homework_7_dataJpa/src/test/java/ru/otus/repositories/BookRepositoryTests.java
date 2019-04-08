package ru.otus.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;

import java.util.List;

import static org.junit.Assert.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetById() {
        Book book = bookRepository.findBookById(1);
        assertEquals("Book1", book.getTitle());
        assertEquals(1, book.getGenre().getId());
        assertEquals(1, book.getAuthor().getId());
    }

    @Test
    public void testGetByIdNotFound() {
        Book book = bookRepository.findBookById(10);
        assertNull(book);
    }

    @Test
    public void testGetByTitle() {
        Book book = bookRepository.findBookByTitle("Book2");
        assertEquals(2, book.getId());
        assertEquals(2, book.getGenre().getId());
        assertEquals(2, book.getAuthor().getId());
    }

    @Test
    public void testGetByTitleNotFound() {
        Book book = bookRepository.findBookByTitle("Book4");
        assertNull(book);
    }

    @Test
    public void testCount() {
        long actualCount = bookRepository.count();
        assertEquals(3, actualCount);
    }

    @Test
    public void testList() {
        List<Book> books = bookRepository.findAll();
        assertEquals(3, books.size());
        books.forEach(book -> assertTrue(book.getTitle().matches("^Book[123]$")));
    }

    @Test
    public void testCreateBook() {
        BookAuthor author = new BookAuthor(1, "Author1");
        BookGenre genre = new BookGenre(1, "Genre1");
        Book expectedBook = new Book("Book4", author, genre);
        bookRepository.save(expectedBook);
        Book actualBook = bookRepository.findBookById(expectedBook.getId());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void testDeleteBook() {
        long count = bookRepository.deleteBookById(3);
        assertTrue(count > 0);
        long actualCount = bookRepository.count();
        assertEquals(2, actualCount);
    }


}
