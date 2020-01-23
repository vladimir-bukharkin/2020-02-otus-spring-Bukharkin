package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;
import ru.otus.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao{

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void insert(Book book) {
        long genreId = book.getGenre().getId() != null ? book.getGenre().getId() : genreDao.getByName(book.getGenre().getName()).getId();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Name", book.getName())
                .addValue("GenreId", genreId);
        KeyHolder bookIdKH = new GeneratedKeyHolder();
        jdbc.update("INSERT INTO Books (Name, GenreId) VALUES (:Name, :GenreId)", params, bookIdKH, new String[]{"Id"});

        List<Long> authorIds = getAuthorIds(book);
        authorIds.forEach(authorId -> {
            SqlParameterSource p = new MapSqlParameterSource()
                    .addValue("BookId", bookIdKH.getKey())
                    .addValue("AuthorId", authorId);
            jdbc.update("INSERT INTO AuthorBookRelations (AuthorId, BookId) VALUES (:AuthorId, :BookId)", p);
        });
    }

    @Override
    public Book getById(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id);
        return jdbc.queryForObject("SELECT * FROM Books " +
                "LEFT JOIN Genres ON Genres.Id = Books.GenreId  " +
                "WHERE Books.Id=:Id", params, new BookMapper());
    }

    @Override
    public Book getByName(String name) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Name", name);
        return jdbc.queryForObject("SELECT * FROM Books " +
                "LEFT JOIN Genres ON Genres.Id = Books.GenreId  " +
                "WHERE Books.Name=:Name", params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(long authorId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("AuthorId", authorId);
        List<Long> bookIds = jdbc.queryForList("SELECT BookId FROM AuthorBookRelations WHERE AuthorId=:AuthorId", params, Long.class);
        return bookIds.stream().map(this::getById).collect(Collectors.toList());
    }

    @Override
    public List<Book> getByGenre(long genreId) {
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("GenreId", genreId);
        return jdbc.query("SELECT * FROM Books WHERE GenreId=:GenreId", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("SELECT * FROM Books LEFT JOIN Genres ON Genres.Id = Books.GenreId" , new BookMapper());
    }

    @Override
    public void update(long id, Book book) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id)
                .addValue("Name", book.getName())
                .addValue("GenreId", book.getGenre().getId());
        jdbc.update("UPDATE Books SET Name=:Name, GenreId=:GenreId WHERE Id=:Id", params);

        params = new MapSqlParameterSource()
                .addValue("BookId", id);
        Set<Long> bdAuthorIds = new HashSet<>(jdbc.queryForList("SELECT AuthorId FROM AuthorBookRelations WHERE BookId=:BookId", params, Long.class));
        Set<Long> authorIds = book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet());
        authorIds.forEach(authorId -> {
            if(!bdAuthorIds.contains(authorId)) {
                jdbc.update("UPDATE AuthorBookRelations SET BookId=:BookId, AuthorId=:AuthorId", new MapSqlParameterSource()
                        .addValue("BookId", id)
                        .addValue("AuthorId", authorId));
            }
        });
        bdAuthorIds.forEach(bdAuthorId -> {
            if (!authorIds.contains(bdAuthorId)) {
                jdbc.update("DELETE FROM AuthorBookRelations WHERE BookId=:BookId AND AuthorId=:AuthorId", new MapSqlParameterSource()
                        .addValue("BookId", id)
                        .addValue("AuthorId", bdAuthorId));
            }
        });
    }

    @Override
    public void remove(long id) {
        jdbc.update("DELETE FROM AuthorBookRelations WHERE Id=:Id", new MapSqlParameterSource()
                .addValue("Id", id));
    }

    private class BookMapper implements RowMapper<Book> {

        private Map<Long, Author> authors = new HashMap<>();

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("Id");
            List<Author> authors = getAuthors(bookId);
            return new Book(bookId,
                    rs.getString("name"),
                    new Genre(rs.getLong("Genres.Id"), rs.getString("Genres.Name")),
                    authors);
        }

        private List<Author> getAuthors(long bookId) {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("BookId", bookId);
            List<Long> authorIds = jdbc.queryForList("SELECT AuthorId FROM AuthorBookRelations WHERE BookId=:BookId", params, Long.class);
            return authorIds.stream()
                    .map(aId -> authors.computeIfAbsent(aId, aLong -> authorDao.getById(aId)))
                    .collect(Collectors.toList());
        }
    }

    private List<Long> getAuthorIds(Book book) {
        return book.getAuthors().stream()
                .map(author -> author.getId() != null ? author.getId() : authorDao.getByName(author.getFirstName(), author.getLastName()).getId())
                .collect(Collectors.toList());
    }
}
