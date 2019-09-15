package ru.otus.hw01.service.interaction;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.Statistic;

public interface ExamInteractionService {

    String ask(Question question);

    void printResults(Statistic statistic);
}
