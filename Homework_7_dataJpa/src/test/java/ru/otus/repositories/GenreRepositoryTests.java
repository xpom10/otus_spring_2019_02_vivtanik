package ru.otus.repositories;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.domain.BookGenre;

import java.util.List;
import java.util.Optional;

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
        Optional<BookGenre> bookGenre = genreRepository.findGenreById(1);
        assertTrue(bookGenre.isPresent());
        assertEquals("Genre1", bookGenre.get().getGenre());
    }

    @Test
    public void testGetGenreByIdNotFound() {
        Optional<BookGenre> bookGenre = genreRepository.findGenreById(10);
        assertFalse(bookGenre.isPresent());
    }

    @Test
    public void testGetGenreByName() {
        Optional<BookGenre> bookGenre = genreRepository.findGenreByGenre("Genre1");
        assertEquals(1, bookGenre.get().getId());
    }

    @Test
    public void testGetGenreByNameNotFound() {
        Optional<BookGenre> bookGenre = genreRepository.findGenreByGenre("Genre10");
        assertFalse(bookGenre.isPresent());
    }

    @Test
    public void testCreateGenre() {
        BookGenre bookGenre = new BookGenre("Genre4");
        genreRepository.save(bookGenre);
        assertTrue(bookGenre.getId() > 0);
        Optional<BookGenre> genre = genreRepository.findGenreByGenre("Genre4");
        assertTrue(genre.isPresent());
    }
}
