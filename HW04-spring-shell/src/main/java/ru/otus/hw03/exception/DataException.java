package ru.otus.hw03.exception;

public class DataException extends ModuleException {

    public DataException(String message) {
        super(message);
    }

    public <T> DataException(String name, T value) {
        super("Data error. Field name=" + name + ", value=" + value);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
