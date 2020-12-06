package ru.otus.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07.dao.AuthorRepository;
import ru.otus.hw07.dao.BookRepository;
import ru.otus.hw07.dao.GenreRepository;
import ru.otus.hw07.domain.Author;
import ru.otus.hw07.domain.Book;
import ru.otus.hw07.domain.Genre;

import java.util.Objects;


@ShellComponent
public class ShellCommands {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public ShellCommands(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @ShellMethod(value = "Returns book by name", key = {"get_bbn", "get_book_by_name"})
    public String getBookByName(@ShellOption String name) {
        return Objects.toString(bookRepository.getByName(name));
    }

    @ShellMethod(value = "Returns books by author", key = {"get_bba", "get_book_by_author"})
    public String getBooksByAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        Author author = authorRepository.getByFirstNameAndLastName(firstName, lastName);
        return author == null ? null : Objects.toString(bookRepository.getByAuthorId(author.getId()));
    }

    @ShellMethod(value = "Returns books by genre", key = {"get_bbg", "get_book_by_genre"})
    public String getBooksByGenre(@ShellOption String name) {
        Genre genre = genreRepository.getByName(name);
        return genre == null ? null : Objects.toString(bookRepository.getByGenreId(genre.getId()));
    }

    @ShellMethod(value = "Returns all books", key = {"get_ab", "get_all_books"})
    public String getAllBooks() {
        return bookRepository.findAll().toString();
    }

    @ShellMethod(value = "Returns all authors", key = {"get_aa", "get_all_authors"})
    public String getAllAuthors() {
        return authorRepository.findAll().toString();
    }

    @ShellMethod(value = "Returns all genres", key = {"get_ag", "get_all_genres"})
    public String getAllGenres() {
        return genreRepository.findAll().toString();
    }

    @ShellMethod(value = "Inserts book", key = {"insert_b", "insert_book"})
    public void insertBook(@ShellOption String name, @ShellOption long genreId, @ShellOption(arity = 2) Long[] authorIds) {
        bookRepository.save(new Book(name, null, null));
    }

    @ShellMethod(value = "Inserts author", key = {"insert_a", "insert_author"})
    public void insertAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        authorRepository.save(new Author(firstName, lastName));
    }

    @ShellMethod(value = "Inserts genre", key = {"insert_g", "insert_genre"})
    public void insertGenre(@ShellOption String name) {
        genreRepository.save(new Genre(name));
    }

    @ShellMethod(value = "Remove book", key = {"remove_b", "remove_book"})
    public void removeBook(@ShellOption int id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod(value = "Remove author", key = {"remove_a", "remove_author"})
    public void removeAuthor(@ShellOption int id) {
        authorRepository.deleteById(id);
    }

    @ShellMethod(value = "Remove genre", key = {"remove_g", "remove_genre"})
    public void removeGenre(@ShellOption int id) {
        genreRepository.deleteById(id);
    }

    @ShellMethod(value = "Update book", key = {"update_b", "update_book"})
    public void updateBook(@ShellOption int id, @ShellOption String name, @ShellOption long genreId, @ShellOption(arity = 2) Long[] authorIds) {
        Book book = new Book(id, name, null, null);
        bookRepository.save(book);
    }

    @ShellMethod(value = "Update author", key = {"update_a", "update_author"})
    public void updateAuthor(@ShellOption int id, @ShellOption String firstName, @ShellOption String lastName) {
        authorRepository.save(new Author(id, firstName, lastName));
    }

    @ShellMethod(value = "Update genre", key = {"update_g", "update_genre"})
    public void updateGenre(@ShellOption int id, @ShellOption String name) {
        genreRepository.save(new Genre(id, name));
    }
}
