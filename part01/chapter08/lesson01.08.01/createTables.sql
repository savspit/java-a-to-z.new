\connect mydatabase

-- roleRights
CREATE TABLE roleRights (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- roles 
CREATE TABLE roles (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- rolesAndRights 
CREATE TABLE rolesAndRights (
	id serial PRIMARY KEY,
	roleId integer references roles(id),
	rightId integer references roleRights(id)
);

-- users
CREATE TABLE users (
	id serial PRIMARY KEY,
	login character varying (2000) NOT NULL,
	password character varying (2000) NOT NULL,
	createDate timestamp,
	roleId integer references roles(id)
);

-- ordersStatuses
CREATE TABLE ordersStatuses (
	id serial PRIMARY KEY,
	name character varying (20) NOT NULL
);

-- ordersCategories
CREATE TABLE ordersCategories (
	id serial PRIMARY KEY,
	name character varying (20)
);

-- orders
CREATE TABLE orders (
	id serial PRIMARY KEY,
	orderDate timestamp,
	orderNumber serial,
	userId integer references users(id),
	statusId integer references ordersStatuses(id),
	categoryId integer references ordersCategories(id)
);

-- ordersComments
CREATE TABLE ordersComments (
	id serial PRIMARY KEY,
	text character varying (2000),
	orderId integer references orders(id)
);

-- ordersAttachedFiles
CREATE TABLE ordersAttachedFiles (
	id serial PRIMARY KEY,
	filePath character varying (2000) NOT NULL,
	orderId integer references orders(id)
);