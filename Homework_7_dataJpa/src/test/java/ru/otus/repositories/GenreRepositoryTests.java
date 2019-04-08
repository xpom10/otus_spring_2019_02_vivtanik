package ru.otus.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookGenre;

import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GenreRepositoryTests {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testGetList() {
        List<BookGenre> bookGenres = genreRepository.findAll();
        assertEquals(3, bookGenres.size());
    }

    @Test
    public void testGetGenreById() {
        BookGenre bookGenre = genreRepository.findGenreById(1);
        assertEquals("Genre1", bookGenre.getGenre());
    }

    @Test
    public void testGetGenreByIdNotFound() {
        BookGenre bookGenre = genreRepository.findGenreById(10);
        assertNull(bookGenre);
    }

    @Test
    public void testGetGenreByName() {
        BookGenre bookGenre = genreRepository.findGenreByGenre("Genre1");
        assertEquals(1, bookGenre.getId());
    }

    @Test
    public void testGetGenreByNameNotFound() {
        BookGenre bookGenre = genreRepository.findGenreByGenre("Genre10");
        assertNull(bookGenre);
    }

    @Test
    public void testCreateGenre() {
        BookGenre bookGenre = new BookGenre("Genre4");
        genreRepository.save(bookGenre);
        assertTrue(bookGenre.getId() > 0);
        BookGenre genre = genreRepository.findGenreByGenre("Genre4");
        assertNotNull(genre);
    }
}
