package ru.otus.hw05.dao;

import ru.otus.hw05.domain.Book;

import java.util.Collection;
import java.util.List;

public interface BookDao {

    void insert(Book book);

    void insert(String bookName, long genreId, Collection<Long> authorIds);

    Book getById(long id);

    Book getByName(String name);

    List<Book> getByAuthor(long authorId);

    List<Book> getByGenre(long genreId);

    List<Book> getAll();

    void update(long id, Book book);

    void update(long id, String bookName, long genreId, Collection<Long> authorIds);

    void remove(long id);
}
