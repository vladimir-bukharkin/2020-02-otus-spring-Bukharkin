package ru.otus.hw04.dao;

import ru.otus.hw04.exception.ModuleException;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws ModuleException;
}
