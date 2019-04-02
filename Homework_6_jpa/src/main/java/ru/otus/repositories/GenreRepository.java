package ru.otus.repositories;

import ru.otus.domain.BookGenre;

import java.util.List;

public interface GenreRepository {

    BookGenre getGenreById(int id);

    List<BookGenre> getGenres();

    BookGenre getGenreByName(String name);

    long createGenre(String genre);
}
