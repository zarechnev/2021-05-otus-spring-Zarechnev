package ru.otus.zarechnev.library.repository;

import ru.otus.zarechnev.library.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment getById(long id);

    Comment update(Comment comment);

    Comment insert(Comment comment);

    void deleteById(long id);

    List<Comment> getAll();
}
