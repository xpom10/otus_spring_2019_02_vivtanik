package ru.otus.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("select b from Book b join fetch b.author join fetch b.genre where b.id = :id")
    Optional<Book> findBookById(@Param("id") long id);

    List<Book> findAll();

    Optional<Book> findBookByTitle(String title);

    long deleteBookById(long id);
}
