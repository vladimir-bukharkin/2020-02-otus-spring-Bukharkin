DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
CREATE TABLE authors(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    CONSTRAINT uq_author UNIQUE (first_name, last_name)
);
CREATE TABLE genres(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);