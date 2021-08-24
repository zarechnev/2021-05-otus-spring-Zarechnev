package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Author;

public interface AuthorService {
    Author findFirstByNameOrCreateAndGet(String name);
}
