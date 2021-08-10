package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.service.GenreService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenreCommandsTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreCommands genreCommands;

    @Test
    void showGenres() {
        genreCommands.showGenres();
        verify(genreService, times(1)).findAll();
    }

    @Test
    void addGenre() {
        genreCommands.addGenre();
        verify(genreService, times(1)).addGenre();
    }
}