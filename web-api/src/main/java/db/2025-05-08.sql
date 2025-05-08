## 添加博客标签关联表
CREATE TABLE `cc_blog_type`
(
    `id`          bigint        NOT NULL COMMENT '主键',
    `blog_id`     bigint        NOT NULL COMMENT '博客id',
    `tag_id`      bigint        NOT NULL COMMENT '帖子id',
    `create_tiem` datetime(255) NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
);
## 自动增长
ALTER TABLE `cc_blog_type`
    MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST;
### 删除无用字段
ALTER TABLE `cc_blog`
    DROP COLUMN `tag_id`;