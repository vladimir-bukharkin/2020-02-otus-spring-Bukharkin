package ru.otus.hw01.view.result.console;

import ru.otus.hw01.domain.question.Question;
import ru.otus.hw01.service.statistic.StatisticService;
import ru.otus.hw01.view.result.QuestionView;

import java.util.Scanner;

public class QuestionConsoleView implements QuestionView {

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
            default:
                throw new InternalError("Not supported question type: " + question.getType());
        }
        try(Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    @Override
    public void printResults(StatisticService statisticService) {
        System.out.println("Результаты тестирования: " +
                "\nВсего вопросов: " + statisticService.getQuestionsCount() +
                "\nПравильных ответов: " + statisticService.getCorrectAnswersCount() +
                "\nИтоговый балл: " + (int) statisticService.getCorrectAnswersPercent()); //Пусть округляет в меньшую сторону:)
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
