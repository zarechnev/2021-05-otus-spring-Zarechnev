package ru.otus.zarechnev.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
class GenreRepositoryJpa implements GenreRepository {

    private final EntityManager entityManager;

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre getById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public void insert(Genre newGenre) {
        entityManager.persist(newGenre);
    }
}
