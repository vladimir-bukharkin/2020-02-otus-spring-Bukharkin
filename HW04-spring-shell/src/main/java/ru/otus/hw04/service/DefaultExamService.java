package ru.otus.hw04.service;

import org.springframework.stereotype.Service;
import ru.otus.hw04.dao.QuestionDao;
import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.exception.QuestionsEmptyException;
import ru.otus.hw04.question.Question;
import ru.otus.hw04.service.interaction.ExamInteractionService;
import ru.otus.hw04.service.statistic.Statistic;

import java.util.List;

@Service
public class DefaultExamService implements ExamService {

    private final QuestionDao dao;
    private final ExamInteractionService interactionService;

    public DefaultExamService(QuestionDao dao, ExamInteractionService interactionService) {
        this.interactionService = interactionService;
        this.dao = dao;
    }

    @Override
    public void askQuestions() throws ModuleException {
        List<Question> questions = dao.getAll();
        if (questions == null || questions.isEmpty()) {
            throw new QuestionsEmptyException();
        }
        Statistic statistic = new Statistic(questions.size());
        questions.forEach(question -> {
            String answer = interactionService.ask(question);
            if (checkAnswer(question, answer)) {
                statistic.incrementCorrectAnswers();
            }
        });
        printResults(statistic);
    }

    @Override
    public void printResults(Statistic statistic) {
        interactionService.printResults(statistic);
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equals(answer.trim().toLowerCase());
    }
}
