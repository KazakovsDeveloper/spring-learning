package ru.otus.book.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.book.service.function.AuthorService;
import ru.otus.book.service.model.Author;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "/authors")
    public ResponseEntity<?> getAuthors() {
        List<Author> allAuthors = authorService.getAllAuthors();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @GetMapping(path = "/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Author authorById = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorById, HttpStatus.OK);
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id,
                                          @RequestParam String name) {
        authorService.updateAuthorNameById(id, name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
