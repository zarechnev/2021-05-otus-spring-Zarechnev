package ru.otus.zarechnev.library.service;

import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Author;
import ru.otus.zarechnev.library.domain.Genre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class UserCommunicatorViaConsole implements UserCommunicator {

    @Override
    public String getAnswer(String prompt) {
        try {
            System.out.println(prompt);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyUser(String message) {
        System.out.println(message);
    }

    @Override
    public Author chooseAuthor(List<Author> authors) {
        Map<String, Long> collect = authors.stream()
                .collect(Collectors.toMap(Author::getName, Author::getId));

        Long authorId = getChooseVariant("Choose author (by id)", collect);

        return authors.stream()
                .filter(author -> authorId.equals(author.getId()))
                .findFirst().orElseThrow();
    }

    @Override
    public Genre chooseGenre(List<Genre> genres) {
        Map<String, Long> collect = genres.stream()
                .collect(Collectors.toMap(Genre::getName, Genre::getId));

        Long genreId = getChooseVariant("Choose genre (by id)", collect);

        return genres.stream()
                .filter(genre -> genreId.equals(genre.getId()))
                .findFirst().orElseThrow();
    }

    private Long getChooseVariant(String message, Map<String, Long> variants) {
        try {
            variants.entrySet().stream()
                    .sorted((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()))
                    .forEach(e -> System.out.printf("%s (%s)%n", e.getKey(), e.getValue()));
            System.out.println(message);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Long.valueOf(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
