/*
 Navicat Premium Data Transfer

 Source Server         : test1
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : blogtest

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 13/06/2025 18:09:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_angry_pig
-- ----------------------------
DROP TABLE IF EXISTS `cc_angry_pig`;
CREATE TABLE `cc_angry_pig`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `cause` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '原因',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` datetime NULL DEFAULT NULL COMMENT '更新人',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `type` tinyint NOT NULL COMMENT '事件类型',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_angry_pig
-- ----------------------------

-- ----------------------------
-- Table structure for cc_blog
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog`;
CREATE TABLE `cc_blog`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标题',
  `picture` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '内容',
  `is_valid` int NOT NULL COMMENT '是否有效',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本',
  `visit` int NOT NULL DEFAULT 0 COMMENT '流量数量',
  `simple_title` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '简化标题',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `state` tinyint NOT NULL COMMENT '0保存 1发布',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_blog
-- ----------------------------

-- ----------------------------
-- Table structure for cc_blog_like
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_like`;
CREATE TABLE `cc_blog_like`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组件',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `blog_id` bigint NOT NULL COMMENT '点赞博客id',
  `create_time` datetime NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_blog_like
-- ----------------------------
INSERT INTO `cc_blog_like` VALUES (2, 8, 13, '2025-04-29 17:03:00');

-- ----------------------------
-- Table structure for cc_blog_type
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog_type`;
CREATE TABLE `cc_blog_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` bigint NOT NULL COMMENT '博客id',
  `tag_id` bigint NOT NULL COMMENT '标签id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_blog_type
-- ----------------------------
INSERT INTO `cc_blog_type` VALUES (1, 72, 8, '2025-05-08 14:56:27', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (2, 72, 5, '2025-05-08 14:56:27', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (3, 72, 2, '2025-05-08 14:56:27', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (4, 72, 6, '2025-05-08 14:56:27', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (5, 72, 3, '2025-05-08 14:56:27', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (6, 14, 3, '2025-05-08 15:21:25', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (7, 14, 2, '2025-05-08 15:21:25', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (8, 14, 6, '2025-05-08 15:21:25', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (9, 14, 5, '2025-05-08 15:21:25', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (10, 13, 6, '2025-05-08 15:26:53', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (11, 13, 5, '2025-05-08 15:26:53', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (12, 13, 3, '2025-05-08 15:26:53', 0, NULL, NULL);
INSERT INTO `cc_blog_type` VALUES (13, 13, 7, '2025-05-08 15:26:53', 0, NULL, NULL);

-- ----------------------------
-- Table structure for cc_feedback
-- ----------------------------
DROP TABLE IF EXISTS `cc_feedback`;
CREATE TABLE `cc_feedback`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avator` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像',
  `message` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '消息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for cc_friendlink
-- ----------------------------
DROP TABLE IF EXISTS `cc_friendlink`;
CREATE TABLE `cc_friendlink`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `web_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '网站链接',
  `web_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '网站名字',
  `web_descript` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '网站描述',
  `web_time` date NULL DEFAULT NULL COMMENT '添加时间',
  `web_img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '网站图片',
  `web_access` int NOT NULL COMMENT '0为为审核通过 1为审核通过',
  `web_email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_friendlink
-- ----------------------------

-- ----------------------------
-- Table structure for cc_img
-- ----------------------------
DROP TABLE IF EXISTS `cc_img`;
CREATE TABLE `cc_img`  (
  `id` int NOT NULL COMMENT '主键',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片类型',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_img
-- ----------------------------

-- ----------------------------
-- Table structure for cc_life_note
-- ----------------------------
DROP TABLE IF EXISTS `cc_life_note`;
CREATE TABLE `cc_life_note`  (
  `id` bigint NOT NULL COMMENT '主键',
  `origin_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '原图地址',
  `web_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网页压缩图地址',
  `user_id` bigint NOT NULL COMMENT '发布者id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cc_life_note
-- ----------------------------

-- ----------------------------
-- Table structure for cc_module
-- ----------------------------
DROP TABLE IF EXISTS `cc_module`;
CREATE TABLE `cc_module`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '模块名字',
  `module_status` tinyint NULL DEFAULT NULL COMMENT '0表示没有开发的 ，  1 表示开发完成的',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_module
-- ----------------------------

-- ----------------------------
-- Table structure for cc_review
-- ----------------------------
DROP TABLE IF EXISTS `cc_review`;
CREATE TABLE `cc_review`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '评论内容',
  `likes` int NULL DEFAULT NULL COMMENT '点赞数',
  `blog_id` bigint NULL DEFAULT NULL COMMENT '博客id',
  `reply_id` bigint NULL DEFAULT NULL COMMENT '回复的是哪个评论的id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '评论用户id',
  `review_type` tinyint NOT NULL COMMENT '评论的类型 0是主评论1是子评论',
  `reply_user_id` bigint NULL DEFAULT NULL COMMENT '回复的用户id',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 407 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_review
-- ----------------------------

-- ----------------------------
-- Table structure for cc_tag
-- ----------------------------
DROP TABLE IF EXISTS `cc_tag`;
CREATE TABLE `cc_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_tag
-- ----------------------------
INSERT INTO `cc_tag` VALUES (2, 'java', NULL, NULL);
INSERT INTO `cc_tag` VALUES (3, 'c', NULL, NULL);
INSERT INTO `cc_tag` VALUES (5, 'sppring', NULL, NULL);
INSERT INTO `cc_tag` VALUES (6, 'redis', NULL, NULL);
INSERT INTO `cc_tag` VALUES (7, 'pringboot', NULL, NULL);
INSERT INTO `cc_tag` VALUES (8, 'vuex', NULL, NULL);
INSERT INTO `cc_tag` VALUES (9, 'c++', NULL, NULL);
INSERT INTO `cc_tag` VALUES (10, 'js', NULL, NULL);
INSERT INTO `cc_tag` VALUES (11, 'html', NULL, NULL);
INSERT INTO `cc_tag` VALUES (12, 'css', NULL, NULL);
INSERT INTO `cc_tag` VALUES (13, 'test', NULL, NULL);
INSERT INTO `cc_tag` VALUES (14, '日常', NULL, NULL);

-- ----------------------------
-- Table structure for cc_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_user`;
CREATE TABLE `cc_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录账号',
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '邮箱',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '个人签名',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `single`(`username`, `email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cc_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `parent_id` bigint NOT NULL COMMENT '上级部门id',
  `ancestors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '祖级列表',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL COMMENT '组件',
  `code_id` bigint NOT NULL COMMENT '类型id',
  `code_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '更新用户',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value`  (
  `id` bigint NOT NULL COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名字',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_user` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', NULL, 'q', NULL, 1, 1, '2025-06-13 14:46:47', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
