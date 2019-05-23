package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import javax.swing.text.html.Option;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRestController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/api/books")
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(Book::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/book")
    public BookDto getBook(@RequestParam String id) {
        Optional<Book> book = bookRepository.findById(id);
        return Book.toDto(book.orElse(new Book()));
    }

    @GetMapping("/api/author")
    public AuthorDto getAuthor(@RequestParam String id) {
        Optional<Author> author = authorRepository.findById(id);
        return Author.toDto(author.orElse(new Author()));
    }

    @GetMapping("/api/authorbooks")
    public List<BookDto> getAuthorBooks(@RequestParam String id) {
        return bookRepository.findBookByAuthor_Id(id).stream().map(Book::toDto).collect(Collectors.toList());
    }


}
