package ru.otus.hw13.exception;

public class BookNotFoundException extends DomainObjectNotFoundException {
    public BookNotFoundException(String id) {
        super("Book", id);
    }
}
