package ru.otus.zarechnev.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.BookRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private UserCommunicator userCommunicator;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void addBook() {
        // given
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");
        when(userCommunicator.getAnswer("Author name:")).thenReturn("Author1");
        when(userCommunicator.getAnswer("Genre:")).thenReturn("Genre1");

        // when
        bookService.addBook();

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setName("Author1"))
                .setGenre(new Genre().setName("Genre1")));
    }

    @Test
    void editBook() {
        // given
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");
        when(userCommunicator.getAnswer("Author name:")).thenReturn("Author1");
        when(userCommunicator.getAnswer("Genre:")).thenReturn("Genre1");

        // when
        bookService.editById("55L");

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setId("55L")
                .setTitle("Title1")
                .setAuthor(new Author().setName("Author1"))
                .setGenre(new Genre().setName("Genre1")));
    }
}