package ru.otus.zarechnev.library.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.zarechnev.library.domain.Genre;

public interface GenreService {
    Mono<Genre> findByName(String name);

    Flux<Genre> findAll();
}
