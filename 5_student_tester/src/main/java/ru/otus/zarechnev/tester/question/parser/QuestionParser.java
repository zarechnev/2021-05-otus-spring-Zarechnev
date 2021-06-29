package ru.otus.zarechnev.tester.question.parser;

import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.List;

public interface QuestionParser {
    List<TestQuestionItem> getTestQuestionItems(List<String> questionsStrings);
}
