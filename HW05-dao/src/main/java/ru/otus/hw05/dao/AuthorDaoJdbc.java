package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("first_name", author.getFirstName())
                .addValue("last_name", author.getLastName());
        jdbc.update("INSERT INTO authors (first_name, last_name) values(:first_name, :last_name)", params);
    }

    @Override
    public Author getById(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.queryForObject("SELECT * FROM authors WHERE id=:id", params, new AuthorMapper());
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("first_name", firstName)
                .addValue("last_name", lastName);
        return jdbc.queryForObject("SELECT * FROM authors WHERE first_name=:first_name AND last_name=:last_name",
                params,
                new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT * FROM authors", new AuthorMapper());
    }

    @Override
    public void update(long id, Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("first_name", author.getFirstName())
                .addValue("last_name", author.getLastName());
        jdbc.update("UPDATE authors SET first_name=:first_name, last_name=:last_name WHERE id=:id", params);
    }

    @Override
    public void remove(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbc.update("DELETE FROM authors WHERE id=:id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
        }
    }
}
