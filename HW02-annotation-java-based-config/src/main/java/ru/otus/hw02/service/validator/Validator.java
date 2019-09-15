package ru.otus.hw02.service.validator;

import ru.otus.hw02.exception.ModuleException;

public interface Validator<T> {

    void validate(T obj) throws ModuleException;
}
