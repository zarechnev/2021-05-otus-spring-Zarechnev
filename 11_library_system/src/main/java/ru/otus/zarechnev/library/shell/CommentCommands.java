package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.service.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Show all comments", key = {"cl", "comment list"})
    public List<Comment> showBookComments() {
        return commentService.findAll();
    }

    @ShellMethod(value = "Add comment", key = {"ca", "comment add"})
    public void addCommentByBookId(Long bookId) {
        commentService.addByBookId(bookId);
    }

    @ShellMethod(value = "Show comment by Id", key = {"cs", "comment show"})
    public Comment showComment(Long commentId) {
        return commentService.findById(commentId);
    }

    @ShellMethod(value = "Edit comment by Id", key = {"ce", "comment edit"})
    public void editComment(Long commentId) {
        commentService.editById(commentId);
    }

    @ShellMethod(value = "Delete comment by Id", key = {"cd", "comment del"})
    public void deleteComment(Long commentId) {
        commentService.deleteById(commentId);
    }

    @ShellMethod(value = "Show comments by book Id", key = {"cb", "comment book"})
    public List<Comment> showCommentsByBookId(Long bookId) {
        return commentService.showCommentsByBookId(bookId);
    }
}