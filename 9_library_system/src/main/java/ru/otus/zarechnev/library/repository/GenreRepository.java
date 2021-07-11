package ru.otus.zarechnev.library.repository;

import ru.otus.zarechnev.library.domain.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> getAll();

    Genre getById(Long genreId);

    void insert(Genre newGenre);
}
