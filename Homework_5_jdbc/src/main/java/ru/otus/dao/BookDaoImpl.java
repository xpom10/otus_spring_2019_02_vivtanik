package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    private final static String SELECT_QUERY =
            "select id, title, b.genre_id, b.author_id, g.genre_name, a.author_name from books b " +
            "join author a on a.AUTHOR_ID = b.author_id " +
            "join genre g on g.GENRE_ID = b.genre_id ";

    private static RowMapper<Book> rowMapper = (resultSet, i) -> {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");

        BookAuthor author = new BookAuthor(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name")
        );

        BookGenre genre = new BookGenre(
                resultSet.getLong("genre_id"),
                resultSet.getString("genre_name")
        );

        return new Book(id, title, author, genre);
    };

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Collections.emptyMap(), Integer.class);
    }

    @Override
    public Book getBookById(int id) {
        try {
            return jdbc.queryForObject(SELECT_QUERY + "where id = :id",
                    new MapSqlParameterSource().addValue("id", id),
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getBooks() {
        return jdbc.query(SELECT_QUERY, rowMapper);
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            return jdbc.queryForObject(SELECT_QUERY + "where title = :title",
                    new MapSqlParameterSource().addValue("title", title),
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int createBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books (title, genre_id, author_id) values(:title, :genre_id, :author_id)",
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("genre_id", book.getGenre().getBookGenreId())
                        .addValue("author_id", book.getAuthor().getAuthorBookId()),
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int deleteBookById(int id) {
        return jdbc.update("delete from books where id = :id",
                new MapSqlParameterSource().addValue("id", id));
    }


}
