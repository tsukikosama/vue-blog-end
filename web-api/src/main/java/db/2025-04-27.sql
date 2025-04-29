## 博客新增点赞按钮
ALTER TABLE `cc_blog`
    ADD COLUMN `likes` int NULL DEFAULT 0 COMMENT '点赞数' AFTER `update_time`;
## 评论表修改
ALTER TABLE `cc_review`
    MODIFY COLUMN `user_id` bigint NULL DEFAULT NULL COMMENT '评论用户id' AFTER `reply_id`,
    ADD COLUMN `reply_user_id` bigint NULL COMMENT '回复的用户id' AFTER `review_type`;

## 新增博客点赞表
CREATE TABLE `cc_blog_like`
(
    `id`          bigint   NOT NULL COMMENT '组件',
    `user_id`     bigint   NOT NULL COMMENT '用户id',
    `blog_id`     bigint   NOT NULL COMMENT '点赞博客id',
    `create_time` datetime NOT NULL COMMENT '点赞时间',
    PRIMARY KEY (`id`)
);
## 设置组件自动增长
ALTER TABLE `cc_blog_like`
    MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组件' FIRST;
## 移除博客表中的点赞数量
ALTER TABLE `blogtest`.`cc_blog`
    DROP COLUMN `likes`;