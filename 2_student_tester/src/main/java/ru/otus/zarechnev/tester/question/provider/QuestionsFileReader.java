package ru.otus.zarechnev.tester.question.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
class QuestionsFileReader implements QuestionsProvider {

    private final String questionFileName;

    @Autowired
    public QuestionsFileReader(@Value("${questionFileName}") String questionFileName) {
        this.questionFileName = questionFileName;
    }

    @Override
    public List<String> getQuestionFileContent() {
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
