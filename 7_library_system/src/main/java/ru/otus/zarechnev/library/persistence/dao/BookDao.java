package ru.otus.zarechnev.library.persistence.dao;

import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.persistence.entity.BookEntity;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(Long bookId);

    void deleteById(Long bookId);

    void insert(BookEntity book);

    void update(BookEntity bookEntity);
}
