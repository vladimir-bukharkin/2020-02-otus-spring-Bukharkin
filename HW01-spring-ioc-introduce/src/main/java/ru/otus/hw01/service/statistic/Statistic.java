package ru.otus.hw01.service.statistic;

public interface Statistic {

    void incrementCorrectAnswers();

    int getQuestionsCount();

    int getCorrectAnswersCount();

    int getResultMark();
}
