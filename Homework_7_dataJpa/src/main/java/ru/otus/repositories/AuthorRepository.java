package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.BookAuthor;

import java.util.List;

public interface AuthorRepository extends CrudRepository<BookAuthor, Long> {

    BookAuthor findBookAuthorById(long id);

    List<BookAuthor> findAll();

    BookAuthor findAuthorByAuthorName(String name);
}
