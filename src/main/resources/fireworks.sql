SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`
(`id` INT(20) NOT NULL AUTO_INCREMENT,
    `username`        VARCHAR(64) DEFAULT NULL COMMENT '用户名',
    `password`        VARCHAR(64) DEFAULT NULL COMMENT '密码',
    `sex`  INT(4)      DEFAULT NULL COMMENT '性别 0表示男，1表示女，2表示未知',
    `phone` VARCHAR(50)       DEFAULT NULL  COMMENT '电话',
    `email`        VARCHAR(100)      DEFAULT NULL COMMENT '邮箱',
    `create_time`        DATETIME DEFAULT NULL COMMENT '账号创建时间',
    `login_time`        DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `expire_time`        DATETIME DEFAULT NULL COMMENT 'token过期时间',
    `validate_code`        VARCHAR(20) DEFAULT NULL COMMENT '随机验证码',
    `code_status`        INT(4) DEFAULT NULL COMMENT '是否激活：0->未激活 1->已激活',
    `rid` INT(4) DEFAULT '1' COMMENT '用户角色，0为管理员，1为普通用户',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户管理表';
  
  DROP TABLE IF EXISTS `ums_role`;
  CREATE TABLE `ums_role`
  (
	`id` INT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
	`description` VARCHAR(500)      DEFAULT NULL COMMENT '描述信息',
	PRIMARY KEY (`id`)
  )ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='角色管理表';