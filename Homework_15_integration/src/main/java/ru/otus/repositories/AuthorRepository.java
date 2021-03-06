package ru.otus.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Author> findAuthorByAuthorName(String name);

}
