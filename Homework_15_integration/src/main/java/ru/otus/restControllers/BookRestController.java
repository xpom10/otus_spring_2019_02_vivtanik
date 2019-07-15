package ru.otus.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;
import ru.otus.services.LibraryService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/books")
    public Flux<BookDto> getBooks() {
        return libraryService.findBooks();
    }

    @GetMapping("/api/books/{id}")
    public Mono<BookDto> getBook(@PathVariable String id) {
        return libraryService.findBook(id);
    }


    @PostMapping(value = "/api/books", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
        return libraryService.saveBook(bookDto);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable String id) {
        return libraryService.deleteBook(id);
    }

    @PutMapping(value = "/api/books/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> editBook(@PathVariable String id, @RequestBody @Valid BookDto bookDto) {
        return libraryService.editBook(id, bookDto);
    }

    @GetMapping("/api/books/genres")
    public Flux<GenreDto> getGenres() {
        return libraryService.findGenres();
    }

    @PostMapping(value = "/api/books/{id}/comment", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CommentDto> addComment(@PathVariable String id, @RequestBody CommentDto comment) {
        return libraryService.addComment(id, comment);
    }

    @GetMapping("/api/books/author/{id}")
    public Flux<BookDto> getAuthorBooks(@PathVariable String id) {
        return libraryService.findAuthorBooks(id);
    }
}
