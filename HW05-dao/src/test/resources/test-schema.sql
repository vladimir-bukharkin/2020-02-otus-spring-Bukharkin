DROP TABLE IF EXISTS authors;
CREATE TABLE authors(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    CONSTRAINT uq_author UNIQUE (first_name, last_name)
);
