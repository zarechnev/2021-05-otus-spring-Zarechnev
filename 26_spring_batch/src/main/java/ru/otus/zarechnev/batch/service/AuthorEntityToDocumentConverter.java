package ru.otus.zarechnev.batch.service;

import ru.otus.zarechnev.batch.domain.AuthorDocument;
import ru.otus.zarechnev.batch.domain.AuthorEntity;

public interface AuthorEntityToDocumentConverter {
    AuthorDocument toDocument(AuthorEntity authorEntity);
}
