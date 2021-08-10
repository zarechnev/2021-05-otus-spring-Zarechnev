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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void getById() {
        // when
        Comment byId = commentRepository.getById(1);

        // then
        assertEquals(1L, byId.getId());
        assertEquals(2L, byId.getBook().getId());
        assertEquals("Абырвалг", byId.getCommentText());
    }

    @Test
    void update() {
        // given
        String newCommentText = "NewCommentText";

        // when
        commentRepository.update(new Comment().setId(1L).setCommentText(newCommentText));

        // then
        assertEquals(newCommentText, entityManager.find(Comment.class, 1L).getCommentText());
    }

    @Test
    void insert() {
        // given
        Comment comment = new Comment()
                .setCommentText("Commmment")
                .setBook(new Book()
                        .setTitle("")
                        .setGenre(new Genre()
                                .setName(""))
                        .setAuthor(new Author()
                                .setName(""))
                );

        // when
        String allCommentsQueryStr = "select c from Comment c join fetch c.book";

        List<Comment> allBeforeAdding = entityManager.createQuery(allCommentsQueryStr, Comment.class).getResultList();

        commentRepository.insert(comment);

        List<Comment> allAfterAdding = entityManager.createQuery(allCommentsQueryStr, Comment.class).getResultList();
        allAfterAdding.removeAll(allBeforeAdding);

        // then
        assertEquals(1, allAfterAdding.size());
        assertEquals("Commmment", allAfterAdding.get(0).getCommentText());
    }

    @Test
    void deleteById() {
        // given
        assertNotNull(commentRepository.getById(1L));

        // when
        commentRepository.deleteById(1L);

        // then
        assertNull(commentRepository.getById(1L));
    }

    @Test
    void getAll() {
        // when
        List<Comment> actual = commentRepository.getAll();

        // then
        assertEquals(2, actual.size());

        assertEquals(1L, actual.get(0).getId());
        assertEquals(2L, actual.get(0).getBook().getId());
        assertEquals("Абырвалг", actual.get(0).getCommentText());

        assertEquals(2L, actual.get(1).getId());
        assertEquals(2L, actual.get(1).getBook().getId());
        assertEquals("Абырвалг2", actual.get(1).getCommentText());
    }
}