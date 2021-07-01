package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.persistence.dao.GenreDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenreCommandsTest {

    @Mock
    private GenreDao genreDao;

    @InjectMocks
    private GenreCommands genreCommands;

    @Test
    void showGenres() {
        genreCommands.showGenres();
        verify(genreDao, times(1)).getAll();
    }
}