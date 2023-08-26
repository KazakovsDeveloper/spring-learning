package ru.otus.book.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.book.service.function.CommentService;
import ru.otus.book.service.model.Book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllComments() throws Exception {
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetCommentById() throws Exception {
        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCommentById() throws Exception {
        mockMvc.perform(put("/comments/1")
                        .queryParam("name", "newName"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCreateComment() throws Exception {
        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Book())))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isAccepted());
    }

}