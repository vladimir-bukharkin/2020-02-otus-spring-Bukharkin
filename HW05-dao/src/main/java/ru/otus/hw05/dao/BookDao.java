package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getByAuthor(Author author);

    List<Book> getAll();

    void update(long id, Book author);

    void remove(long id);
}
