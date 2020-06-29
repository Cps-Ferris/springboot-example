/*
 Navicat Premium Data Transfer

 Source Server         : MySQL本机
 Source Server Type    : MySQL
 Source Server Version : 50519
 Source Host           : localhost:3306
 Source Schema         : springboot_example

 Target Server Type    : MySQL
 Target Server Version : 50519
 File Encoding         : 65001

 Date: 29/06/2020 20:19:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `gender` int(255) NULL DEFAULT NULL COMMENT '性别(0女；1男)',
  `is_disable` int(255) NULL DEFAULT NULL COMMENT '是否禁用(0启用；1禁用)',
  `is_delete` int(255) NULL DEFAULT NULL COMMENT '是否逻辑删除(0不删除；1删除)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '甲', '123456', 0, 0, 0, '2020-06-29 00:00:00', '2020-06-29 00:00:00');
INSERT INTO `user` VALUES (2, '乙', '123456', 1, 1, 0, '2020-05-10 00:00:00', '2020-05-10 00:00:00');
INSERT INTO `user` VALUES (3, '丙', '123456', 0, 0, 0, '2020-04-18 00:00:00', '2020-04-18 00:00:00');
INSERT INTO `user` VALUES (4, '丁', '123456', 1, 1, 0, '2020-02-20 00:00:00', '2020-02-20 00:00:00');
INSERT INTO `user` VALUES (5, '甲乙', '123456', 0, 0, 0, '2020-01-10 00:00:00', '2020-01-10 00:00:00');
INSERT INTO `user` VALUES (6, '丙丁', '123456', 1, 1, 0, '2020-06-06 00:00:00', '2020-06-06 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
