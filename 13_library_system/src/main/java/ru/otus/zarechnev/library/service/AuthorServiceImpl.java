package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public List<Author> findAll() {
        return bookRepository.findAll().stream()
                .map(Book::getAuthor)
                .collect(Collectors.toList());
    }
}