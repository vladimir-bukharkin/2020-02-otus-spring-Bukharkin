package ru.otus.hw02.service.interaction;

import ru.otus.hw02.domain.question.Question;
import ru.otus.hw02.service.statistic.Statistic;

public interface ExamInteractionService {

    String ask(Question question);

    void printResults(Statistic statistic);
}
