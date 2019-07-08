package ru.otus.security;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecurityTests extends BaseTests {

    @ParameterizedTest
    @ValueSource(strings = {"/add", "/books", "/book", "/edit", "/author"})
    void testUnathorized(String url) throws Exception {
        mockMvc.perform(get(url).with(user("sdasd").roles("USER1"))).andExpect(status().is(403));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/books", "/book", "/author"})
    void testUser(String url) throws Exception {
        mockMvc.perform(get(url).with(user("user").password("password").roles("USER"))).andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/add", "/edit"})
    void testUserForbidden(String url) throws Exception {
        mockMvc.perform(get(url).with(user("user").password("password").roles("USER"))).andExpect(status().is(403));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/add", "/books", "/book", "/edit", "/author"})
    void testAdmin(String url) throws Exception {
        mockMvc.perform(get(url).with(user("admin").password("password").roles("ADMIN"))).andExpect(status().isOk());
    }

}
