package ru.otus.zarechnev.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.zarechnev.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
class CommentRepositoryJpa implements CommentRepository {

    private final EntityManager entityManager;

    @Override
    public Comment getById(long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public Comment update(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Comment insert(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public void deleteById(long id) {
        final Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = entityManager
                .createQuery("select c from Comment c join fetch c.book", Comment.class);
        return query.getResultList();
    }
}
