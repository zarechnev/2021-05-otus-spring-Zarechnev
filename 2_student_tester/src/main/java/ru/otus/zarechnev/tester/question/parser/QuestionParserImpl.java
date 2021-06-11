package ru.otus.zarechnev.tester.question.parser;

import org.springframework.stereotype.Component;
import ru.otus.zarechnev.tester.domain.QuestionAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.*;

@Component
class QuestionParserImpl implements QuestionParser {

    public static final String DELIMITER = ";";

    @Override
    public List<TestQuestionItem> getTestQuestionItems(List<String> questionsStrings) {
        List<TestQuestionItem> testQuestionItems = new ArrayList<>();

        questionsStrings.forEach(line -> {
            Queue<String> queue = new LinkedList<>(Arrays.asList(line.split(DELIMITER)));

            TestQuestionItem testQuestionItem = new TestQuestionItem()
                    .setQuestionId(queue.poll())
                    .setQuestion(queue.poll());

            while (!queue.isEmpty()) {
                QuestionAnswer questionAnswer = new QuestionAnswer()
                        .setAnswer(queue.poll())
                        .setCorrect(Boolean.parseBoolean(queue.poll()));
                testQuestionItem.getQuestionAnswers().add(questionAnswer);
            }

            testQuestionItems.add(testQuestionItem);
        });

        return testQuestionItems;
    }
}
