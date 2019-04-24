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
@Document(collection = "authors")
public class BookAuthor {

    @Id
    private String id;

    @Field("author_name")
    private String authorName;

    public BookAuthor(String authorName) {
        this.authorName = authorName;
    }
}
