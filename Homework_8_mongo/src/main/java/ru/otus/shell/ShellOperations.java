package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ShellOperations {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Get count of books", key = "bc")
    public void bookCount() {
        long count = bookRepository.count();
        System.out.println(String.format("Book count: '%s'", count));
    }

    @ShellMethod(value = "Get book by title", key = "bt")
    public void bookByTitle(@ShellOption String title) {
        Optional<Book> book = bookRepository.findBookByTitle(title);
        if (book.isPresent()) {
            System.out.println(String.format("Book found with id '%s'", book.get().getId()));
        } else {
            System.out.println(String.format("Book not found with title '%s'", title));
        }
    }

    @ShellMethod(value = "All authors", key = "a")
    public void authors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> System.out.println(String.format("Author: %s", author.getAuthorName())));
    }

    @ShellMethod(value = "Get author by name", key = "an")
    public void authorByName(@ShellOption String name) {
        Optional<Author> author = authorRepository.findAuthorByAuthorName(name);
        if (author.isPresent()) {
            System.out.println(String.format("Author found with id '%s'", author.get().getId()));
        } else {
            System.out.println(String.format("Author not found with name '%s'", name));
        }
    }

    @ShellMethod(value = "Delete book", key = "db")
    public void deleteBook(@ShellOption String title) {
        Optional<Book> book = bookRepository.findBookByTitle(title);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            System.out.println(String.format("Book delete with id '%s'", book.get().getId()));
        } else {
            System.out.println(String.format("Book not found with title '%s'", title));
        }
    }

    @ShellMethod(value = "Get comments for book", key = "bcomments")
    public void bookComments(@ShellOption String title) {
        Optional<Book> book = bookRepository.findBookByTitle(title);
        if (book.isPresent()) {
            book.get().getComments().forEach(comment ->
                    System.out.println(String.format("Comment: '%s'", comment.getComment())));
        } else {
            System.out.println(String.format("Book not found with title '%s'", title));
        }
    }

    @ShellMethod(value = "Add comment for book", key = "addComm")
    public void createComment(@ShellOption String title, @ShellOption String comment) {
        long modifiedCount = bookRepository.addComment(title, new Comment(comment));
        System.out.println(String.format("Comment add, modified count: %s", modifiedCount));
    }

    @ShellMethod(value = "Get all genres", key = "bg")
    public void bookGenres() {
        List<Genre> genres = bookRepository.findGenres();
        genres.forEach(genre -> System.out.println(String.format("Genre: %s", genre)));
    }

    @ShellMethod(value = "Create book with author", key = "cba")
    public void createBookWithAuthor(@ShellOption String title, @ShellOption String genre, @ShellOption String author) {
        Optional<Book> book = bookRepository.findBookByTitle(title);
        if (book.isPresent()) {
            System.out.println(String.format("Book already exists with id '%s'", book.get().getId()));
        } else {
            Book newBook = bookRepository.save(new Book(title, new Genre(genre), new Author(author)));
            System.out.println(String.format("Book created with id '%s'", newBook.getId()));
        }
    }

    @ShellMethod(value = "Delete author", key = "da")
    public void deleteAuthor(@ShellOption String authorName) {
        Optional<Author> author = authorRepository.findAuthorByAuthorName(authorName);
        if (author.isPresent()) {
            authorRepository.delete(author.get());
            System.out.println(String.format("Author delete with id %s", author.get().getId()));
        } else {
            System.out.println(String.format("Author not found with author name %s", authorName));
        }
    }

}