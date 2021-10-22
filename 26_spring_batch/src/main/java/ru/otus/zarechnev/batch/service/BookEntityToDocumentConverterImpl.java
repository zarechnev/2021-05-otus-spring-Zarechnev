package ru.otus.zarechnev.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.zarechnev.batch.domain.AuthorDocument;
import ru.otus.zarechnev.batch.domain.BookDocument;
import ru.otus.zarechnev.batch.domain.BookEntity;

@Service
class BookEntityToDocumentConverterImpl implements BookEntityToDocumentConverter {

    @Override
    public BookDocument toDocument(BookEntity bookEntity) {
        return new BookDocument()
                .setId(String.valueOf(bookEntity.getId()))
                .setTitle(bookEntity.getTitle())
                .setAuthor(new AuthorDocument()
                        .setId(String.valueOf(bookEntity.getAuthor().getId()))
                        .setName(bookEntity.getAuthor().getName())
                );
    }
}
