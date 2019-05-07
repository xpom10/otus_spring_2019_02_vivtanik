package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.repositories.BookRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping({"/", "/books"})
    public String getBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book")
    public ModelAndView getBook(@RequestParam String id, ModelAndView modelAndView) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(book1 -> modelAndView.addObject("book", book1));
        modelAndView.addObject("comment", new Comment());
        modelAndView.setViewName("book");
        return modelAndView;
    }

    @PostMapping("/comment")
    public String addComment(@RequestParam(required = false) String id, @Valid Comment comment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Optional<Book> book = bookRepository.findById(id);
            model.addAttribute("book", book.orElse(null));
            return "book";
        }
        bookRepository.addComment(id, comment);
        return String.format("redirect:/book?id=%s", id);
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam String id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.orElse(new Book()));
        return "edit";
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public String editBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping(value = "/add", produces = "application/json")
    public String addBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
