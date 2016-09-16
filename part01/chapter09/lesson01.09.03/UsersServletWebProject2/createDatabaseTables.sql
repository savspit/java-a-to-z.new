CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    login VARCHAR(255),
    email VARCHAR(255),
    createDate TIMESTAMP
);