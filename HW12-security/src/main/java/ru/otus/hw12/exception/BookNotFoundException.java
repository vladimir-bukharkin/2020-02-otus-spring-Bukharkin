package ru.otus.hw12.exception;

public class BookNotFoundException extends DomainObjectNotFoundException {
    public BookNotFoundException(String id) {
        super("Book", id);
    }
}
