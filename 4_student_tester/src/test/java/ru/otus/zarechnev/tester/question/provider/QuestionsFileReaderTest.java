package ru.otus.zarechnev.tester.question.provider;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class QuestionsFileReaderTest {

    @Test
    void correctParsing() {
        // given
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        when(messageSource.getMessage(any(), any(), any()))
                .thenReturn("test_questions.csv");

        QuestionsProvider questionsReader = new QuestionsFileReader(messageSource);
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