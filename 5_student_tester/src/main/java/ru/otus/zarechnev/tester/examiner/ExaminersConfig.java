package ru.otus.zarechnev.tester.examiner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ExaminersConfig {

    @Value("${hardExaminerPassScore}")
    private int hardExaminerPassScore;

    @Value("${lightExaminerPassScore}")
    private int lightExaminerPassScore;

    @Bean
    public Examiner hardExaminer() {
        return new ExaminerImpl(hardExaminerPassScore);
    }

    @Bean
    public Examiner lightExaminer() {
        return new ExaminerImpl(lightExaminerPassScore);
    }
}
