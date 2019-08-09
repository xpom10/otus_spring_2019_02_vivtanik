package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Flux<BookDto> findBooks() {
        return bookRepository.findAll().map(Book::toDto);
    }

    @Override
    public Mono<BookDto> findBook(String id) {
        return bookRepository.findById(id).map(Book::toDto);
    }

    @Override
    public Mono<BookDto> saveBook(BookDto bookDto) {
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

    @Override
    public Mono<BookDto> editBook(String id, BookDto bookDto) {
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

    @Override
    public Mono<Void> deleteBook(String id) {
        return bookRepository.deleteById(id).then();
    }

    @Override
    public Flux<GenreDto> findGenres() {
        return bookRepository.findGenres().map(Genre::toDto);
    }

    @Override
    public Mono<CommentDto> addComment(String id, CommentDto comment) {
        Comment addcomment = Comment.fromDto(comment);
        return bookRepository.addComment(id, addcomment).map(Comment::toDto);
    }

    @Override
    public Flux<BookDto> findAuthorBooks(String id) {
        return bookRepository.findBookByAuthor_Id(id).map(Book::toDto);
    }

    @Override
    public Flux<AuthorDto> findAuthors() {
        return authorRepository.findAll().map(Author::toDto);
    }

    @Override
    public Mono<AuthorDto> findAuthor(String id) {
        return authorRepository.findById(id).map(Author::toDto);
    }
}
