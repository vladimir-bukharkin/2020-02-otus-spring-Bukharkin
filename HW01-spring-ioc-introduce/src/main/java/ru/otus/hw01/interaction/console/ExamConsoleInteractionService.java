package ru.otus.hw01.interaction.console;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.interaction.ExamInteractionService;
import ru.otus.hw01.service.statistic.Statistic;
import ru.otus.hw01.service.statistic.StatisticCalculator;

import java.util.Scanner;

public class ExamConsoleInteractionService implements ExamInteractionService {

    private final Scanner scanner = new Scanner(System.in);
    private final StatisticCalculator statisticCalculator;

    public ExamConsoleInteractionService(StatisticCalculator statisticCalculator) {
        this.statisticCalculator = statisticCalculator;
    }

    @Override
    public String ask(Question question) {
        switch (question.getType()) {
            case SINGLE:
                askSingleQuestion(question);
                break;
            case MULTIPLY:
                askMultiplyQuestion(question);
                break;
            case TEXT:
                askTextQuestion(question);
                break;
            default:
                throw new InternalError("Not supported question type: " + question.getType());
        }
        return scanner.nextLine();
    }

    @Override
    public void printResults(Statistic statistic) {
        System.out.println("Результаты тестирования: " +
                "\nВсего вопросов: " + statistic.getQuestionsCount() +
                "\nПравильных ответов: " + statistic.getCorrectAnswersCount() +
                "\nИтоговый балл: " + statisticCalculator.getResultMark(statistic));
    }

    private void askSingleQuestion(Question question) {
        System.out.println(question.getQuestion() +
                "\nВопрос с единственным вариантом ответа" +
                "\nВведите номер правильного варианта ответа...");
    }

    private void askMultiplyQuestion(Question question) {
        System.out.println(question.getQuestion() +
                "\nВопрос с несколькими вариантами ответа" +
                "\nВведите номера правильных вариантов ответа через \",\" (Пример: 1,3,5)...");
    }

    private void askTextQuestion(Question question) {
        System.out.println(question.getQuestion() +
                "\nВопрос c ответом в ввиде текста" +
                "\nВведите ответ словами...");
    }
}
