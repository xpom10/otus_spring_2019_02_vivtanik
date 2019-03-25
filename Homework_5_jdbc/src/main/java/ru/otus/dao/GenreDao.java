package ru.otus.dao;

import ru.otus.domain.BookGenre;

import java.util.List;

public interface GenreDao {

    BookGenre getGenreById(int id);

    List<BookGenre> getGenres();

    BookGenre getGenreByName(String name);

    int createGenre(String genre);
}
