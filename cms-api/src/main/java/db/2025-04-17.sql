ALTER TABLE `blog`.`cc_type`
    CHANGE COLUMN `updeate_time` `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间' AFTER `create_time`;