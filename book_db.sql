-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: java_db
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_book`
--

DROP TABLE IF EXISTS `tb_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_book` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `bookid` varchar(45) DEFAULT NULL,
  `bookname` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_book`
--

LOCK TABLES `tb_book` WRITE;
/*!40000 ALTER TABLE `tb_book` DISABLE KEYS */;
INSERT INTO `tb_book` VALUES (4,'a1','aa','bb','cc'),(5,'a2','aa','aaa','aaaa'),(6,'a3','cc','ccc','홍길동');
/*!40000 ALTER TABLE `tb_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_game`
--

DROP TABLE IF EXISTS `tb_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_game` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `win` int DEFAULT NULL,
  `loss` int DEFAULT NULL,
  `draw` int DEFAULT NULL,
  `date_reg` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_game`
--

LOCK TABLES `tb_game` WRITE;
/*!40000 ALTER TABLE `tb_game` DISABLE KEYS */;
INSERT INTO `tb_game` VALUES (1,0,0,1,'2023-08-07 17:08:23'),(2,1,0,1,'2023-08-07 17:08:28'),(3,1,0,2,'2023-08-07 17:08:33'),(4,1,1,2,'2023-08-07 17:08:38'),(5,2,1,2,'2023-08-07 17:09:29'),(6,3,1,2,'2023-08-07 17:09:49'),(7,1,0,0,'2023-08-07 17:10:58'),(8,0,1,0,'2023-08-07 17:11:16'),(9,0,2,0,'2023-08-07 17:11:20'),(10,0,2,0,'2023-08-07 17:12:27'),(11,0,2,0,'2023-08-07 17:12:33'),(12,0,0,1,'2023-08-07 17:18:58'),(13,1,0,0,'2023-08-07 17:19:02'),(14,0,0,1,'2023-08-07 17:19:06'),(15,0,0,1,'2023-08-07 17:19:10'),(16,0,0,1,'2023-08-07 17:19:14');
/*!40000 ALTER TABLE `tb_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_info`
--

DROP TABLE IF EXISTS `tb_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_info` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `id` varchar(45) DEFAULT NULL,
  `pw` varchar(45) DEFAULT NULL,
  `nick` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `hp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_info`
--

LOCK TABLES `tb_info` WRITE;
/*!40000 ALTER TABLE `tb_info` DISABLE KEYS */;
INSERT INTO `tb_info` VALUES (1,'hjk','qwer','hjk','hjk','0100000'),(2,'hjk1','qwer','hjk1','hjk1','02020202');
/*!40000 ALTER TABLE `tb_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_japangi`
--

DROP TABLE IF EXISTS `tb_japangi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_japangi` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `goods1name` varchar(45) DEFAULT NULL,
  `goods1price` int DEFAULT NULL,
  `goods1cnt` int DEFAULT NULL,
  `goods2name` varchar(45) DEFAULT NULL,
  `goods2price` int DEFAULT NULL,
  `goods2cnt` int DEFAULT NULL,
  `goods3name` varchar(45) DEFAULT NULL,
  `goods3price` int DEFAULT NULL,
  `goods3cnt` int DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_japangi`
--

LOCK TABLES `tb_japangi` WRITE;
/*!40000 ALTER TABLE `tb_japangi` DISABLE KEYS */;
INSERT INTO `tb_japangi` VALUES (1,'a',111,11,'b',121,12,'c',131,13),(2,'1',3,7,'2',5,8,'3',6,9),(3,'펩시',1313,2,'소다',1313,2,'포도',13131,2),(4,'코카',1300,10,'사이다',2000,10,'환타 파인',1500,10),(5,'1',2,3,'1',2,3,'1',2,3),(6,'코카',1200,12,'스프',1600,15,'판타',1800,20),(7,'코카콜라',1800,19,'스프라이트',1700,26,'환타오렌지',1600,21);
/*!40000 ALTER TABLE `tb_japangi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_lend`
--

DROP TABLE IF EXISTS `tb_lend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_lend` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `checkid` varchar(45) DEFAULT NULL,
  `bookname` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `date_reg` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lend`
--

LOCK TABLES `tb_lend` WRITE;
/*!40000 ALTER TABLE `tb_lend` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_lend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_member`
--

DROP TABLE IF EXISTS `tb_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `id` varchar(45) DEFAULT NULL,
  `pw` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `hp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_member`
--

LOCK TABLES `tb_member` WRITE;
/*!40000 ALTER TABLE `tb_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-07 17:14:04
