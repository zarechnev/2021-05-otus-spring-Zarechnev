package ru.otus.zarechnev.tester.question.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
class QuestionsFileReader implements QuestionsProvider {

    private final MessageSource messageSource;

    @Override
    public List<String> getQuestionFileContent() {
        String questionFileName = messageSource.getMessage("questionFileName", null, Locale.getDefault());

        List<String> lines = new ArrayList<>();

        try (
                InputStream inputStream = new ClassPathResource(questionFileName).getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return lines;
    }
}
