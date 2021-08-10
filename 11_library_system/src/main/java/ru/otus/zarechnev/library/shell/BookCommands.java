package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Add book", key = {"ba", "book add"})
    public void addBook() {
        bookService.addBook();
    }

    @ShellMethod(value = "Edit book by Id", key = {"be", "book edit"})
    public void editBook(Long bookId) {
        bookService.editById(bookId);
    }

    @ShellMethod(value = "Show book by Id", key = {"bs", "book show"})
    public Book showBook(Long bookId) {
        return bookService.findById(bookId);
    }

    @ShellMethod(value = "Show all books", key = {"bl", "books list"})
    public List<Book> showBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Delete book by Id", key = {"bd", "book del"})
    public void deleteBook(Long bookId) {
        bookService.deleteById(bookId);
    }

}