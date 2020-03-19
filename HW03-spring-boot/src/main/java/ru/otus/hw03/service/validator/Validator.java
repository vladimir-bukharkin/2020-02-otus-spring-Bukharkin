package ru.otus.hw03.service.validator;

import ru.otus.hw03.exception.ModuleException;

public interface Validator<T> {

    void validate(T obj) throws ModuleException;
}
