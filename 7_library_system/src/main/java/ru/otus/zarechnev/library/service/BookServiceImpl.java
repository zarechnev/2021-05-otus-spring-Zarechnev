package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.persistence.BookPersistenceService;
import ru.otus.zarechnev.library.persistence.dao.BookDao;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final UserCommunicator userCommunicator;
    private final BookPersistenceService bookPersistenceService;
    private final BookDao bookDao;

    @Override
    public void addBook() {
        Book newBook = userCommunicator.getNewBook();
        bookPersistenceService.insert(newBook);
    }

    @Override
    public void editBook(Long bookId) {
        Book bookToEdit = bookDao.getById(bookId);
        Book editedBook = userCommunicator.getEditedBook(bookToEdit);
        bookPersistenceService.update(editedBook);
    }
}
