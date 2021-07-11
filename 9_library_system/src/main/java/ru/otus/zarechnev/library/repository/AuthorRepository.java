package ru.otus.zarechnev.library.repository;

import ru.otus.zarechnev.library.domain.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> getAll();

    Author getById(Long authorId);

    void insert(Author newAuthor);
}
