-- MySQL dump 10.13  Distrib 5.5.27, for Win64 (x86)
--
-- Host: localhost    Database: dilshan_marksys_backup
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Current Database: `dilshan_marksys_backup`
--

/*!40000 DROP DATABASE IF EXISTS `dilshan_marksys_backup`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dilshan_marksys_backup` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `dilshan_marksys_backup`;

--
-- Table structure for table `arpicodaily_detail`
--

DROP TABLE IF EXISTS `arpicodaily_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arpicodaily_detail` (
  `daily_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_code` varchar(255) NOT NULL,
  `daily_name` varchar(255) NOT NULL,
  `location_address` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `category_code` varchar(255) NOT NULL,
  `transaction_date` varchar(225) NOT NULL,
  `create_date` varchar(225) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  PRIMARY KEY (`daily_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arpicodaily_detail`
--

LOCK TABLES `arpicodaily_detail` WRITE;
/*!40000 ALTER TABLE `arpicodaily_detail` DISABLE KEYS */;
INSERT INTO `arpicodaily_detail` VALUES (1,'MA001','Maharagama','No32/A, Awissawella Rd, Maharagama',112565565,'AD001','2018-06-15','2023-06-15','192.168.1.1'),(2,'NU001','Nugegoda','No45, Kohuwala Rd, Nugegoda',112525525,'AD001','2022-06-18','2024-06-24','192.168.1.2'),(4,'KO001','Kottawa','No13/A/1, Borella Rd, Kottawa',112585585,'AD001','2022-06-16','2022-06-26','192.168.1.4');
/*!40000 ALTER TABLE `arpicodaily_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `backup_table`
--

DROP TABLE IF EXISTS `backup_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `backup_table` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) NOT NULL,
  `backup_file_path` varchar(1000) DEFAULT NULL,
  `backuped_date_time` varchar(255) DEFAULT NULL,
  `first_col_name` varchar(255) DEFAULT NULL,
  `second_col_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backup_table`
--

LOCK TABLES `backup_table` WRITE;
/*!40000 ALTER TABLE `backup_table` DISABLE KEYS */;
INSERT INTO `backup_table` VALUES (1,'arpicodaily_detail',NULL,NULL,'transaction_date','create_date'),(2,'showroom_detail',NULL,NULL,NULL,'create_date'),(3,'supercenter_detail',NULL,NULL,'transaction_date',NULL),(4,'furniture_detail',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `backup_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_data`
--

DROP TABLE IF EXISTS `department_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_data` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_code` varchar(255) NOT NULL,
  `category_code` varchar(255) NOT NULL,
  `depatyment_name` varchar(255) NOT NULL,
  `department_location` varchar(255) NOT NULL,
  `department_contact` int(10) NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_data`
--

LOCK TABLES `department_data` WRITE;
/*!40000 ALTER TABLE `department_data` DISABLE KEYS */;
INSERT INTO `department_data` VALUES (1,'IT101','DEV001','IT Development','Navinna',112321321),(2,'IT101','IMP001','IT Implementation','Navinna',112369369),(3,'IT101','IS001','IT Information System','Navinna',112357357),(4,'IN101','LI001','Life Insurance','Hidepark',112654654),(5,'IN101','PI001','Property Insurance','Hidepark',112621621),(6,'IN101','VI001','Vehicle Insurance','Hidepark',112687687);
/*!40000 ALTER TABLE `department_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_data`
--

DROP TABLE IF EXISTS `employee_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_data` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `personal_address` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `nic_number` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `join_date` varchar(255) NOT NULL,
  `employee_number` varchar(255) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_data`
--

LOCK TABLES `employee_data` WRITE;
/*!40000 ALTER TABLE `employee_data` DISABLE KEYS */;
INSERT INTO `employee_data` VALUES (1,'Mary R Weems','King Of Prussia, Pennsylvania(PA), 19406',775405230,'610-491-3379','Human Resource','1971-10-13','3459'),(2,'Virginia P Dawkins','Little Rock, Arkansas(AR), 72205',775251382,'501-405-8659','Furniture Department','1973-02-03','3268'),(3,'Sheree D Herndon','Los Angeles, California(CA), 90017',715020013,'951-790-5120','Arpidac','1957-02-13','4473'),(4,'William B Castro','Bay City, Michigan(MI), 48708',785455220,'989-892-4887','Supercenter','1991-14-24','2037'),(5,'Patrick C Redwine','South Bend, Indiana(IN), 46625',710147250,'574-914-0490','Information Department','-1977-06-24','3760'),(6,'Eric H Diaz','Cordelia, California(CA), 94533',705895870,'707-863-1960','Security Service','1963-08-10','1330');
/*!40000 ALTER TABLE `employee_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `furniture_detail`
--

DROP TABLE IF EXISTS `furniture_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `furniture_detail` (
  `furniture_id` int(11) NOT NULL AUTO_INCREMENT,
  `furniture_code` varchar(255) NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `furniture_center_address` varchar(255) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `category_code` varchar(255) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  PRIMARY KEY (`furniture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `furniture_detail`
--

LOCK TABLES `furniture_detail` WRITE;
/*!40000 ALTER TABLE `furniture_detail` DISABLE KEYS */;
INSERT INTO `furniture_detail` VALUES (1,'MA001','Matara','No10/05, Hakmana Rd, Matara','0415885885','FU001','192.168.2.1'),(2,'GA001','Galle','No03/C/02, Kaluwella Rd, Galle','0917887887','FU001','192.168.2.2'),(3,'WE001','Weligama','No36/2/A, Galle Rd, Weligama','0415665665','FU001','192.168.2.3'),(4,'HA001','Hakmana','No14/C/03, Beliatta Rd, Hakmana. 2022','0478568568','FU001','192.168.2.4');
/*!40000 ALTER TABLE `furniture_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameters`
--

DROP TABLE IF EXISTS `parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameters` (
  `parameter_id` int(11) NOT NULL AUTO_INCREMENT,
  `backup_years` int(11) NOT NULL,
  `db_name` varchar(255) NOT NULL,
  `db_username` varchar(255) NOT NULL,
  `db_password` varchar(255) NOT NULL,
  `backup_file_path` varchar(255) NOT NULL,
  `backup_file_name` varchar(255) NOT NULL,
  PRIMARY KEY (`parameter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameters`
--

LOCK TABLES `parameters` WRITE;
/*!40000 ALTER TABLE `parameters` DISABLE KEYS */;
INSERT INTO `parameters` VALUES (1,5,'dilshan_marksys_backup','root','root','latest_backup_file','marksys_backup');
/*!40000 ALTER TABLE `parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_log`
--

DROP TABLE IF EXISTS `report_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_date` varchar(255) NOT NULL,
  `timestamp` varchar(255) NOT NULL,
  `category` varchar(500) NOT NULL,
  `description` varchar(5000) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_log`
--

LOCK TABLES `report_log` WRITE;
/*!40000 ALTER TABLE `report_log` DISABLE KEYS */;
INSERT INTO `report_log` VALUES (1,'2022-06-22','2022-06-22 15:12:46','backup table','null null found as the filter date'),(2,'2022-06-22','2022-06-22 15:12:46','backup table','null null found as the filter date'),(3,'2022-06-22','2022-06-22 15:12:46','backup table','null null found as the filter date'),(4,'2022-06-22','2022-06-22 15:12:46','backup table','null null found as the filter date'),(5,'2022-06-22','2022-06-22 15:12:46','date filter','between 2022-06-22 and 2019-01-01 have to backup the database'),(6,'2022-06-22','2022-06-22 15:12:48','backup','Database backup successful!'),(7,'2022-06-22','2022-06-22 15:12:51','delete','furniture_detail have not enough data to filter the date!'),(8,'2022-06-22','2022-06-22 15:12:52','delete','3 years data has been deleted from ?arpicodaily_detail? tables!'),(9,'2022-06-22','2022-06-22 15:12:57','delete','3 years data has been deleted from ?showroom_detail? tables!'),(10,'2022-06-22','2022-06-22 15:13:00','delete','3 years data has been deleted from ?supercenter_detail? tables!'),(11,'2022-06-23','2022-06-23 08:27:00','backup table','null null found as the filter date'),(12,'2022-06-23','2022-06-23 08:27:00','backup table','null null found as the filter date'),(13,'2022-06-23','2022-06-23 08:27:00','backup table','null null found as the filter date'),(14,'2022-06-23','2022-06-23 08:27:00','backup table','null null found as the filter date'),(15,'2022-06-23','2022-06-23 08:29:06','backup table','null null found as the filter date'),(16,'2022-06-23','2022-06-23 08:29:06','backup table','null null found as the filter date'),(17,'2022-06-23','2022-06-23 08:29:06','backup table','null null found as the filter date'),(18,'2022-06-23','2022-06-23 08:29:06','backup table','null null found as the filter date'),(19,'2022-06-23','2022-06-23 08:29:14','date filter','Data delete year count was less than 3. User did not agree to set it to 3 years. Then terminate the programme.'),(20,'2022-06-23','2022-06-23 08:31:01','backup table','null null found as the filter date'),(21,'2022-06-23','2022-06-23 08:31:01','backup table','null null found as the filter date'),(22,'2022-06-23','2022-06-23 08:31:01','backup table','null null found as the filter date'),(23,'2022-06-23','2022-06-23 08:31:01','backup table','null null found as the filter date'),(24,'2022-06-23','2022-06-23 08:31:04','date filter','Data delete year count was less than 3. User did not agree to set it to 3 years. Then terminate the programme.'),(25,'2022-06-23','2022-06-23 08:31:54','backup table','null null found as the filter date'),(26,'2022-06-23','2022-06-23 08:31:54','backup table','null null found as the filter date'),(27,'2022-06-23','2022-06-23 08:31:54','backup table','null null found as the filter date'),(28,'2022-06-23','2022-06-23 08:31:54','backup table','null null found as the filter date'),(29,'2022-06-23','2022-06-23 08:32:08','date filter','Data delete year count was less than 3. User did not agree to set it to 3 years. Then terminate the programme.'),(30,'2022-06-23','2022-06-23 08:32:54','backup table','null null found as the filter date'),(31,'2022-06-23','2022-06-23 08:32:54','backup table','null null found as the filter date'),(32,'2022-06-23','2022-06-23 08:32:54','backup table','null null found as the filter date'),(33,'2022-06-23','2022-06-23 08:32:54','backup table','null null found as the filter date'),(34,'2022-06-23','2022-06-23 08:33:00','date filter','Data backup and delete year count was less than 3. User did not agree to set it to 3 years. Then terminate the programme.'),(35,'2022-06-23','2022-06-23 08:33:11','backup table','null null found as the filter date'),(36,'2022-06-23','2022-06-23 08:33:11','backup table','null null found as the filter date'),(37,'2022-06-23','2022-06-23 08:33:11','backup table','null null found as the filter date'),(38,'2022-06-23','2022-06-23 08:33:11','backup table','null null found as the filter date'),(39,'2022-06-23','2022-06-23 08:33:25','date filter','Data backup and delete year count was less than 3. User did not agree to set it to 3 years. Then terminate the programme.'),(40,'2022-06-23','2022-06-23 08:33:48','backup table','null null found as the filter date'),(41,'2022-06-23','2022-06-23 08:33:48','backup table','null null found as the filter date'),(42,'2022-06-23','2022-06-23 08:33:48','backup table','null null found as the filter date'),(43,'2022-06-23','2022-06-23 08:33:48','backup table','null null found as the filter date'),(44,'2022-06-23','2022-06-23 08:33:54','date filter','Data backup and delete year count was less than 3. There for set it to 3 years when user is approved.'),(45,'2022-06-23','2022-06-23 08:33:54','date filter','between 2022-06-23 and 2019-01-01 have to backup the database'),(46,'2022-06-23','2022-06-23 08:33:56','backup','Database backup successful!'),(47,'2022-06-23','2022-06-23 08:33:59','delete','furniture_detail have not enough data to filter the date!'),(48,'2022-06-23','2022-06-23 08:34:00','delete','2 years data not found in ?arpicodaily_detail? tables'),(49,'2022-06-23','2022-06-23 08:34:09','delete','2 years data not found in ?showroom_detail? tables'),(50,'2022-06-23','2022-06-23 08:34:11','delete','2 years data not found in ?supercenter_detail? tables'),(51,'2022-06-23','2022-06-23 08:35:49','backup table','null null found as the filter date'),(52,'2022-06-23','2022-06-23 08:35:49','backup table','null null found as the filter date'),(53,'2022-06-23','2022-06-23 08:35:49','backup table','null null found as the filter date'),(54,'2022-06-23','2022-06-23 08:35:49','backup table','null null found as the filter date'),(55,'2022-06-23','2022-06-23 08:35:53','date filter','Data backup and delete year count was less than 3. There for set it to 3 years when user is approved.'),(56,'2022-06-23','2022-06-23 08:35:53','date filter','between 2022-06-23 and 2019-01-01 have to backup the database'),(57,'2022-06-23','2022-06-23 08:35:55','backup','Database backup successful!'),(58,'2022-06-23','2022-06-23 08:35:56','delete','furniture_detail have not enough data to filter the date!'),(59,'2022-06-23','2022-06-23 08:35:58','delete','3 years data not found in ?arpicodaily_detail? tables'),(60,'2022-06-23','2022-06-23 08:36:02','delete','3 years data not found in ?showroom_detail? tables'),(61,'2022-06-23','2022-06-23 08:36:05','delete','3 years data not found in ?supercenter_detail? tables'),(62,'2022-06-23','2022-06-23 08:36:21','backup table','null null found as the filter date'),(63,'2022-06-23','2022-06-23 08:36:21','backup table','null null found as the filter date'),(64,'2022-06-23','2022-06-23 08:36:21','backup table','null null found as the filter date'),(65,'2022-06-23','2022-06-23 08:36:21','backup table','null null found as the filter date'),(66,'2022-06-23','2022-06-23 08:36:21','date filter','between 2022-06-23 and 2017-01-01 have to backup the database');
/*!40000 ALTER TABLE `report_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showroom_detail`
--

DROP TABLE IF EXISTS `showroom_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `showroom_detail` (
  `showroom_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_code` varchar(255) NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `showroom_address` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `category_code` varchar(255) NOT NULL,
  `create_date` varchar(225) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  PRIMARY KEY (`showroom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showroom_detail`
--

LOCK TABLES `showroom_detail` WRITE;
/*!40000 ALTER TABLE `showroom_detail` DISABLE KEYS */;
INSERT INTO `showroom_detail` VALUES (2,'DE001','Dehiwala','No52, Samanala Mawatha, Galle Rd, Dehiwala.',112789789,'SR001','2025-06-18','192.168.3.2');
/*!40000 ALTER TABLE `showroom_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supercenter_detail`
--

DROP TABLE IF EXISTS `supercenter_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supercenter_detail` (
  `center_id` int(11) NOT NULL AUTO_INCREMENT,
  `center_code` varchar(255) NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `center_address` varchar(255) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `category_code` varchar(255) NOT NULL,
  `transaction_date` varchar(255) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  PRIMARY KEY (`center_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supercenter_detail`
--

LOCK TABLES `supercenter_detail` WRITE;
/*!40000 ALTER TABLE `supercenter_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `supercenter_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-23  8:36:23
