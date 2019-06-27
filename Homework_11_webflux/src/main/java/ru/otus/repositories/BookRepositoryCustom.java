package ru.otus.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {

    Mono<Comment> addComment(String id, Comment comment);

    Flux<Genre> findGenres();

}
