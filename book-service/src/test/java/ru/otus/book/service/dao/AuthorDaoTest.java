package ru.otus.book.service.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.book.service.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Dao для работы с авторами должно")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorDaoTest {

    private final static Long EXISTING_AUTHOR_ID = 1L;

    @Autowired
    private AuthorDao dao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        Author author = new Author();
        author.setName("Александр Дюма");
        Author save = dao.save(author);
        Long id = save.getId();
        Optional<Author> optionalAuthor = dao.findById(id);
        System.out.println("****"+optionalAuthor.get().getId()+"*****");
        assertThat(author).usingRecursiveComparison().isEqualTo(optionalAuthor.get());
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Optional<Author> optionalAuthor = dao.findById(EXISTING_AUTHOR_ID);
        Author expectedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(optionalAuthor).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> dao.findById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        dao.deleteById(EXISTING_AUTHOR_ID);

        Optional<Author> byId = dao.findById(EXISTING_AUTHOR_ID);
        assertEquals(Optional.empty(), byId);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Iterable<Author> all = dao.findAll();
        List<Author> authors = new ArrayList<>();
        all.forEach(authors::add);
        all.forEach(t -> {
            System.out.println("***" + t.getName() + "***");
        });
        assertThat(authors.get(0).getName())
                .isEqualTo("Александр Сергеевич Пушкин");
    }

    @DisplayName("обновлять имя автора в БД")
    @Test
    void shouldUpdateTitleOfBook() {
        dao.updateNameById(1L, "А.С.Пушкин");
        Optional<Author> optionalAuthor = dao.findById(1L);
        assertTrue(optionalAuthor.isPresent());
        System.out.println(optionalAuthor.get());
        assertEquals("А.С.Пушкин", optionalAuthor.get().getName());
    }

}