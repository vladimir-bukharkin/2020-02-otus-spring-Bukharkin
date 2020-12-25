package ru.otus.hw09.service;

import org.springframework.data.domain.Pageable;
import ru.otus.hw09.dto.Book;
import ru.otus.hw09.dto.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);

    Optional<Comment> findById(String id);

    List<Comment> findAll();

    List<Comment> findAll(Pageable pageable);

    List<Comment> findByBook(Book book);

    void delete(Comment comment);

    void deleteById(String id);
}
