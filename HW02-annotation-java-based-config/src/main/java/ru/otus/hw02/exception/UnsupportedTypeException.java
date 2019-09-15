package ru.otus.hw02.exception;

public class UnsupportedTypeException extends ModuleException {

    public UnsupportedTypeException(String type) {
        super("Type: " + type);
    }
}
