package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.persistence.dao.AuthorDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorCommandsTest {

    @Mock
    private AuthorDao authorDao;

    @InjectMocks
    private AuthorCommands authorCommands;

    @Test
    void showAuthors() {
        authorCommands.showAuthors();
        verify(authorDao, times(1)).getAll();
    }
}