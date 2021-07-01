package ru.otus.zarechnev.library.persistence;

interface AuthorPersistenceService {
    Long checkIfAuthorExistOrCreateAndGetId(String authorName);
}
