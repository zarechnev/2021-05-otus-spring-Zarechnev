package ru.otus.zarechnev.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.service.CommentService;

import java.util.List;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    @ShellMethod(value = "Show all comments", key = {"cl", "comment list"})
    public Map<String, List<Comment>> showAllCommentsByBookTitles() {
        return commentService.findAllByBookTitles();
    }

    @ShellMethod(value = "Show comments by book Id", key = {"cb", "comment book"})
    public List<Comment> showCommentsByBookId(String bookId) {
        return commentService.findAllForBookByBookId(bookId);
    }

    @ShellMethod(value = "Add comment by book id", key = {"ca", "comment add"})
    public void addCommentByBookId(String bookId) {
        commentService.addByBookId(bookId);
    }

    @ShellMethod(value = "Edit comment by book Id", key = {"ce", "comment edit"})
    public void editCommentByBookId(String bookId) {
        commentService.editByBookId(bookId);
    }

    @ShellMethod(value = "Delete comment by book Id", key = {"cd", "comment del"})
    public void deleteCommentByBookId(String bookId) {
        commentService.deleteByBookId(bookId);
    }
}