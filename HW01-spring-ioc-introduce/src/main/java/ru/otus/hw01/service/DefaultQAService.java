package ru.otus.hw01.service;

import ru.otus.hw01.dao.QuestionLDao;
import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.StatisticService;
import ru.otus.hw01.view.result.QuestionView;

import java.util.Iterator;

public class DefaultQAService implements QAService {

    private final QuestionView view;
    private final Iterator<Question> questions;
    private final StatisticService statisticService;

    public DefaultQAService(QuestionLDao dao, QuestionView view, StatisticService statisticService) {
        this.view = view;
        this.statisticService = statisticService;
        this.questions = dao.getAll().iterator();
    }

    @Override
    public void askNextQuestion() {
        Question question = questions.next();
        String answer = view.ask(question);
        if (checkAnswer(question, answer)) {
            statisticService.addCorrectAnswer();
        }
    }

    @Override
    public void printResults() {
        view.printResults(statisticService);
    }

    @Override
    public boolean hasNextQuestion() {
        return questions.hasNext();
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equals(answer);
    }

    @Override
    public StatisticService getStatisticService() {
        return statisticService;
    }
}
