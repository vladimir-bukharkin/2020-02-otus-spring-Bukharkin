package ru.otus.hw01.service.statistic;

import org.springframework.lang.NonNull;

public class DefaultStatisticService implements StatisticService {

    @NonNull
    private Statistic statistic;

    @Override
    public float getCorrectAnswersPercent() {
        return statistic.getCorrectAnswers()/statistic.getCount(); //todo check
    }

    @Override
    public int getCorrectAnswersCount() {
        return statistic.getCorrectAnswers();
    }

    @Override
    public int getQuestionsCount() {
        return statistic.getCount();
    }

    public void addCorrectAnswer() {
        statistic.incrementCorrectAnswers();
    }

    @Override
    public void init(int count) {
        statistic = new Statistic(count);
    }
}
