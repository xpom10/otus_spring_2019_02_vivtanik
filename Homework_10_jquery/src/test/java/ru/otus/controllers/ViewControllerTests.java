package ru.otus.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ViewController.class)
public class ViewControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testBooksView() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }

    @Test
    public void testBookView() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"));
    }

    @Test
    public void testAddView() throws Exception {
        mvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    public void testEditView() throws Exception {
        mvc.perform(get("/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    public void testAuthorView() throws Exception {
        mvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(view().name("author"));
    }
}
