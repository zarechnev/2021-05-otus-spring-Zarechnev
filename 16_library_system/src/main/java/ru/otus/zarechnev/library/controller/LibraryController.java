package ru.otus.zarechnev.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.service.AuthorService;
import ru.otus.zarechnev.library.service.BookService;
import ru.otus.zarechnev.library.service.GenreService;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String allBooksPage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "allBooks";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "saveBook";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book().setAuthor(new Author()).setGenre(new Genre()));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "saveBook";
    }

    @PostMapping("/save")
    public RedirectView saveBook(BookDTO bookDTO) {
        bookService.saveFromDto(bookDTO);
        return new RedirectView("/");
    }

    @GetMapping("/deleteBook")
    public RedirectView deleteBook(@RequestParam("id") Long id) {
        bookService.delete(bookService.findById(id));
        return new RedirectView("/");
    }
}
