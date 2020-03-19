package ru.otus.hw03.service.statistic;

import org.springframework.stereotype.Service;

@Service
public class DefaultStatisticCalculator implements StatisticCalculator {

    @Override
    public int getResultMark(Statistic statistic) {
        return statistic.getCorrectAnswersCount()*100/statistic.getQuestionsCount(); //Пусть округляет в меньшую сторону:)
    }
}