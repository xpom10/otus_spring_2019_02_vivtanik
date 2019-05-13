package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("genre")
    private Genre genre;

    @DBRef
    @Field("author")
    private Author author;

    @Field("comments")
    private List<Comment> comments = new ArrayList<>();

    public Book(String title, Genre genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    private void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Book(String id, String title, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book(String title, Genre genre, Author author, List<Comment> comments) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        comments.forEach(this::addComment);
    }

    public static Book fromDto(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(new Author(bookDto.author.id, bookDto.author.authorName));
        book.setGenre(new Genre(bookDto.getGenreName()));
        if (bookDto.getComments() != null) {
            book.setComments(bookDto.comments.stream().map(Comment::new).collect(Collectors.toList()));
        }
        return book;
    }

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(new AuthorDto(book.getAuthor().getId(), book.getAuthor().getAuthorName()));
        bookDto.setGenreName(book.getGenre().getGenreName());
        if (book.getComments() != null) {
            bookDto.setComments(book.comments.stream().map(Comment::getComment).collect(Collectors.toList()));
        }
        return bookDto;
    }
}
