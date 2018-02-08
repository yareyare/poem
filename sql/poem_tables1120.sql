-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.100.177    Database: poem
-- ------------------------------------------------------
-- Server version	5.6.26

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

drop database if exists poem;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE poem CHARACTER SET utf8;

use poem;

DROP TABLE IF EXISTS `appreciation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appreciation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(64) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `poetries_id` (`poetries_id`),
  CONSTRAINT `appreciation_ibfk_1` FOREIGN KEY (`poetries_id`) REFERENCES `poetries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `type` varchar(1) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `poetries_id` (`poetries_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`poetries_id`) REFERENCES `poetries` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dynasty`
--

DROP TABLE IF EXISTS `dynasty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dynasty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '朝代',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(64) NOT NULL,
  `file` varchar(64) NOT NULL,
  `type` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_path_UNIQUE` (`path`,`file`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `picture_poeties`
--

DROP TABLE IF EXISTS `picture_poeties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture_poeties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_id` int(11) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_poeties_UNIQUE` (`picture_id`),
  KEY `poetries_id` (`poetries_id`),
  CONSTRAINT `picture_poeties_ibfk_1` FOREIGN KEY (`poetries_id`) REFERENCES `poetries` (`id`),
  CONSTRAINT `picture_poeties_ibfk_2` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `picture_poets`
--

DROP TABLE IF EXISTS `picture_poets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture_poets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_id` int(11) NOT NULL,
  `poets_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_poets_UNIQUE` (`picture_id`),
  KEY `poets_id` (`poets_id`),
  CONSTRAINT `picture_poets_ibfk_1` FOREIGN KEY (`poets_id`) REFERENCES `poets` (`id`),
  CONSTRAINT `picture_poets_ibfk_2` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poem`
--

DROP TABLE IF EXISTS `poem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poet_id` int(11) DEFAULT NULL,
  `content` text COMMENT '诗歌正文',
  `title` varchar(255) DEFAULT NULL,
  `dynasty_id` varchar(16) NOT NULL COMMENT '朝代id',
  `types` varchar(255) DEFAULT NULL COMMENT '本诗所有的所属类型',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poem_detail`
--

DROP TABLE IF EXISTS `poem_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poem_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poem_id` int(11) NOT NULL COMMENT '诗id',
  `type` varchar(32) NOT NULL COMMENT '类型:译文 赏析 创作背景等',
  `content` text COMMENT '译注赏内容',
  `reference` text COMMENT '参考',
  `sort` int(2) NOT NULL COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poem_type`
--

DROP TABLE IF EXISTS `poem_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poem_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL COMMENT '大类',
  `type1` varchar(16) DEFAULT NULL COMMENT '小类',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `_type_type1` (`type`,`type1`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poem_type_ref`
--

DROP TABLE IF EXISTS `poem_type_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poem_type_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poem_id` int(11) NOT NULL,
  `poem_type_id` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `_poemId_typeId` (`poem_id`,`poem_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poet`
--

DROP TABLE IF EXISTS `poet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `introduce` text NOT NULL,
  `dynasty_id` int(4) NOT NULL COMMENT '朝代id',
  `pic_url` VARCHAR(16) '诗人图片地址',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poet_detail`
--

DROP TABLE IF EXISTS `poet_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poet_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poet_id` int(11) NOT NULL COMMENT '诗人id',
  `type` varchar(32) NOT NULL COMMENT '类型:诗人介绍 创作背景等',
  `content` text COMMENT '诗人各项介绍的内容',
  `reference` text COMMENT '参考',
  `sort` int(2) NOT NULL COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `praise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `praise_UNIQUE` (`poetries_id`,`users_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `praise_ibfk_1` FOREIGN KEY (`poetries_id`) REFERENCES `poetries` (`id`),
  CONSTRAINT `praise_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `id` varchar(11) NOT NULL,
  `user_id` varchar(11) NOT NULL,
  `status` int(2) NOT NULL COMMENT '是否有效 1:有效 0:无效',
  `inValidTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `translation`
--

DROP TABLE IF EXISTS `translation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `translation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(64) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `poetries_id` (`poetries_id`),
  CONSTRAINT `translation_ibfk_1` FOREIGN KEY (`poetries_id`) REFERENCES `poetries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(128) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `ic` varchar(256) DEFAULT NULL,
  `phone` varchar(128) NOT NULL,
  `status` int(2) NOT NULL COMMENT '是否有效 1:有效 0:无效',
  `active` int(2) NOT NULL COMMENT '短信是否激活 1:有效 0:无效',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `deviceToken` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-20 18:35:24
