package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ViewController {

    @GetMapping("/author")
    public String getAuthorView() {
        return "author";
    }

    @GetMapping({"/", "/books"})
    public String getBooksView() {
        return "books";
    }

    @GetMapping("/book")
    public String getBookView() {
        return "book";
    }

    @GetMapping("/edit")
    public String editBook() {
        return "edit";
    }

    @GetMapping("/add")
    public String addBookView() {
        return "add";
    }

}
