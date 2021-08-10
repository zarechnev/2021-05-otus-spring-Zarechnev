package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.service.BookService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookCommandsTest {

    @Mock
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCommands bookCommands;

    @Test
    void addBook() {
        bookCommands.addBook();
        verify(bookService, times(1)).addBook();
    }

    @Test
    void showBook() {
        bookCommands.showBook(1L);
        verify(bookRepository, times(1)).getById(1L);
    }

    @Test
    void showBooks() {
        bookCommands.showBooks();
        verify(bookRepository, times(1)).getAll();
    }

    @Test
    void editBook() {
        bookCommands.editBook(1L);
        verify(bookService, times(1)).editById(1L);
    }

    @Test
    void deleteBook() {
        bookCommands.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}