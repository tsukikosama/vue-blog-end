### 新增生活日记表
CREATE TABLE `cc_life_note`
(
    `id`          bigint       NOT NULL COMMENT '主键',
    `origin_url`  varchar(255) NOT NULL COMMENT '原图地址',
    `web_url`     varchar(255) NOT NULL COMMENT '网页压缩图地址',
    `user_id`     bigint       NOT NULL COMMENT '发布者id',
    `description` varchar(255) NULL COMMENT '描述',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
);