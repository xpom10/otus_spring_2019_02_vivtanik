package ru.otus.repositories;

import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository {

    long count();

    Book getBookById(long id);

    List<Book> getBooks();

    Book getBookByTitle(String title);

    long createBook(Book book);

    long deleteBookById(int id);
}
