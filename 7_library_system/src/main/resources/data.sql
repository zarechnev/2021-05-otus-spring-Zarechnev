INSERT INTO Genres (Id, Name) VALUES (1, 'Drama');
INSERT INTO Genres (Id, Name) VALUES (2, 'Fantasy');
INSERT INTO Genres (Id, Name) VALUES (3, 'Thriller');
INSERT INTO Genres (Id, Name) VALUES (4, 'Mystery');

INSERT INTO Authors (Id, Name) VALUES (1, 'Mark Twain');
INSERT INTO Authors (Id, Name) VALUES (2, 'Luis Kerol');
INSERT INTO Authors (Id, Name) VALUES (3, 'Stephen King');
INSERT INTO Authors (Id, Name) VALUES (4, 'J. K. Rowling');

INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (1, 'Last Witch', 2, 2);
INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (2, 'First King', 1, 4);
INSERT INTO Books (Id, Title, GenreId, AuthorId) VALUES (3, 'Python. Last chapters', 3, 1);

INSERT INTO Sequence (Id, Name, LastValue) VALUES (1, 'Genre', 4);
INSERT INTO Sequence (Id, Name, LastValue) VALUES (2, 'Author', 4);
INSERT INTO Sequence (Id, Name, LastValue) VALUES (3, 'Book', 3);
