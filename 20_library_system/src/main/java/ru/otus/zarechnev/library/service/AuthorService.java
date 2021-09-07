package ru.otus.zarechnev.library.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.zarechnev.library.domain.Author;

public interface AuthorService {
    Mono<Author> findByName(String name);

    Flux<Author> findAll();
}
