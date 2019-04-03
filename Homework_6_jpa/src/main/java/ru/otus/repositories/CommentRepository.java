package ru.otus.repositories;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository {

    long createComment(Comment comment);

    List<Comment> getAllCommentForBook(long bookId);
}
