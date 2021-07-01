package ru.otus.zarechnev.library.persistence.sequence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SequenceServiceImpl implements SequenceService {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public synchronized Long getNewBookId() {
        Long currentVal = namedParameterJdbcOperations.getJdbcOperations()
                .queryForObject("SELECT LastValue FROM Sequence WHERE Name = 'Book'", Long.class);
        Long newVal = currentVal + 1;
        namedParameterJdbcOperations.update(
                "UPDATE Sequence SET LastValue = :value WHERE Name = 'Book'",
                Map.of("value", newVal)
        );

        return newVal;
    }

    @Override
    public synchronized Long getNewGenreId() {
        Long currentVal = namedParameterJdbcOperations.getJdbcOperations()
                .queryForObject("SELECT LastValue FROM Sequence WHERE Name = 'Genre'", Long.class);
        Long newVal = currentVal + 1;
        namedParameterJdbcOperations.update(
                "UPDATE Sequence SET LastValue = :value WHERE Name = 'Genre'",
                Map.of("value", newVal)
        );

        return newVal;
    }

    @Override
    public synchronized Long getNewAuthorId() {
        Long currentVal = namedParameterJdbcOperations.getJdbcOperations()
                .queryForObject("SELECT LastValue FROM Sequence WHERE Name = 'Author'", Long.class);
        Long newVal = currentVal + 1;
        namedParameterJdbcOperations.update(
                "UPDATE Sequence SET LastValue = :value WHERE Name = 'Author'",
                Map.of("value", newVal)
        );

        return newVal;
    }
}
