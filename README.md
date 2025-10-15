# Library Management System (JDBC + Java + MySQL)

A simple console-based Library Management System using Java and JDBC.

## Features
- Add new books  
- Display books  
- Update or delete books
- Delete all books  
- Connects to MySQL database

## Database Setup
Run these SQL commands in MySQL:

```sql
create database librarydb;
use librarydb;

create table books (
    book_id int auto_increment primary key,
    title varchar(100) not null,
    author varchar(100) not null,
    quantity int not null
);
