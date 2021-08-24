package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Genre;

public interface GenreService {
    Genre findFirstByNameOrCreateAndGet(String name);
}
