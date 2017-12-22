drop database if exists poem_all;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE poem_all CHARACTER SET utf8;

use poem_all;

#朝代
CREATE TABLE `dynasty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16)  NOT NULL COMMENT '朝代',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

#诗歌
CREATE TABLE `poem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_id` int(11) NOT NULL COMMENT '爬下来的id',
  `poet_id` int(11) DEFAULT NULL,
  `content` text  COMMENT '诗歌正文',
  `title` varchar(255)  DEFAULT NULL,
  `dynasty_id` varchar(16)  NOT NULL COMMENT '朝代id',
  `types` varchar(255) NOT NULL COMMENT '本诗所有的所属类型',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

#诗歌详情
CREATE TABLE `poem_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poem_id` int(11) NOT NULL COMMENT '诗id',
  `type` varchar(32)  NOT NULL COMMENT '类型:译文 赏析 创作背景等',
  `content` text  COMMENT '译注赏内容',
  `reference` text COMMENT '参考',
  `sort` int(2) NOT NULL COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

#诗歌类型
CREATE TABLE `poem_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL COMMENT '大类',
  `type1` varchar(16) DEFAULT NULL COMMENT '小类',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `_type_type1` (`type`,`type1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `poem_type_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poem_id` int(11) NOT NULL,
  `poem_type_id` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `_poemId_typeId` (`poem_id`,`poem_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

#诗人
CREATE TABLE `poet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref_id` int(11) NOT NULL COMMENT '爬下来的id',
  `name` varchar(255) DEFAULT NULL,
  `dynasty_id` int(4) NOT NULL COMMENT '朝代id',
  `introduce` text NOT NULL,
  `pic_url` varchar(16) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` int(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `create_date` datetime NOT NULL DEFAULT '2017-12-20 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


