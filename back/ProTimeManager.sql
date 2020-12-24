/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ProTimeManager

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 24/12/2020 17:09:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `commenter_id` int DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (1, '我是你爹', 1, '2020-12-22 00:00:00');
INSERT INTO `comment` VALUES (2, '我是你爹', 2, '2020-12-23 12:00:00');
INSERT INTO `comment` VALUES (3, '我是你爹', 3, '2020-12-23 17:00:00');
INSERT INTO `comment` VALUES (4, '我是你爹', 1, '2020-12-23 00:24:50');
INSERT INTO `comment` VALUES (5, 'Really Hard to write', 1, '2020-12-23 05:35:25');
INSERT INTO `comment` VALUES (6, 'I see it !', 1, '2020-12-24 02:37:55');
COMMIT;

-- ----------------------------
-- Table structure for request
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `id` int NOT NULL,
  `request_info` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `request_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int NOT NULL,
  `sender_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `review_content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `feedback_content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `review_time` datetime DEFAULT NULL,
  `feedback_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for scheme
-- ----------------------------
DROP TABLE IF EXISTS `scheme`;
CREATE TABLE `scheme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `scheme_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tag_list` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scheme
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tag_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tag_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` VALUES (53, 'Ready-2-Go', 'U r ready to start, right?', 'yellow');
INSERT INTO `tag` VALUES (54, 'Complete', 'Completed Task', 'green');
INSERT INTO `tag` VALUES (55, 'Danger', 'Dangerous Task', 'red');
INSERT INTO `tag` VALUES (56, 'Removed', 'You are being removed or quit by yourself', 'black');
INSERT INTO `tag` VALUES (57, 'Ongoing', 'You are working on this task', 'blue');
COMMIT;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_list` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `states_list` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `need_feedback` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `feedback_id` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `comments_id` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `scheme_id` int DEFAULT NULL,
  `task_title` varchar(255) DEFAULT NULL,
  `task_sender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES (1, '1_999_2_3_5_7', 'Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ,Today I want to visit peach ', '3_3_3_3_3_3', '2020-12-14 22:08:47', NULL, NULL, '1_2_3_4_5', NULL, 'Visit Peach', 'yoshi');
INSERT INTO `task` VALUES (2, '1_2_3_4_5', 'Today I want to solve problem1', '3_2_1_0_3', '2020-12-29 22:08:50', NULL, NULL, NULL, NULL, 'solve problem1', 'yoshi');
INSERT INTO `task` VALUES (3, '1_2_3_4_5', 'Today I want to solve problem2', '3_2_0_3_1', '2020-11-02 22:08:53', NULL, NULL, '2_3', NULL, 'solve problem2', 'admin');
INSERT INTO `task` VALUES (4, '1_2_3_4_5', 'Today I want to solve problem3', '2_3_0_3_2', '2020-11-24 22:08:58', NULL, NULL, NULL, NULL, 'solve problem3', 'yoshi');
INSERT INTO `task` VALUES (5, '2_3_4_5', 'Today I want to solve problem4', '2_3_3_3', '2020-12-13 22:09:05', NULL, NULL, '2_3', NULL, 'solve problem4', 'task');
INSERT INTO `task` VALUES (6, '1', 'Submit PA-0 before 12-25', '0', '2020-12-25 00:07:51', NULL, NULL, NULL, NULL, 'Submit PA-0', 'Teacher Li');
INSERT INTO `task` VALUES (7, '1', 'Submit PA-1 before 12-27', '1', '2020-12-27 00:08:00', NULL, NULL, NULL, NULL, 'Submit PA-1', 'Teacher Li');
INSERT INTO `task` VALUES (8, '1', 'Submit PA-2 before 12-29', '0', '2020-12-29 00:08:06', NULL, NULL, NULL, NULL, 'Submit PA-2', 'Teacher Li');
INSERT INTO `task` VALUES (9, '1', 'Submit PA-3 before 12-31', '0', '2020-12-31 01:08:11', NULL, NULL, NULL, NULL, 'Submit PA-3', 'Teacher Li');
INSERT INTO `task` VALUES (10, '1', 'Submit Cloud Computing', '0', '2020-12-26 00:00:00', NULL, NULL, NULL, NULL, 'Submit Cloud Computing', 'Teacher Zhang');
INSERT INTO `task` VALUES (11, '1', 'Analyzing Fault Data', '1', '2020-12-28 00:00:00', NULL, NULL, NULL, NULL, 'Analysis', 'Leader Zhang');
INSERT INTO `task` VALUES (12, '1', 'C++ Programming Test 1', '1', '2021-01-01 00:00:00', NULL, NULL, NULL, NULL, 'C++ episode I', 'Shen Yang');
INSERT INTO `task` VALUES (13, '1', 'C++ Programming Test 2', '1', '2021-01-01 00:00:00', NULL, NULL, NULL, NULL, 'C++ episode II', 'Shen Yang');
INSERT INTO `task` VALUES (14, '1', 'C++ Programming Test 3', '1', '2021-01-01 00:00:00', NULL, NULL, NULL, NULL, 'C++ episode III', 'Shen Yang');
INSERT INTO `task` VALUES (15, '1', 'C++ Programming Test 4', '1', '2021-01-01 00:00:00', NULL, NULL, NULL, NULL, 'C++ episode IV', 'Shen Yang');
INSERT INTO `task` VALUES (16, '999', 'Today I want to play with mario', '2', '2020-12-20 22:08:44', NULL, NULL, NULL, NULL, 'Play Pipe', 'admin');
INSERT INTO `task` VALUES (17, '1', 'Description', '0', '2020-12-28 00:00:00', NULL, NULL, NULL, NULL, 'Title', 'admin');
INSERT INTO `task` VALUES (18, '1', '就让我加进去吧', '0', '2020-12-25 00:00:00', NULL, NULL, NULL, NULL, '大哥我都用中文了', 'admin');
INSERT INTO `task` VALUES (19, '1', 'test', '0', '2020-12-24 00:00:00', NULL, NULL, NULL, NULL, 'test', 'admin');
INSERT INTO `task` VALUES (20, '1', 'Play It', '0', '2020-12-26 00:00:00', NULL, NULL, NULL, NULL, 'Cyberpunk2077', 'admin');
INSERT INTO `task` VALUES (21, NULL, 'No Body Here', NULL, '2020-12-31 00:00:00', NULL, NULL, NULL, NULL, 'Empty', 'Empty');
INSERT INTO `task` VALUES (22, '1_2_3_4_5_6', 'No chance of surviving', '0_0_0_0_0_0', '2020-12-29 12:23:34', NULL, NULL, NULL, NULL, 'Task', '1');
INSERT INTO `task` VALUES (23, '1_2_3_4_5_6', 'by admin', '0_0_0_0_0_0', '2020-12-25 00:00:00', NULL, NULL, NULL, NULL, 'by admin', 'admin');
INSERT INTO `task` VALUES (24, '1_4', 'admin, can U see it?', '0_0', '2020-12-30 00:00:00', NULL, NULL, '6', NULL, 'Hey admin Hello', 'xys');
COMMIT;

-- ----------------------------
-- Table structure for task_group
-- ----------------------------
DROP TABLE IF EXISTS `task_group`;
CREATE TABLE `task_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_list` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `member_list` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `task_list` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  `group_type` int DEFAULT NULL,
  `link_code` int NOT NULL,
  `group_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_group
-- ----------------------------
BEGIN;
INSERT INTO `task_group` VALUES (1, NULL, '1_2_3_4_5_6', '1_2_3_4_5_21_22_23', 'Game Group', 1, 0, 6677883, 'Let us play game together');
INSERT INTO `task_group` VALUES (2, NULL, '4_5', NULL, 'Ocaml Group', 1, 0, 1123123, 'Let learn Ocaml');
INSERT INTO `task_group` VALUES (3, NULL, '1', '6_7_8_9', 'PA Lab', 1, NULL, 124124, 'Do U know how a virtual machine work? just come and write a your own VM');
INSERT INTO `task_group` VALUES (4, NULL, '1', '10', 'Cloud Lab', 1, NULL, 5623523, 'Hadoop, Spark , specialize on Big Data');
INSERT INTO `task_group` VALUES (5, NULL, '1_4', '11_24', 'Data Lab', 1, NULL, 1555, 'Basic ML Application');
INSERT INTO `task_group` VALUES (6, NULL, '_1', '12_13_14_15', 'Programming Lab', 1, NULL, 125215, 'Learn to use C++, an amazing tool');
INSERT INTO `task_group` VALUES (7, ' ', '1', ' ', 'Test Group', 1, NULL, 6666, 'Test Group');
INSERT INTO `task_group` VALUES (8, NULL, '1', NULL, 'New New', 1, NULL, 1, 'It is a brand new group');
COMMIT;

-- ----------------------------
-- Table structure for time_class
-- ----------------------------
DROP TABLE IF EXISTS `time_class`;
CREATE TABLE `time_class` (
  `id` int NOT NULL,
  `student_list` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `teacher_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time_class
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` int DEFAULT NULL,
  `tasks` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_groups` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `classes` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `request_id` int DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 0, '1_2_3_4_6_7_8_9_10_11_12_13_14_15_22_23_24', '1_3_4_5_6_7_7_7_7_8', NULL, NULL, '管理员');
INSERT INTO `user` VALUES (2, 'mario', 'admin', 0, '1_2_3_4_5_22_23', '1', NULL, NULL, '马里奥');
INSERT INTO `user` VALUES (3, 'peach', 'admin', 0, '1_2_3_4_5_22_23', '1', NULL, NULL, '桃子公主');
INSERT INTO `user` VALUES (4, 'xys', 'admin', 0, '2_3_4_5_22_23_24', '1_5', NULL, NULL, '许羽晟');
INSERT INTO `user` VALUES (5, 'xysPro', 'admin', 0, '1_2_3_4_5_22_23', '1', NULL, NULL, '许羽晟Pro');
INSERT INTO `user` VALUES (6, 'Ducker', 'admin', 0, '1_2_3_4_5_22_23', '1_2_3_4_5', NULL, NULL, '鸭子王');
INSERT INTO `user` VALUES (7, 'man', 'admin', 0, '1', '1', NULL, NULL, 'Man');
COMMIT;

-- ----------------------------
-- Table structure for user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `URI` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_avatar
-- ----------------------------
BEGIN;
INSERT INTO `user_avatar` VALUES (1, '1avatar.png');
INSERT INTO `user_avatar` VALUES (2, '2avatar.png');
INSERT INTO `user_avatar` VALUES (3, '3avatar.png');
INSERT INTO `user_avatar` VALUES (4, '4avatar.png');
INSERT INTO `user_avatar` VALUES (5, '5avatar.png');
INSERT INTO `user_avatar` VALUES (6, '6avatar.png');
INSERT INTO `user_avatar` VALUES (7, '7avatar.png');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
