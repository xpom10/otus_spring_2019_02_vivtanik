package ru.otus.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {

    Flux<Book> findBookByAuthor_Id(String id);

    Mono<Book> findBookByTitle(String title);

    void deleteBookByAuthorId(String id);

}
