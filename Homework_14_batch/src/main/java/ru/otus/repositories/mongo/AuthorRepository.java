package ru.otus.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, Long> {

    Optional<Author> findAuthorByAuthorName(String name);

}
