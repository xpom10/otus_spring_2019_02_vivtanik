package ru.otus.repositories;

import reactor.core.publisher.Flux;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {

    long addComment(String id, Comment comment);

    Flux<Genre> findGenres();

}
