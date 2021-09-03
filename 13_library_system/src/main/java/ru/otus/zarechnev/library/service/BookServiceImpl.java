package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.AuthorRepository;
import ru.otus.zarechnev.library.repository.BookRepository;
import ru.otus.zarechnev.library.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final UserCommunicator userCommunicator;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    @Transactional
    public void addBook() {
        bookRepository.save(fillAndGetBook());
    }

    @Override
    @Transactional
    public void editById(String bookId) {
        bookRepository.save(fillAndGetBook().setId(bookId));
    }

    @Override
    public void deleteById(String bookId) {
        bookRepository.deleteById(bookId);
    }

    private Book fillAndGetBook() {
        String title = userCommunicator.getAnswer("Title:");
        String authorName = userCommunicator.getAnswer("Author name:");
        String genreName = userCommunicator.getAnswer("Genre:");

        return new Book()
                .setTitle(title)
                .setAuthor(authorRepository.save(new Author().setName(authorName)))
                .setGenre(genreRepository.save(new Genre().setName(genreName)));
    }
}
