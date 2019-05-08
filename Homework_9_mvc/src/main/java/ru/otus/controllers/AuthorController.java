package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/author")
    public String getAuthor(@RequestParam String id, Model model) {
        List<BookDto> authorBooks = bookRepository.findBookByAuthor_Id(id).stream().map(Book::toDto).collect(Collectors.toList());
        Optional<Author> author = authorRepository.findById(id);
        AuthorDto authorDto = Author.toDto(author.orElse(new Author()));
        model.addAttribute("author", authorDto);
        model.addAttribute("books", authorBooks);
        return "author";
    }
}
