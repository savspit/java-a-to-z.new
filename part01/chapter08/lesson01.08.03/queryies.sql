-- show all cars
SELECT cb.name FROM carBody AS cb

-- show all cars and detailes
SELECT cb.name, t.name, e.name, g.name FROM carBody AS cb
LEFT JOIN transmission AS t ON cb.transmissionId = t.id
LEFT JOIN engine AS e ON cb.engineId = e.id
LEFT JOIN gearbox AS g ON cb.gearboxId = g.id

-- show transmissions that are not used in cars
SELECT t.name FROM transmission AS t LEFT JOIN carBody AS cb ON cb.transmissionId = t.id WHERE cb.id IS NULL

-- show engines that are not used in cars
SELECT e.name FROM engine AS e LEFT JOIN carBody AS cb ON cb.transmissionId = e.id WHERE cb.id IS NULL

-- show gearboxes that are not used in cars
SELECT g.name FROM gearbox AS g LEFT JOIN carBody AS cb ON cb.transmissionId = g.id WHERE cb.id IS NULL