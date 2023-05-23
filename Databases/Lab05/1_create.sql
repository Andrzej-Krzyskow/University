--create database employee_projects

USE employee_projects

DROP TABLE IF EXISTS
					employee_project_task,
					project,
					client,
					employee,
					department;

CREATE TABLE client(
	client_id				INT IDENTITY(1,1)	NOT NULL,
	client_name				VARCHAR(100)		NOT NULL,
	address					VARCHAR(200)				,
	email					VARCHAR(30)			UNIQUE	,
	phone					INT							,
	business				VARCHAR(100)		NOT NULL,
	PRIMARY KEY (client_id)
);

CREATE TABLE project(
	project_id				INT IDENTITY(1,1)	NOT NULL,
	description				VARCHAR(200)				,
	start_date				DATE						,
	planned_end_date		DATE						,
	actual_end_date			DATE						,
	budget					INT					CHECK(budget>0),
	client_id				INT					NOT NULL,
	FOREIGN KEY (client_id) REFERENCES client (client_id),
	PRIMARY KEY (project_id),
	CHECK(actual_end_date>planned_end_date)
);

CREATE TABLE department(
	department_no			INT IDENTITY(1,1)	NOT NULL,
	department_name			VARCHAR(100)		NOT NULL,
	PRIMARY KEY (department_no)
);

CREATE TABLE employee(
	employee_no				INT	IDENTITY(1,1)	NOT NULL,
	employee_name			VARCHAR(100)		NOT NULL,
	job						VARCHAR(100)				,
	salary					INT					CHECK (salary>1700),
	department_no			INT					NOT NULL,
	FOREIGN KEY (department_no) REFERENCES department (department_no),
	PRIMARY KEY (employee_no)
);

CREATE TABLE employee_project_task(
	project_id				INT					NOT NULL,
	employee_no				INT					NOT NULL,
	start_date				DATE						,
	end_date				DATE						,
	task					VARCHAR(100)				,
	status					VARCHAR(30)				,
	FOREIGN KEY (project_id) REFERENCES project (project_id),
	FOREIGN KEY (employee_no) REFERENCES employee (employee_no),
	PRIMARY KEY (project_id, employee_no)
);