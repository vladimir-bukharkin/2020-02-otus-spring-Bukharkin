package ru.otus.hw04.service;

import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.question.Question;
import ru.otus.hw04.service.login.User;
import ru.otus.hw04.service.statistic.Statistic;

public interface ExamService {

    void startExam(User user) throws ModuleException;

    void printResults(Statistic statistic);

    void printLastResult(String username) throws ModuleException;

    boolean checkAnswer(Question question, String answer);
}
