# Sports-Management-Using-JDBC
A Java-based sports club management system using JDBC to manage athletes, trainers, and appointments with a MySQL database.      

***
Read me of SQL qurries :
CREATE TABLE athlete(
    -> id INT AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(255) NOT NULL,
    ->  gender VARCHAR(255) NOT NULL);

CREATE TABLE trainers(
    -> id INT  AUTO_INCREMENT PRIMARY KEY,
    -> name VARCHAR(255) NOT NULL,
    -> specialization VARCHAR(255) NOT NULL);

	create TABLE appointments(
    ->  id INT AUTO_INCREMENT PRIMARY KEY,
    -> athlete_id INT NOT NULL,
    -> trainers_id INT NOT NULL,
    -> appointment_date DATE NOT NULL,
    -> FOREIGN KEY(athlete_id) REFERENCES athlete(id),
    -> FOREIGN KEY(trainers_id) REFERENCES trainers(id));

mysql> INSERT INTO trainers(name,specialization) VALUES ("Soham Wankhede", "Kabbadi Trainer");
Query OK, 1 row affected (0.02 sec)

mysql> INSERT INTO trainers(name,specialization) VALUES ("Shreyash Jain" , "Track and Field");
Query OK, 1 row affected (0.01 sec)

mysql> INSERT INTO trainers(name,specialization) VALUES ("Aditya More" , "F1 Driving Trainer");
Query OK, 1 row affected (0.00 sec)

mysql> INSERT INTO trainers(name,specialization) VALUES ("Abbu Thasal" , "Swimming Coach");
Query OK, 1 row affected (0.00 sec)

mysql> INSERT INTO trainers(name,specialization) VALUES ("Mandar Bhobaskar" , "Kho Kho ");
Query OK, 1 row affected (0.00 sec)

mysql> SELECT * FROM trainers;
+----+------------------+--------------------+
| id | name             | specialization     |
+----+------------------+--------------------+
|  1 | Soham Wankhede   | Kabbadi Trainer    |
|  2 | Shreyash Jain    | Track and Field    |
|  3 | Aditya More      | F1 Driving Trainer |
|  4 | Abbu Thasal      | Swimming Coach     |
|  5 | Mandar Bhobaskar | Kho Kho            |
+----+------------------+--------------------+

****
