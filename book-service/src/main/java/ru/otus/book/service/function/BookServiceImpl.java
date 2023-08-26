package ru.otus.book.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.dao.BookDao;
import ru.otus.book.service.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional(readOnly = false)
    public void createBook(Book book) {
        log.info("Вставка книги в таблицу {}", book);
        Book book1 = bookDao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        log.info("Получение книги по id: {}", id);
        Optional<Book> optionalAuthor = bookDao.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            log.info("Нет книги по данному id");
            return new Book();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        log.info("Получение всех книг");
        Iterable<Book> books = bookDao.findAll();
        List<Book> list = new ArrayList<>();
        books.forEach(list::add);
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBookById(Long id) {
        log.info("Удаление книги по id: {}", id);
        bookDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBookNameById(Long id, String name) {
        log.info("Обновление имени книги под id {}", id);
        bookDao.updateTitleById(id, name);
    }
}
