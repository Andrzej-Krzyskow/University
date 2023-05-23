USE university_ranking;

--SELECT DISTINCT id, country_name  FROM country;
--SELECT DISTINCT country_name FROM country;

--SELECT DISTINCT id, country_name  FROM country;
--SELECT * FROM university;

--SELECT * FROM ranking_system;

--SELECT * FROM ranking_criteria;

SELECT *
FROM university_ranking_year
INNER JOIN ranking_criteria ON ranking_criteria_id = ranking_criteria.id
INNER JOIN ranking_system ON ranking_system.id = ranking_criteria.ranking_system_id
WHERE score >= 85 AND system_name = 'World University Rankings';


SELECT TOP 10 *
FROM university_ranking_year
INNER JOIN ranking_criteria ON ranking_criteria_id = ranking_criteria.id
INNER JOIN ranking_system ON ranking_system.id = ranking_criteria.ranking_system_id
INNER JOIN university ON university_id = university.id
INNER JOIN country ON country.id = country_id
WHERE	system_name = 'World University Rankings'
AND		year = 2021
ORDER BY score DESC

SELECT AVG(score) AS average_score
FROM university_ranking_year
WHERE ranking_criteria_id = 1
AND year = 2021;

SELECT c.country_name, COUNT(*) AS num_universities
FROM university u
JOIN country c ON u.country_id = c.id
GROUP BY c.country_name
ORDER BY num_universities DESC;

SELECT rs.system_name, COUNT(*) AS num_criteria
FROM ranking_criteria rc
JOIN ranking_system rs ON rc.ranking_system_id = rs.id
GROUP BY rs.system_name
ORDER BY num_criteria DESC;