package ru.otus.hw03.question;

import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.exception.UnsupportedTypeException;

public class Question {

    private final long id;
    private final String question;
    private final String answer;
    private final QuestionType type;

    public Question(long id, String question, String answer, QuestionType type) throws ModuleException {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
    }

    public Question(long id, String question, String answer, String type) throws ModuleException {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = QuestionType.instanceOf(type);
        if (this.type == null) {
            throw new UnsupportedTypeException(type);
        }
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionType getType() {
        return type;
    }
}
