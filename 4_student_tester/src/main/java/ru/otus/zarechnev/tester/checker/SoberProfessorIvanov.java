package ru.otus.zarechnev.tester.checker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.zarechnev.tester.domain.QuestionAnswer;
import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class SoberProfessorIvanov implements AnswerChecker {

    private final int passingScorePercent;

    public SoberProfessorIvanov(@Value("${soberProfessorIvanovPassingScoreInPercent}") int passingScorePercent) {
        this.passingScorePercent = passingScorePercent;
    }

    @Override
    public boolean isPassed(List<TestQuestionItem> questionItems, List<StudentAnswer> answers) {
        int correctAnsCount = 0;

        for (TestQuestionItem testQuestionItem : questionItems) {
            Optional<StudentAnswer> first = answers.stream()
                    .filter(a -> testQuestionItem.getQuestionId().equals(a.getQuestionId()))
                    .findFirst();

            if (first.isEmpty()) {
                continue;
            }

            if (isAnswerCorrect(testQuestionItem.getQuestionAnswers(), first.get())) {
                correctAnsCount++;
            }
        }
        return isTestPassed(correctAnsCount, questionItems.size());
    }

    private boolean isAnswerCorrect(List<QuestionAnswer> questionAnswers, StudentAnswer studentAnswer) {
        List<Integer> correctAnswers = questionAnswers.stream()
                .filter(QuestionAnswer::isCorrect)
                .map(questionAnswers::indexOf)
                .collect(Collectors.toList());

        return correctAnswers.containsAll(studentAnswer.getCheckedAnswers())
                && studentAnswer.getCheckedAnswers().containsAll(correctAnswers);
    }

    private boolean isTestPassed(int correctAnsCount, int questionsCount) {
        return correctAnsCount * 100 / questionsCount >= passingScorePercent;
    }
}
