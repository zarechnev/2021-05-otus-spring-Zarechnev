package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Comment;

import java.util.List;

public interface CommentService {

    void editById(Long commentId);

    void addByBookId(Long bookId);

    List<Comment> findAll();

    Comment findById(Long commentId);

    void deleteById(Long commentId);
}
