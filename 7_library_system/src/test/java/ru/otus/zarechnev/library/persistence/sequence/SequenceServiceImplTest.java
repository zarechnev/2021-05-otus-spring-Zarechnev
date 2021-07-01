package ru.otus.zarechnev.library.persistence.sequence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(SequenceServiceImpl.class)
class SequenceServiceImplTest {

    @Autowired
    private SequenceService sequenceService;

    @Test
    void getNewBookId() {
        assertEquals(sequenceService.getNewBookId() + 1, sequenceService.getNewBookId());
    }

    @Test
    void getNewGenreId() {
        assertEquals(sequenceService.getNewGenreId() + 1, sequenceService.getNewGenreId());
    }

    @Test
    void getNewAuthorId() {
        assertEquals(sequenceService.getNewAuthorId() + 1, sequenceService.getNewAuthorId());
    }

}