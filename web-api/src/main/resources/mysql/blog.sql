/*
 Navicat Premium Data Transfer

 Source Server         : miku
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 8.138.16.124:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 10/04/2025 11:35:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_blog
-- ----------------------------
DROP TABLE IF EXISTS `cc_blog`;
CREATE TABLE `cc_blog`  (
                            `bid` int(11) NOT NULL AUTO_INCREMENT,
                            `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `picture` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                            `is_valid` int(11) NULL DEFAULT NULL,
                            `create_date` date NULL DEFAULT NULL,
                            `version` int(11) NULL DEFAULT NULL,
                            `tid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `visit` int(11) NULL DEFAULT NULL,
                            `simple_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `uid` int(11) NULL DEFAULT NULL COMMENT '哪个用户发布的博客',
                            `state` int(11) NULL DEFAULT NULL COMMENT '0保存 1发布',
                            PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_code
-- ----------------------------
DROP TABLE IF EXISTS `cc_code`;
CREATE TABLE `cc_code`  (
                            `id` int(11) NOT NULL,
                            `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cc_feedback
-- ----------------------------
DROP TABLE IF EXISTS `cc_feedback`;
CREATE TABLE `cc_feedback`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `avator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                                `datetime` datetime NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_friendlink
-- ----------------------------
DROP TABLE IF EXISTS `cc_friendlink`;
CREATE TABLE `cc_friendlink`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `web_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `web_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `web_descript` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                                  `web_time` date NULL DEFAULT NULL,
                                  `web_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                  `web_access` int(11) NOT NULL COMMENT '0为为审核通过 1为审核通过',
                                  `web_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_img
-- ----------------------------
DROP TABLE IF EXISTS `cc_img`;
CREATE TABLE `cc_img`  (
                           `id` int(11) NOT NULL COMMENT '主键',
                           `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                           `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片类型',
                           `update_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_module
-- ----------------------------
DROP TABLE IF EXISTS `cc_module`;
CREATE TABLE `cc_module`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `module_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `module_type` int(11) NULL DEFAULT NULL COMMENT '0表示没有开发的 ，  1 表示开发完成的',
                              `date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块上线的时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_review
-- ----------------------------
DROP TABLE IF EXISTS `cc_review`;
CREATE TABLE `cc_review`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
                              `reply` int(11) NULL DEFAULT NULL COMMENT '0表示评论 1 表示回复',
                              `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                              `likes` int(11) NULL DEFAULT NULL,
                              `username` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `bid` int(11) NULL DEFAULT NULL COMMENT '博客id',
                              `rid` int(11) NULL DEFAULT NULL COMMENT '回复的是哪个评论的id',
                              `usericon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 403 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_type
-- ----------------------------
DROP TABLE IF EXISTS `cc_type`;
CREATE TABLE `cc_type`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cc_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_user`;
CREATE TABLE `cc_user`  (
                            `uid` int(11) NOT NULL AUTO_INCREMENT,
                            `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `user_icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `create_time` date NULL DEFAULT NULL,
                            `user_type` int(11) NULL DEFAULT NULL COMMENT '1 会员 0 普通人',
                            `is_valid` int(11) NULL DEFAULT NULL COMMENT '0 不合法 1 合法',
                            `version` int(11) NULL DEFAULT NULL,
                            `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `about` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人签名',
                            `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `ban` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1正常 0是禁用',
                            PRIMARY KEY (`uid`) USING BTREE,
                            UNIQUE INDEX `single`(`username`, `email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
