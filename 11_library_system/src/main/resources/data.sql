INSERT INTO GENRES (NAME) VALUES ('Drama');
INSERT INTO GENRES (NAME) VALUES ('Fantasy');
INSERT INTO GENRES (NAME) VALUES ('Thriller');
INSERT INTO GENRES (NAME) VALUES ('Mystery');

INSERT INTO AUTHORS (NAME) VALUES ('Mark Twain');
INSERT INTO AUTHORS (NAME) VALUES ('Luis Kerol');
INSERT INTO AUTHORS (NAME) VALUES ('Stephen King');
INSERT INTO AUTHORS (NAME) VALUES ('J. K. Rowling');

INSERT INTO BOOKS (TITLE, GENRE_ID, AUTHOR_ID) VALUES ('Last Witch', 2, 2);
INSERT INTO BOOKS (TITLE, GENRE_ID, AUTHOR_ID) VALUES ('First King', 1, 4);
INSERT INTO BOOKS (TITLE, GENRE_ID, AUTHOR_ID) VALUES ('Python. Last chapters', 3, 1);

INSERT INTO BOOK_COMMENTS (COMMENT_TEXT, BOOK_ID) VALUES ('Абырвалг', 2);