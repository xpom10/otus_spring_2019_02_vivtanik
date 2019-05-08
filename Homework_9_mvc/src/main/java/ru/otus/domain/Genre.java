package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.dto.GenreDto;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Field("genre_name")
    private String genreName;

    public static Genre fromDto(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setGenreName(genreDto.genreName);
        return genre;
    }

    public static GenreDto toDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreName(genre.genreName);
        return genreDto;
    }

}
