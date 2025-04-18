ALTER TABLE `cc_type`
    CHANGE COLUMN `updeate_time` `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间' AFTER `create_time`;

## 移除时间线的更新时间字段
ALTER TABLE `cc_module` DROP COLUMN `date`;