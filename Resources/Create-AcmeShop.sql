start transaction;

drop database if exists `acmeshop`;
create database `acmeshop`;

use `acmeshop`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `acmeshop`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `acmeshop`.* to 'acme-manager'@'%';

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
INSERT INTO `actor_folder` VALUES (10,11),(10,12),(10,13),(10,14);
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
INSERT INTO `admin` VALUES (10,0,'ponsavi@acme.org','\0','\0',8);
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
INSERT INTO `config` VALUES (9,0,0.21,2,50,'Buy, sell or auction everything that you can imagine',100,20,5,1,'Compra, vende o subasta todo que puedas imaginar',5,10);
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
INSERT INTO `config_spamwords` VALUES (9,'sex'),(9,'sexo'),(9,'viagra'),(9,'cialis');
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
INSERT INTO `folder` VALUES (11,0,'Root','',NULL),(12,0,'Inbox','',11),(13,0,'Outbox','',11),(14,0,'TrashBox','',11);
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
INSERT INTO `useraccount` VALUES (8,0,'','21232f297a57a5a743894a0e4a801fc3','admin');
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
INSERT INTO `useraccount_authorities` VALUES (8,'ADMIN');
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

-- Dump completed on 2018-06-07 19:01:02
