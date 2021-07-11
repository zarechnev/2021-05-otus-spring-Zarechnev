package ru.otus.zarechnev.library.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Service
class UserCommunicatorViaConsole implements UserCommunicator {

    @Override
    public Long getChooseVariant(String message, Map<String, Long> variants) {
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
}
