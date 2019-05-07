package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Field("title")
    @NotBlank(message = "Название книги не может быть пустым")
    private String title;

    @Field("genre")
    @Valid
    private Genre genre;

    @DBRef
    @Field("author")
    @Valid
    private Author author;

    @Field("comments")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Book(String title, Genre genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public Book(String title, Genre genre, Author author, List<Comment> comments) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        comments.forEach(this::addComment);
    }
}
