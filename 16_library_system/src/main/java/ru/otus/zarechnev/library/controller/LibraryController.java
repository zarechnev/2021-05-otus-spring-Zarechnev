package ru.otus.zarechnev.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.zarechnev.library.dto.BookDTO;
import ru.otus.zarechnev.library.service.BookService;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;

    @GetMapping("/")
    public String allBooksPage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "allBooks.html";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "editBook.html";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook.html";
    }

    @PostMapping("/save")
    public RedirectView saveBook(BookDTO bookDTO) {
        bookService.saveFromDto(bookDTO);
        return new RedirectView("/");
    }

    // TODO org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Значение NULL не разрешено для поля "BOOK_ID"
    // при попытке удалить книгу с комментариями

    @GetMapping("/deleteBook")
    public RedirectView deleteBook(@RequestParam("id") Long id) {
        bookService.deleteById(id);
        return new RedirectView("/");
    }
}
