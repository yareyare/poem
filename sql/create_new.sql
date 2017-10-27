#诗人表
CREATE TABLE `dynasty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '朝代',

  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` INT(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2529 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#诗歌表
CREATE TABLE `poem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poet_id` int(11) DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci COMMENT '诗歌正文',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dynasty_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '朝代id',
  `types` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '本诗所有的所属类型',

  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` INT(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


#诗人表
CREATE TABLE `poet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dynasty_id` INT (4) NOT NULL COMMENT '朝代id',

  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` INT(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2529 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


#诗歌的其他项 译，注，赏，创作背景等
CREATE TABLE `poet_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poem_id` int(11) NOT NULL COMMENT '诗id',
  `type` INT (4) NOT NULL COMMENT '类型:译文 赏析 创作背景等',
  `content` text COLLATE utf8_unicode_ci COMMENT '译注赏内容',
  `reference` text COLLATE utf8_unicode_ci COMMENT '参考',
  `sort` INT (2) NOT NULL COMMENT '排序' ,

  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用标志位：1-可用 0-停用',
  `delete_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志位：1-已删除 0-未删除',
  `data_version` INT(8) NOT NULL DEFAULT '0' COMMENT '数据修改版本号',
  `create_date` datetime NOT NULL DEFAULT '2017-00-00 00:00:00' COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2529 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

