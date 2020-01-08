package ru.otus.hw03.exception;

public class UnsupportedTypeException extends ModuleException {

    public UnsupportedTypeException(String type) {
        super("Type: " + type);
    }
}
