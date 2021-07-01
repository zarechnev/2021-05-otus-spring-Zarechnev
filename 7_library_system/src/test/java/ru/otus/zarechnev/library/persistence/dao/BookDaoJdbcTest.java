package ru.otus.zarechnev.library.persistence.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.persistence.entity.BookEntity;
import ru.otus.zarechnev.library.persistence.sequence.SequenceServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import({
        BookDaoJdbc.class,
        GenreDaoJdbc.class,
        AuthorDaoJdbc.class,
        SequenceServiceImpl.class
})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void getAll() {
        // given
        List<Book> expected = List.of(
                new Book()
                        .setId(1L)
                        .setTitle("Last Witch")
                        .setGenre(new Genre().setId(2L).setName("Fantasy"))
                        .setAuthor(new Author().setId(2L).setName("Luis Kerol")),
                new Book()
                        .setId(2L)
                        .setTitle("First King")
                        .setGenre(new Genre().setId(1L).setName("Drama"))
                        .setAuthor(new Author().setId(1L).setName("Mark Twain"))
        );

        // when
        List<Book> actual = bookDaoJdbc.getAll();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        // given
        Book expected = new Book()
                .setId(1L)
                .setTitle("Last Witch")
                .setGenre(new Genre().setId(2L).setName("Fantasy"))
                .setAuthor(new Author().setId(2L).setName("Luis Kerol"));

        // when
        Book actual = bookDaoJdbc.getById(1L);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(1L))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(1L);

        assertThatThrownBy(() -> bookDaoJdbc.getById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void insert() {
        // given
        genreDaoJdbc.insert(new Genre().setId(1567L).setName("genre name 143"));
        authorDaoJdbc.insert(new Author().setId(1123L).setName("Author name122"));

        BookEntity bookEntity = new BookEntity()
                .setId(324L)
                .setTitle("title")
                .setAuthorId(1123L)
                .setGenreId(1567L);

        Book expectedBook = new Book()
                .setId(324L)
                .setTitle("title")
                .setAuthor(new Author().setId(1123L).setName("Author name122"))
                .setGenre(new Genre().setId(1567L).setName("genre name 143"));

        // when
        List<Book> allBeforeAdding = bookDaoJdbc.getAll();

        bookDaoJdbc.insert(bookEntity);

        List<Book> allAfterAdding = bookDaoJdbc.getAll();
        allAfterAdding.removeAll(allBeforeAdding);

        // then
        assertEquals(List.of(expectedBook), allAfterAdding);
    }
}