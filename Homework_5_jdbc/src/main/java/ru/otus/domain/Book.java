package ru.otus.domain;

import java.util.Objects;

public class Book {

    private int id;

    private String title;

    private int authorId;

    private int genreId;

    public Book() {
    }

    public Book(String title, int authorId, int genreId) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookAuthorId() {
        return authorId;
    }

    public void setBookAuthorId(int bookAuthor) {
        this.authorId = bookAuthor;
    }

    public int getBookGenreId() {
        return genreId;
    }

    public void setBookGenreId(int bookGenre) {
        this.genreId = bookGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                authorId == book.authorId &&
                genreId == book.genreId &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorId, genreId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                '}';
    }
}
