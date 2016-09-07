
-- tables

CREATE TABLE IF NOT EXISTS authors (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    link VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS offers (
    id serial PRIMARY KEY,
    text VARCHAR(2000),
    link VARCHAR(2000),
    createDate TIMESTAMP,
    authorId INTEGER REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS properties (
    id serial PRIMARY KEY,
    runTime TIMESTAMP
);


-- rules

CREATE OR REPLACE RULE authors_ignore_duplicates AS
    ON INSERT TO authors
    WHERE (EXISTS
        (SELECT 1 FROM authors WHERE authors.name = NEW.name AND authors.link = NEW.link))
        DO INSTEAD NOTHING;

CREATE OR REPLACE RULE offers_ignore_duplicates AS
    ON INSERT TO offers
    WHERE (EXISTS
        (SELECT 1 FROM offers WHERE offers.text = NEW.text AND offers.link = NEW.link))
        DO INSTEAD NOTHING;