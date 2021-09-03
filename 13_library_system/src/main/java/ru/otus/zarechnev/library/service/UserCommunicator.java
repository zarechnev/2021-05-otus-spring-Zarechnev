package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Comment;

import java.util.List;

interface UserCommunicator {

    String getAnswer(String prompt);

    Comment chooseComment(List<Comment> comments);
}
