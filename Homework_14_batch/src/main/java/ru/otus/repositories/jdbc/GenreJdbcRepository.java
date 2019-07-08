package ru.otus.repositories.jdbc;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.jdbc.BookGenre;

import java.util.List;
import java.util.Optional;

public interface GenreJdbcRepository extends CrudRepository<BookGenre, Long> {

    Optional<BookGenre> findGenreById(long id);

    List<BookGenre> findAll();

    Optional<BookGenre> findGenreByGenre(String name);
}
