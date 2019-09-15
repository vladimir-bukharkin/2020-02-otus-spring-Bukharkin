package ru.otus.hw02.dao;

import ru.otus.hw02.exception.ModuleException;

import java.util.List;

public interface Dao<T> {

    List<T> getAll() throws ModuleException;
}
