package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;

import java.util.List;

public interface BookService {

    void addBook();

    void editById(Long bookId);

    Book findById(Long bookId);

    List<Book> findAll();

    void deleteById(Long bookId);

    List<Comment> showCommentsByBookId(Long bookId);
}
