-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: 172.19.0.2    Database: bankingservice
-- ------------------------------------------------------
-- Server version	8.0.37

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
  `additional_info` json DEFAULT NULL,
  `financing_info` json DEFAULT NULL,
  `vehicle_info` json DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_info` json DEFAULT NULL,
  `personal_document` json DEFAULT NULL,
  `credit_document` longtext,
  `user_data` json DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice`
--

LOCK TABLES `practice` WRITE;
/*!40000 ALTER TABLE `practice` DISABLE KEYS */;
INSERT INTO `practice` VALUES ('AN4164','COMPLETED','alessandro','dilevrano','alessandro','fdfd@gmail.com','{\"job\": \"Software Developer\", \"gender\": \"male\", \"province\": \"Taranto\", \"dateOfBirth\": \"1992-01-15\"}','{\"loanTerm\": 36.0, \"vehicleId\": \"F123\", \"loanAmount\": 15000.0, \"financingId\": \"F001\"}','{\"year\": 2021, \"brand\": \"Ford\", \"model\": \"Fusion\", \"vehicleId\": \"F123\"}','transfer','{\"Owner\": \"John Doe\", \"BankId\": \"IT2141825\"}','{\"documentId\": \"CA88078DH\", \"expireDate\": \"2029-01-15\", \"documentType\": \"DNI\"}','asdfasfafaf','{\"step\": \"completed\", \"addressInfo\": {\"resTown\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"resAddress\": \"789 Oak St\", \"resCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"resZipCode\": \"10002\", \"resProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}, \"isDomEqualsRes\": true}, \"accountCheck\": true, \"documentInfo\": {\"idDocumentType\": \"DriverLicense\", \"idDocumentNumber\": \"123456789\", \"idDocumentExpireDate\": \"2025-06-01\", \"healthInsuranceNumber\": \"123456789\", \"idDocumentReleaseCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"idDocumentReleaseDate\": \"2015-06-01\", \"idDocumentReleaseEntity\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"idDocumentReleaseProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"householdInfo\": {\"familyMembers\": \"1\", \"houseProperty\": {\"code\": \"R\", \"label\": \"Rented\", \"value\": true}, \"maritalStatus\": {\"code\": \"S\", \"label\": \"Single\", \"value\": true}, \"workingMembers\": \"1\", \"workStartingDate\": \"2012-06-01\"}, \"personalDetails\": {\"pep\": false, \"email\": \"jane.doe@example.com\", \"phone\": \"+1 917 555 1234\", \"gender\": \"F\", \"surname\": \"Doe\", \"taxCode\": \"98765432109\", \"birthCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"birthDate\": \"1990-05-15\", \"firstName\": \"Jane\", \"citizenship\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"fcaEmployee\": false, \"birthCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"birthProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"incomeDocumentInfo\": {\"taxYear\": \"2021\", \"companyInfo\": {\"companyCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"companyName\": \"XYZ Corp\", \"companyAddress\": \"321 Maple St\", \"companyCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true, \"codeIso3\": \"USA\"}, \"companyZipCode\": \"10003\", \"companyProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"incomeDocType\": \"W2\", \"jobOccupation\": {\"code\": \"E\", \"label\": \"Employee\", \"value\": true, \"grossAnnualIncomeRequired\": false}, \"netMonthlyIncome\": 4000.0, \"workingStartDate\": \"2015-06-01\", \"taxWitholdingCode\": \"FED\", \"taxWitholdingFlag\": true, \"countryOfEmployment\": \"US\", \"inpsRegistrationFlag\": true}}'),('JD1023','COMPLETED','alessandro','dilevrano','alessandro','test@example.com','{\"job\": \"Software Developer\", \"gender\": \"male\", \"province\": \"Taranto\", \"dateOfBirth\": \"1993-01-15\"}','{\"loanTerm\": 36.0, \"vehicleId\": \"F123\", \"loanAmount\": 15000.0, \"financingId\": \"F001\"}','{\"year\": 2021, \"brand\": \"Ford\", \"model\": \"Fusion\", \"vehicleId\": \"F123\"}','card','{\"Code\": \"123\", \"Owner\": \"John Doe\", \"CardNumber\": \"1234567890123456\", \"expireDate\": \"2025-01-15\"}','{\"documentId\": \"CA88078DH\", \"expireDate\": \"2029-01-15\", \"documentType\": \"DNI\"}',NULL,'{\"step\": \"completed\", \"addressInfo\": {\"resTown\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"resAddress\": \"789 Oak St\", \"resCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"resZipCode\": \"10002\", \"resProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}, \"isDomEqualsRes\": true}, \"accountCheck\": true, \"documentInfo\": {\"idDocumentType\": \"DriverLicense\", \"idDocumentNumber\": \"123456789\", \"idDocumentExpireDate\": \"2025-06-01\", \"healthInsuranceNumber\": \"123456789\", \"idDocumentReleaseCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"idDocumentReleaseDate\": \"2015-06-01\", \"idDocumentReleaseEntity\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"idDocumentReleaseProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"householdInfo\": {\"familyMembers\": \"1\", \"houseProperty\": {\"code\": \"R\", \"label\": \"Rented\", \"value\": true}, \"maritalStatus\": {\"code\": \"S\", \"label\": \"Single\", \"value\": true}, \"workingMembers\": \"1\", \"workStartingDate\": \"2012-06-01\"}, \"personalDetails\": {\"pep\": false, \"email\": \"jane.doe@example.com\", \"phone\": \"+1 917 555 1234\", \"gender\": \"F\", \"surname\": \"Doe\", \"taxCode\": \"98765432109\", \"birthCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"birthDate\": \"1990-05-15\", \"firstName\": \"Jane\", \"citizenship\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"fcaEmployee\": false, \"birthCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true}, \"birthProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"incomeDocumentInfo\": {\"taxYear\": \"2021\", \"companyInfo\": {\"companyCity\": {\"code\": \"NYC\", \"label\": \"New York City\", \"value\": true, \"belfioreCode\": \"36061\", \"provinceCode\": \"NY\"}, \"companyName\": \"XYZ Corp\", \"companyAddress\": \"321 Maple St\", \"companyCountry\": {\"code\": \"US\", \"label\": \"United States\", \"value\": true, \"codeIso3\": \"USA\"}, \"companyZipCode\": \"10003\", \"companyProvince\": {\"code\": \"NY\", \"label\": \"New York\", \"value\": true, \"countryCode\": \"US\"}}, \"incomeDocType\": \"W2\", \"jobOccupation\": {\"code\": \"E\", \"label\": \"Employee\", \"value\": true, \"grossAnnualIncomeRequired\": false}, \"netMonthlyIncome\": 4000.0, \"workingStartDate\": \"2015-06-01\", \"taxWitholdingCode\": \"FED\", \"taxWitholdingFlag\": true, \"countryOfEmployment\": \"US\", \"inpsRegistrationFlag\": true}}');
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

-- Dump completed on 2024-06-16 11:02:04
