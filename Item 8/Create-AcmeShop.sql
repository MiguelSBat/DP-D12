-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: AcmeShop
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hardBan` bit(1) DEFAULT NULL,
  `softBan` bit(1) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_folder`
--

DROP TABLE IF EXISTS `actor_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_folder` (
  `Actor_id` int(11) NOT NULL,
  `folders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dp573h40udupcm5m1kgn2bc2r` (`folders_id`),
  CONSTRAINT `FK_dp573h40udupcm5m1kgn2bc2r` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_folder`
--

LOCK TABLES `actor_folder` WRITE;
/*!40000 ALTER TABLE `actor_folder` DISABLE KEYS */;
INSERT INTO `actor_folder` VALUES (1224,1231),(1224,1232),(1224,1233),(1224,1234),(1226,1235),(1226,1236),(1226,1237),(1226,1238),(1227,1239),(1227,1240),(1227,1241),(1227,1242),(1228,1243),(1228,1244),(1228,1245),(1228,1246),(1225,1247),(1225,1248),(1225,1249),(1225,1250),(1229,1251),(1229,1252),(1229,1253),(1229,1254),(1230,1255),(1230,1256),(1230,1257),(1230,1258);
/*!40000 ALTER TABLE `actor_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_report`
--

DROP TABLE IF EXISTS `actor_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_report` (
  `Actor_id` int(11) NOT NULL,
  `reports_id` int(11) NOT NULL,
  UNIQUE KEY `UK_1596f4s4xlfusbutnf9sw5vga` (`reports_id`),
  CONSTRAINT `FK_1596f4s4xlfusbutnf9sw5vga` FOREIGN KEY (`reports_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_report`
--

LOCK TABLES `actor_report` WRITE;
/*!40000 ALTER TABLE `actor_report` DISABLE KEYS */;
INSERT INTO `actor_report` VALUES (1226,1262);
/*!40000 ALTER TABLE `actor_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_valoration`
--

DROP TABLE IF EXISTS `actor_valoration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_valoration` (
  `Actor_id` int(11) NOT NULL,
  `valorations_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3u7oquu4ivqe6hi85uh28hx0` (`valorations_id`),
  CONSTRAINT `FK_3u7oquu4ivqe6hi85uh28hx0` FOREIGN KEY (`valorations_id`) REFERENCES `valoration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_valoration`
--

LOCK TABLES `actor_valoration` WRITE;
/*!40000 ALTER TABLE `actor_valoration` DISABLE KEYS */;
INSERT INTO `actor_valoration` VALUES (1227,1265),(1229,1266),(1226,1267),(1226,1268),(1229,1269);
/*!40000 ALTER TABLE `actor_valoration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hardBan` bit(1) DEFAULT NULL,
  `softBan` bit(1) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1224,0,'ponsavi@acme.org','\0','\0',1216);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ne2sdvjpax4rsbfim7wlb9u21` (`endDate`),
  KEY `FK_syuvmapvxdmbobyaqvq8nq9e4` (`business_id`),
  KEY `FK_6o64o0f1bbnakn5y1x0s2ypq6` (`item_id`),
  CONSTRAINT `FK_6o64o0f1bbnakn5y1x0s2ypq6` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_syuvmapvxdmbobyaqvq8nq9e4` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement_tags`
--

DROP TABLE IF EXISTS `advertisement_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement_tags` (
  `Advertisement_id` int(11) NOT NULL,
  `tags` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement_tags`
--

LOCK TABLES `advertisement_tags` WRITE;
/*!40000 ALTER TABLE `advertisement_tags` DISABLE KEYS */;
INSERT INTO `advertisement_tags` VALUES (1288,'Consola'),(1289,'Video Juego'),(1290,'Consola'),(1291,'Amibo'),(1292,'Consola'),(1293,'Consola'),(1294,'Consola');
/*!40000 ALTER TABLE `advertisement_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_10g8xii7lw9kq0kcsobgmtw72` (`question_id`),
  CONSTRAINT `FK_10g8xii7lw9kq0kcsobgmtw72` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1301,0,'2018-01-15 00:00:00','No viene en ese color',1300);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctionadvertisement`
--

DROP TABLE IF EXISTS `auctionadvertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctionadvertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `instantBuyPrice` double DEFAULT NULL,
  `secret` bit(1) NOT NULL,
  `startingPrice` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AuctionAdvertisementUK_ne2sdvjpax4rsbfim7wlb9u21` (`endDate`),
  KEY `FK_pj1sm4vn4ifkh5mwjaqrojhqh` (`user_id`),
  KEY `FK_60smfuan6bvfe9ywc7wi6r7n5` (`business_id`),
  KEY `FK_3i62m9ifjeryrd2xa9xu38a29` (`item_id`),
  CONSTRAINT `FK_3i62m9ifjeryrd2xa9xu38a29` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_60smfuan6bvfe9ywc7wi6r7n5` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`),
  CONSTRAINT `FK_pj1sm4vn4ifkh5mwjaqrojhqh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctionadvertisement`
--

LOCK TABLES `auctionadvertisement` WRITE;
/*!40000 ALTER TABLE `auctionadvertisement` DISABLE KEYS */;
INSERT INTO `auctionadvertisement` VALUES (1292,0,'2019-01-15 00:00:00','2018-01-15 00:00:00',NULL,1274,520.5,'\0',220.5,1226),(1293,0,'2018-01-15 00:00:00','2017-01-15 00:00:00',NULL,1274,520.5,'\0',220.5,1226),(1294,0,'2019-01-15 00:00:00','2017-01-15 00:00:00',1229,1277,999.99,'',220.5,NULL);
/*!40000 ALTER TABLE `auctionadvertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `auctionAdvertisement_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s3x284uhjjrde3w0asq6mucgc` (`auctionAdvertisement_id`),
  KEY `FK_4rricb8rqjpqekqxntmcl0sny` (`user_id`),
  CONSTRAINT `FK_4rricb8rqjpqekqxntmcl0sny` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_s3x284uhjjrde3w0asq6mucgc` FOREIGN KEY (`auctionAdvertisement_id`) REFERENCES `auctionadvertisement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (1299,0,250.35,1292,1227);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hardBan` bit(1) DEFAULT NULL,
  `softBan` bit(1) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `VATNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `paypalEmail` varchar(255) DEFAULT NULL,
  `premium` bit(1) DEFAULT NULL,
  `reputation` int(11) DEFAULT NULL,
  `suspicious` bit(1) DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5xywdlc3au4r9n02d9plqwxrv` (`userAccount_id`),
  CONSTRAINT `FK_5xywdlc3au4r9n02d9plqwxrv` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (1229,0,'ponsavi@acme.org','\0','\0',1221,'das432-das','business1','ponsavi@paypal.org','',5,'\0',''),(1230,0,'ponsavi@acme.org','\0','\0',1222,'das432-das','business2','ponsavi@acme.org','',5,'','\0');
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_answer`
--

DROP TABLE IF EXISTS `business_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_answer` (
  `Business_id` int(11) NOT NULL,
  `answers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_4gny5hxc0arntva9pwsy3isch` (`answers_id`),
  KEY `FK_8fhhd3n95v1b0o9hfdkohyghm` (`Business_id`),
  CONSTRAINT `FK_8fhhd3n95v1b0o9hfdkohyghm` FOREIGN KEY (`Business_id`) REFERENCES `business` (`id`),
  CONSTRAINT `FK_4gny5hxc0arntva9pwsy3isch` FOREIGN KEY (`answers_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_answer`
--

LOCK TABLES `business_answer` WRITE;
/*!40000 ALTER TABLE `business_answer` DISABLE KEYS */;
INSERT INTO `business_answer` VALUES (1229,1301);
/*!40000 ALTER TABLE `business_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_businessinfo`
--

DROP TABLE IF EXISTS `business_businessinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_businessinfo` (
  `Business_id` int(11) NOT NULL,
  `businessInfos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_e5c5ow3fh7lp4d7kca2tg9usk` (`businessInfos_id`),
  KEY `FK_2k0irlxc2v83gtcnnlayl0cc` (`Business_id`),
  CONSTRAINT `FK_2k0irlxc2v83gtcnnlayl0cc` FOREIGN KEY (`Business_id`) REFERENCES `business` (`id`),
  CONSTRAINT `FK_e5c5ow3fh7lp4d7kca2tg9usk` FOREIGN KEY (`businessInfos_id`) REFERENCES `businessinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_businessinfo`
--

LOCK TABLES `business_businessinfo` WRITE;
/*!40000 ALTER TABLE `business_businessinfo` DISABLE KEYS */;
INSERT INTO `business_businessinfo` VALUES (1229,1272);
/*!40000 ALTER TABLE `business_businessinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_photosurl`
--

DROP TABLE IF EXISTS `business_photosurl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_photosurl` (
  `Business_id` int(11) NOT NULL,
  `photosURL` varchar(255) DEFAULT NULL,
  KEY `FK_d6slgovc8l4mev46bmf2nt9lf` (`Business_id`),
  CONSTRAINT `FK_d6slgovc8l4mev46bmf2nt9lf` FOREIGN KEY (`Business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_photosurl`
--

LOCK TABLES `business_photosurl` WRITE;
/*!40000 ALTER TABLE `business_photosurl` DISABLE KEYS */;
INSERT INTO `business_photosurl` VALUES (1229,'\"https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-perfil-masculino-avatar-5-by-vexels.png\"'),(1230,'\"https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-perfil-masculino-avatar-5-by-vexels.png\"');
/*!40000 ALTER TABLE `business_photosurl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessinfo`
--

DROP TABLE IF EXISTS `businessinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessinfo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessinfo`
--

LOCK TABLES `businessinfo` WRITE;
/*!40000 ALTER TABLE `businessinfo` DISABLE KEYS */;
INSERT INTO `businessinfo` VALUES (1272,0,'Vendemos por dinero muchas Nintendo Switch','adv. Shibuya','Tokyo','Japon');
/*!40000 ALTER TABLE `businessinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `receiver_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1271,0,'2018-01-15 00:00:00','I want sex with you',1226,1228);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `VAT` double DEFAULT NULL,
  `advertisementExpirationMonths` int(11) DEFAULT NULL,
  `businessMaxAdvertisements` int(11) DEFAULT NULL,
  `englishWelcomeMessage` varchar(255) DEFAULT NULL,
  `premiumMaxAdvertisements` int(11) DEFAULT NULL,
  `premiumPrice` double DEFAULT NULL,
  `reportWeightTreshold` int(11) DEFAULT NULL,
  `reputationTreshold` int(11) DEFAULT NULL,
  `spanishWelcomeMessage` varchar(255) DEFAULT NULL,
  `transactionReportWeight` int(11) DEFAULT NULL,
  `userMaxAdvertisements` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1223,0,0.21,2,50,'Buy, sell or auction everything that you can imagine',100,20,5,1,'Compra, vende o subasta todo que puedas imaginar',5,10);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_spamwords`
--

DROP TABLE IF EXISTS `config_spamwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_spamwords` (
  `Config_id` int(11) NOT NULL,
  `spamWords` varchar(255) DEFAULT NULL,
  KEY `FK_iw8kdf34pm7f24c9ctb5shu4d` (`Config_id`),
  CONSTRAINT `FK_iw8kdf34pm7f24c9ctb5shu4d` FOREIGN KEY (`Config_id`) REFERENCES `config` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_spamwords`
--

LOCK TABLES `config_spamwords` WRITE;
/*!40000 ALTER TABLE `config_spamwords` DISABLE KEYS */;
INSERT INTO `config_spamwords` VALUES (1223,'sex'),(1223,'sexo'),(1223,'viagra'),(1223,'cialis');
/*!40000 ALTER TABLE `config_spamwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expressadvertisement`
--

DROP TABLE IF EXISTS `expressadvertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expressadvertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ExpressAdvertisementUK_ne2sdvjpax4rsbfim7wlb9u21` (`endDate`),
  KEY `FK_roj25cela6142yq5j43yhp6y8` (`user_id`),
  KEY `FK_8k71s997jfsjqitssyg1wywfu` (`business_id`),
  KEY `FK_etrmr8hhol16snjfns2s94jfu` (`item_id`),
  CONSTRAINT `FK_etrmr8hhol16snjfns2s94jfu` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_8k71s997jfsjqitssyg1wywfu` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`),
  CONSTRAINT `FK_roj25cela6142yq5j43yhp6y8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expressadvertisement`
--

LOCK TABLES `expressadvertisement` WRITE;
/*!40000 ALTER TABLE `expressadvertisement` DISABLE KEYS */;
INSERT INTO `expressadvertisement` VALUES (1290,0,'2030-01-15 00:00:00','2018-01-15 00:00:00',NULL,1274,320.5,1226),(1291,0,'2030-01-15 00:00:00','2018-01-15 00:00:00',NULL,1276,70.5,1226);
/*!40000 ALTER TABLE `expressadvertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturationdata`
--

DROP TABLE IF EXISTS `facturationdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturationdata` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `IDNumber` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jrmwge4sayesx1homxjk7dicx` (`ticket_id`),
  KEY `FK_q1avbqxms5bdwlkxyq0xy55b3` (`user_id`),
  CONSTRAINT `FK_q1avbqxms5bdwlkxyq0xy55b3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_jrmwge4sayesx1homxjk7dicx` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturationdata`
--

LOCK TABLES `facturationdata` WRITE;
/*!40000 ALTER TABLE `facturationdata` DISABLE KEYS */;
INSERT INTO `facturationdata` VALUES (1286,0,'00000001A','Sevilla','España','Xi','41008','Wang-Fen',1281,1226),(1287,0,'00000002B','Madrid','España','Xi','41008','Wang-Fen',1282,1226);
/*!40000 ALTER TABLE `facturationdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `systemFolders` bit(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e6lcmpm09goh6x4n16fbq5uka` (`parent_id`),
  CONSTRAINT `FK_e6lcmpm09goh6x4n16fbq5uka` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (1231,0,'Root','',NULL),(1232,0,'Inbox','',1231),(1233,0,'Outbox','',1231),(1234,0,'TrashBox','',1231),(1235,0,'Root','',NULL),(1236,0,'Inbox','',1235),(1237,0,'Outbox','',1235),(1238,0,'TrashBox','',1235),(1239,0,'Root','',NULL),(1240,0,'Inbox','',1239),(1241,0,'Outbox','',1239),(1242,0,'TrashBox','',1239),(1243,0,'Root','',NULL),(1244,0,'Inbox','',1243),(1245,0,'Outbox','',1243),(1246,0,'TrashBox','',1243),(1247,0,'Root','',NULL),(1248,0,'Inbox','',1247),(1249,0,'Outbox','',1247),(1250,0,'TrashBox','',1247),(1251,0,'Root','',NULL),(1252,0,'Inbox','',1251),(1253,0,'Outbox','',1251),(1254,0,'TrashBox','',1251),(1255,0,'Root','',NULL),(1256,0,'Inbox','',1255),(1257,0,'Outbox','',1255),(1258,0,'TrashBox','',1255);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_message`
--

DROP TABLE IF EXISTS `folder_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_message` (
  `Folder_id` int(11) NOT NULL,
  `messages_id` int(11) NOT NULL,
  KEY `FK_5nh3mwey9bw25ansh2thcbcdh` (`messages_id`),
  KEY `FK_dwna03p0i8so6ov91ouups81r` (`Folder_id`),
  CONSTRAINT `FK_dwna03p0i8so6ov91ouups81r` FOREIGN KEY (`Folder_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_5nh3mwey9bw25ansh2thcbcdh` FOREIGN KEY (`messages_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_message`
--

LOCK TABLES `folder_message` WRITE;
/*!40000 ALTER TABLE `folder_message` DISABLE KEYS */;
INSERT INTO `folder_message` VALUES (1236,1260),(1237,1259),(1237,1261),(1240,1259),(1241,1260),(1252,1261);
/*!40000 ALTER TABLE `folder_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_epaovey8hx223i0ow4a08uyh5` (`business_id`),
  KEY `FK_r540dp19wvjcy00hftuhf3ejp` (`user_id`),
  CONSTRAINT `FK_r540dp19wvjcy00hftuhf3ejp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_epaovey8hx223i0ow4a08uyh5` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1273,0,'Totalmente nueva','Nintendo switch','http://www.nintenderos.com/wp-content/uploads/2017/10/nintendo-switch.jpg',1229,NULL),(1274,0,'Totalmente nueva','Item2','http://www.nintenderos.com/wp-content/uploads/2017/10/nintendo-switch.jpg',NULL,1226),(1275,0,'Viene incluido con todos los DLCs','Zelda Breath of the Wild Switch','https://media.vandal.net/m/43030/the-legend-of-zelda-breath-of-the-wild-201732131429_1.jpg',1229,NULL),(1276,0,'Amibo Link Nivel 50 y entrenado para COMPETITIVO SSB','Amibo Link twilight princess','https://images-na.ssl-images-amazon.com/images/I/619sdEL41VL._SX342_.jpg',NULL,1226),(1277,0,'Tiene Todos los juegos instalados','SuperNess Mini','https://images-na.ssl-images-amazon.com/images/I/81dKE5hBovL._SX385_.jpg',1229,NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1259,0,'cuerpo del mensaje 1.','2017-10-01 19:00:00','Asunto1',1226),(1260,0,'cuerpo del mensaje 2.','2017-10-01 19:00:00','Asunto1',1227),(1261,0,'cuerpo del mensaje 3.','2017-10-01 19:00:00','Asunto1',1226);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_actor`
--

DROP TABLE IF EXISTS `message_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_actor` (
  `messagesReceived_id` int(11) NOT NULL,
  `recipients_id` int(11) NOT NULL,
  KEY `FK_apm75cjw83uf1irk3vn5616eq` (`messagesReceived_id`),
  CONSTRAINT `FK_apm75cjw83uf1irk3vn5616eq` FOREIGN KEY (`messagesReceived_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_actor`
--

LOCK TABLES `message_actor` WRITE;
/*!40000 ALTER TABLE `message_actor` DISABLE KEYS */;
INSERT INTO `message_actor` VALUES (1259,1227),(1260,1226),(1261,1229);
/*!40000 ALTER TABLE `message_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderator`
--

DROP TABLE IF EXISTS `moderator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moderator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hardBan` bit(1) DEFAULT NULL,
  `softBan` bit(1) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dvoe3msx5ofgj5qm5tyj6hnm9` (`userAccount_id`),
  CONSTRAINT `FK_dvoe3msx5ofgj5qm5tyj6hnm9` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderator`
--

LOCK TABLES `moderator` WRITE;
/*!40000 ALTER TABLE `moderator` DISABLE KEYS */;
INSERT INTO `moderator` VALUES (1225,0,'ponsavi@acme.org','\0','\0',1218);
/*!40000 ALTER TABLE `moderator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `shopAdvertisement_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a44nxvac74654ia50txuipcr5` (`shopAdvertisement_id`),
  KEY `FK_dj45olnbfmjigkgu8jpg1pxfa` (`user_id`),
  CONSTRAINT `FK_dj45olnbfmjigkgu8jpg1pxfa` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_a44nxvac74654ia50txuipcr5` FOREIGN KEY (`shopAdvertisement_id`) REFERENCES `shopadvertisement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1300,0,'2018-01-15 00:00:00','¿La tienes en verde?',1288,1226);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1262,0,'Me acosa por el chat',1,1228),(1263,0,'Me amenaza por el chat',1,1228),(1264,0,'Me manda foto porno por el chat',1,1228);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `shopAdvertisement_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mouxf2j8lif860nmyptca2nci` (`shopAdvertisement_id`),
  KEY `FK_ctpcwm42wnp8voxqtp6wxawx0` (`user_id`),
  CONSTRAINT `FK_ctpcwm42wnp8voxqtp6wxawx0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_mouxf2j8lif860nmyptca2nci` FOREIGN KEY (`shopAdvertisement_id`) REFERENCES `shopadvertisement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1295,0,'2018-01-15 00:00:00',4,'Muy buena pero no lo tienen en verde',1288,1226);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saleline`
--

DROP TABLE IF EXISTS `saleline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saleline` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `advertisement_id` int(11) NOT NULL,
  `shoppingCart_id` int(11) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dgs2bm117tso8mpl6tqb88ebt` (`shoppingCart_id`),
  KEY `FK_r9t1jie2fpg0tc4xkf0vuv934` (`ticket_id`),
  CONSTRAINT `FK_r9t1jie2fpg0tc4xkf0vuv934` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  CONSTRAINT `FK_dgs2bm117tso8mpl6tqb88ebt` FOREIGN KEY (`shoppingCart_id`) REFERENCES `shoppingcart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saleline`
--

LOCK TABLES `saleline` WRITE;
/*!40000 ALTER TABLE `saleline` DISABLE KEYS */;
INSERT INTO `saleline` VALUES (1296,0,1,1288,NULL,1281),(1297,0,1,1288,1278,NULL),(1298,0,1,1288,NULL,1282);
/*!40000 ALTER TABLE `saleline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipmentaddress`
--

DROP TABLE IF EXISTS `shipmentaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipmentaddress` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_59dqu6e5emkl8epvhcwcjysrh` (`ticket_id`),
  KEY `FK_ko9nflfub9ehvdhnaqase8m01` (`user_id`),
  CONSTRAINT `FK_ko9nflfub9ehvdhnaqase8m01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_59dqu6e5emkl8epvhcwcjysrh` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipmentaddress`
--

LOCK TABLES `shipmentaddress` WRITE;
/*!40000 ALTER TABLE `shipmentaddress` DISABLE KEYS */;
INSERT INTO `shipmentaddress` VALUES (1283,0,'c\\ Rue 13 del Perzebe','Sevilla','España','41008',1281,1226),(1284,0,'c\\ Rue 13 del Perzebe','Madrid','España','41008',1282,1226);
/*!40000 ALTER TABLE `shipmentaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippinginfo`
--

DROP TABLE IF EXISTS `shippinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippinginfo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `trackingNumber` int(11) DEFAULT NULL,
  `ticket_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hpnck4x662afdligdngdnbnm1` (`ticket_id`),
  CONSTRAINT `FK_hpnck4x662afdligdngdnbnm1` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippinginfo`
--

LOCK TABLES `shippinginfo` WRITE;
/*!40000 ALTER TABLE `shippinginfo` DISABLE KEYS */;
INSERT INTO `shippinginfo` VALUES (1285,0,'esta en camino','SEUR',123456789,1281);
/*!40000 ALTER TABLE `shippinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopadvertisement`
--

DROP TABLE IF EXISTS `shopadvertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopadvertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `publicationDate` datetime DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ShopAdvertisementUK_ne2sdvjpax4rsbfim7wlb9u21` (`endDate`),
  KEY `FK_o4t5c1qhqh963xj7e7gyxdbi2` (`business_id`),
  KEY `FK_n135rs6sm9jd29srmgaj067ik` (`item_id`),
  CONSTRAINT `FK_n135rs6sm9jd29srmgaj067ik` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK_o4t5c1qhqh963xj7e7gyxdbi2` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopadvertisement`
--

LOCK TABLES `shopadvertisement` WRITE;
/*!40000 ALTER TABLE `shopadvertisement` DISABLE KEYS */;
INSERT INTO `shopadvertisement` VALUES (1288,0,'2030-01-15 00:00:00','2018-01-15 00:00:00',1229,1273,290.5,100),(1289,0,'2030-01-15 00:00:00','2018-01-15 00:00:00',1229,1275,60,60);
/*!40000 ALTER TABLE `shopadvertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopadvertisement_question`
--

DROP TABLE IF EXISTS `shopadvertisement_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopadvertisement_question` (
  `ShopAdvertisement_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  UNIQUE KEY `UK_kcdntehrkx37fb1y14rsdr0ua` (`questions_id`),
  KEY `FK_fsk4i1h5j3u5u8sorm1rbykfy` (`ShopAdvertisement_id`),
  CONSTRAINT `FK_fsk4i1h5j3u5u8sorm1rbykfy` FOREIGN KEY (`ShopAdvertisement_id`) REFERENCES `shopadvertisement` (`id`),
  CONSTRAINT `FK_kcdntehrkx37fb1y14rsdr0ua` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopadvertisement_question`
--

LOCK TABLES `shopadvertisement_question` WRITE;
/*!40000 ALTER TABLE `shopadvertisement_question` DISABLE KEYS */;
INSERT INTO `shopadvertisement_question` VALUES (1288,1300);
/*!40000 ALTER TABLE `shopadvertisement_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fi14liew17whcr51gl4j8ek10` (`user_id`),
  CONSTRAINT `FK_fi14liew17whcr51gl4j8ek10` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` VALUES (1278,0,1226),(1279,0,1227),(1280,0,1228);
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialidentity`
--

DROP TABLE IF EXISTS `socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialidentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accountURL` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialidentity`
--

LOCK TABLES `socialidentity` WRITE;
/*!40000 ALTER TABLE `socialidentity` DISABLE KEYS */;
INSERT INTO `socialidentity` VALUES (1270,0,'http://CambiarPorUnaURL.com','1','http://SeriaUnaURL.com');
/*!40000 ALTER TABLE `socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lc0q5jjuytcxjxnqb4bhm8fd5` (`business_id`),
  KEY `FK_dtnhsxgk6dhcrddc702w4nyyy` (`seller_id`),
  KEY `FK_1nne0brl6rr604w9ng3to3rom` (`user_id`),
  CONSTRAINT `FK_1nne0brl6rr604w9ng3to3rom` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_dtnhsxgk6dhcrddc702w4nyyy` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_lc0q5jjuytcxjxnqb4bhm8fd5` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1281,0,'2018-01-15 00:00:00','SENT',1229,NULL,1226),(1282,0,'2017-01-15 00:00:00','SENT',1229,NULL,1226);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `emailAddress` varchar(255) DEFAULT NULL,
  `hardBan` bit(1) DEFAULT NULL,
  `softBan` bit(1) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `premium` bit(1) DEFAULT NULL,
  `reputation` int(11) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1226,0,'buyer@acmeshop.org','\0','\0',1217,'Pepón','656343002','\0',5,'Samper Villagrán','\0'),(1227,0,'user2@mail.com','\0','\0',1219,'userName2','656222111','',5,'userSurname2','\0'),(1228,0,'user3@mail.com','\0','\0',1220,'userName3','656222113','',5,'userSurname3','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_photosurl`
--

DROP TABLE IF EXISTS `user_photosurl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_photosurl` (
  `User_id` int(11) NOT NULL,
  `photosURL` varchar(255) DEFAULT NULL,
  KEY `FK_rmiob43ffu6ekntioe0gnnmnd` (`User_id`),
  CONSTRAINT `FK_rmiob43ffu6ekntioe0gnnmnd` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_photosurl`
--

LOCK TABLES `user_photosurl` WRITE;
/*!40000 ALTER TABLE `user_photosurl` DISABLE KEYS */;
INSERT INTO `user_photosurl` VALUES (1226,'\"https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-perfil-masculino-avatar-5-by-vexels.png\"'),(1227,'\"https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-perfil-masculino-avatar-5-by-vexels.png\"'),(1228,'\"https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-perfil-masculino-avatar-5-by-vexels.png\"');
/*!40000 ALTER TABLE `user_photosurl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_socialidentity`
--

DROP TABLE IF EXISTS `user_socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_socialidentity` (
  `User_id` int(11) NOT NULL,
  `socialIdentities_id` int(11) NOT NULL,
  UNIQUE KEY `UK_2o11v8eqj9ilvlbcok78fgqxf` (`socialIdentities_id`),
  KEY `FK_lwx4kcr0ra6caghj2svfkkyhv` (`User_id`),
  CONSTRAINT `FK_lwx4kcr0ra6caghj2svfkkyhv` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_2o11v8eqj9ilvlbcok78fgqxf` FOREIGN KEY (`socialIdentities_id`) REFERENCES `socialidentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_socialidentity`
--

LOCK TABLES `user_socialidentity` WRITE;
/*!40000 ALTER TABLE `user_socialidentity` DISABLE KEYS */;
INSERT INTO `user_socialidentity` VALUES (1226,1270);
/*!40000 ALTER TABLE `user_socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accountNonLocked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1216,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(1217,0,'','24c9e15e52afc47c225b757e7bee1f9d','user1'),(1218,0,'','38caf4a470117125b995f7ce53e6e6b9','moderator1'),(1219,0,'','7e58d63b60197ceb55a1c487989a3720','user2'),(1220,0,'','92877af70a45fd6a2ed7fe81e1236b78','user3'),(1221,0,'','ab36fdc41550db15fd4a47f2e44f0076','business1'),(1222,0,'','9bde7258dadf923622274ab89ca3d28d','business2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (1216,'ADMIN'),(1217,'USER'),(1218,'MODERATOR'),(1219,'USER'),(1220,'USER'),(1221,'BUSINESS'),(1222,'BUSINESS');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoration`
--

DROP TABLE IF EXISTS `valoration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valoration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoration`
--

LOCK TABLES `valoration` WRITE;
/*!40000 ALTER TABLE `valoration` DISABLE KEYS */;
INSERT INTO `valoration` VALUES (1265,0,'2018-01-15 00:00:00',3,1226),(1266,0,'2018-01-15 00:00:00',5,1226),(1267,0,'2018-01-15 00:00:00',2,1227),(1268,0,'2018-01-15 00:00:00',1,1228),(1269,0,'2018-01-15 00:00:00',4,1228);
/*!40000 ALTER TABLE `valoration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-06 17:23:07
