\connect mydatabase

-- roleRights
INSERT INTO roleRights(name) VALUES ('admin');
INSERT INTO roleRights(name) VALUES ('read');
INSERT INTO roleRights(name) VALUES ('write');

-- roles
INSERT INTO roles(
			name,
			roleRightId) VALUES (
			'admin',
			(SELECT id FROM roleRights WHERE name='admin')
);
INSERT INTO roles(
			name,
			roleRightId) VALUES (
			'user',
			(SELECT id FROM roleRights WHERE name='read')
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

-- ordersComments
INSERT INTO ordersComments(text) VALUES ('some kind of comment...');

-- ordersCategories
INSERT INTO ordersCategories(name) VALUES ('category1');
INSERT INTO ordersCategories(name) VALUES ('category2');

-- ordersAttachedFiles
INSERT INTO ordersAttachedFiles(filePath) VALUES ('C:\\image.jpg');
INSERT INTO ordersAttachedFiles(filePath) VALUES ('C:\\desc.doc');

-- orders
INSERT INTO orders(
			orderDate,
			orderNumber, 
			userId, 
			statusId, 
			commentId, 
			categoryId, 
			attachedFileId) VALUES (
			'1999-01-08 04:05:06',
			'1',
			(SELECT id FROM users WHERE login='admin'),
			(SELECT id FROM ordersStatuses WHERE name='created'),
			(SELECT id FROM ordersComments WHERE text='some kind of comment...'),
			(SELECT id FROM ordersCategories WHERE name='category1'),
			(SELECT id FROM ordersAttachedFiles WHERE filePath LIKE '%image%')
);
INSERT INTO orders(
			orderDate,
			orderNumber, 
			userId, 
			statusId, 
			commentId, 
			categoryId, 
			attachedFileId) VALUES (
			'1999-01-08 04:05:06',
			'2',
			(SELECT id FROM users WHERE login='user'),
			(SELECT id FROM ordersStatuses WHERE name='created'),
			(SELECT id FROM ordersComments WHERE text='some kind of comment...'),
			(SELECT id FROM ordersCategories WHERE name='category2'),
			(SELECT id FROM ordersAttachedFiles WHERE filePath LIKE '%desc%')
);