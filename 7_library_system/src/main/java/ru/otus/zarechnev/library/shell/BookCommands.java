package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.persistence.dao.BookDao;
import ru.otus.zarechnev.library.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;
    private final BookDao bookDao;

    @ShellMethod(value = "Add book", key = {"ba", "book add"})
    public void addBook() {
        bookService.addBook();
    }

    @ShellMethod(value = "Show book by Id", key = {"bs", "book show"})
    public Book showBook(Long bookId) {
        return bookDao.getById(bookId);
    }

    @ShellMethod(value = "Show books", key = {"bl", "books list"})
    public List<Book> showBooks() {
        return bookDao.getAll();
    }

    @ShellMethod(value = "Edit book by Id", key = {"be", "book edit"})
    public void editBook(Long bookId) {
        bookService.editBook(bookId);
    }

    @ShellMethod(value = "Delete book by Id", key = {"bd", "book del"})
    public void deleteBook(Long bookId) {
        bookDao.deleteById(bookId);
    }

}