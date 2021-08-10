package ru.otus.zarechnev.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.zarechnev.library.domain.Author;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void getAll() {
        // given
        List<Author> expected = List.of(
                new Author().setId(1L).setName("Mark Twain"),
                new Author().setId(2L).setName("Luis Kerol")
        );

        // when
        List<Author> actual = authorRepository.getAll();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        assertEquals(new Author().setId(1L).setName("Mark Twain"), authorRepository.getById(1L));
    }

    @Test
    void insert() {
        // when
        List<Author> allBeforeAdding = entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();

        authorRepository.insert(new Author().setName("NewGenre"));

        List<Author> allAfterAdding = entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();

        allAfterAdding.removeAll(allBeforeAdding);

        // then
        assertEquals(List.of(new Author().setId(3).setName("NewGenre")), allAfterAdding);
    }
}