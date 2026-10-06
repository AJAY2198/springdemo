CREATE DATABASE springdemo;

Use springdemo;
CREATE TABLE students (
    id   INTEGER PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    age integer
);

INSERT INTO students (id, first_name, last_name, age) VALUES
(1, 'Ajay','Akole',28),
(2, 'Amita','Akole',28),
(3, 'Vilas','Akole',28),
(4, 'Nilima','Akole',28);


select * from students;