package ru.otus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.dto.CommentDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testBooks() throws Exception {
        Mockito.when(bookRepository.findAll())
                .thenReturn(Collections.singletonList(new Book("111", "book1", new Genre("genre1"), new Author("111", "author1"))));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("genre1")))
                .andExpect(content().string(containsString("book1")));
    }

    @Test
    public void testBook() throws Exception {
        Mockito.when(bookRepository.findById("1"))
                .thenReturn(Optional.of(new Book("1", "book1", new Genre("genre1"), new Author("111", "author1"),
                        Collections.singletonList(new Comment("comment1")))));

        mvc.perform(get("/book?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("comment1")))
                .andExpect(content().string(containsString("book1")));
    }

    @Test
    public void testBookEdit() throws Exception {
        Mockito.when(bookRepository.findById("1"))
                .thenReturn(Optional.of(new Book("1", "book1", new Genre("genre1"), new Author("111", "author1"))));

        mvc.perform(get("/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("genre1")))
                .andExpect(content().string(containsString("book1")));
    }

    @Test
    public void testAddComment() throws Exception {
        Mockito.when(bookRepository.findById("1"))
                .thenReturn(Optional.of(new Book("1", "book1", new Genre("genre1"), new Author("111", "author1"))));

        CommentDto commentDto = new CommentDto("comment1");
        String content = mapper.writeValueAsString(commentDto);

        mvc.perform(post("/comment?id=1").content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("comment1")))
                .andExpect(content().string(containsString("book1")));
    }

}
