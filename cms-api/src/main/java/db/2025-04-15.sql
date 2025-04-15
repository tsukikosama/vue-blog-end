### 将用户表的日期改为datetime
ALTER TABLE `cc_user`
    MODIFY COLUMN `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `password`;