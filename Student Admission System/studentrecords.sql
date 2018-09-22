-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2018 at 10:34 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentrecords`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_credentials`
--

CREATE TABLE `admin_credentials` (
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_credentials`
--

INSERT INTO `admin_credentials` (`username`, `password`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3');

-- --------------------------------------------------------

--
-- Table structure for table `fee`
--

CREATE TABLE `fee` (
  `uniqueID` int(11) DEFAULT NULL,
  `amount` int(15) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`uniqueID`, `amount`, `payment_date`, `status`) VALUES
(1113, 9436, '2018-09-23', 'P'),
(2112, 9436, NULL, 'NP');

-- --------------------------------------------------------

--
-- Table structure for table `records`
--

CREATE TABLE `records` (
  `fname` varchar(30) DEFAULT NULL,
  `lname` varchar(30) DEFAULT NULL,
  `emailID` varchar(45) DEFAULT NULL,
  `dept` varchar(5) DEFAULT NULL,
  `admissionYear` int(11) DEFAULT NULL,
  `engineeringYear` varchar(5) DEFAULT NULL,
  `uniqueID` int(11) NOT NULL,
  `phoneNo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `records`
--

INSERT INTO `records` (`fname`, `lname`, `emailID`, `dept`, `admissionYear`, `engineeringYear`, `uniqueID`, `phoneNo`) VALUES
('Saurabh', 'Bora', 'saurabhbora@gmail.com', 'CS', 2014, 'FE', 1113, 1234567890),
('abcdef', 'xyz', 'abc@gmail.com', 'CS', 2016, 'SE', 2112, 1243567451);

-- --------------------------------------------------------

--
-- Table structure for table `student_credentials`
--

CREATE TABLE `student_credentials` (
  `uniqueID` int(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_credentials`
--

INSERT INTO `student_credentials` (`uniqueID`, `username`, `password`) VALUES
(1113, 'ssb', 'e10adc3949ba59abbe56e057f20f883e'),
(2112, 'abc', 'e10adc3949ba59abbe56e057f20f883e');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD KEY `fk_fee` (`uniqueID`);

--
-- Indexes for table `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`uniqueID`);

--
-- Indexes for table `student_credentials`
--
ALTER TABLE `student_credentials`
  ADD KEY `uniqueID` (`uniqueID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fee`
--
ALTER TABLE `fee`
  ADD CONSTRAINT `fk_fee` FOREIGN KEY (`uniqueID`) REFERENCES `records` (`uniqueID`) ON DELETE CASCADE;

--
-- Constraints for table `student_credentials`
--
ALTER TABLE `student_credentials`
  ADD CONSTRAINT `student_credentials_ibfk_1` FOREIGN KEY (`uniqueID`) REFERENCES `records` (`uniqueID`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
