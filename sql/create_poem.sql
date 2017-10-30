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
  `types` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '本诗所有的所属类型',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43031 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

