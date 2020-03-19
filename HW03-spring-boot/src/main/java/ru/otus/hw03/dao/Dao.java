package ru.otus.hw03.dao;

import ru.otus.hw03.exception.ModuleException;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws ModuleException;
}
