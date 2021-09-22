package ru.otus.zarechnev.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.repository.BookRepository;

@Slf4j
@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Mono<Book> saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Mono<Book> findById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    @Transactional
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(String bookId) {
        return bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public Mono<Void> saveFromDto(BookDTO bookDTO) {
        log.info("Starting saving edited book by dto={}", bookDTO);

        authorService.findByName(bookDTO.getAuthor())
                .subscribe(author -> {
                    log.info("Author={} was found by name={}", author, bookDTO.getAuthor());
                    genreService.findByName(bookDTO.getGenre())
                            .subscribe(genre -> {
                                log.info("Genre={} was found by name={}", genre, bookDTO.getGenre());
                                bookRepository.save(new Book()
                                                .setId(bookDTO.getId())
                                                .setTitle(bookDTO.getTitle())
                                                .setAuthor(author)
                                                .setGenre(genre))
                                        .subscribe(book -> log.info("New book={} was saved successfully", book));
                            });
                });

        return Mono.empty();
    }
}
