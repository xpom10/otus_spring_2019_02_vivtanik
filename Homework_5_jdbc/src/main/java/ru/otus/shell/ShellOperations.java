package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dao.BookDao;

@ShellComponent
public class ShellOperations {

    private final BookDao bookDao;

    public ShellOperations(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ShellMethod("Get count of books")
    public void bookCount() {
        int count = bookDao.count();
        System.out.println(String.format("Book count: %s", count));
    }

    @ShellMethod("Get book by id")
    public void BookById(@ShellOption int id) {

    }


}
