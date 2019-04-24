package ru.otus.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findBookById(@Param("id") long id);

    List<Book> findAll();

    Optional<Book> findBookByTitle(String title);

    long deleteBookById(long id);
}
