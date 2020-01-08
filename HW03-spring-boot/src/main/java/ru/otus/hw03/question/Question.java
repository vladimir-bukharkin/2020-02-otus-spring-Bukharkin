package ru.otus.hw03.question;

import ru.otus.hw03.exception.ModuleException;
import ru.otus.hw03.exception.UnsupportedTypeException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id == question1.id &&
                Objects.equals(question, question1.question) &&
                Objects.equals(answer, question1.answer) &&
                type == question1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, type);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + type +
                '}';
    }
}
