package ru.otus.zarechnev.library.repository;

import ru.otus.zarechnev.library.domain.Book;

import java.util.List;

public interface BookRepository {
    Book insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    Book update(Book book);
}
