package ru.otus.hw06.dao;

import ru.otus.hw06.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);

    Author getByName(String firstName, String lastName);

    List<Author> getAll();

    void update(long id, Author author);

    void remove(long id);
}
