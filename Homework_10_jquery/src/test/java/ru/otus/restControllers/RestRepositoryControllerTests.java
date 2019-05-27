package ru.otus.restControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.repositories.AuthorRepository;
import ru.otus.repositories.BookRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestRepositoryController.class)
public class RestRepositoryControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void testBooks() throws Exception {
        Mockito.when(bookRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Book("1", "title", new Genre("genre"), new Author("author")),
                        new Book("2", "title2", new Genre("genre2"), new Author("author2"))
                ));
        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].genreName", is("genre")))
                .andExpect(jsonPath("$[0].author.authorName", is("author")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testBook() throws Exception {
        Mockito.when(bookRepository.findById("1"))
                .thenReturn(Optional.of(
                        new Book("1", "title", new Genre("genre"), new Author("author"))
                ));
        mvc.perform(get("/api/book?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")))
                .andExpect(jsonPath("title", is("title")))
                .andExpect(jsonPath("genreName", is("genre")))
                .andExpect(jsonPath("author.authorName", is("author")));
    }

    @Test
    public void testAuthor() throws Exception {
        Mockito.when(authorRepository.findById("1"))
                .thenReturn(Optional.of(
                        new Author("1", "author")
                ));
        mvc.perform(get("/api/author?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")))
                .andExpect(jsonPath("authorName", is("author")));
    }

    @Test
    public void testGenres() throws Exception {
        Mockito.when(bookRepository.findGenres())
                .thenReturn(Arrays.asList(
                        new Genre("genre"),
                        new Genre("genre2"))
                );
        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genreName", is("genre")))
                .andExpect(jsonPath("$[1].genreName", is("genre2")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testAuthors() throws Exception {
        Mockito.when(authorRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Author("author"),
                        new Author("author1"))
                );
        mvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].authorName", is("author")))
                .andExpect(jsonPath("$[1].authorName", is("author1")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testAuthorBooks() throws Exception {
        Mockito.when(bookRepository.findBookByAuthor_Id("1"))
                .thenReturn(Arrays.asList(
                        new Book("1", "title", new Genre("genre"), new Author("1", "author")),
                        new Book("2", "title2", new Genre("genre2"), new Author("2", "author1"))
                ));
        mvc.perform(get("/api/authorbooks?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].genreName", is("genre")))
                .andExpect(jsonPath("$[0].author.authorName", is("author")));
    }

    @Test
    public void testAddComment() throws Exception {
        Mockito.when(bookRepository.findById("1"))
                .thenReturn(Optional.of(new Book("1", "book1", new Genre("genre1"), new Author("111", "author1"))));

        mvc.perform(post("/api/comment?id=1").param("comment", "comment1"))
                .andExpect(status().is(201));
    }

    @Test
    public void testAddBook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("book");
        bookDto.setGenreName("genre");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorName("author");
        bookDto.setAuthor(authorDto);

        mvc.perform(post("/api/add").contentType("application/json").content(new ObjectMapper().writeValueAsBytes(bookDto)))
                .andExpect(status().is(201));
    }

    @Test
    public void testEditBook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("book");
        bookDto.setGenreName("genre");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorName("author");
        bookDto.setAuthor(authorDto);
        String body = new ObjectMapper().writeValueAsString(bookDto);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/editbook?id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body);

        mvc.perform(builder)
                .andExpect(status().is(201));
    }



}
