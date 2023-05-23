USE university_ranking;

INSERT INTO country (country_name)
VALUES	('Poland'),
		('Germany'),
		('France'),
		('Scotland'),
		('Belgium'),
		('Spain'),
		('USA');




INSERT INTO university (university_name, country_id)
SELECT 'PWr', id FROM country WHERE country_name = 'Poland'
UNION ALL
SELECT 'University of Freiburg', id FROM country WHERE country_name = 'Germany'
UNION ALL
SELECT 'UWr', id FROM country WHERE country_name = 'Poland'
UNION ALL
SELECT 'Heidelberg University', id FROM country WHERE country_name = 'Germany'
UNION ALL
SELECT 'Harvard University', id FROM country WHERE country_name = 'USA'
UNION ALL
SELECT 'Stanford University', id FROM country WHERE country_name = 'USA';


--DELETE FROM university;
--DBCC CHECKIDENT('university', RESEED, 0);


INSERT INTO ranking_system (system_name)
VALUES ('World University Rankings'),
       ('Country University Rankings'),
       ('Subject-Specific Rankings');


INSERT INTO ranking_criteria (criteria_name, ranking_system_id)
VALUES ('Academic Reputation', 1),
       ('Employer Reputation', 1),
       ('Citations per Faculty', 1),
       ('International Faculty Ratio', 1),
       ('International Student Ratio', 1),
       ('Research', 2),
       ('Teaching', 2),
       ('Research Income', 2),
       ('Subject Ranking', 3),
       ('Publication Citation', 3);


INSERT INTO university_ranking_year (university_id, ranking_criteria_id, year, score)
VALUES 
       (1, 1, 2021, 93),
       (1, 2, 2021, 85),
       (1, 3, 2021, 77),
       (1, 4, 2021, 81),
       (1, 5, 2021, 84),
       (1, 6, 2021, 79),
       (1, 7, 2021, 86),
       (1, 8, 2021, 76),
       (1, 9, 2021, 82),
       (1, 10, 2021, 88),
       (2, 1, 2021, 91),
       (2, 2, 2021, 83),
       (2, 3, 2021, 76),
       (2, 4, 2021, 80),
       (2, 5, 2021, 83),
       (2, 6, 2021, 78),
       (2, 7, 2021, 85),
       (2, 8, 2021, 74),
       (2, 9, 2021, 81),
       (2, 10, 2021, 87),
       (3, 1, 2021, 90),
       (3, 2, 2021, 82),
       (3, 3, 2021, 75),
       (3, 4, 2021, 79),
       (3, 5, 2021, 82),
       (3, 6, 2021, 77),
       (3, 7, 2021, 84),
       (3, 8, 2021, 73),
       (3, 9, 2021, 80),
       (3, 10, 2021, 86),
       (4, 1, 2021, 89),
       (4, 2, 2021, 81),
       (4, 3, 2021, 74),
       (4, 4, 2021, 78),
       (4, 5, 2021, 81),
       (4, 6, 2021, 76),
       (4, 7, 2021, 83),
       (4, 8, 2021, 72),
       (4, 9, 2021, 79),
       (4, 10, 2021, 85),
       (5, 1, 2021, 88),
       (5, 2, 2021, 80),
       (5, 3, 2021, 73),
       (5, 4, 2021, 77),
       (5, 5, 2021, 80),
       (5, 6, 2021, 75),
       (5, 7, 2021, 82),
       (5, 8, 2021, 71),
       (5, 9, 2021, 78),
       (5, 10, 2021, 84),
       (6, 1, 2021, 92),
       (6, 2, 2021, 86),
	   (6, 3, 2021, 78),
	   (6, 4, 2021, 77),
	   (6, 5, 2021, 99),
	   (6, 6, 2021, 87),
	   (6, 7, 2021, 88),
	   (6, 8, 2021, 84),
	   (6, 9, 2021, 80),
	   (6, 10, 2021, 81);