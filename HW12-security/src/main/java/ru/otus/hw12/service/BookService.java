package ru.otus.hw12.service;

import org.springframework.data.domain.Pageable;
import ru.otus.hw12.dto.Author;
import ru.otus.hw12.dto.Book;
import ru.otus.hw12.dto.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    Optional<Book> findById(String id);

    Optional<Book> findByAuthorFirstNameAndAuthorLastName(String id);

    List<Book> findAll();

    List<Book> findAll(Pageable pageable);

    List<Book> findAllByAuthorsContaining(Author author);

    List<Book> findAllByGenre(Genre genre);

    void delete(Book book);

    void deleteById(String id);
}
