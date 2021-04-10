package ru.otus.hw11.dao.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.hw11.dao.domain.Author;
import ru.otus.hw11.dao.domain.Book;
import ru.otus.hw11.dao.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAllByAuthorsContaining(Author author);

    Flux<Book> findAllByName(String name);

    Flux<Book> findAllByGenre(Genre genre);
}
