package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.BookGenre;

import java.util.List;

public interface GenreRepository extends CrudRepository<BookGenre, Long> {

    BookGenre findGenreById(long id);

    List<BookGenre> findAll();

    BookGenre findGenreByGenre(String name);
}
