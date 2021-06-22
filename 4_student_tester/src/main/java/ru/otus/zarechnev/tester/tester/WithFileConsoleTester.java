package ru.otus.zarechnev.tester.tester;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.zarechnev.tester.checker.AnswerChecker;
import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;
import ru.otus.zarechnev.tester.interviewer.Interviewer;
import ru.otus.zarechnev.tester.question.parser.QuestionParser;
import ru.otus.zarechnev.tester.question.provider.QuestionsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class WithFileConsoleTester implements Tester {

    private final QuestionsProvider questionsFileReader;
    private final QuestionParser questionParser;
    private final AnswerChecker soberProfessorIvanov;
    private final MessageSource messageSource;
    private final Interviewer consoleInterviewer;

    @Override
    public void startTest() {
        List<String> questionFileContent = questionsFileReader.getQuestionFileContent();
        List<TestQuestionItem> testQuestionItems = questionParser.getTestQuestionItems(questionFileContent);

        List<StudentAnswer> answers = new ArrayList<>(testQuestionItems.size());

        for (TestQuestionItem testQuestionItem : testQuestionItems) {
            StudentAnswer studentAnswer = consoleInterviewer.askAndGetAnswer(testQuestionItem);
            answers.add(studentAnswer);
        }

        boolean passed = soberProfessorIvanov.isPassed(testQuestionItems, answers);

        if (passed) {
            System.out.println(messageSource
                    .getMessage("exam.result.success.msg", null, Locale.getDefault()));
        } else {
            System.out.println(messageSource
                    .getMessage("exam.result.disSuccess.msg", null, Locale.getDefault()));
        }
    }
}