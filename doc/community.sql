create database community;
use community;

-- 帖子表
create table if not exists community.`discuss_post`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `title` varchar(256) null comment '帖子标题',
    `content` text null comment '文章内容',
    `user_id` int(11) not null comment '用户id',
    `type` int(11) null comment '0-正常 1-置顶',
    `status` int(11) null comment '0-正常 1-精华 2-拉黑',
    `comment_count` int(11) null comment '评论数量',
    `score` double null comment '热度',
    `create_time` datetime  not null comment '创建时间',
    `update_time` datetime not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '帖子表';


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