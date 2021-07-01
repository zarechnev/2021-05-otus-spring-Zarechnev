package ru.otus.zarechnev.library.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.persistence.dao.BookDao;
import ru.otus.zarechnev.library.persistence.entity.BookEntity;
import ru.otus.zarechnev.library.persistence.exception.IdForNewObjectNotSupported;
import ru.otus.zarechnev.library.persistence.sequence.SequenceService;

@Service
@RequiredArgsConstructor
class BookPersistenceServiceImpl implements BookPersistenceService {

    private final SequenceService sequenceService;
    private final BookDao bookDao;
    private final AuthorPersistenceService authorPersistenceService;
    private final GenrePersistenceService genrePersistenceService;

    @Override
    public void insert(Book newBook) {
        if (newBook.getId() != null) {
            throw new IdForNewObjectNotSupported(newBook.toString());
        }

        Long authorId = authorPersistenceService
                .checkIfAuthorExistOrCreateAndGetId(newBook.getAuthor().getName());
        Long genreId = genrePersistenceService
                .checkIfGenreExistOrCreateAndGetId(newBook.getGenre().getName());

        BookEntity bookEntity = new BookEntity()
                .setId(sequenceService.getNewBookId())
                .setTitle(newBook.getTitle())
                .setAuthorId(authorId)
                .setGenreId(genreId);

        bookDao.insert(bookEntity);
    }

    @Override
    public void update(Book editedBook) {
        Long authorId = authorPersistenceService
                .checkIfAuthorExistOrCreateAndGetId(editedBook.getAuthor().getName());
        Long genreId = genrePersistenceService
                .checkIfGenreExistOrCreateAndGetId(editedBook.getGenre().getName());

        BookEntity bookEntity = new BookEntity()
                .setId(editedBook.getId())
                .setTitle(editedBook.getTitle())
                .setAuthorId(authorId)
                .setGenreId(genreId);

        bookDao.update(bookEntity);
    }
}
