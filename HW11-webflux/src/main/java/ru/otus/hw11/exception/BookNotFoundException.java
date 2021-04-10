package ru.otus.hw11.exception;

public class BookNotFoundException extends DomainObjectNotFoundException {
    public BookNotFoundException(String id) {
        super("Book", id);
    }
}
