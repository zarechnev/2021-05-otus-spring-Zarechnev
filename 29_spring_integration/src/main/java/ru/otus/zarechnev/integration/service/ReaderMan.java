package ru.otus.zarechnev.integration.service;

import ru.otus.zarechnev.integration.domain.Book;

public interface ReaderMan {
    Book onGettingBook(Book book);
}
