package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final UserCommunicator userCommunicator;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Map<String, List<Comment>> findAllByBookTitles() {
        return bookRepository.findAll().stream()
                .filter(item -> item.getComments() != null && !item.getComments().isEmpty())
                .collect(Collectors.toMap(Book::getTitle, Book::getComments));
    }

    @Override
    public List<Comment> findAllForBookByBookId(String bookId) {
        return bookRepository.findById(bookId).get().getComments();
    }

    @Override
    @Transactional
    public void addByBookId(String bookId) {
        String commentText = userCommunicator.getAnswer("Write comment please:");
        Book bookById = bookRepository.findById(bookId).get();
        bookById.getComments().add(new Comment().setCommentText(commentText));
        bookRepository.save(bookById);
    }

    @Override
    @Transactional
    public void editByBookId(String bookId) {
        Book book = bookRepository.findById(bookId).get();
        List<Comment> comments = book.getComments();
        Comment comment = userCommunicator.chooseComment(comments);
        int index = comments.indexOf(comment);
        String answer = userCommunicator.getAnswer("Write new comment please:");
        comments.get(index).setCommentText(answer);
        bookRepository.save(book);
    }

    @Override
    public void deleteByBookId(String bookId) {
        Book book = bookRepository.findById(bookId).get();
        List<Comment> comments = book.getComments();
        Comment comment = userCommunicator.chooseComment(comments);
        comments.remove(comment);
        bookRepository.save(book);
    }
}
