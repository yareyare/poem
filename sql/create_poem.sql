CREATE DATABASE IF NOT EXISTS `poetry` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_swedish_ci */;

use poetry;

#DROP TABLE IF EXISTS `poets`;
#DROP TABLE IF EXISTS `poetries`;
DROP TABLE IF EXISTS picture_poeties;
DROP TABLE IF EXISTS picture_poets;
DROP TABLE IF EXISTS praise;
DROP TABLE IF EXISTS comment;

DROP TABLE IF EXISTS session;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS picture;
DROP TABLE IF EXISTS translation;
DROP TABLE IF EXISTS appreciation;



#诗人表
CREATE TABLE `poets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `dynasty` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '朝代',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2529 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#诗歌表
CREATE TABLE `poetries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poet_id` int(11) DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dynasty` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '朝代',
  `types` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '本诗所有的所属类型',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


#session
CREATE TABLE `session` (
  `id` varchar(11) NOT NULL,
  `user_id` varchar(11) NOT NULL,
  `status` int(2) NOT NULL COMMENT '是否有效 1:有效 0:无效',
  `inValidTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#用户表
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

#图片表
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(64) NOT NULL,
  `file` varchar(64) NOT NULL,
  `type` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_path_UNIQUE` (`path`,`file`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#诗歌译文表
CREATE TABLE `translation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(64) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(poetries_id) references poetries(id)      #poetries.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#诗歌赏析表
CREATE TABLE `appreciation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(64) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(poetries_id) references poetries(id)      #poetries.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#诗歌插图-关系表
CREATE TABLE `picture_poeties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_id` int(11) NOT NULL,
  `poetries_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_poeties_UNIQUE` (`picture_id`),
  FOREIGN KEY(poetries_id) references poetries(id),     #poetries.id 外健
  FOREIGN KEY(picture_id) references picture(id)        #picture.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#诗人-画像-关系表
CREATE TABLE `picture_poets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_id` int(11) NOT NULL,
  `poets_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `picture_poets_UNIQUE` (`picture_id`),
  FOREIGN KEY(poets_id) references poets(id),         #poets.id 外健
  FOREIGN KEY(picture_id) references picture(id)      #picture.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#点赞
CREATE TABLE `praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `praise_UNIQUE` (`poetries_id`,`users_id`),
  FOREIGN KEY(poetries_id) references poetries(id),      #poetries.id 外健
  FOREIGN KEY(users_id) references users(id)             #users.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#评论  #回复
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `type` varchar(1) NOT NULL default 1,           #1.评论，2.回复
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY(poetries_id) references poetries(id),          #poets.id 外健
  FOREIGN KEY(users_id) references users(id)      #picture.id 外健
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
