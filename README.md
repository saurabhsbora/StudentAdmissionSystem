# StudentAdmissionSystem
GUI based Student Admission System using Java.  
Concepts involved : *Swings (GUI), Multithreading, Client-Server Model, JDBC connectivity, Socket Programming, Collection & Generics.*  
![Home Screen](https://raw.githubusercontent.com/enthussb/StudentAdmissionSystem/master/Student%20Admission%20System/screenshots/Home.png)

## Functionalities
- Student Registration & Authentication
- Student Dashboard
  - Student Profile
  - Fee Report
  - Update Details
- Admin Dashboard
  - Display all records in tabular format
  - Real time searching on all fields
  - Sorting on all fields
  - Update any particular record
  - Export records in csv format
- MD5 hashing to store passwords securely

## Development Environment
- JDK 8
- Mysql Server
- Eclipse IDE with WindowBuilder
- Required JARs
  - mysql-connector-java-5.1.46
  - jgoodies-forms-1.8.0
  - jgoodies-forms-1.8.0-sources
  - jgoodies-common-1.8.0

## Database Schema
```
CREATE TABLE `admin_credentials` (
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(35) DEFAULT NULL
)

CREATE TABLE `fee` (
  `uniqueID` int(11) DEFAULT NULL,
  `amount` int(15) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  FOREIGN KEY (uniqueID) REFERENCES student_credentials(uniqueID) ON DELETE CASCADE
)

CREATE TABLE `records` (
  `fname` varchar(30) DEFAULT NULL,
  `lname` varchar(30) DEFAULT NULL,
  `emailID` varchar(45) DEFAULT NULL,
  `dept` varchar(5) DEFAULT NULL,
  `admissionYear` int(11) DEFAULT NULL,
  `engineeringYear` varchar(5) DEFAULT NULL,
  `uniqueID` int(11) NOT NULL,
  `phoneNo` bigint(20) DEFAULT NULL,
  FOREIGN KEY (uniqueID) REFERENCES student_credentials(uniqueID) ON DELETE CASCADE
)

CREATE TABLE `student_credentials` (
  `uniqueID` int(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`uniqueID`)
)
```

## How to Run this application
1. Clone this repository
2. Configure the environment for this application
3. Navigate to `Student Admission System/src/com/sdl/StudentAdmission/`
4. Run the `Server.java` to start the server and start listening to clients
5. Run the `StudentAdmissionPortal.java` to initialize a client and start the application

