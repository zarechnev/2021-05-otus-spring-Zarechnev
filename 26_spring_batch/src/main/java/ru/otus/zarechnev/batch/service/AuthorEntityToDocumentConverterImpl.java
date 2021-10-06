package ru.otus.zarechnev.batch.service;

import org.springframework.stereotype.Service;
import ru.otus.zarechnev.batch.domain.AuthorDocument;
import ru.otus.zarechnev.batch.domain.AuthorEntity;

@Service
class AuthorEntityToDocumentConverterImpl implements AuthorEntityToDocumentConverter {

    @Override
    public AuthorDocument toDocument(AuthorEntity authorEntity) {
        return new AuthorDocument()
                .setId(String.valueOf(authorEntity.getId()))
                .setName(authorEntity.getName());
    }
}
