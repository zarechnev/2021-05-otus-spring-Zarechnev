package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();
}
