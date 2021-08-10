package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class GenreServiceImpl implements GenreService {

    private final BookRepository bookRepository;

    @Override
    public List<Genre> findAll() {
        return bookRepository.findAll().stream()
                .map(Book::getGenre)
                .collect(Collectors.toList());
    }
}
