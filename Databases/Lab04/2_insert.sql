USE university_ranking;

INSERT INTO country (country_name, country_continent)
VALUES	('Poland', 'Europe'),
		('Germany','Europe'),
		('France','Europe'),
		('Scotland','Europe'),
		('Belgium','Europe'),
		('Spain','Europe'),
		('USA','North America');




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


INSERT INTO university_year(university_id,year, num_students,student_staff_ratio)
VALUES
--	(1, 11000, -10, 0),
	(1, 2009, 4567, 3.2),
    (2, 2004, 3674, 4.5),
    (3, 2017, 2789, 6.1),
    (4, 2015, 3987, 2.8),
    (5, 2002, 5892, 8.3),
    (6, 2006, 4234, 5.7),
    (1, 2001, 3121, 4.9),
    (2, 2003, 4568, 7.2),
    (3, 2005, 3456, 5.5),
    (4, 2011, 5678, 6.8),
    (5, 2020, 4890, 3.1),
    (6, 2014, 4321, 2.5),
    (1, 2018, 3456, 4.7),
    (2, 2023, 4567, 3.9),
    (3, 2008, 2890, 7.1),
    (4, 2016, 3789, 5.6),
    (5, 2000, 4678, 6.4),
    (6, 2019, 5234, 4.2),
    (1, 2006, 4567, 8.1),
    (2, 2007, 5345, 5.2),
    (3, 2013, 4567, 6.5),
    (4, 2019, 5678, 4.8),
    (5, 2003, 4234, 2.9),
    (6, 2011, 3567, 3.8),
    (1, 2010, 4231, 6.9),
    (2, 2014, 4567, 2.6),
    (3, 2021, 3456, 5.7),
    (4, 2002, 5678, 7.3),
    (5, 2016, 4890, 3.5),
    (6, 2012, 4321, 5.1),
    (1, 2005, 3456, 3.9),
    (2, 2018, 4567, 6.2),
    (3, 2001, 2890, 4.5),
    (4, 2008, 3789, 5.7),
    (5, 2022, 4678, 4.1),
    (6, 2007, 5234, 2.3),
    (1, 2015, 4567, 7.9),
    (2, 2000, 5345, 4.2),
    (3, 2009, 4567, 6.4),
    (4, 2013, 5678, 3.8),
    (5, 2011, 4234, 5.6),
    (6, 2020, 3567, 4.3),
	(1, 2012, 4231, 3.1),
	(2, 2006, 4567, 6.5),
	(3, 2014, 3456, 4.9),
	(4, 2004, 5678, 5.2),
	(5, 2023, 4890, 3.7),
	(6, 2001, 4321, 5.3),
	(1, 2003, 3456, 7.2),
	(2, 2019, 4567, 5.8),
	(3, 2016, 2890, 4.1),
	(4, 2012, 3789, 6.2),
	(5, 2009, 4678, 5.5),
	(6, 2005, 5234, 3.6),
	(1, 2017, 4567, 2.7),
	(2, 2011, 5345, 4.8),
	(3, 2000, 4567, 6.3),
	(4, 2020, 5678, 4.4),
	(5, 2008, 4234, 5.1),
	(6, 2018, 3567, 6.7),
	(1, 2013, 4231, 3.3),
	(2, 2002, 4567, 5.6),
	(3, 2010, 3456, 7.4),
	(4, 2017, 5678, 4.9),
	(5, 2001, 4890, 6.1),
	(6, 2009, 4321, 4.7),
	(1, 2007, 3456, 5.2),
	(2, 2015, 4567, 3.6),
	(3, 2022, 2890, 5.5),
	(4, 2005, 3789, 6.3),
	(5, 2012, 4678, 4.2),
	(6, 2016, 5234, 7.1),
	(1, 2021, 4567, 4.9),
	(2, 2010, 5345, 6.8),
	(3, 2003, 4567, 5.1),
	(4, 2009, 5678, 3.5),
	(5, 2018, 4234, 4.3),
	(6, 2004, 3567, 6.4);
	

INSERT INTO university_ranking_year (university_id, ranking_criteria_id, year, score)
VALUES 
       (1, 1, 2021, 9),
       (1, 2, 2020, 8),
       (1, 3, 2022, 7),
       (1, 4, 2023, 8),
       (1, 5, 2021, 8),
       (1, 6, 2021, 7),
       (1, 7, 2021, 8),
       (1, 8, 2021, 7),
       (1, 9, 2021, 8),
       (1, 10, 2021, 8),
       (2, 1, 2021, 9),
       (2, 2, 2020, 8),
       (2, 3, 2021, 7),
       (2, 4, 2022, 8),
       (2, 5, 2021, 8),
       (2, 6, 2023, 7),
       (2, 7, 2021, 8),
       (2, 8, 2021, 7),
       (2, 9, 2021, 8),
       (2, 10, 2021, 7),
       (3, 1, 2021, 9),
       (3, 2, 2023, 8),
       (3, 3, 2022, 7),
       (3, 4, 2020, 7),
       (3, 5, 2021, 8),
       (3, 6, 2021, 7),
       (3, 7, 2022, 8),
       (3, 8, 2023, 7),
       (3, 9, 2020, 8),
       (3, 10, 2021, 6),
       (4, 1, 2021, 8),
       (4, 2, 2022, 8),
       (4, 3, 2023, 7),
       (4, 4, 2020, 7),
       (4, 5, 2021, 8),
       (4, 6, 2021, 7),
       (4, 7, 2021, 8),
       (4, 8, 2021, 7),
       (4, 9, 2021, 7),
       (4, 10, 2021, 5),
       (5, 1, 2021, 8),
       (5, 2, 2022, 8),
       (5, 3, 2020, 7),
       (5, 4, 2021, 7),
       (5, 5, 2021, 8),
       (5, 6, 2023, 7),
       (5, 7, 2021, 8),
       (5, 8, 2021, 7),
       (5, 9, 2021, 7),
       (5, 10, 2021, 4),
       (6, 1, 2021, 9),
       (6, 2, 2021, 8),
	   (6, 3, 2023, 7),
	   (6, 4, 2021, 7),
	   (6, 5, 2022, 9),
	   (6, 6, 2021, 8),
	   (6, 7, 2020, 8),
	   (6, 8, 2021, 8),
	   (6, 9, 2021, 8),
	   (6, 10, 2021, 1);