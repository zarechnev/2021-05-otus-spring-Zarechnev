package ru.otus.zarechnev.library.service;

import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Genre;

import java.util.List;

interface UserCommunicator {

    String getAnswer(String prompt);

    void notifyUser(String message);

    Author chooseAuthor(List<Author> authors);

    Genre chooseGenre(List<Genre> genres);
}
