package ru.otus.zarechnev.library.persistence.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("SELECT Id, Name FROM Genres", new GenreMapper());
    }

    @Override
    public Genre getById(Long genreId) {
        Map<String, Object> params = Collections.singletonMap("id", genreId);
        return namedParameterJdbcOperations
                .queryForObject("SELECT Id, Name FROM Genres WHERE Id = :id", params, new GenreMapper()
        );
    }

    @Override
    public void insert(Genre newGenre) {
        Map<String, Object> params = Map.of(
                "id", newGenre.getId(),
                "name", newGenre.getName()
        );
        namedParameterJdbcOperations.update(
                "INSERT INTO Genres (Id, Name) VALUES (:id, :name)",
                params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("Id");
            String name = resultSet.getString("Name");
            return new Genre().setId(id).setName(name);
        }
    }
}
