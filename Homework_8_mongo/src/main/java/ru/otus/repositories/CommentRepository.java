package ru.otus.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {

    List<Comment> findAllByBookId(long bookId);
}
