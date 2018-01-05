CREATE DATABASE  IF NOT EXISTS `COMP3095` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `COMP3095`;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `token` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,NULL,NULL,'admin@domain.ca',NULL,'admin','admin','admin:3bafafaa3eeb36ec');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DEPARTMENTS`
--

DROP TABLE IF EXISTS `DEPARTMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPARTMENTS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DEPARTMENTS`
--

LOCK TABLES `DEPARTMENTS` WRITE;
/*!40000 ALTER TABLE `DEPARTMENTS` DISABLE KEYS */;
INSERT INTO `DEPARTMENTS` VALUES (1,'Marketing','Building E, Floor 56'),(2,'Planning','Building C, Floor 65'),(3,'IT','Building D, Floor 23'),(4,'Application Development','South Bulding E, Floor 15'),(5,'Support','North Building, Floor 44'),(6,'Financing','Building Z, Floor 44'),(15,'Toronto Department','Toronto, York Street');
/*!40000 ALTER TABLE `DEPARTMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSITIONS`
--

DROP TABLE IF EXISTS `POSITIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSITIONS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `departmentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `positions_departmentID_idx` (`departmentID`),
  CONSTRAINT `positions_departmentID` FOREIGN KEY (`departmentID`) REFERENCES `DEPARTMENTS` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSITIONS`
--

LOCK TABLES `POSITIONS` WRITE;
/*!40000 ALTER TABLE `POSITIONS` DISABLE KEYS */;
INSERT INTO `POSITIONS` VALUES (1,'Analyst',2),(2,'Administrative Assitant',5),(3,'Sales',1),(4,'Department Manager',5),(5,'Department Staff',3),(6,'Web Developer',4),(7,'Mobile Developer',4),(8,'Project Manager',3),(9,'Chief',15);
/*!40000 ALTER TABLE `POSITIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `hireYear` int(4) DEFAULT NULL,
  `jobPositionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `employee_jobPositionID_idx` (`jobPositionID`),
  CONSTRAINT `employee_jobPositionID` FOREIGN KEY (`jobPositionID`) REFERENCES `POSITIONS` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE`
--

LOCK TABLES `EMPLOYEE` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE` DISABLE KEYS */;
INSERT INTO `EMPLOYEE` VALUES (1,'Malik','Yavari','mr.yavari@mail.ru',4243,2012,7),(2,'Maxim','Bond','maximbond@gmail.com',9112,2013,6),(3,'John','Kennedy','johnkennedy@georgebrown.ca',123,2011,8),(4,'Vladimir','Putin','vladimirputin@mail.ru',111,2010,3),(6,'Donald','Trump','don.trumo@gmail.com',112,2010,2),(10,'Jon','Jones','jon.jones@gmail.com',2231,2010,9);
/*!40000 ALTER TABLE `EMPLOYEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ATTENDANCE_DATE`
--

DROP TABLE IF EXISTS `ATTENDANCE_DATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATTENDANCE_DATE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date_UNIQUE` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATTENDANCE_DATE`
--

LOCK TABLES `ATTENDANCE_DATE` WRITE;
/*!40000 ALTER TABLE `ATTENDANCE_DATE` DISABLE KEYS */;
INSERT INTO `ATTENDANCE_DATE` VALUES (15,'2017-12-08'),(14,'2017-12-13'),(11,'2018-01-01'),(13,'2018-01-02'),(16,'2018-01-03'),(12,'2018-01-04');
/*!40000 ALTER TABLE `ATTENDANCE_DATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ATTENDANCE_EMP`
--

DROP TABLE IF EXISTS `ATTENDANCE_EMP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ATTENDANCE_EMP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attDateID` int(11) DEFAULT NULL,
  `empID` int(11) DEFAULT NULL,
  `present` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `attendance_emp_empID_idx` (`attDateID`,`empID`),
  KEY `attendance_emp_empID_idx1` (`empID`),
  CONSTRAINT `attendance_emp_attDateID` FOREIGN KEY (`attDateID`) REFERENCES `ATTENDANCE_DATE` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `attendance_emp_empID` FOREIGN KEY (`empID`) REFERENCES `EMPLOYEE` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ATTENDANCE_EMP`
--

LOCK TABLES `ATTENDANCE_EMP` WRITE;
/*!40000 ALTER TABLE `ATTENDANCE_EMP` DISABLE KEYS */;
INSERT INTO `ATTENDANCE_EMP` VALUES (3,13,1,'\0'),(4,13,2,''),(5,13,3,''),(6,13,4,'\0'),(7,13,6,'\0'),(8,14,1,'\0'),(9,14,2,''),(10,15,6,''),(11,16,10,'\0'),(12,16,10,''),(13,16,10,'\0');
/*!40000 ALTER TABLE `ATTENDANCE_EMP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUP_T`
--

DROP TABLE IF EXISTS `GROUP_T`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUP_T` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `departmentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group_departmentID_idx` (`departmentID`),
  CONSTRAINT `group_departmentID` FOREIGN KEY (`departmentID`) REFERENCES `DEPARTMENTS` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUP_T`
--

LOCK TABLES `GROUP_T` WRITE;
/*!40000 ALTER TABLE `GROUP_T` DISABLE KEYS */;
INSERT INTO `GROUP_T` VALUES (1,'First Group',1),(2,'IT Assistants',3),(3,'Malikas Group',4),(4,'MaxMalik Group',4),(17,'My Test Group',4),(18,'My Group',4),(19,'MyGroupGBC',4),(20,'Chief Group',15);
/*!40000 ALTER TABLE `GROUP_T` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUP_EMP`
--

DROP TABLE IF EXISTS `GROUP_EMP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUP_EMP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupID` int(11) DEFAULT NULL,
  `empID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group_emp_groupID_idx` (`groupID`,`empID`),
  KEY `group_emp_empID_idx` (`empID`),
  CONSTRAINT `group_emp_empID` FOREIGN KEY (`empID`) REFERENCES `EMPLOYEE` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_emp_groupID` FOREIGN KEY (`groupID`) REFERENCES `GROUP_T` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUP_EMP`
--

LOCK TABLES `GROUP_EMP` WRITE;
/*!40000 ALTER TABLE `GROUP_EMP` DISABLE KEYS */;
INSERT INTO `GROUP_EMP` VALUES (1,1,4),(2,2,3),(3,3,1),(5,4,1),(4,4,2),(29,17,1),(28,17,2),(31,18,1),(30,18,2),(32,19,1),(33,19,2),(34,20,10);
/*!40000 ALTER TABLE `GROUP_EMP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_TEMP`
--

DROP TABLE IF EXISTS `REPORT_TEMP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT_TEMP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `departmentID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `report_temp_depID_idx` (`departmentID`),
  CONSTRAINT `report_temp_depID` FOREIGN KEY (`departmentID`) REFERENCES `DEPARTMENTS` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_TEMP`
--

LOCK TABLES `REPORT_TEMP` WRITE;
/*!40000 ALTER TABLE `REPORT_TEMP` DISABLE KEYS */;
INSERT INTO `REPORT_TEMP` VALUES (7,'Fall Report 2017',4);
/*!40000 ALTER TABLE `REPORT_TEMP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_SECT`
--

DROP TABLE IF EXISTS `REPORT_SECT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT_SECT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `reportID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `report_sect_reportID_idx` (`reportID`),
  CONSTRAINT `report_sect_reportID` FOREIGN KEY (`reportID`) REFERENCES `REPORT_TEMP` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_SECT`
--

LOCK TABLES `REPORT_SECT` WRITE;
/*!40000 ALTER TABLE `REPORT_SECT` DISABLE KEYS */;
INSERT INTO `REPORT_SECT` VALUES (8,'Contribution',7),(9,'Responsibility',7),(10,'Value',7);
/*!40000 ALTER TABLE `REPORT_SECT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_CRITERIA`
--

DROP TABLE IF EXISTS `REPORT_CRITERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT_CRITERIA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `maxValue` int(11) DEFAULT NULL,
  `sectionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `report_criteria_sectID_idx` (`sectionID`),
  CONSTRAINT `report_criteria_sectID` FOREIGN KEY (`sectionID`) REFERENCES `REPORT_SECT` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_CRITERIA`
--

LOCK TABLES `REPORT_CRITERIA` WRITE;
/*!40000 ALTER TABLE `REPORT_CRITERIA` DISABLE KEYS */;
INSERT INTO `REPORT_CRITERIA` VALUES (10,'Research and Gathering',3,8),(11,'Sharing Information',5,8),(12,'Using Time Wisely',5,8),(13,'Ready to Work',5,8),(14,'Fullfil Teams Role',4,9),(15,'Sharing Work Equally',2,9),(16,'Helping Team Members',5,9),(17,'Listen to Others',5,10),(18,'Include Teammates',5,10),(19,'Make Fair Decision',5,10);
/*!40000 ALTER TABLE `REPORT_CRITERIA` ENABLE KEYS */;
UNLOCK TABLES;
