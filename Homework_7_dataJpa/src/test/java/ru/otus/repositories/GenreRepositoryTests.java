package ru.otus.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookGenre;
import ru.otus.repositories.GenreRepository;
import ru.otus.repositories.GenreRepositoryJpa;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@RunWith(SpringRunner.class)
public class GenreRepositoryTests {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testGetList() {
        List<BookGenre> bookGenres = genreRepository.getGenres();
        assertEquals(3, bookGenres.size());
    }

    @Test
    public void testGetGenreById() {
        BookGenre bookGenre = genreRepository.getGenreById(1);
        assertEquals("Genre1", bookGenre.getGenre());
    }

    @Test
    public void testGetGenreByIdNotFound() {
        BookGenre bookGenre = genreRepository.getGenreById(10);
        assertNull(bookGenre);
    }

    @Test
    public void testGetGenreByName() {
        BookGenre bookGenre = genreRepository.getGenreByName("Genre1");
        assertEquals(1, bookGenre.getBookGenreId());
    }

    @Test
    public void testGetGenreByNameNotFound() {
        BookGenre bookGenre = genreRepository.getGenreByName("Genre10");
        assertNull(bookGenre);
    }

    @Test
    public void testCreateGenre() {
        BookGenre bookGenre = new BookGenre("Genre4");
        long id = genreRepository.createGenre(bookGenre);
        assertTrue(id > 0);
        BookGenre genre = genreRepository.getGenreByName("Genre4");
        assertNotNull(genre);
    }
}
