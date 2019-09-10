package ru.otus.hw01.service.validator;

import ru.otus.hw01.exception.ModuleException;

public interface Validator<T> {

    void validate(T obj) throws ModuleException;
}
