package ru.otus.zarechnev.library.persistence.dao;

import ru.otus.zarechnev.library.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    Genre getById(Long genreId);

    void insert(Genre newGenre);
}
