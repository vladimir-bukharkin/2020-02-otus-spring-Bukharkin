package ru.otus.hw01.service.statistic;

public interface StatisticService {

    float getCorrectAnswersPercent();

    int getCorrectAnswersCount();

    int getQuestionsCount();

    void addCorrectAnswer();

    void init(int count);
}
