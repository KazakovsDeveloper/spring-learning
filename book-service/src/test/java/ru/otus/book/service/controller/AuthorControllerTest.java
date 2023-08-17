package ru.otus.book.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.book.service.function.AuthorService;
import ru.otus.book.service.model.Author;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAuthorById() throws Exception {
        mockMvc.perform(put("/authors/1")
                        .queryParam("name", "newName"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCreateAuthor() throws Exception {
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Author())))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isAccepted());
    }


}