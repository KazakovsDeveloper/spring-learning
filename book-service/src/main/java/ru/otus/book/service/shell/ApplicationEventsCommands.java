package ru.otus.book.service.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.book.service.function.BookService;
import ru.otus.book.service.model.Author;
import ru.otus.book.service.model.Book;
import ru.otus.book.service.model.Comment;
import ru.otus.book.service.model.Genre;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final BookService bookService;
    private String userName;

    @ShellMethod(value = "Get books", key = {"g", "getAll"})
    public List<Book> getAllBooks(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        System.out.println("Привет " + userName);
        System.out.println("Вот список книг: ");
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Get book", key = {"g1", "getOne"})
    public Book getBookById(@ShellOption Long id) {
        System.out.println("Вот твоя книга: ");
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "Insert book", key = {"in", "insert"})
    public void insertBook(@ShellOption Long id, String title, String authorName, String genreName, String comment) {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(null, comment, null));
        bookService.createBook(
                new Book(null, title, new Author(null, authorName, null),
                        new Genre(null, genreName, null),
                        comments));
        System.out.println("Создана книга: " + bookService.getBookById(id));
    }

    @ShellMethod(value = "Delete book", key = {"d", "delete"})
    public void deleteBook(@ShellOption Long id) {
        bookService.deleteBookById(id);
        System.out.println("Книга удалена");
    }
}
