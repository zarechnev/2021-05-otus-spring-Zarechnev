package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(String bookId);

    void addBook();

    void editById(String bookId);

    void deleteById(String bookId);
}
