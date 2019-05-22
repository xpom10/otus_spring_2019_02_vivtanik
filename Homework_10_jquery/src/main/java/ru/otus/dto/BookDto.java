package ru.otus.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Stream;

@Data
public class BookDto {

    public String id;

    @NotBlank(message = "Название книги не может быть пустым")
    public String title;

    @Valid
    public AuthorDto author;

    @NotBlank(message = "Жанр не может быть пустым")
    public String genreName;

    public List<String> comments;
}
