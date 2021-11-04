package ru.otus.zarechnev.library.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    @HystrixCommand(commandKey="findAllBooks", fallbackMethod="buildFallbackFindAllBooks")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> buildFallbackFindAllBooks() {
        return List.of();
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book saveFromDto(BookDTO bookDTO) {
        Book bookToSave = bookDTO.getId() != null
                ? bookRepository.findById(bookDTO.getId()).get()
                : new Book().setAuthor(new Author()).setGenre(new Genre());

        if (!bookDTO.getAuthor().equals(bookToSave.getAuthor().getName())) {
            Author authorToSavedBook = authorService.findFirstByNameOrCreateAndGet(bookDTO.getAuthor());
            bookToSave.setAuthor(authorToSavedBook);
        }

        if (!bookDTO.getGenre().equals(bookToSave.getGenre().getName())) {
            Genre genreToSavedBook = genreService.findFirstByNameOrCreateAndGet(bookDTO.getGenre());
            bookToSave.setGenre(genreToSavedBook);
        }

        bookToSave.setTitle(bookDTO.getTitle());

        return bookRepository.save(bookToSave);
    }
}
