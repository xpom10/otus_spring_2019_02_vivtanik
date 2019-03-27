package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    Book getBookById(int id);

    List<Book> getBooks();

    Book getBookByTitle(String title);

    int createBook(Book book);

    int deleteBookById(int id);


}
