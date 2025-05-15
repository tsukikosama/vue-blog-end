CREATE TABLE `cc_code`
(
    `id`          bigint       NOT NULL COMMENT 'id',
    `code`        varchar(255) NOT NULL COMMENT '标签名字',
    `value`       varchar(255) NOT NULL COMMENT '标签值',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `remark`      varchar(255) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);
CREATE TABLE `cc_dict`
(
    `id`          bigint       NOT NULL COMMENT '组件',
    `code_id`     bigint       NOT NULL COMMENT '类型id',
    `code_value`  varchar(255) NOT NULL COMMENT '字典值',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `create_user` bigint       NOT NULL COMMENT '更新用户',
    `remark`      varchar(255) NULL COMMENT '备注',
    PRIMARY KEY (`id`)
);