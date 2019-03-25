package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.BookGenre;

import java.util.List;
import java.util.Objects;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcTemplate jdbc;

    private final static RowMapper<BookGenre> rowMapper = (resultSet, i) -> {
        BookGenre genre = new BookGenre();
        genre.setBookGenreId(resultSet.getInt("genre_id"));
        genre.setGenre(resultSet.getString("genre_name"));
        return genre;
    };

    public GenreDaoImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public BookGenre getGenreById(int id) {
        try {
            return jdbc.queryForObject("select * from genre where genre_id = :id",
                    new MapSqlParameterSource().addValue("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BookGenre> getGenres() {
        return jdbc.query("select * from genre", rowMapper);
    }

    @Override
    public BookGenre getGenreByName(String name) {
        try {
            return jdbc.queryForObject("select * from genre where genre_name = :name",
                    new MapSqlParameterSource().addValue("name", name), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int createGenre(String genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genre (GENRE_NAME) values (:genre)",
                new MapSqlParameterSource().addValue("genre", genre),
                keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }


}
