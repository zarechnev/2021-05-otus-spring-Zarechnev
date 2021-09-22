package ru.otus.zarechnev.library.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.dto.BookDTO;

public interface BookService {

    Mono<Book> saveBook(Book book);

    Mono<Void> saveFromDto(BookDTO bookDTO);

    Mono<Book> findById(String bookId);

    Flux<Book> findAll();

    Mono<Void> deleteById(String bookId);
}
