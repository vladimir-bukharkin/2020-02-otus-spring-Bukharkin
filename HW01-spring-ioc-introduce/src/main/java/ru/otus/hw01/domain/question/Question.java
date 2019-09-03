package ru.otus.hw01.domain.question;

import org.springframework.lang.NonNull;
import ru.otus.hw01.exception.DataException;
import ru.otus.hw01.exception.ModuleException;
import ru.otus.hw01.exception.NotSupportedTypeException;

import javax.validation.constraints.NotBlank;

public class Question {

    private final long id;
    @NonNull
    @NotBlank
    private final String question;
    private final String answer;
    @NonNull
    @NotBlank
    private final QuestionType type;

    public Question(long id, String question, String answer, QuestionType type) throws ModuleException {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
        checkFields();
    }

    public Question(long id, String question, String answer, String type) throws ModuleException {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = QuestionType.instanceOf(type);
        if (this.type == null) {
            throw new NotSupportedTypeException(type);
        }
        checkFields();
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

    private void checkFields() throws DataException {
        if (id < 1) {
            throw new DataException("Id", id);
        }
        if (question == null || question.isEmpty()) {
            throw new DataException("Question", id);
        }
        if (type == null) {
            throw new DataException("Type", type);
        }
    }
}
