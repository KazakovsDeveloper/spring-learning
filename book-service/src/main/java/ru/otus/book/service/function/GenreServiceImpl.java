package ru.otus.book.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.dao.GenreDao;
import ru.otus.book.service.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }


    @Override
    @Transactional(readOnly = false)
    public void createGenre(Genre genre) {
        log.info("Вставка жанра в таблицу {}", genre);
        Genre insert = genreDao.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(Long id) {
        log.info("Получение жанра по id: {}", id);
        Optional<Genre> optionalGenre = genreDao.findById(id);
        if (optionalGenre.isPresent()) {
            return optionalGenre.get();
        } else {
            log.info("Нет жанра по данному id");
            return new Genre();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        log.info("Получение всех жанров");
        Iterable<Genre> all = genreDao.findAll();
        List<Genre> genres = new ArrayList<>();
        all.forEach(genres::add);
        return genres;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteGenreById(Long id) {
        log.info("Удаление жанра по id: {}", id);
        genreDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateGenreById(Long id, String name) {
        log.info("Обновление названия жанра под id {}", id);
        genreDao.updateNameById(id, name);
    }
}
