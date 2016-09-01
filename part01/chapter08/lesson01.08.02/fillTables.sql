\connect mydatabase

-- filterOperators
INSERT INTO filterOperators(name) VALUES ('>');
INSERT INTO filterOperators(name) VALUES ('<');
INSERT INTO filterOperators(name) VALUES ('=');
INSERT INTO filterOperators(name) VALUES ('>=');
INSERT INTO filterOperators(name) VALUES ('<=');
INSERT INTO filterOperators(name) VALUES ('<>');
INSERT INTO filterOperators(name) VALUES ('IN');
INSERT INTO filterOperators(name) VALUES ('LIKE');
INSERT INTO filterOperators(name) VALUES ('NOT IN');
INSERT INTO filterOperators(name) VALUES ('NOT LIKE');

-- filterOperators
INSERT INTO filterOperators(name) VALUES ('>');
INSERT INTO filterOperators(name) VALUES ('<');

-- filters
INSERT INTO filters(
            active,
            extended,
            name,
            tableName,
            fieldName,
            fieldValue,
            userId,
            operationId) VALUES (
            false,
            "orderNumber = 1",
            "orders",
            "orderNumber",
            1,
            (SELECT id FROM users WHERE login LIKE '%admin%'),
            (SELECT id FROM filterOperators WHERE name='=')
);
INSERT INTO filters(
            active,
            extended,
            name,
            tableName,
            fieldName,
            fieldValue,
            userId,
            operationId) VALUES (
            false,
            "orderComment like 1",
            "orders",
            "orderNumber",
            1,
            (SELECT id FROM users WHERE login LIKE '%user%'),
            (SELECT id FROM filterOperators WHERE name='=')
);

