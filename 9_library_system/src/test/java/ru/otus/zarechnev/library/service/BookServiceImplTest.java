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
import java.util.Map;

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
        when(authorRepository.getAll())
                .thenReturn(List.of(
                        new Author().setId(3L).setName("Name1"),
                        new Author().setId(11L).setName("Name2")
                ));
        when(userCommunicator.getChooseVariant(
                "Choose author (by id)",
                Map.of("Name1", 3L, "Name2", 11L)
        ))
                .thenReturn(3L);
        when(genreRepository.getAll())
                .thenReturn(List.of(
                        new Genre().setId(33L).setName("Genre1"),
                        new Genre().setId(14L).setName("Genre2")
                ));
        when(userCommunicator.getChooseVariant(
                "Choose genre (by id)",
                Map.of("Genre1", 33L, "Genre2", 14L)
        ))
                .thenReturn(14L);

        // when
        bookService.addBook();

        // then
        verify(bookRepository, times(1)).insert(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));
    }

    @Test
    void editBook() {
        // given
        when(bookRepository.getById(55L)).thenReturn(new Book().setId(55L));
        when(userCommunicator.getAnswer("Title:")).thenReturn("Title1");
        when(authorRepository.getAll())
                .thenReturn(List.of(
                        new Author().setId(3L).setName("Name1"),
                        new Author().setId(11L).setName("Name2")
                ));
        when(userCommunicator.getChooseVariant(
                "Choose author (by id)",
                Map.of("Name1", 3L, "Name2", 11L)
        ))
                .thenReturn(3L);
        when(genreRepository.getAll())
                .thenReturn(List.of(
                        new Genre().setId(33L).setName("Genre1"),
                        new Genre().setId(14L).setName("Genre2")
                ));
        when(userCommunicator.getChooseVariant(
                "Choose genre (by id)",
                Map.of("Genre1", 33L, "Genre2", 14L)
        ))
                .thenReturn(14L);

        // when
        bookService.editById(55L);

        // then
        verify(bookRepository, times(1)).update(new Book()
                .setId(55L)
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));
    }
}