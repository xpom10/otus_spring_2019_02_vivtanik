package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "comment")
    private String comment;


    public Comment(Book book, String comment) {
        this.book = book;
        this.comment = comment;
    }
}
