package ru.otus.book.service.function;

import ru.otus.book.service.model.Author;

import java.util.List;

public interface AuthorService {

    void createAuthor(Author author);

    Author getAuthorById(Long id);

    List<Author> getAllAuthors();

    void deleteAuthorById(Long id);

    void updateAuthorNameById(Long id, String name);

}
