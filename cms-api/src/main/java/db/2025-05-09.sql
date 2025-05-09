### 修改标明错误
ALTER TABLE `cc_blog_type`
    CHANGE COLUMN `create_tiem` `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `tag_id`;