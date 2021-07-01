package ru.otus.zarechnev.library.persistence;

import ru.otus.zarechnev.library.domain.Book;

public interface BookPersistenceService {

    void insert(Book newBook);

    void update(Book editedBook);
}
