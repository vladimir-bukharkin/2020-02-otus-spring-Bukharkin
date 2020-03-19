package ru.otus.hw03.service;

import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.question.Question;
import ru.otus.hw03.service.statistic.Statistic;

public interface ExamService {

    void askQuestions() throws ModuleException;

    void printResults(Statistic statistic);

    boolean checkAnswer(Question question, String answer);
}
