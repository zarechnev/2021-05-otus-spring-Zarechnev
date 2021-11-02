package ru.otus.zarechnev.integration.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.zarechnev.integration.domain.Book;
import ru.otus.zarechnev.integration.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Test
    void getNextBook() {
        when(bookRepository.findAll()).thenReturn(List.of(
                new Book().setId(0L).setTitle("0"),
                new Book().setId(1L).setTitle("1"),
                new Book().setId(2L).setTitle("2"),
                new Book().setId(3L).setTitle("3"),
                new Book().setId(4L).setTitle("4"),
                new Book().setId(5L).setTitle("5")
        ));

        assertEquals(
                new Book().setId(0L).setTitle("0"),
                libraryService.getNextBook(new Book().setId(5L).setTitle("5"))
        );

        assertEquals(
                new Book().setId(5L).setTitle("5"),
                libraryService.getNextBook(new Book().setId(4L).setTitle("4"))
        );
    }
}