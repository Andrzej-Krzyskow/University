USE university_ranking;

--SELECT * FROM university;

--SELECT * FROM ranking_system;

--SELECT * FROM ranking_criteria;

SELECT * FROM university_ranking_year;

SELECT * FROM university_year;


-- TASK 2
SELECT id as 'University ID', university_name as 'University Name'
FROM university;

-- TASK 3
ALTER TABLE country
ADD country_continent VARCHAR(100)	NOT NULL

ALTER TABLE country
ALTER COLUMN country_name VARCHAR(100) NOT NULL

-- TASK 4
ALTER TABLE university_ranking_year
ADD CONSTRAINT check_year CHECK (year>1800)

ALTER TABLE university_ranking_year
ADD CONSTRAINT check_score CHECK (score BETWEEN 0 AND 10)

-- TASK 5
DELETE FROM university_year
WHERE university_year_id = 1;


-- TASK 6
DECLARE @x INT;
SET @x = 1

UPDATE University
SET university_name = 'Wrocław University of Science and Technology'
WHERE id = @x;

SELECT * FROM university;