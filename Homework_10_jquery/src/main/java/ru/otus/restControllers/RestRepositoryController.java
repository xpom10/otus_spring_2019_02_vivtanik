package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RestRepositoryController {

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

    @GetMapping("/api/genres")
    public List<GenreDto> getGenres() {
        return bookRepository.findGenres().stream().map(Genre::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll().stream().map(Author::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/authorbooks")
    public List<BookDto> getAuthorBooks(@RequestParam String id) {
        return bookRepository.findBookByAuthor_Id(id).stream().map(Book::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/comment", produces = "application/json")
    public ResponseEntity<CommentDto> addComment(@RequestParam String id, @Valid CommentDto comment, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Comment addComment = Comment.fromDto(comment);
        bookRepository.addComment(id, addComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PostMapping(value = "/api/add", produces = "application/json")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Book addBook = Book.fromDto(bookDto);
        bookRepository.insert(addBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @PutMapping(value = "/api/editbook", produces = "application/json")
    public ResponseEntity<BookDto> editBook(@RequestParam String id, @RequestBody @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Book book = Book.fromDto(bookDto);
        book.setId(id);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(Book.toDto(book));
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<?> deleteBook(@RequestParam String id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
