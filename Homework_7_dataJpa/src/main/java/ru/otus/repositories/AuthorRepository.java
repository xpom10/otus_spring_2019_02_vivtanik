package ru.otus.repositories;

import ru.otus.domain.BookAuthor;

import java.util.List;

public interface AuthorRepository {

    BookAuthor getAuthorById(long id);

    List<BookAuthor> getAuthors();

    BookAuthor getAuthorByName(String name);

    long createAuthor(BookAuthor author);

    long deleteAuthor(long id);
}
