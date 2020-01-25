package ru.otus.hw05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05.dao.AuthorDao;
import ru.otus.hw05.dao.BookDao;
import ru.otus.hw05.dao.GenreDao;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@ShellComponent
public class ShellCommands {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;

    public ShellCommands(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "Returns book by name", key = {"get_bbn", "get_book_by_name"})
    public String getBookByName(@ShellOption String name) {
        return Objects.toString(bookDao.getByName(name));
    }

    @ShellMethod(value = "Returns books by author", key = {"get_bba", "get_book_by_author"})
    public String getBooksByAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        Author author = authorDao.getByName(firstName, lastName);
        return author == null ? null : Objects.toString(bookDao.getByAuthor(author.getId()));
    }

    @ShellMethod(value = "Returns books by genre", key = {"get_bbg", "get_book_by_genre"})
    public String getBooksByGenre(@ShellOption String name) {
        Genre genre = genreDao.getByName(name);
        return genre == null ? null : Objects.toString(bookDao.getByGenre(genre.getId()));
    }

    @ShellMethod(value = "Returns all books", key = {"get_ab", "get_all_books"})
    public String getAllBooks() {
        return bookDao.getAll().toString();
    }

    @ShellMethod(value = "Returns all authors", key = {"get_aa", "get_all_authors"})
    public String getAllAuthors() {
        return authorDao.getAll().toString();
    }

    @ShellMethod(value = "Returns all genres", key = {"get_ag", "get_all_genres"})
    public String getAllGenres() {
        return genreDao.getAll().toString();
    }

    @ShellMethod(value = "Inserts book", key = {"insert_b", "insert_book"})
    public void insertBook(@ShellOption String name, @ShellOption long genreId, @ShellOption(arity = 2) Long[] authorIds) {
        bookDao.insert(name, genreId, Arrays.asList(authorIds));
    }

    @ShellMethod(value = "Inserts author", key = {"insert_a", "insert_author"})
    public void insertAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        authorDao.insert(new Author(firstName, lastName));
    }

    @ShellMethod(value = "Inserts genre", key = {"insert_g", "insert_genre"})
    public void insertGenre(@ShellOption String name) {
        genreDao.insert(new Genre(name));
    }

    @ShellMethod(value = "Remove book", key = {"remove_b", "remove_book"})
    public void removeBook(@ShellOption long id) {
        bookDao.remove(id);
    }

    @ShellMethod(value = "Remove author", key = {"remove_a", "remove_author"})
    public void removeAuthor(@ShellOption long id) {
        authorDao.remove(id);
    }

    @ShellMethod(value = "Remove genre", key = {"remove_g", "remove_genre"})
    public void removeGenre(@ShellOption long id) {
        genreDao.remove(id);
    }

    @ShellMethod(value = "Update book", key = {"update_b", "update_book"})
    public void updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long genreId, @ShellOption(arity = 2) Long[] authorIds) {
        bookDao.update(id, name, genreId, Arrays.asList(authorIds));
    }

    @ShellMethod(value = "Update author", key = {"update_a", "update_author"})
    public void updateAuthor(@ShellOption long id, @ShellOption String firstName, @ShellOption String lastName) {
        authorDao.update(id, new Author(id, firstName, lastName));
    }

    @ShellMethod(value = "Update genre", key = {"update_g", "update_genre"})
    public void updateGenre(@ShellOption long id, @ShellOption String name) {
        genreDao.update(id, new Genre(id, name));
    }
}
