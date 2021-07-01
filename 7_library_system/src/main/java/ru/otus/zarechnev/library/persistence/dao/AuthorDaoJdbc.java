package ru.otus.zarechnev.library.persistence.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("SELECT Id, Name FROM Authors", new AuthorMapper());
    }

    @Override
    public Author getById(Long authorId) {
        Map<String, Object> params = Collections.singletonMap("id", authorId);
        return namedParameterJdbcOperations
                .queryForObject("SELECT Id, Name FROM Authors WHERE Id = :id", params, new AuthorMapper());
    }

    @Override
    public void insert(Author newAuthor) {
        Map<String, Object> params = Map.of(
                "id", newAuthor.getId(),
                "name", newAuthor.getName()
        );
        namedParameterJdbcOperations.update(
                "INSERT INTO Authors (Id, Name) VALUES (:id, :name)",
                params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("Id");
            String name = resultSet.getString("Name");
            return new Author().setId(id).setName(name);
        }
    }
}
