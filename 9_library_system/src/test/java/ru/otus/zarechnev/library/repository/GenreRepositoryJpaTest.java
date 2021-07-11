package ru.otus.zarechnev.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.zarechnev.library.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void getAll() {
        // given
        List<Genre> expected = List.of(
                new Genre().setId(1L).setName("Drama"),
                new Genre().setId(2L).setName("Fantasy")
        );

        // when
        List<Genre> actual = genreRepository.getAll();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        assertEquals(new Genre().setId(1L).setName("Drama"), genreRepository.getById(1L));
    }

    @Test
    void insert() {
        // when
        List<Genre> allBeforeAdding = entityManager.createQuery("select g from Genre g", Genre.class)
                .getResultList();

        genreRepository.insert(new Genre().setName("NewGenre"));

        List<Genre> allAfterAdding = entityManager.createQuery("select g from Genre g", Genre.class)
                .getResultList();

        allAfterAdding.removeAll(allBeforeAdding);

        // then
        assertEquals(List.of(new Genre().setId(3).setName("NewGenre")), allAfterAdding);
    }
}