package ru.otus.book.service.function;

import ru.otus.book.service.model.Comment;

import java.util.List;

public interface CommentService {

    void createComment(Comment comment);

    Comment getCommentById(Long id);

    List<Comment> getAllComments();

    void deleteCommentById(Long id);

    void updateCommentById(Long id, String comment);

}
