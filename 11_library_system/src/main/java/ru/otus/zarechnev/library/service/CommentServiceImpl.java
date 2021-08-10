package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserCommunicator userCommunicator;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void editById(Long commentId) {
        Comment commentById = commentRepository.findById(commentId).get();
        String commentText = userCommunicator.getAnswer("Write comment please:");
        commentRepository.save(commentById.setCommentText(commentText));
    }

    @Override
    @Transactional
    public void addByBookId(Long bookId) {
        Book bookById = bookRepository.findById(bookId).get();
        String commentText = userCommunicator.getAnswer("Write comment please:");
        commentRepository.save(new Comment().setCommentText(commentText).setBook(bookById));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Override
    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
