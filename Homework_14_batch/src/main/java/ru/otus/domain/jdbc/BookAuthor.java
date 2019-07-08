package ru.otus.domain.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    public BookAuthor(String authorName) {
        this.authorName = authorName;
    }
}
