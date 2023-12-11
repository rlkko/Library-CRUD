-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `number` int NOT NULL AUTO_INCREMENT,
  `clientFirstName` varchar(30) DEFAULT NULL,
  `clientLastName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'reiciendis','qui'),(2,'blanditiis','natus'),(3,'molestiae','doloribus'),(4,'non','accusantium'),(5,'natus','aspernatur'),(6,'repellendus','nulla'),(7,'odio','nisi'),(8,'hic','corporis'),(9,'ab','quasi'),(10,'ducimus','vitae'),(11,'blanditiis','non'),(12,'consequatur','eos'),(13,'consectetur','consequatur'),(14,'voluptas','aut'),(15,'et','sed'),(16,'ut','enim'),(17,'mollitia','ex'),(18,'aliquam','corrupti'),(19,'consequuntur','consequatur'),(20,'autem','earum'),(21,'atque','exercitationem'),(22,'illo','saepe'),(23,'veniam','debitis'),(24,'molestiae','velit'),(25,'id','voluptas'),(26,'omnis','quam'),(27,'aut','dicta'),(28,'delectus','facilis'),(29,'dolores','aut'),(30,'tempora','sit'),(31,'odio','libero'),(32,'ratione','reprehenderit'),(33,'exercitationem','illum'),(34,'eveniet','alias'),(35,'quia','dolor'),(36,'enim','animi'),(37,'sed','alias'),(38,'dolores','magni'),(39,'at','exercitationem'),(40,'sint','ut');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-11 17:02:16
