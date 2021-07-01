package ru.otus.zarechnev.library.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.dao.GenreDao;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenrePersistenceServiceImplTest {

    @Mock
    private GenreDao genreDao;
    @Mock
    private SequenceService sequenceService;
    @InjectMocks
    private GenrePersistenceServiceImpl genrePersistenceService;

    @Test
    void existingGenre() {
        // given
        when(genreDao.getAll()).thenReturn(List.of(
                new Genre().setId(22L).setName("EXIST"),
                new Genre().setId(23L).setName("EXIST2")
        ));

        // when
        Long actual = genrePersistenceService.checkIfGenreExistOrCreateAndGetId("EXIST");

        // then
        assertEquals(22L, actual);
        verify(sequenceService, never()).getNewGenreId();
    }

    @Test
    void newGenre() {
        // given
        when(genreDao.getAll()).thenReturn(List.of(
                new Genre().setId(22L).setName("EXIST"),
                new Genre().setId(23L).setName("EXIST2")
        ));
        when(sequenceService.getNewGenreId()).thenReturn(33L);

        // when
        Long actual = genrePersistenceService.checkIfGenreExistOrCreateAndGetId("NON Exist");

        // then
        assertEquals(33L, actual);
        verify(genreDao, times(1)).insert(new Genre().setId(33L).setName("NON Exist"));
    }
}