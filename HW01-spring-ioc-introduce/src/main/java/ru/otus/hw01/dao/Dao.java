package ru.otus.hw01.dao;

import ru.otus.hw01.exception.ModuleException;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws ModuleException;
}
