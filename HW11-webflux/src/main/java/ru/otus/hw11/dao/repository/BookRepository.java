package ru.otus.hw11.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw11.dao.domain.Author;
import ru.otus.hw11.dao.domain.Book;
import ru.otus.hw11.dao.domain.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorsContaining(Author author);

    List<Book> findAllByGenre(Genre genre);
}
