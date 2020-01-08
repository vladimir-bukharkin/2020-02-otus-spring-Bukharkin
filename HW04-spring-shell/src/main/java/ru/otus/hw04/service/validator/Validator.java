package ru.otus.hw04.service.validator;

import ru.otus.hw04.exception.ModuleException;

public interface Validator<T> {

    void validate(T obj) throws ModuleException;
}
