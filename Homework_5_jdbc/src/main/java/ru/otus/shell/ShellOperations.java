package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.domain.BookAuthor;
import ru.otus.domain.BookGenre;

import java.util.List;

@ShellComponent
public class ShellOperations {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public ShellOperations(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @ShellMethod("Get count of books")
    public void bookCount() {
        int count = bookDao.count();
        System.out.println(String.format("Book count: '%s'", count));
    }

    @ShellMethod("Get book by id")
    public void bookById(@ShellOption int id) {
        Book book = bookDao.getBookById(id);
        if (book != null) {
            System.out.println(String.format("Book title: '%s'", book.getTitle()));
        } else {
            System.out.println(String.format("Book for id = '%s' not found", id));
        }
    }

    @ShellMethod("Create author")
    public void createAuthor(@ShellOption String name) {
        BookAuthor bookAuthor = authorDao.getAuthorByName(name);
        if (bookAuthor != null) {
            System.out.println(String.format("Author '%s' already exists", name));
            return;
        }
        int id = authorDao.createAuthor(name);
        System.out.println(String.format("Author '%s' create with id '%s'", name, id));
    }

    @ShellMethod("Get book id")
    public void bookIdByTitle(@ShellOption String title) {
        Book book = bookDao.getBookByTitle(title);
        if (book != null) {
            System.out.println(String.format("Book id: %s", book.getId()));
        } else {
            System.out.println(String.format("Book for title = '%s' not found", title));
        }
    }

    @ShellMethod("Get all books")
    public void allBooks() {
        List<Book> books = bookDao.getBooks();
        books.forEach(book -> System.out.println(String.format("Book title: '%s'", book.getTitle())));
    }

    @ShellMethod("Create book")
    public void createBook(@ShellOption String title, @ShellOption String genre, @ShellOption String author) {
        BookGenre bookGenre = genreDao.getGenreByName(genre);
        if (bookGenre == null) {
            System.out.println(String.format("Genre '%s' for new book '%s' not found, please create Genre", genre, title));
            return;
        }
        BookAuthor bookAuthor = authorDao.getAuthorByName(author);
        if (bookAuthor == null) {
            System.out.println(String.format("Author '%s' for new book '%s' not found, please create Author", author, title));
            return;
        }
        Book bookForCreate = new Book(title, bookAuthor.getAuthorBookId(), bookGenre.getBookGenreId());
        int id = bookDao.createBook(bookForCreate);
        System.out.println(String.format("Book created with id '%s'", id));
    }

    @ShellMethod("Delete book")
    public void deleteBook(@ShellOption int id) {
        int count = bookDao.deleteBookById(id);
        if (count > 0) {
            System.out.println("Book delete");
        } else {
            System.out.println(String.format("Book with id '%s' not delete", id));
        }
    }

    @ShellMethod("Get all genres")
    public void allGenres() {
        List<BookGenre> genres = genreDao.getGenres();
        genres.forEach(genre -> System.out.println(String.format("Genre id: '%s', genre: '%s'", genre.getBookGenreId(), genre.getGenre())));
    }

    @ShellMethod("Create genre")
    public void createGenre(@ShellOption String genre) {
        int id = genreDao.createGenre(genre);
        System.out.println(String.format("Genre '%s' create with id '%s'", genre, id));
    }

    @ShellMethod("Get all authors")
    public void getAuthors() {
        List<BookAuthor> authors = authorDao.getAuthors();
        authors.forEach(bookAuthor -> System.out.println(String.format("Book author: '%s'", bookAuthor.getAuthorName())));
    }

    @ShellMethod("Delete author")
    public void deleteAuthor(@ShellOption int id) {
        authorDao.deleteAuthor(id);
        System.out.println(String.format("Author delete with id '%s'", id));
    }

    @ShellMethod("Get author id")
    public void getAuthor(@ShellOption String name) {
        BookAuthor author = authorDao.getAuthorByName(name);
        if (author == null) {
            System.out.println(String.format("Author with name '%s' not found", name));
        } else {
            System.out.println(String.format("Author name '%s'", author.getAuthorBookId()));
        }
    }

}
