package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.BookAuthor;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<BookAuthor, Long> {

    Optional<BookAuthor> findBookAuthorById(long id);

    List<BookAuthor> findAll();

    Optional<BookAuthor> findAuthorByAuthorName(String name);
}
