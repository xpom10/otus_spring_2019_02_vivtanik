package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping({"/", "/books"})
    public String getBooksView() {
        return "books";
    }

    @GetMapping("/book")
    public String getBookView(@RequestParam String id, Model model) {
        return "book";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam String id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        BookDto bookDto = Book.toDto(book.orElse(new Book()));
        model.addAttribute("book", bookDto);
        return "edit";
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public String editBook(@ModelAttribute("book") @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        Book book = Book.fromDto(bookDto);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addBookView() {
        return "add";
    }

    @PostMapping(value = "/add", produces = "application/json")
    public String addBook(@ModelAttribute("book") @Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        Book book = Book.fromDto(bookDto);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
