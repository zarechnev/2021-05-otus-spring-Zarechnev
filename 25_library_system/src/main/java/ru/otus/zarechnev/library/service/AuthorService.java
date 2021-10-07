package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findFirstByNameOrCreateAndGet(String name);

    List<Author> findAll();
}
