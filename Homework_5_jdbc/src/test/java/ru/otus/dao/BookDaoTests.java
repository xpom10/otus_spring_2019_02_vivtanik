package ru.otus.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;

import java.util.List;

import static org.junit.Assert.*;


@JdbcTest
@Import(BookDaoImpl.class)
@Sql(scripts = {"classpath:schema.sql", "classpath:testData.sql"})
@RunWith(SpringRunner.class)
public class BookDaoTests {

    @Autowired
    BookDao bookDao;

    @Test
    public void testGetById() {
        Book book = bookDao.getBookById(1);
        assertEquals("Book1", book.getTitle());
        assertEquals(1, book.getGenre().getBookGenreId());
        assertEquals(1, book.getAuthor().getAuthorBookId());
    }

    @Test
    public void testGetByIdNotFound() {
        Book book = bookDao.getBookById(10);
        assertNull(book);
    }

    @Test
    public void testGetByTitle() {
        Book book = bookDao.getBookByTitle("Book2");
        assertEquals(2, book.getId());
        assertEquals(2, book.getGenre().getBookGenreId());
        assertEquals(2, book.getAuthor().getAuthorBookId());
    }

    @Test
    public void testGetByTitleNotFound() {
        Book book = bookDao.getBookByTitle("Book4");
        assertNull(book);
    }

    @Test
    public void testCount() {
        int actualCount = bookDao.count();
        assertEquals(3, actualCount);
    }

    @Test
    public void testList() {
        List<Book> books = bookDao.getBooks();
        assertEquals(3, books.size());
        books.forEach(book -> assertTrue(book.getTitle().matches("^Book[123]$")));
    }

    @Test
    public void testCreateBook() {
        BookAuthor author = new BookAuthor(1, "Author1");
        BookGenre genre = new BookGenre(1, "Genre1");
        Book expectedBook = new Book("Book4", author, genre);
        int id = bookDao.createBook(expectedBook);
        expectedBook.setId(id);
        Book actualBook = bookDao.getBookById(id);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void testDeleteBook() {
        int count = bookDao.deleteBookById(3);
        assertTrue(count > 0);
        int actualCount = bookDao.count();
        assertEquals(2, actualCount);
    }


}
