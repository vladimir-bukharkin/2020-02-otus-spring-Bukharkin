package ru.otus.hw01.exception;

public class NotSupportedTypeException extends ModuleException {

    public NotSupportedTypeException(String type) {
        super("Type: " + type);
    }
}
