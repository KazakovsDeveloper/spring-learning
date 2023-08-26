package ru.otus.book.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.book.service.function.BookService;
import ru.otus.book.service.model.Book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_USER"}
    )
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_USER"}
    )
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_USER"}
    )
    public void testUpdateBookById() throws Exception {
        mockMvc.perform(put("/books/1")
                        .queryParam("name", "newName"))
                .andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_USER"}
    )
    public void testCreateBook() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Book())))
                .andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_USER"}
    )
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isAccepted());
    }


}