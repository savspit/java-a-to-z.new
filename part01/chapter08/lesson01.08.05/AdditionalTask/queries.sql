1.

SELECT p.name personName, c.name companyName FROM person AS p JOIN company AS c ON p.company_id = c.id AND c.id <> 5


2.

SELECT c.name companyName, COUNT(p.id) numberOfPersons
FROM company AS c LEFT JOIN person AS p ON c.id = p.company_id
GROUP BY c.id
HAVING COUNT(p.id) =
(
SELECT MAX(q.numberOfPersons) FROM
(SELECT c.id, COUNT(p.id) numberOfPersons FROM company AS c LEFT JOIN person AS p ON c.id = p.company_id GROUP BY c.id) AS q
)
