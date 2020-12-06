DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS genre;
CREATE TABLE author(
    Id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    UNIQUE (first_name, last_name)
);
CREATE TABLE genre(
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE book(
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    genre_id INT NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre(Id)
);
CREATE TABLE book_author(
    author_id INT NOT NULL,
    book_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(Id),
    FOREIGN KEY (book_id) REFERENCES book(Id),
    UNIQUE (author_id, book_id)
);
