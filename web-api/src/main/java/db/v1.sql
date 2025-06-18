-- 2025-06-17 系统用户新增状态
ALTER TABLE `sys_user`
    ADD COLUMN `status` tinyint NOT NULL DEFAULT 1 COMMENT '0禁用 1启用' AFTER `dept_id`;