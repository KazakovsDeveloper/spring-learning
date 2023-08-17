package ru.otus.book.service.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.book.service.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Dao для работы с книгами должно")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookDaoTest {

    private static final Long EXISTING_BOOK_ID = 1L;
    private static final String NEW_BOOK_TITLE = "Анна Каренина";
    private static final String EXISTING_BOOK_TITLE = "Евгений Онегин";
    private static final Long EXISTING_AUTHOR_ID = 1L;
    private static final Long EXISTING_GENRE_ID = 2L;

    @Autowired
    private BookDao dao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book();
        expectedBook.setTitle(NEW_BOOK_TITLE);
        Book save = dao.save(expectedBook);
        Optional<Book> optionalBook = dao.findById(save.getId());
        assertTrue(optionalBook.isPresent());
        System.out.println("***" + optionalBook.get().getId());
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(optionalBook.get());
    }

    @DisplayName("возвращать ожидаемую книгу по ее id")
    @Test
    void shouldReturnExpectedBookById() {
        Optional<Book> optionalBook = dao.findById(EXISTING_BOOK_ID);
        Book expectedBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(optionalBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> dao.findById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        dao.deleteById(EXISTING_BOOK_ID);

        Optional<Book> byId = dao.findById(EXISTING_BOOK_ID);
        assertEquals(Optional.empty(), byId);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Iterable<Book> books = dao.findAll();
        List<Book> books2 = new ArrayList<>();
        books.forEach(books2::add);
        System.out.println("*****" + books + "*****");
        assertThat(books2.get(0).getTitle())
                .isEqualTo("Евгений Онегин");
    }

    @DisplayName("обновлять заголовок книги в БД")
    @Test
    void shouldUpdateTitleOfBook() {
        dao.updateTitleById(1L, "ЕО");
        Optional<Book> optionalBook = dao.findById(1L);
        assertTrue(optionalBook.isPresent());
        System.out.println(optionalBook.get());
        assertEquals("ЕО", optionalBook.get().getTitle());
    }


}