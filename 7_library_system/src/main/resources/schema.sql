DROP TABLE IF EXISTS Genres;
CREATE TABLE Genres(
    Id BIGINT PRIMARY KEY,
    Name VARCHAR(255)
);

DROP TABLE IF EXISTS Authors;
CREATE TABLE Authors(
    Id BIGINT PRIMARY KEY,
    Name VARCHAR(255)
    );

DROP TABLE IF EXISTS Books;
CREATE TABLE Books(
    Id BIGINT PRIMARY KEY,
    Title VARCHAR(255),
    GenreId BIGINT,
    AuthorId BIGINT
);

DROP TABLE IF EXISTS Sequence;
CREATE TABLE Sequence(
    Id BIGINT PRIMARY KEY,
    Name VARCHAR(255),
    LastValue BIGINT
);
