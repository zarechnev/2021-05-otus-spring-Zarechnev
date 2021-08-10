package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.repository.CommentRepository;
import ru.otus.zarechnev.library.service.CommentService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentCommandsTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentCommands commentCommands;

    @Test
    void showBookComments() {
        commentCommands.showBookComments();
        verify(commentRepository, times(1)).getAll();
    }

    @Test
    void addCommentByBookId() {
        commentCommands.addCommentByBookId(1L);
        verify(commentService, times(1)).addByBookId(1L);
    }

    @Test
    void showComment() {
        commentCommands.showComment(1L);
        verify(commentRepository, times(1)).getById(1L);
    }

    @Test
    void editComment() {
        commentCommands.editComment(1L);
        verify(commentService, times(1)).editById(1L);
    }

    @Test
    void deleteComment() {
        commentCommands.deleteComment(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }
}