package ru.otus.hw06.dao;

import ru.otus.hw06.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);

    Genre getByName(String name);

    List<Genre> getAll();

    void update(long id, Genre genre);

    void remove(long id);
}
