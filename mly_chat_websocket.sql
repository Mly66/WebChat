/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : mly_chat_websocket

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 17/01/2025 23:07:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mly_chat_user
-- ----------------------------
DROP TABLE IF EXISTS `mly_chat_user`;
CREATE TABLE `mly_chat_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `user_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户状态(在线、离线)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mly_chat_user
-- ----------------------------
INSERT INTO `mly_chat_user` VALUES (1, 'admin', '清风故人来', 'mly', '', '');
INSERT INTO `mly_chat_user` VALUES (2, 'mly', '梨花不等故人来', 'mly', '', '');
INSERT INTO `mly_chat_user` VALUES (3, 'wwwjwwwj', '微我无酒', 'mly', '', '');
INSERT INTO `mly_chat_user` VALUES (4, '1811512', '1811512', 'mly', '', '');
INSERT INTO `mly_chat_user` VALUES (5, '178260', '178260', 'mly', '', '');
INSERT INTO `mly_chat_user` VALUES (6, 'nbnbb', 'nbnbb', 'mly', '', '');

-- ----------------------------
-- Table structure for mly_friend
-- ----------------------------
DROP TABLE IF EXISTS `mly_friend`;
CREATE TABLE `mly_friend`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主动加的人的用户名',
  `to_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '被加的人的用户名',
  `is_friend` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是好友（0：否  1：是  2: 申请中）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mly_friend
-- ----------------------------
INSERT INTO `mly_friend` VALUES (1, 'admin', 'mly', 0);
INSERT INTO `mly_friend` VALUES (2, 'wwwjwwwj', 'mly', 0);

-- ----------------------------
-- Table structure for mly_private_message
-- ----------------------------
DROP TABLE IF EXISTS `mly_private_message`;
CREATE TABLE `mly_private_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发消息者',
  `to_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接收消息者',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 315 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mly_private_message
-- ----------------------------
INSERT INTO `mly_private_message` VALUES (315, 'mly', 'admin', 'mly 发送：是发送到发');
INSERT INTO `mly_private_message` VALUES (316, 'admin', 'mly', 'admin 发送：对的');

SET FOREIGN_KEY_CHECKS = 1;
