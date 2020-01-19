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
                .addValue("FirstName", author.getFirstName())
                .addValue("LastName", author.getLastName());
        jdbc.update("INSERT INTO Authors (FirstName, LastName) values(:FirstName, :LastName)", params);
    }

    @Override
    public Author getById(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id);
        return jdbc.queryForObject("SELECT * FROM Authors WHERE Id=:Id", params, new AuthorMapper());
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("FirstName", firstName)
                .addValue("LastName", lastName);
        return jdbc.queryForObject("SELECT * FROM Authors WHERE FirstName=:FirstName AND LastName=:LastName",
                params,
                new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT * FROM Authors", new AuthorMapper());
    }

    @Override
    public void update(long id, Author author) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id)
                .addValue("FirstName", author.getFirstName())
                .addValue("LastName", author.getLastName());
        jdbc.update("UPDATE Authors SET FirstName=:FirstName, LastName=:LastName WHERE Id=:Id", params);
    }

    @Override
    public void remove(long id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("Id", id);
        jdbc.update("DELETE FROM Authors WHERE Id=:Id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getInt("Id"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"));
        }
    }
}
