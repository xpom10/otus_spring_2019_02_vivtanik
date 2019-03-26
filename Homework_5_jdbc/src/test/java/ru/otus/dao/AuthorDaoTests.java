package ru.otus.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookAuthor;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(properties = {
        "spring.datasource.initialization-mode=never",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
        })
@Sql(scripts = {"classpath:schema.sql", "classpath:testData.sql"})
@RunWith(SpringRunner.class)
public class AuthorDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testGetList() {
        List<BookAuthor> bookAuthors = authorDao.getAuthors();
        assertEquals(3, bookAuthors.size());
    }

    @Test
    public void testAuthorNames() {
        List<BookAuthor> bookAuthors = authorDao.getAuthors();
        bookAuthors.forEach(author -> assertTrue(author.getAuthorName().matches("^Author[123]$")));
    }

    @Test
    public void testGetAuthorById() {
        BookAuthor bookAuthor = authorDao.getAuthorById(1);
        assertEquals("Author1", bookAuthor.getAuthorName());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        BookAuthor bookAuthor = authorDao.getAuthorById(10);
        assertNull(bookAuthor);
    }

    @Test
    public void testGetAuthorByName() {
        BookAuthor bookAuthor = authorDao.getAuthorByName("Author1");
        assertEquals(1, bookAuthor.getAuthorBookId());
    }

    @Test
    public void testGetAuthorByNameNotFound() {
        BookAuthor bookAuthor = authorDao.getAuthorByName("Author10");
        assertNull(bookAuthor);
    }

    @Test
    public void testCreateAuthor() {
        int id = authorDao.createAuthor("Author4");
        assertTrue(id > 0);
        BookAuthor bookAuthor = authorDao.getAuthorByName("Author4");
        assertNotNull(bookAuthor);
    }

    @Test
    public void testDeleteAuthor() {
        int count = authorDao.deleteAuthor(3);
        assertEquals(1, count);
        List<BookAuthor> bookAuthors = authorDao.getAuthors();
        assertEquals(2, bookAuthors.size());
    }

}
