\connect mydatabase

-- roleRights
INSERT INTO roleRights(name) VALUES ('admin');
INSERT INTO roleRights(name) VALUES ('read');
INSERT INTO roleRights(name) VALUES ('write');

-- roles
INSERT INTO roles(name) VALUES ('admin');
INSERT INTO roles(name) VALUES ('user');

-- rolesAndRights
INSERT INTO rolesAndRights(
			roleId, 
			rightId) VALUES (
			(SELECT id FROM roles WHERE name='admin'),
			(SELECT id FROM roleRights WHERE name='admin')
);
INSERT INTO rolesAndRights(
			roleId, 
			rightId) VALUES (
			(SELECT id FROM roles WHERE name='user'),
			(SELECT id FROM roleRights WHERE name='read')
);
INSERT INTO rolesAndRights(
			roleId, 
			rightId) VALUES (
			(SELECT id FROM roles WHERE name='user'),
			(SELECT id FROM roleRights WHERE name='write')
);

-- users
INSERT INTO users(
			login,
			password,
			createDate,
			roleId) VALUES (
			'admin',
			'admin',
			'1999-01-08 04:05:06',
			(SELECT id FROM roles WHERE name='admin')
);
INSERT INTO users(
			login,
			password,
			createDate,
			roleId) VALUES (
			'user',
			'123',
			'1999-01-08 04:05:06',
			(SELECT id FROM roles WHERE name='user')
);

-- ordersStatuses
INSERT INTO ordersStatuses(name) VALUES ('created');
INSERT INTO ordersStatuses(name) VALUES ('deleted');

-- ordersCategories
INSERT INTO ordersCategories(name) VALUES ('category1');
INSERT INTO ordersCategories(name) VALUES ('category2');

-- orders
INSERT INTO orders(
			orderDate,
			orderNumber, 
			userId, 
			statusId, 
			categoryId) VALUES (
			'1999-01-08 04:05:06',
			1,
			(SELECT id FROM users WHERE login='admin'),
			(SELECT id FROM ordersStatuses WHERE name='created'),
			(SELECT id FROM ordersCategories WHERE name='category1')
);
INSERT INTO orders(
			orderDate,
			orderNumber, 
			userId, 
			statusId, 
			categoryId) VALUES (
			'1999-01-08 04:05:06',
			2,
			(SELECT id FROM users WHERE login='user'),
			(SELECT id FROM ordersStatuses WHERE name='created'),
			(SELECT id FROM ordersCategories WHERE name='category2')
);

-- ordersComments
INSERT INTO ordersComments(
			text,
			orderId) VALUES (
			'some kind of comment 1...',
			(SELECT id FROM orders WHERE orderNumber=1)
);
INSERT INTO ordersComments(
			text,
			orderId) VALUES (
			'some kind of comment 2...',
			(SELECT id FROM orders WHERE orderNumber=1)
);
INSERT INTO ordersComments(
			text,
			orderId) VALUES (
			'some kind of comment 1...',
			(SELECT id FROM orders WHERE orderNumber=2)
);

-- ordersAttachedFiles
INSERT INTO ordersAttachedFiles(
			filePath,
			orderId) VALUES (
			'C:\\image1.jpg',
			(SELECT id FROM orders WHERE orderNumber=1)
);
INSERT INTO ordersAttachedFiles(
			filePath,
			orderId) VALUES (
			'C:\\image2.jpg',
			(SELECT id FROM orders WHERE orderNumber=1)
);
INSERT INTO ordersAttachedFiles(
			filePath,
			orderId) VALUES (
			'C:\\desc.doc',
			(SELECT id FROM orders WHERE orderNumber=2));