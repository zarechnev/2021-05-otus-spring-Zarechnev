package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Map<String, List<Comment>> findAllByBookTitles();

    List<Comment> findAllForBookByBookId(String bookId);

    void addByBookId(String bookId);

    void editByBookId(String bookId);

    void deleteByBookId(String bookId);
}
