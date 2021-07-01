package ru.otus.zarechnev.tester.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.zarechnev.tester.tester.Tester;

import java.util.Set;

@ShellComponent
@RequiredArgsConstructor
public class TesterShellCommands {

    private final Tester tester;

    @ShellMethod(value = "Show help command", key = {"h"})
    public String showHelp() {
        return "Помощь по командам:\n" +
                "h, help -\t помощь по командам\n" +
                "l, list -\t список экзаменаторов\n" +
                "e <examinerName>, exam <examinerName> -\t начать экзамен <имя экзаменатора>";
    }

    @ShellMethod(value = "Show examiners list", key = {"l", "list"})
    public Set<String> showExaminersList() {
        return tester.getExaminersNames();
    }

    @ShellMethod(value = "Start exam command", key = {"e", "exam"})
    public void startExam(String examinerName) {
        tester.startTest(examinerName);
    }
}