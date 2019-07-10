package ru.otus.repositories.jdbc;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.jdbc.BookAuthor;

import java.util.List;
import java.util.Optional;


public interface AuthorJdbcRepository extends CrudRepository<BookAuthor, Long> {

    Optional<BookAuthor> findBookAuthorById(long id);

    List<BookAuthor> findAll();

    Optional<BookAuthor> findAuthorByAuthorName(String name);
}
