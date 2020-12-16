package ru.otus.hw07.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @EntityGraph(value = "Book")
    Book getByName(String name);

    @EntityGraph(value = "Book")
    List<Book> findAll();

    @EntityGraph(value = "Book")
    List<Book> getByAuthorId(long authorId);

    @EntityGraph(value = "Book")
    List<Book> getByGenreId(long genreId);
}
