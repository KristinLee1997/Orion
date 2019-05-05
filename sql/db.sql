create database orion charset utf8mb64;
use orion;

-- auto-generated definition
create table question
(
    id          bigint auto_increment
        primary key,
    title       text          null comment '问题题目',
    description text          null comment '问题描述',
    type        int default 0 null comment '问题类型，0：填空，1：选择',
    category_id int default 0 null comment '问题种类，例如数据库，操作系统等',
    difficulty  int default 0 null comment '难度级别',
    selection   varchar(1000) null comment '选择题选项',
    answer      text          null comment '问题答案'
);

-- auto-generated definition
create table category
(
    id            int auto_increment comment '题目类型id'
        primary key,
    category_name varchar(50) null comment '题目类型'
);


-- auto-generated definition
create table ac_question
(
    id          bigint auto_increment
        primary key,
    gaea_id     bigint null,
    question_id bigint null
);

create index ac_question_gaea_id_index
    on ac_question (gaea_id);

create index ac_question_question_id_index
    on ac_question (question_id);

