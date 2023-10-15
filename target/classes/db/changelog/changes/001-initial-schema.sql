--liquibase formatted sql

--changeset gustavo.faria:001
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL CONSTRAINT username_unique UNIQUE ,
                       email VARCHAR(255) NOT NULL CONSTRAINT email_unique  UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE posts (
                       id SERIAL PRIMARY KEY,
                       value TEXT NOT NULL,
                       type VARCHAR(255) NOT NULL,
                       date_post TIMESTAMP NOT NULL,
                       user_id INT REFERENCES users(id),
                       posts_id INT REFERENCES posts(id)
);

CREATE TABLE albums (
                        id SERIAL PRIMARY KEY,
                        name_album VARCHAR(255) NOT NULL,
                        user_id INT REFERENCES users(id)
);

CREATE TABLE photos (
                        id SERIAL PRIMARY KEY,
                        photo_path VARCHAR(255) NOT NULL,
                        album_id INT REFERENCES albums(id)
);