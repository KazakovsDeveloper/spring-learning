package ru.otus.book.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.dao.CommentDao;
import ru.otus.book.service.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void createComment(Comment comment) {
        log.info("Вставка комментария в таблицу {}", comment);
        Comment comment1 = commentDao.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        log.info("Получение комментария по id: {}", id);
        Optional<Comment> optionalComment = commentDao.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            log.info("Нет комментария по данному id");
            return new Comment();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        log.info("Получение всех комментариев");
        Iterable<Comment> all = commentDao.findAll();
        List<Comment> list = new ArrayList<>();
        all.forEach(list::add);
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCommentById(Long id) {
        log.info("Удаление комментарии по id: {}", id);
        commentDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateCommentById(Long id, String comment) {
        log.info("Обновление комментария под id {}", id);
        commentDao.updateCommentById(id, comment);
    }
}
