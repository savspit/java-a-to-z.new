CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255),
    email VARCHAR(255),
    createDate TIMESTAMP
);