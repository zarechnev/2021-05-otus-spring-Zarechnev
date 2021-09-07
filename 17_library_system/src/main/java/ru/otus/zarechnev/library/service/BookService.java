package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    Book saveFromDto(BookDTO bookDTO);

    Book findById(Long bookId);

    List<Book> findAll();

    void deleteById(Long bookId);
}
