CREATE DATABASE  IF NOT EXISTS `gestaoigreja` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestaoigreja`;
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
-- Table structure for table `Caixa`
--

DROP TABLE IF EXISTS `Caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Caixa` (
  `caixaid` int unsigned NOT NULL AUTO_INCREMENT,
  `RecebimentoMissa_recebMissaId` int unsigned NOT NULL,
  `ContasAPagar_contPagId` int unsigned NOT NULL,
  `RecebimentoDizimo_recDizimoId` int unsigned NOT NULL,
  `caixaDescricaoorigemPagamento` varchar(255) DEFAULT NULL,
  `caixavalor` double DEFAULT NULL,
  `caixadatarecebimento` datetime DEFAULT NULL,
  PRIMARY KEY (`caixaid`),
  KEY `Caixa_FKIndex1` (`RecebimentoDizimo_recDizimoId`),
  KEY `Caixa_FKIndex2` (`ContasAPagar_contPagId`),
  KEY `Caixa_FKIndex3` (`RecebimentoMissa_recebMissaId`),
  CONSTRAINT `Caixa_ibfk_1` FOREIGN KEY (`RecebimentoDizimo_recDizimoId`) REFERENCES `RecebimentoDizimo` (`recDizimoId`),
  CONSTRAINT `Caixa_ibfk_2` FOREIGN KEY (`ContasAPagar_contPagId`) REFERENCES `ContasAPagar` (`contPagId`),
  CONSTRAINT `Caixa_ibfk_3` FOREIGN KEY (`RecebimentoMissa_recebMissaId`) REFERENCES `RecebimentoMissa` (`recebMissaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Caixa`
--

LOCK TABLES `Caixa` WRITE;
/*!40000 ALTER TABLE `Caixa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoriaFuncionario`
--

DROP TABLE IF EXISTS `CategoriaFuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategoriaFuncionario` (
  `catFuncId` int unsigned NOT NULL AUTO_INCREMENT,
  `catFuncNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`catFuncId`),
  UNIQUE KEY `nomeCatFunc_UN` (`catFuncNome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoriaFuncionario`
--

LOCK TABLES `CategoriaFuncionario` WRITE;
/*!40000 ALTER TABLE `CategoriaFuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `CategoriaFuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cidade`
--

DROP TABLE IF EXISTS `Cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cidade` (
  `cidadeId` int unsigned NOT NULL AUTO_INCREMENT,
  `cidadeNomeCidade` varchar(255) DEFAULT NULL,
  `cidadeUf` char(2) DEFAULT NULL,
  PRIMARY KEY (`cidadeId`),
  UNIQUE KEY `cidadeNomeCidade_UN` (`cidadeNomeCidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cidade`
--

LOCK TABLES `Cidade` WRITE;
/*!40000 ALTER TABLE `Cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Coletor`
--

DROP TABLE IF EXISTS `Coletor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coletor` (
  `colId` int unsigned NOT NULL,
  `Comunidade_comuId` int unsigned NOT NULL,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `Coletor_colId` int unsigned NOT NULL,
  `colCpf` int unsigned NOT NULL,
  `colNome` varchar(255) DEFAULT NULL,
  `colDataNascimento` date DEFAULT NULL,
  `colCelular` int unsigned DEFAULT NULL,
  `colTelefone` int unsigned DEFAULT NULL,
  `colEndereco` varchar(255) DEFAULT NULL,
  `colNEndereco` int unsigned DEFAULT NULL,
  `colObservacoes` varchar(255) DEFAULT NULL,
  `colRg` int unsigned DEFAULT NULL,
  PRIMARY KEY (`colId`),
  UNIQUE KEY `coletor_UN` (`colCpf`,`colNome`,`colRg`),
  KEY `idCidade_FK_coletor` (`Cidade_cidadeId`),
  KEY `Comunidade_FK_coletor` (`Comunidade_comuId`),
  KEY `Coletor_colId` (`Coletor_colId`),
  CONSTRAINT `Coletor_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `Cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Coletor_ibfk_2` FOREIGN KEY (`Coletor_colId`) REFERENCES `Coletor` (`colId`),
  CONSTRAINT `Coletor_ibfk_3` FOREIGN KEY (`Comunidade_comuId`) REFERENCES `Comunidade` (`comuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coletor`
--

LOCK TABLES `Coletor` WRITE;
/*!40000 ALTER TABLE `Coletor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Coletor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comunidade`
--

DROP TABLE IF EXISTS `Comunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comunidade` (
  `comuId` int unsigned NOT NULL AUTO_INCREMENT,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `comuFantasia` varchar(255) DEFAULT NULL,
  `comuRazaoSocial` varchar(255) DEFAULT NULL,
  `comuCnpj` int unsigned DEFAULT NULL,
  `comuEndereco` varchar(255) DEFAULT NULL,
  `comuNumeroEndereco` varchar(255) DEFAULT NULL,
  `comuBairro` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`comuId`),
  UNIQUE KEY `Comunidade_Unique` (`comuFantasia`,`comuRazaoSocial`,`comuCnpj`),
  KEY `Comunidade_FK_cidade` (`Cidade_cidadeId`),
  CONSTRAINT `Comunidade_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `Cidade` (`cidadeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comunidade`
--

LOCK TABLES `Comunidade` WRITE;
/*!40000 ALTER TABLE `Comunidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContasAPagar`
--

DROP TABLE IF EXISTS `ContasAPagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ContasAPagar` (
  `contPagId` int unsigned NOT NULL AUTO_INCREMENT,
  `Fornecedor_forId` int unsigned NOT NULL,
  `contPagDescricao` varchar(255) DEFAULT NULL,
  `contPagFormatoPagamento` varchar(255) DEFAULT NULL,
  `contPagDataPagamento` datetime DEFAULT NULL,
  PRIMARY KEY (`contPagId`),
  KEY `ContasAPagar_FK` (`Fornecedor_forId`),
  CONSTRAINT `ContasAPagar_ibfk_1` FOREIGN KEY (`Fornecedor_forId`) REFERENCES `Fornecedor` (`forId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContasAPagar`
--

LOCK TABLES `ContasAPagar` WRITE;
/*!40000 ALTER TABLE `ContasAPagar` DISABLE KEYS */;
/*!40000 ALTER TABLE `ContasAPagar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dizimista`
--

DROP TABLE IF EXISTS `Dizimista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dizimista` (
  `dizId` int unsigned NOT NULL AUTO_INCREMENT,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `Coletor_colId` int unsigned NOT NULL,
  `dizCpf` int unsigned NOT NULL,
  `dizNome` varchar(255) DEFAULT NULL,
  `dizDataNascimento` date DEFAULT NULL,
  `dizCelular` int unsigned DEFAULT NULL,
  `dizTelefone` int unsigned DEFAULT NULL,
  `dizEndereco` varchar(255) DEFAULT NULL,
  `dizNEndereco` int unsigned DEFAULT NULL,
  `dizObservacoes` varchar(255) DEFAULT NULL,
  `dizDataCadastro` date DEFAULT NULL,
  PRIMARY KEY (`dizId`),
  UNIQUE KEY `NomeCpf_UN` (`dizNome`,`dizCpf`,`dizDataCadastro`),
  KEY `idCidade_FK` (`Cidade_cidadeId`),
  KEY `Dizimista_FKIndex2` (`Coletor_colId`),
  CONSTRAINT `Dizimista_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `Cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Dizimista_ibfk_2` FOREIGN KEY (`Coletor_colId`) REFERENCES `Coletor` (`colId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dizimista`
--

LOCK TABLES `Dizimista` WRITE;
/*!40000 ALTER TABLE `Dizimista` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dizimista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fornecedor`
--

DROP TABLE IF EXISTS `Fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Fornecedor` (
  `forId` int unsigned NOT NULL AUTO_INCREMENT,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `forCnpj` int unsigned NOT NULL,
  `forNome` varchar(255) DEFAULT NULL,
  `forCelular` int unsigned DEFAULT NULL,
  `forTelefone` int unsigned DEFAULT NULL,
  `forRazaoSocial` varchar(255) DEFAULT NULL,
  `forEndereco` varchar(255) DEFAULT NULL,
  `forNEndereco` int unsigned DEFAULT NULL,
  PRIMARY KEY (`forId`),
  UNIQUE KEY `Nome_Razao_UN` (`forNome`,`forRazaoSocial`,`forCnpj`),
  KEY `idCidade_Fornecedor_FK` (`Cidade_cidadeId`),
  CONSTRAINT `Fornecedor_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `Cidade` (`cidadeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fornecedor`
--

LOCK TABLES `Fornecedor` WRITE;
/*!40000 ALTER TABLE `Fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Funcionario`
--

DROP TABLE IF EXISTS `Funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Funcionario` (
  `funcId` int unsigned NOT NULL,
  `CategoriaFuncionario_catFuncId` int unsigned NOT NULL,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `funcRg` int unsigned DEFAULT NULL,
  `funcCpf` int unsigned NOT NULL,
  `funcNome` varchar(255) DEFAULT NULL,
  `funcDataNascimento` date DEFAULT NULL,
  `funcCelular` int unsigned DEFAULT NULL,
  `funcTelefone` int unsigned DEFAULT NULL,
  `funcEndereco` varchar(255) DEFAULT NULL,
  `funcNumeroEndereco` int unsigned DEFAULT NULL,
  `funcObservacoes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`funcId`),
  UNIQUE KEY `cpfRgFuncionario_UN` (`funcCpf`,`funcRg`,`funcNome`),
  KEY `idCidade_FK` (`Cidade_cidadeId`),
  KEY `IdCategoraFuncionario_FK` (`CategoriaFuncionario_catFuncId`),
  CONSTRAINT `Funcionario_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `Cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Funcionario_ibfk_2` FOREIGN KEY (`CategoriaFuncionario_catFuncId`) REFERENCES `CategoriaFuncionario` (`catFuncId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Funcionario`
--

LOCK TABLES `Funcionario` WRITE;
/*!40000 ALTER TABLE `Funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Missa`
--

DROP TABLE IF EXISTS `Missa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Missa` (
  `missaId` int unsigned NOT NULL AUTO_INCREMENT,
  `Funcionario_funcId` int unsigned NOT NULL,
  `Comunidade_comuId` int unsigned NOT NULL,
  `missaDataHorario` datetime DEFAULT NULL,
  PRIMARY KEY (`missaId`),
  KEY `Missa_FKIndex1` (`Comunidade_comuId`),
  KEY `Missa_FKIndex2` (`Funcionario_funcId`),
  CONSTRAINT `Missa_ibfk_1` FOREIGN KEY (`Comunidade_comuId`) REFERENCES `Comunidade` (`comuId`),
  CONSTRAINT `Missa_ibfk_2` FOREIGN KEY (`Funcionario_funcId`) REFERENCES `Funcionario` (`funcId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Missa`
--

LOCK TABLES `Missa` WRITE;
/*!40000 ALTER TABLE `Missa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Missa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermissaoFuncionario`
--

DROP TABLE IF EXISTS `PermissaoFuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PermissaoFuncionario` (
  `Permissoes_permiId` int unsigned NOT NULL,
  `Funcionario_funcId` int unsigned NOT NULL,
  PRIMARY KEY (`Permissoes_permiId`,`Funcionario_funcId`),
  KEY `Funcionario_has_Permissoes_FKIndex1` (`Funcionario_funcId`),
  KEY `Funcionario_has_Permissoes_FKIndex2` (`Permissoes_permiId`),
  CONSTRAINT `PermissaoFuncionario_ibfk_1` FOREIGN KEY (`Funcionario_funcId`) REFERENCES `Funcionario` (`funcId`),
  CONSTRAINT `PermissaoFuncionario_ibfk_2` FOREIGN KEY (`Permissoes_permiId`) REFERENCES `Permissoes` (`permiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissaoFuncionario`
--

LOCK TABLES `PermissaoFuncionario` WRITE;
/*!40000 ALTER TABLE `PermissaoFuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `PermissaoFuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permissoes`
--

DROP TABLE IF EXISTS `Permissoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Permissoes` (
  `permiId` int unsigned NOT NULL AUTO_INCREMENT,
  `permiNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permiId`),
  UNIQUE KEY `PermissoesNome_UN` (`permiNome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permissoes`
--

LOCK TABLES `Permissoes` WRITE;
/*!40000 ALTER TABLE `Permissoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `Permissoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecebimentoDizimo`
--

DROP TABLE IF EXISTS `RecebimentoDizimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RecebimentoDizimo` (
  `recDizimoId` int unsigned NOT NULL AUTO_INCREMENT,
  `Dizimista_dizId` int unsigned NOT NULL,
  `recDizimoValorRecebimento` double DEFAULT NULL,
  `recDizimoDataRecebimento` datetime DEFAULT NULL,
  PRIMARY KEY (`recDizimoId`),
  KEY `dataRecebimento_UN` (`recDizimoDataRecebimento`),
  KEY `RecebimentoDizimo_FK` (`Dizimista_dizId`),
  CONSTRAINT `RecebimentoDizimo_ibfk_1` FOREIGN KEY (`Dizimista_dizId`) REFERENCES `Dizimista` (`dizId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecebimentoDizimo`
--

LOCK TABLES `RecebimentoDizimo` WRITE;
/*!40000 ALTER TABLE `RecebimentoDizimo` DISABLE KEYS */;
/*!40000 ALTER TABLE `RecebimentoDizimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecebimentoMissa`
--

DROP TABLE IF EXISTS `RecebimentoMissa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RecebimentoMissa` (
  `recebMissaId` int unsigned NOT NULL AUTO_INCREMENT,
  `Missa_missaId` int unsigned NOT NULL,
  `recebMissaValorRecebido` float DEFAULT NULL,
  `recebMissaDataRecebimento` date DEFAULT NULL,
  PRIMARY KEY (`recebMissaId`),
  KEY `RecebimentoMissa_FKIndex1` (`Missa_missaId`),
  CONSTRAINT `RecebimentoMissa_ibfk_1` FOREIGN KEY (`Missa_missaId`) REFERENCES `Missa` (`missaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecebimentoMissa`
--

LOCK TABLES `RecebimentoMissa` WRITE;
/*!40000 ALTER TABLE `RecebimentoMissa` DISABLE KEYS */;
/*!40000 ALTER TABLE `RecebimentoMissa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-04 17:03:34
