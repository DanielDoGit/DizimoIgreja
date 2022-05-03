-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: gestaoigreja
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `cidadeid` int NOT NULL,
  `cidadenome` varchar(255) DEFAULT NULL,
  `cidadeuf` char(2) DEFAULT NULL,
  PRIMARY KEY (`cidadeid`),
  UNIQUE KEY `cidadenome_unique` (`cidadenome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dizimista`
--

DROP TABLE IF EXISTS `dizimista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dizimista` (
  `dizimistaid` int NOT NULL,
  `dizimistaidcidade` int DEFAULT NULL,
  `dizimistaNome` varchar(255) DEFAULT NULL,
  `dizimistacpf` int DEFAULT NULL,
  `dizimistadatanascimento` date DEFAULT NULL,
  `dizimistacelular` int DEFAULT NULL,
  `dizimistatelefone` int DEFAULT NULL,
  `dizimistaendereco` varchar(255) DEFAULT NULL,
  `dizimistanumeroEndereco` int DEFAULT NULL,
  `dizimistaobservacoes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dizimistaid`),
  UNIQUE KEY `dizimistaNome_dizimistaCpf_unique` (`dizimistaNome`,`dizimistacpf`),
  KEY `IdCidade_fk` (`dizimistaidcidade`),
  CONSTRAINT `IdCidade_fk` FOREIGN KEY (`dizimistaidcidade`) REFERENCES `cidade` (`cidadeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dizimista`
--

LOCK TABLES `dizimista` WRITE;
/*!40000 ALTER TABLE `dizimista` DISABLE KEYS */;
/*!40000 ALTER TABLE `dizimista` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-02 17:08:19
