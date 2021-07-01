package ru.otus.zarechnev.library.persistence;

interface GenrePersistenceService {
    Long checkIfGenreExistOrCreateAndGetId(String genreName);
}
