--create database university_ranking

USE university_ranking

DROP TABLE IF EXISTS
					university_ranking_year,
					ranking_criteria,
					university_year,
					university,
					country,
					ranking_system;

CREATE TABLE country(
	id						INT IDENTITY(1,1)	NOT NULL,
	country_name			VARCHAR(50)			NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE university(
	id						INT IDENTITY(1,1)	NOT NULL,
	country_id				INT					NOT NULL,
	university_name			VARCHAR(50)			NOT NULL,
	FOREIGN KEY (country_id) REFERENCES country (id),
	PRIMARY KEY (id)
);

CREATE TABLE ranking_system(
	id						INT IDENTITY(1,1)	NOT NULL,
	system_name				VARCHAR(50)			NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE ranking_criteria(
	id						INT IDENTITY(1,1)	NOT NULL,
	ranking_system_id		INT					NOT NULL,
	criteria_name			VARCHAR(50)			NOT NULL,
	FOREIGN KEY (ranking_system_id) REFERENCES ranking_system (id),
	PRIMARY KEY (id)
);

CREATE TABLE university_year(
	university_year_id		INT	IDENTITY(1,1)	NOT NULL,
	university_id			INT					NOT NULL,
	year					INT					CHECK (year>1700),
	num_students			INT					CHECK (num_students>=0),
	student_staff_ratio		FLOAT				NOT NULL,
	FOREIGN KEY (university_id) REFERENCES university (id),
	PRIMARY KEY (university_year_id)
);

CREATE TABLE university_ranking_year(
	university_id			INT					NOT NULL,
	ranking_criteria_id		INT					NOT NULL,
	year					INT					NOT NULL,
	score					INT					,
	FOREIGN KEY (university_id) REFERENCES university (id),
	FOREIGN KEY (ranking_criteria_id) REFERENCES ranking_criteria (id),
	PRIMARY KEY (university_id, ranking_criteria_id, year)
);