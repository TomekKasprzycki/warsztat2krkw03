create database warsztat2krkw03
charset utf8mb4
collate utf8mb4_unicode_ci;

CREATE TABLE users(
id int AUTO_INCREMENT,
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR (255) NOT NULL,
PRIMARY KEY (id)
);