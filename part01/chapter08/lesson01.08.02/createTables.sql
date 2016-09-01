\connect mydatabase

-- filterOperators
CREATE TABLE filterOperators (
	id serial PRIMARY KEY,
	name character varying (4) NOT NULL
);

-- filters
CREATE TABLE filters (
	id serial PRIMARY KEY,
	active boolean,
	extended boolean,
	name character varying (255),
	tableName character varying (255),
	fieldName character varying (255),
	fieldValue character varying (255),
	userId integer references users(id),
	operatorId integer references filterOperators(id)
);

