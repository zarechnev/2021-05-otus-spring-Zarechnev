DROP TABLE IF EXISTS BOOK_COMMENTS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS AUTHORS;

CREATE TABLE GENRES(
    ID BIGINT IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE AUTHORS(
    ID BIGINT IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE BOOKS(
    ID BIGINT IDENTITY PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    GENRE_ID BIGINT NOT NULL REFERENCES GENRES (ID),
    AUTHOR_ID BIGINT NOT NULL REFERENCES AUTHORS (ID),
    CONSTRAINT AUTHOR_ID_FK FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID),
    CONSTRAINT GENRE_ID_FL FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID)
);

CREATE TABLE BOOK_COMMENTS
(
    ID BIGINT IDENTITY PRIMARY KEY,
    COMMENT_TEXT VARCHAR(1000) NOT NULL,
    BOOK_ID BIGINT REFERENCES BOOKS (ID) ON DELETE CASCADE
);