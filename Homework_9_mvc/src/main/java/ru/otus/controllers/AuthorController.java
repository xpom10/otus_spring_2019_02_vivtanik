package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/author")
    public String getAuthor(@RequestParam String id, Model model) {
        List<Book> authorBooks = bookRepository.findBookByAuthor_Id(id);
        Optional<Author> author = authorRepository.findById(id);
        model.addAttribute("author", author.get());
        model.addAttribute("books", authorBooks);
        return "author";
    }
}
