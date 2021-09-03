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
    void showAllComments() {
        commentCommands.showAllCommentsByBookTitles();
        verify(commentService, times(1)).findAllByBookTitles();
    }

    @Test
    void showCommentsByBookId() {
        commentCommands.showCommentsByBookId("1");
        verify(commentService, times(1)).findAllForBookByBookId("1");
    }

    @Test
    void addCommentByBookId() {
        commentCommands.addCommentByBookId("1");
        verify(commentService, times(1)).addByBookId("1");
    }

    @Test
    void editCommentByBookId() {
        commentCommands.editCommentByBookId("1L");
        verify(commentService, times(1)).editByBookId("1L");
    }

    @Test
    void deleteCommentByBookId() {
        commentCommands.deleteCommentByBookId("1L");
        verify(commentService, times(1)).deleteByBookId("1L");
    }
}