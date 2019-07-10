package ru.otus.repositories.mongo;

import ru.otus.domain.mongo.Genre;

import java.util.List;

public interface BookRepositoryCustom {

    List<Genre> findGenres();

}
