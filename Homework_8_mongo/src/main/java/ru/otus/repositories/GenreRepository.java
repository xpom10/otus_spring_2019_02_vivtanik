package ru.otus.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.BookGenre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<BookGenre, Long> {

    Optional<BookGenre> findGenreById(long id);

    List<BookGenre> findAll();

    Optional<BookGenre> findGenreByGenre(String name);
}
