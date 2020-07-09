/*
SQLyog Ultimate v8.32 
MySQL - 5.5.27 : Database - fireworks
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`fireworks` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fireworks`;

/*Table structure for table `ims_group` */

DROP TABLE IF EXISTS `ims_group`;

CREATE TABLE `ims_group`
(
    `id`          int(20) NOT NULL AUTO_INCREMENT COMMENT '组id',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    `group_name`  varchar(50)  DEFAULT '' COMMENT '分组名',
    `order_num`   int(4)       DEFAULT '0' COMMENT '显示顺序',
    `create_by`   varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='组别信息表';

/*Data for the table `ims_group` */

/*Table structure for table `ims_img` */

DROP TABLE IF EXISTS `ims_img`;

CREATE TABLE `ims_img`
(
    `id`          int(20)      NOT NULL AUTO_INCREMENT COMMENT '图片id',
    `user_id`     int(20)      NOT NULL COMMENT '用户id',
    `group_id`    int(20)      DEFAULT '0' COMMENT '分组id',
    `img_url`     varchar(100) NOT NULL COMMENT '图片url',
    `is_marked`   char(1)      DEFAULT '0' COMMENT '是否收藏，未收藏0，收藏1',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    `order_num`   int(4)       DEFAULT '0' COMMENT '显示顺序',
    `del_flag`    char(1)      DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `upload_by`   varchar(64)  DEFAULT '' COMMENT '上传者',
    `upload_time` datetime     DEFAULT NULL COMMENT '上传时间',
    `update_by`   varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='图片表';

/*Data for the table `ims_img` */

/*Table structure for table `sms_login_info` */

DROP TABLE IF EXISTS `sms_login_info`;

CREATE TABLE `sms_login_info`
(
    `id`             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50)  DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(50)  DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)  DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)  DEFAULT '' COMMENT '操作系统',
    `status`         char(1)      DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime     DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统访问记录';

/*Data for the table `sms_login_info` */

/*Table structure for table `sms_oper_log` */

DROP TABLE IF EXISTS `sms_oper_log`;

CREATE TABLE `sms_oper_log`
(
    `id`             int(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50)   DEFAULT '' COMMENT '模块标题',
    `business_type`  int(2)        DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)  DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)   DEFAULT '' COMMENT '请求方式',
    `oper_name`      varchar(50)   DEFAULT '' COMMENT '操作人员',
    `oper_url`       varchar(255)  DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(50)   DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)  DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) DEFAULT '' COMMENT '返回参数',
    `status`         int(1)        DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime      DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='操作日志记录';

/*Data for the table `sms_oper_log` */

/*Table structure for table `ums_role` */

DROP TABLE IF EXISTS `ums_role`;

CREATE TABLE `ums_role`
(
    `id`          int(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(64)  DEFAULT NULL COMMENT '名称',
    `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色管理表';

/*Data for the table `ums_role` */

/*Table structure for table `ums_user` */

DROP TABLE IF EXISTS `ums_user`;

CREATE TABLE `ums_user`
(
    `id`            int(20) NOT NULL AUTO_INCREMENT,
    `username`      varchar(64)  DEFAULT NULL COMMENT '用户名',
    `old_password`  varchar(64)  DEFAULT NULL COMMENT '密码',
    `sex`           int(4)       DEFAULT NULL COMMENT '性别 0表示男，1表示女，2表示未知',
    `phone`         varchar(50)  DEFAULT NULL COMMENT '电话',
    `e_mail`        varchar(100) DEFAULT NULL COMMENT '邮箱',
    `create_time`   datetime     DEFAULT NULL COMMENT '账号创建时间',
    `login_time`    datetime     DEFAULT NULL COMMENT '最后登录时间',
    `expire_time`   datetime     DEFAULT NULL COMMENT 'token过期时间',
    `validate_code` varchar(20)  DEFAULT NULL COMMENT '随机验证码',
    `code_status`   int(4)       DEFAULT NULL COMMENT '是否激活：0->未激活 1->已激活',
    `rid`           int(4)       DEFAULT '1' COMMENT '用户角色，0为管理员，1为普通用户',
    `birthday`      datetime     DEFAULT NULL COMMENT '生日',
    `verify_code`   varchar(20)  DEFAULT NULL COMMENT '验证码',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8 COMMENT ='用户管理表';

/*Data for the table `ums_user` */

insert into `ums_user`(`id`, `username`, `old_password`, `sex`, `phone`, `e_mail`, `create_time`, `login_time`,
                       `expire_time`, `validate_code`, `code_status`, `rid`, `birthday`, `verify_code`)
values (1, 'd的思维啊', 'dewa', NULL, '312', '321', '0000-00-00 00:00:00', NULL, NULL, NULL, NULL, 1, NULL, NULL),
       (2, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL),
       (3, 'admin1', '123456hyh', 1, '123456789', '1332790762@qq.com', NULL, NULL, NULL, 'd25b7b91-9', 1, NULL, NULL,
        NULL),
       (4, 'zhaoyanwei', 'zyw123', 0, '15167053180', '1332790762@qq.com', NULL, NULL, NULL, '0c430cc9-d', 1, NULL,
        '2020-07-03 00:00:00', NULL),
       (5, 'zyw1', 'zyw123', 0, '15167053180', '1332790762@qq.com', NULL, NULL, NULL, 'e17aea1f-2', 1, NULL,
        '2020-07-03 00:00:00', NULL),
       (6, 'zyw2', 'f20f42cd19586b826a174b4bb12f8e03', 1, '15167053180', '1332790762@qq.com', NULL,
        '2020-07-05 01:19:40', NULL, '1c4ac5d5-9', 1, NULL, '2020-07-03 00:00:00', NULL),
       (7, 'hyhhyh', 'be8cfa8dc9c60bc446a4739353ba1645', 0, '14252525225', '1332790762@qq.com', NULL, NULL, NULL,
        'b9575f38-0', 1, NULL, '2020-07-17 00:00:00', NULL),
       (8, 'qingdexin', '699d225b50bfced5ddaaa2b72ec7a7a6', NULL, '13575823912', NULL, NULL, NULL, NULL, '7ca43', 0,
        NULL, '2020-07-03 00:00:00', 'xue3'),
       (9, '1', 'c4ca4238a0b923820dcc509a6f75849b', 0, '1', NULL, NULL, NULL, NULL, 'c7760', 0, NULL,
        '2020-07-16 00:00:00', 'nw57'),
       (10, 'qingdexin1', 'e120ea280aa50693d5568d0071456460', 0, '1323454654', '1332790762@qq.com', NULL, NULL, NULL,
        '476bd', 1, NULL, '2020-07-30 00:00:00', 'q95i');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
