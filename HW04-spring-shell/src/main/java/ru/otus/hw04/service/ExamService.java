package ru.otus.hw04.service;

import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.question.Question;
import ru.otus.hw04.service.statistic.Statistic;

public interface ExamService {

    void askQuestions() throws ModuleException;

    void printResults(Statistic statistic);

    boolean checkAnswer(Question question, String answer);
}
