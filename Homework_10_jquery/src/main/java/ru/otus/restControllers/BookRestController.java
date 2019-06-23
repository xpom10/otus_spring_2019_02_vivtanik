package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;
import ru.otus.repositories.BookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(Book::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBook(@PathVariable String id) {
        Optional<Book> book = bookRepository.findById(id);
        return Book.toDto(book.orElse(new Book()));
    }

    @PostMapping(value = "/api/books", produces = "application/json")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Book addBook = Book.fromDto(bookDto);
        bookRepository.insert(addBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/api/books/{id}", produces = "application/json")
    public ResponseEntity<BookDto> editBook(@PathVariable String id, @RequestBody @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Book book = Book.fromDto(bookDto);
        book.setId(id);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(Book.toDto(book));
    }

    @GetMapping("/api/books/genres")
    public List<GenreDto> getGenres() {
        return bookRepository.findGenres().stream().map(Genre::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/books/{id}/comment", produces = "application/json")
    public ResponseEntity<CommentDto> addComment(@PathVariable String id, @Valid CommentDto comment, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Comment addComment = Comment.fromDto(comment);
        bookRepository.addComment(id, addComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/api/books/author/{id}")
    public List<BookDto> getAuthorBooks(@PathVariable String id) {
        return bookRepository.findBookByAuthor_Id(id).stream().map(Book::toDto).collect(Collectors.toList());
    }
}
