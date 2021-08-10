package ru.otus.zarechnev.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
class AuthorRepositoryJpa implements AuthorRepository {

    private final EntityManager entityManager;

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author getById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void insert(Author newAuthor) {
        entityManager.persist(newAuthor);
    }
}
