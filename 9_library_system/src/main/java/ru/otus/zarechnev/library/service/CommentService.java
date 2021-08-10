package ru.otus.zarechnev.library.service;

public interface CommentService {

    void editById(Long commentId);

    void addByBookId(Long bookId);
}
