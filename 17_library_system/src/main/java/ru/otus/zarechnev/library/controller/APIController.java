package ru.otus.zarechnev.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.service.AuthorService;
import ru.otus.zarechnev.library.service.BookService;
import ru.otus.zarechnev.library.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class APIController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/books")
    public List<BookDTO> allBooks() {
        return bookService.findAll().stream()
                .map(BookDTO::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public BookDTO bookById(@PathVariable("id") Long id) {
        return BookDTO.of(bookService.findById(id));
    }

    @PostMapping("/books")
    public void saveBook(@RequestBody BookDTO bookDTO) {
        bookService.saveFromDto(bookDTO);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/genres")
    public List<String> allGenres() {
        return genreService.findAll().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<String> allAuthors() {
        return authorService.findAll().stream()
                .map(Author::getName)
                .collect(Collectors.toList());
    }
}
