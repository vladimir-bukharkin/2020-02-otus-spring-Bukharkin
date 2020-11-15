INSERT INTO Authors(FirstName, LastName) VALUES ('Lev', 'Tolstoy');
INSERT INTO Authors(FirstName, LastName) VALUES ('Joshua', 'Bloch');
INSERT INTO Authors(FirstName, LastName) VALUES ('Josh', 'Long');

INSERT INTO Genres(Name) VALUES ('novel');
INSERT INTO Genres(Name) VALUES ('story');
INSERT INTO Genres(Name) VALUES ('education');
INSERT INTO Genres(Name) VALUES ('other');

INSERT INTO Books(Name, GenreId) VALUES ('Plague', 1);
INSERT INTO Books(Name, GenreId) VALUES ('Effective Java', 2);
INSERT INTO Books(Name, GenreId) VALUES ('War and Peace', 1);
INSERT INTO Books(Name, GenreId) VALUES ('Java Concurrency in Practice', 2);
INSERT INTO Books(Name, GenreId) VALUES ('The Reactive Spring Book', 2);

INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (1, 3);
INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (2, 3);
INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (2, 4);
INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (3, 5);
INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (3, 1);
INSERT INTO AuthorBookRelations(AuthorId, BookId) VALUES (3, 2);