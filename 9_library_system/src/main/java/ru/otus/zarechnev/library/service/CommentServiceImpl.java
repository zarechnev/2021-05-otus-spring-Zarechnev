package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.repository.CommentRepository;

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserCommunicator userCommunicator;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void editById(Long commentId) {
        Comment commentById = commentRepository.getById(commentId);
        String commentText = userCommunicator.getAnswer("Write comment please:");
        commentRepository.update(commentById.setCommentText(commentText));
    }

    @Override
    @Transactional
    public void addByBookId(Long bookId) {
        Book bookById = bookRepository.getById(bookId);
        String commentText = userCommunicator.getAnswer("Write comment please:");
        commentRepository.insert(new Comment().setCommentText(commentText).setBook(bookById));
    }
}
