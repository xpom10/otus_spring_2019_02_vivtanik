package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;

    private String title;

    private BookAuthor author;

    private BookGenre genre;

    public Book(String title, BookAuthor author, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
