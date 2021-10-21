package ru.otus.zarechnev.batch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static ru.otus.zarechnev.batch.config.ImportAuthorBatchJobConfig.IMPORT_AUTHOR_JOB_NAME;
import static ru.otus.zarechnev.batch.config.ImportBookBatchJobConfig.IMPORT_BOOK_JOB_NAME;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final Job importBookJob;
    private final Job importAuthorJob;

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution importAuthorJobExecution = jobLauncher.run(importAuthorJob, new JobParameters());
        JobExecution importBookJobExecution = jobLauncher.run(importBookJob, new JobParameters());
        System.out.println(importAuthorJobExecution);
        System.out.println(importBookJobExecution);
    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long importAuthorExecutionId = jobOperator.start(IMPORT_AUTHOR_JOB_NAME, "");
        Long importBookExecutionId = jobOperator.start(IMPORT_BOOK_JOB_NAME, "");
        String importAuthorSummary = jobOperator.getSummary(importAuthorExecutionId);
        String importBookSummary = jobOperator.getSummary(importBookExecutionId);
        System.out.printf("%s, %s%n", importBookSummary, importAuthorSummary);
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_AUTHOR_JOB_NAME));
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_BOOK_JOB_NAME));
    }
}
