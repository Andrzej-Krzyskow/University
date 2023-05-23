USE employee_projects


SELECT * FROM client;

SELECT * FROM project;

SELECT * FROM department;

SELECT * FROM employee;

SELECT * FROM employee_project_task;

-- TASK 2
SELECT  employee_no, employee_name FROM employee
WHERE employee_name LIKE 'M%'

-- TASK 3
SELECT  employee_no, employee_name, LEN(employee_name) as name_len FROM employee
WHERE LEN(employee_name) = 
	(SELECT MAX(LEN(employee_name)) FROM employee)

-- TASK 4
SELECT employee_no,department_name, employee_name, salary FROM employee
INNER JOIN department ON department.department_no = employee.department_no
ORDER BY salary DESC

-- TASK 5
SELECT department.department_no, department_name, COUNT(*) as no_of_employees FROM employee
INNER JOIN department ON department.department_no = employee.department_no
GROUP BY department.department_no, department_name
ORDER BY no_of_employees DESC


-- TASK 6
SELECT department.department_no, department_name, SUM(salary) as salary_sum FROM employee
INNER JOIN department ON department.department_no = employee.department_no
GROUP BY department.department_no, department_name
HAVING SUM(salary) =
(SELECT MAX (salary_sum) FROM
	(SELECT SUM(salary) as salary_sum FROM employee
	GROUP BY department_no) as salary_table)
ORDER BY salary_sum DESC
