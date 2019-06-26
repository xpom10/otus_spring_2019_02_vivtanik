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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRestController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/api/books")
    public Flux<BookDto> getBooks() {
        return bookRepository.findAll().map(Book::toDto);
    }

    @GetMapping("/api/books/{id}")
    public Mono<BookDto> getBook(@PathVariable String id) {
        return bookRepository.findById(id).map(Book::toDto);
    }


    @PostMapping(value = "/api/books", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> addBook(@RequestBody @Valid BookDto bookDto) {
        return authorRepository
                .findAuthorByAuthorName(bookDto.getAuthor().authorName)
                .switchIfEmpty(Mono.just(new Author(bookDto.getAuthor().authorName)))
                .flatMap(authorRepository::save)
                .map(authorMono -> {
                    bookDto.getAuthor().setId(authorMono.getId());
                    return bookDto;
                })
                .map(Book::fromDto)
                .flatMap(bookRepository::save)
                .map(Book::toDto);

    }

    @DeleteMapping("/api/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id).then();
    }

    @PutMapping(value = "/api/books/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> editBook(@PathVariable String id, @RequestBody @Valid BookDto bookDto) {
        return authorRepository
                .findAuthorByAuthorName(bookDto.getAuthor().authorName)
                .switchIfEmpty(Mono.just(new Author(bookDto.getAuthor().authorName)))
                .flatMap(authorRepository::save)
                .map(authorMono -> {
                    bookDto.getAuthor().setId(authorMono.getId());
                    return bookDto;
                })
                .map(Book::fromDto)
                .map(book -> {
                    book.setId(id);
                    return book;
                })
                .flatMap(bookRepository::save)
                .map(Book::toDto);
    }

    @GetMapping("/api/books/genres")
    public Flux<GenreDto> getGenres() {
        return bookRepository.findGenres().map(Genre::toDto);
    }

    @PostMapping(value = "/api/books/{id}/comment", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable String id, @Valid CommentDto comment) {
        Comment addComment = Comment.fromDto(comment);
        bookRepository.addComment(id, addComment);
        return comment;
    }

    @GetMapping("/api/books/author/{id}")
    public Flux<BookDto> getAuthorBooks(@PathVariable String id) {
        return bookRepository.findBookByAuthor_Id(id).map(Book::toDto);
    }
}
