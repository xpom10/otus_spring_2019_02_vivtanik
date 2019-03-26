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
import ru.otus.domain.BookGenre;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(properties = {
        "spring.datasource.initialization-mode=never",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Sql(scripts = {"classpath:schema.sql", "classpath:testData.sql"})
@RunWith(SpringRunner.class)
public class GenreDaoTests {
    
    @Autowired
    GenreDao genreDao;

    @Test
    public void testGetList() {
        List<BookGenre> bookGenres = genreDao.getGenres();
        assertEquals(3, bookGenres.size());
    }

    @Test
    public void testGetGenreById() {
        BookGenre bookGenre = genreDao.getGenreById(1);
        assertEquals("Genre1", bookGenre.getGenre());
    }

    @Test
    public void testGetGenreByIdNotFound() {
        BookGenre bookGenre = genreDao.getGenreById(10);
        assertNull(bookGenre);
    }

    @Test
    public void testGetGenreByName() {
        BookGenre bookGenre = genreDao.getGenreByName("Genre1");
        assertEquals(1, bookGenre.getBookGenreId());
    }

    @Test
    public void testGetGenreByNameNotFound() {
        BookGenre bookGenre = genreDao.getGenreByName("Genre10");
        assertNull(bookGenre);
    }

    @Test
    public void testCreateGenre() {
        int id = genreDao.createGenre("Genre4");
        assertTrue(id > 0);
        BookGenre genre = genreDao.getGenreByName("Genre4");
        assertNotNull(genre);
    }
}
