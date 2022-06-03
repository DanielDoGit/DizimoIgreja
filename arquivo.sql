-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: gestaoigreja
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caixa` (
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
  CONSTRAINT `Caixa_ibfk_1` FOREIGN KEY (`RecebimentoDizimo_recDizimoId`) REFERENCES `recebimentodizimo` (`recDizimoId`),
  CONSTRAINT `Caixa_ibfk_2` FOREIGN KEY (`ContasAPagar_contPagId`) REFERENCES `contasapagar` (`contPagId`),
  CONSTRAINT `Caixa_ibfk_3` FOREIGN KEY (`RecebimentoMissa_recebMissaId`) REFERENCES `recebimentomissa` (`recebMissaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriafuncionario`
--

DROP TABLE IF EXISTS `categoriafuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoriafuncionario` (
  `catFuncId` int unsigned NOT NULL AUTO_INCREMENT,
  `catFuncNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`catFuncId`),
  UNIQUE KEY `nomeCatFunc_UN` (`catFuncNome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriafuncionario`
--

LOCK TABLES `categoriafuncionario` WRITE;
/*!40000 ALTER TABLE `categoriafuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoriafuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `cidadeId` int unsigned NOT NULL AUTO_INCREMENT,
  `cidadeNomeCidade` varchar(255) DEFAULT NULL,
  `cidadeUf` char(2) DEFAULT NULL,
  PRIMARY KEY (`cidadeId`),
  UNIQUE KEY `cidadeNomeCidade_UN` (`cidadeNomeCidade`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Cândido Mota','SP');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coletor`
--

DROP TABLE IF EXISTS `coletor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coletor` (
  `colId` int unsigned NOT NULL,
  `Comunidade_comuId` int unsigned NOT NULL,
  `Cidade_cidadeId` int unsigned NOT NULL,
  `colCpf` int unsigned NOT NULL,
  `colNome` varchar(255) DEFAULT NULL,
  `colDataNascimento` date DEFAULT NULL,
  `colCelular` int unsigned DEFAULT NULL,
  `colTelefone` int unsigned DEFAULT NULL,
  `colEndereco` varchar(255) DEFAULT NULL,
  `colNEndereco` int unsigned DEFAULT NULL,
  `colObservacoes` varchar(255) DEFAULT NULL,
  `colRg` int unsigned DEFAULT NULL,
  `colLogin` varchar(255) DEFAULT NULL,
  `colSenha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`colId`),
  KEY `idCidade_FK_coletor` (`Cidade_cidadeId`),
  KEY `Comunidade_FK_coletor` (`Comunidade_comuId`),
  CONSTRAINT `Coletor_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Coletor_ibfk_3` FOREIGN KEY (`Comunidade_comuId`) REFERENCES `comunidade` (`comuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coletor`
--

LOCK TABLES `coletor` WRITE;
/*!40000 ALTER TABLE `coletor` DISABLE KEYS */;
INSERT INTO `coletor` VALUES (1,1,1,11111,'Daniel','2002-07-10',997565,1833415655,'Rua adalberto sorares Filho',23,NULL,88888888,'master','master');
/*!40000 ALTER TABLE `coletor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunidade`
--

DROP TABLE IF EXISTS `comunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comunidade` (
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
  CONSTRAINT `Comunidade_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `cidade` (`cidadeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunidade`
--

LOCK TABLES `comunidade` WRITE;
/*!40000 ALTER TABLE `comunidade` DISABLE KEYS */;
INSERT INTO `comunidade` VALUES (1,1,'Santa Clara','Mitra Diocesana',23586999,'Rua adalfredo Barreto','25','Jardim Paraíso');
/*!40000 ALTER TABLE `comunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contasapagar`
--

DROP TABLE IF EXISTS `contasapagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contasapagar` (
  `contPagId` int unsigned NOT NULL AUTO_INCREMENT,
  `Fornecedor_forId` int unsigned NOT NULL,
  `contPagDescricao` varchar(255) DEFAULT NULL,
  `contPagFormatoPagamento` varchar(255) DEFAULT NULL,
  `contPagDataPagamento` datetime DEFAULT NULL,
  PRIMARY KEY (`contPagId`),
  KEY `ContasAPagar_FK` (`Fornecedor_forId`),
  CONSTRAINT `ContasAPagar_ibfk_1` FOREIGN KEY (`Fornecedor_forId`) REFERENCES `fornecedor` (`forId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contasapagar`
--

LOCK TABLES `contasapagar` WRITE;
/*!40000 ALTER TABLE `contasapagar` DISABLE KEYS */;
/*!40000 ALTER TABLE `contasapagar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dizimista`
--

DROP TABLE IF EXISTS `dizimista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dizimista` (
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
  CONSTRAINT `Dizimista_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Dizimista_ibfk_2` FOREIGN KEY (`Coletor_colId`) REFERENCES `coletor` (`colId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dizimista`
--

LOCK TABLES `dizimista` WRITE;
/*!40000 ALTER TABLE `dizimista` DISABLE KEYS */;
/*!40000 ALTER TABLE `dizimista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
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
  CONSTRAINT `Fornecedor_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `cidade` (`cidadeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
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
  CONSTRAINT `Funcionario_ibfk_1` FOREIGN KEY (`Cidade_cidadeId`) REFERENCES `cidade` (`cidadeId`) ON DELETE CASCADE,
  CONSTRAINT `Funcionario_ibfk_2` FOREIGN KEY (`CategoriaFuncionario_catFuncId`) REFERENCES `categoriafuncionario` (`catFuncId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missa`
--

DROP TABLE IF EXISTS `missa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missa` (
  `missaId` int unsigned NOT NULL AUTO_INCREMENT,
  `Funcionario_funcId` int unsigned NOT NULL,
  `Comunidade_comuId` int unsigned NOT NULL,
  `missaDataHorario` datetime DEFAULT NULL,
  PRIMARY KEY (`missaId`),
  KEY `Missa_FKIndex1` (`Comunidade_comuId`),
  KEY `Missa_FKIndex2` (`Funcionario_funcId`),
  CONSTRAINT `Missa_ibfk_1` FOREIGN KEY (`Comunidade_comuId`) REFERENCES `comunidade` (`comuId`),
  CONSTRAINT `Missa_ibfk_2` FOREIGN KEY (`Funcionario_funcId`) REFERENCES `funcionario` (`funcId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missa`
--

LOCK TABLES `missa` WRITE;
/*!40000 ALTER TABLE `missa` DISABLE KEYS */;
/*!40000 ALTER TABLE `missa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissaofuncionario`
--

DROP TABLE IF EXISTS `permissaofuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissaofuncionario` (
  `Permissoes_permiId` int unsigned NOT NULL,
  `Funcionario_funcId` int unsigned NOT NULL,
  PRIMARY KEY (`Permissoes_permiId`,`Funcionario_funcId`),
  KEY `Funcionario_has_Permissoes_FKIndex1` (`Funcionario_funcId`),
  KEY `Funcionario_has_Permissoes_FKIndex2` (`Permissoes_permiId`),
  CONSTRAINT `PermissaoFuncionario_ibfk_1` FOREIGN KEY (`Funcionario_funcId`) REFERENCES `funcionario` (`funcId`),
  CONSTRAINT `PermissaoFuncionario_ibfk_2` FOREIGN KEY (`Permissoes_permiId`) REFERENCES `permissoes` (`permiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissaofuncionario`
--

LOCK TABLES `permissaofuncionario` WRITE;
/*!40000 ALTER TABLE `permissaofuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissaofuncionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissoes`
--

DROP TABLE IF EXISTS `permissoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissoes` (
  `permiId` int unsigned NOT NULL AUTO_INCREMENT,
  `permiNome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permiId`),
  UNIQUE KEY `PermissoesNome_UN` (`permiNome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissoes`
--

LOCK TABLES `permissoes` WRITE;
/*!40000 ALTER TABLE `permissoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recebimentodizimo`
--

DROP TABLE IF EXISTS `recebimentodizimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recebimentodizimo` (
  `recDizimoId` int unsigned NOT NULL AUTO_INCREMENT,
  `Dizimista_dizId` int unsigned NOT NULL,
  `recDizimoValorRecebimento` double DEFAULT NULL,
  `recDizimoDataRecebimento` datetime DEFAULT NULL,
  PRIMARY KEY (`recDizimoId`),
  KEY `dataRecebimento_UN` (`recDizimoDataRecebimento`),
  KEY `RecebimentoDizimo_FK` (`Dizimista_dizId`),
  CONSTRAINT `RecebimentoDizimo_ibfk_1` FOREIGN KEY (`Dizimista_dizId`) REFERENCES `dizimista` (`dizId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recebimentodizimo`
--

LOCK TABLES `recebimentodizimo` WRITE;
/*!40000 ALTER TABLE `recebimentodizimo` DISABLE KEYS */;
/*!40000 ALTER TABLE `recebimentodizimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recebimentomissa`
--

DROP TABLE IF EXISTS `recebimentomissa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recebimentomissa` (
  `recebMissaId` int unsigned NOT NULL AUTO_INCREMENT,
  `Missa_missaId` int unsigned NOT NULL,
  `recebMissaValorRecebido` float DEFAULT NULL,
  `recebMissaDataRecebimento` date DEFAULT NULL,
  PRIMARY KEY (`recebMissaId`),
  KEY `RecebimentoMissa_FKIndex1` (`Missa_missaId`),
  CONSTRAINT `RecebimentoMissa_ibfk_1` FOREIGN KEY (`Missa_missaId`) REFERENCES `missa` (`missaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recebimentomissa`
--

LOCK TABLES `recebimentomissa` WRITE;
/*!40000 ALTER TABLE `recebimentomissa` DISABLE KEYS */;
/*!40000 ALTER TABLE `recebimentomissa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 15:22:19
