INSERT INTO Genres (Id, Name) VALUES (1, 'Drama');
INSERT INTO Genres (Id, Name) VALUES (2, 'Fantasy');

INSERT INTO Authors (Id, Name) VALUES (1, 'Mark Twain');
INSERT INTO Authors (Id, Name) VALUES (2, 'Luis Kerol');

INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (1, 'Last Witch', 2, 2);
INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (2, 'First King', 1, 1);

INSERT INTO Sequence (Id, Name, LastValue) VALUES (1, 'Genre', 2);
INSERT INTO Sequence (Id, Name, LastValue) VALUES (2, 'Author', 2);
INSERT INTO Sequence (Id, Name, LastValue) VALUES (3, 'Book', 2);

