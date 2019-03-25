package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.domain.BookGenre;

import java.util.List;

@ShellComponent
public class ShellOperations {

    private final BookDao bookDao;
    private final GenreDao genreDao;

    public ShellOperations(BookDao bookDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
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

}
