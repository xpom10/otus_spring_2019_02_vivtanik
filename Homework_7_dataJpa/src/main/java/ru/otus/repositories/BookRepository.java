package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookById(long id);

    List<Book> findAll();

    Book findBookByTitle(String title);

    long deleteBookById(long id);
}
