## 更新用户表
ALTER TABLE `cc_user`
    CHANGE COLUMN `uid` `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST,
    MODIFY COLUMN `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号' AFTER `id`,
    MODIFY COLUMN `user_icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像' AFTER `username`,
    MODIFY COLUMN `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' AFTER `user_icon`,
    MODIFY COLUMN `create_time` date NULL DEFAULT NULL COMMENT '创建时间' AFTER `password`,
    MODIFY COLUMN `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁' AFTER `is_valid`,
    MODIFY COLUMN `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' AFTER `version`,
    MODIFY COLUMN `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称' AFTER `about`,
    ADD COLUMN `update_time` datetime NULL COMMENT '更新时间' AFTER `ban`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`) USING BTREE;
