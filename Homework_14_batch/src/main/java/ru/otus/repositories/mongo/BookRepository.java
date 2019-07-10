package ru.otus.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.Book;

public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {

    void deleteBookByAuthorId(String authorId);

}
