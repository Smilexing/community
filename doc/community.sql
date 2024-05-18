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


LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'SYSTEM','SYSTEM','SYSTEM','nowcoder1@sina.com',0,1,NULL,'http://static.nowcoder.com/images/head/notify.png','2019-04-13 02:11:03'),(11,'nowcoder11','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder11@sina.com',1,1,NULL,'http://images.nowcoder.com/head/11t.png','2019-04-17 09:11:27'),(12,'nowcoder12','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder12@sina.com',1,1,NULL,'http://images.nowcoder.com/head/12t.png','2019-04-17 09:11:28'),(13,'nowcoder13','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder13@sina.com',1,1,NULL,'http://images.nowcoder.com/head/13t.png','2019-04-17 09:11:28'),(21,'nowcoder21','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder21@sina.com',2,1,NULL,'http://images.nowcoder.com/head/21t.png','2019-04-17 09:11:28'),(22,'nowcoder22','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder22@sina.com',2,1,NULL,'http://images.nowcoder.com/head/22t.png','2019-04-17 09:11:28'),(23,'nowcoder23','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder23@sina.com',2,1,NULL,'http://images.nowcoder.com/head/23t.png','2019-04-17 09:11:28'),(24,'nowcoder24','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder24@sina.com',2,1,NULL,'http://images.nowcoder.com/head/24t.png','2019-04-17 09:11:28'),(25,'nowcoder25','25ac0a2e8bd0f28928de3c56149283d6','49f10','nowcoder25@sina.com',2,1,NULL,'http://images.nowcoder.com/head/25t.png','2019-04-17 09:11:28'),(101,'liubei','390ba5f6b5f18dd4c63d7cda170a0c74','12345','nowcoder101@sina.com',0,1,NULL,'http://images.nowcoder.com/head/100t.png','2019-04-03 04:04:55'),(102,'guanyu','390ba5f6b5f18dd4c63d7cda170a0c74','12345','nowcoder102@sina.com',0,1,NULL,'http://images.nowcoder.com/head/200t.png','2019-04-03 04:04:55'),(103,'zhangfei','390ba5f6b5f18dd4c63d7cda170a0c74','12345','nowcoder103@sina.com',0,1,NULL,'http://images.nowcoder.com/head/100t.png','2019-04-03 04:04:55'),(111,'aaa','b8ca3cbc6fd57c78736c66611a7e7044','167f9','nowcoder111@sina.com',0,1,NULL,'http://images.nowcoder.com/head/111t.png','2019-04-03 07:31:19'),(112,'bbb','216dc48902665b6a6dba534717d49592','90ad0','nowcoder112@sina.com',0,1,NULL,'http://images.nowcoder.com/head/112t.png','2019-04-04 10:36:24'),(113,'ccc','511247cf6bf9cf3d37aef555d0dd9b75','fe290','nowcoder113@sina.com',0,1,NULL,'http://images.nowcoder.com/head/705t.png','2019-04-06 13:29:52'),(114,'ddd','d432b1aaec9197cb6e23ed8e335fe42f','fd1b1','nowcoder114@sina.com',0,1,NULL,'http://images.nowcoder.com/head/972t.png','2019-04-06 13:30:36'),(115,'eee','caca1fe634005d505afd82ef7874fc4f','0c8d2','nowcoder115@sina.com',0,1,NULL,'http://images.nowcoder.com/head/316t.png','2019-04-06 13:30:48'),(116,'fff','deda51913a3ae16d9915fc4c520ac4b6','19341','nowcoder116@sina.com',0,1,NULL,'http://images.nowcoder.com/head/180t.png','2019-04-06 13:31:00'),(117,'ggg','4e85bff4afbb34b2dd676f5e5737050f','9931d','nowcoder117@sina.com',0,1,NULL,'http://images.nowcoder.com/head/896t.png','2019-04-06 13:31:19'),(118,'hhh','8d4c0d490ea1585cd7973bb55bd39d07','e123d','nowcoder118@sina.com',0,1,NULL,'http://images.nowcoder.com/head/834t.png','2019-04-06 13:38:18'),(119,'iii','2214de584a27c7c28dd46a9505bfdc8b','f8880','nowcoder119@sina.com',0,1,NULL,'http://images.nowcoder.com/head/240t.png','2019-04-06 13:38:33'),(120,'jjj','9522866891d647323a7fb5c640b8fa37','12c25','nowcoder120@sina.com',0,1,NULL,'http://images.nowcoder.com/head/760t.png','2019-04-06 13:39:45'),(121,'kkk','5a80e7d897dec9b0aec2919fb42a158e','b8710','nowcoder121@sina.com',0,1,NULL,'http://images.nowcoder.com/head/358t.png','2019-04-06 13:41:34'),(122,'lll','fdc3616df634614bc0ffacee17f735bd','b067f','nowcoder122@sina.com',0,1,NULL,'http://images.nowcoder.com/head/70t.png','2019-04-06 13:45:36'),(123,'mmm','d9b57ddfa9faa06c581c803dc2811edb','f7014','nowcoder123@sina.com',0,1,NULL,'http://images.nowcoder.com/head/160t.png','2019-04-06 13:48:34'),(124,'nnn','f878b7181a95a3330d90198deab16aca','bbf47','nowcoder124@sina.com',0,1,NULL,'http://images.nowcoder.com/head/506t.png','2019-04-06 13:49:39'),(125,'ooo','f71e07cc9c6ebb9e8cfc1fc58265ff33','ff72a','nowcoder125@sina.com',0,1,NULL,'http://images.nowcoder.com/head/45t.png','2019-04-06 13:50:35'),(126,'ppp','e2f178c5076dabbb35b73da19774b271','5027b','nowcoder126.sina.com',0,1,NULL,'http://images.nowcoder.com/head/771t.png','2019-04-06 13:51:42'),(127,'qqq','d209b28b19fdcb4aafc3a795157a4651','3aebf','nowcoder127@sina.com',0,1,NULL,'http://images.nowcoder.com/head/492t.png','2019-04-06 13:52:29'),(128,'rrr','a6043995e741593540687d87c1ce40e8','c543c','nowcoder128@sina.com',0,1,NULL,'http://images.nowcoder.com/head/779t.png','2019-04-06 13:53:19'),(129,'sss','ae8754f0d791f9fea1627a1862c4de5f','d3641','nowcoder129@sina.com',0,1,NULL,'http://images.nowcoder.com/head/977t.png','2019-04-06 13:57:34'),(131,'ttt','d50960f067142c59cc3bdac61b87759f','72450','nowcoder131@sina.com',0,1,NULL,'http://images.nowcoder.com/head/677t.png','2019-04-08 04:05:49'),(132,'uuu','a80ba77157d2fd9c67dd3187907cef42','f1113','nowcoder132@sina.com',0,1,NULL,'http://images.nowcoder.com/head/278t.png','2019-04-08 06:07:04'),(133,'vvv','252c226ba0a601ec3fa4d7c58b2291d9','13211','nowcoder133@sina.com',0,1,NULL,'http://images.nowcoder.com/head/133t.png','2019-04-19 03:08:55'),(134,'www','3d3aeebb9cd302ae581dfa8fedd86ceb','dfc00','nowcoder134@sina.com',0,1,NULL,'http://images.nowcoder.com/head/134t.png','2019-04-19 10:13:57'),(137,'xxx','046291c11cdfb896aa7cd48714bb6352','968fc','nowcoder137@sina.com',0,1,NULL,'http://images.nowcoder.com/head/677t.png','2019-04-26 11:48:31'),(138,'yyy','046291c11cdfb896aa7cd48714bb6352','968fc','nowcoder138@sina.com',0,1,'69dcd69f4c0145058df820e90820ba1e','http://images.nowcoder.com/head/138t.png','2019-04-25 07:18:07'),(144,'zzz','07b83b7747ca08bc4b0153d5b8ce7519','21e8b','nowcoder144@sina.com',0,1,'107eb2cbb8454fbe96848790e6a730b1','http://images.nowcoder.com/head/144t.png','2019-04-26 08:59:50'),(145,'lhh','d980a16ea0b3c8a81062ee806e65a4bc','5abfc','nowcoder145@sina.com',0,1,'f217b637e9544e2a9b4a88f78c583d03','http://images.nowcoder.com/head/145t.png','2019-04-28 07:30:36'),(146,'lihonghe','100489ece9bacfa4d57eb5777b4d4643','00d7a','nowcoder146@sina.com',0,1,'5a61faee7af94e89ba7237b1277c9fed','http://images.nowcoder.com/head/146t.png','2019-04-29 03:47:24'),(149,'niuke','ebce124c4ba2de3be92dc9a3bc1ea75b','90196','nowcoder149@sina.com',0,1,'d7a5714a145b4461b5a4199cc5a0014f','http://images.nowcoder.com/head/149t.png','2019-05-17 07:48:11');
UNLOCK TABLES;
