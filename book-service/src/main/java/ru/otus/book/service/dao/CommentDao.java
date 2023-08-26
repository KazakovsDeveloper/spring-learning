package ru.otus.book.service.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.book.service.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao extends CrudRepository<Comment, Long> {

    @Modifying
    @Query("update Comment e set e.comment = :comment where e.id = :id")
    void updateCommentById(Long id, String comment);
}
