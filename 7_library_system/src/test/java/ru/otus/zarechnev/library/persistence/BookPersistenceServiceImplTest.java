package ru.otus.zarechnev.library.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.persistence.dao.AuthorDao;
import ru.otus.zarechnev.library.persistence.dao.BookDao;
import ru.otus.zarechnev.library.persistence.dao.GenreDao;
import ru.otus.zarechnev.library.persistence.exception.IdForNewObjectNotSupported;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BookPersistenceServiceImplTest {

    @Mock
    private SequenceService sequenceService;
    @Mock
    private BookDao bookDao;
    @Mock
    private AuthorDao authorDao;
    @Mock
    private GenreDao genreDao;
    @Mock
    private AuthorPersistenceService authorPersistenceService;
    @Mock
    private GenrePersistenceService genrePersistenceService;

    @InjectMocks
    private BookPersistenceServiceImpl bookPersistenceService;

    @Test
    void insertThrowWhenIdPresent() {
        assertThrows(
                IdForNewObjectNotSupported.class,
                () -> bookPersistenceService.insert(new Book().setId(38L))
        );
    }
}