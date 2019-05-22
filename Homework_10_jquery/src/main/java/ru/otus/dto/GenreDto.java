package ru.otus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GenreDto {

    @NotBlank(message = "Жанр не может быть пустым")
    public String genreName;
}
