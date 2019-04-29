package ru.otus.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, Long> {

    Optional<Author> findAuthorByAuthorName(String name);

}
