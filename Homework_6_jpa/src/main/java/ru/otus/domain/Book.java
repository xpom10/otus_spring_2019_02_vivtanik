package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "author_id")
    private BookAuthor author;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private BookGenre genre;

    public Book(String title, BookAuthor author, BookGenre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
