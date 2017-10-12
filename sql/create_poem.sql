use poetry;

#DROP TABLE IF EXISTS `poets`;
#DROP TABLE IF EXISTS `poetries`;


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
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


#诗歌类型表
CREATE TABLE `poetries_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL COMMENT '大类',
  `type1` varchar(16) DEFAULT NULL COMMENT '小类',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#诗歌与类型关系表
CREATE TABLE `poetries_to_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_id` int(11) NOT NULL AUTO_INCREMENT,
  `poetries_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
