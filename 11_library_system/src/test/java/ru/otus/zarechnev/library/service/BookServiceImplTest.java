package ru.otus.zarechnev.library.service;

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

import java.util.List;

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
    private GenreRepository genreRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void addBook() {
        // given
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");

        List<Author> authors = List.of(
                new Author().setId(3L).setName("Name1"),
                new Author().setId(11L).setName("Name2")
        );
        when(authorRepository.findAll()).thenReturn(authors);
        when(userCommunicator.chooseAuthor(authors))
                .thenReturn(new Author().setId(3L).setName("Name1"));

        List<Genre> genres = List.of(
                new Genre().setId(33L).setName("Genre1"),
                new Genre().setId(14L).setName("Genre2")
        );
        when(genreRepository.findAll()).thenReturn(genres);
        when(userCommunicator.chooseGenre(genres)).thenReturn(new Genre().setId(14L).setName("Genre2"));

        // when
        bookService.addBook();

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));
    }

    @Test
    void editBook() {
        // given
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");

        List<Author> authors = List.of(
                new Author().setId(3L).setName("Name1"),
                new Author().setId(11L).setName("Name2")
        );
        when(authorRepository.findAll()).thenReturn(authors);
        when(userCommunicator.chooseAuthor(authors))
                .thenReturn(new Author().setId(3L).setName("Name1"));

        List<Genre> genres = List.of(
                new Genre().setId(33L).setName("Genre1"),
                new Genre().setId(14L).setName("Genre2")
        );
        when(genreRepository.findAll()).thenReturn(genres);
        when(userCommunicator.chooseGenre(genres)).thenReturn(new Genre().setId(14L).setName("Genre2"));

        // when
        bookService.editById(55L);

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setId(55L)
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));
    }
}