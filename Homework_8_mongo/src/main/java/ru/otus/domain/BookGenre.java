package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genres")
public class BookGenre {

    @Id
    private String id;

    @Field("genre_name")
    private String genre;

    public BookGenre(String genre) {
        this.genre = genre;
    }
}
