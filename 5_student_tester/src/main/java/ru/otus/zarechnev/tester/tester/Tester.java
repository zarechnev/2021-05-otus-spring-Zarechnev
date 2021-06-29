package ru.otus.zarechnev.tester.tester;

import java.util.Set;

public interface Tester {
    Set<String> getExaminersNames();
    void startTest(String examinerName);
}
