package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.GenreRepository;
import ru.otus.zarechnev.library.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreRepository genreRepository;
    private final GenreService genreService;

    @ShellMethod(value = "Show genres list", key = {"gl", "genre list"})
    public List<Genre> showGenres() {
        return genreRepository.getAll();
    }

    @ShellMethod(value = "Add genre", key = {"ga", "genre add"})
    public void addGenre() {
        genreService.addGenre();
    }
}