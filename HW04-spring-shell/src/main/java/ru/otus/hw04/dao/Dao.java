package ru.otus.hw04.dao;

import ru.otus.hw04.exception.ModuleException;

import java.util.Collection;

public interface Dao<T> {

    Collection<T> getAll() throws ModuleException;
}
