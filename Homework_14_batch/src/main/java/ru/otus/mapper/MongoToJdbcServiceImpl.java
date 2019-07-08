package ru.otus.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.jdbc.BookAuthor;
import ru.otus.domain.jdbc.BookGenre;
import ru.otus.domain.jdbc.MigrationBook;
import ru.otus.domain.mongo.Author;
import ru.otus.domain.mongo.Book;
import ru.otus.repositories.jdbc.AuthorJdbcRepository;
import ru.otus.repositories.jdbc.GenreJdbcRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MongoToJdbcServiceImpl implements MongoToJdbcService {

    private final AuthorJdbcRepository authorJdbcRepository;
    private final GenreJdbcRepository genreJdbcRepository;

    @Override
    public BookAuthor toBookAuthor(Author author) {
        return new BookAuthor(author.getAuthorName());
    }

    @Override
    public BookGenre toBookGenre(Book book) {
        return new BookGenre(book.getGenre().getGenreName());
    }

    @Override
    public MigrationBook toMigrationBook(Book book) {
        BookAuthor author = authorJdbcRepository.findAuthorByAuthorName(book.getAuthor().getAuthorName()).orElse(new BookAuthor());
        BookGenre genre = genreJdbcRepository.findGenreByGenre(book.getGenre().getGenreName()).orElse(new BookGenre());
        return new MigrationBook(book.getTitle(), author.getId(), genre.getId());
    }
}
