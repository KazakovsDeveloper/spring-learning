package ru.otus.book.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.book.service.function.GenreService;
import ru.otus.book.service.model.Genre;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetGenreById() throws Exception {
        mockMvc.perform(get("/genres/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateGenreById() throws Exception {
        mockMvc.perform(put("/genres/1")
                        .queryParam("name", "newName"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testCreateGenre() throws Exception {
        mockMvc.perform(post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Genre())))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDeleteGenre() throws Exception {
        mockMvc.perform(delete("/genres/1"))
                .andExpect(status().isAccepted());
    }

}