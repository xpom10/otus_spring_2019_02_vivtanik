package ru.otus.dao;

import ru.otus.domain.BookAuthor;

import java.util.List;

public interface AuthorDao {

    BookAuthor getAuthorById(int id);

    List<BookAuthor> getAuthors();

    BookAuthor getAuthorByName(String name);

    int createAuthor(BookAuthor author);

    int deleteAuthor(int id);
}
