package ru.otus.zarechnev.tester.tester;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.otus.zarechnev.tester.checker.AnswerChecker;
import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;
import ru.otus.zarechnev.tester.question.parser.QuestionParser;
import ru.otus.zarechnev.tester.question.provider.QuestionsProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WithFileConsoleTester implements Tester {

    private static final String ANSWER_SPLITTER = ",";

    private final QuestionsProvider questionsFileReader;
    private final QuestionParser questionParser;
    private final AnswerChecker soberProfessorIvanov;

    @Override
    public void startTest() {
        List<String> questionFileContent = questionsFileReader.getQuestionFileContent();
        List<TestQuestionItem> testQuestionItems = questionParser.getTestQuestionItems(questionFileContent);

        if (CollectionUtils.isEmpty(testQuestionItems)) {
            System.out.println("There is no questions!");
            return;
        }

        List<StudentAnswer> answers = new ArrayList<>(testQuestionItems.size());

        for (TestQuestionItem testQuestionItem : testQuestionItems) {
            askQuestion(testQuestionItem);
            StudentAnswer studentAnswer = parseAnswer();
            studentAnswer.setQuestionId(testQuestionItem.getQuestionId());
            answers.add(studentAnswer);
        }

        boolean passed = soberProfessorIvanov.isPassed(testQuestionItems, answers);

        if (passed) {
            System.out.println("Congrats! Exam is passed =)");
        } else {
            System.out.println("Sorry, exam isn't passed =(");
        }
    }

    private void askQuestion(TestQuestionItem question) {
        System.out.println(question.getQuestion());

        for (int i = 0; i < question.getQuestionAnswers().size(); i++) {
            System.out.printf("%d) %s\n", i + 1, question.getQuestionAnswers().get(i).getAnswer());
        }
    }

    private StudentAnswer parseAnswer() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            String[] split = line.split(ANSWER_SPLITTER);

            StudentAnswer studentAnswer = new StudentAnswer();

            for (String ans: split) {
                studentAnswer.getCheckedAnswers().add(Integer.parseInt(ans) - 1);
            }

            return studentAnswer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}