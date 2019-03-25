package ru.otus.domain;

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

    public int getBookAuthor() {
        return authorId;
    }

    public void setBookAuthor(int bookAuthor) {
        this.authorId = bookAuthor;
    }

    public int getBookGenre() {
        return genreId;
    }

    public void setBookGenre(int bookGenre) {
        this.genreId = bookGenre;
    }
}
