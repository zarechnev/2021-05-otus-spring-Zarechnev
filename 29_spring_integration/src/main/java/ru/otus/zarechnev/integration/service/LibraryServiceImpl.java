package ru.otus.zarechnev.integration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.integration.domain.Book;
import ru.otus.zarechnev.integration.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;

    @Override
    public Book getNextBook(Book book) {
        List<Book> all = bookRepository.findAll();
        int nextBookIndex = all.indexOf(book) + 1;

        if (nextBookIndex == all.size()) {
            nextBookIndex -= all.size();
        }

        return all.get(nextBookIndex);
    }
}
