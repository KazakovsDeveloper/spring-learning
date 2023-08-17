package ru.otus.book.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.book.service.function.BookService;
import ru.otus.book.service.model.Book;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/books")
    public ResponseEntity<?> getBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book bookById = bookService.getBookById(id);
        return new ResponseEntity<>(bookById, HttpStatus.OK);
    }

    @PostMapping(path = "/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,
                                        @RequestParam String name) {
        bookService.updateBookNameById(id, name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
