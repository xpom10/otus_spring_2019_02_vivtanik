package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    private static RowMapper<Book> rowMapper = (resultSet, i) -> {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setBookGenreId(resultSet.getInt("genre_id"));
        book.setBookAuthorId(resultSet.getInt("author_id"));
        return book;
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
            return jdbc.queryForObject("select id, title, genre_id, author_id from books where id = :id",
                    Collections.singletonMap("id", id),
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getBooks() {
        return jdbc.query("select id, title, genre_id, author_id from books", rowMapper);
    }

    @Override
    public Book getBookByTitle(String title) {
        try {
            return jdbc.queryForObject("select id, title, genre_id, author_id from books where title = :title",
                    Collections.singletonMap("title", title),
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
                        .addValue("genre_id", book.getBookGenreId())
                        .addValue("author_id", book.getBookAuthorId()),
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int deleteBookById(int id) {
        return jdbc.update("delete from books where id = :id",
                new MapSqlParameterSource().addValue("id", id));
    }


}
