package ru.otus.hw11.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw11.dao.mapper.LibraryMapper;
import ru.otus.hw11.dao.repository.BookRepository;
import ru.otus.hw11.dto.Author;
import ru.otus.hw11.dto.Book;
import ru.otus.hw11.dto.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class BookServiceImpl implements BookService{

    private final LibraryMapper libraryMapper;
    private final BookRepository bookRepository;

    public BookServiceImpl(LibraryMapper libraryMapper, BookRepository bookRepository) {
        this.libraryMapper = libraryMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        var result = bookRepository.save(libraryMapper.map(book));
        return libraryMapper.map(result);
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id)
                .map(libraryMapper::map);
    }

    @Override
    public Optional<Book> findByAuthorFirstNameAndAuthorLastName(String id) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAuthorsContaining(Author author) {
        return bookRepository.findAllByAuthorsContaining(libraryMapper.map(author))
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(libraryMapper.map(genre))
                .stream()
                .map(libraryMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(libraryMapper.map(book));
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
