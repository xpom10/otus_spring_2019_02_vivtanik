package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;
import ru.otus.repositories.GenreRepository;

import java.util.List;

@ShellComponent
public class ShellOperations {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public ShellOperations(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @ShellMethod("Get count of books")
    public void bookCount() {
        long count = bookRepository.count();
        System.out.println(String.format("Book count: '%s'", count));
    }

    @ShellMethod("Get book by id")
    public void bookById(@ShellOption int id) {
        Book book = bookRepository.getBookById(id);
        if (book != null) {
            System.out.println(String.format("Book title: '%s'", book.getTitle()));
        } else {
            System.out.println(String.format("Book for id = '%s' not found", id));
        }
    }

    @ShellMethod("Create author")
    public void createAuthor(@ShellOption String name) {
        BookAuthor bookAuthor = authorRepository.getAuthorByName(name);
        if (bookAuthor != null) {
            System.out.println(String.format("Author '%s' already exists", name));
            return;
        }
        authorRepository.createAuthor(name);
        System.out.println(String.format("Author '%s' create", name));
    }

    @ShellMethod("Get book id")
    public void bookIdByTitle(@ShellOption String title) {
        Book book = bookRepository.getBookByTitle(title);
        if (book != null) {
            System.out.println(String.format("Book id: %s", book.getId()));
        } else {
            System.out.println(String.format("Book for title = '%s' not found", title));
        }
    }

    @ShellMethod("Get all books")
    public void allBooks() {
        List<Book> books = bookRepository.getBooks();
        books.forEach(book -> System.out.println(String.format("Book title: '%s'", book.getTitle())));
    }

    @ShellMethod("Create book")
    public void createBook(@ShellOption String title, @ShellOption String genre, @ShellOption String author) {
        BookGenre bookGenre = genreRepository.getGenreByName(genre);
        if (bookGenre == null) {
            System.out.println(String.format("Genre '%s' for new book '%s' not found, please create Genre", genre, title));
            return;
        }
        BookAuthor bookAuthor = authorRepository.getAuthorByName(author);
        if (bookAuthor == null) {
            System.out.println(String.format("Author '%s' for new book '%s' not found, please create Author", author, title));
            return;
        }
        Book bookForCreate = new Book(title, bookAuthor, bookGenre);
        bookRepository.createBook(bookForCreate);
        System.out.println("Book created");
    }

    @ShellMethod("Delete book")
    public void deleteBook(@ShellOption int id) {
        long count = bookRepository.deleteBookById(id);
        if (count > 0) {
            System.out.println("Book delete");
        } else {
            System.out.println(String.format("Book with id '%s' not delete", id));
        }
    }

    @ShellMethod("Get all genres")
    public void allGenres() {
        List<BookGenre> genres = genreRepository.getGenres();
        genres.forEach(genre -> System.out.println(String.format("Genre id: '%s', genre: '%s'", genre.getBookGenreId(), genre.getGenre())));
    }

    @ShellMethod("Create genre")
    public void createGenre(@ShellOption String genre) {
        genreRepository.createGenre(genre);
        System.out.println(String.format("Genre '%s' create", genre));
    }

    @ShellMethod("Get all authors")
    public void getAuthors() {
        List<BookAuthor> authors = authorRepository.getAuthors();
        authors.forEach(bookAuthor -> System.out.println(String.format("Book author: '%s'", bookAuthor.getAuthorName())));
    }

    @ShellMethod("Delete author")
    public void deleteAuthor(@ShellOption int id) {
        authorRepository.deleteAuthor(id);
        System.out.println(String.format("Author delete with id '%s'", id));
    }

    @ShellMethod("Get author id")
    public void getAuthor(@ShellOption String name) {
        BookAuthor author = authorRepository.getAuthorByName(name);
        if (author == null) {
            System.out.println(String.format("Author with name '%s' not found", name));
        } else {
            System.out.println(String.format("Author name '%s'", author.getAuthorBookId()));
        }
    }

}
