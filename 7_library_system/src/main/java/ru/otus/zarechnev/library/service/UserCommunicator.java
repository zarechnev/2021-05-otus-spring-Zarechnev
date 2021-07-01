package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Book;

interface UserCommunicator {
    Book getNewBook();

    Book getEditedBook(Book book);
}
