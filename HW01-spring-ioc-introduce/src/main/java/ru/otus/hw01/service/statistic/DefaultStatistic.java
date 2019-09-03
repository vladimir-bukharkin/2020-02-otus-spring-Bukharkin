package ru.otus.hw01.service.statistic;

import ru.otus.hw01.exception.DataException;

public class DefaultStatistic implements Statistic {

    private final int count;
    private int correctAnswers;

    DefaultStatistic(int count) throws DataException {
        if (count < 1) {
            throw new DataException("Questions count", count);
        }
        this.count = count;
    }

    @Override
    public void incrementCorrectAnswers() {
        correctAnswers++;
    }

    @Override
    public int getQuestionsCount() {
        return count;
    }

    @Override
    public int getCorrectAnswersCount() {
        return correctAnswers;
    }

    @Override
    public int getResultMark() {
        return correctAnswers*100/count; //Пусть округляет в меньшую сторону:)
    }
}
