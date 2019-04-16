package ru.otus.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByBookId(long bookId);
}
