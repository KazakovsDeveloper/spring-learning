package ru.otus.book.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.dao.AuthorDao;
import ru.otus.book.service.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void createAuthor(Author author) {
        log.info("Вставка автора в таблицу {}", author);
        Author author1 = authorDao.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(Long id) {
        log.info("Получение автора по id: {}", id);
        Optional<Author> optionalAuthor = authorDao.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            log.info("Нет автора по данному id");
            return new Author();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        log.info("Получение всех авторов");
        Iterable<Author> authors = authorDao.findAll();
        List<Author> list = new ArrayList<>();
        authors.forEach(list::add);
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAuthorById(Long id) {
        log.info("Удаление автора по id: {}", id);
        authorDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateAuthorNameById(Long id, String name) {
        log.info("Обновление имени автора под id {}", id);
        authorDao.updateNameById(id, name);
    }
}
