package ru.otus.zarechnev.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.service.AuthorService;
import ru.otus.zarechnev.library.service.BookService;
import ru.otus.zarechnev.library.service.GenreService;

@RestController
@RequiredArgsConstructor
public class APIController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/books")
    public Flux<BookDTO> allBooks() {
        return bookService.findAll()
                .map(BookDTO::of);
    }

    @GetMapping("/books/{id}")
    public Mono<BookDTO> bookById(@PathVariable("id") String id) {
        return bookService.findById(id)
                .map(BookDTO::of);
    }

    @PostMapping("/books")
    public Mono<Void> saveBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveFromDto(bookDTO);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookService.deleteById(id);
    }

    @GetMapping("/genres")
    public Flux<Genre> allGenres() {
        return genreService.findAll();
    }

    @GetMapping("/authors")
    public Flux<Author> allAuthors() {
        return authorService.findAll();
    }
}
