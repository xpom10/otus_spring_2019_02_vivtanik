package ru.otus.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {

    Optional<Book> findBookByTitle(String title);

    void deleteBookByAuthorId(String authorId);

}
