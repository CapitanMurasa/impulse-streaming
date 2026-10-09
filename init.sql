CREATE TABLE USERS(
    id SERIAL PRIMARY KEY,
    username varchar(150) NOT NULL UNIQUE,
    password varchar(255) NOT NULL
)