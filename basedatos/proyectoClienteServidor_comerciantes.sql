-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: localhost    Database: proyectoClienteServidor
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `comerciantes`
--

DROP TABLE IF EXISTS `comerciantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comerciantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `correoElectronico` varchar(50) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `nombreEmpresa` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `direccionComercio` varchar(50) NOT NULL,
  `NumeroContacto` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comerciantes`
--

LOCK TABLES `comerciantes` WRITE;
/*!40000 ALTER TABLE `comerciantes` DISABLE KEYS */;
INSERT INTO `comerciantes` VALUES (1,'kevin@mail.com','nuevaPass','Case Lab','Accesorios para celular','Porvenir, Desamparados','85471496'),(3,'kevin3@mail.com','contra1234','Case Lab','Accesorios para celular','Porvenir, Desamparados','85471488'),(5,'sadsa','sad','facebook','sdas','da','dsa'),(6,'kevinaraya87@gmail.com','pass1234','caselab','accesorios de celular','porvenir','85471488'),(7,'pas@gmail.com','pass12e1a','amazon','productos electronicos','estados unidos','87123213'),(8,'asdda','dsadas','dsadas','adsdsa','asd','sda'),(9,'kevovn','passcode','fidelitas','universidad','san pedro','22222222'),(10,'sdadsadsa','sddsas','dsasad','sadds','dsads','sadds'),(11,'sadsa','qweqw','dwqd','zxas','wqe','213213'),(12,'','','','','',''),(13,'asdas','sad','das','asd','ad','ad'),(14,'xssxsa','wqeqw','das','xza','xwqsw','das'),(15,'saddsa','dssda','sadds','sadsda','dsadsa','2132'),(16,'assda','dsaas','qweqw','ewqsad','das','asd'),(17,'assda','dsaas','qweqw','ewqsad','das','asd'),(18,'dasdsa','xqwdw','dasdq','dqwdq','dqwdqw','dqwd'),(19,'sadds','sads','asddssda','sdsdasad','sdasad','213213');
/*!40000 ALTER TABLE `comerciantes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-26 23:40:19
