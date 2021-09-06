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

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void addBook() {
        // when
        bookService.saveBook(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));

        // then
        verify(bookRepository, times(1)).save(new Book()
                .setTitle("Title1")
                .setAuthor(new Author().setId(3L).setName("Name1"))
                .setGenre(new Genre().setId(14L).setName("Genre2")));
    }
}