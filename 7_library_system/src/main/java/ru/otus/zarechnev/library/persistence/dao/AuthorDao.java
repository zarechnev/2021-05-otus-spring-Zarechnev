package ru.otus.zarechnev.library.persistence.dao;

import ru.otus.zarechnev.library.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(Long authorId);

    void insert(Author newAuthor);
}
