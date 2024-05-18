create database community;
use community;

-- 帖子表
DROP TABLE IF EXISTS `discuss_post`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `discuss_post` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `user_id` varchar(45) DEFAULT NULL,
                                `title` varchar(100) DEFAULT NULL,
                                `content` text,
                                `type` int(11) DEFAULT NULL COMMENT '0-普通; 1-置顶;',
                                `status` int(11) DEFAULT NULL COMMENT '0-正常; 1-精华; 2-拉黑;',
                                `create_time` timestamp NULL DEFAULT NULL,
                                `comment_count` int(11) DEFAULT NULL,
                                `score` double DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- 用户表

DROP TABLE IF EXISTS `user`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) DEFAULT NULL,
                        `password` varchar(50) DEFAULT NULL,
                        `salt` varchar(50) DEFAULT NULL,
                        `email` varchar(100) DEFAULT NULL,
                        `type` int(11) DEFAULT NULL COMMENT '0-普通用户; 1-超级管理员; 2-版主;',
                        `status` int(11) DEFAULT NULL COMMENT '0-未激活; 1-已激活;',
                        `activation_code` varchar(100) DEFAULT NULL,
                        `header_url` varchar(200) DEFAULT NULL,
                        `create_time` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `index_username` (`username`(20)),
                        KEY `index_email` (`email`(20))
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- 从101开始自增


