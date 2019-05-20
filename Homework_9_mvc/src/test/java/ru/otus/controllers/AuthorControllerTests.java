package ru.otus.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testAuthor() throws Exception {
        Mockito.when(authorRepository.findById("111"))
                .thenReturn(Optional.of(new Author("111", "author1")));
        Mockito.when(bookRepository.findBookByAuthor_Id("111"))
                .thenReturn(Collections.singletonList(new Book("111", "book1", new Genre("genre1"), new Author("111", "author1"))));

        mvc.perform(get("/author?id=111"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("book1")));
    }
}
