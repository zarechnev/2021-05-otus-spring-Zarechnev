package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.dao.GenreDao;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreDao genreDao;

    @ShellMethod(value = "Show genres list", key = {"gl", "genre list"})
    public List<Genre> showGenres() {
        return genreDao.getAll();
    }

}