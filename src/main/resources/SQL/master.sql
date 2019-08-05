
-- ----------------------------
-- 用户表
-- ----------------------------
CREATE TABLE `t_user`
(
    `id`          bigint(20)                        NOT NULL AUTO_INCREMENT,
    `username`    varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
    `password`    varchar(55) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '密码',
    `mobile`      varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
    `sex`         varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '性别',
    `email`       varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '邮箱',
    `create_time` datetime                                   DEFAULT NULL,
    `update_time` datetime                                   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 89
  DEFAULT CHARSET = utf8mb4;


-- ----------------------------
-- 部门表
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `department`
(
    `id`             INT(11) NOT NULL AUTO_INCREMENT,
    `department_name` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4;


-- ----------------------------
-- 员工表
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `employee`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `lastName` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `email`    VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `gender`   INT(2)                             DEFAULT NULL,
    `d_id`     INT(11)                            DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4;