-- MySQL dump 10.13  Distrib 5.5.30, for osx10.6 (i386)
--
-- Host: localhost    Database: crmdb
-- ------------------------------------------------------
-- Server version	5.5.30

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (3,'12312',NULL,'1','1',NULL,1,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,'李99','2013-09-28 17:00:28','李99',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_administrativ_level_pl`
--

LOCK TABLES `account_administrativ_level_pl` WRITE;
/*!40000 ALTER TABLE `account_administrativ_level_pl` DISABLE KEYS */;
INSERT INTO `account_administrativ_level_pl` VALUES (1,'一级'),(2,'一级甲等'),(3,'一级乙等'),(4,'一级丙等'),(5,'二级'),(6,'二级甲等'),(7,'二级乙等'),(8,'二级丙等'),(9,'三级'),(10,'三级甲等');
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
-- Table structure for table `account_medicalType_pl`
--

DROP TABLE IF EXISTS `account_medicalType_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_medicalType_pl` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_medicalType_pl`
--

LOCK TABLES `account_medicalType_pl` WRITE;
/*!40000 ALTER TABLE `account_medicalType_pl` DISABLE KEYS */;
INSERT INTO `account_medicalType_pl` VALUES (1,'麻药目标医院'),(2,'慢痛目标医院'),(3,'其他');
/*!40000 ALTER TABLE `account_medicalType_pl` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountcrmuser`
--

LOCK TABLES `accountcrmuser` WRITE;
/*!40000 ALTER TABLE `accountcrmuser` DISABLE KEYS */;
INSERT INTO `accountcrmuser` VALUES (2114,3,1),(2115,3,2),(2116,3,3),(2117,3,4),(2118,3,5),(2119,3,6),(2120,3,7),(2121,3,8),(2113,3,99),(2014,5,20),(2016,6,20),(2017,7,20),(2018,8,20),(2019,9,20),(2020,10,20),(2021,11,20),(2022,12,20),(2023,13,20),(2024,14,20),(2025,15,20),(2026,16,20),(2027,17,20),(2028,18,20),(2029,19,20),(2030,20,20),(2031,21,20),(2032,22,20),(2033,23,20),(2034,24,20),(2035,25,20),(2036,26,20),(2037,27,20),(2038,28,20),(2039,29,20),(2040,30,20),(2041,31,20),(2042,32,20),(2043,33,20),(2044,34,20),(2045,35,20),(2046,36,20),(2047,37,20),(2048,38,20),(2049,39,20),(2050,40,20),(2051,41,20),(2052,42,20),(2053,43,20),(2054,44,20),(2055,45,20),(2056,46,20),(2057,47,20),(2058,48,20),(2059,49,20),(2060,50,20),(2061,51,20),(2062,52,20),(2109,53,5),(2110,53,15),(2063,53,20),(2064,54,20),(2065,55,20),(2066,56,20),(2067,57,20),(2068,58,20),(2069,59,20),(2070,60,20),(2071,61,20),(2072,62,20),(2073,63,20),(2074,64,20),(2075,65,20),(2076,66,20),(2077,67,20),(2078,68,20),(2079,69,20),(2080,70,20),(2081,71,20),(2082,72,20),(2083,73,20),(2084,74,20),(2085,75,20),(2086,76,20),(2087,77,20),(2088,78,20),(2089,79,20),(2090,80,20),(2091,81,20),(2092,82,20),(2093,83,20),(2094,84,20),(2095,85,20),(2096,86,20),(2097,87,20),(2098,88,20),(2099,89,20),(2100,90,20),(2101,91,20),(2102,92,20),(2103,93,20),(2104,94,20),(2105,95,20),(2106,96,20),(2107,97,20),(2108,98,20);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,NULL,20,2,1380479760000,1380476160000,'辅导工作',1,7,1,1,1,NULL,'李20','2013-10-01 01:37:15','李20','2013-10-01 01:37:15','李20'),(4,NULL,2,2,1382380740000,1382377140000,'cc',1,7,1,1,1,NULL,'李20','2013-10-01 01:40:17','李20','2013-10-01 01:40:17','李20');
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_visiting_purpose_pl`
--

LOCK TABLES `activity_visiting_purpose_pl` WRITE;
/*!40000 ALTER TABLE `activity_visiting_purpose_pl` DISABLE KEYS */;
INSERT INTO `activity_visiting_purpose_pl` VALUES (1,'传递产品知识'),(2,'处方观念沟通'),(3,'病例沟通'),(4,'会议安排'),(5,'会议跟进'),(6,'交接工作'),(7,'了解竞争');
/*!40000 ALTER TABLE `activity_visiting_purpose_pl` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'北京','1'),(2,'天津市','2'),(3,'石家庄市','3'),(4,'唐山市','3'),(5,'秦皇岛市','3'),(6,'邯郸市','3'),(7,'邢台市','3'),(8,'保定市','3'),(9,'张家口市','3'),(10,'承德市','3'),(11,'沧州市','3'),(12,'衡水市','3'),(13,'太原市','4'),(14,'大同市','4'),(15,'阳泉市','4'),(16,'长治市','4'),(17,'晋城市','4'),(18,'朔州市','4'),(19,'忻州市','4'),(20,'吕梁市','4'),(21,'晋中市','4'),(22,'呼和浩特市','5'),(23,'包头市','5'),(24,'乌海市','5'),(25,'赤峰市','5'),(26,'呼伦贝尔市','5'),(27,'兴安盟','5'),(28,'通辽市','5'),(29,'锡林郭勒盟','5'),(30,'乌兰察布盟','5'),(31,'沈阳市','6'),(32,'大连市','6'),(33,'鞍山市','6'),(34,'抚顺市','6'),(35,'丹东市','6'),(36,'锦州市','6'),(37,'阜新市','6'),(38,'辽阳市','6'),(39,'盘锦市','6'),(40,'铁岭市','6'),(41,'朝阳市','6'),(42,'长春市','7'),(43,'吉林市','7'),(44,'四平市','7'),(45,'四平市','7'),(46,'辽源市','7'),(47,'通化市','7'),(48,'白山市','7'),(49,'松原市','7'),(50,'白城市','7'),(51,'延边朝鲜族自治州','7'),(52,'长沙市','8'),(53,'株洲市','8'),(54,'湘潭市','8'),(55,'衡阳市','8'),(56,'邵阳市','8'),(57,'岳阳市','8'),(58,'张家界市','8'),(59,'郴州市','8'),(60,'永州市','8'),(61,'怀化市','8'),(62,'海口市','9'),(63,'三亚市','9'),(64,'重庆市','10'),(65,'成都市','11'),(66,'自贡市','11'),(67,'攀枝花市','11'),(68,'贵阳市','12'),(69,'六盘水市','12'),(70,'遵义市','12'),(71,'昆明市','13'),(72,'曲靖市','13'),(73,'玉溪市','13'),(74,'拉萨市','14'),(75,'昌都地区','14'),(76,'山南地区','14'),(77,'兰州市','15'),(78,'嘉峪关市','15'),(79,'金昌市','15'),(80,'西宁市','16'),(81,'海东地区','16'),(82,'海北藏族自治州','16'),(83,'银川市','17'),(84,'石嘴山市','17'),(85,'吴忠市','17'),(86,'乌鲁木齐市','18'),(87,'克拉玛依市','18'),(88,'吐鲁番地区','18'),(89,'南宁市','19'),(90,'柳州市','19'),(91,'桂林市','19'),(92,'武汉市','20'),(93,'黄石市','20'),(94,'十堰市','20'),(95,'郑州市','21'),(96,'开封市','21'),(97,'洛阳市','21');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (5,'cdcdcd',3,'1',1,NULL,NULL,NULL,NULL,NULL,'1',1,1,NULL,NULL,NULL,1,1,NULL,NULL,'李99','2013-09-28 17:00:40','李99',NULL,NULL),(6,'122',3,'1',1,NULL,NULL,NULL,NULL,'sd@sina.sdfdsf','1',1,1,NULL,NULL,NULL,1,2,NULL,NULL,'李99','2013-09-28 21:48:12','李99','2013-09-30 21:15:00',NULL),(7,'asdsd',3,'1',1,'as',NULL,NULL,NULL,NULL,'1',1,1,NULL,NULL,NULL,1,1,NULL,NULL,'李99','2013-09-30 07:45:00','李99','2013-09-30 07:45:00',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=1371 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactcrmuser`
--

LOCK TABLES `contactcrmuser` WRITE;
/*!40000 ALTER TABLE `contactcrmuser` DISABLE KEYS */;
INSERT INTO `contactcrmuser` VALUES (1349,1,1),(1350,1,5),(1351,1,6),(1352,1,7),(1353,1,8),(1354,1,9),(1355,1,10),(1356,1,11),(1357,1,12),(1358,1,13),(1363,1,20),(1344,1,99),(1362,2,21),(1345,2,99),(1361,3,21),(1346,3,99),(1347,4,99),(1365,5,99),(1366,6,99),(1368,7,1),(1369,7,2),(1370,7,3),(1138,7,20),(1367,7,99),(1139,8,20),(1140,9,20),(1141,10,20),(1142,11,20),(1143,12,20),(1144,13,20),(1145,14,20),(1146,15,20),(1147,16,20),(1148,17,20),(1149,18,20),(1150,19,20),(1151,20,20),(1152,21,20),(1153,22,20),(1154,23,20),(1155,24,20),(1156,25,20),(1157,26,20),(1158,27,20),(1159,28,20),(1160,29,20),(1161,30,20),(1162,31,20),(1163,32,20),(1164,33,20),(1165,34,20),(1166,35,20),(1167,36,20),(1168,37,20),(1169,38,20),(1170,39,20),(1171,40,20),(1172,41,20),(1173,42,20),(1174,43,20),(1175,44,20),(1176,45,20),(1177,46,20),(1178,47,20),(1179,48,20),(1180,49,20),(1181,50,20),(1182,51,20),(1183,52,20),(1184,53,20),(1185,54,20),(1186,55,20),(1187,56,20),(1188,57,20),(1189,58,20),(1190,59,20),(1191,60,20),(1192,61,20),(1193,62,20),(1194,63,20),(1195,64,20),(1196,65,20),(1197,66,20),(1198,67,20),(1199,68,20),(1200,69,20),(1201,70,20),(1202,71,20),(1203,72,20),(1204,73,20),(1205,74,20),(1206,75,20),(1207,76,20),(1208,77,20),(1209,78,20),(1210,79,20),(1211,80,20),(1212,81,20),(1213,82,20),(1214,83,20),(1215,84,20),(1216,85,20),(1217,86,20),(1218,87,20),(1219,88,20),(1220,89,20),(1221,90,20),(1222,91,20),(1223,92,20),(1224,93,20),(1225,94,20),(1226,95,20),(1227,96,20),(1228,97,20),(1229,98,20),(1230,99,20),(1231,100,20),(1232,101,20),(1233,102,20),(1234,103,20),(1235,104,20),(1236,105,20),(1237,106,20),(1238,107,20),(1239,108,20),(1240,109,20),(1241,110,20),(1242,111,20),(1243,112,20),(1244,113,20),(1245,114,20),(1246,115,20),(1247,116,20),(1248,117,20),(1249,118,20),(1250,119,20),(1251,120,20),(1252,121,20),(1253,122,20),(1254,123,20),(1255,124,20),(1256,125,20),(1257,126,20),(1258,127,20),(1259,128,20),(1260,129,20),(1261,130,20),(1262,131,20),(1263,132,20),(1264,133,20),(1265,134,20),(1266,135,20),(1267,136,20),(1268,137,20),(1269,138,20),(1270,139,20),(1271,140,20),(1272,141,20),(1273,142,20),(1274,143,20),(1275,144,20),(1276,145,20),(1277,146,20),(1278,147,20),(1279,148,20),(1280,149,20),(1281,150,20),(1282,151,20),(1283,152,20),(1284,153,20),(1285,154,20),(1286,155,20),(1287,156,20),(1288,157,20),(1289,158,20),(1290,159,20),(1291,160,20),(1292,161,20),(1343,161,23),(1342,161,34),(1293,162,20),(1294,163,20),(1295,164,20),(1296,165,20),(1297,166,20),(1298,167,20),(1299,168,20),(1300,169,20),(1301,170,20),(1302,171,20),(1303,172,20),(1304,173,20),(1305,174,20),(1306,175,20),(1307,176,20),(1308,177,20),(1309,178,20),(1339,180,4),(1334,180,12),(1311,180,20),(1312,181,20),(1313,182,20),(1314,183,20),(1315,184,20),(1316,185,20),(1317,186,20),(1318,187,20),(1320,189,20),(1333,190,2),(1321,190,20),(1335,190,33),(1322,191,20),(1323,192,20),(1324,193,20),(1325,194,20),(1326,195,20),(1327,196,20),(1328,197,20),(1340,198,5),(1341,198,15),(1331,198,55),(1330,199,20);
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
  `cityId` mediumint(9) NOT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `pl2` mediumint(9) DEFAULT NULL,
  `role` mediumint(9) DEFAULT NULL,
  `pl4` mediumint(9) DEFAULT NULL,
  `pl5` mediumint(9) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sessionKey` varchar(255) DEFAULT NULL,
  `lastLoginTime` bigint(20) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  `reportto` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crmuser`
--

LOCK TABLES `crmuser` WRITE;
/*!40000 ALTER TABLE `crmuser` DISABLE KEYS */;
INSERT INTO `crmuser` VALUES (1,'李1',NULL,'医疗设备部','13811991','myemail1@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',42,1,1,2,2,4,'522177','827ccb0eea8a706c4c34a16891f84e7b','18247',NULL,NULL,20),(2,'李2',NULL,'医疗设备部','13811992','myemail2@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',70,1,1,3,2,2,'40125','827ccb0eea8a706c4c34a16891f84e7b','785502',NULL,NULL,20),(3,'李3',NULL,'医疗设备部','13811993','myemail3@gmail.com',NULL,'/crm-admin/image/3.jpeg','职员',12,1,2,3,1,4,'633094','827ccb0eea8a706c4c34a16891f84e7b','871771',NULL,NULL,20),(4,'李4',NULL,'医疗设备部','13811994','myemail4@gmail.com',NULL,'/crm-admin/image/18.jpeg','职员',53,1,1,1,2,4,'44098','827ccb0eea8a706c4c34a16891f84e7b','1347',NULL,NULL,20),(5,'李5',NULL,'医疗设备部','13811995','myemail5@gmail.com',NULL,'/crm-admin/image/13.jpeg','职员',79,1,2,3,1,2,'320207','827ccb0eea8a706c4c34a16891f84e7b','390426',NULL,NULL,20),(6,'李6',NULL,'医疗设备部','13811996','myemail6@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',29,1,2,3,2,5,'467744','827ccb0eea8a706c4c34a16891f84e7b','947086',NULL,NULL,20),(7,'李7',NULL,'医疗设备部','13811997','myemail7@gmail.com',NULL,'/crm-admin/image/19.jpeg','职员',80,1,1,2,1,1,'377096','827ccb0eea8a706c4c34a16891f84e7b','563155',NULL,NULL,20),(8,'李8',NULL,'医疗设备部','13811998','myemail8@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',5,1,2,2,1,2,'481251','827ccb0eea8a706c4c34a16891f84e7b','973518',NULL,NULL,20),(9,'李9',NULL,'医疗设备部','13811999','myemail9@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',4,1,2,3,1,1,'273968','827ccb0eea8a706c4c34a16891f84e7b','177125',NULL,NULL,20),(10,'李10',NULL,'医疗设备部','138119910','myemail10@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',54,1,1,3,1,1,'925086','827ccb0eea8a706c4c34a16891f84e7b','964072',NULL,NULL,20),(11,'李11',NULL,'医疗设备部','138119911','myemail11@gmail.com',NULL,'/crm-admin/image/3.jpeg','职员',11,1,2,1,1,5,'802529','827ccb0eea8a706c4c34a16891f84e7b','287984',NULL,NULL,20),(12,'李12',NULL,'医疗设备部','138119912','myemail12@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',92,1,1,1,1,1,'236384','827ccb0eea8a706c4c34a16891f84e7b','546707',NULL,NULL,20),(13,'李13',NULL,'医疗设备部','138119913','myemail13@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',61,1,1,2,1,3,'773337','827ccb0eea8a706c4c34a16891f84e7b','868582',NULL,NULL,20),(14,'李14',NULL,'医疗设备部','138119914','myemail14@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',6,1,2,3,1,6,'156532','827ccb0eea8a706c4c34a16891f84e7b','701788',NULL,NULL,20),(15,'李15',NULL,'医疗设备部','138119915','myemail15@gmail.com',NULL,'/crm-admin/image/9.jpeg','职员',42,1,1,3,2,6,'461651','827ccb0eea8a706c4c34a16891f84e7b','902197',NULL,NULL,20),(16,'李16',NULL,'医疗设备部','138119916','myemail16@gmail.com',NULL,'/crm-admin/image/12.jpeg','职员',13,1,1,2,1,6,'837660','827ccb0eea8a706c4c34a16891f84e7b','404622',NULL,NULL,20),(17,'李17',NULL,'医疗设备部','138119917','myemail17@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',78,1,2,3,1,5,'802347','827ccb0eea8a706c4c34a16891f84e7b','315519',NULL,NULL,20),(18,'李18',NULL,'医疗设备部','138119918','myemail18@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',11,1,1,3,1,2,'497756','827ccb0eea8a706c4c34a16891f84e7b','362731',NULL,NULL,20),(19,'李19',NULL,'医疗设备部','138119919','myemail19@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',65,1,2,3,2,4,'80741','827ccb0eea8a706c4c34a16891f84e7b','866096',NULL,NULL,20),(20,'李20',NULL,'医疗设备部','138119920','myemail20@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',1,1,2,2,2,3,'tig','827ccb0eea8a706c4c34a16891f84e7b','241290',NULL,NULL,0),(21,'李21',NULL,'医疗设备部','138119921','myemail21@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',75,1,2,3,2,3,'pi','827ccb0eea8a706c4c34a16891f84e7b','607162',NULL,NULL,20),(22,'李22',NULL,'医疗设备部','138119922','myemail22@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',23,1,1,3,1,2,'ki','827ccb0eea8a706c4c34a16891f84e7b','310942',NULL,NULL,20),(23,'李23',NULL,'医疗设备部','138119923','myemail23@gmail.com',NULL,'/crm-admin/image/18.jpeg','职员',93,1,2,3,2,3,'39443','827ccb0eea8a706c4c34a16891f84e7b','732222',NULL,NULL,20),(24,'李24',NULL,'医疗设备部','138119924','myemail24@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',6,1,1,3,2,4,'824836','827ccb0eea8a706c4c34a16891f84e7b','727286',NULL,NULL,20),(25,'李25',NULL,'医疗设备部','138119925','myemail25@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',40,1,1,3,2,5,'4852','827ccb0eea8a706c4c34a16891f84e7b','438765',NULL,NULL,20),(26,'李26',NULL,'医疗设备部','138119926','myemail26@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',12,1,2,1,1,2,'548750','827ccb0eea8a706c4c34a16891f84e7b','10965',NULL,NULL,20),(27,'李27',NULL,'医疗设备部','138119927','myemail27@gmail.com',NULL,'/crm-admin/image/19.jpeg','职员',63,1,2,3,1,4,'728194','827ccb0eea8a706c4c34a16891f84e7b','737534',NULL,NULL,20),(28,'李28',NULL,'医疗设备部','138119928','myemail28@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',10,1,2,3,1,6,'993723','827ccb0eea8a706c4c34a16891f84e7b','653774',NULL,NULL,20),(29,'李29',NULL,'医疗设备部','138119929','myemail29@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',71,1,2,2,2,4,'783034','827ccb0eea8a706c4c34a16891f84e7b','55267',NULL,NULL,20),(30,'李30',NULL,'医疗设备部','138119930','myemail30@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',5,1,2,3,1,3,'932998','827ccb0eea8a706c4c34a16891f84e7b','314014',NULL,NULL,20),(31,'李31',NULL,'医疗设备部','138119931','myemail31@gmail.com',NULL,'/crm-admin/image/18.jpeg','职员',4,1,2,2,2,5,'314892','827ccb0eea8a706c4c34a16891f84e7b','403271',NULL,NULL,20),(32,'李32',NULL,'医疗设备部','138119932','myemail32@gmail.com',NULL,'/crm-admin/image/13.jpeg','职员',42,1,2,1,1,5,'774466','827ccb0eea8a706c4c34a16891f84e7b','73311',NULL,NULL,20),(33,'李33',NULL,'医疗设备部','138119933','myemail33@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',96,1,2,3,1,1,'926655','827ccb0eea8a706c4c34a16891f84e7b','155743',NULL,NULL,20),(34,'李34',NULL,'医疗设备部','138119934','myemail34@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',52,1,2,1,2,4,'308877','827ccb0eea8a706c4c34a16891f84e7b','557785',NULL,NULL,20),(35,'李35',NULL,'医疗设备部','138119935','myemail35@gmail.com',NULL,'/crm-admin/image/2.jpeg','职员',58,1,1,1,1,6,'763421','827ccb0eea8a706c4c34a16891f84e7b','320694',NULL,NULL,20),(36,'李36',NULL,'医疗设备部','138119936','myemail36@gmail.com',NULL,'/crm-admin/image/12.jpeg','职员',54,1,2,2,1,5,'889473','827ccb0eea8a706c4c34a16891f84e7b','929117',NULL,NULL,20),(37,'李37',NULL,'医疗设备部','138119937','myemail37@gmail.com',NULL,'/crm-admin/image/8.jpeg','职员',89,1,1,3,2,3,'156101','827ccb0eea8a706c4c34a16891f84e7b','682505',NULL,NULL,20),(38,'李38',NULL,'医疗设备部','138119938','myemail38@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',59,1,1,1,2,2,'111089','827ccb0eea8a706c4c34a16891f84e7b','624172',NULL,NULL,20),(39,'李39',NULL,'医疗设备部','138119939','myemail39@gmail.com',NULL,'/crm-admin/image/14.jpeg','职员',39,1,2,2,2,4,'86143','827ccb0eea8a706c4c34a16891f84e7b','72346',NULL,NULL,20),(40,'李40',NULL,'医疗设备部','138119940','myemail40@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',57,1,2,3,2,6,'96449','827ccb0eea8a706c4c34a16891f84e7b','488216',NULL,NULL,20),(41,'李41',NULL,'医疗设备部','138119941','myemail41@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',60,1,2,3,2,6,'222816','827ccb0eea8a706c4c34a16891f84e7b','223042',NULL,NULL,20),(42,'李42',NULL,'医疗设备部','138119942','myemail42@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',18,1,2,3,1,3,'823732','827ccb0eea8a706c4c34a16891f84e7b','649564',NULL,NULL,20),(43,'李43',NULL,'医疗设备部','138119943','myemail43@gmail.com',NULL,'/crm-admin/image/14.jpeg','职员',45,1,2,2,1,3,'449213','827ccb0eea8a706c4c34a16891f84e7b','577695',NULL,NULL,20),(44,'李44',NULL,'医疗设备部','138119944','myemail44@gmail.com',NULL,'/crm-admin/image/13.jpeg','职员',31,1,1,1,2,1,'773872','827ccb0eea8a706c4c34a16891f84e7b','938780',NULL,NULL,20),(45,'李45',NULL,'医疗设备部','138119945','myemail45@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',67,1,2,1,2,5,'520719','827ccb0eea8a706c4c34a16891f84e7b','959819',NULL,NULL,20),(46,'李46',NULL,'医疗设备部','138119946','myemail46@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',80,1,2,1,2,5,'280979','827ccb0eea8a706c4c34a16891f84e7b','981755',NULL,NULL,20),(47,'李47',NULL,'医疗设备部','138119947','myemail47@gmail.com',NULL,'/crm-admin/image/2.jpeg','职员',19,1,1,3,1,4,'841739','827ccb0eea8a706c4c34a16891f84e7b','28318',NULL,NULL,20),(48,'李48',NULL,'医疗设备部','138119948','myemail48@gmail.com',NULL,'/crm-admin/image/11.jpeg','职员',49,1,1,2,2,4,'364760','827ccb0eea8a706c4c34a16891f84e7b','195325',NULL,NULL,20),(49,'李49',NULL,'医疗设备部','138119949','myemail49@gmail.com',NULL,'/crm-admin/image/11.jpeg','职员',95,1,1,3,2,1,'297581','827ccb0eea8a706c4c34a16891f84e7b','890670',NULL,NULL,20),(50,'李50',NULL,'医疗设备部','138119950','myemail50@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',10,1,1,3,2,2,'392628','827ccb0eea8a706c4c34a16891f84e7b','866378',NULL,NULL,20),(51,'李51',NULL,'医疗设备部','138119951','myemail51@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',65,1,1,3,1,2,'69398','827ccb0eea8a706c4c34a16891f84e7b','658878',NULL,NULL,20),(52,'李52',NULL,'医疗设备部','138119952','myemail52@gmail.com',NULL,'/crm-admin/image/2.jpeg','职员',30,1,2,1,1,2,'168105','827ccb0eea8a706c4c34a16891f84e7b','694258',NULL,NULL,20),(53,'李53',NULL,'医疗设备部','138119953','myemail53@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',52,1,2,1,1,4,'631332','827ccb0eea8a706c4c34a16891f84e7b','493656',NULL,NULL,20),(54,'李54',NULL,'医疗设备部','138119954','myemail54@gmail.com',NULL,'/crm-admin/image/2.jpeg','职员',66,1,1,2,1,6,'651345','827ccb0eea8a706c4c34a16891f84e7b','384509',NULL,NULL,20),(55,'李55',NULL,'医疗设备部','138119955','myemail55@gmail.com',NULL,'/crm-admin/image/12.jpeg','职员',42,1,2,3,1,3,'361730','827ccb0eea8a706c4c34a16891f84e7b','440574',NULL,NULL,20),(56,'李56',NULL,'医疗设备部','138119956','myemail56@gmail.com',NULL,'/crm-admin/image/17.jpeg','职员',35,1,2,1,2,2,'853617','827ccb0eea8a706c4c34a16891f84e7b','48344',NULL,NULL,20),(57,'李57',NULL,'医疗设备部','138119957','myemail57@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',43,1,1,3,2,5,'181893','827ccb0eea8a706c4c34a16891f84e7b','919000',NULL,NULL,20),(58,'李58',NULL,'医疗设备部','138119958','myemail58@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',22,1,2,2,2,3,'347616','827ccb0eea8a706c4c34a16891f84e7b','448968',NULL,NULL,20),(59,'李59',NULL,'医疗设备部','138119959','myemail59@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',77,1,2,2,2,5,'191401','827ccb0eea8a706c4c34a16891f84e7b','486840',NULL,NULL,20),(60,'李60',NULL,'医疗设备部','138119960','myemail60@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',35,1,1,3,2,4,'913159','827ccb0eea8a706c4c34a16891f84e7b','86298',NULL,NULL,20),(61,'李61',NULL,'医疗设备部','138119961','myemail61@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',23,1,2,3,2,5,'990592','827ccb0eea8a706c4c34a16891f84e7b','969970',NULL,NULL,20),(62,'李62',NULL,'医疗设备部','138119962','myemail62@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',55,1,1,1,2,5,'212482','827ccb0eea8a706c4c34a16891f84e7b','589957',NULL,NULL,20),(63,'李63',NULL,'医疗设备部','138119963','myemail63@gmail.com',NULL,'/crm-admin/image/9.jpeg','职员',84,1,1,2,1,5,'89634','827ccb0eea8a706c4c34a16891f84e7b','38877',NULL,NULL,20),(64,'李64',NULL,'医疗设备部','138119964','myemail64@gmail.com',NULL,'/crm-admin/image/7.jpeg','职员',65,1,2,3,2,6,'809726','827ccb0eea8a706c4c34a16891f84e7b','423512',NULL,NULL,20),(65,'李65',NULL,'医疗设备部','138119965','myemail65@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',58,1,1,3,2,3,'778728','827ccb0eea8a706c4c34a16891f84e7b','999930',NULL,NULL,20),(66,'李66',NULL,'医疗设备部','138119966','myemail66@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',79,1,1,2,1,3,'463463','827ccb0eea8a706c4c34a16891f84e7b','728113',NULL,NULL,20),(67,'李67',NULL,'医疗设备部','138119967','myemail67@gmail.com',NULL,'/crm-admin/image/9.jpeg','职员',91,1,1,3,2,1,'980132','827ccb0eea8a706c4c34a16891f84e7b','639777',NULL,NULL,20),(68,'李68',NULL,'医疗设备部','138119968','myemail68@gmail.com',NULL,'/crm-admin/image/14.jpeg','职员',1,1,2,2,1,6,'509269','827ccb0eea8a706c4c34a16891f84e7b','13545',NULL,NULL,20),(69,'李69',NULL,'医疗设备部','138119969','myemail69@gmail.com',NULL,'/crm-admin/image/3.jpeg','职员',66,1,2,3,2,6,'604953','827ccb0eea8a706c4c34a16891f84e7b','147396',NULL,NULL,20),(70,'李70',NULL,'医疗设备部','138119970','myemail70@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',44,1,2,2,2,2,'495957','827ccb0eea8a706c4c34a16891f84e7b','695343',NULL,NULL,20),(71,'李71',NULL,'医疗设备部','138119971','myemail71@gmail.com',NULL,'/crm-admin/image/13.jpeg','职员',66,1,2,1,2,3,'663926','827ccb0eea8a706c4c34a16891f84e7b','33531',NULL,NULL,20),(72,'李72',NULL,'医疗设备部','138119972','myemail72@gmail.com',NULL,'/crm-admin/image/14.jpeg','职员',56,1,1,3,1,6,'830761','827ccb0eea8a706c4c34a16891f84e7b','80624',NULL,NULL,20),(73,'李73',NULL,'医疗设备部','138119973','myemail73@gmail.com',NULL,'/crm-admin/image/10.jpeg','职员',84,1,2,1,2,5,'161029','827ccb0eea8a706c4c34a16891f84e7b','301526',NULL,NULL,20),(74,'李74',NULL,'医疗设备部','138119974','myemail74@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',79,1,2,1,2,2,'311861','827ccb0eea8a706c4c34a16891f84e7b','264762',NULL,NULL,20),(75,'李75',NULL,'医疗设备部','138119975','myemail75@gmail.com',NULL,'/crm-admin/image/15.jpeg','职员',70,1,2,2,1,1,'75217','827ccb0eea8a706c4c34a16891f84e7b','418232',NULL,NULL,20),(76,'李76',NULL,'医疗设备部','138119976','myemail76@gmail.com',NULL,'/crm-admin/image/8.jpeg','职员',51,1,2,3,2,1,'439502','827ccb0eea8a706c4c34a16891f84e7b','295875',NULL,NULL,20),(77,'李77',NULL,'医疗设备部','138119977','myemail77@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',74,1,1,3,1,6,'970860','827ccb0eea8a706c4c34a16891f84e7b','223677',NULL,NULL,20),(78,'李78',NULL,'医疗设备部','138119978','myemail78@gmail.com',NULL,'/crm-admin/image/3.jpeg','职员',78,1,1,3,2,3,'534795','827ccb0eea8a706c4c34a16891f84e7b','229764',NULL,NULL,20),(79,'李79',NULL,'医疗设备部','138119979','myemail79@gmail.com',NULL,'/crm-admin/image/8.jpeg','职员',69,1,2,3,1,4,'760397','827ccb0eea8a706c4c34a16891f84e7b','476787',NULL,NULL,20),(80,'李80',NULL,'医疗设备部','138119980','myemail80@gmail.com',NULL,'/crm-admin/image/12.jpeg','职员',62,1,1,1,1,2,'196598','827ccb0eea8a706c4c34a16891f84e7b','693644',NULL,NULL,20),(81,'李81',NULL,'医疗设备部','138119981','myemail81@gmail.com',NULL,'/crm-admin/image/12.jpeg','职员',87,1,1,3,2,6,'700801','827ccb0eea8a706c4c34a16891f84e7b','36861',NULL,NULL,20),(82,'李82',NULL,'医疗设备部','138119982','myemail82@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',1,1,1,1,1,3,'913212','827ccb0eea8a706c4c34a16891f84e7b','102371',NULL,NULL,20),(83,'李83',NULL,'医疗设备部','138119983','myemail83@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',20,1,1,3,1,1,'462656','827ccb0eea8a706c4c34a16891f84e7b','400275',NULL,NULL,20),(84,'李84',NULL,'医疗设备部','138119984','myemail84@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',8,1,1,3,1,1,'572645','827ccb0eea8a706c4c34a16891f84e7b','693260',NULL,NULL,20),(85,'李85',NULL,'医疗设备部','138119985','myemail85@gmail.com',NULL,'/crm-admin/image/5.jpeg','职员',25,1,2,3,2,6,'474256','827ccb0eea8a706c4c34a16891f84e7b','264479',NULL,NULL,20),(86,'李86',NULL,'医疗设备部','138119986','myemail86@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',70,1,2,3,1,4,'652345','827ccb0eea8a706c4c34a16891f84e7b','241613',NULL,NULL,20),(87,'李87',NULL,'医疗设备部','138119987','myemail87@gmail.com',NULL,'/crm-admin/image/17.jpeg','职员',13,1,2,3,2,1,'837958','827ccb0eea8a706c4c34a16891f84e7b','413628',NULL,NULL,20),(88,'李88',NULL,'医疗设备部','138119988','myemail88@gmail.com',NULL,'/crm-admin/image/18.jpeg','职员',19,1,1,3,2,2,'231754','827ccb0eea8a706c4c34a16891f84e7b','342301',NULL,NULL,20),(89,'李89',NULL,'医疗设备部','138119989','myemail89@gmail.com',NULL,'/crm-admin/image/2.jpeg','职员',79,1,2,3,2,5,'643898','827ccb0eea8a706c4c34a16891f84e7b','469622',NULL,NULL,20),(90,'李90',NULL,'医疗设备部','138119990','myemail90@gmail.com',NULL,'/crm-admin/image/8.jpeg','职员',56,1,1,2,2,1,'523229','827ccb0eea8a706c4c34a16891f84e7b','320209',NULL,NULL,20),(91,'李91',NULL,'医疗设备部','138119991','myemail91@gmail.com',NULL,'/crm-admin/image/9.jpeg','职员',34,1,1,2,1,4,'683450','827ccb0eea8a706c4c34a16891f84e7b','191180',NULL,NULL,20),(92,'李92',NULL,'医疗设备部','138119992','myemail92@gmail.com',NULL,'/crm-admin/image/16.jpeg','职员',75,1,1,3,1,3,'846564','827ccb0eea8a706c4c34a16891f84e7b','994271',NULL,NULL,20),(93,'李93',NULL,'医疗设备部','138119993','myemail93@gmail.com',NULL,'/crm-admin/image/4.jpeg','职员',89,1,2,3,1,4,'181471','827ccb0eea8a706c4c34a16891f84e7b','396817',NULL,NULL,20),(94,'李94',NULL,'医疗设备部','138119994','myemail94@gmail.com',NULL,'/crm-admin/image/14.jpeg','职员',58,1,1,3,1,2,'366665','827ccb0eea8a706c4c34a16891f84e7b','1000273',NULL,NULL,20),(95,'李95',NULL,'医疗设备部','138119995','myemail95@gmail.com',NULL,'/crm-admin/image/3.jpeg','职员',80,1,2,2,1,2,'287910','827ccb0eea8a706c4c34a16891f84e7b','809913',NULL,NULL,20),(96,'李96',NULL,'医疗设备部','138119996','myemail96@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',26,1,1,3,1,2,'338557','827ccb0eea8a706c4c34a16891f84e7b','47749',NULL,NULL,20),(97,'李97',NULL,'医疗设备部','138119997','myemail97@gmail.com',NULL,'/crm-admin/image/6.jpeg','职员',41,1,1,2,1,5,'828056','827ccb0eea8a706c4c34a16891f84e7b','808004',NULL,NULL,20),(98,'李98',NULL,'医疗设备部','138119998','myemail98@gmail.com',NULL,'/crm-admin/image/11.jpeg','职员',77,1,2,2,1,3,'123609','827ccb0eea8a706c4c34a16891f84e7b','895773',NULL,NULL,20),(99,'李99',NULL,'医疗设备部','138119999','myemail99@gmail.com',NULL,'/crm-admin/image/1.jpeg','职员',93,1,2,1,1,1,'li','827ccb0eea8a706c4c34a16891f84e7b','53856',NULL,NULL,20);
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
-- Table structure for table `dealerAccount`
--

DROP TABLE IF EXISTS `dealerAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealerAccount` (
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
-- Dumping data for table `dealerAccount`
--

LOCK TABLES `dealerAccount` WRITE;
/*!40000 ALTER TABLE `dealerAccount` DISABLE KEYS */;
INSERT INTO `dealerAccount` VALUES (1,'经销商0','北京市朝阳区望京东路0号','1381199260',1,1,1,NULL),(2,'经销商1','北京市朝阳区望京东路1号','1381199261',2,2,2,NULL),(3,'经销商2','北京市朝阳区望京东路2号','1381199262',1,3,3,NULL),(4,'经销商3','北京市朝阳区望京东路3号','1381199263',2,1,4,NULL),(5,'经销商4','北京市朝阳区望京东路4号','1381199264',1,2,5,NULL),(6,'经销商5','北京市朝阳区望京东路5号','1381199265',2,3,6,NULL),(7,'经销商6','北京市朝阳区望京东路6号','1381199266',1,1,7,NULL),(8,'经销商7','北京市朝阳区望京东路7号','1381199267',2,2,1,NULL),(9,'经销商8','北京市朝阳区望京东路8号','1381199268',1,3,2,NULL),(10,'经销商9','北京市朝阳区望京东路9号','1381199269',2,1,3,NULL),(11,'经销商10','北京市朝阳区望京东路10号','13811992610',1,2,4,NULL),(12,'经销商11','北京市朝阳区望京东路11号','13811992611',2,3,5,NULL),(13,'经销商12','北京市朝阳区望京东路12号','13811992612',1,1,6,NULL),(14,'经销商13','北京市朝阳区望京东路13号','13811992613',2,2,7,NULL),(15,'经销商14','北京市朝阳区望京东路14号','13811992614',1,3,1,NULL),(16,'经销商15','北京市朝阳区望京东路15号','13811992615',2,1,2,NULL),(17,'经销商16','北京市朝阳区望京东路16号','13811992616',1,2,3,NULL),(18,'经销商17','北京市朝阳区望京东路17号','13811992617',2,3,4,NULL),(19,'经销商18','北京市朝阳区望京东路18号','13811992618',1,1,5,NULL),(20,'经销商19','北京市朝阳区望京东路19号','13811992619',2,2,6,NULL),(21,'经销商20','北京市朝阳区望京东路20号','13811992620',1,3,7,NULL),(22,'经销商21','北京市朝阳区望京东路21号','13811992621',2,1,1,NULL),(23,'经销商22','北京市朝阳区望京东路22号','13811992622',1,2,2,NULL),(24,'经销商23','北京市朝阳区望京东路23号','13811992623',2,3,3,NULL),(25,'经销商24','北京市朝阳区望京东路24号','13811992624',1,1,4,NULL),(26,'经销商25','北京市朝阳区望京东路25号','13811992625',2,2,5,NULL),(27,'经销商26','北京市朝阳区望京东路26号','13811992626',1,3,6,NULL),(28,'经销商27','北京市朝阳区望京东路27号','13811992627',2,1,7,NULL),(29,'经销商28','北京市朝阳区望京东路28号','13811992628',1,2,1,NULL),(30,'经销商29','北京市朝阳区望京东路29号','13811992629',2,3,2,NULL),(31,'经销商30','北京市朝阳区望京东路30号','13811992630',1,1,3,NULL),(32,'经销商31','北京市朝阳区望京东路31号','13811992631',2,2,4,NULL),(33,'经销商32','北京市朝阳区望京东路32号','13811992632',1,3,5,NULL),(34,'经销商33','北京市朝阳区望京东路33号','13811992633',2,1,6,NULL),(35,'经销商34','北京市朝阳区望京东路34号','13811992634',1,2,7,NULL),(36,'经销商35','北京市朝阳区望京东路35号','13811992635',2,3,1,NULL),(37,'经销商36','北京市朝阳区望京东路36号','13811992636',1,1,2,NULL),(38,'经销商37','北京市朝阳区望京东路37号','13811992637',2,2,3,NULL),(39,'经销商38','北京市朝阳区望京东路38号','13811992638',1,3,4,NULL),(40,'经销商39','北京市朝阳区望京东路39号','13811992639',2,1,5,NULL),(41,'经销商40','北京市朝阳区望京东路40号','13811992640',1,2,6,NULL),(42,'经销商41','北京市朝阳区望京东路41号','13811992641',2,3,7,NULL),(43,'经销商42','北京市朝阳区望京东路42号','13811992642',1,1,1,NULL),(44,'经销商43','北京市朝阳区望京东路43号','13811992643',2,2,2,NULL),(45,'经销商44','北京市朝阳区望京东路44号','13811992644',1,3,3,NULL),(46,'经销商45','北京市朝阳区望京东路45号','13811992645',2,1,4,NULL),(47,'经销商46','北京市朝阳区望京东路46号','13811992646',1,2,5,NULL),(48,'经销商47','北京市朝阳区望京东路47号','13811992647',2,3,6,NULL),(49,'经销商48','北京市朝阳区望京东路48号','13811992648',1,1,7,NULL),(50,'经销商49','北京市朝阳区望京东路49号','13811992649',2,2,1,NULL),(51,'经销商50','北京市朝阳区望京东路50号','13811992650',1,3,2,NULL),(52,'经销商51','北京市朝阳区望京东路51号','13811992651',2,1,3,NULL),(53,'经销商52','北京市朝阳区望京东路52号','13811992652',1,2,4,NULL),(54,'经销商53','北京市朝阳区望京东路53号','13811992653',2,3,5,NULL),(55,'经销商54','北京市朝阳区望京东路54号','13811992654',1,1,6,NULL),(56,'经销商55','北京市朝阳区望京东路55号','13811992655',2,2,7,NULL),(57,'经销商56','北京市朝阳区望京东路56号','13811992656',1,3,1,NULL),(58,'经销商57','北京市朝阳区望京东路57号','13811992657',2,1,2,NULL),(59,'经销商58','北京市朝阳区望京东路58号','13811992658',1,2,3,NULL),(60,'经销商59','北京市朝阳区望京东路59号','13811992659',2,3,4,NULL),(61,'经销商60','北京市朝阳区望京东路60号','13811992660',1,1,5,NULL),(62,'经销商61','北京市朝阳区望京东路61号','13811992661',2,2,6,NULL),(63,'经销商62','北京市朝阳区望京东路62号','13811992662',1,3,7,NULL),(64,'经销商63','北京市朝阳区望京东路63号','13811992663',2,1,1,NULL),(65,'经销商64','北京市朝阳区望京东路64号','13811992664',1,2,2,NULL),(66,'经销商65','北京市朝阳区望京东路65号','13811992665',2,3,3,NULL),(67,'经销商66','北京市朝阳区望京东路66号','13811992666',1,1,4,NULL),(68,'经销商67','北京市朝阳区望京东路67号','13811992667',2,2,5,NULL),(69,'经销商68','北京市朝阳区望京东路68号','13811992668',1,3,6,NULL),(70,'经销商69','北京市朝阳区望京东路69号','13811992669',2,1,7,NULL),(71,'经销商70','北京市朝阳区望京东路70号','13811992670',1,2,1,NULL),(72,'经销商71','北京市朝阳区望京东路71号','13811992671',2,3,2,NULL),(73,'经销商72','北京市朝阳区望京东路72号','13811992672',1,1,3,NULL),(74,'经销商73','北京市朝阳区望京东路73号','13811992673',2,2,4,NULL),(75,'经销商74','北京市朝阳区望京东路74号','13811992674',1,3,5,NULL),(76,'经销商75','北京市朝阳区望京东路75号','13811992675',2,1,6,NULL),(77,'经销商76','北京市朝阳区望京东路76号','13811992676',1,2,7,NULL),(78,'经销商77','北京市朝阳区望京东路77号','13811992677',2,3,1,NULL),(79,'经销商78','北京市朝阳区望京东路78号','13811992678',1,1,2,NULL),(80,'经销商79','北京市朝阳区望京东路79号','13811992679',2,2,3,NULL),(81,'经销商80','北京市朝阳区望京东路80号','13811992680',1,3,4,NULL),(82,'经销商81','北京市朝阳区望京东路81号','13811992681',2,1,5,NULL),(83,'经销商82','北京市朝阳区望京东路82号','13811992682',1,2,6,NULL),(84,'经销商83','北京市朝阳区望京东路83号','13811992683',2,3,7,NULL),(85,'经销商84','北京市朝阳区望京东路84号','13811992684',1,1,1,NULL),(86,'经销商85','北京市朝阳区望京东路85号','13811992685',2,2,2,NULL),(87,'经销商86','北京市朝阳区望京东路86号','13811992686',1,3,3,NULL),(88,'经销商87','北京市朝阳区望京东路87号','13811992687',2,1,4,NULL);
/*!40000 ALTER TABLE `dealerAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealerContact`
--

DROP TABLE IF EXISTS `dealerContact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealerContact` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` mediumint(9) DEFAULT NULL,
  `tel_work` varchar(255) DEFAULT NULL,
  `dealerAccountId` mediumint(9) NOT NULL,
  `status` mediumint(9) DEFAULT NULL,
  `pl1` mediumint(9) DEFAULT NULL,
  `whenadded` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealerContact`
--

LOCK TABLES `dealerContact` WRITE;
/*!40000 ALTER TABLE `dealerContact` DISABLE KEYS */;
INSERT INTO `dealerContact` VALUES (1,'张历历0',1,'1381199260',18,1,3,NULL),(2,'张历历1',2,'1381199261',19,2,2,NULL),(3,'张历历2',1,'1381199262',6,1,1,NULL),(4,'张历历3',2,'1381199263',13,2,2,NULL),(5,'张历历4',1,'1381199264',44,1,2,NULL),(6,'张历历5',2,'1381199265',67,2,2,NULL),(7,'张历历6',1,'1381199266',66,1,1,NULL),(8,'张历历7',2,'1381199267',24,2,2,NULL),(9,'张历历8',1,'1381199268',45,1,1,NULL),(10,'张历历9',2,'1381199269',25,2,2,NULL),(11,'张历历10',1,'13811992610',12,1,1,NULL),(12,'张历历11',2,'13811992611',55,2,3,NULL),(13,'张历历12',1,'13811992612',22,1,2,NULL),(14,'张历历13',2,'13811992613',60,2,1,NULL),(15,'张历历14',1,'13811992614',28,1,1,NULL),(16,'张历历15',2,'13811992615',36,2,3,NULL),(17,'张历历16',1,'13811992616',44,1,1,NULL),(18,'张历历17',2,'13811992617',77,2,2,NULL),(19,'张历历18',1,'13811992618',29,1,3,NULL),(20,'张历历19',2,'13811992619',1,2,1,NULL),(21,'张历历20',1,'13811992620',67,1,3,NULL),(22,'张历历21',2,'13811992621',6,2,1,NULL),(23,'张历历22',1,'13811992622',19,1,3,NULL),(24,'张历历23',2,'13811992623',12,2,1,NULL),(25,'张历历24',1,'13811992624',34,1,1,NULL),(26,'张历历25',2,'13811992625',3,2,3,NULL),(27,'张历历26',1,'13811992626',36,1,1,NULL),(28,'张历历27',2,'13811992627',1,2,3,NULL),(29,'张历历28',1,'13811992628',80,1,3,NULL),(30,'张历历29',2,'13811992629',32,2,3,NULL),(31,'张历历30',1,'13811992630',59,1,1,NULL),(32,'张历历31',2,'13811992631',55,2,3,NULL),(33,'张历历32',1,'13811992632',65,1,3,NULL),(34,'张历历33',2,'13811992633',65,2,2,NULL),(35,'张历历34',1,'13811992634',65,1,2,NULL),(36,'张历历35',2,'13811992635',76,2,2,NULL),(37,'张历历36',1,'13811992636',11,1,3,NULL),(38,'张历历37',2,'13811992637',1,2,3,NULL),(39,'张历历38',1,'13811992638',45,1,2,NULL),(40,'张历历39',2,'13811992639',40,2,2,NULL),(41,'张历历40',1,'13811992640',27,1,1,NULL),(42,'张历历41',2,'13811992641',42,2,1,NULL),(43,'张历历42',1,'13811992642',15,1,3,NULL),(44,'张历历43',2,'13811992643',17,2,3,NULL),(45,'张历历44',1,'13811992644',9,1,3,NULL),(46,'张历历45',2,'13811992645',52,2,3,NULL),(47,'张历历46',1,'13811992646',58,1,1,NULL),(48,'张历历47',2,'13811992647',9,2,3,NULL),(49,'张历历48',1,'13811992648',44,1,1,NULL),(50,'张历历49',2,'13811992649',30,2,3,NULL),(51,'张历历50',1,'13811992650',48,1,1,NULL),(52,'张历历51',2,'13811992651',19,2,3,NULL),(53,'张历历52',1,'13811992652',16,1,2,NULL),(54,'张历历53',2,'13811992653',41,2,2,NULL),(55,'张历历54',1,'13811992654',45,1,1,NULL),(56,'张历历55',2,'13811992655',17,2,1,NULL),(57,'张历历56',1,'13811992656',1,1,3,NULL),(58,'张历历57',2,'13811992657',54,2,2,NULL),(59,'张历历58',1,'13811992658',46,1,2,NULL),(60,'张历历59',2,'13811992659',63,2,1,NULL),(61,'张历历60',1,'13811992660',28,1,1,NULL),(62,'张历历61',2,'13811992661',48,2,3,NULL),(63,'张历历62',1,'13811992662',10,1,2,NULL),(64,'张历历63',2,'13811992663',80,2,1,NULL),(65,'张历历64',1,'13811992664',59,1,1,NULL),(66,'张历历65',2,'13811992665',19,2,1,NULL),(67,'张历历66',1,'13811992666',43,1,1,NULL),(68,'张历历67',2,'13811992667',32,2,2,NULL),(69,'张历历68',1,'13811992668',62,1,1,NULL),(70,'张历历69',2,'13811992669',17,2,3,NULL),(71,'张历历70',1,'13811992670',54,1,1,NULL),(72,'张历历71',2,'13811992671',63,2,1,NULL),(73,'张历历72',1,'13811992672',62,1,3,NULL),(74,'张历历73',2,'13811992673',5,2,1,NULL),(75,'张历历74',1,'13811992674',46,1,3,NULL),(76,'张历历75',2,'13811992675',10,2,3,NULL),(77,'张历历76',1,'13811992676',48,1,3,NULL),(78,'张历历77',2,'13811992677',63,2,1,NULL),(79,'张历历78',1,'13811992678',46,1,1,NULL),(80,'张历历79',2,'13811992679',70,2,3,NULL);
/*!40000 ALTER TABLE `dealerContact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalMeeting`
--

DROP TABLE IF EXISTS `externalMeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalMeeting` (
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
-- Dumping data for table `externalMeeting`
--

LOCK TABLES `externalMeeting` WRITE;
/*!40000 ALTER TABLE `externalMeeting` DISABLE KEYS */;
INSERT INTO `externalMeeting` VALUES (32,20,1375804800000,1375804800000,'拜访','[7,167]',2,2,0);
/*!40000 ALTER TABLE `externalMeeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internalMeeting`
--

DROP TABLE IF EXISTS `internalMeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `internalMeeting` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `crmuserId` mediumint(9) NOT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `starttime` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `contactId` mediumint(9) DEFAULT NULL,
  `crmusermanagerId` mediumint(9) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internalMeeting`
--

LOCK TABLES `internalMeeting` WRITE;
/*!40000 ALTER TABLE `internalMeeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `internalMeeting` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'北京'),(2,'天津市'),(3,'河北省'),(4,'山西省'),(5,'内蒙古'),(6,'辽宁省'),(7,'吉林省'),(8,'湖南省'),(9,'海南省'),(10,'重庆市'),(11,'四川省'),(12,'贵州省'),(13,'云南省'),(14,'西藏'),(15,'甘肃省'),(16,'青海省'),(17,'宁夏'),(18,'新疆'),(19,'广西'),(20,'湖北省'),(21,'河南省');
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

-- Dump completed on 2013-10-01  2:09:12
