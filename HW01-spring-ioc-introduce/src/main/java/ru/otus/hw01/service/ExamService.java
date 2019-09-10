package ru.otus.hw01.service;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.exception.ModuleException;
import ru.otus.hw01.service.statistic.Statistic;

public interface ExamService {

    void askQuestions() throws ModuleException;

    void printResults(Statistic statistic);

    boolean checkAnswer(Question question, String answer);
}
