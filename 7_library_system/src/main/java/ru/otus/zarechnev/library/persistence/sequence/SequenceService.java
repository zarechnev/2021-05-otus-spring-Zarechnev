package ru.otus.zarechnev.library.persistence.sequence;

public interface SequenceService {
    Long getNewBookId();

    Long getNewGenreId();

    Long getNewAuthorId();
}
