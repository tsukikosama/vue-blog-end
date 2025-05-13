### 新增记录表
CREATE TABLE `cc_angry_pig`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50) NOT NULL COMMENT '用户',
    `cause`       text        NOT NULL COMMENT '原因',
    `create_time` datetime    NOT NULL COMMENT '时间',
    `create_user` bigint      NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
);
ALTER TABLE `cc_angry_pig`
    ADD COLUMN `type` tinyint NOT NULL COMMENT '事件类型' AFTER `create_user`;