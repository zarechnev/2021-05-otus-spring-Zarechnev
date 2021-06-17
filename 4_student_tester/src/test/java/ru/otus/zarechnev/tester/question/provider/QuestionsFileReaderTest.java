package ru.otus.zarechnev.tester.question.provider;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsFileReaderTest {

    @Test
    void correctParsing() {
        // given
        QuestionsProvider questionsReader = new QuestionsFileReader("test_questions.csv");
        List<String> expected = Arrays.asList(
                "s1;What is the name of our planet?;Mars;false;Earth;true",
                "s2;What is the name of the satellite of the Earth?;Moon;true;Callisto;false");
        List<String> actual;

        // when
        actual = questionsReader.getQuestionFileContent();

        // then
        assertEquals(expected, actual);
    }
}