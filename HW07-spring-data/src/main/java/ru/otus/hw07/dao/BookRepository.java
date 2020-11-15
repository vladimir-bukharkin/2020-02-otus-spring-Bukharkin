package ru.otus.hw07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw07.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

//    void insert(String bookName, long genreId, Collection<Long> authorIds);
    Book getByName(String name);

    List<Book> getByAuthorId(long authorId);

    List<Book> getByGenreId(long genreId);
}
