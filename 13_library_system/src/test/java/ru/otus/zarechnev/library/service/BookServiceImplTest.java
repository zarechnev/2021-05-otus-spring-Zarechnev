package ru.otus.zarechnev.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.AuthorRepository;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.repository.GenreRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private UserCommunicator userCommunicator;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");
        when(userCommunicator.getAnswer("Author name:")).thenReturn("Author1");
        when(userCommunicator.getAnswer("Genre:")).thenReturn("Genre1");
        when(authorRepository.save(new Author().setName("Author1")))
                .thenReturn(new Author().setName("Author1").setId("a"));
        when(genreRepository.save(new Genre().setName("Genre1")))
                .thenReturn(new Genre().setName("Genre1").setId("g"));
    }

    @Test
    void addBook() {
        // when
        bookService.addBook();

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setName("Author1").setId("a"))
                .setGenre(new Genre().setName("Genre1").setId("g")));
    }

    @Test
    void editBook() {
        // when
        bookService.editById("55L");

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setId("55L")
                .setTitle("Title1")
                .setAuthor(new Author().setName("Author1").setId("a"))
                .setGenre(new Genre().setName("Genre1").setId("g")));
    }
}