package ru.otus.book.service.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.book.service.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Dao для работы с комментариями должно")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentDaoTest {

    private final static Long EXISTING_COMMENT_ID = 1L;

    @Autowired
    private CommentDao dao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldInsertComment() {
        Comment comment = new Comment();
        comment.setComment("Oyyy");
        Comment save = dao.save(comment);
        Optional<Comment> optionalComment = dao.findById(save.getId());
        assertTrue(optionalComment.isPresent());
        System.out.println("***"+optionalComment.get().getId());
        assertThat(comment).usingRecursiveComparison().isEqualTo(optionalComment.get());
    }

    @DisplayName("возвращать ожидаемый комментарий по его id")
    @Test
    void shouldReturnExpectedCommentById() {
        Optional<Comment> optionalComment = dao.findById(EXISTING_COMMENT_ID);
        Comment comment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(optionalComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(comment);
    }

    @DisplayName("удалять заданный комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        assertThatCode(() -> dao.findById(EXISTING_COMMENT_ID))
                .doesNotThrowAnyException();

        dao.deleteById(EXISTING_COMMENT_ID);

        Optional<Comment> byId = dao.findById(EXISTING_COMMENT_ID);
        assertEquals(Optional.empty(), byId);
    }

    @DisplayName("возвращать ожидаемый список комментариев")
    @Test
    void shouldReturnExpectedCommentList() {
        Iterable<Comment> all = dao.findAll();
        List<Comment> comments = new ArrayList<>();
        all.forEach(comments::add);
        System.out.println("*****" + all + "*****");
        assertThat(comments.get(0).getComment())
                .isEqualTo("круто");
    }

}