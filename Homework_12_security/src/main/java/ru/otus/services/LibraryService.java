package ru.otus.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.dto.GenreDto;

public interface LibraryService {

    Flux<BookDto> findBooks();

    Mono<BookDto> findBook(String id);

    Mono<BookDto> saveBook(BookDto bookDto);

    Mono<BookDto> editBook(String id, BookDto bookDto);

    Mono<Void> deleteBook(String id);

    Flux<GenreDto> findGenres();

    Mono<CommentDto> addComment(String id, CommentDto comment);

    Flux<BookDto> findAuthorBooks(String id);

    Flux<AuthorDto> findAuthors();

    Mono<AuthorDto> findAuthor(String id);
}
