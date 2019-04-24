package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {


    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    @Field("author")
    private BookAuthor author;

    @DBRef
    @Field("genre")
    private BookGenre genre;

    public Book(String title, BookAuthor author, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
