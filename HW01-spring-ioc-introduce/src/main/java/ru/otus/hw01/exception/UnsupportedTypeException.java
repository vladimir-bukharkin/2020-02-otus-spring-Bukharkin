package ru.otus.hw01.exception;

public class UnsupportedTypeException extends ModuleException {

    public UnsupportedTypeException(String type) {
        super("Type: " + type);
    }
}
