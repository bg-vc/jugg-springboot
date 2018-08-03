/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 28/07/2018 11:42:24
*/

CREATE DATABASE IF NOT EXISTS springboot DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE springboot;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
BEGIN;
INSERT INTO `t_users` VALUES (1, 'jugg123', 'hao123', '2018-07-26 22:05:46', '2018-07-26 22:05:46');
INSERT INTO `t_users` VALUES (2, 'vince', 'hao234', '2018-07-26 22:16:49', '2018-07-26 22:16:49');
INSERT INTO `t_users` VALUES (3, 'vince', '2223', '2018-07-26 22:25:00', '2018-07-26 22:25:02');
INSERT INTO `t_users` VALUES (4, '123vince', 'sss', '2018-07-26 22:25:16', '2018-07-26 22:25:21');
INSERT INTO `t_users` VALUES (5, 'ssvince22', 'sss33', '2018-07-26 22:25:33', '2018-07-26 22:25:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
