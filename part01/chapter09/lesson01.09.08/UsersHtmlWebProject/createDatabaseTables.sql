CREATE TABLE IF NOT EXISTS roles (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS cities (
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS countries (
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255),
    email VARCHAR(255),
    createDate TIMESTAMP,
    cityId INTEGER REFERENCES cities(id),
    countryId INTEGER REFERENCES countries(id),
    roleId INTEGER REFERENCES roles(id)
);