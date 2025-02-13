CREATE SEQUENCE movies_seq AS BIGINT START WITH 1 INCREMENT BY 50;

CREATE TABLE movies
(
    id     INT IDENTITY ,
    title  VARCHAR(255),
    genre  VARCHAR(255),
    rating INT NOT NULL,
    CONSTRAINT pk_movies PRIMARY KEY (id)
);

INSERT INTO movies (id, title, genre, rating) VALUES (1, 'Alien', 'Sci-Fi', 10);
INSERT INTO movies (id, title, genre, rating) VALUES (2, 'Event-Horizon', 'Sci-Fi', 7);
INSERT INTO movies (id, title, genre, rating) VALUES (3, 'Titanic', 'Romance', 10);
INSERT INTO movies (id, title, genre, rating) VALUES (4, 'Men in Black', 'Comedy', 8);
INSERT INTO movies (id, title, genre, rating) VALUES (5, 'The Godfather', 'Mafia', 10);
INSERT INTO movies (id, title, genre, rating) VALUES (6, 'Fight Club', 'Action', 6);
INSERT INTO movies (id, title, genre, rating) VALUES (7, 'Gladiator', 'Action', 7);
INSERT INTO movies (id, title, genre, rating) VALUES (8, 'Starship Troopers', 'Sci-Fi', 3);
INSERT INTO movies (id, title, genre, rating) VALUES (9, 'V for Vendetta', 'Action', 1);
INSERT INTO movies (id, title, genre, rating) VALUES (10, '300', 'Action', 4);