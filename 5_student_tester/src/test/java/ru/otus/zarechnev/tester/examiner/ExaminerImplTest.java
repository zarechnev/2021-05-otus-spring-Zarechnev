package ru.otus.zarechnev.tester.examiner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.zarechnev.tester.domain.QuestionAnswer;
import ru.otus.zarechnev.tester.domain.StudentAnswer;
import ru.otus.zarechnev.tester.domain.TestQuestionItem;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        classes = ExaminersConfig.class,
        properties = {
                "hardExaminerPassScore=100",
                "lightExaminerPassScore=50"
        }
)
class ExaminerImplTest {

    @Autowired
    private Examiner hardExaminer;
    @Autowired
    private Examiner lightExaminer;

    @Test
    void lightExaminerTest() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q1-1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(false));

        TestQuestionItem q2 = new TestQuestionItem().setQuestionId("q2");
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-1").setCorrect(false));
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        StudentAnswer ans1 = new StudentAnswer().setQuestionId("q1");
        ans1.getCheckedAnswers().add(0);
        StudentAnswer ans2 = new StudentAnswer().setQuestionId("q2");
        ans2.getCheckedAnswers().add(0);

        // when
        boolean passed = lightExaminer.isPassed(
                Arrays.asList(q1, q2),
                Arrays.asList(ans1, ans2)
        );

        // then
        assertTrue(passed);
    }

    @Test
    void onlyAllCorrect_negative() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q1-1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(false));

        TestQuestionItem q2 = new TestQuestionItem().setQuestionId("q2");
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-1").setCorrect(false));
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        StudentAnswer ans1 = new StudentAnswer().setQuestionId("q1");
        ans1.getCheckedAnswers().add(0);
        StudentAnswer ans2 = new StudentAnswer().setQuestionId("q2");
        ans2.getCheckedAnswers().add(0);

        // when
        boolean passed = hardExaminer.isPassed(
                Arrays.asList(q1, q2),
                Arrays.asList(ans1, ans2)
        );

        // then
        assertFalse(passed);
    }

    @Test
    void onlyAllCorrect() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q1-1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(false));

        TestQuestionItem q2 = new TestQuestionItem().setQuestionId("q2");
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-1").setCorrect(false));
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        StudentAnswer ans1 = new StudentAnswer().setQuestionId("q1");
        ans1.getCheckedAnswers().add(0);
        StudentAnswer ans2 = new StudentAnswer().setQuestionId("q2");
        ans2.getCheckedAnswers().add(1);

        // when
        boolean passed = hardExaminer.isPassed(
                Arrays.asList(q1, q2),
                Arrays.asList(ans1, ans2)
        );

        // then
        assertTrue(passed);
    }

    @Test
    void withoutAnswers() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q1-1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(false));

        TestQuestionItem q2 = new TestQuestionItem().setQuestionId("q2");
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-1").setCorrect(false));
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        // when
        boolean passed = hardExaminer.isPassed(
                Arrays.asList(q1, q2),
                Collections.emptyList()
        );

        // then
        assertFalse(passed);
    }

    @Test
    void multipleAnswers() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q1-1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        TestQuestionItem q2 = new TestQuestionItem().setQuestionId("q2");
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-1").setCorrect(false));
        q2.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans-q2-2").setCorrect(true));

        StudentAnswer ans1 = new StudentAnswer().setQuestionId("q1");
        ans1.getCheckedAnswers().add(0);
        ans1.getCheckedAnswers().add(1);
        StudentAnswer ans2 = new StudentAnswer().setQuestionId("q2");
        ans2.getCheckedAnswers().add(0);

        // when
        boolean passed = hardExaminer.isPassed(
                Arrays.asList(q1, q2),
                Arrays.asList(ans1, ans2)
        );

        // then
        assertFalse(passed);
    }

    @Test
    void ifAllCheckedInAnswers() {
        // given
        TestQuestionItem q1 = new TestQuestionItem().setQuestionId("q1");
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans1").setCorrect(true));
        q1.getQuestionAnswers().add(new QuestionAnswer().setAnswer("ans2").setCorrect(false));

        StudentAnswer ans1 = new StudentAnswer().setQuestionId("q1");
        ans1.getCheckedAnswers().add(1);
        ans1.getCheckedAnswers().add(1);

        // when
        boolean passed = hardExaminer.isPassed(
                Collections.singletonList(q1),
                Collections.singletonList(ans1)
        );

        // then
        assertFalse(passed);
    }
}