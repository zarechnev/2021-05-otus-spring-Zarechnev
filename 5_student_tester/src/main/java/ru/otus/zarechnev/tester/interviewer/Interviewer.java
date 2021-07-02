package ru.otus.zarechnev.tester.interviewer;

import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

public interface Interviewer {
    StudentAnswer askAndGetAnswer(TestQuestionItem testQuestionItem);
}
