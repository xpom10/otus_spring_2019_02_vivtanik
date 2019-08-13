package ru.otus.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {

    Flux<Book> findBookByAuthor_Id(String id);

    Mono<Void> deleteBookByAuthorId(String id);

}
