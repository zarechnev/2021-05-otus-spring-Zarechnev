package ru.otus.zarechnev.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    @GetMapping("/")
    public Mono<String> allBooksPage(Model model) {
        return Mono.just("index");
    }
}
