package ru.otus.repositories;

import ru.otus.domain.BookAuthor;

import java.util.List;

public interface AuthorRepository {

    BookAuthor getAuthorById(int id);

    List<BookAuthor> getAuthors();

    BookAuthor getAuthorByName(String name);

    long createAuthor(String author);

    long deleteAuthor(int id);
}
