-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: bankingservice
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `practice`
--

DROP TABLE IF EXISTS `practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `practice` (
  `practice_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `financing_id` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES ('OF101214','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',0.00),('BH0815','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',0.00),('HM1606','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('FX1069','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('TP181717','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('JI141810','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('DE21614','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('IV121612','CREATED','ferdinando','dilevrano','dileale@gmail.com@gmail.com','393444343532','F001',15000.00),('HA0152','CREATED','alessandro','dilevrano','dileale@gmail.com','33974325','',0.00),('WL81811','CREATED','ferdinando','dilevrano','dileferdi@gmail.com','393444343532','F001',15000.00),('WS41216','CREATED','ferdinando','dilevrano','dileferdi@gmail.com','393444343532','F001',15000.00);
/*!40000 ALTER TABLE `practice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-15 18:26:58
