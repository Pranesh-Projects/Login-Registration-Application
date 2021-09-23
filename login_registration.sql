# to create table 'user'
CREATE TABLE user(
    user_id INT AUTO_INCREMENT PRIMARY KEY ,
    first_name VARCHAR(20) NOT NULL ,
    last_name VARCHAR(20),
    email VARCHAR(20) NOT NULL ,
    password VARCHAR(20) NOT NULL
);

# to create table 'role'
CREATE TABLE role(
    role_id INT AUTO_INCREMENT PRIMARY KEY ,
    role_name VARCHAR(20) NOT NULL
);

# to create table 'users_roles'
CREATE TABLE users_roles(
    user_id INT,
    role_id INT
);

ALTER TABLE role RENAME COLUMN role_id TO id;
ALTER TABLE user RENAME COLUMN user_id TO id;