package ru.otus.hw04.service;

import org.springframework.stereotype.Service;
import ru.otus.hw04.dao.QuestionDao;
import ru.otus.hw04.dao.StatisticDao;
import ru.otus.hw04.exception.ModuleException;
import ru.otus.hw04.exception.QuestionsEmptyException;
import ru.otus.hw04.question.Question;
import ru.otus.hw04.service.interaction.ExamInteractionService;
import ru.otus.hw04.service.login.User;
import ru.otus.hw04.service.statistic.Statistic;

import java.util.Collection;

@Service
public class DefaultExamService implements ExamService {

    private final QuestionDao questionDao;
    private final StatisticDao statisticDao;
    private final ExamInteractionService interactionService;

    public DefaultExamService(QuestionDao questionDao, StatisticDao statisticDao, ExamInteractionService interactionService) {
        this.interactionService = interactionService;
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
    }

    @Override
    public void startExam(User user) throws ModuleException {
        Collection<Question> questions = questionDao.getAll();
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
        statisticDao.save(user, statistic);
        printResults(statistic);
    }

    @Override
    public void printResults(Statistic statistic) {
        interactionService.printResults(statistic);
    }

    @Override
    public void printLastResult(String username) throws ModuleException {
        printResults(statisticDao.getLastForUser(username));
    }

    @Override
    public boolean checkAnswer(Question question, String answer) {
        return question.getAnswer().equals(answer.trim().toLowerCase());
    }
}
