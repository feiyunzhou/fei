-- MySQL dump 10.13  Distrib 5.5.33, for Win64 (x86)
--
-- Host: localhost    Database: crmdb
-- ------------------------------------------------------
-- Server version	5.5.33

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `bdm_code` varchar(255) DEFAULT NULL,
  `administrativ_level` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `hospital_type` mediumint(9) DEFAULT NULL,
  `local_or_army` mediumint(9) DEFAULT NULL,
  `comprehensive_or_specialized` mediumint(9) DEFAULT NULL,
  `key_type` mediumint(9) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `duty_officer` varchar(255) DEFAULT NULL,
  `num_of_doctors` mediumint(9) DEFAULT NULL,
  `num_of_assistant_doctors` mediumint(9) DEFAULT NULL,
  `num_of_staff` mediumint(9) DEFAULT NULL,
  `num_of_treat_per_year` mediumint(9) DEFAULT NULL,
  `num_of_outpatient` mediumint(9) DEFAULT NULL,
  `total_num_of_sickbed` mediumint(9) DEFAULT NULL,
  `num_of_anesthesia_doctor` mediumint(9) DEFAULT NULL,
  `num_of_pain_doctor` mediumint(9) DEFAULT NULL,
  `num_of_surgery_per_year` mediumint(9) DEFAULT NULL,
  `num_of_surgery_room` mediumint(9) DEFAULT NULL,
  `num_of_using_opiates_medicine` mediumint(9) DEFAULT NULL,
  `num_of_using_opiates_injection` mediumint(9) DEFAULT NULL,
  `date_of_establish` datetime DEFAULT NULL,
  `registered_capital` mediumint(9) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `market_classification` mediumint(9) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `districts` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `responsible_person` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (5,'bh',NULL,'1','1',1,1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'李99','2013-10-14 21:23:00','李99','2013-10-14 21:23:00',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_administrativ_level_pl`
--

DROP TABLE IF EXISTS `account_administrativ_level_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_administrativ_level_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_administrativ_level_pl`
--

LOCK TABLES `account_administrativ_level_pl` WRITE;
/*!40000 ALTER TABLE `account_administrativ_level_pl` DISABLE KEYS */;
INSERT INTO `account_administrativ_level_pl` VALUES (1,'一级'),(2,'一级甲等'),(3,'一级乙等'),(4,'一级丙等'),(5,'二级'),(6,'二级甲等'),(7,'二级乙等'),(8,'二级丙等'),(9,'三级'),(10,'三级甲等'),(11,'三级乙等'),(12,'三级丙等'),(13,'未评级未评等');
/*!40000 ALTER TABLE `account_administrativ_level_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_comprehensive_or_specialized_pl`
--

DROP TABLE IF EXISTS `account_comprehensive_or_specialized_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_comprehensive_or_specialized_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_comprehensive_or_specialized_pl`
--

LOCK TABLES `account_comprehensive_or_specialized_pl` WRITE;
/*!40000 ALTER TABLE `account_comprehensive_or_specialized_pl` DISABLE KEYS */;
INSERT INTO `account_comprehensive_or_specialized_pl` VALUES (1,'综合医院'),(2,'专科医院');
/*!40000 ALTER TABLE `account_comprehensive_or_specialized_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_grade_pl`
--

DROP TABLE IF EXISTS `account_grade_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_grade_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_grade_pl`
--

LOCK TABLES `account_grade_pl` WRITE;
/*!40000 ALTER TABLE `account_grade_pl` DISABLE KEYS */;
INSERT INTO `account_grade_pl` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `account_grade_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_local_or_army_pl`
--

DROP TABLE IF EXISTS `account_local_or_army_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_local_or_army_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_local_or_army_pl`
--

LOCK TABLES `account_local_or_army_pl` WRITE;
/*!40000 ALTER TABLE `account_local_or_army_pl` DISABLE KEYS */;
INSERT INTO `account_local_or_army_pl` VALUES (1,'地方医院'),(2,'部队医院');
/*!40000 ALTER TABLE `account_local_or_army_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_market_classification_pl`
--

DROP TABLE IF EXISTS `account_market_classification_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_market_classification_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_market_classification_pl`
--

LOCK TABLES `account_market_classification_pl` WRITE;
/*!40000 ALTER TABLE `account_market_classification_pl` DISABLE KEYS */;
INSERT INTO `account_market_classification_pl` VALUES (1,'战略城市'),(2,'一级城市'),(3,'二级城市'),(4,'三级城市'),(5,'四级城市');
/*!40000 ALTER TABLE `account_market_classification_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_medicaltype_pl`
--

DROP TABLE IF EXISTS `account_medicaltype_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_medicaltype_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_medicaltype_pl`
--

LOCK TABLES `account_medicaltype_pl` WRITE;
/*!40000 ALTER TABLE `account_medicaltype_pl` DISABLE KEYS */;
INSERT INTO `account_medicaltype_pl` VALUES (1,'麻药目标医院'),(2,'慢痛目标医院'),(3,'其他');
/*!40000 ALTER TABLE `account_medicaltype_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl1`
--

DROP TABLE IF EXISTS `account_pl1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl1` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl1`
--

LOCK TABLES `account_pl1` WRITE;
/*!40000 ALTER TABLE `account_pl1` DISABLE KEYS */;
INSERT INTO `account_pl1` VALUES (1,'有效'),(2,'无效'),(3,'终止'),(4,'候选');
/*!40000 ALTER TABLE `account_pl1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl2`
--

DROP TABLE IF EXISTS `account_pl2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl2` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl2`
--

LOCK TABLES `account_pl2` WRITE;
/*!40000 ALTER TABLE `account_pl2` DISABLE KEYS */;
INSERT INTO `account_pl2` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `account_pl2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl3`
--

DROP TABLE IF EXISTS `account_pl3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl3` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl3`
--

LOCK TABLES `account_pl3` WRITE;
/*!40000 ALTER TABLE `account_pl3` DISABLE KEYS */;
INSERT INTO `account_pl3` VALUES (1,'是'),(2,'否');
/*!40000 ALTER TABLE `account_pl3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl4`
--

DROP TABLE IF EXISTS `account_pl4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl4` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl4`
--

LOCK TABLES `account_pl4` WRITE;
/*!40000 ALTER TABLE `account_pl4` DISABLE KEYS */;
INSERT INTO `account_pl4` VALUES (1,'一级城市'),(2,'二级城市'),(3,'战略城市');
/*!40000 ALTER TABLE `account_pl4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl5`
--

DROP TABLE IF EXISTS `account_pl5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl5` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl5`
--

LOCK TABLES `account_pl5` WRITE;
/*!40000 ALTER TABLE `account_pl5` DISABLE KEYS */;
INSERT INTO `account_pl5` VALUES (1,'北中国'),(2,'南中国'),(3,'东中国');
/*!40000 ALTER TABLE `account_pl5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_pl6`
--

DROP TABLE IF EXISTS `account_pl6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_pl6` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_pl6`
--

LOCK TABLES `account_pl6` WRITE;
/*!40000 ALTER TABLE `account_pl6` DISABLE KEYS */;
INSERT INTO `account_pl6` VALUES (1,'麻药目标医院'),(2,'慢痛目标医院'),(3,'其他');
/*!40000 ALTER TABLE `account_pl6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_point_pl`
--

DROP TABLE IF EXISTS `account_point_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_point_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_point_pl`
--

LOCK TABLES `account_point_pl` WRITE;
/*!40000 ALTER TABLE `account_point_pl` DISABLE KEYS */;
INSERT INTO `account_point_pl` VALUES (1,'癌痛重点医院'),(2,'慢痛重点医院'),(3,'奥诺美重点医院'),(4,'非重点医院');
/*!40000 ALTER TABLE `account_point_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_status_pl`
--

DROP TABLE IF EXISTS `account_status_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_status_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_status_pl`
--

LOCK TABLES `account_status_pl` WRITE;
/*!40000 ALTER TABLE `account_status_pl` DISABLE KEYS */;
INSERT INTO `account_status_pl` VALUES (1,'有效'),(2,'无效');
/*!40000 ALTER TABLE `account_status_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_type_pl`
--

DROP TABLE IF EXISTS `account_type_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_type_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type_pl`
--

LOCK TABLES `account_type_pl` WRITE;
/*!40000 ALTER TABLE `account_type_pl` DISABLE KEYS */;
INSERT INTO `account_type_pl` VALUES (1,'癌痛目标医院'),(2,'慢痛目标医院'),(3,'奥诺美目标医院'),(4,'非目标医院');
/*!40000 ALTER TABLE `account_type_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountcrmuser`
--

DROP TABLE IF EXISTS `accountcrmuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountcrmuser` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `accountId` mediumint(9) NOT NULL,
  `crmuserId` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `accountId` (`accountId`,`crmuserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2110 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountcrmuser`
--

LOCK TABLES `accountcrmuser` WRITE;
/*!40000 ALTER TABLE `accountcrmuser` DISABLE KEYS */;
INSERT INTO `accountcrmuser` VALUES (2014,5,20),(2109,5,99),(2016,6,20),(2017,7,20),(2018,8,20),(2019,9,20),(2020,10,20),(2021,11,20),(2022,12,20),(2023,13,20),(2024,14,20),(2025,15,20),(2026,16,20),(2027,17,20),(2028,18,20),(2029,19,20),(2030,20,20),(2031,21,20),(2032,22,20),(2033,23,20),(2034,24,20),(2035,25,20),(2036,26,20),(2037,27,20),(2038,28,20),(2039,29,20),(2040,30,20),(2041,31,20),(2042,32,20),(2043,33,20),(2044,34,20),(2045,35,20),(2046,36,20),(2047,37,20),(2048,38,20),(2049,39,20),(2050,40,20),(2051,41,20),(2052,42,20),(2053,43,20),(2054,44,20),(2055,45,20),(2056,46,20),(2057,47,20),(2058,48,20),(2059,49,20),(2060,50,20),(2061,51,20),(2062,52,20),(2063,53,20),(2064,54,20),(2065,55,20),(2066,56,20),(2067,57,20),(2068,58,20),(2069,59,20),(2070,60,20),(2071,61,20),(2072,62,20),(2073,63,20),(2074,64,20),(2075,65,20),(2076,66,20),(2077,67,20),(2078,68,20),(2079,69,20),(2080,70,20),(2081,71,20),(2082,72,20),(2083,73,20),(2084,74,20),(2085,75,20),(2086,76,20),(2087,77,20),(2088,78,20),(2089,79,20),(2090,80,20),(2091,81,20),(2092,82,20),(2093,83,20),(2094,84,20),(2095,85,20),(2096,86,20),(2097,87,20),(2098,88,20),(2099,89,20),(2100,90,20),(2101,91,20),(2102,92,20),(2103,93,20),(2104,94,20),(2105,95,20),(2106,96,20),(2107,97,20),(2108,98,20);
/*!40000 ALTER TABLE `accountcrmuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `crmuserId` mediumint(9) NOT NULL,
  `event_type` mediumint(9) NOT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `starttime` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `participants` varchar(512) DEFAULT NULL,
  `activity_type` mediumint(9) DEFAULT NULL,
  `contactId` mediumint(9) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `visiting_purpose` mediumint(9) DEFAULT NULL,
  `feature_product` mediumint(9) DEFAULT NULL,
  `act_endtime` datetime DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `responsible_person` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (3,NULL,21,2,1380724740000,1380721140000,'sdf','李20, 李21',1,7,2,1,1,'2013-10-03 21:40:20','李20','2013-10-03 21:39:26','李20','2013-10-03 21:39:26','李20'),(4,NULL,21,1,1380638400000,1380634800000,'www','李21',1,8,2,1,1,'2013-10-05 15:48:19','李21','2013-10-03 21:41:07','李21','2013-10-03 21:41:07','李21'),(5,NULL,99,1,1380677940000,1380674340000,'AAAA','李99',1,5,2,1,1,'2013-10-04 20:09:20','李99','2013-10-04 08:39:27','李99','2013-10-04 08:39:27','李99'),(6,NULL,99,1,1381282740000,1381279140000,'BBB','李99',1,8,2,1,1,'2013-10-04 20:09:16','李99','2013-10-04 08:39:41','李99','2013-10-04 08:39:41','李99'),(7,NULL,99,1,1381196460000,1381192860000,'AA','李99',1,7,2,1,1,'2013-10-04 20:09:13','李99','2013-10-04 08:41:59','李99','2013-10-04 08:41:59','李99'),(8,NULL,99,1,1381801320000,1381797720000,'aaaaaav2','李99',1,7,2,1,1,'2013-10-04 20:09:10','李99','2013-10-04 08:42:56','李99','2013-10-04 08:42:56','李99'),(9,NULL,99,1,1381931400000,1381927800000,'bbb','李99',1,5,2,1,1,'2013-10-04 20:50:31','李99','2013-10-04 20:50:28','李99','2013-10-04 20:50:28','李99'),(10,NULL,20,1,1381221960000,1381218360000,'sdf','李20',1,7,2,1,1,'2013-10-05 15:47:05','李20','2013-10-05 15:47:02','李20','2013-10-05 15:47:02','李20'),(11,NULL,20,1,1381833060000,1381829460000,'bbbbb','李20',1,7,2,1,1,'2013-10-05 17:31:35','李20','2013-10-05 17:31:32','李20','2013-10-05 17:31:32','李20'),(12,NULL,20,1,1382351460000,1382347860000,'ssss','李20',1,8,2,1,1,'2013-10-05 17:32:04','李20','2013-10-05 17:32:00','李20','2013-10-05 17:32:00','李20'),(13,NULL,99,1,1381761300000,1381757700000,'e23','李99',1,8,1,1,1,NULL,'李99','2013-10-14 21:36:03','李99','2013-10-14 21:36:03','李99');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_activity_types_pl`
--

DROP TABLE IF EXISTS `activity_activity_types_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_activity_types_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_activity_types_pl`
--

LOCK TABLES `activity_activity_types_pl` WRITE;
/*!40000 ALTER TABLE `activity_activity_types_pl` DISABLE KEYS */;
INSERT INTO `activity_activity_types_pl` VALUES (1,'专业化拜访'),(2,'事务性拜访');
/*!40000 ALTER TABLE `activity_activity_types_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_event_type_pl`
--

DROP TABLE IF EXISTS `activity_event_type_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_event_type_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_event_type_pl`
--

LOCK TABLES `activity_event_type_pl` WRITE;
/*!40000 ALTER TABLE `activity_event_type_pl` DISABLE KEYS */;
INSERT INTO `activity_event_type_pl` VALUES (1,'拜访'),(2,'辅导');
/*!40000 ALTER TABLE `activity_event_type_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_feature_product_pl`
--

DROP TABLE IF EXISTS `activity_feature_product_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_feature_product_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_feature_product_pl`
--

LOCK TABLES `activity_feature_product_pl` WRITE;
/*!40000 ALTER TABLE `activity_feature_product_pl` DISABLE KEYS */;
INSERT INTO `activity_feature_product_pl` VALUES (1,'美施康定'),(2,'奥施康定'),(3,'奇曼丁'),(4,'若思本'),(5,'奥诺美');
/*!40000 ALTER TABLE `activity_feature_product_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_status`
--

DROP TABLE IF EXISTS `activity_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_status` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_status`
--

LOCK TABLES `activity_status` WRITE;
/*!40000 ALTER TABLE `activity_status` DISABLE KEYS */;
INSERT INTO `activity_status` VALUES (1,'计划'),(2,'完成');
/*!40000 ALTER TABLE `activity_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_status_pl`
--

DROP TABLE IF EXISTS `activity_status_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_status_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_status_pl`
--

LOCK TABLES `activity_status_pl` WRITE;
/*!40000 ALTER TABLE `activity_status_pl` DISABLE KEYS */;
INSERT INTO `activity_status_pl` VALUES (1,'计划'),(2,'完成');
/*!40000 ALTER TABLE `activity_status_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_types`
--

DROP TABLE IF EXISTS `activity_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_types` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_types`
--

LOCK TABLES `activity_types` WRITE;
/*!40000 ALTER TABLE `activity_types` DISABLE KEYS */;
INSERT INTO `activity_types` VALUES (1,'拜访'),(2,'外部会议'),(3,'内部会议');
/*!40000 ALTER TABLE `activity_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_visiting_purpose_pl`
--

DROP TABLE IF EXISTS `activity_visiting_purpose_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_visiting_purpose_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  `activity_type` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_visiting_purpose_pl`
--

LOCK TABLES `activity_visiting_purpose_pl` WRITE;
/*!40000 ALTER TABLE `activity_visiting_purpose_pl` DISABLE KEYS */;
INSERT INTO `activity_visiting_purpose_pl` VALUES (1,'传递产品知识',1),(2,'处方观念沟通',1),(3,'病例沟通',1),(4,'会议安排',2),(5,'会议跟进',2),(6,'交接工作',2),(7,'了解竞争',2);
/*!40000 ALTER TABLE `activity_visiting_purpose_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitycrmuser`
--

DROP TABLE IF EXISTS `activitycrmuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activitycrmuser` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `activityId` mediumint(9) NOT NULL,
  `crmuserId` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `activityId` (`activityId`,`crmuserId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitycrmuser`
--

LOCK TABLES `activitycrmuser` WRITE;
/*!40000 ALTER TABLE `activitycrmuser` DISABLE KEYS */;
INSERT INTO `activitycrmuser` VALUES (18,3,20),(19,3,21),(20,4,21),(21,5,99),(22,6,99),(23,7,99),(24,8,99),(25,9,99),(26,10,20),(27,11,20),(28,12,20),(29,13,99);
/*!40000 ALTER TABLE `activitycrmuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appinfo`
--

DROP TABLE IF EXISTS `appinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appinfo` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appinfo`
--

LOCK TABLES `appinfo` WRITE;
/*!40000 ALTER TABLE `appinfo` DISABLE KEYS */;
INSERT INTO `appinfo` VALUES (1,'爱奇艺'),(2,'影视圈'),(3,'多米音乐'),(4,'百度地图'),(5,'新浪微博'),(6,'三星学习'),(7,'印象笔记'),(8,'愤怒的小鸟'),(9,'疯狂飙车'),(10,'股票专家'),(11,'名片全能王'),(12,'携程旅游');
/*!40000 ALTER TABLE `appinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boolean_pl`
--

DROP TABLE IF EXISTS `boolean_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boolean_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boolean_pl`
--

LOCK TABLES `boolean_pl` WRITE;
/*!40000 ALTER TABLE `boolean_pl` DISABLE KEYS */;
INSERT INTO `boolean_pl` VALUES (1,'是'),(2,'否');
/*!40000 ALTER TABLE `boolean_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `provinceId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_visiting_purpose_pl`
--

DROP TABLE IF EXISTS `com_visiting_purpose_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_visiting_purpose_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_visiting_purpose_pl`
--

LOCK TABLES `com_visiting_purpose_pl` WRITE;
/*!40000 ALTER TABLE `com_visiting_purpose_pl` DISABLE KEYS */;
INSERT INTO `com_visiting_purpose_pl` VALUES (1,'处方观念沟通'),(2,'病例沟通');
/*!40000 ALTER TABLE `com_visiting_purpose_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `accountId` mediumint(9) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `native_place` varchar(255) DEFAULT NULL,
  `office_tel` varchar(255) DEFAULT NULL,
  `office_fax` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `market_classification` mediumint(9) DEFAULT NULL,
  `grade` mediumint(9) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `districts` varchar(255) DEFAULT NULL,
  `duty` mediumint(9) DEFAULT NULL,
  `job_title` mediumint(9) DEFAULT NULL,
  `visiting_target` mediumint(9) DEFAULT NULL,
  `product_target` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `responsible_person` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (5,'BBcdcdcd',3,'1',1,NULL,NULL,NULL,NULL,NULL,'1',1,2,NULL,NULL,NULL,1,1,NULL,NULL,'李99','2013-09-28 17:00:40','李99','2013-10-04 08:38:00',NULL),(6,'122',3,'1',1,NULL,NULL,NULL,NULL,'sd@sina.sdfdsf','1',1,1,NULL,NULL,NULL,1,2,NULL,NULL,'李99','2013-09-28 21:48:12','李99','2013-09-30 21:15:00',NULL),(7,'asdsd',3,'1',1,'as',NULL,NULL,NULL,NULL,'1',1,1,NULL,NULL,NULL,1,1,NULL,NULL,'李99','2013-09-30 07:45:00','李99','2013-09-30 07:45:00',NULL),(8,'BBBB',4,'1',1,NULL,NULL,NULL,NULL,NULL,'1',1,2,NULL,NULL,NULL,1,1,NULL,NULL,'李99','2013-10-01 02:23:00','李99','2013-10-04 08:38:00',NULL);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_department_pl`
--

DROP TABLE IF EXISTS `contact_department_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_department_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_department_pl`
--

LOCK TABLES `contact_department_pl` WRITE;
/*!40000 ALTER TABLE `contact_department_pl` DISABLE KEYS */;
INSERT INTO `contact_department_pl` VALUES (1,'ICU'),(2,'内科'),(3,'外科'),(4,'中医科'),(5,'化疗科'),(6,'关怀科'),(7,'牙科'),(8,'急症科'),(9,'骨科'),(10,'肝胆外科'),(11,'血液科'),(12,'风湿科'),(13,'呼吸科');
/*!40000 ALTER TABLE `contact_department_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_duty_pl`
--

DROP TABLE IF EXISTS `contact_duty_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_duty_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_duty_pl`
--

LOCK TABLES `contact_duty_pl` WRITE;
/*!40000 ALTER TABLE `contact_duty_pl` DISABLE KEYS */;
INSERT INTO `contact_duty_pl` VALUES (1,'主任'),(2,'主治医师'),(3,'科室主任'),(4,'采购'),(5,'科室主任'),(6,'院长'),(7,'副院长'),(8,'药剂科主任');
/*!40000 ALTER TABLE `contact_duty_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_generalization_target_pl`
--

DROP TABLE IF EXISTS `contact_generalization_target_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_generalization_target_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_generalization_target_pl`
--

LOCK TABLES `contact_generalization_target_pl` WRITE;
/*!40000 ALTER TABLE `contact_generalization_target_pl` DISABLE KEYS */;
INSERT INTO `contact_generalization_target_pl` VALUES (1,'美施康定'),(2,'奥施康定'),(3,'奇曼丁'),(4,'若思本'),(5,'奥诺美');
/*!40000 ALTER TABLE `contact_generalization_target_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_grade_pl`
--

DROP TABLE IF EXISTS `contact_grade_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_grade_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_grade_pl`
--

LOCK TABLES `contact_grade_pl` WRITE;
/*!40000 ALTER TABLE `contact_grade_pl` DISABLE KEYS */;
INSERT INTO `contact_grade_pl` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `contact_grade_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_job_title_pl`
--

DROP TABLE IF EXISTS `contact_job_title_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_job_title_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_job_title_pl`
--

LOCK TABLES `contact_job_title_pl` WRITE;
/*!40000 ALTER TABLE `contact_job_title_pl` DISABLE KEYS */;
INSERT INTO `contact_job_title_pl` VALUES (1,'住院医师'),(2,'主治医师'),(3,'副主任医师'),(4,'主任医师'),(5,'护士'),(6,'主管护师'),(7,'药师'),(8,'技师');
/*!40000 ALTER TABLE `contact_job_title_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_market_classification_pl`
--

DROP TABLE IF EXISTS `contact_market_classification_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_market_classification_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_market_classification_pl`
--

LOCK TABLES `contact_market_classification_pl` WRITE;
/*!40000 ALTER TABLE `contact_market_classification_pl` DISABLE KEYS */;
INSERT INTO `contact_market_classification_pl` VALUES (1,'战略城市'),(2,'一级城市'),(3,'二级城市'),(4,'三级城市'),(5,'四级城市');
/*!40000 ALTER TABLE `contact_market_classification_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl1`
--

DROP TABLE IF EXISTS `contact_pl1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl1` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl1`
--

LOCK TABLES `contact_pl1` WRITE;
/*!40000 ALTER TABLE `contact_pl1` DISABLE KEYS */;
INSERT INTO `contact_pl1` VALUES (1,'肿瘤科'),(2,'内科'),(3,'外科'),(4,'中医科'),(5,'化疗科'),(6,'关怀科'),(7,'牙科'),(8,'急症科'),(9,'骨科'),(10,'肝胆外科'),(11,'血液科'),(12,'风湿科'),(13,'呼吸科');
/*!40000 ALTER TABLE `contact_pl1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl2`
--

DROP TABLE IF EXISTS `contact_pl2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl2` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl2`
--

LOCK TABLES `contact_pl2` WRITE;
/*!40000 ALTER TABLE `contact_pl2` DISABLE KEYS */;
INSERT INTO `contact_pl2` VALUES (1,'主任'),(2,'主治医师'),(3,'科室主任'),(4,'采购'),(5,'科室主任'),(6,'院长');
/*!40000 ALTER TABLE `contact_pl2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl3`
--

DROP TABLE IF EXISTS `contact_pl3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl3` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl3`
--

LOCK TABLES `contact_pl3` WRITE;
/*!40000 ALTER TABLE `contact_pl3` DISABLE KEYS */;
INSERT INTO `contact_pl3` VALUES (1,'住院医师'),(2,'主治医师'),(3,'副主任医师'),(4,'主任医师'),(5,'护士'),(6,'主管护师'),(7,'药师'),(8,'技师');
/*!40000 ALTER TABLE `contact_pl3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl4`
--

DROP TABLE IF EXISTS `contact_pl4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl4` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl4`
--

LOCK TABLES `contact_pl4` WRITE;
/*!40000 ALTER TABLE `contact_pl4` DISABLE KEYS */;
INSERT INTO `contact_pl4` VALUES (1,'奥施康定'),(2,'奇曼丁'),(3,'意施丁'),(4,'综合'),(5,'麻药'),(6,'慢痛');
/*!40000 ALTER TABLE `contact_pl4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl5`
--

DROP TABLE IF EXISTS `contact_pl5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl5` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl5`
--

LOCK TABLES `contact_pl5` WRITE;
/*!40000 ALTER TABLE `contact_pl5` DISABLE KEYS */;
INSERT INTO `contact_pl5` VALUES (1,'有效'),(2,'无效');
/*!40000 ALTER TABLE `contact_pl5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_pl6`
--

DROP TABLE IF EXISTS `contact_pl6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_pl6` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_pl6`
--

LOCK TABLES `contact_pl6` WRITE;
/*!40000 ALTER TABLE `contact_pl6` DISABLE KEYS */;
INSERT INTO `contact_pl6` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `contact_pl6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_status_pl`
--

DROP TABLE IF EXISTS `contact_status_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_status_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_status_pl`
--

LOCK TABLES `contact_status_pl` WRITE;
/*!40000 ALTER TABLE `contact_status_pl` DISABLE KEYS */;
INSERT INTO `contact_status_pl` VALUES (1,'有效'),(2,'无效');
/*!40000 ALTER TABLE `contact_status_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactcrmuser`
--

DROP TABLE IF EXISTS `contactcrmuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactcrmuser` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `contactId` mediumint(9) NOT NULL,
  `crmuserId` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `contactId` (`contactId`,`crmuserId`)
) ENGINE=InnoDB AUTO_INCREMENT=1332 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactcrmuser`
--

LOCK TABLES `contactcrmuser` WRITE;
/*!40000 ALTER TABLE `contactcrmuser` DISABLE KEYS */;
INSERT INTO `contactcrmuser` VALUES (1138,7,20),(1139,8,20),(1331,8,99),(1140,9,20),(1141,10,20),(1142,11,20),(1143,12,20),(1144,13,20),(1145,14,20),(1146,15,20),(1147,16,20),(1148,17,20),(1149,18,20),(1150,19,20),(1151,20,20),(1152,21,20),(1153,22,20),(1154,23,20),(1155,24,20),(1156,25,20),(1157,26,20),(1158,27,20),(1159,28,20),(1160,29,20),(1161,30,20),(1162,31,20),(1163,32,20),(1164,33,20),(1165,34,20),(1166,35,20),(1167,36,20),(1168,37,20),(1169,38,20),(1170,39,20),(1171,40,20),(1172,41,20),(1173,42,20),(1174,43,20),(1175,44,20),(1176,45,20),(1177,46,20),(1178,47,20),(1179,48,20),(1180,49,20),(1181,50,20),(1182,51,20),(1183,52,20),(1184,53,20),(1185,54,20),(1186,55,20),(1187,56,20),(1188,57,20),(1189,58,20),(1190,59,20),(1191,60,20),(1192,61,20),(1193,62,20),(1194,63,20),(1195,64,20),(1196,65,20),(1197,66,20),(1198,67,20),(1199,68,20),(1200,69,20),(1201,70,20),(1202,71,20),(1203,72,20),(1204,73,20),(1205,74,20),(1206,75,20),(1207,76,20),(1208,77,20),(1209,78,20),(1210,79,20),(1211,80,20),(1212,81,20),(1213,82,20),(1214,83,20),(1215,84,20),(1216,85,20),(1217,86,20),(1218,87,20),(1219,88,20),(1220,89,20),(1221,90,20),(1222,91,20),(1223,92,20),(1224,93,20),(1225,94,20),(1226,95,20),(1227,96,20),(1228,97,20),(1229,98,20),(1230,99,20),(1231,100,20),(1232,101,20),(1233,102,20),(1234,103,20),(1235,104,20),(1236,105,20),(1237,106,20),(1238,107,20),(1239,108,20),(1240,109,20),(1241,110,20),(1242,111,20),(1243,112,20),(1244,113,20),(1245,114,20),(1246,115,20),(1247,116,20),(1248,117,20),(1249,118,20),(1250,119,20),(1251,120,20),(1252,121,20),(1253,122,20),(1254,123,20),(1255,124,20),(1256,125,20),(1257,126,20),(1258,127,20),(1259,128,20),(1260,129,20),(1261,130,20),(1262,131,20),(1263,132,20),(1264,133,20),(1265,134,20),(1266,135,20),(1267,136,20),(1268,137,20),(1269,138,20),(1270,139,20),(1271,140,20),(1272,141,20),(1273,142,20),(1274,143,20),(1275,144,20),(1276,145,20),(1277,146,20),(1278,147,20),(1279,148,20),(1280,149,20),(1281,150,20),(1282,151,20),(1283,152,20),(1284,153,20),(1285,154,20),(1286,155,20),(1287,156,20),(1288,157,20),(1289,158,20),(1290,159,20),(1291,160,20),(1292,161,20),(1293,162,20),(1294,163,20),(1295,164,20),(1296,165,20),(1297,166,20),(1298,167,20),(1299,168,20),(1300,169,20),(1301,170,20),(1302,171,20),(1303,172,20),(1304,173,20),(1305,174,20),(1306,175,20),(1307,176,20),(1308,177,20),(1309,178,20),(1310,179,20),(1311,180,20),(1312,181,20),(1313,182,20),(1314,183,20),(1315,184,20),(1316,185,20),(1317,186,20),(1318,187,20),(1319,188,20),(1320,189,20),(1321,190,20),(1322,191,20),(1323,192,20),(1324,193,20),(1325,194,20),(1326,195,20),(1327,196,20),(1328,197,20),(1329,198,20),(1330,199,20);
/*!40000 ALTER TABLE `contactcrmuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crmuser`
--

DROP TABLE IF EXISTS `crmuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crmuser` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `cellPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employeeNumber` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `jobTitle` varchar(255) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `pl2` mediumint(9) DEFAULT NULL,
  `role` mediumint(9) DEFAULT NULL,
  `pl4` mediumint(9) DEFAULT NULL,
  `pl5` mediumint(9) DEFAULT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `lastLoginTime` bigint(20) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `reportto` mediumint(9) DEFAULT NULL,
  `parcel` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modify_datetime` datetime DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `postId` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crmuser`
--

LOCK TABLES `crmuser` WRITE;
/*!40000 ALTER TABLE `crmuser` DISABLE KEYS */;
INSERT INTO `crmuser` VALUES (99,'李99',NULL,'医疗设备部','13811999933','myemail99@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',1,1,1,1,1,1,'li','827ccb0eea8a706c4c34a16891f84e7b','53856',NULL,NULL,100,NULL,'李99','2013-10-14 21:31:00',NULL,NULL,1),(100,'gvg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,'jjj',NULL,NULL,NULL,'2013-10-14 18:58:00',101,NULL,'李99','2013-10-14 18:58:00','李99',NULL,NULL),(101,'gvg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,1,1,1,'jjj',NULL,NULL,NULL,'2013-10-14 18:58:00',99,NULL,'李99','2013-10-14 18:58:00','李99',NULL,NULL);
/*!40000 ALTER TABLE `crmuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crmuser_pl1`
--

DROP TABLE IF EXISTS `crmuser_pl1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crmuser_pl1` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crmuser_pl1`
--

LOCK TABLES `crmuser_pl1` WRITE;
/*!40000 ALTER TABLE `crmuser_pl1` DISABLE KEYS */;
INSERT INTO `crmuser_pl1` VALUES (1,'有效'),(2,'无效');
/*!40000 ALTER TABLE `crmuser_pl1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crmuser_pl2`
--

DROP TABLE IF EXISTS `crmuser_pl2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crmuser_pl2` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crmuser_pl2`
--

LOCK TABLES `crmuser_pl2` WRITE;
/*!40000 ALTER TABLE `crmuser_pl2` DISABLE KEYS */;
INSERT INTO `crmuser_pl2` VALUES (1,'麻药'),(2,'慢痛'),(3,'全部');
/*!40000 ALTER TABLE `crmuser_pl2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealeraccount`
--

DROP TABLE IF EXISTS `dealeraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealeraccount` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `tele` varchar(255) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `pl2` mediumint(9) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealeraccount`
--

LOCK TABLES `dealeraccount` WRITE;
/*!40000 ALTER TABLE `dealeraccount` DISABLE KEYS */;
INSERT INTO `dealeraccount` VALUES (1,'经销商0','北京市朝阳区望京东路0号','1381199260',1,1,1,NULL),(2,'经销商1','北京市朝阳区望京东路1号','1381199261',2,2,2,NULL),(3,'经销商2','北京市朝阳区望京东路2号','1381199262',1,3,3,NULL),(4,'经销商3','北京市朝阳区望京东路3号','1381199263',2,1,4,NULL),(5,'经销商4','北京市朝阳区望京东路4号','1381199264',1,2,5,NULL),(6,'经销商5','北京市朝阳区望京东路5号','1381199265',2,3,6,NULL),(7,'经销商6','北京市朝阳区望京东路6号','1381199266',1,1,7,NULL),(8,'经销商7','北京市朝阳区望京东路7号','1381199267',2,2,1,NULL),(9,'经销商8','北京市朝阳区望京东路8号','1381199268',1,3,2,NULL),(10,'经销商9','北京市朝阳区望京东路9号','1381199269',2,1,3,NULL),(11,'经销商10','北京市朝阳区望京东路10号','13811992610',1,2,4,NULL),(12,'经销商11','北京市朝阳区望京东路11号','13811992611',2,3,5,NULL),(13,'经销商12','北京市朝阳区望京东路12号','13811992612',1,1,6,NULL),(14,'经销商13','北京市朝阳区望京东路13号','13811992613',2,2,7,NULL),(15,'经销商14','北京市朝阳区望京东路14号','13811992614',1,3,1,NULL),(16,'经销商15','北京市朝阳区望京东路15号','13811992615',2,1,2,NULL),(17,'经销商16','北京市朝阳区望京东路16号','13811992616',1,2,3,NULL),(18,'经销商17','北京市朝阳区望京东路17号','13811992617',2,3,4,NULL),(19,'经销商18','北京市朝阳区望京东路18号','13811992618',1,1,5,NULL),(20,'经销商19','北京市朝阳区望京东路19号','13811992619',2,2,6,NULL),(21,'经销商20','北京市朝阳区望京东路20号','13811992620',1,3,7,NULL),(22,'经销商21','北京市朝阳区望京东路21号','13811992621',2,1,1,NULL),(23,'经销商22','北京市朝阳区望京东路22号','13811992622',1,2,2,NULL),(24,'经销商23','北京市朝阳区望京东路23号','13811992623',2,3,3,NULL),(25,'经销商24','北京市朝阳区望京东路24号','13811992624',1,1,4,NULL),(26,'经销商25','北京市朝阳区望京东路25号','13811992625',2,2,5,NULL),(27,'经销商26','北京市朝阳区望京东路26号','13811992626',1,3,6,NULL),(28,'经销商27','北京市朝阳区望京东路27号','13811992627',2,1,7,NULL),(29,'经销商28','北京市朝阳区望京东路28号','13811992628',1,2,1,NULL),(30,'经销商29','北京市朝阳区望京东路29号','13811992629',2,3,2,NULL),(31,'经销商30','北京市朝阳区望京东路30号','13811992630',1,1,3,NULL),(32,'经销商31','北京市朝阳区望京东路31号','13811992631',2,2,4,NULL),(33,'经销商32','北京市朝阳区望京东路32号','13811992632',1,3,5,NULL),(34,'经销商33','北京市朝阳区望京东路33号','13811992633',2,1,6,NULL),(35,'经销商34','北京市朝阳区望京东路34号','13811992634',1,2,7,NULL),(36,'经销商35','北京市朝阳区望京东路35号','13811992635',2,3,1,NULL),(37,'经销商36','北京市朝阳区望京东路36号','13811992636',1,1,2,NULL),(38,'经销商37','北京市朝阳区望京东路37号','13811992637',2,2,3,NULL),(39,'经销商38','北京市朝阳区望京东路38号','13811992638',1,3,4,NULL),(40,'经销商39','北京市朝阳区望京东路39号','13811992639',2,1,5,NULL),(41,'经销商40','北京市朝阳区望京东路40号','13811992640',1,2,6,NULL),(42,'经销商41','北京市朝阳区望京东路41号','13811992641',2,3,7,NULL),(43,'经销商42','北京市朝阳区望京东路42号','13811992642',1,1,1,NULL),(44,'经销商43','北京市朝阳区望京东路43号','13811992643',2,2,2,NULL),(45,'经销商44','北京市朝阳区望京东路44号','13811992644',1,3,3,NULL),(46,'经销商45','北京市朝阳区望京东路45号','13811992645',2,1,4,NULL),(47,'经销商46','北京市朝阳区望京东路46号','13811992646',1,2,5,NULL),(48,'经销商47','北京市朝阳区望京东路47号','13811992647',2,3,6,NULL),(49,'经销商48','北京市朝阳区望京东路48号','13811992648',1,1,7,NULL),(50,'经销商49','北京市朝阳区望京东路49号','13811992649',2,2,1,NULL),(51,'经销商50','北京市朝阳区望京东路50号','13811992650',1,3,2,NULL),(52,'经销商51','北京市朝阳区望京东路51号','13811992651',2,1,3,NULL),(53,'经销商52','北京市朝阳区望京东路52号','13811992652',1,2,4,NULL),(54,'经销商53','北京市朝阳区望京东路53号','13811992653',2,3,5,NULL),(55,'经销商54','北京市朝阳区望京东路54号','13811992654',1,1,6,NULL),(56,'经销商55','北京市朝阳区望京东路55号','13811992655',2,2,7,NULL),(57,'经销商56','北京市朝阳区望京东路56号','13811992656',1,3,1,NULL),(58,'经销商57','北京市朝阳区望京东路57号','13811992657',2,1,2,NULL),(59,'经销商58','北京市朝阳区望京东路58号','13811992658',1,2,3,NULL),(60,'经销商59','北京市朝阳区望京东路59号','13811992659',2,3,4,NULL),(61,'经销商60','北京市朝阳区望京东路60号','13811992660',1,1,5,NULL),(62,'经销商61','北京市朝阳区望京东路61号','13811992661',2,2,6,NULL),(63,'经销商62','北京市朝阳区望京东路62号','13811992662',1,3,7,NULL),(64,'经销商63','北京市朝阳区望京东路63号','13811992663',2,1,1,NULL),(65,'经销商64','北京市朝阳区望京东路64号','13811992664',1,2,2,NULL),(66,'经销商65','北京市朝阳区望京东路65号','13811992665',2,3,3,NULL),(67,'经销商66','北京市朝阳区望京东路66号','13811992666',1,1,4,NULL),(68,'经销商67','北京市朝阳区望京东路67号','13811992667',2,2,5,NULL),(69,'经销商68','北京市朝阳区望京东路68号','13811992668',1,3,6,NULL),(70,'经销商69','北京市朝阳区望京东路69号','13811992669',2,1,7,NULL),(71,'经销商70','北京市朝阳区望京东路70号','13811992670',1,2,1,NULL),(72,'经销商71','北京市朝阳区望京东路71号','13811992671',2,3,2,NULL),(73,'经销商72','北京市朝阳区望京东路72号','13811992672',1,1,3,NULL),(74,'经销商73','北京市朝阳区望京东路73号','13811992673',2,2,4,NULL),(75,'经销商74','北京市朝阳区望京东路74号','13811992674',1,3,5,NULL),(76,'经销商75','北京市朝阳区望京东路75号','13811992675',2,1,6,NULL),(77,'经销商76','北京市朝阳区望京东路76号','13811992676',1,2,7,NULL),(78,'经销商77','北京市朝阳区望京东路77号','13811992677',2,3,1,NULL),(79,'经销商78','北京市朝阳区望京东路78号','13811992678',1,1,2,NULL),(80,'经销商79','北京市朝阳区望京东路79号','13811992679',2,2,3,NULL),(81,'经销商80','北京市朝阳区望京东路80号','13811992680',1,3,4,NULL),(82,'经销商81','北京市朝阳区望京东路81号','13811992681',2,1,5,NULL),(83,'经销商82','北京市朝阳区望京东路82号','13811992682',1,2,6,NULL),(84,'经销商83','北京市朝阳区望京东路83号','13811992683',2,3,7,NULL),(85,'经销商84','北京市朝阳区望京东路84号','13811992684',1,1,1,NULL),(86,'经销商85','北京市朝阳区望京东路85号','13811992685',2,2,2,NULL),(87,'经销商86','北京市朝阳区望京东路86号','13811992686',1,3,3,NULL),(88,'经销商87','北京市朝阳区望京东路87号','13811992687',2,1,4,NULL);
/*!40000 ALTER TABLE `dealeraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealercontact`
--

DROP TABLE IF EXISTS `dealercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealercontact` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `tel_work` varchar(255) DEFAULT NULL,
  `dealerAccountId` mediumint(9) NOT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealercontact`
--

LOCK TABLES `dealercontact` WRITE;
/*!40000 ALTER TABLE `dealercontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `dealercontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dl`
--

DROP TABLE IF EXISTS `dl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(255) DEFAULT NULL,
  `appId` mediumint(9) DEFAULT NULL,
  `dlDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dl`
--

LOCK TABLES `dl` WRITE;
/*!40000 ALTER TABLE `dl` DISABLE KEYS */;
INSERT INTO `dl` VALUES (1,'N2',2,'2013-10-09 14:42:28'),(2,'N3',3,'2013-10-20 15:42:28'),(3,'N1',4,'2013-10-04 16:42:28'),(4,'N2',5,'2013-10-28 17:42:28'),(5,'N3',6,'2013-10-06 18:42:28'),(6,'N1',7,'2013-10-23 19:42:28'),(7,'N2',8,'2013-10-03 20:42:28'),(8,'N3',9,'2013-10-18 21:42:28'),(9,'N1',10,'2013-10-22 22:42:28'),(10,'N2',1,'2013-10-13 23:42:28'),(11,'N3',2,'2013-10-06 00:42:28'),(12,'N1',3,'2013-10-18 01:42:28'),(13,'N2',4,'2013-10-13 02:42:28'),(14,'N3',5,'2013-10-19 03:42:28'),(15,'N1',6,'2013-10-11 04:42:28'),(16,'N2',7,'2013-10-02 05:42:28'),(17,'N3',8,'2013-10-09 06:42:28'),(18,'N1',9,'2013-10-27 07:42:28'),(19,'N2',10,'2013-10-24 08:42:28'),(20,'N3',1,'2013-10-24 09:42:28'),(21,'N1',2,'2013-10-21 10:42:28'),(22,'N2',3,'2013-10-15 13:42:28'),(23,'N3',4,'2013-10-24 14:42:28'),(24,'N1',5,'2013-10-13 15:42:28'),(25,'N2',6,'2013-10-01 16:42:28'),(26,'N3',7,'2013-10-25 17:42:28'),(27,'N1',8,'2013-10-06 18:42:28'),(28,'N2',9,'2013-10-04 19:42:28'),(29,'N3',10,'2013-10-05 20:42:28'),(30,'N1',1,'2013-10-28 21:42:28'),(31,'N2',2,'2013-10-09 22:42:28'),(32,'N3',3,'2013-10-29 23:42:28'),(33,'N1',4,'2013-10-12 00:42:28'),(34,'N2',5,'2013-10-04 01:42:28'),(35,'N3',6,'2013-10-12 02:42:28'),(36,'N1',7,'2013-10-03 03:42:28'),(37,'N2',8,'2013-10-19 04:42:28'),(38,'N3',9,'2013-10-04 05:42:28'),(39,'N1',10,'2013-10-16 06:42:28'),(40,'N2',1,'2013-10-11 07:42:28'),(41,'N3',2,'2013-10-12 08:42:28'),(42,'N1',3,'2013-10-18 09:42:28'),(43,'N2',4,'2013-10-11 10:42:28'),(44,'N3',5,'2013-10-21 13:42:28'),(45,'N1',6,'2013-10-11 14:42:28'),(46,'N2',7,'2013-10-21 15:42:28'),(47,'N3',8,'2013-10-21 16:42:28'),(48,'N1',9,'2013-10-16 17:42:28'),(49,'N2',10,'2013-10-22 18:42:28'),(50,'N3',1,'2013-10-13 19:42:28'),(51,'N1',2,'2013-10-29 20:42:28'),(52,'N2',3,'2013-10-02 21:42:28'),(53,'N3',4,'2013-10-07 22:42:28'),(54,'N1',5,'2013-10-19 23:42:28'),(55,'N2',6,'2013-10-20 00:42:28'),(56,'N3',7,'2013-10-04 01:42:28'),(57,'N1',8,'2013-10-18 02:42:28'),(58,'N2',9,'2013-10-30 03:42:28'),(59,'N3',10,'2013-10-22 04:42:28'),(60,'N1',1,'2013-10-10 05:42:28'),(61,'N2',2,'2013-10-08 06:42:28'),(62,'N3',3,'2013-10-09 07:42:28'),(63,'N1',4,'2013-10-08 08:42:28'),(64,'N2',5,'2013-10-16 09:42:28'),(65,'N3',6,'2013-10-19 10:42:28'),(66,'N1',7,'2013-10-06 13:42:28'),(67,'N2',8,'2013-10-02 14:42:28'),(68,'N3',9,'2013-10-10 15:42:28'),(69,'N1',10,'2013-10-15 16:42:28'),(70,'N2',1,'2013-10-18 17:42:28'),(71,'N3',2,'2013-10-23 18:42:28'),(72,'N1',3,'2013-10-21 19:42:28'),(73,'N2',4,'2013-10-04 20:42:28'),(74,'N3',5,'2013-10-17 21:42:28'),(75,'N1',6,'2013-10-16 22:42:28'),(76,'N2',7,'2013-10-01 23:42:28'),(77,'N3',8,'2013-10-06 00:42:28'),(78,'N1',9,'2013-10-12 01:42:28'),(79,'N2',10,'2013-10-11 02:42:28'),(80,'N3',1,'2013-10-12 03:42:28'),(81,'N1',2,'2013-10-15 04:42:28'),(82,'N2',3,'2013-10-24 05:42:28'),(83,'N3',4,'2013-10-04 06:42:28'),(84,'N1',5,'2013-10-25 07:42:28'),(85,'N2',6,'2013-10-25 08:42:28'),(86,'N3',7,'2013-10-18 09:42:28'),(87,'N1',8,'2013-10-21 10:42:28'),(88,'N2',9,'2013-10-15 13:42:28'),(89,'N3',10,'2013-10-14 14:42:28'),(90,'N1',1,'2013-10-29 15:42:28'),(91,'N2',2,'2013-10-07 16:42:28'),(92,'N3',3,'2013-10-17 17:42:28'),(93,'N1',4,'2013-10-14 18:42:28'),(94,'N2',5,'2013-10-27 19:42:28'),(95,'N3',6,'2013-10-20 20:42:28'),(96,'N1',7,'2013-10-04 21:42:28'),(97,'N2',8,'2013-10-05 22:42:28'),(98,'N3',9,'2013-10-20 23:42:28'),(99,'N1',10,'2013-10-08 00:42:28'),(100,'N2',1,'2013-10-24 01:42:28'),(101,'N3',2,'2013-10-24 02:42:28'),(102,'N1',3,'2013-10-04 03:42:28'),(103,'N2',4,'2013-10-30 04:42:28'),(104,'N3',5,'2013-10-05 05:42:28'),(105,'N1',6,'2013-10-22 06:42:28'),(106,'N2',7,'2013-10-02 07:42:28'),(107,'N3',8,'2013-10-22 08:42:28'),(108,'N1',9,'2013-10-24 09:42:28'),(109,'N2',10,'2013-10-24 10:42:28'),(110,'N3',1,'2013-10-08 13:42:28'),(111,'N1',2,'2013-10-18 14:42:28'),(112,'N2',3,'2013-10-02 15:42:28'),(113,'N3',4,'2013-10-21 16:42:28'),(114,'N1',5,'2013-10-08 17:42:28'),(115,'N2',6,'2013-10-12 18:42:28'),(116,'N3',7,'2013-10-09 19:42:28'),(117,'N1',8,'2013-10-23 20:42:28'),(118,'N2',9,'2013-10-21 21:42:28'),(119,'N3',10,'2013-10-24 22:42:28'),(120,'N1',1,'2013-10-19 23:42:28'),(121,'N2',2,'2013-10-18 00:42:28'),(122,'N3',3,'2013-10-08 01:42:28'),(123,'N1',4,'2013-10-11 02:42:28'),(124,'N2',5,'2013-10-13 03:42:28'),(125,'N3',6,'2013-10-03 04:42:28'),(126,'N1',7,'2013-10-15 05:42:28'),(127,'N2',8,'2013-10-23 06:42:28'),(128,'N3',9,'2013-10-30 07:42:28'),(129,'N1',10,'2013-10-12 08:42:28'),(130,'N2',1,'2013-10-06 09:42:28'),(131,'N3',2,'2013-10-09 10:42:28'),(132,'N1',3,'2013-10-04 13:42:28'),(133,'N2',4,'2013-10-03 14:42:28'),(134,'N3',5,'2013-10-14 15:42:28'),(135,'N1',6,'2013-10-17 16:42:28'),(136,'N2',7,'2013-10-22 17:42:28'),(137,'N3',8,'2013-10-04 18:42:28'),(138,'N1',9,'2013-10-19 19:42:28'),(139,'N2',10,'2013-10-07 20:42:28'),(140,'N3',1,'2013-10-28 21:42:28'),(141,'N1',2,'2013-10-05 22:42:28'),(142,'N2',3,'2013-10-27 23:42:28'),(143,'N3',4,'2013-10-28 00:42:28'),(144,'N1',5,'2013-10-20 01:42:28'),(145,'N2',6,'2013-10-30 02:42:28'),(146,'N3',7,'2013-10-25 03:42:28'),(147,'N1',8,'2013-10-25 04:42:28'),(148,'N2',9,'2013-10-02 05:42:28'),(149,'N3',10,'2013-10-06 06:42:28'),(150,'N1',1,'2013-10-24 07:42:28'),(151,'N2',2,'2013-10-29 08:42:28'),(152,'N3',3,'2013-10-23 09:42:28'),(153,'N1',4,'2013-10-13 10:42:28'),(154,'N2',5,'2013-10-16 13:42:28'),(155,'N3',6,'2013-10-06 14:42:28'),(156,'N1',7,'2013-10-22 15:42:28'),(157,'N2',8,'2013-10-19 16:42:28'),(158,'N3',9,'2013-10-29 17:42:28'),(159,'N1',10,'2013-10-25 18:42:28'),(160,'N2',1,'2013-10-13 19:42:28'),(161,'N3',2,'2013-10-10 20:42:28'),(162,'N1',3,'2013-10-13 21:42:28'),(163,'N2',4,'2013-10-28 22:42:28'),(164,'N3',5,'2013-10-09 23:42:28'),(165,'N1',6,'2013-10-09 00:42:28'),(166,'N2',7,'2013-10-02 01:42:28'),(167,'N3',8,'2013-10-13 02:42:28'),(168,'N1',9,'2013-10-23 03:42:28'),(169,'N2',10,'2013-10-29 04:42:28'),(170,'N3',1,'2013-10-15 05:42:28'),(171,'N1',2,'2013-10-27 06:42:28'),(172,'N2',3,'2013-10-02 07:42:28'),(173,'N3',4,'2013-10-16 08:42:28'),(174,'N1',5,'2013-10-03 09:42:28'),(175,'N2',6,'2013-10-04 10:42:28'),(176,'N3',7,'2013-10-04 13:42:28'),(177,'N1',8,'2013-10-21 14:42:28'),(178,'N2',9,'2013-10-06 15:42:28'),(179,'N3',10,'2013-10-11 16:42:28'),(180,'N1',1,'2013-10-24 17:42:28'),(181,'N2',2,'2013-10-07 18:42:28'),(182,'N3',3,'2013-10-05 19:42:28'),(183,'N1',4,'2013-10-26 20:42:28'),(184,'N2',5,'2013-10-22 21:42:28'),(185,'N3',6,'2013-10-13 22:42:28'),(186,'N1',7,'2013-10-05 23:42:28'),(187,'N2',8,'2013-10-22 00:42:28'),(188,'N3',9,'2013-10-03 01:42:28'),(189,'N1',10,'2013-10-07 02:42:28'),(190,'N2',1,'2013-10-15 03:42:28'),(191,'N3',2,'2013-10-07 04:42:28'),(192,'N1',3,'2013-10-11 05:42:28'),(193,'N2',4,'2013-10-24 06:42:28'),(194,'N3',5,'2013-10-29 07:42:28'),(195,'N1',6,'2013-10-15 08:42:28'),(196,'N2',7,'2013-10-12 09:42:28'),(197,'N3',8,'2013-10-26 10:42:28'),(198,'N1',9,'2013-10-04 13:42:28'),(199,'N2',10,'2013-10-07 14:42:28'),(200,'N3',1,'2013-10-20 15:42:28'),(201,'N1',2,'2013-10-23 16:42:28'),(202,'N2',3,'2013-10-16 17:42:28'),(203,'N3',4,'2013-10-02 18:42:28'),(204,'N1',5,'2013-10-23 19:42:28'),(205,'N2',6,'2013-10-08 20:42:28'),(206,'N3',7,'2013-10-02 21:42:28'),(207,'N1',8,'2013-10-21 22:42:28'),(208,'N2',9,'2013-10-18 23:42:28'),(209,'N3',10,'2013-10-11 00:42:28'),(210,'N1',1,'2013-10-23 01:42:28'),(211,'N2',2,'2013-10-29 02:42:28'),(212,'N3',3,'2013-10-18 03:42:28'),(213,'N1',4,'2013-10-06 04:42:28'),(214,'N2',5,'2013-10-16 05:42:28'),(215,'N3',6,'2013-10-11 06:42:28'),(216,'N1',7,'2013-10-22 07:42:28'),(217,'N2',8,'2013-10-23 08:42:28'),(218,'N3',9,'2013-10-25 09:42:28'),(219,'N1',10,'2013-10-28 10:42:28'),(220,'N2',1,'2013-10-04 13:42:28'),(221,'N3',2,'2013-10-14 14:42:28'),(222,'N1',3,'2013-10-14 15:42:28'),(223,'N2',4,'2013-10-13 16:42:28'),(224,'N3',5,'2013-10-17 17:42:28'),(225,'N1',6,'2013-10-12 18:42:28'),(226,'N2',7,'2013-10-15 19:42:28'),(227,'N3',8,'2013-10-29 20:42:28'),(228,'N1',9,'2013-10-21 21:42:28'),(229,'N2',10,'2013-10-08 22:42:28'),(230,'N3',1,'2013-10-24 23:42:28'),(231,'N1',2,'2013-10-08 00:42:28'),(232,'N2',3,'2013-10-06 01:42:28'),(233,'N3',4,'2013-10-26 02:42:28'),(234,'N1',5,'2013-10-20 03:42:28'),(235,'N2',6,'2013-10-07 04:42:28'),(236,'N3',7,'2013-10-22 05:42:28'),(237,'N1',8,'2013-10-21 06:42:28'),(238,'N2',9,'2013-10-29 07:42:28'),(239,'N3',10,'2013-10-28 08:42:28'),(240,'N1',1,'2013-10-10 09:42:28'),(241,'N2',2,'2013-10-14 10:42:28'),(242,'N3',3,'2013-10-01 13:42:28'),(243,'N1',4,'2013-10-10 14:42:28'),(244,'N2',5,'2013-10-18 15:42:28'),(245,'N3',6,'2013-10-08 16:42:28'),(246,'N1',7,'2013-10-22 17:42:28'),(247,'N2',8,'2013-10-05 18:42:28'),(248,'N3',9,'2013-10-21 19:42:28'),(249,'N1',10,'2013-10-23 20:42:28'),(250,'N2',1,'2013-10-11 21:42:28'),(251,'N3',2,'2013-10-10 22:42:28'),(252,'N1',3,'2013-10-17 23:42:28'),(253,'N2',4,'2013-10-23 00:42:28'),(254,'N3',5,'2013-10-06 01:42:28'),(255,'N1',6,'2013-10-17 02:42:28'),(256,'N2',7,'2013-10-03 03:42:28'),(257,'N3',8,'2013-10-27 04:42:28'),(258,'N1',9,'2013-10-10 05:42:28'),(259,'N2',10,'2013-10-29 06:42:28'),(260,'N3',1,'2013-10-09 07:42:28'),(261,'N1',2,'2013-10-22 08:42:28'),(262,'N2',3,'2013-10-23 09:42:28'),(263,'N3',4,'2013-10-08 10:42:28'),(264,'N1',5,'2013-10-20 13:42:28'),(265,'N2',6,'2013-10-02 14:42:28'),(266,'N3',7,'2013-10-08 15:42:28'),(267,'N1',8,'2013-10-15 16:42:28'),(268,'N2',9,'2013-10-05 17:42:28'),(269,'N3',10,'2013-10-18 18:42:28'),(270,'N1',1,'2013-10-11 19:42:28'),(271,'N2',2,'2013-10-12 20:42:28'),(272,'N3',3,'2013-10-09 21:42:28'),(273,'N1',4,'2013-10-11 22:42:28'),(274,'N2',5,'2013-10-04 23:42:28'),(275,'N3',6,'2013-10-16 00:42:28'),(276,'N1',7,'2013-10-08 01:42:28'),(277,'N2',8,'2013-10-03 02:42:28'),(278,'N3',9,'2013-10-30 03:42:28'),(279,'N1',10,'2013-10-05 04:42:28'),(280,'N2',1,'2013-10-25 05:42:28'),(281,'N3',2,'2013-10-12 06:42:28'),(282,'N1',3,'2013-10-02 07:42:28'),(283,'N2',4,'2013-10-04 08:42:28'),(284,'N3',5,'2013-10-09 09:42:28'),(285,'N1',6,'2013-10-12 10:42:28'),(286,'N2',7,'2013-10-16 13:42:28'),(287,'N3',8,'2013-10-21 14:42:28'),(288,'N1',9,'2013-10-20 15:42:28'),(289,'N2',10,'2013-10-23 16:42:28'),(290,'N3',1,'2013-10-04 17:42:28'),(291,'N1',2,'2013-10-14 18:42:28'),(292,'N2',3,'2013-10-06 19:42:28'),(293,'N3',4,'2013-10-20 20:42:28'),(294,'N1',5,'2013-10-21 21:42:28'),(295,'N2',6,'2013-10-14 22:42:28'),(296,'N3',7,'2013-10-24 23:42:28'),(297,'N1',8,'2013-10-04 00:42:28'),(298,'N2',9,'2013-10-02 01:42:28'),(299,'N3',10,'2013-10-28 02:42:28'),(300,'N1',1,'2013-10-09 03:42:28'),(301,'N2',2,'2013-10-10 04:42:28'),(302,'N3',3,'2013-10-28 05:42:28'),(303,'N1',4,'2013-10-20 06:42:28'),(304,'N2',5,'2013-10-26 07:42:28'),(305,'N3',6,'2013-10-27 08:42:28'),(306,'N1',7,'2013-10-28 09:42:28'),(307,'N2',8,'2013-10-04 10:42:28'),(308,'N3',9,'2013-10-07 13:42:28'),(309,'N1',10,'2013-10-27 14:42:28'),(310,'N2',1,'2013-10-06 15:42:28'),(311,'N3',2,'2013-10-13 16:42:28'),(312,'N1',3,'2013-10-07 17:42:28'),(313,'N2',4,'2013-10-06 18:42:28'),(314,'N3',5,'2013-10-26 19:42:28'),(315,'N1',6,'2013-10-22 20:42:28'),(316,'N2',7,'2013-10-08 21:42:28'),(317,'N3',8,'2013-10-02 22:42:28'),(318,'N1',9,'2013-10-20 23:42:28'),(319,'N2',10,'2013-10-02 00:42:28'),(320,'N3',1,'2013-10-22 01:42:28'),(321,'N1',2,'2013-10-13 02:42:28'),(322,'N2',3,'2013-10-25 03:42:28'),(323,'N3',4,'2013-10-28 04:42:28'),(324,'N1',5,'2013-10-26 05:42:28'),(325,'N2',6,'2013-10-20 06:42:28'),(326,'N3',7,'2013-10-28 07:42:28'),(327,'N1',8,'2013-10-16 08:42:28'),(328,'N2',9,'2013-10-22 09:42:28'),(329,'N3',10,'2013-10-26 10:42:28'),(330,'N1',1,'2013-10-03 13:42:28'),(331,'N2',2,'2013-10-08 14:42:28'),(332,'N3',3,'2013-10-21 15:42:28'),(333,'N1',4,'2013-10-22 16:42:28'),(334,'N2',5,'2013-10-22 17:42:28'),(335,'N3',6,'2013-10-12 18:42:28'),(336,'N1',7,'2013-10-25 19:42:28'),(337,'N2',8,'2013-10-07 20:42:28'),(338,'N3',9,'2013-10-19 21:42:28'),(339,'N1',10,'2013-10-06 22:42:28'),(340,'N2',1,'2013-10-04 23:42:28'),(341,'N3',2,'2013-10-27 00:42:28'),(342,'N1',3,'2013-10-16 01:42:28'),(343,'N2',4,'2013-10-28 02:42:28'),(344,'N3',5,'2013-10-03 03:42:28'),(345,'N1',6,'2013-10-05 04:42:28'),(346,'N2',7,'2013-10-14 05:42:28'),(347,'N3',8,'2013-10-23 06:42:28'),(348,'N1',9,'2013-10-23 07:42:28'),(349,'N2',10,'2013-10-08 08:42:28'),(350,'N3',1,'2013-10-22 09:42:28'),(351,'N1',2,'2013-10-27 10:42:28'),(352,'N2',3,'2013-10-11 13:42:28'),(353,'N3',4,'2013-10-22 14:42:28'),(354,'N1',5,'2013-10-01 15:42:28'),(355,'N2',6,'2013-10-25 16:42:28'),(356,'N3',7,'2013-10-21 17:42:28'),(357,'N1',8,'2013-10-27 18:42:28'),(358,'N2',9,'2013-10-23 19:42:28'),(359,'N3',10,'2013-10-09 20:42:28'),(360,'N1',1,'2013-10-16 21:42:28'),(361,'N2',2,'2013-10-08 22:42:28'),(362,'N3',3,'2013-10-01 23:42:28'),(363,'N1',4,'2013-10-25 00:42:28'),(364,'N2',5,'2013-10-02 01:42:28'),(365,'N3',6,'2013-10-06 02:42:28'),(366,'N1',7,'2013-10-10 03:42:28'),(367,'N2',8,'2013-10-12 04:42:28'),(368,'N3',9,'2013-10-17 05:42:28'),(369,'N1',10,'2013-10-12 06:42:28'),(370,'N2',1,'2013-10-26 07:42:28'),(371,'N3',2,'2013-10-12 08:42:28'),(372,'N1',3,'2013-10-11 09:42:28'),(373,'N2',4,'2013-10-18 10:42:28'),(374,'N3',5,'2013-10-13 13:42:28'),(375,'N1',6,'2013-10-12 14:42:28'),(376,'N2',7,'2013-10-26 15:42:28'),(377,'N3',8,'2013-10-08 16:42:28'),(378,'N1',9,'2013-10-18 17:42:28'),(379,'N2',10,'2013-10-02 18:42:28'),(380,'N3',1,'2013-10-11 19:42:28'),(381,'N1',2,'2013-10-15 20:42:28'),(382,'N2',3,'2013-10-15 21:42:28'),(383,'N3',4,'2013-10-23 22:42:28'),(384,'N1',5,'2013-10-17 23:42:28'),(385,'N2',6,'2013-10-03 00:42:28'),(386,'N3',7,'2013-10-06 01:42:28'),(387,'N1',8,'2013-10-06 02:42:28'),(388,'N2',9,'2013-10-26 03:42:28'),(389,'N3',10,'2013-10-26 04:42:28'),(390,'N1',1,'2013-10-03 05:42:28'),(391,'N2',2,'2013-10-23 06:42:28'),(392,'N3',3,'2013-10-18 07:42:28'),(393,'N1',4,'2013-10-03 08:42:28'),(394,'N2',5,'2013-10-26 09:42:28'),(395,'N3',6,'2013-10-26 10:42:28'),(396,'N1',7,'2013-10-26 13:42:28'),(397,'N2',8,'2013-10-18 14:42:28'),(398,'N3',9,'2013-10-14 15:42:28'),(399,'N1',10,'2013-10-09 16:42:28'),(400,'N2',1,'2013-10-13 17:42:28'),(401,'N3',2,'2013-10-12 18:42:28'),(402,'N1',3,'2013-10-12 19:42:28'),(403,'N2',4,'2013-10-27 20:42:28'),(404,'N3',5,'2013-10-16 21:42:28'),(405,'N1',6,'2013-10-19 22:42:28'),(406,'N2',7,'2013-10-21 23:42:28'),(407,'N3',8,'2013-10-04 00:42:28'),(408,'N1',9,'2013-10-26 01:42:28'),(409,'N2',10,'2013-10-25 02:42:28'),(410,'N3',1,'2013-10-26 03:42:28'),(411,'N1',2,'2013-10-25 04:42:28'),(412,'N2',3,'2013-10-25 05:42:28'),(413,'N3',4,'2013-10-21 06:42:28'),(414,'N1',5,'2013-10-03 07:42:28'),(415,'N2',6,'2013-10-21 08:42:28'),(416,'N3',7,'2013-10-07 09:42:28'),(417,'N1',8,'2013-10-12 10:42:28'),(418,'N2',9,'2013-10-20 13:42:28'),(419,'N3',10,'2013-10-13 14:42:28'),(420,'N1',1,'2013-10-20 15:42:28'),(421,'N2',2,'2013-10-05 16:42:28'),(422,'N3',3,'2013-10-27 17:42:28'),(423,'N1',4,'2013-10-11 18:42:28'),(424,'N2',5,'2013-10-06 19:42:28'),(425,'N3',6,'2013-10-01 20:42:28'),(426,'N1',7,'2013-10-10 21:42:28'),(427,'N2',8,'2013-10-20 22:42:28'),(428,'N3',9,'2013-10-27 23:42:28'),(429,'N1',10,'2013-10-03 00:42:28'),(430,'N2',1,'2013-10-29 01:42:28'),(431,'N3',2,'2013-10-03 02:42:28'),(432,'N1',3,'2013-10-17 03:42:28'),(433,'N2',4,'2013-10-17 04:42:28'),(434,'N3',5,'2013-10-26 05:42:28'),(435,'N1',6,'2013-10-12 06:42:28'),(436,'N2',7,'2013-10-21 07:42:28'),(437,'N3',8,'2013-10-04 08:42:28'),(438,'N1',9,'2013-10-28 09:42:28'),(439,'N2',10,'2013-10-13 10:42:28'),(440,'N3',1,'2013-10-02 13:42:28'),(441,'N1',2,'2013-10-16 14:42:28'),(442,'N2',3,'2013-10-15 15:42:28'),(443,'N3',4,'2013-10-17 16:42:28'),(444,'N1',5,'2013-10-19 17:42:28'),(445,'N2',6,'2013-10-21 18:42:28'),(446,'N3',7,'2013-10-23 19:42:28'),(447,'N1',8,'2013-10-05 20:42:28'),(448,'N2',9,'2013-10-10 21:42:28'),(449,'N3',10,'2013-10-12 22:42:28'),(450,'N1',1,'2013-10-12 23:42:28'),(451,'N2',2,'2013-10-18 00:42:28'),(452,'N3',3,'2013-10-16 01:42:28'),(453,'N1',4,'2013-10-20 02:42:28'),(454,'N2',5,'2013-10-20 03:42:28'),(455,'N3',6,'2013-10-05 04:42:28'),(456,'N1',7,'2013-10-14 05:42:28'),(457,'N2',8,'2013-10-16 06:42:28'),(458,'N3',9,'2013-10-13 07:42:28'),(459,'N1',10,'2013-10-27 08:42:28'),(460,'N2',1,'2013-10-20 09:42:28'),(461,'N3',2,'2013-10-17 10:42:28'),(462,'N1',3,'2013-10-19 13:42:28'),(463,'N2',4,'2013-10-10 14:42:28'),(464,'N3',5,'2013-10-25 15:42:28'),(465,'N1',6,'2013-10-24 16:42:28'),(466,'N2',7,'2013-10-03 17:42:28'),(467,'N3',8,'2013-10-08 18:42:28'),(468,'N1',9,'2013-10-19 19:42:28'),(469,'N2',10,'2013-10-07 20:42:28'),(470,'N3',1,'2013-10-28 21:42:28'),(471,'N1',2,'2013-10-04 22:42:28'),(472,'N2',3,'2013-10-24 23:42:28'),(473,'N3',4,'2013-10-09 00:42:28'),(474,'N1',5,'2013-10-20 01:42:28'),(475,'N2',6,'2013-10-17 02:42:28'),(476,'N3',7,'2013-10-09 03:42:28'),(477,'N1',8,'2013-10-03 04:42:28'),(478,'N2',9,'2013-10-13 05:42:28'),(479,'N3',10,'2013-10-03 06:42:28'),(480,'N1',1,'2013-10-25 07:42:28'),(481,'N2',2,'2013-10-20 08:42:28'),(482,'N3',3,'2013-10-15 09:42:28'),(483,'N1',4,'2013-10-24 10:42:28'),(484,'N2',5,'2013-10-29 13:42:28'),(485,'N3',6,'2013-10-04 14:42:28'),(486,'N1',7,'2013-10-09 15:42:28'),(487,'N2',8,'2013-10-20 16:42:28'),(488,'N3',9,'2013-10-15 17:42:28'),(489,'N1',10,'2013-10-01 18:42:28'),(490,'N2',1,'2013-10-03 19:42:28'),(491,'N3',2,'2013-10-22 20:42:28'),(492,'N1',3,'2013-10-13 21:42:28'),(493,'N2',4,'2013-10-19 22:42:28'),(494,'N3',5,'2013-10-20 23:42:28'),(495,'N1',6,'2013-10-05 00:42:28'),(496,'N2',7,'2013-10-02 01:42:28'),(497,'N3',8,'2013-10-17 02:42:28'),(498,'N1',9,'2013-10-06 03:42:28'),(499,'N2',10,'2013-10-07 04:42:28'),(500,'N3',1,'2013-10-24 05:42:28'),(501,'N1',2,'2013-10-21 06:42:28'),(502,'N2',3,'2013-10-29 07:42:28'),(503,'N3',4,'2013-10-21 08:42:28'),(504,'N1',5,'2013-10-22 09:42:28'),(505,'N2',6,'2013-10-21 10:42:28'),(506,'N3',7,'2013-10-24 13:42:28'),(507,'N1',8,'2013-10-07 14:42:28'),(508,'N2',9,'2013-10-09 15:42:28'),(509,'N3',10,'2013-10-11 16:42:28'),(510,'N1',1,'2013-10-19 17:42:28'),(511,'N2',2,'2013-10-12 18:42:28'),(512,'N3',3,'2013-10-03 19:42:28'),(513,'N1',4,'2013-10-25 20:42:28'),(514,'N2',5,'2013-10-04 21:42:28'),(515,'N3',6,'2013-10-18 22:42:28'),(516,'N1',7,'2013-10-08 23:42:28'),(517,'N2',8,'2013-10-17 00:42:28'),(518,'N3',9,'2013-10-20 01:42:28'),(519,'N1',10,'2013-10-22 02:42:28'),(520,'N2',1,'2013-10-24 03:42:28'),(521,'N3',2,'2013-10-07 04:42:28'),(522,'N1',3,'2013-10-19 05:42:28'),(523,'N2',4,'2013-10-28 06:42:28'),(524,'N3',5,'2013-10-10 07:42:28'),(525,'N1',6,'2013-10-10 08:42:28'),(526,'N2',7,'2013-10-10 09:42:28'),(527,'N3',8,'2013-10-30 10:42:28'),(528,'N1',9,'2013-10-11 13:42:28'),(529,'N2',10,'2013-10-22 14:42:28'),(530,'N3',1,'2013-10-12 15:42:28'),(531,'N1',2,'2013-10-02 16:42:28'),(532,'N2',3,'2013-10-07 17:42:28'),(533,'N3',4,'2013-10-16 18:42:28'),(534,'N1',5,'2013-10-15 19:42:28'),(535,'N2',6,'2013-10-15 20:42:28'),(536,'N3',7,'2013-10-13 21:42:28'),(537,'N1',8,'2013-10-11 22:42:28'),(538,'N2',9,'2013-10-09 23:42:28'),(539,'N3',10,'2013-10-07 00:42:28'),(540,'N1',1,'2013-10-05 01:42:28'),(541,'N2',2,'2013-10-10 02:42:28'),(542,'N3',3,'2013-10-21 03:42:28'),(543,'N1',4,'2013-10-15 04:42:28'),(544,'N2',5,'2013-10-12 05:42:28'),(545,'N3',6,'2013-10-16 06:42:28'),(546,'N1',7,'2013-10-04 07:42:28'),(547,'N2',8,'2013-10-12 08:42:28'),(548,'N3',9,'2013-10-20 09:42:28'),(549,'N1',10,'2013-10-12 10:42:28'),(550,'N2',1,'2013-10-17 13:42:28'),(551,'N3',2,'2013-10-22 14:42:28'),(552,'N1',3,'2013-10-26 15:42:28'),(553,'N2',4,'2013-10-29 16:42:28'),(554,'N3',5,'2013-10-12 17:42:28'),(555,'N1',6,'2013-10-13 18:42:28'),(556,'N2',7,'2013-10-06 19:42:28'),(557,'N3',8,'2013-10-18 20:42:28'),(558,'N1',9,'2013-10-12 21:42:28'),(559,'N2',10,'2013-10-02 22:42:28'),(560,'N3',1,'2013-10-03 23:42:28'),(561,'N1',2,'2013-10-27 00:42:28'),(562,'N2',3,'2013-10-23 01:42:28'),(563,'N3',4,'2013-10-17 02:42:28'),(564,'N1',5,'2013-10-23 03:42:28'),(565,'N2',6,'2013-10-03 04:42:28'),(566,'N3',7,'2013-10-26 05:42:28'),(567,'N1',8,'2013-10-05 06:42:28'),(568,'N2',9,'2013-10-06 07:42:28'),(569,'N3',10,'2013-10-09 08:42:28'),(570,'N1',1,'2013-10-15 09:42:28'),(571,'N2',2,'2013-10-07 10:42:28'),(572,'N3',3,'2013-10-21 13:42:28'),(573,'N1',4,'2013-10-16 14:42:28'),(574,'N2',5,'2013-10-13 15:42:28'),(575,'N3',6,'2013-10-04 16:42:28'),(576,'N1',7,'2013-10-26 17:42:28'),(577,'N2',8,'2013-10-24 18:42:28'),(578,'N3',9,'2013-10-27 19:42:28'),(579,'N1',10,'2013-10-25 20:42:28'),(580,'N2',1,'2013-10-16 21:42:28'),(581,'N3',2,'2013-10-08 22:42:28'),(582,'N1',3,'2013-10-15 23:42:28'),(583,'N2',4,'2013-10-29 00:42:28'),(584,'N3',5,'2013-10-06 01:42:28'),(585,'N1',6,'2013-10-04 02:42:28'),(586,'N2',7,'2013-10-28 03:42:28'),(587,'N3',8,'2013-10-25 04:42:28'),(588,'N1',9,'2013-10-10 05:42:28'),(589,'N2',10,'2013-10-07 06:42:28'),(590,'N3',1,'2013-10-27 07:42:28'),(591,'N1',2,'2013-10-19 08:42:28'),(592,'N2',3,'2013-10-19 09:42:28'),(593,'N3',4,'2013-10-29 10:42:28'),(594,'N1',5,'2013-10-10 13:42:28'),(595,'N2',6,'2013-10-27 14:42:28'),(596,'N3',7,'2013-10-19 15:42:28'),(597,'N1',8,'2013-10-10 16:42:28'),(598,'N2',9,'2013-10-24 17:42:28'),(599,'N3',10,'2013-10-28 18:42:28'),(600,'N1',1,'2013-10-29 19:42:28'),(601,'N2',2,'2013-10-03 20:42:28'),(602,'N3',3,'2013-10-15 21:42:28'),(603,'N1',4,'2013-10-14 22:42:28'),(604,'N2',5,'2013-10-17 23:42:28'),(605,'N3',6,'2013-10-07 00:42:28'),(606,'N1',7,'2013-10-09 01:42:28'),(607,'N2',8,'2013-10-17 02:42:28'),(608,'N3',9,'2013-10-18 03:42:28'),(609,'N1',10,'2013-10-24 04:42:28'),(610,'N2',1,'2013-10-25 05:42:28'),(611,'N3',2,'2013-10-05 06:42:28'),(612,'N1',3,'2013-10-16 07:42:28'),(613,'N2',4,'2013-10-04 08:42:28'),(614,'N3',5,'2013-10-08 09:42:28'),(615,'N1',6,'2013-10-13 10:42:28'),(616,'N2',7,'2013-10-06 13:42:28'),(617,'N3',8,'2013-10-20 14:42:28'),(618,'N1',9,'2013-10-08 15:42:28'),(619,'N2',10,'2013-10-19 16:42:28'),(620,'N3',1,'2013-10-26 17:42:28'),(621,'N1',2,'2013-10-16 18:42:28'),(622,'N2',3,'2013-10-18 19:42:28'),(623,'N3',4,'2013-10-24 20:42:28'),(624,'N1',5,'2013-10-13 21:42:28'),(625,'N2',6,'2013-10-17 22:42:28'),(626,'N3',7,'2013-10-10 23:42:28'),(627,'N1',8,'2013-10-03 00:42:28'),(628,'N2',9,'2013-10-03 01:42:28'),(629,'N3',10,'2013-10-14 02:42:28'),(630,'N1',1,'2013-10-11 03:42:28'),(631,'N2',2,'2013-10-22 04:42:28'),(632,'N3',3,'2013-10-26 05:42:28'),(633,'N1',4,'2013-10-05 06:42:28'),(634,'N2',5,'2013-10-09 07:42:28'),(635,'N3',6,'2013-10-10 08:42:28'),(636,'N1',7,'2013-10-07 09:42:28'),(637,'N2',8,'2013-10-14 10:42:28'),(638,'N3',9,'2013-10-29 13:42:28'),(639,'N1',10,'2013-10-27 14:42:28'),(640,'N2',1,'2013-10-22 15:42:28'),(641,'N3',2,'2013-10-19 16:42:28'),(642,'N1',3,'2013-10-17 17:42:28'),(643,'N2',4,'2013-10-19 18:42:28'),(644,'N3',5,'2013-10-19 19:42:28'),(645,'N1',6,'2013-10-25 20:42:28'),(646,'N2',7,'2013-10-22 21:42:28'),(647,'N3',8,'2013-10-11 22:42:28'),(648,'N1',9,'2013-10-26 23:42:28'),(649,'N2',10,'2013-10-10 00:42:28'),(650,'N3',1,'2013-10-13 01:42:28'),(651,'N1',2,'2013-10-08 02:42:28'),(652,'N2',3,'2013-10-30 03:42:28'),(653,'N3',4,'2013-10-29 04:42:28'),(654,'N1',5,'2013-10-12 05:42:28'),(655,'N2',6,'2013-10-23 06:42:28'),(656,'N3',7,'2013-10-05 07:42:28'),(657,'N1',8,'2013-10-21 08:42:28'),(658,'N2',9,'2013-10-08 09:42:28'),(659,'N3',10,'2013-10-23 10:42:28'),(660,'N1',1,'2013-10-13 13:42:28'),(661,'N2',2,'2013-10-16 14:42:28'),(662,'N3',3,'2013-10-26 15:42:28'),(663,'N1',4,'2013-10-01 16:42:28'),(664,'N2',5,'2013-10-09 17:42:28'),(665,'N3',6,'2013-10-14 18:42:28'),(666,'N1',7,'2013-10-15 19:42:28'),(667,'N2',8,'2013-10-29 20:42:28'),(668,'N3',9,'2013-10-15 21:42:28'),(669,'N1',10,'2013-10-07 22:42:28'),(670,'N2',1,'2013-10-03 23:42:28'),(671,'N3',2,'2013-10-20 00:42:28'),(672,'N1',3,'2013-10-12 01:42:28'),(673,'N2',4,'2013-10-13 02:42:28'),(674,'N3',5,'2013-10-23 03:42:28'),(675,'N1',6,'2013-10-25 04:42:28'),(676,'N2',7,'2013-10-07 05:42:28'),(677,'N3',8,'2013-10-11 06:42:28'),(678,'N1',9,'2013-10-24 07:42:28'),(679,'N2',10,'2013-10-07 08:42:28'),(680,'N3',1,'2013-10-14 09:42:28'),(681,'N1',2,'2013-10-03 10:42:28'),(682,'N2',3,'2013-10-07 13:42:28'),(683,'N3',4,'2013-10-15 14:42:28'),(684,'N1',5,'2013-10-28 15:42:28'),(685,'N2',6,'2013-10-05 16:42:28'),(686,'N3',7,'2013-10-11 17:42:28'),(687,'N1',8,'2013-10-15 18:42:28'),(688,'N2',9,'2013-10-09 19:42:28'),(689,'N3',10,'2013-10-04 20:42:28'),(690,'N1',1,'2013-10-27 21:42:28'),(691,'N2',2,'2013-10-02 22:42:28'),(692,'N3',3,'2013-10-10 23:42:28'),(693,'N1',4,'2013-10-18 00:42:28'),(694,'N2',5,'2013-10-22 01:42:28'),(695,'N3',6,'2013-10-04 02:42:28'),(696,'N1',7,'2013-10-04 03:42:28'),(697,'N2',8,'2013-10-05 04:42:28'),(698,'N3',9,'2013-10-08 05:42:28'),(699,'N1',10,'2013-10-23 06:42:28'),(700,'N2',1,'2013-10-24 07:42:28'),(701,'N3',2,'2013-10-17 08:42:28'),(702,'N1',3,'2013-10-03 09:42:28'),(703,'N2',4,'2013-10-23 10:42:28'),(704,'N3',5,'2013-10-08 13:42:28'),(705,'N1',6,'2013-10-23 14:42:28'),(706,'N2',7,'2013-10-07 15:42:28'),(707,'N3',8,'2013-10-13 16:42:28'),(708,'N1',9,'2013-10-13 17:42:28'),(709,'N2',10,'2013-10-17 18:42:28'),(710,'N3',1,'2013-10-01 19:42:28'),(711,'N1',2,'2013-10-28 20:42:28'),(712,'N2',3,'2013-10-27 21:42:28'),(713,'N3',4,'2013-10-02 22:42:28'),(714,'N1',5,'2013-10-08 23:42:28'),(715,'N2',6,'2013-10-09 00:42:28'),(716,'N3',7,'2013-10-13 01:42:28'),(717,'N1',8,'2013-10-28 02:42:28'),(718,'N2',9,'2013-10-19 03:42:28'),(719,'N3',10,'2013-10-24 04:42:28'),(720,'N1',1,'2013-10-12 05:42:28'),(721,'N2',2,'2013-10-22 06:42:28'),(722,'N3',3,'2013-10-14 07:42:28'),(723,'N1',4,'2013-10-09 08:42:28'),(724,'N2',5,'2013-10-09 09:42:28'),(725,'N3',6,'2013-10-30 10:42:28'),(726,'N1',7,'2013-10-15 13:42:28'),(727,'N2',8,'2013-10-23 14:42:28'),(728,'N3',9,'2013-10-14 15:42:28'),(729,'N1',10,'2013-10-22 16:42:28'),(730,'N2',1,'2013-10-12 17:42:28'),(731,'N3',2,'2013-10-02 18:42:28'),(732,'N1',3,'2013-10-18 19:42:28'),(733,'N2',4,'2013-10-20 20:42:28'),(734,'N3',5,'2013-10-05 21:42:28'),(735,'N1',6,'2013-10-10 22:42:28'),(736,'N2',7,'2013-10-21 23:42:28'),(737,'N3',8,'2013-10-08 00:42:28'),(738,'N1',9,'2013-10-02 01:42:28'),(739,'N2',10,'2013-10-23 02:42:28'),(740,'N3',1,'2013-10-24 03:42:28'),(741,'N1',2,'2013-10-27 04:42:28'),(742,'N2',3,'2013-10-05 05:42:28'),(743,'N3',4,'2013-10-29 06:42:28'),(744,'N1',5,'2013-10-21 07:42:28'),(745,'N2',6,'2013-10-05 08:42:28'),(746,'N3',7,'2013-10-12 09:42:28'),(747,'N1',8,'2013-10-22 10:42:28'),(748,'N2',9,'2013-10-13 13:42:28'),(749,'N3',10,'2013-10-05 14:42:28'),(750,'N1',1,'2013-10-25 15:42:28'),(751,'N2',2,'2013-10-21 16:42:28'),(752,'N3',3,'2013-10-01 17:42:28'),(753,'N1',4,'2013-10-16 18:42:28'),(754,'N2',5,'2013-10-24 19:42:28'),(755,'N3',6,'2013-10-25 20:42:28'),(756,'N1',7,'2013-10-03 21:42:28'),(757,'N2',8,'2013-10-23 22:42:28'),(758,'N3',9,'2013-10-09 23:42:28'),(759,'N1',10,'2013-10-29 00:42:28'),(760,'N2',1,'2013-10-30 01:42:28'),(761,'N3',2,'2013-10-11 02:42:28'),(762,'N1',3,'2013-10-26 03:42:28'),(763,'N2',4,'2013-10-13 04:42:28'),(764,'N3',5,'2013-10-05 05:42:28'),(765,'N1',6,'2013-10-23 06:42:28'),(766,'N2',7,'2013-10-13 07:42:28'),(767,'N3',8,'2013-10-18 08:42:28'),(768,'N1',9,'2013-10-12 09:42:28'),(769,'N2',10,'2013-10-17 10:42:28'),(770,'N3',1,'2013-10-29 13:42:28'),(771,'N1',2,'2013-10-13 14:42:28'),(772,'N2',3,'2013-10-05 15:42:28'),(773,'N3',4,'2013-10-01 16:42:28'),(774,'N1',5,'2013-10-15 17:42:28'),(775,'N2',6,'2013-10-23 18:42:28'),(776,'N3',7,'2013-10-16 19:42:28'),(777,'N1',8,'2013-10-23 20:42:28'),(778,'N2',9,'2013-10-26 21:42:28'),(779,'N3',10,'2013-10-29 22:42:28'),(780,'N1',1,'2013-10-02 23:42:28'),(781,'N2',2,'2013-10-30 00:42:28'),(782,'N3',3,'2013-10-19 01:42:28'),(783,'N1',4,'2013-10-17 02:42:28'),(784,'N2',5,'2013-10-12 03:42:28'),(785,'N3',6,'2013-10-12 04:42:28'),(786,'N1',7,'2013-10-07 05:42:28'),(787,'N2',8,'2013-10-20 06:42:28'),(788,'N3',9,'2013-10-15 07:42:28'),(789,'N1',10,'2013-10-22 08:42:28'),(790,'N2',1,'2013-10-17 09:42:28'),(791,'N3',2,'2013-10-04 10:42:28'),(792,'N1',3,'2013-10-18 13:42:28'),(793,'N2',4,'2013-10-10 14:42:28'),(794,'N3',5,'2013-10-12 15:42:28'),(795,'N1',6,'2013-10-26 16:42:28'),(796,'N2',7,'2013-10-25 17:42:28'),(797,'N3',8,'2013-10-21 18:42:28'),(798,'N1',9,'2013-10-18 19:42:28'),(799,'N2',10,'2013-10-25 20:42:28'),(800,'N3',1,'2013-10-10 21:42:28'),(801,'N1',2,'2013-10-07 22:42:28'),(802,'N2',3,'2013-10-09 23:42:28'),(803,'N3',4,'2013-10-07 00:42:28'),(804,'N1',5,'2013-10-19 01:42:28'),(805,'N2',6,'2013-10-15 02:42:28'),(806,'N3',7,'2013-10-24 03:42:28'),(807,'N1',8,'2013-10-11 04:42:28'),(808,'N2',9,'2013-10-22 05:42:28'),(809,'N3',10,'2013-10-08 06:42:28'),(810,'N1',1,'2013-10-23 07:42:28'),(811,'N2',2,'2013-10-08 08:42:28'),(812,'N3',3,'2013-10-24 09:42:28'),(813,'N1',4,'2013-10-12 10:42:28'),(814,'N2',5,'2013-10-15 13:42:28'),(815,'N3',6,'2013-10-07 14:42:28'),(816,'N1',7,'2013-10-01 15:42:28'),(817,'N2',8,'2013-10-12 16:42:28'),(818,'N3',9,'2013-10-04 17:42:28'),(819,'N1',10,'2013-10-14 18:42:28'),(820,'N2',1,'2013-10-17 19:42:28'),(821,'N3',2,'2013-10-27 20:42:28'),(822,'N1',3,'2013-10-16 21:42:28'),(823,'N2',4,'2013-10-25 22:42:28'),(824,'N3',5,'2013-10-29 23:42:28'),(825,'N1',6,'2013-10-29 00:42:28'),(826,'N2',7,'2013-10-11 01:42:28'),(827,'N3',8,'2013-10-16 02:42:28'),(828,'N1',9,'2013-10-04 03:42:28'),(829,'N2',10,'2013-10-09 04:42:28'),(830,'N3',1,'2013-10-04 05:42:28'),(831,'N1',2,'2013-10-28 06:42:28'),(832,'N2',3,'2013-10-11 07:42:28'),(833,'N3',4,'2013-10-15 08:42:28'),(834,'N1',5,'2013-10-25 09:42:28'),(835,'N2',6,'2013-10-06 10:42:28'),(836,'N3',7,'2013-10-06 13:42:28'),(837,'N1',8,'2013-10-06 14:42:28'),(838,'N2',9,'2013-10-05 15:42:28'),(839,'N3',10,'2013-10-14 16:42:28'),(840,'N1',1,'2013-10-10 17:42:28'),(841,'N2',2,'2013-10-02 18:42:28'),(842,'N3',3,'2013-10-08 19:42:28'),(843,'N1',4,'2013-10-24 20:42:28'),(844,'N2',5,'2013-10-19 21:42:28'),(845,'N3',6,'2013-10-19 22:42:28'),(846,'N1',7,'2013-10-25 23:42:28'),(847,'N2',8,'2013-10-21 00:42:28'),(848,'N3',9,'2013-10-18 01:42:28'),(849,'N1',10,'2013-10-14 02:42:28'),(850,'N2',1,'2013-10-07 03:42:28'),(851,'N3',2,'2013-10-26 04:42:28'),(852,'N1',3,'2013-10-04 05:42:28'),(853,'N2',4,'2013-10-23 06:42:28'),(854,'N3',5,'2013-10-04 07:42:28'),(855,'N1',6,'2013-10-12 08:42:28'),(856,'N2',7,'2013-10-25 09:42:28'),(857,'N3',8,'2013-10-24 10:42:28'),(858,'N1',9,'2013-10-02 13:42:28'),(859,'N2',10,'2013-10-11 14:42:28'),(860,'N3',1,'2013-10-13 15:42:28'),(861,'N1',2,'2013-10-09 16:42:28'),(862,'N2',3,'2013-10-03 17:42:28'),(863,'N3',4,'2013-10-24 18:42:28'),(864,'N1',5,'2013-10-17 19:42:28'),(865,'N2',6,'2013-10-11 20:42:28'),(866,'N3',7,'2013-10-25 21:42:28'),(867,'N1',8,'2013-10-29 22:42:28'),(868,'N2',9,'2013-10-01 23:42:28'),(869,'N3',10,'2013-10-17 00:42:28'),(870,'N1',1,'2013-10-07 01:42:28'),(871,'N2',2,'2013-10-12 02:42:28'),(872,'N3',3,'2013-10-29 03:42:28'),(873,'N1',4,'2013-10-23 04:42:28'),(874,'N2',5,'2013-10-20 05:42:28'),(875,'N3',6,'2013-10-21 06:42:28'),(876,'N1',7,'2013-10-14 07:42:28'),(877,'N2',8,'2013-10-22 08:42:28'),(878,'N3',9,'2013-10-13 09:42:28'),(879,'N1',10,'2013-10-17 10:42:28'),(880,'N2',1,'2013-10-13 13:42:28'),(881,'N3',2,'2013-10-27 14:42:28'),(882,'N1',3,'2013-10-27 15:42:28'),(883,'N2',4,'2013-10-01 16:42:28'),(884,'N3',5,'2013-10-15 17:42:28'),(885,'N1',6,'2013-10-17 18:42:28'),(886,'N2',7,'2013-10-12 19:42:28'),(887,'N3',8,'2013-10-12 20:42:28'),(888,'N1',9,'2013-10-25 21:42:28'),(889,'N2',10,'2013-10-14 22:42:28'),(890,'N3',1,'2013-10-29 23:42:28'),(891,'N1',2,'2013-10-08 00:42:28'),(892,'N2',3,'2013-10-20 01:42:28'),(893,'N3',4,'2013-10-18 02:42:28'),(894,'N1',5,'2013-10-29 03:42:28'),(895,'N2',6,'2013-10-11 04:42:28'),(896,'N3',7,'2013-10-11 05:42:28'),(897,'N1',8,'2013-10-06 06:42:28'),(898,'N2',9,'2013-10-04 07:42:28'),(899,'N3',10,'2013-10-27 08:42:28'),(900,'N1',1,'2013-10-25 09:42:28'),(901,'N2',2,'2013-10-13 10:42:28'),(902,'N3',3,'2013-10-28 13:42:28'),(903,'N1',4,'2013-10-23 14:42:28'),(904,'N2',5,'2013-10-20 15:42:28'),(905,'N3',6,'2013-10-21 16:42:28'),(906,'N1',7,'2013-10-08 17:42:28'),(907,'N2',8,'2013-10-07 18:42:28'),(908,'N3',9,'2013-10-29 19:42:28'),(909,'N1',10,'2013-10-01 20:42:28'),(910,'N2',1,'2013-10-15 21:42:28'),(911,'N3',2,'2013-10-08 22:42:28'),(912,'N1',3,'2013-10-18 23:42:28'),(913,'N2',4,'2013-10-16 00:42:28'),(914,'N3',5,'2013-10-24 01:42:28'),(915,'N1',6,'2013-10-10 02:42:28'),(916,'N2',7,'2013-10-17 03:42:28'),(917,'N3',8,'2013-10-11 04:42:28'),(918,'N1',9,'2013-10-03 05:42:28'),(919,'N2',10,'2013-10-30 06:42:28'),(920,'N3',1,'2013-10-23 07:42:28'),(921,'N1',2,'2013-10-28 08:42:28'),(922,'N2',3,'2013-10-22 09:42:28'),(923,'N3',4,'2013-10-14 10:42:28'),(924,'N1',5,'2013-10-17 13:42:28'),(925,'N2',6,'2013-10-11 14:42:28'),(926,'N3',7,'2013-10-03 15:42:28'),(927,'N1',8,'2013-10-17 16:42:28'),(928,'N2',9,'2013-10-05 17:42:28'),(929,'N3',10,'2013-10-25 18:42:28'),(930,'N1',1,'2013-10-26 19:42:28'),(931,'N2',2,'2013-10-01 20:42:28'),(932,'N3',3,'2013-10-08 21:42:28'),(933,'N1',4,'2013-10-17 22:42:28'),(934,'N2',5,'2013-10-01 23:42:28'),(935,'N3',6,'2013-10-27 00:42:28'),(936,'N1',7,'2013-10-03 01:42:28'),(937,'N2',8,'2013-10-13 02:42:28'),(938,'N3',9,'2013-10-25 03:42:28'),(939,'N1',10,'2013-10-02 04:42:28'),(940,'N2',1,'2013-10-29 05:42:28'),(941,'N3',2,'2013-10-11 06:42:28'),(942,'N1',3,'2013-10-12 07:42:28'),(943,'N2',4,'2013-10-09 08:42:28'),(944,'N3',5,'2013-10-02 09:42:28'),(945,'N1',6,'2013-10-26 10:42:28'),(946,'N2',7,'2013-10-19 13:42:28'),(947,'N3',8,'2013-10-12 14:42:28'),(948,'N1',9,'2013-10-26 15:42:28'),(949,'N2',10,'2013-10-26 16:42:28'),(950,'N3',1,'2013-10-18 17:42:28'),(951,'N1',2,'2013-10-04 18:42:28'),(952,'N2',3,'2013-10-12 19:42:28'),(953,'N3',4,'2013-10-17 20:42:28'),(954,'N1',5,'2013-10-12 21:42:28'),(955,'N2',6,'2013-10-29 22:42:28'),(956,'N3',7,'2013-10-19 23:42:28'),(957,'N1',8,'2013-10-22 00:42:28'),(958,'N2',9,'2013-10-21 01:42:28'),(959,'N3',10,'2013-10-28 02:42:28'),(960,'N1',1,'2013-10-08 03:42:28'),(961,'N2',2,'2013-10-09 04:42:28'),(962,'N3',3,'2013-10-23 05:42:28'),(963,'N1',4,'2013-10-07 06:42:28'),(964,'N2',5,'2013-10-04 07:42:28'),(965,'N3',6,'2013-10-14 08:42:28'),(966,'N1',7,'2013-10-08 09:42:28'),(967,'N2',8,'2013-10-03 10:42:28'),(968,'N3',9,'2013-10-22 13:42:28'),(969,'N1',10,'2013-10-25 14:42:28'),(970,'N2',1,'2013-10-27 15:42:28'),(971,'N3',2,'2013-10-06 16:42:28'),(972,'N1',3,'2013-10-19 17:42:28'),(973,'N2',4,'2013-10-28 18:42:28'),(974,'N3',5,'2013-10-21 19:42:28'),(975,'N1',6,'2013-10-12 20:42:28'),(976,'N2',7,'2013-10-29 21:42:28'),(977,'N3',8,'2013-10-18 22:42:28'),(978,'N1',9,'2013-10-07 23:42:28'),(979,'N2',10,'2013-10-14 00:42:28'),(980,'N3',1,'2013-10-10 01:42:28'),(981,'N1',2,'2013-10-27 02:42:28'),(982,'N2',3,'2013-10-05 03:42:28'),(983,'N3',4,'2013-10-27 04:42:28'),(984,'N1',5,'2013-10-16 05:42:28'),(985,'N2',6,'2013-10-27 06:42:28'),(986,'N3',7,'2013-10-17 07:42:28'),(987,'N1',8,'2013-10-24 08:42:28'),(988,'N2',9,'2013-10-06 09:42:28'),(989,'N3',10,'2013-10-22 10:42:28'),(990,'N1',1,'2013-10-04 13:42:28'),(991,'N2',2,'2013-10-26 14:42:28'),(992,'N3',3,'2013-10-27 15:42:28'),(993,'N1',4,'2013-10-28 16:42:28'),(994,'N2',5,'2013-10-28 17:42:28'),(995,'N3',6,'2013-10-21 18:42:28'),(996,'N1',7,'2013-10-03 19:42:28'),(997,'N2',8,'2013-10-26 20:42:28'),(998,'N3',9,'2013-10-10 21:42:28'),(999,'N1',10,'2013-10-05 22:42:28'),(1000,'N2',1,'2013-10-27 23:42:28'),(1001,'N3',2,'2013-10-07 00:42:28'),(1002,'N1',3,'2013-10-25 01:42:28'),(1003,'N2',4,'2013-10-27 02:42:28'),(1004,'N3',5,'2013-10-13 03:42:28'),(1005,'N1',6,'2013-10-13 04:42:28'),(1006,'N2',7,'2013-10-15 05:42:28'),(1007,'N3',8,'2013-10-23 06:42:28'),(1008,'N1',9,'2013-10-26 07:42:28'),(1009,'N2',10,'2013-10-21 08:42:28'),(1010,'N3',1,'2013-10-05 09:42:28'),(1011,'N1',2,'2013-10-21 10:42:28'),(1012,'N2',3,'2013-10-22 13:42:28'),(1013,'N3',4,'2013-10-21 14:42:28'),(1014,'N1',5,'2013-10-29 15:42:28'),(1015,'N2',6,'2013-10-22 16:42:28'),(1016,'N3',7,'2013-10-08 17:42:28'),(1017,'N1',8,'2013-10-07 18:42:28'),(1018,'N2',9,'2013-10-22 19:42:28'),(1019,'N3',10,'2013-10-07 20:42:28'),(1020,'N1',1,'2013-10-16 21:42:28'),(1021,'N2',2,'2013-10-03 22:42:28'),(1022,'N3',3,'2013-10-06 23:42:28'),(1023,'N1',4,'2013-10-26 00:42:28'),(1024,'N2',5,'2013-10-18 01:42:28'),(1025,'N3',6,'2013-10-21 02:42:28'),(1026,'N1',7,'2013-10-20 03:42:28'),(1027,'N2',8,'2013-10-17 04:42:28'),(1028,'N3',9,'2013-10-16 05:42:28'),(1029,'N1',10,'2013-10-14 06:42:28'),(1030,'N2',1,'2013-10-29 07:42:28'),(1031,'N3',2,'2013-10-27 08:42:28'),(1032,'N1',3,'2013-10-10 09:42:28'),(1033,'N2',4,'2013-10-25 10:42:28'),(1034,'N3',5,'2013-10-17 13:42:28'),(1035,'N1',6,'2013-10-23 14:42:28'),(1036,'N2',7,'2013-10-24 15:42:28'),(1037,'N3',8,'2013-10-27 16:42:28'),(1038,'N1',9,'2013-10-28 17:42:28'),(1039,'N2',10,'2013-10-18 18:42:28'),(1040,'N3',1,'2013-10-01 19:42:28'),(1041,'N1',2,'2013-10-18 20:42:28'),(1042,'N2',3,'2013-10-26 21:42:28'),(1043,'N3',4,'2013-10-03 22:42:28'),(1044,'N1',5,'2013-10-27 23:42:28'),(1045,'N2',6,'2013-10-29 00:42:28'),(1046,'N3',7,'2013-10-06 01:42:28'),(1047,'N1',8,'2013-10-02 02:42:28'),(1048,'N2',9,'2013-10-14 03:42:28'),(1049,'N3',10,'2013-10-02 04:42:28'),(1050,'N1',1,'2013-10-02 05:42:28'),(1051,'N2',2,'2013-10-06 06:42:28'),(1052,'N3',3,'2013-10-22 07:42:28'),(1053,'N1',4,'2013-10-05 08:42:28'),(1054,'N2',5,'2013-10-03 09:42:28'),(1055,'N3',6,'2013-10-10 10:42:28'),(1056,'N1',7,'2013-10-20 13:42:28'),(1057,'N2',8,'2013-10-03 14:42:28'),(1058,'N3',9,'2013-10-29 15:42:28'),(1059,'N1',10,'2013-10-20 16:42:28'),(1060,'N2',1,'2013-10-20 17:42:28'),(1061,'N3',2,'2013-10-23 18:42:28'),(1062,'N1',3,'2013-10-01 19:42:28'),(1063,'N2',4,'2013-10-13 20:42:28'),(1064,'N3',5,'2013-10-07 21:42:28'),(1065,'N1',6,'2013-10-12 22:42:28'),(1066,'N2',7,'2013-10-07 23:42:28'),(1067,'N3',8,'2013-10-10 00:42:28'),(1068,'N1',9,'2013-10-15 01:42:28'),(1069,'N2',10,'2013-10-16 02:42:28'),(1070,'N3',1,'2013-10-05 03:42:28'),(1071,'N1',2,'2013-10-19 04:42:28'),(1072,'N2',3,'2013-10-06 05:42:28'),(1073,'N3',4,'2013-10-16 06:42:28'),(1074,'N1',5,'2013-10-08 07:42:28'),(1075,'N2',6,'2013-10-20 08:42:28'),(1076,'N3',7,'2013-10-29 09:42:28'),(1077,'N1',8,'2013-10-22 10:42:28'),(1078,'N2',9,'2013-10-17 13:42:28'),(1079,'N3',10,'2013-10-24 14:42:28'),(1080,'N1',1,'2013-10-18 15:42:28'),(1081,'N2',2,'2013-10-24 16:42:28'),(1082,'N3',3,'2013-10-04 17:42:28'),(1083,'N1',4,'2013-10-24 18:42:28'),(1084,'N2',5,'2013-10-07 19:42:28'),(1085,'N3',6,'2013-10-18 20:42:28'),(1086,'N1',7,'2013-10-26 21:42:28'),(1087,'N2',8,'2013-10-01 22:42:28'),(1088,'N3',9,'2013-10-04 23:42:28'),(1089,'N1',10,'2013-10-16 00:42:28'),(1090,'N2',1,'2013-10-23 01:42:28'),(1091,'N3',2,'2013-10-25 02:42:28'),(1092,'N1',3,'2013-10-27 03:42:28'),(1093,'N2',4,'2013-10-06 04:42:28'),(1094,'N3',5,'2013-10-02 05:42:28'),(1095,'N1',6,'2013-10-16 06:42:28'),(1096,'N2',7,'2013-10-08 07:42:28'),(1097,'N3',8,'2013-10-12 08:42:28'),(1098,'N1',9,'2013-10-13 09:42:28'),(1099,'N2',10,'2013-10-18 10:42:28'),(1100,'N3',1,'2013-10-29 13:42:28'),(1101,'N1',2,'2013-10-05 14:42:28'),(1102,'N2',3,'2013-10-06 15:42:28'),(1103,'N3',4,'2013-10-13 16:42:28'),(1104,'N1',5,'2013-10-16 17:42:28'),(1105,'N2',6,'2013-10-28 18:42:28'),(1106,'N3',7,'2013-10-23 19:42:28'),(1107,'N1',8,'2013-10-07 20:42:28'),(1108,'N2',9,'2013-10-16 21:42:28'),(1109,'N3',10,'2013-10-13 22:42:28'),(1110,'N1',1,'2013-10-18 23:42:28'),(1111,'N2',2,'2013-10-28 00:42:28'),(1112,'N3',3,'2013-10-24 01:42:28'),(1113,'N1',4,'2013-10-03 02:42:28'),(1114,'N2',5,'2013-10-06 03:42:28'),(1115,'N3',6,'2013-10-06 04:42:28'),(1116,'N1',7,'2013-10-02 05:42:28'),(1117,'N2',8,'2013-10-11 06:42:28'),(1118,'N3',9,'2013-10-13 07:42:28'),(1119,'N1',10,'2013-10-26 08:42:28'),(1120,'N2',1,'2013-10-27 09:42:28'),(1121,'N3',2,'2013-10-02 10:42:28'),(1122,'N1',3,'2013-10-26 13:42:28'),(1123,'N2',4,'2013-10-05 14:42:28'),(1124,'N3',5,'2013-10-12 15:42:28'),(1125,'N1',6,'2013-10-17 16:42:28'),(1126,'N2',7,'2013-10-29 17:42:28'),(1127,'N3',8,'2013-10-12 18:42:28'),(1128,'N1',9,'2013-10-13 19:42:28'),(1129,'N2',10,'2013-10-19 20:42:28'),(1130,'N3',1,'2013-10-29 21:42:28'),(1131,'N1',2,'2013-10-04 22:42:28'),(1132,'N2',3,'2013-10-01 23:42:28'),(1133,'N3',4,'2013-10-11 00:42:28'),(1134,'N1',5,'2013-10-15 01:42:28'),(1135,'N2',6,'2013-10-09 02:42:28'),(1136,'N3',7,'2013-10-30 03:42:28'),(1137,'N1',8,'2013-10-08 04:42:28'),(1138,'N2',9,'2013-10-25 05:42:28'),(1139,'N3',10,'2013-10-15 06:42:28'),(1140,'N1',1,'2013-10-02 07:42:28'),(1141,'N2',2,'2013-10-05 08:42:28'),(1142,'N3',3,'2013-10-05 09:42:28'),(1143,'N1',4,'2013-10-08 10:42:28'),(1144,'N2',5,'2013-10-01 13:42:28'),(1145,'N3',6,'2013-10-26 14:42:28'),(1146,'N1',7,'2013-10-21 15:42:28'),(1147,'N2',8,'2013-10-16 16:42:28'),(1148,'N3',9,'2013-10-26 17:42:28'),(1149,'N1',10,'2013-10-18 18:42:28'),(1150,'N2',1,'2013-10-27 19:42:28'),(1151,'N3',2,'2013-10-13 20:42:28'),(1152,'N1',3,'2013-10-22 21:42:28'),(1153,'N2',4,'2013-10-29 22:42:28'),(1154,'N3',5,'2013-10-16 23:42:28'),(1155,'N1',6,'2013-10-23 00:42:28'),(1156,'N2',7,'2013-10-02 01:42:28'),(1157,'N3',8,'2013-10-23 02:42:28'),(1158,'N1',9,'2013-10-14 03:42:28'),(1159,'N2',10,'2013-10-14 04:42:28'),(1160,'N3',1,'2013-10-19 05:42:28'),(1161,'N1',2,'2013-10-16 06:42:28'),(1162,'N2',3,'2013-10-14 07:42:28'),(1163,'N3',4,'2013-10-11 08:42:28'),(1164,'N1',5,'2013-10-27 09:42:28'),(1165,'N2',6,'2013-10-20 10:42:28'),(1166,'N3',7,'2013-10-10 13:42:28'),(1167,'N1',8,'2013-10-01 14:42:28'),(1168,'N2',9,'2013-10-18 15:42:28'),(1169,'N3',10,'2013-10-26 16:42:28'),(1170,'N1',1,'2013-10-16 17:42:28'),(1171,'N2',2,'2013-10-08 18:42:28'),(1172,'N3',3,'2013-10-27 19:42:28'),(1173,'N1',4,'2013-10-12 20:42:28'),(1174,'N2',5,'2013-10-17 21:42:28'),(1175,'N3',6,'2013-10-08 22:42:28'),(1176,'N1',7,'2013-10-19 23:42:28'),(1177,'N2',8,'2013-10-05 00:42:28'),(1178,'N3',9,'2013-10-22 01:42:28'),(1179,'N1',10,'2013-10-15 02:42:28'),(1180,'N2',1,'2013-10-29 03:42:28'),(1181,'N3',2,'2013-10-05 04:42:28'),(1182,'N1',3,'2013-10-17 05:42:28'),(1183,'N2',4,'2013-10-15 06:42:28'),(1184,'N3',5,'2013-10-17 07:42:28'),(1185,'N1',6,'2013-10-18 08:42:28'),(1186,'N2',7,'2013-10-15 09:42:28'),(1187,'N3',8,'2013-10-19 10:42:28'),(1188,'N1',9,'2013-10-16 13:42:28'),(1189,'N2',10,'2013-10-17 14:42:28'),(1190,'N3',1,'2013-10-16 15:42:28'),(1191,'N1',2,'2013-10-21 16:42:28'),(1192,'N2',3,'2013-10-11 17:42:28'),(1193,'N3',4,'2013-10-27 18:42:28'),(1194,'N1',5,'2013-10-21 19:42:28'),(1195,'N2',6,'2013-10-17 20:42:28'),(1196,'N3',7,'2013-10-01 21:42:28'),(1197,'N1',8,'2013-10-26 22:42:28'),(1198,'N2',9,'2013-10-20 23:42:28'),(1199,'N3',10,'2013-10-13 00:42:28'),(1200,'N1',1,'2013-10-06 01:42:28'),(1201,'N2',2,'2013-10-14 02:42:28'),(1202,'N3',3,'2013-10-08 03:42:28'),(1203,'N1',4,'2013-10-03 04:42:28'),(1204,'N2',5,'2013-10-23 05:42:28'),(1205,'N3',6,'2013-10-05 06:42:28'),(1206,'N1',7,'2013-10-09 07:42:28'),(1207,'N2',8,'2013-10-26 08:42:28'),(1208,'N3',9,'2013-10-09 09:42:28'),(1209,'N1',10,'2013-10-17 10:42:28'),(1210,'N2',1,'2013-10-06 13:42:28'),(1211,'N3',2,'2013-10-22 14:42:28'),(1212,'N1',3,'2013-10-17 15:42:28'),(1213,'N2',4,'2013-10-03 16:42:28'),(1214,'N3',5,'2013-10-16 17:42:28'),(1215,'N1',6,'2013-10-25 18:42:28'),(1216,'N2',7,'2013-10-08 19:42:28'),(1217,'N3',8,'2013-10-20 20:42:28'),(1218,'N1',9,'2013-10-06 21:42:28'),(1219,'N2',10,'2013-10-17 22:42:28'),(1220,'N3',1,'2013-10-19 23:42:28'),(1221,'N1',2,'2013-10-27 00:42:28'),(1222,'N2',3,'2013-10-22 01:42:28'),(1223,'N3',4,'2013-10-29 02:42:28'),(1224,'N1',5,'2013-10-13 03:42:28'),(1225,'N2',6,'2013-10-22 04:42:28'),(1226,'N3',7,'2013-10-03 05:42:28'),(1227,'N1',8,'2013-10-11 06:42:28'),(1228,'N2',9,'2013-10-06 07:42:28'),(1229,'N3',10,'2013-10-07 08:42:28'),(1230,'N1',1,'2013-10-11 09:42:28'),(1231,'N2',2,'2013-10-17 10:42:28'),(1232,'N3',3,'2013-10-12 13:42:28'),(1233,'N1',4,'2013-10-11 14:42:28'),(1234,'N2',5,'2013-10-28 15:42:28'),(1235,'N3',6,'2013-10-22 16:42:28'),(1236,'N1',7,'2013-10-28 17:42:28'),(1237,'N2',8,'2013-10-07 18:42:28'),(1238,'N3',9,'2013-10-06 19:42:28'),(1239,'N1',10,'2013-10-11 20:42:28'),(1240,'N2',1,'2013-10-18 21:42:28'),(1241,'N3',2,'2013-10-19 22:42:28'),(1242,'N1',3,'2013-10-17 23:42:28'),(1243,'N2',4,'2013-10-22 00:42:28'),(1244,'N3',5,'2013-10-09 01:42:28'),(1245,'N1',6,'2013-10-13 02:42:28'),(1246,'N2',7,'2013-10-05 03:42:28'),(1247,'N3',8,'2013-10-16 04:42:28'),(1248,'N1',9,'2013-10-28 05:42:28'),(1249,'N2',10,'2013-10-30 06:42:28'),(1250,'N3',1,'2013-10-02 07:42:28'),(1251,'N1',2,'2013-10-06 08:42:28'),(1252,'N2',3,'2013-10-05 09:42:28'),(1253,'N3',4,'2013-10-07 10:42:28'),(1254,'N1',5,'2013-10-13 13:42:28'),(1255,'N2',6,'2013-10-18 14:42:28'),(1256,'N3',7,'2013-10-07 15:42:28'),(1257,'N1',8,'2013-10-17 16:42:28'),(1258,'N2',9,'2013-10-24 17:42:28'),(1259,'N3',10,'2013-10-22 18:42:28'),(1260,'N1',1,'2013-10-08 19:42:28'),(1261,'N2',2,'2013-10-15 20:42:28'),(1262,'N3',3,'2013-10-02 21:42:28'),(1263,'N1',4,'2013-10-18 22:42:28'),(1264,'N2',5,'2013-10-27 23:42:28'),(1265,'N3',6,'2013-10-16 00:42:28'),(1266,'N1',7,'2013-10-13 01:42:28'),(1267,'N2',8,'2013-10-21 02:42:28'),(1268,'N3',9,'2013-10-08 03:42:28'),(1269,'N1',10,'2013-10-20 04:42:28'),(1270,'N2',1,'2013-10-24 05:42:28'),(1271,'N3',2,'2013-10-26 06:42:28'),(1272,'N1',3,'2013-10-02 07:42:28'),(1273,'N2',4,'2013-10-07 08:42:28'),(1274,'N3',5,'2013-10-29 09:42:28'),(1275,'N1',6,'2013-10-26 10:42:28'),(1276,'N2',7,'2013-10-05 13:42:28'),(1277,'N3',8,'2013-10-14 14:42:28'),(1278,'N1',9,'2013-10-10 15:42:28'),(1279,'N2',10,'2013-10-05 16:42:28'),(1280,'N3',1,'2013-10-03 17:42:28'),(1281,'N1',2,'2013-10-12 18:42:28'),(1282,'N2',3,'2013-10-26 19:42:28'),(1283,'N3',4,'2013-10-16 20:42:28'),(1284,'N1',5,'2013-10-02 21:42:28'),(1285,'N2',6,'2013-10-28 22:42:28'),(1286,'N3',7,'2013-10-03 23:42:28'),(1287,'N1',8,'2013-10-17 00:42:28'),(1288,'N2',9,'2013-10-04 01:42:28'),(1289,'N3',10,'2013-10-25 02:42:28'),(1290,'N1',1,'2013-10-07 03:42:28'),(1291,'N2',2,'2013-10-07 04:42:28'),(1292,'N3',3,'2013-10-05 05:42:28'),(1293,'N1',4,'2013-10-12 06:42:28'),(1294,'N2',5,'2013-10-17 07:42:28'),(1295,'N3',6,'2013-10-26 08:42:28'),(1296,'N1',7,'2013-10-29 09:42:28'),(1297,'N2',8,'2013-10-08 10:42:28'),(1298,'N3',9,'2013-10-02 13:42:28'),(1299,'N1',10,'2013-10-25 14:42:28'),(1300,'N2',1,'2013-10-23 15:42:28'),(1301,'N3',2,'2013-10-20 16:42:28'),(1302,'N1',3,'2013-10-01 17:42:28'),(1303,'N2',4,'2013-10-21 18:42:28'),(1304,'N3',5,'2013-10-20 19:42:28'),(1305,'N1',6,'2013-10-16 20:42:28'),(1306,'N2',7,'2013-10-29 21:42:28'),(1307,'N3',8,'2013-10-21 22:42:28'),(1308,'N1',9,'2013-10-13 23:42:28'),(1309,'N2',10,'2013-10-30 00:42:28'),(1310,'N3',1,'2013-10-08 01:42:28'),(1311,'N1',2,'2013-10-13 02:42:28'),(1312,'N2',3,'2013-10-10 03:42:28'),(1313,'N3',4,'2013-10-22 04:42:28'),(1314,'N1',5,'2013-10-16 05:42:28'),(1315,'N2',6,'2013-10-07 06:42:28'),(1316,'N3',7,'2013-10-20 07:42:28'),(1317,'N1',8,'2013-10-27 08:42:28'),(1318,'N2',9,'2013-10-14 09:42:28'),(1319,'N3',10,'2013-10-12 10:42:28'),(1320,'N1',1,'2013-10-03 13:42:28'),(1321,'N2',2,'2013-10-15 14:42:28'),(1322,'N3',3,'2013-10-27 15:42:28'),(1323,'N1',4,'2013-10-09 16:42:28'),(1324,'N2',5,'2013-10-02 17:42:28'),(1325,'N3',6,'2013-10-25 18:42:28'),(1326,'N1',7,'2013-10-19 19:42:28'),(1327,'N2',8,'2013-10-24 20:42:28'),(1328,'N3',9,'2013-10-23 21:42:28'),(1329,'N1',10,'2013-10-18 22:42:28'),(1330,'N2',1,'2013-10-11 23:42:28'),(1331,'N3',2,'2013-10-17 00:42:28'),(1332,'N1',3,'2013-10-14 01:42:28'),(1333,'N2',4,'2013-10-14 02:42:28'),(1334,'N3',5,'2013-10-27 03:42:28'),(1335,'N1',6,'2013-10-13 04:42:28'),(1336,'N2',7,'2013-10-22 05:42:28'),(1337,'N3',8,'2013-10-23 06:42:28'),(1338,'N1',9,'2013-10-14 07:42:28'),(1339,'N2',10,'2013-10-25 08:42:28'),(1340,'N3',1,'2013-10-05 09:42:28'),(1341,'N1',2,'2013-10-28 10:42:28'),(1342,'N2',3,'2013-10-13 13:42:28'),(1343,'N3',4,'2013-10-04 14:42:28'),(1344,'N1',5,'2013-10-03 15:42:28'),(1345,'N2',6,'2013-10-10 16:42:28'),(1346,'N3',7,'2013-10-02 17:42:28'),(1347,'N1',8,'2013-10-10 18:42:28'),(1348,'N2',9,'2013-10-28 19:42:28'),(1349,'N3',10,'2013-10-22 20:42:28'),(1350,'N1',1,'2013-10-26 21:42:28'),(1351,'N2',2,'2013-10-19 22:42:28'),(1352,'N3',3,'2013-10-19 23:42:28'),(1353,'N1',4,'2013-10-04 00:42:28'),(1354,'N2',5,'2013-10-17 01:42:28'),(1355,'N3',6,'2013-10-05 02:42:28'),(1356,'N1',7,'2013-10-21 03:42:28'),(1357,'N2',8,'2013-10-21 04:42:28'),(1358,'N3',9,'2013-10-03 05:42:28'),(1359,'N1',10,'2013-10-26 06:42:28'),(1360,'N2',1,'2013-10-30 07:42:28'),(1361,'N3',2,'2013-10-03 08:42:28'),(1362,'N1',3,'2013-10-17 09:42:28'),(1363,'N2',4,'2013-10-28 10:42:28'),(1364,'N3',5,'2013-10-08 13:42:28'),(1365,'N1',6,'2013-10-23 14:42:28'),(1366,'N2',7,'2013-10-01 15:42:28'),(1367,'N3',8,'2013-10-16 16:42:28'),(1368,'N1',9,'2013-10-26 17:42:28'),(1369,'N2',10,'2013-10-06 18:42:28'),(1370,'N3',1,'2013-10-23 19:42:28'),(1371,'N1',2,'2013-10-04 20:42:28'),(1372,'N2',3,'2013-10-09 21:42:28'),(1373,'N3',4,'2013-10-28 22:42:28'),(1374,'N1',5,'2013-10-21 23:42:28'),(1375,'N2',6,'2013-10-03 00:42:28'),(1376,'N3',7,'2013-10-25 01:42:28'),(1377,'N1',8,'2013-10-18 02:42:28'),(1378,'N2',9,'2013-10-22 03:42:28'),(1379,'N3',10,'2013-10-28 04:42:28'),(1380,'N1',1,'2013-10-30 05:42:28'),(1381,'N2',2,'2013-10-07 06:42:28'),(1382,'N3',3,'2013-10-14 07:42:28'),(1383,'N1',4,'2013-10-27 08:42:28'),(1384,'N2',5,'2013-10-30 09:42:28'),(1385,'N3',6,'2013-10-29 10:42:28'),(1386,'N1',7,'2013-10-07 13:42:28'),(1387,'N2',8,'2013-10-21 14:42:28'),(1388,'N3',9,'2013-10-06 15:42:28'),(1389,'N1',10,'2013-10-27 16:42:28'),(1390,'N2',1,'2013-10-03 17:42:28'),(1391,'N3',2,'2013-10-25 18:42:28'),(1392,'N1',3,'2013-10-09 19:42:28'),(1393,'N2',4,'2013-10-24 20:42:28'),(1394,'N3',5,'2013-10-09 21:42:28'),(1395,'N1',6,'2013-10-06 22:42:28'),(1396,'N2',7,'2013-10-04 23:42:28'),(1397,'N3',8,'2013-10-06 00:42:28'),(1398,'N1',9,'2013-10-18 01:42:28'),(1399,'N2',10,'2013-10-06 02:42:28'),(1400,'N3',1,'2013-10-08 03:42:28'),(1401,'N1',2,'2013-10-15 04:42:28'),(1402,'N2',3,'2013-10-09 05:42:28'),(1403,'N3',4,'2013-10-20 06:42:28'),(1404,'N1',5,'2013-10-07 07:42:28'),(1405,'N2',6,'2013-10-04 08:42:28'),(1406,'N3',7,'2013-10-29 09:42:28'),(1407,'N1',8,'2013-10-22 10:42:28'),(1408,'N2',9,'2013-10-11 13:42:28'),(1409,'N3',10,'2013-10-28 14:42:28'),(1410,'N1',1,'2013-10-22 15:42:28'),(1411,'N2',2,'2013-10-21 16:42:28'),(1412,'N3',3,'2013-10-06 17:42:28'),(1413,'N1',4,'2013-10-26 18:42:28'),(1414,'N2',5,'2013-10-18 19:42:28'),(1415,'N3',6,'2013-10-01 20:42:28'),(1416,'N1',7,'2013-10-07 21:42:28'),(1417,'N2',8,'2013-10-11 22:42:28'),(1418,'N3',9,'2013-10-27 23:42:28'),(1419,'N1',10,'2013-10-20 00:42:28'),(1420,'N2',1,'2013-10-27 01:42:28'),(1421,'N3',2,'2013-10-15 02:42:28'),(1422,'N1',3,'2013-10-26 03:42:28'),(1423,'N2',4,'2013-10-08 04:42:28'),(1424,'N3',5,'2013-10-17 05:42:28'),(1425,'N1',6,'2013-10-10 06:42:28'),(1426,'N2',7,'2013-10-30 07:42:28'),(1427,'N3',8,'2013-10-26 08:42:28'),(1428,'N1',9,'2013-10-28 09:42:28'),(1429,'N2',10,'2013-10-02 10:42:28'),(1430,'N3',1,'2013-10-08 13:42:28'),(1431,'N1',2,'2013-10-08 14:42:28'),(1432,'N2',3,'2013-10-05 15:42:28'),(1433,'N3',4,'2013-10-11 16:42:28'),(1434,'N1',5,'2013-10-24 17:42:28'),(1435,'N2',6,'2013-10-03 18:42:28'),(1436,'N3',7,'2013-10-28 19:42:28'),(1437,'N1',8,'2013-10-13 20:42:28'),(1438,'N2',9,'2013-10-12 21:42:28'),(1439,'N3',10,'2013-10-14 22:42:28'),(1440,'N1',1,'2013-10-09 23:42:28'),(1441,'N2',2,'2013-10-24 00:42:28'),(1442,'N3',3,'2013-10-22 01:42:28'),(1443,'N1',4,'2013-10-24 02:42:28'),(1444,'N2',5,'2013-10-30 03:42:28'),(1445,'N3',6,'2013-10-28 04:42:28'),(1446,'N1',7,'2013-10-24 05:42:28'),(1447,'N2',8,'2013-10-04 06:42:28'),(1448,'N3',9,'2013-10-13 07:42:28'),(1449,'N1',10,'2013-10-21 08:42:28'),(1450,'N2',1,'2013-10-16 09:42:28'),(1451,'N3',2,'2013-10-03 10:42:28'),(1452,'N1',3,'2013-10-19 13:42:28'),(1453,'N2',4,'2013-10-05 14:42:28'),(1454,'N3',5,'2013-10-11 15:42:28'),(1455,'N1',6,'2013-10-02 16:42:28'),(1456,'N2',7,'2013-10-24 17:42:28'),(1457,'N3',8,'2013-10-01 18:42:28'),(1458,'N1',9,'2013-10-27 19:42:28'),(1459,'N2',10,'2013-10-06 20:42:28'),(1460,'N3',1,'2013-10-15 21:42:28'),(1461,'N1',2,'2013-10-10 22:42:28'),(1462,'N2',3,'2013-10-25 23:42:28'),(1463,'N3',4,'2013-10-22 00:42:28'),(1464,'N1',5,'2013-10-23 01:42:28'),(1465,'N2',6,'2013-10-09 02:42:28'),(1466,'N3',7,'2013-10-21 03:42:28'),(1467,'N1',8,'2013-10-04 04:42:28'),(1468,'N2',9,'2013-10-08 05:42:28'),(1469,'N3',10,'2013-10-13 06:42:28'),(1470,'N1',1,'2013-10-04 07:42:28'),(1471,'N2',2,'2013-10-25 08:42:28'),(1472,'N3',3,'2013-10-03 09:42:28'),(1473,'N1',4,'2013-10-05 10:42:28'),(1474,'N2',5,'2013-10-27 13:42:28'),(1475,'N3',6,'2013-10-02 14:42:28'),(1476,'N1',7,'2013-10-19 15:42:28'),(1477,'N2',8,'2013-10-15 16:42:28'),(1478,'N3',9,'2013-10-15 17:42:28'),(1479,'N1',10,'2013-10-08 18:42:28'),(1480,'N2',1,'2013-10-29 19:42:28'),(1481,'N3',2,'2013-10-26 20:42:28'),(1482,'N1',3,'2013-10-15 21:42:28'),(1483,'N2',4,'2013-10-28 22:42:28'),(1484,'N3',5,'2013-10-28 23:42:28'),(1485,'N1',6,'2013-10-30 00:42:28'),(1486,'N2',7,'2013-10-09 01:42:28'),(1487,'N3',8,'2013-10-24 02:42:28'),(1488,'N1',9,'2013-10-29 03:42:28'),(1489,'N2',10,'2013-10-11 04:42:28'),(1490,'N3',1,'2013-10-04 05:42:28'),(1491,'N1',2,'2013-10-29 06:42:28'),(1492,'N2',3,'2013-10-20 07:42:28'),(1493,'N3',4,'2013-10-11 08:42:28'),(1494,'N1',5,'2013-10-24 09:42:28'),(1495,'N2',6,'2013-10-06 10:42:28'),(1496,'N3',7,'2013-10-05 13:42:28'),(1497,'N1',8,'2013-10-15 14:42:28'),(1498,'N2',9,'2013-10-02 15:42:28'),(1499,'N3',10,'2013-10-15 16:42:28'),(1500,'N1',1,'2013-10-15 17:42:28'),(1501,'N2',2,'2013-10-25 18:42:28'),(1502,'N3',3,'2013-10-16 19:42:28'),(1503,'N1',4,'2013-10-05 20:42:28'),(1504,'N2',5,'2013-10-24 21:42:28'),(1505,'N3',6,'2013-10-26 22:42:28'),(1506,'N1',7,'2013-10-03 23:42:28'),(1507,'N2',8,'2013-10-06 00:42:28'),(1508,'N3',9,'2013-10-13 01:42:28'),(1509,'N1',10,'2013-10-28 02:42:28'),(1510,'N2',1,'2013-10-16 03:42:28'),(1511,'N3',2,'2013-10-22 04:42:28'),(1512,'N1',3,'2013-10-11 05:42:28'),(1513,'N2',4,'2013-10-03 06:42:28'),(1514,'N3',5,'2013-10-22 07:42:28'),(1515,'N1',6,'2013-10-14 08:42:28'),(1516,'N2',7,'2013-10-07 09:42:28'),(1517,'N3',8,'2013-10-15 10:42:28'),(1518,'N1',9,'2013-10-20 13:42:28'),(1519,'N2',10,'2013-10-20 14:42:28'),(1520,'N3',1,'2013-10-08 15:42:28'),(1521,'N1',2,'2013-10-29 16:42:28'),(1522,'N2',3,'2013-10-25 17:42:28'),(1523,'N3',4,'2013-10-15 18:42:28'),(1524,'N1',5,'2013-10-06 19:42:28'),(1525,'N2',6,'2013-10-15 20:42:28'),(1526,'N3',7,'2013-10-29 21:42:28'),(1527,'N1',8,'2013-10-09 22:42:28'),(1528,'N2',9,'2013-10-17 23:42:28'),(1529,'N3',10,'2013-10-15 00:42:28'),(1530,'N1',1,'2013-10-25 01:42:28'),(1531,'N2',2,'2013-10-30 02:42:28'),(1532,'N3',3,'2013-10-03 03:42:28'),(1533,'N1',4,'2013-10-27 04:42:28'),(1534,'N2',5,'2013-10-20 05:42:28'),(1535,'N3',6,'2013-10-18 06:42:28'),(1536,'N1',7,'2013-10-03 07:42:28'),(1537,'N2',8,'2013-10-06 08:42:28'),(1538,'N3',9,'2013-10-16 09:42:28'),(1539,'N1',10,'2013-10-24 10:42:28'),(1540,'N2',1,'2013-10-25 13:42:28'),(1541,'N3',2,'2013-10-15 14:42:28'),(1542,'N1',3,'2013-10-17 15:42:28'),(1543,'N2',4,'2013-10-13 16:42:28'),(1544,'N3',5,'2013-10-23 17:42:28'),(1545,'N1',6,'2013-10-15 18:42:28'),(1546,'N2',7,'2013-10-08 19:42:28'),(1547,'N3',8,'2013-10-18 20:42:28'),(1548,'N1',9,'2013-10-27 21:42:28'),(1549,'N2',10,'2013-10-13 22:42:28'),(1550,'N3',1,'2013-10-03 23:42:28'),(1551,'N1',2,'2013-10-04 00:42:28'),(1552,'N2',3,'2013-10-16 01:42:28'),(1553,'N3',4,'2013-10-14 02:42:28'),(1554,'N1',5,'2013-10-05 03:42:28'),(1555,'N2',6,'2013-10-03 04:42:28'),(1556,'N3',7,'2013-10-18 05:42:28'),(1557,'N1',8,'2013-10-30 06:42:28'),(1558,'N2',9,'2013-10-05 07:42:28'),(1559,'N3',10,'2013-10-21 08:42:28'),(1560,'N1',1,'2013-10-30 09:42:28'),(1561,'N2',2,'2013-10-15 10:42:28'),(1562,'N3',3,'2013-10-29 13:42:28'),(1563,'N1',4,'2013-10-23 14:42:28'),(1564,'N2',5,'2013-10-25 15:42:28'),(1565,'N3',6,'2013-10-20 16:42:28'),(1566,'N1',7,'2013-10-27 17:42:28'),(1567,'N2',8,'2013-10-26 18:42:28'),(1568,'N3',9,'2013-10-23 19:42:28'),(1569,'N1',10,'2013-10-27 20:42:28'),(1570,'N2',1,'2013-10-03 21:42:28'),(1571,'N3',2,'2013-10-22 22:42:28'),(1572,'N1',3,'2013-10-18 23:42:28'),(1573,'N2',4,'2013-10-10 00:42:28'),(1574,'N3',5,'2013-10-23 01:42:28'),(1575,'N1',6,'2013-10-18 02:42:28'),(1576,'N2',7,'2013-10-24 03:42:28'),(1577,'N3',8,'2013-10-27 04:42:28'),(1578,'N1',9,'2013-10-02 05:42:28'),(1579,'N2',10,'2013-10-15 06:42:28'),(1580,'N3',1,'2013-10-18 07:42:28'),(1581,'N1',2,'2013-10-04 08:42:28'),(1582,'N2',3,'2013-10-29 09:42:28'),(1583,'N3',4,'2013-10-16 10:42:28'),(1584,'N1',5,'2013-10-29 13:42:28'),(1585,'N2',6,'2013-10-16 14:42:28'),(1586,'N3',7,'2013-10-21 15:42:28'),(1587,'N1',8,'2013-10-05 16:42:28'),(1588,'N2',9,'2013-10-22 17:42:28'),(1589,'N3',10,'2013-10-03 18:42:28'),(1590,'N1',1,'2013-10-26 19:42:28'),(1591,'N2',2,'2013-10-06 20:42:28'),(1592,'N3',3,'2013-10-22 21:42:28'),(1593,'N1',4,'2013-10-03 22:42:28'),(1594,'N2',5,'2013-10-27 23:42:28'),(1595,'N3',6,'2013-10-27 00:42:28'),(1596,'N1',7,'2013-10-04 01:42:28'),(1597,'N2',8,'2013-10-13 02:42:28'),(1598,'N3',9,'2013-10-30 03:42:28'),(1599,'N1',10,'2013-10-04 04:42:28'),(1600,'N2',1,'2013-10-25 05:42:28'),(1601,'N3',2,'2013-10-25 06:42:28'),(1602,'N1',3,'2013-10-30 07:42:28'),(1603,'N2',4,'2013-10-30 08:42:28'),(1604,'N3',5,'2013-10-30 09:42:28'),(1605,'N1',6,'2013-10-16 10:42:28'),(1606,'N2',7,'2013-10-29 13:42:28'),(1607,'N3',8,'2013-10-18 14:42:28'),(1608,'N1',9,'2013-10-04 15:42:28'),(1609,'N2',10,'2013-10-18 16:42:28'),(1610,'N3',1,'2013-10-28 17:42:28'),(1611,'N1',2,'2013-10-01 18:42:28'),(1612,'N2',3,'2013-10-13 19:42:28'),(1613,'N3',4,'2013-10-02 20:42:28'),(1614,'N1',5,'2013-10-28 21:42:28'),(1615,'N2',6,'2013-10-02 22:42:28'),(1616,'N3',7,'2013-10-06 23:42:28'),(1617,'N1',8,'2013-10-24 00:42:28'),(1618,'N2',9,'2013-10-30 01:42:28'),(1619,'N3',10,'2013-10-14 02:42:28'),(1620,'N1',1,'2013-10-25 03:42:28'),(1621,'N2',2,'2013-10-27 04:42:28'),(1622,'N3',3,'2013-10-19 05:42:28'),(1623,'N1',4,'2013-10-09 06:42:28'),(1624,'N2',5,'2013-10-28 07:42:28'),(1625,'N3',6,'2013-10-23 08:42:28'),(1626,'N1',7,'2013-10-29 09:42:28'),(1627,'N2',8,'2013-10-10 10:42:28'),(1628,'N3',9,'2013-10-13 13:42:28'),(1629,'N1',10,'2013-10-21 14:42:28'),(1630,'N2',1,'2013-10-06 15:42:28'),(1631,'N3',2,'2013-10-27 16:42:28'),(1632,'N1',3,'2013-10-14 17:42:28'),(1633,'N2',4,'2013-10-10 18:42:28'),(1634,'N3',5,'2013-10-05 19:42:28'),(1635,'N1',6,'2013-10-05 20:42:28'),(1636,'N2',7,'2013-10-12 21:42:28'),(1637,'N3',8,'2013-10-14 22:42:28'),(1638,'N1',9,'2013-10-24 23:42:28'),(1639,'N2',10,'2013-10-09 00:42:28'),(1640,'N3',1,'2013-10-24 01:42:28'),(1641,'N1',2,'2013-10-19 02:42:28'),(1642,'N2',3,'2013-10-07 03:42:28'),(1643,'N3',4,'2013-10-28 04:42:28'),(1644,'N1',5,'2013-10-25 05:42:28'),(1645,'N2',6,'2013-10-28 06:42:28'),(1646,'N3',7,'2013-10-03 07:42:28'),(1647,'N1',8,'2013-10-11 08:42:28'),(1648,'N2',9,'2013-10-13 09:42:28'),(1649,'N3',10,'2013-10-11 10:42:28'),(1650,'N1',1,'2013-10-16 13:42:28'),(1651,'N2',2,'2013-10-20 14:42:28'),(1652,'N3',3,'2013-10-20 15:42:28'),(1653,'N1',4,'2013-10-16 16:42:28'),(1654,'N2',5,'2013-10-08 17:42:28'),(1655,'N3',6,'2013-10-06 18:42:28'),(1656,'N1',7,'2013-10-03 19:42:28'),(1657,'N2',8,'2013-10-17 20:42:28'),(1658,'N3',9,'2013-10-16 21:42:28'),(1659,'N1',10,'2013-10-18 22:42:28'),(1660,'N2',1,'2013-10-07 23:42:28'),(1661,'N3',2,'2013-10-13 00:42:28'),(1662,'N1',3,'2013-10-19 01:42:28'),(1663,'N2',4,'2013-10-25 02:42:28'),(1664,'N3',5,'2013-10-07 03:42:28'),(1665,'N1',6,'2013-10-09 04:42:28'),(1666,'N2',7,'2013-10-16 05:42:28'),(1667,'N3',8,'2013-10-29 06:42:28'),(1668,'N1',9,'2013-10-15 07:42:28'),(1669,'N2',10,'2013-10-27 08:42:28'),(1670,'N3',1,'2013-10-13 09:42:28'),(1671,'N1',2,'2013-10-07 10:42:28'),(1672,'N2',3,'2013-10-21 13:42:28'),(1673,'N3',4,'2013-10-07 14:42:28'),(1674,'N1',5,'2013-10-21 15:42:28'),(1675,'N2',6,'2013-10-28 16:42:28'),(1676,'N3',7,'2013-10-15 17:42:28'),(1677,'N1',8,'2013-10-01 18:42:28'),(1678,'N2',9,'2013-10-16 19:42:28'),(1679,'N3',10,'2013-10-08 20:42:28'),(1680,'N1',1,'2013-10-20 21:42:28'),(1681,'N2',2,'2013-10-19 22:42:28'),(1682,'N3',3,'2013-10-17 23:42:28'),(1683,'N1',4,'2013-10-04 00:42:28'),(1684,'N2',5,'2013-10-07 01:42:28'),(1685,'N3',6,'2013-10-11 02:42:28'),(1686,'N1',7,'2013-10-15 03:42:28'),(1687,'N2',8,'2013-10-19 04:42:28'),(1688,'N3',9,'2013-10-11 05:42:28'),(1689,'N1',10,'2013-10-14 06:42:28'),(1690,'N2',1,'2013-10-07 07:42:28'),(1691,'N3',2,'2013-10-26 08:42:28'),(1692,'N1',3,'2013-10-03 09:42:28'),(1693,'N2',4,'2013-10-03 10:42:28'),(1694,'N3',5,'2013-10-06 13:42:28'),(1695,'N1',6,'2013-10-25 14:42:28'),(1696,'N2',7,'2013-10-26 15:42:28'),(1697,'N3',8,'2013-10-03 16:42:28'),(1698,'N1',9,'2013-10-29 17:42:28'),(1699,'N2',10,'2013-10-12 18:42:28'),(1700,'N3',1,'2013-10-23 19:42:28'),(1701,'N1',2,'2013-10-02 20:42:28'),(1702,'N2',3,'2013-10-20 21:42:28'),(1703,'N3',4,'2013-10-16 22:42:28'),(1704,'N1',5,'2013-10-01 23:42:28'),(1705,'N2',6,'2013-10-11 00:42:28'),(1706,'N3',7,'2013-10-27 01:42:28'),(1707,'N1',8,'2013-10-05 02:42:28'),(1708,'N2',9,'2013-10-13 03:42:28'),(1709,'N3',10,'2013-10-10 04:42:28'),(1710,'N1',1,'2013-10-06 05:42:28'),(1711,'N2',2,'2013-10-16 06:42:28'),(1712,'N3',3,'2013-10-30 07:42:28'),(1713,'N1',4,'2013-10-05 08:42:28'),(1714,'N2',5,'2013-10-02 09:42:28'),(1715,'N3',6,'2013-10-22 10:42:28'),(1716,'N1',7,'2013-10-04 13:42:28'),(1717,'N2',8,'2013-10-11 14:42:28'),(1718,'N3',9,'2013-10-29 15:42:28'),(1719,'N1',10,'2013-10-10 16:42:28'),(1720,'N2',1,'2013-10-19 17:42:28'),(1721,'N3',2,'2013-10-05 18:42:28'),(1722,'N1',3,'2013-10-02 19:42:28'),(1723,'N2',4,'2013-10-20 20:42:28'),(1724,'N3',5,'2013-10-18 21:42:28'),(1725,'N1',6,'2013-10-09 22:42:28'),(1726,'N2',7,'2013-10-09 23:42:28'),(1727,'N3',8,'2013-10-09 00:42:28'),(1728,'N1',9,'2013-10-05 01:42:28'),(1729,'N2',10,'2013-10-07 02:42:28'),(1730,'N3',1,'2013-10-07 03:42:28'),(1731,'N1',2,'2013-10-19 04:42:28'),(1732,'N2',3,'2013-10-23 05:42:28'),(1733,'N3',4,'2013-10-23 06:42:28'),(1734,'N1',5,'2013-10-05 07:42:28'),(1735,'N2',6,'2013-10-24 08:42:28'),(1736,'N3',7,'2013-10-14 09:42:28'),(1737,'N1',8,'2013-10-29 10:42:28'),(1738,'N2',9,'2013-10-01 13:42:28'),(1739,'N3',10,'2013-10-28 14:42:28'),(1740,'N1',1,'2013-10-12 15:42:28'),(1741,'N2',2,'2013-10-18 16:42:28'),(1742,'N3',3,'2013-10-27 17:42:28'),(1743,'N1',4,'2013-10-22 18:42:28'),(1744,'N2',5,'2013-10-25 19:42:28'),(1745,'N3',6,'2013-10-06 20:42:28'),(1746,'N1',7,'2013-10-06 21:42:28'),(1747,'N2',8,'2013-10-10 22:42:28'),(1748,'N3',9,'2013-10-01 23:42:28'),(1749,'N1',10,'2013-10-09 00:42:28'),(1750,'N2',1,'2013-10-09 01:42:28'),(1751,'N3',2,'2013-10-12 02:42:28'),(1752,'N1',3,'2013-10-12 03:42:28'),(1753,'N2',4,'2013-10-09 04:42:28'),(1754,'N3',5,'2013-10-14 05:42:28'),(1755,'N1',6,'2013-10-13 06:42:28'),(1756,'N2',7,'2013-10-21 07:42:28'),(1757,'N3',8,'2013-10-03 08:42:28'),(1758,'N1',9,'2013-10-09 09:42:28'),(1759,'N2',10,'2013-10-28 10:42:28'),(1760,'N3',1,'2013-10-19 13:42:28'),(1761,'N1',2,'2013-10-20 14:42:28'),(1762,'N2',3,'2013-10-27 15:42:28'),(1763,'N3',4,'2013-10-16 16:42:28'),(1764,'N1',5,'2013-10-20 17:42:28'),(1765,'N2',6,'2013-10-28 18:42:28'),(1766,'N3',7,'2013-10-05 19:42:28'),(1767,'N1',8,'2013-10-02 20:42:28'),(1768,'N2',9,'2013-10-23 21:42:28'),(1769,'N3',10,'2013-10-22 22:42:28'),(1770,'N1',1,'2013-10-28 23:42:28'),(1771,'N2',2,'2013-10-21 00:42:28'),(1772,'N3',3,'2013-10-24 01:42:28'),(1773,'N1',4,'2013-10-05 02:42:28'),(1774,'N2',5,'2013-10-25 03:42:28'),(1775,'N3',6,'2013-10-18 04:42:28'),(1776,'N1',7,'2013-10-25 05:42:28'),(1777,'N2',8,'2013-10-18 06:42:28'),(1778,'N3',9,'2013-10-23 07:42:28'),(1779,'N1',10,'2013-10-14 08:42:28'),(1780,'N2',1,'2013-10-19 09:42:28'),(1781,'N3',2,'2013-10-05 10:42:28'),(1782,'N1',3,'2013-10-12 13:42:28'),(1783,'N2',4,'2013-10-07 14:42:28'),(1784,'N3',5,'2013-10-25 15:42:28'),(1785,'N1',6,'2013-10-19 16:42:28'),(1786,'N2',7,'2013-10-23 17:42:28'),(1787,'N3',8,'2013-10-27 18:42:28'),(1788,'N1',9,'2013-10-13 19:42:28'),(1789,'N2',10,'2013-10-07 20:42:28'),(1790,'N3',1,'2013-10-13 21:42:28'),(1791,'N1',2,'2013-10-09 22:42:28'),(1792,'N2',3,'2013-10-13 23:42:28'),(1793,'N3',4,'2013-10-28 00:42:28'),(1794,'N1',5,'2013-10-12 01:42:28'),(1795,'N2',6,'2013-10-07 02:42:28'),(1796,'N3',7,'2013-10-08 03:42:28'),(1797,'N1',8,'2013-10-05 04:42:28'),(1798,'N2',9,'2013-10-15 05:42:28'),(1799,'N3',10,'2013-10-13 06:42:28'),(1800,'N1',1,'2013-10-03 07:42:28'),(1801,'N2',2,'2013-10-05 08:42:28'),(1802,'N3',3,'2013-10-10 09:42:28'),(1803,'N1',4,'2013-10-06 10:42:28'),(1804,'N2',5,'2013-10-10 13:42:28'),(1805,'N3',6,'2013-10-04 14:42:28'),(1806,'N1',7,'2013-10-26 15:42:28'),(1807,'N2',8,'2013-10-11 16:42:28'),(1808,'N3',9,'2013-10-21 17:42:28'),(1809,'N1',10,'2013-10-21 18:42:28'),(1810,'N2',1,'2013-10-19 19:42:28'),(1811,'N3',2,'2013-10-13 20:42:28'),(1812,'N1',3,'2013-10-02 21:42:28'),(1813,'N2',4,'2013-10-02 22:42:28'),(1814,'N3',5,'2013-10-05 23:42:28'),(1815,'N1',6,'2013-10-18 00:42:28'),(1816,'N2',7,'2013-10-22 01:42:28'),(1817,'N3',8,'2013-10-21 02:42:28'),(1818,'N1',9,'2013-10-26 03:42:28'),(1819,'N2',10,'2013-10-11 04:42:28'),(1820,'N3',1,'2013-10-05 05:42:28'),(1821,'N1',2,'2013-10-13 06:42:28'),(1822,'N2',3,'2013-10-11 07:42:28'),(1823,'N3',4,'2013-10-18 08:42:28'),(1824,'N1',5,'2013-10-18 09:42:28'),(1825,'N2',6,'2013-10-05 10:42:28'),(1826,'N3',7,'2013-10-05 13:42:28'),(1827,'N1',8,'2013-10-01 14:42:28'),(1828,'N2',9,'2013-10-25 15:42:28'),(1829,'N3',10,'2013-10-14 16:42:28'),(1830,'N1',1,'2013-10-04 17:42:28'),(1831,'N2',2,'2013-10-20 18:42:28'),(1832,'N3',3,'2013-10-14 19:42:28'),(1833,'N1',4,'2013-10-08 20:42:28'),(1834,'N2',5,'2013-10-18 21:42:28'),(1835,'N3',6,'2013-10-22 22:42:28'),(1836,'N1',7,'2013-10-09 23:42:28'),(1837,'N2',8,'2013-10-27 00:42:28'),(1838,'N3',9,'2013-10-16 01:42:28'),(1839,'N1',10,'2013-10-18 02:42:28'),(1840,'N2',1,'2013-10-18 03:42:28'),(1841,'N3',2,'2013-10-17 04:42:28'),(1842,'N1',3,'2013-10-19 05:42:28'),(1843,'N2',4,'2013-10-07 06:42:28'),(1844,'N3',5,'2013-10-28 07:42:28'),(1845,'N1',6,'2013-10-15 08:42:28'),(1846,'N2',7,'2013-10-15 09:42:28'),(1847,'N3',8,'2013-10-24 10:42:28'),(1848,'N1',9,'2013-10-27 13:42:28'),(1849,'N2',10,'2013-10-16 14:42:28'),(1850,'N3',1,'2013-10-07 15:42:28'),(1851,'N1',2,'2013-10-02 16:42:28'),(1852,'N2',3,'2013-10-20 17:42:28'),(1853,'N3',4,'2013-10-04 18:42:28'),(1854,'N1',5,'2013-10-06 19:42:28'),(1855,'N2',6,'2013-10-26 20:42:28'),(1856,'N3',7,'2013-10-22 21:42:28'),(1857,'N1',8,'2013-10-06 22:42:28'),(1858,'N2',9,'2013-10-28 23:42:28'),(1859,'N3',10,'2013-10-19 00:42:28'),(1860,'N1',1,'2013-10-28 01:42:28'),(1861,'N2',2,'2013-10-25 02:42:28'),(1862,'N3',3,'2013-10-04 03:42:28'),(1863,'N1',4,'2013-10-08 04:42:28'),(1864,'N2',5,'2013-10-30 05:42:28'),(1865,'N3',6,'2013-10-23 06:42:28'),(1866,'N1',7,'2013-10-30 07:42:28'),(1867,'N2',8,'2013-10-17 08:42:28'),(1868,'N3',9,'2013-10-07 09:42:28'),(1869,'N1',10,'2013-10-30 10:42:28'),(1870,'N2',1,'2013-10-02 13:42:28'),(1871,'N3',2,'2013-10-09 14:42:28'),(1872,'N1',3,'2013-10-07 15:42:28'),(1873,'N2',4,'2013-10-16 16:42:28'),(1874,'N3',5,'2013-10-18 17:42:28'),(1875,'N1',6,'2013-10-28 18:42:28'),(1876,'N2',7,'2013-10-02 19:42:28'),(1877,'N3',8,'2013-10-26 20:42:28'),(1878,'N1',9,'2013-10-02 21:42:28'),(1879,'N2',10,'2013-10-08 22:42:28'),(1880,'N3',1,'2013-10-14 23:42:28'),(1881,'N1',2,'2013-10-24 00:42:28'),(1882,'N2',3,'2013-10-21 01:42:28'),(1883,'N3',4,'2013-10-07 02:42:28'),(1884,'N1',5,'2013-10-28 03:42:28'),(1885,'N2',6,'2013-10-05 04:42:28'),(1886,'N3',7,'2013-10-16 05:42:28'),(1887,'N1',8,'2013-10-04 06:42:28'),(1888,'N2',9,'2013-10-20 07:42:28'),(1889,'N3',10,'2013-10-30 08:42:28'),(1890,'N1',1,'2013-10-04 09:42:28'),(1891,'N2',2,'2013-10-15 10:42:28'),(1892,'N3',3,'2013-10-17 13:42:28'),(1893,'N1',4,'2013-10-08 14:42:28'),(1894,'N2',5,'2013-10-29 15:42:28'),(1895,'N3',6,'2013-10-06 16:42:28'),(1896,'N1',7,'2013-10-21 17:42:28'),(1897,'N2',8,'2013-10-29 18:42:28'),(1898,'N3',9,'2013-10-24 19:42:28'),(1899,'N1',10,'2013-10-23 20:42:28'),(1900,'N2',1,'2013-10-29 21:42:28'),(1901,'N3',2,'2013-10-21 22:42:28'),(1902,'N1',3,'2013-10-20 23:42:28'),(1903,'N2',4,'2013-10-25 00:42:28'),(1904,'N3',5,'2013-10-04 01:42:28'),(1905,'N1',6,'2013-10-27 02:42:28'),(1906,'N2',7,'2013-10-27 03:42:28'),(1907,'N3',8,'2013-10-03 04:42:28'),(1908,'N1',9,'2013-10-24 05:42:28'),(1909,'N2',10,'2013-10-05 06:42:28'),(1910,'N3',1,'2013-10-08 07:42:28'),(1911,'N1',2,'2013-10-18 08:42:28'),(1912,'N2',3,'2013-10-30 09:42:28'),(1913,'N3',4,'2013-10-04 10:42:28'),(1914,'N1',5,'2013-10-14 13:42:28'),(1915,'N2',6,'2013-10-02 14:42:28'),(1916,'N3',7,'2013-10-25 15:42:28'),(1917,'N1',8,'2013-10-27 16:42:28'),(1918,'N2',9,'2013-10-29 17:42:28'),(1919,'N3',10,'2013-10-16 18:42:29'),(1920,'N1',1,'2013-10-02 19:42:29'),(1921,'N2',2,'2013-10-15 20:42:29'),(1922,'N3',3,'2013-10-20 21:42:29'),(1923,'N1',4,'2013-10-06 22:42:29'),(1924,'N2',5,'2013-10-08 23:42:29'),(1925,'N3',6,'2013-10-17 00:42:29'),(1926,'N1',7,'2013-10-05 01:42:29'),(1927,'N2',8,'2013-10-29 02:42:29'),(1928,'N3',9,'2013-10-04 03:42:29'),(1929,'N1',10,'2013-10-16 04:42:29'),(1930,'N2',1,'2013-10-26 05:42:29'),(1931,'N3',2,'2013-10-20 06:42:29'),(1932,'N1',3,'2013-10-17 07:42:29'),(1933,'N2',4,'2013-10-24 08:42:29'),(1934,'N3',5,'2013-10-11 09:42:29'),(1935,'N1',6,'2013-10-23 10:42:29'),(1936,'N2',7,'2013-10-28 13:42:29'),(1937,'N3',8,'2013-10-03 14:42:29'),(1938,'N1',9,'2013-10-27 15:42:29'),(1939,'N2',10,'2013-10-13 16:42:29'),(1940,'N3',1,'2013-10-18 17:42:29'),(1941,'N1',2,'2013-10-13 18:42:29'),(1942,'N2',3,'2013-10-03 19:42:29'),(1943,'N3',4,'2013-10-29 20:42:29'),(1944,'N1',5,'2013-10-26 21:42:29'),(1945,'N2',6,'2013-10-12 22:42:29'),(1946,'N3',7,'2013-10-07 23:42:29'),(1947,'N1',8,'2013-10-04 00:42:29'),(1948,'N2',9,'2013-10-04 01:42:29'),(1949,'N3',10,'2013-10-22 02:42:29'),(1950,'N1',1,'2013-10-30 03:42:29'),(1951,'N2',2,'2013-10-04 04:42:29'),(1952,'N3',3,'2013-10-21 05:42:29'),(1953,'N1',4,'2013-10-18 06:42:29'),(1954,'N2',5,'2013-10-20 07:42:29'),(1955,'N3',6,'2013-10-28 08:42:29'),(1956,'N1',7,'2013-10-13 09:42:29'),(1957,'N2',8,'2013-10-11 10:42:29'),(1958,'N3',9,'2013-10-22 13:42:29'),(1959,'N1',10,'2013-10-21 14:42:29'),(1960,'N2',1,'2013-10-25 15:42:29'),(1961,'N3',2,'2013-10-23 16:42:29'),(1962,'N1',3,'2013-10-15 17:42:29'),(1963,'N2',4,'2013-10-01 18:42:29'),(1964,'N3',5,'2013-10-07 19:42:29'),(1965,'N1',6,'2013-10-11 20:42:29'),(1966,'N2',7,'2013-10-06 21:42:29'),(1967,'N3',8,'2013-10-07 22:42:29'),(1968,'N1',9,'2013-10-11 23:42:29'),(1969,'N2',10,'2013-10-20 00:42:29'),(1970,'N3',1,'2013-10-23 01:42:29'),(1971,'N1',2,'2013-10-05 02:42:29'),(1972,'N2',3,'2013-10-22 03:42:29'),(1973,'N3',4,'2013-10-03 04:42:29'),(1974,'N1',5,'2013-10-27 05:42:29'),(1975,'N2',6,'2013-10-13 06:42:29'),(1976,'N3',7,'2013-10-18 07:42:29'),(1977,'N1',8,'2013-10-08 08:42:29'),(1978,'N2',9,'2013-10-15 09:42:29'),(1979,'N3',10,'2013-10-26 10:42:29'),(1980,'N1',1,'2013-10-26 13:42:29'),(1981,'N2',2,'2013-10-12 14:42:29'),(1982,'N3',3,'2013-10-18 15:42:29'),(1983,'N1',4,'2013-10-22 16:42:29'),(1984,'N2',5,'2013-10-19 17:42:29'),(1985,'N3',6,'2013-10-24 18:42:29'),(1986,'N1',7,'2013-10-11 19:42:29'),(1987,'N2',8,'2013-10-28 20:42:29'),(1988,'N3',9,'2013-10-29 21:42:29'),(1989,'N1',10,'2013-10-27 22:42:29'),(1990,'N2',1,'2013-10-16 23:42:29'),(1991,'N3',2,'2013-10-16 00:42:29'),(1992,'N1',3,'2013-10-15 01:42:29'),(1993,'N2',4,'2013-10-28 02:42:29'),(1994,'N3',5,'2013-10-12 03:42:29'),(1995,'N1',6,'2013-10-28 04:42:29'),(1996,'N2',7,'2013-10-04 05:42:29'),(1997,'N3',8,'2013-10-08 06:42:29'),(1998,'N1',9,'2013-10-05 07:42:29'),(1999,'N2',10,'2013-10-12 08:42:29');
/*!40000 ALTER TABLE `dl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalmeeting`
--

DROP TABLE IF EXISTS `externalmeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalmeeting` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `crmuserId` mediumint(9) NOT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `starttime` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `contactIds` varchar(512) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `activity_type` mediumint(9) DEFAULT NULL,
  `coachId` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `externalmeeting`
--

LOCK TABLES `externalmeeting` WRITE;
/*!40000 ALTER TABLE `externalmeeting` DISABLE KEYS */;
INSERT INTO `externalmeeting` VALUES (32,20,1375804800000,1375804800000,'拜访','[7,167]',2,2,0);
/*!40000 ALTER TABLE `externalmeeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internalmeeting`
--

DROP TABLE IF EXISTS `internalmeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `internalmeeting` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `crmuserId` mediumint(9) NOT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `starttime` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `contactId` mediumint(9) DEFAULT NULL,
  `crmusermanagerId` mediumint(9) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `activity_type` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internalmeeting`
--

LOCK TABLES `internalmeeting` WRITE;
/*!40000 ALTER TABLE `internalmeeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `internalmeeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_level1_pl`
--

DROP TABLE IF EXISTS `region_level1_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region_level1_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_level1_pl`
--

LOCK TABLES `region_level1_pl` WRITE;
/*!40000 ALTER TABLE `region_level1_pl` DISABLE KEYS */;
INSERT INTO `region_level1_pl` VALUES (1,'北中国'),(2,'南中国'),(3,'东中国');
/*!40000 ALTER TABLE `region_level1_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_level2_pl`
--

DROP TABLE IF EXISTS `region_level2_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region_level2_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_level2_pl`
--

LOCK TABLES `region_level2_pl` WRITE;
/*!40000 ALTER TABLE `region_level2_pl` DISABLE KEYS */;
INSERT INTO `region_level2_pl` VALUES (1,'华北'),(2,'西北'),(3,'东北'),(4,'西南'),(5,'华南'),(6,'华中'),(7,'华东');
/*!40000 ALTER TABLE `region_level2_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'高级用户'),(2,'主管'),(3,'销售代表');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_visiting_purpose_pl`
--

DROP TABLE IF EXISTS `sales_visiting_purpose_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_visiting_purpose_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_visiting_purpose_pl`
--

LOCK TABLES `sales_visiting_purpose_pl` WRITE;
/*!40000 ALTER TABLE `sales_visiting_purpose_pl` DISABLE KEYS */;
INSERT INTO `sales_visiting_purpose_pl` VALUES (1,'会议安排'),(2,'会议跟进'),(3,'交接工作'),(4,'了解竞争');
/*!40000 ALTER TABLE `sales_visiting_purpose_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex_pl`
--

DROP TABLE IF EXISTS `sex_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex_pl`
--

LOCK TABLES `sex_pl` WRITE;
/*!40000 ALTER TABLE `sex_pl` DISABLE KEYS */;
INSERT INTO `sex_pl` VALUES (1,'男'),(2,'女');
/*!40000 ALTER TABLE `sex_pl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-15  9:51:11
