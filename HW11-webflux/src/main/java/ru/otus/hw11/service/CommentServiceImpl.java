package ru.otus.hw11.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw11.dao.mapper.LibraryMapper;
import ru.otus.hw11.dao.repository.CommentRepository;
import ru.otus.hw11.dto.Book;
import ru.otus.hw11.dto.Comment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentServiceImpl implements CommentService{

    private final LibraryMapper libraryMapper;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(LibraryMapper libraryMapper, CommentRepository commentRepository) {
        this.libraryMapper = libraryMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        var result = commentRepository.save(libraryMapper.map(comment));
        return libraryMapper.map(result);
    }

    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id)
                .map(libraryMapper::map);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return commentRepository.findAllByBook(libraryMapper.map(book))
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(libraryMapper.map(comment));
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
