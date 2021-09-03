package ru.otus.zarechnev.library.service;

import org.springframework.stereotype.Service;
import ru.otus.zarechnev.library.domain.Comment;

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
    public Comment chooseComment(List<Comment> comments) {
        Map<String, Integer> collect = comments.stream()
                .collect(Collectors.toMap(Comment::getCommentText, comments::indexOf));

        int index = getChooseVariant("Choose comment to edit (by id)", collect);

        return comments.get(index);
    }

    private int getChooseVariant(String message, Map<String, Integer> variants) {
        try {
            variants.entrySet().stream()
                    .sorted((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()))
                    .forEach(e -> System.out.printf("%s (%s)%n", e.getKey(), e.getValue()));
            System.out.println(message);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
