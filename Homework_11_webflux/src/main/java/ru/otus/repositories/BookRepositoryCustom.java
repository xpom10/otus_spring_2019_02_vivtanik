package ru.otus.repositories;

import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {

    long addComment(String id, Comment comment);

    List<Genre> findGenres();

}
