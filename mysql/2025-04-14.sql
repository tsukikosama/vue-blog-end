## 更新博客表
ALTER TABLE `cc_blog`
    CHANGE COLUMN `bid` `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST,
    CHANGE COLUMN `tid` `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签id' AFTER `version`,
    CHANGE COLUMN `uid` `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id' AFTER `simple_title`,
    MODIFY COLUMN `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题' AFTER `id`,
    MODIFY COLUMN `picture` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片' AFTER `title`,
    MODIFY COLUMN `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `picture`,
    MODIFY COLUMN `is_valid` int(11) NULL DEFAULT NULL COMMENT '是否有效' AFTER `content`,
    MODIFY COLUMN `create_date` date NULL DEFAULT NULL COMMENT '创建日期' AFTER `is_valid`,
    MODIFY COLUMN `version` int(11) NULL DEFAULT NULL COMMENT '版本' AFTER `create_date`,
    MODIFY COLUMN `visit` int(11) NULL DEFAULT NULL COMMENT '流量数量' AFTER `tag_id`,
    MODIFY COLUMN `simple_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简化标题' AFTER `visit`,
    ADD COLUMN `create_time` datetime NULL COMMENT '创建时间' AFTER `state`,
    ADD COLUMN `update_time` datetime NULL COMMENT '更新时间' AFTER `create_time`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`id`) USING BTREE;

## 更新反馈表
ALTER TABLE `cc_feedback`
    MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST,
    MODIFY COLUMN `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' AFTER `id`,
    MODIFY COLUMN `avator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像' AFTER `nickname`,
    MODIFY COLUMN `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息' AFTER `avator`,
    MODIFY COLUMN `datetime` datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `message`;

ALTER TABLE `cc_blog`
    MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST;
ALTER TABLE `cc_blog`
    MODIFY COLUMN `tag_id` bigint NULL DEFAULT NULL COMMENT '标签id' AFTER `version`,
    MODIFY COLUMN `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id' AFTER `simple_title`;
ALTER TABLE `cc_blog`
    MODIFY COLUMN `tag_id` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签id' AFTER `version`,
    MODIFY COLUMN `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户id' AFTER `simple_title`;

### 更新友链表
ALTER TABLE `cc_friendlink`
    MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST,
    MODIFY COLUMN `web_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站链接' AFTER `id`,
    MODIFY COLUMN `web_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名字' AFTER `web_url`,
    MODIFY COLUMN `web_descript` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '网站描述' AFTER `web_name`,
    MODIFY COLUMN `web_time` date NULL DEFAULT NULL COMMENT '添加时间' AFTER `web_descript`,
    MODIFY COLUMN `web_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站图片' AFTER `web_time`;

### 更新模块
ALTER TABLE `cc_module`
    MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST,
    MODIFY COLUMN `module_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名字' AFTER `id`,
    ADD COLUMN `create_time` datetime NULL COMMENT '创建时间' AFTER `date`,
    ADD COLUMN `update_time` datetime NULL COMMENT '更新时间' AFTER `create_time`;

### 更新评论表
ALTER TABLE `cc_review`
    DROP COLUMN `username`,
    DROP COLUMN `usericon`,
    CHANGE COLUMN `time` `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间' AFTER `reply`,
    CHANGE COLUMN `bid` `blog_id` bigint(11) NULL DEFAULT NULL COMMENT '博客id' AFTER `likes`,
    CHANGE COLUMN `rid` `reply_id` bigint(11) NULL DEFAULT NULL COMMENT '回复的是哪个评论的id' AFTER `blog_id`,
    CHANGE COLUMN `uid` `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id' AFTER `reply_id`,
    MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST,
    MODIFY COLUMN `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容' AFTER `id`,
    MODIFY COLUMN `likes` int(11) NULL DEFAULT NULL COMMENT '点赞数' AFTER `create_time`;
ALTER TABLE `cc_review`
    MODIFY COLUMN `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST;

### 更新标签表
ALTER TABLE `cc_type`
    CHANGE COLUMN `type_name` `tag_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称' AFTER `id`,
    MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST,
    ADD COLUMN `create_time` datetime NULL COMMENT '创建时间' AFTER `tag_name`,
    ADD COLUMN `updeate_time` datetime NULL COMMENT '修改时间' AFTER `create_time`;
ALTER TABLE `cc_type`
    MODIFY COLUMN `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST;
ALTER TABLE `cc_user`
    MODIFY COLUMN `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST;

### 更新用户字段
ALTER TABLE `cc_user`
    CHANGE COLUMN `user_icon` `avatar` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像' AFTER `username`;