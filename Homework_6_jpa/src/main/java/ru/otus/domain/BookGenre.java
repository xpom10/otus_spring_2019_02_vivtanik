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
@Table(name = "genre")
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long bookGenreId;

    @Column(name = "genre_name")
    private String genre;

    @OneToMany(mappedBy = "genre")
    private List<Book> book;

    public BookGenre(long id, String genre) {
        this.bookGenreId = id;
        this.genre = genre;
    }

    public BookGenre(String genre) {
        this.genre = genre;
    }
}
