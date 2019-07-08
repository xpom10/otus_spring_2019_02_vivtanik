package ru.otus.domain.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MigrationBook {

    private String title;

    private Long authorId;

    private Long genreId;
}
