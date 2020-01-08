package ru.otus.hw04.exception;

public class UnsupportedTypeException extends ModuleException {

    public UnsupportedTypeException(String type) {
        super("Type: " + type);
    }
}
