package ru.otus.hw10.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw10.dao.domain.Book;
import ru.otus.hw10.dao.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook(Book book);

    List<Comment> findAllByBookId(String bookId);
}
