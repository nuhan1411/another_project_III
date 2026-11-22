-- ========================================================
-- Employee Management System - Database Schema & Initial Data
-- ========================================================

-- 1. Create Database if not exists
CREATE DATABASE IF NOT EXISTS employeemanagement;
USE employeemanagement;

-- 2. Create Login Table
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

-- 3. Create Employee Table
CREATE TABLE IF NOT EXISTS employee (
    empid VARCHAR(20) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    fname VARCHAR(100),
    dob VARCHAR(50),
    salary VARCHAR(50),
    address VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(100),
    education VARCHAR(50),
    designation VARCHAR(100),
    nid VARCHAR(50)
);

-- 4. Seed Data: Default Admin Credentials
-- You can log in with username: admin and password: admin123
INSERT INTO login (username, password)
VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE password = VALUES(password);

-- 5. Seed Data: Sample Employees (Optional)
INSERT INTO employee (empid, name, fname, dob, salary, address, phone, email, education, designation, nid)
VALUES 
    ('100001', 'John Doe', 'Robert Doe', 'Jan 15, 1990', '75000', '123 Tech Street, Silicon Valley', '01711000001', 'john.doe@example.com', 'CSE', 'Software Engineer', '1990123456789'),
    ('100002', 'Sarah Smith', 'Michael Smith', 'Mar 22, 1993', '65000', '456 Innovation Ave, New York', '01711000002', 'sarah.smith@example.com', 'SWE', 'Quality Assurance Lead', '1993987654321')
ON DUPLICATE KEY UPDATE name = VALUES(name);
