package ru.otus.zarechnev.integration.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.zarechnev.integration.domain.Book;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReaderManImpl implements ReaderMan {

    @Override
    public Book onGettingBook(Book book) {
        log.warn("Reader get new book={} and start read", book);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.warn("Reader ask new book after book={}", book);

        return book;
    }
}
