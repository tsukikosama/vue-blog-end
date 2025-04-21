ALTER TABLE `cc_review`
ADD COLUMN `review_type` tinyint NOT NULL COMMENT '评论的类型' AFTER `user_id`;

ALTER TABLE `cc_review` DROP COLUMN `reply`;