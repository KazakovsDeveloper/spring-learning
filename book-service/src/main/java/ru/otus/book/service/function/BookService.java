package ru.otus.book.service.function;

import ru.otus.book.service.model.Book;

import java.util.List;

public interface BookService {

    void createBook(Book book);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    void deleteBookById(Long id);

    void updateBookNameById(Long id, String name);

}
