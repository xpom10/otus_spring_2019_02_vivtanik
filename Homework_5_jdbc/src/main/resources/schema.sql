drop schema if exists library;
CREATE schema library;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID INT PRIMARY KEY, TITLE VARCHAR(255));
