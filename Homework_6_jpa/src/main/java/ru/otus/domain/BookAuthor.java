package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long authorBookId;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(mappedBy = "author")
    private List<Book> book;

    public BookAuthor(long id, String authorName) {
        this.authorBookId = id;
        this.authorName = authorName;
    }

    public BookAuthor(String authorName) {
        this.authorName = authorName;
    }
}
