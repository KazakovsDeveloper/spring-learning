package ru.otus.book.service.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.book.service.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DisplayName("Dao для работы с жанрами должно")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreDaoTest {

    private final static Long EXISTING_GENRE_ID = 1L;

    @Autowired
    private GenreDao dao;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        Genre genre = new Genre();
        genre.setName("мистика");
        Genre save = dao.save(genre);
        Optional<Genre> optionalGenre = dao.findById(save.getId());
        assertTrue(optionalGenre.isPresent());
        System.out.println(optionalGenre.get());
        assertThat(genre).usingRecursiveComparison().isEqualTo(optionalGenre.get());
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Optional<Genre> byId = dao.findById(EXISTING_GENRE_ID);
        Genre genre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(byId).isPresent().get()
                .usingRecursiveComparison().isEqualTo(genre);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> dao.findById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        dao.deleteById(EXISTING_GENRE_ID);

        Optional<Genre> byId = dao.findById(EXISTING_GENRE_ID);
        assertEquals(Optional.empty(), byId);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        Iterable<Genre> all = dao.findAll();
        List<Genre> genres = new ArrayList<>();
        all.forEach(genres::add);
        System.out.println("*****" + all + "*****");
        assertThat(genres.get(0).getName())
                .isEqualTo("проза");
    }

    @DisplayName("обновлять имя жанра в БД")
    @Test
    void shouldUpdateNameOfGenre() {
        dao.updateNameById(1L, "ПРОЗА");
        Optional<Genre> optionalGenre = dao.findById(1L);
        assertTrue(optionalGenre.isPresent());
        System.out.println(optionalGenre.get());
        assertEquals("ПРОЗА", optionalGenre.get().getName());
    }

}