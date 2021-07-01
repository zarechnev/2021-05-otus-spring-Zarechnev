package ru.otus.zarechnev.library.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.persistence.dao.AuthorDao;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorPersistenceServiceImplTest {

    @Mock
    private AuthorDao genreDao;
    @Mock
    private SequenceService sequenceService;
    @InjectMocks
    private AuthorPersistenceServiceImpl genrePersistenceService;

    @Test
    void existingAuthor() {
        // given
        when(genreDao.getAll()).thenReturn(List.of(
                new Author().setId(22L).setName("EXIST"),
                new Author().setId(23L).setName("EXIST2")
        ));

        // when
        Long actual = genrePersistenceService.checkIfAuthorExistOrCreateAndGetId("EXIST");

        // then
        assertEquals(22L, actual);
        verify(sequenceService, never()).getNewAuthorId();
    }

    @Test
    void newAuthor() {
        // given
        when(genreDao.getAll()).thenReturn(List.of(
                new Author().setId(22L).setName("EXIST"),
                new Author().setId(23L).setName("EXIST2")
        ));
        when(sequenceService.getNewAuthorId()).thenReturn(33L);

        // when
        Long actual = genrePersistenceService.checkIfAuthorExistOrCreateAndGetId("NON Exist");

        // then
        assertEquals(33L, actual);
        verify(genreDao, times(1)).insert(new Author().setId(33L).setName("NON Exist"));
    }
}