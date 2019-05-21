package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(Book::toDto).collect(Collectors.toList());
    }
}
