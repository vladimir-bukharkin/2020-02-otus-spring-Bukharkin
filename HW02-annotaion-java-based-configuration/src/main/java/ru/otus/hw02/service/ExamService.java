package ru.otus.hw02.service;

import ru.otus.hw02.domain.question.Question;
import ru.otus.hw02.exception.ModuleException;
import ru.otus.hw02.service.statistic.Statistic;

public interface ExamService {

    Statistic askQuestions() throws ModuleException;

    void printResults(Statistic statistic);

    boolean checkAnswer(Question question, String answer);
}
