package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.persistence.dao.AuthorDao;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorDao authorDao;

    @ShellMethod(value = "Show authors", key = {"al", "author list"})
    public List<Author> showAuthors() {
        return authorDao.getAll();
    }

}