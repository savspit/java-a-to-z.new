CREATE TABLE IF NOT EXISTS roles (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS addresses (
    id serial PRIMARY KEY,
    name VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS musicTypes (
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    addressId INTEGER REFERENCES addresses(id) UNIQUE,
    roleId INTEGER REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS usersAndMusicTypes (
    id serial PRIMARY KEY,
    usersId INTEGER REFERENCES users(id),
    musicTypeId INTEGER REFERENCES musicTypes(id)
);