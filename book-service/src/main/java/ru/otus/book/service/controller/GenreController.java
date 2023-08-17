package ru.otus.book.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.book.service.function.GenreService;
import ru.otus.book.service.model.Genre;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping(path = "/genres")
    public ResponseEntity<?> getGenres() {
        List<Genre> allGenres = genreService.getAllGenres();
        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }

    @GetMapping(path = "/genres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenreById(@PathVariable Long id) {
        Genre genreById = genreService.getGenreById(id);
        return new ResponseEntity<>(genreById, HttpStatus.OK);
    }

    @PostMapping(path = "/genres")
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        genreService.createGenre(genre);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/genres/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id,
                                         @RequestParam String name) {
        genreService.updateGenreById(id, name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/genres/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable Long id) {
        genreService.deleteGenreById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
