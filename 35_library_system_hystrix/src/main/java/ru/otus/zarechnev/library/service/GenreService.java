package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findFirstByNameOrCreateAndGet(String name);

    List<Genre> findAll();
}
