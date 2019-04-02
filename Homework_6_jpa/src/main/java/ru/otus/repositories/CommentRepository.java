package ru.otus.repositories;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void createComment(Comment comment);

    List<Comment> getAllCommentForBook(String bookId);
}
