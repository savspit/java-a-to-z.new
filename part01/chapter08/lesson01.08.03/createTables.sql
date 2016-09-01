-- transmission
CREATE TABLE transmission (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- engine
CREATE TABLE engine (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- gearbox
CREATE TABLE gearbox (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- carBody
CREATE TABLE carBody (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL,
	transmissionId integer references transmission,
	engineId integer references engine,
	gearboxId integer references gearbox
);

