drop database if exists redis;
create database redis;
use redis;
create table t_user
(
    `id`          int unsigned     not null auto_increment,
    `username`    varchar(50)      not null default '' comment '用户名',
    `password`    varchar(50)      not null default '' comment '密码',
    `sex`         tinyint          not null default '0' comment '性别 0=女 1=男',
    `deleted`     tinyint unsigned not null default '0' comment '删除标志，默认0不删除，1删除',
    `update_time` timestamp        not null comment '更新时间',
    `create_time` timestamp        not null comment '创建时间',
    primary key (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1001
  DEFAULT CHARSET = UTF8MB4 comment '用户表';
