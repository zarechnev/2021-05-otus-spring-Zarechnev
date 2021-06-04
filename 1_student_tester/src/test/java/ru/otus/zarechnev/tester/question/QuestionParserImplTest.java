package ru.otus.zarechnev.tester.question;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionParserImplTest {

    @Test
    void correctParsing() {
        // given
        QuestionParser questionParser = new QuestionParserImpl("test_questions.csv");
        List<TestQuestionItem> expected = Arrays.asList(
                new TestQuestionItem()
                        .setQuestion("What is the name of our planet?")
                        .setAnswers(Arrays.asList(
                                new Answer().setAnswer("Mars").setCorrect(false),
                                new Answer().setAnswer("Earth").setCorrect(true)
                        )),
                new TestQuestionItem()
                        .setQuestion("What is the name of the satellite of the Earth?")
                        .setAnswers(Arrays.asList(
                                new Answer().setAnswer("Moon").setCorrect(true),
                                new Answer().setAnswer("Callisto").setCorrect(false)
                        ))
        );
        List<TestQuestionItem> actual;

        // when
        actual = questionParser.getTestQuestionItems();

        // then
        assertEquals(expected, actual);
    }
}