package ru.otus.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.controllers.ViewController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ViewController.class)
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void testBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void testBook() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void testAdd() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void testEdit() throws Exception {
        mockMvc.perform(get("/edit"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void testAuthor() throws Exception {
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk());
    }

    @Test
    void testBooksUnauthorized() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().is(401));
    }

    @Test
    void testBookUnauthorized() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().is(401));
    }

    @Test
    void testAddUnauthorized() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().is(401));
    }

    @Test
    void testEditUnauthorized() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().is(401));
    }

    @Test
    void testAuthorUnauthorized() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().is(401));
    }
}
