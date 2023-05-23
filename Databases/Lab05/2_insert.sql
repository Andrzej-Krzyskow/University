--DELETE FROM university;
--DBCC CHECKIDENT('client', RESEED, 0);

USE employee_projects


INSERT INTO client (client_name, address, email, phone, business) VALUES 
('ABC Corporation', '123 Main Street, Anytown USA', 'abc@abc.com',12121212 , 'Retail'),
('XYZ Inc.', '456 High Street, Anytown USA', 'xyz@xyz.com', 6666666, 'Manufacturing'),
('Green Acres', '789 Park Lane, Anytown USA', 'abc@greenacres.com', 7777777, 'Agriculture'),
('Big Fish Games', '321 Waterfront Ave, Seattle WA', 'abc@bigfishgames.com', 1234567, 'Gaming'),
('Tech Solutions LLC', '777 Tech Blvd, San Francisco CA', 'info@techsolutions.com', 9876543, 'Technology'),
('Sunshine Corporation', '4321 Beach Blvd, Miami FL', 'abc@sunshinecorp.com', 5551212, 'Hospitality'),
('AutoZone', '2345 Main Street, Memphis TN', 'info@autozone.com', 9011234, 'Automotive'),
('Global Marketing Inc.', '567 5th Avenue, New York NY', 'xyz@globalmarketing.com', 21255512, 'Marketing'),
('ABC Financial Services', '111 Wall St, New York NY', 'info@abcfinserv.com', 21255555, 'Finance'),
('Westward Leasing', '789 Sunset Blvd, Los Angeles CA', 'xyz@westwardleasing.com', 31055543, 'Leasing'),
('Johnson & Johnson', '1234 Pharma Way, New Brunswick NJ', 'info@jnj.com', 7325555, 'Pharmaceuticals'),
('Dream Builders', '9876 Construction Blvd, Dallas TX', 'abc@dreambuilders.com', 21455512, 'Construction'),
('Beachside Vacation Rentals', '555 Beachfront Rd, San Diego CA', 'xyz@vacationrentals.com', 6195551, 'Hospitality'),
('Acme Plumbing', '7777 Main St, Atlanta GA', 'xyz@acmeplumbing.com', 40455543, 'Plumbing'),
('La Belle Salon', '888 Beauty Lane, Beverly Hills CA', 'info@labellesalon.com', 31055555, 'Beauty');

INSERT INTO client (client_name, address, email, business) VALUES 
('Blue Ridge Holdings', '345 Mountain View Dr, Asheville NC', 'abc@blueridgeholdings.com',  'Real Estate'),
('Sustainable Energy Group', '5432 Park Ave, Denver CO', 'abc@group.com',  'Energy'),
('Sweet Tooth Bakery', '2345 Baker Street, New Orleans LA', 'xyz@sweettoothbakery.com',  'Bakery'),
('North Star Consulting', '4321 Consulting Blvd, Boston MA', 'info@consulting.com',  'Consulting'),
('Fresh Farms', '4567 Farmhouse Rd, Kansas City MO', 'xyz@freshfarms.com',  'Agriculture');



INSERT INTO project (description, start_date, planned_end_date, actual_end_date, budget, client_id) VALUES 
('Website redesign', '2022-01-01', '2022-02-28', '2022-03-15', 5000, 1),
('Marketing campaign', '2022-03-01', '2022-06-30', '2022-07-15', 10000, 1),
('Product launch', '2022-05-01', '2022-07-31', NULL, 15000, 2),
('Software development', '2022-06-01', '2022-09-30', NULL, 20000, 3),
('Event planning', '2022-08-01', '2022-09-30', '2022-10-15', 8000, 4),
('Social media strategy', '2022-09-01', '2022-11-30', NULL, 12000, 5),
('Content creation', '2022-10-01', '2022-12-31', NULL, 9000, 6),
('Brand identity design', '2022-11-01', '2023-01-31', NULL, 15000, 7),
('Market research', '2023-01-01', '2023-04-30', NULL, 18000, 8),
('Employee training', '2023-02-01', '2023-03-31', '2023-04-15', 5000, 9),
('Website development', '2023-03-01', '2023-06-30', '2023-08-31', 25000, 10),
('E-commerce platform', '2023-04-01', '2023-09-30', NULL, 30000, 11),
('Product design', '2023-05-01', '2023-08-31', NULL, 20000, 12),
('Graphic design', '2023-06-01', '2023-09-30', NULL, 12000, 13),
('Video production', '2023-07-01', '2023-08-31', '2023-11-30', 8000, 14),
('Mobile app development', '2023-08-01', '2023-11-30', NULL, 35000, 15),
('SEO optimization', '2023-09-01', '2024-01-31', NULL, 15000, 16),
('Print advertising', '2023-10-01', '2024-02-29', NULL, 10000, 17),
('Event sponsorship', '2023-11-01', '2024-03-31', NULL, 20000, 18),
('Public relations', '2023-12-01', '2024-05-31', NULL, 25000, 19),
('New product launch campaign', '2023-06-15', '2023-09-30', NULL, 40000, 7),
('Corporate sustainability initiative', '2022-09-01', '2022-12-31', NULL, 100000, 20),
('Expansion of retail store chain', '2023-01-01', '2023-08-31', NULL, 75000, 20),
('ERP system implementation', '2022-07-01', '2022-10-31', '2023-06-30', 120000, 5),
('Mobile app development', '2023-02-01', '2023-06-30', '2023-08-31', 60000, 14);



INSERT INTO department (department_name) VALUES
('Retail'),
('Manufacturing'),
('Agriculture'),
('Gaming'),
('Technology'),
('Hospitality'),
('Automotive'),
('Marketing'),
('Finance'),
('Leasing'),
('Pharmaceuticals'),
('Construction'),
('Plumbing'),
('Beauty'),
('Sales'),
('Human Resources'),
('Finance'),
('Engineering');


INSERT INTO employee (employee_name, job, salary, department_no) VALUES
('John Doe', 'Marketing Manager', 5500, 8),
('Jane Smith', 'Marketing Specialist', 3500, 8),
('Peter Parker', 'Sales Manager', 6000, 15),
('Mary Johnson', 'Sales Representative', 4000, 15),
('David Lee', 'HR Manager', 6500,  16),
('Jessica Brown', 'HR Assistant', 2500,  16),
('Richard Wilson', 'Finance Director', 8000,  9),
('Oliver Davis', 'Financial Analyst', 4500,  9),
('Michael Chen', 'Software Engineer', 7000, 18),
('Emily Kim', 'System Analyst', 5500,  5),
('Christopher Taylor', 'Farm Manager', 7500,  3),
('Sophie Baker', 'Game Designer', 6000,  4),
('William Adams', 'Hotel Manager', 8500,  6),
('Amanda Young', 'Auto Mechanic', 5000,  7),
('Daniel Harris', 'Marketing Coordinator', 6800,  8),
('Lucas Wright', 'Accountant', 3800,  9),
('Olivia Green', 'Leasing Agent', 7200,  10),
('Victoria Allen', 'Clinical Trial Manager', 4200,  11),
('Ethan Turner', 'Construction Manager', 3200, 12),
('Isabella Baker', 'Plumber', 5000,  13);

INSERT INTO employee_project_task (project_id, employee_no, start_date, end_date, task, status) VALUES
(1, 1, '2022-01-01', '2022-01-15', 'Research', 'In Progress'),
(1, 2, '2022-01-05', '2022-02-05', 'Design', 'Completed'),
(1, 3, '2022-01-15', '2022-02-15', 'Development', 'In Progress'),
(2, 4, '2022-02-01', '2022-02-28', 'Testing', 'Completed'),
(2, 5, '2022-02-15', '2022-03-15', 'Deployment', 'In Progress'),
(2, 6, '2022-03-01', '2022-03-31', 'Maintenance', 'Not Started'),
(3, 7, '2022-04-01', '2022-04-30', 'Research', 'Not Started'),
(3, 8, '2022-04-15', '2022-05-15', 'Design', 'Not Started'),
(3, 9, '2022-05-01', '2022-06-01', 'Development', 'Not Started'),
(4, 10, '2022-06-01', '2022-06-30', 'Testing', 'Not Started'),
(4, 11, '2022-06-15', '2022-07-15', 'Deployment', 'Not Started'),
(4, 12, '2022-07-01', '2022-07-31', 'Maintenance', 'Not Started'),
(5, 13, '2022-08-01', '2022-08-31', 'Research', 'Not Started'),
(5, 14, '2022-08-15', '2022-09-15', 'Design', 'Not Started'),
(5, 15, '2022-09-01', '2022-10-01', 'Development', 'Not Started'),
(6, 16, '2022-10-01', '2022-10-31', 'Testing', 'Not Started'),
(6, 17, '2022-10-15', '2022-11-15', 'Deployment', 'Not Started'),
(6, 18, '2022-11-01', '2022-11-30', 'Maintenance', 'Not Started'),
(7, 19, '2023-01-01', '2023-01-31', 'Research', 'Not Started'),
(7, 20, '2023-01-15', '2023-02-15', 'Design', 'Not Started');
