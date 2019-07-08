package ru.otus.mapper;

import ru.otus.domain.jdbc.BookAuthor;
import ru.otus.domain.jdbc.BookGenre;
import ru.otus.domain.jdbc.MigrationBook;
import ru.otus.domain.mongo.Author;
import ru.otus.domain.mongo.Book;

public interface MongoToJdbcService {

    BookAuthor toBookAuthor(Author author);

    BookGenre toBookGenre(Book book);

    MigrationBook toMigrationBook(Book book);

}
