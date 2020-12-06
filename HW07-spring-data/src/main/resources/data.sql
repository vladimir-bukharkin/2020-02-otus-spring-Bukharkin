INSERT INTO author(first_name, last_name) VALUES ('Leo', 'Tolstoy');
INSERT INTO author(first_name, last_name) VALUES ('Joshua', 'Bloch');
INSERT INTO author(first_name, last_name) VALUES ('Josh', 'Long');
INSERT INTO author(first_name, last_name) VALUES ('Albert', 'Camus');
INSERT INTO author(first_name, last_name) VALUES ('Other', 'Author');

INSERT INTO genre(name) VALUES ('novel');
INSERT INTO genre(name) VALUES ('education');
INSERT INTO genre(name) VALUES ('other');

INSERT INTO book(name, genre_id) VALUES ('Plague', 1);
INSERT INTO book(name, genre_id) VALUES ('Effective Java', 2);
INSERT INTO book(name, genre_id) VALUES ('War and Peace', 1);
INSERT INTO book(name, genre_id) VALUES ('Java Concurrency in Practice', 2);
INSERT INTO book(name, genre_id) VALUES ('The Reactive Spring Book', 2);
INSERT INTO book(name, genre_id) VALUES ('Anna Karenina', 1);
INSERT INTO book(name, genre_id) VALUES ('Cook book', 3);

INSERT INTO book_author(author_id, book_id) VALUES (1, 3);
INSERT INTO book_author(author_id, book_id) VALUES (1, 6);
INSERT INTO book_author(author_id, book_id) VALUES (2, 2);
INSERT INTO book_author(author_id, book_id) VALUES (2, 4);
INSERT INTO book_author(author_id, book_id) VALUES (3, 5);
INSERT INTO book_author(author_id, book_id) VALUES (4, 1);
INSERT INTO book_author(author_id, book_id) VALUES (5, 4);
INSERT INTO book_author(author_id, book_id) VALUES (5, 2);
INSERT INTO book_author(author_id, book_id) VALUES (5, 7);