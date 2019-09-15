package ru.otus.hw01.exception;

public class QuestionParsingException extends ModuleException {

    public QuestionParsingException(Throwable cause) {
        super(cause);
    }

    public QuestionParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
