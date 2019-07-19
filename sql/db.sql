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

ALTER TABLE users ADD groupID int;

CREATE TABLE groupsTable(
id int AUTO_INCREMENT,
name VARCHAR (255) NOT NULL UNIQUE,
PRIMARY KEY (id));

ALTER TABLE users
ADD FOREIGN KEY (groupID) REFERENCES groupsTable(id);

CREATE TABLE exercise(
id int AUTO_INCREMENT,
title VARCHAR (255) NOT NULL UNIQUE,
description TEXT,
PRIMARY KEY (id));

CREATE TABLE solution(
id int AUTO_INCREMENT,
created DATE,
updated DATE,
description TEXT,
exerciseID int,
userID int,
PRIMARY KEY (id),
FOREIGN KEY (exerciseID) REFERENCES exercise(id),
FOREIGN KEY (userID) REFERENCES users(id));


