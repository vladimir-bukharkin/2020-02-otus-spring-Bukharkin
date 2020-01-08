package ru.otus.hw03.service.statistic;

import ru.otus.hw03.exception.DataException;

public class Statistic {

    private final int count;
    private int correctAnswers;

    public Statistic(int count) throws DataException {
        if (count < 1) {
            throw new DataException("Questions count", count);
        }
        this.count = count;
    }

    public void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public int getQuestionsCount() {
        return count;
    }

    public int getCorrectAnswersCount() {
        return correctAnswers;
    }
}
