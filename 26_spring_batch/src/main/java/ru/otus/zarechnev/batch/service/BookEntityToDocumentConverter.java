package ru.otus.zarechnev.batch.service;

import ru.otus.zarechnev.batch.domain.BookDocument;
import ru.otus.zarechnev.batch.domain.BookEntity;

public interface BookEntityToDocumentConverter {
    BookDocument toDocument(BookEntity bookEntity);
}
