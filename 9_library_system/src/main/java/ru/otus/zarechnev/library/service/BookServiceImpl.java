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
import java.util.Map;
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
        bookRepository.insert(fillBook());
    }

    @Override
    @Transactional
    public void editById(Long bookId) {
        Book bookToEdit = bookRepository.getById(bookId);
        if (bookToEdit == null) {
            userCommunicator.notifyUser(String.format("Book with id=%s doesn't exists", bookId));
            return;
        }

        bookRepository.update(fillBook().setId(bookId));
    }

    private Book fillBook() {
        String title = userCommunicator.getAnswer("Title:");

        Author author = getAuthor();
        Genre genre = getGenre();

        return new Book()
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre);
    }

    private Author getAuthor() {
        List<Author> authors = authorRepository.getAll();

        Map<String, Long> collect = authors.stream()
                .collect(Collectors.toMap(Author::getName, Author::getId));

        Long authorId = userCommunicator.getChooseVariant("Choose author (by id)", collect);

        return authors.stream()
                .filter(author -> authorId.equals(author.getId()))
                .findFirst().orElseThrow();
    }

    private Genre getGenre() {
        List<Genre> genres = genreRepository.getAll();

        Map<String, Long> collect = genres.stream()
                .collect(Collectors.toMap(Genre::getName, Genre::getId));

        Long genreId = userCommunicator.getChooseVariant("Choose genre (by id)", collect);

        return genres.stream()
                .filter(genre -> genreId.equals(genre.getId()))
                .findFirst().orElseThrow();
    }
}
