package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.BookAuthor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    private static RowMapper<BookAuthor> rowMapper = ((resultSet, i) -> {
        BookAuthor author = new BookAuthor();
        author.setAuthorBookId(resultSet.getInt("author_id"));
        author.setAuthorName(resultSet.getString("author_name"));
        return author;
    });

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public BookAuthor getAuthorById(int id) {
        try {
            return jdbc.queryForObject("select author_id, author_name from author where author_id = :id",
                    new MapSqlParameterSource().addValue("id", id),
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BookAuthor> getAuthors() {
        return jdbc.query("select author_id, author_name from author",
                Collections.emptyMap(),
                rowMapper);
    }

    @Override
    public BookAuthor getAuthorByName(String name) {
        try {
            return jdbc.queryForObject("select author_id, author_name from author where author_name = :name",
                    new MapSqlParameterSource().addValue("name", name),
                    rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int createAuthor(BookAuthor author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into author (author_name) values (:name)",
                new MapSqlParameterSource().addValue("name", author.getAuthorName()),
                keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int deleteAuthor(int id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("delete from author where author_id = :id",
                new MapSqlParameterSource().addValue("id", id),
                keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
