package ru.otus.zarechnev.tester.examiner;

import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.List;

public interface Examiner {
    boolean isPassed(List<TestQuestionItem> questionItems, List<StudentAnswer> answers);
}
