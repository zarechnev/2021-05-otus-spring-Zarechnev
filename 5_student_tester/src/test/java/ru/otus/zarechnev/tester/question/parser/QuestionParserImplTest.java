package ru.otus.zarechnev.tester.question.parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.zarechnev.tester.domain.QuestionAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = QuestionParserImpl.class)
class QuestionParserImplTest {

    @Autowired
    private QuestionParser questionParser;

    @Test
    void correctParsing() {
        // given
        List<String> strings = Arrays.asList(
                "s1;What is the name of our planet?;Mars;false;Earth;true",
                "s2;What is the name of the satellite of the Earth?;Moon;true;Callisto;false"
        );

        List<TestQuestionItem> expected = Arrays.asList(
                new TestQuestionItem()
                        .setQuestionId("s1")
                        .setQuestion("What is the name of our planet?")
                        .setQuestionAnswers(Arrays.asList(
                                new QuestionAnswer().setAnswer("Mars").setCorrect(false),
                                new QuestionAnswer().setAnswer("Earth").setCorrect(true)
                        )),
                new TestQuestionItem()
                        .setQuestionId("s2")
                        .setQuestion("What is the name of the satellite of the Earth?")
                        .setQuestionAnswers(Arrays.asList(
                                new QuestionAnswer().setAnswer("Moon").setCorrect(true),
                                new QuestionAnswer().setAnswer("Callisto").setCorrect(false)
                        ))
        );

        // when
        List<TestQuestionItem> actual = questionParser.getTestQuestionItems(strings);

        // then
        assertEquals(expected, actual);
    }
}