package ru.otus.zarechnev.library.service;

import java.util.Map;

interface UserCommunicator {
    Long getChooseVariant(String message, Map<String, Long> variants);

    String getAnswer(String prompt);

    void notifyUser(String message);
}
