package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.BookGenre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<BookGenre, Long> {

    Optional<BookGenre> findGenreById(long id);

    List<BookGenre> findAll();

    Optional<BookGenre> findGenreByGenre(String name);
}
