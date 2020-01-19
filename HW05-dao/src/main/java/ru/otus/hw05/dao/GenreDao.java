package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre author);

    Genre getById(long id);

    Genre getByName(String name);

    List<Genre> getAll();

    void update(long id, Genre author);

    void remove(long id);
}
