-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookingorders`
--

DROP TABLE IF EXISTS `bookingorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingorders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `uid` int(11) NOT NULL,
  `roomType` varchar(20) NOT NULL,
  `extraBed` int(11) NOT NULL,
  `numRooms` int(11) DEFAULT NULL,
  `bookingDate` date DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `referenceID` varchar(40) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`uid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `idBookingOrderbookingordersUser` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingorders`
--

LOCK TABLES `bookingorders` WRITE;
/*!40000 ALTER TABLE `bookingorders` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookingorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `uid` int(11) NOT NULL,
  `roomType` varchar(20) NOT NULL,
  `extraBed` int(11) NOT NULL,
  `numRooms` int(11) DEFAULT NULL,
  `bookingDate` date DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `referenceID` varchar(40) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`uid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `idBookingUser` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (29,'2016-05-16','2016-05-17',1,'Twin',1,2,'2016-05-17','Sydney','7r46t2lc1avi90vmomeojc6i4q','01331');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preview` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomrates`
--

DROP TABLE IF EXISTS `roomrates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomrates` (
  `rate` double DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `roomType` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomrates`
--

LOCK TABLES `roomrates` WRITE;
/*!40000 ALTER TABLE `roomrates` DISABLE KEYS */;
INSERT INTO `roomrates` VALUES (2,'2016-05-16','2016-05-17','Sydney','Twin');
/*!40000 ALTER TABLE `roomrates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `roomType` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,80,1,'1',NULL),(2,80,NULL,'Single','Brisbane'),(3,80,NULL,'Single','Brisbane'),(4,80,NULL,'Single','Brisbane'),(5,80,NULL,'Single','Brisbane'),(6,130,NULL,'Twin','Brisbane'),(7,130,NULL,'Twin','Brisbane'),(8,130,NULL,'Twin','Brisbane'),(9,80,NULL,'Single','Sydney'),(10,80,NULL,'Single','Sydney'),(11,80,NULL,'Single','Sydney'),(12,80,NULL,'Single','Sydney'),(13,130,NULL,'Twin','Sydney'),(14,130,NULL,'Twin','Sydney'),(15,130,NULL,'Twin','Sydney'),(16,150,NULL,'Queen','Canberra');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `nickName` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `cardNumber` varchar(16) DEFAULT NULL,
  `cardName` varchar(30) DEFAULT NULL,
  `cardExpire` varchar(30) DEFAULT NULL,
  `cvc` varchar(5) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kngx286','hasaki','Kenneth','Ng','Ken','B06','kenneth.s.k.ng@gmail.com','999','Ken','999','999',NULL),(2,'kngx286','hasaki',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'dingus','hasaki','Kennyq','G','ayy','','wasuremono.s@gmail.com','','','','',NULL),(4,'dingus2','hasaki',NULL,NULL,NULL,NULL,'wasuremono.s@gmail.com',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userspending`
--

DROP TABLE IF EXISTS `userspending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userspending` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `verification` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userspending`
--

LOCK TABLES `userspending` WRITE;
/*!40000 ALTER TABLE `userspending` DISABLE KEYS */;
INSERT INTO `userspending` VALUES (5,'knxg286','asdasdasdaslkjdh','test@test.com','eotab0mia4cp01ismat8nc2nai'),(6,'knxg286','asdasdasdaslkjdh','test@test.com','7dm592nrk4gn092mit91gl8mpr'),(7,'knxg286','fasdfsadfs','test@test.com','f7da2ogeip44btsfv2vnlhucda'),(8,'knxg286','skljahvdkjadhv','test@test.com','nv7ubfjcml3n093pgiattdviuf'),(9,'kngs286','sldkjghlk','test@test.com','rj2grq29f03uuo1b0t283vku3i'),(10,'kngx296','hasaki','test@test.com','magl6ngf4oj5s6ftl1tv26g083'),(11,'kngx296','hasaki','test@test.com','90tl0r5sc3vvodiqqsvdoso4dd');
/*!40000 ALTER TABLE `userspending` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-18  8:29:22
