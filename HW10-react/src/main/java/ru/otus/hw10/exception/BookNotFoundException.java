package ru.otus.hw10.exception;

public class BookNotFoundException extends DomainObjectNotFoundException {
    public BookNotFoundException(String id) {
        super("Book", id);
    }
}
