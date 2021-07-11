package ru.otus.zarechnev.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.domain.Genre;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void getAll() {
        // given
        List<Book> expected = List.of(
                new Book()
                        .setId(1L)
                        .setTitle("Last Witch")
                        .setGenre(new Genre().setId(2L).setName("Fantasy"))
                        .setAuthor(new Author().setId(2L).setName("Luis Kerol"))
                        .setComments(Collections.emptyList()),
                new Book()
                        .setId(2L)
                        .setTitle("First King")
                        .setGenre(new Genre().setId(1L).setName("Drama"))
                        .setAuthor(new Author().setId(2L).setName("Luis Kerol"))
                        .setComments(Collections.emptyList())
        );

        // when
        List<Book> actual = bookRepository.getAll();

        // then
        assertNotNull(actual.get(1).getComments().get(0).getBook());

        assertEquals(2, actual.get(1).getComments().size());
        assertEquals(
                new Comment().setId(1L).setCommentText("Абырвалг"),
                actual.get(1).getComments().get(0).setBook(null)
        );

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
        assertEquals(expected.get(1), actual.get(1).setComments(Collections.emptyList()));
    }

    @Test
    void getById() {
        // given
        Book expected = new Book()
                .setId(1L)
                .setTitle("Last Witch")
                .setGenre(new Genre().setId(2L).setName("Fantasy"))
                .setAuthor(new Author().setId(2L).setName("Luis Kerol"))
                .setComments(Collections.emptyList());

        // when
        Book actual = bookRepository.getById(1L);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        // given
        assertNotNull(bookRepository.getById(1L));

        // when
        bookRepository.deleteById(1L);

        // then
        assertNull(bookRepository.getById(1L));
    }

    @Test
    void insert() {
        // given
        Book newBook = new Book()
                .setTitle("title")
                .setAuthor(new Author().setName("Author name122"))
                .setGenre(new Genre().setName("genre name 143"));

        // when
        String allBooksQueryStr = "select b from Book b join fetch b.author join fetch b.genre";
        List<Book> allBeforeAdding = entityManager.createQuery(allBooksQueryStr, Book.class).getResultList();

        bookRepository.insert(newBook);

        List<Book> allAfterAdding = entityManager.createQuery(allBooksQueryStr, Book.class).getResultList();
        allAfterAdding.removeAll(allBeforeAdding);

        // then
        assertEquals(List.of(newBook), allAfterAdding);
    }

    @Test
    void update() {
        // given
        String newTittle = "NewTitle";

        // when
        bookRepository.update(new Book().setId(1L).setTitle(newTittle));

        // then
        assertEquals(newTittle, entityManager.find(Book.class, 1L).getTitle());
    }
}