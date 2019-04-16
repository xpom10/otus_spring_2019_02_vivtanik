DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS AUTHOR;

CREATE TABLE GENRE
(
  GENRE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  GENRE_NAME    VARCHAR(255) NOT NULL
);

CREATE TABLE AUTHOR
(
  AUTHOR_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  AUTHOR_NAME      VARCHAR(255) NOT NULL
);

CREATE TABLE BOOKS
(
  ID        BIGINT AUTO_INCREMENT PRIMARY KEY,
  TITLE     VARCHAR(255) NOT NULL,
  GENRE_ID  BIGINT          NOT NULL,
  AUTHOR_ID BIGINT          NOT NULL,
  FOREIGN KEY (GENRE_ID) references GENRE (GENRE_ID),
  FOREIGN KEY (AUTHOR_ID) references AUTHOR (AUTHOR_ID)
);

CREATE TABLE COMMENTS
(
  COMMENT_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  BOOK_ID BIGINT NOT NULL,
  COMMENT VARCHAR(255) NOT NULL,
  FOREIGN KEY (BOOK_ID) references BOOKS (ID)
);