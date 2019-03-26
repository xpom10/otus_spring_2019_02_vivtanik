package ru.otus.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.Book;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(properties = {
        "spring.datasource.initialization-mode=never",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Sql(scripts = {"classpath:schema.sql", "classpath:testData.sql"})
@RunWith(SpringRunner.class)
public class BookDaoTests {

    @Autowired
    BookDao bookDao;

    @Test
    public void testGetById() {
        Book book = bookDao.getBookById(1);
        assertEquals("Book1", book.getTitle());
        assertEquals(1, book.getBookGenreId());
        assertEquals(1, book.getBookAuthorId());
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
        assertEquals(2, book.getBookGenreId());
        assertEquals(2, book.getBookAuthorId());
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
        Book expectedBook = new Book("Book4", 1, 2);
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
