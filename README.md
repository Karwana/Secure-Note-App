# Secure Note App

A console-based application for secure note management with user authentication and role-based access control.

## Features
- User registration and login with BCrypt password hashing
- Create, view, edit and delete notes (CRUD)
- Role-based access control (USER / ADMIN)
- Admin panel for managing all notes
- Database credentials managed via environment variables (dotenv)

## Tech Stack
- Java
- MySQL
- JDBC
- BCrypt
- dotenv

## Setup
1. Clone the repository
2. Create a `.env` file in the root directory with the following: 
DB_URL=jdbc:mysql://localhost:3306/your_database
DB_USER=your_username
DB_PASSWORD=your_password
3. Run `schema.sql` in your MySQL client to create the required tables
4. Build and run the project
