package ru.otus.zarechnev.tester.checker;

import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.List;

public interface AnswerChecker {
    boolean isPassed(List<TestQuestionItem> questionItems, List<StudentAnswer> answers);
}
