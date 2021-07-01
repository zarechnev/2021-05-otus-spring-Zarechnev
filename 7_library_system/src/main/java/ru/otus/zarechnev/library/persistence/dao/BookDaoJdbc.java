package ru.otus.zarechnev.library.persistence.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.entity.BookEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations
                .query("SELECT Books.Id, Title, Genres.Id as GenreId, Genres.Name as GenreName, Authors.Id as AuthorId," +
                                " Authors.Name as AuthorName" +
                                " FROM Books, Genres, Authors" +
                                " WHERE Books.GenreId = Genres.Id" +
                                " AND Books.AuthorId = Authors.Id",
                        new BookMapper()
                );
    }

    @Override
    public Book getById(Long bookId) {
        Map<String, Object> params = Collections.singletonMap("id", bookId);
        return namedParameterJdbcOperations
                .queryForObject("SELECT Books.Id, Title, Genres.Id as GenreId, Genres.Name as GenreName, Authors.Id as AuthorId," +
                                " Authors.Name as AuthorName" +
                                " FROM Books, Genres, Authors" +
                                " WHERE Books.Id = :id" +
                                " AND Books.GenreId = Genres.Id" +
                                " AND Books.AuthorId = Authors.Id",
                        params, new BookMapper()
                );
    }

    @Override
    public void deleteById(Long bookId) {
        Map<String, Object> params = Collections.singletonMap("id", bookId);
        namedParameterJdbcOperations.update("DELETE FROM Books WHERE Id = :id", params);
    }

    @Override
    public void insert(BookEntity bookEntity) {
        Map<String, Object> params = Map.of(
                "id", bookEntity.getId(),
                "title", bookEntity.getTitle(),
                "genreId", bookEntity.getGenreId(),
                "authorId", bookEntity.getAuthorId()
        );
        namedParameterJdbcOperations.update(
                "INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (:id, :title, :genreId, :authorId)",
                params
        );
    }

    @Override
    public void update(BookEntity bookEntity) {
        Map<String, Object> params = Map.of(
                "id", bookEntity.getId(),
                "title", bookEntity.getTitle(),
                "genreId", bookEntity.getGenreId(),
                "authorId", bookEntity.getAuthorId()
        );
        namedParameterJdbcOperations.update(
                "UPDATE Books SET Title = :title," +
                        "GenreId = :genreId," +
                        "AuthorId = :authorId WHERE Id = :id",
                params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("Id");
            String title = resultSet.getString("Title");
            Long genreId = resultSet.getLong("GenreId");
            String genreName = resultSet.getString("GenreName");
            Long authorId = resultSet.getLong("AuthorId");
            String authorName = resultSet.getString("AuthorName");
            return new Book()
                    .setId(id)
                    .setTitle(title)
                    .setGenre(new Genre().setId(genreId).setName(genreName))
                    .setAuthor(new Author().setId(authorId).setName(authorName));
        }
    }
}
