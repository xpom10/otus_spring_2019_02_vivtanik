package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @DBRef
    @Field("book")
    private Book book;

    @Field("comment")
    private String comment;


    public Comment(Book book, String comment) {
        this.book = book;
        this.comment = comment;
    }
}
