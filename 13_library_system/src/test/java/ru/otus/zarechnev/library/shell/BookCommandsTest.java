package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.service.BookService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookCommandsTest {

    @Mock
    private BookService bookService;
    @InjectMocks
    private BookCommands bookCommands;

    @Test
    void addBook() {
        bookCommands.addBook();
        verify(bookService, times(1)).addBook();
    }

    @Test
    void showBook() {
        bookCommands.showBook("1");
        verify(bookService, times(1)).findById("1");
    }

    @Test
    void showBooks() {
        bookCommands.showBooks();
        verify(bookService, times(1)).findAll();
    }

    @Test
    void editBook() {
        bookCommands.editBook("1");
        verify(bookService, times(1)).editById("1");
    }

    @Test
    void deleteBook() {
        bookCommands.deleteBook("1");
        verify(bookService, times(1)).deleteById("1");
    }
}