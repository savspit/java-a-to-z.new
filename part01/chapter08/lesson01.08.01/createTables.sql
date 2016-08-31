\connect mydatabase

-- roleRights
CREATE TABLE roleRights (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL
);

-- roles 
CREATE TABLE roles (
	id serial PRIMARY KEY,
	name character varying (255) NOT NULL,
	roleRightId integer references roleRights(id)
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

-- ordersComments
CREATE TABLE ordersComments (
	id serial PRIMARY KEY,
	text character varying (2000)
);

-- ordersCategories
CREATE TABLE ordersCategories (
	id serial PRIMARY KEY,
	name character varying (20)
);

-- ordersAttachedFiles
CREATE TABLE ordersAttachedFiles (
	id serial PRIMARY KEY,
	filePath character varying (2000) NOT NULL
);

-- orders
CREATE TABLE orders (
	id serial PRIMARY KEY,
	orderDate timestamp,
	orderNumber serial,
	userId integer references users(id),
	statusId integer references ordersStatuses(id),
	commentId integer references ordersComments(id),
	categoryId integer references ordersCategories(id),
	attachedFileId integer references ordersAttachedFiles(id)
);