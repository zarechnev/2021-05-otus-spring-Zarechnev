package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Show genres list", key = {"gl", "genre list"})
    public List<Genre> showGenres() {
        return genreService.findAll();
    }

    @ShellMethod(value = "Add genre", key = {"ga", "genre add"})
    public void addGenre() {
        genreService.addGenre();
    }
}