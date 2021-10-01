package ru.otus.zarechnev.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Book;
import ru.otus.zarechnev.library.domain.Genre;
import ru.otus.zarechnev.library.service.AuthorService;
import ru.otus.zarechnev.library.service.BookService;
import ru.otus.zarechnev.library.service.GenreService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @Test
    void allBooksPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        verify(bookService).findAll();
    }

    @WithMockUser(username = "test", authorities = {"reader"})
    @Test
    void editBook() throws Exception {
        when(bookService.findById(1L))
                .thenReturn(new Book()
                        .setId(1L)
                        .setTitle("")
                        .setGenre(new Genre().setName(""))
                        .setAuthor(new Author().setName("")));

        mockMvc.perform(get("/edit?id=1"))
                .andExpect(status().isOk());
    }
}