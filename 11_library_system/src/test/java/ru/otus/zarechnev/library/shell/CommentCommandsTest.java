package ru.otus.zarechnev.library.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.library.service.CommentService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentCommandsTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentCommands commentCommands;

    @Test
    void showBookComments() {
        commentCommands.showBookComments();
        verify(commentService, times(1)).findAll();
    }

    @Test
    void addCommentByBookId() {
        commentCommands.addCommentByBookId(1L);
        verify(commentService, times(1)).addByBookId(1L);
    }

    @Test
    void showComment() {
        commentCommands.showComment(1L);
        verify(commentService, times(1)).findById(1L);
    }

    @Test
    void editComment() {
        commentCommands.editComment(1L);
        verify(commentService, times(1)).editById(1L);
    }
}