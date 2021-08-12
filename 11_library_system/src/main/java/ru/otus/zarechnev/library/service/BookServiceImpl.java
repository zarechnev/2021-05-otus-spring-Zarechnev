package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Comment;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.AuthorRepository;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final UserCommunicator userCommunicator;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void addBook() {
        bookRepository.save(fillBook());
    }

    @Override
    @Transactional
    public void editById(Long bookId) {
        bookRepository.save(fillBook().setId(bookId));
    }

    @Override
    @Transactional
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public List<Comment> showCommentsByBookId(Long bookId) {
        return bookRepository.findById(bookId).get().getComments().stream().collect(Collectors.toList());
    }

    private Book fillBook() {
        String title = userCommunicator.getAnswer("Title:");
        Author author = userCommunicator.chooseAuthor(authorRepository.findAll());
        Genre genre = userCommunicator.chooseGenre(genreRepository.findAll());

        return new Book()
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre);
    }
}
