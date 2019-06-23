package ru.otus.restControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Author;
import ru.otus.repositories.AuthorRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorRestController.class)
public class AuthorRestControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void testAuthor() throws Exception {
        Mockito.when(authorRepository.findById("1"))
                .thenReturn(Optional.of(
                        new Author("1", "author")
                ));
        mvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")))
                .andExpect(jsonPath("authorName", is("author")));
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
}
