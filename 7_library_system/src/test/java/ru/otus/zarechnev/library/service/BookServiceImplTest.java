package ru.otus.zarechnev.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.BookPersistenceService;
import ru.otus.zarechnev.library.persistence.dao.BookDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private UserCommunicator userCommunicator;
    @Mock
    private BookPersistenceService bookPersistenceService;
    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void addBook() {
        // given
        when(userCommunicator.getNewBook()).thenReturn(new Book().setId(543L));

        // when
        bookService.addBook();

        // then
        verify(bookPersistenceService, times(1)).insert(new Book().setId(543L));
    }

    @Test
    void editBook() {
        // given
        when(bookDao.getById(816L)).thenReturn(new Book().setId(816L));

        Book book = new Book()
                .setId(816L)
                .setTitle("Title")
                .setAuthor(new Author().setId(23L).setName("sdf"))
                .setGenre(new Genre().setId(567L).setName("asd"));

        when(userCommunicator.getEditedBook(new Book().setId(816L))).thenReturn(book);

        // when
        bookService.editBook(816L);

        // then
        verify(bookPersistenceService, times(1)).update(book);
    }
}