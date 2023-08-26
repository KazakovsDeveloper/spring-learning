package ru.otus.book.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.book.service.function.CommentService;
import ru.otus.book.service.model.Comment;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/comments")
    public ResponseEntity<?> getComments() {
        List<Comment> allComments = commentService.getAllComments();
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @GetMapping(path = "/comments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        Comment commentById = commentService.getCommentById(id);
        return new ResponseEntity<>(commentById, HttpStatus.OK);
    }

    @PostMapping(path = "/comments")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id,
                                           @RequestParam String name) {
        commentService.updateCommentById(id, name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
