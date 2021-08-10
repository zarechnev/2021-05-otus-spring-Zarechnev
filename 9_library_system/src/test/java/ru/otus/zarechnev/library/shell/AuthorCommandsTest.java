package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.repository.AuthorRepository;
import ru.otus.zarechnev.library.service.AuthorService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorCommandsTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorCommands authorCommands;

    @Test
    void showAuthors() {
        authorCommands.showAuthors();
        verify(authorRepository, times(1)).getAll();
    }

    @Test
    void addAuthor() {
        authorCommands.addAuthor();
        verify(authorService, times(1)).addAuthor();
    }
}