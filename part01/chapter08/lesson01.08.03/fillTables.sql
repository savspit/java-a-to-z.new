-- transmission
INSERT INTO transmission(name) VALUES ('mechanical');
INSERT INTO transmission(name) VALUES ('hydromechanical');
INSERT INTO transmission(name) VALUES ('hydraulic');
INSERT INTO transmission(name) VALUES ('hydrostatic');
INSERT INTO transmission(name) VALUES ('electromechanical');

-- engine
INSERT INTO engine(name) VALUES ('gasoline');
INSERT INTO engine(name) VALUES ('diesel');
INSERT INTO engine(name) VALUES ('gas');

-- gearbox
INSERT INTO gearbox(name) VALUES ('mechanical');
INSERT INTO gearbox(name) VALUES ('auto');
INSERT INTO gearbox(name) VALUES ('variable');

-- carBody
INSERT INTO carBody(
            name,
            transmissionId,
            engineId,
            gearboxId) VALUES (
            'car body 1',
            (SELECT id FROM transmission WHERE name='mechanical'),
            (SELECT id FROM engine WHERE name='gasoline'),
            (SELECT id FROM gearbox WHERE name='auto')
);
INSERT INTO carBody(
            name,
            transmissionId,
            engineId,
            gearboxId) VALUES (
            'car body 2',
            (SELECT id FROM transmission WHERE name='electromechanical'),
            (SELECT id FROM engine WHERE name='diesel'),
            (SELECT id FROM gearbox WHERE name='variable')
);
INSERT INTO carBody(
            name,
            transmissionId,
            engineId,
            gearboxId) VALUES (
            'car body 3',
            (SELECT id FROM transmission WHERE name='hydrostatic'),
            (SELECT id FROM engine WHERE name='gas'),
            (SELECT id FROM gearbox WHERE name='mechanical')
);