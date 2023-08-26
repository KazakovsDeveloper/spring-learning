package ru.otus.book.service.function;

import ru.otus.book.service.model.Genre;

import java.util.List;

public interface GenreService {

    void createGenre(Genre genre);

    Genre getGenreById(Long id);

    List<Genre> getAllGenres();

    void deleteGenreById(Long id);

    void updateGenreById(Long id, String name);

}
