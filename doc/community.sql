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