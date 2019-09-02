package ru.otus.hw01.service;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.Statistic;

public interface QAService {

    Statistic askQuestions();

    void printResults(Statistic statistic);

    boolean checkAnswer(Question question, String answer);
}
