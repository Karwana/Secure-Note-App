-- Secure Note App - Database Schema

DROP DATABASE IF EXISTS secure_notes;
CREATE DATABASE secure_notes;
USE secure_notes;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notes (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       title VARCHAR(50) NOT NULL,
                       content TEXT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- To set a user as admin, run this after creating the account:
-- UPDATE users SET role = 'ADMIN' WHERE username = 'your_username';