DROP DATABASE IF EXISTS `d_home`;
CREATE DATABASE IF NOT EXISTS d_home DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use d_home;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` varchar(36) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'normal',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('03251ac0-60af-4f1f-b7bd-6a24364d548e', '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', 'BOSS', '12345678901', 'super', '2017-06-10 08:15:02', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_admin` VALUES ('08834ebb-4b31-11e7-a833-d017c205bc97', '456@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', 'normal', '12345678901', 'normal', '2017-06-08 09:46:50', '2017-06-08 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` varchar(36) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `plot_name` varchar(100) DEFAULT NULL,
  `headIcon` varchar(500) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('369fc96b-4d74-11e7-9e73-d017c205bc97', '10086@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '壹', '12345678901', '朝阳区', 'uploads/20557ad73ef99f67.jpg!zmm', '北京市朝阳区', '2017-06-10 08:33:43', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_customer` VALUES ('aaf03f93-4a97-11e7-844f-d017c205bc97', '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '零', '12345678901', '赣州', 'uploads/下载.jpg', '赣州', '2017-06-10 08:34:04', '2017-06-10 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` varchar(36) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `principal` varchar(10) NOT NULL COMMENT '负责人',
  `logo` varchar(500) DEFAULT 'logo.jsp',
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `open_date` timestamp NULL DEFAULT NULL,
  `longitude` float DEFAULT '114.948',
  `latitude` float DEFAULT '25.8044',
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `checked` varchar(1) DEFAULT 'N',
  `checked_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1d02c50c-4a95-11e7-844f-d017c205bc97', '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '雅居装饰', '老周', 'uploads/u=1225223081,4272081876&fm=23&gp=0.jpg', '赣州技师', '12345678901', '12345678901', '2016-09-09 00:00:00', '114.948', '25.8044', '暂无简介', '2017-06-06 00:00:00', 'Y', '2017-06-06 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('2877ce8f-4c2a-11e7-9e73-d017c205bc97', '786@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', '金石建筑', '李平', 'uploads/12.jpg', null, '13855555556', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('342d1c14-4d73-11e7-9e73-d017c205bc97', '6666@666.com', '4QrcOUm6Wau+VuBX8g+IPg==', '132', '王栈', 'uploads/12.jpg', null, '12345678902', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('5082f832-4bee-11e7-9e73-d017c205bc97', '789@789.com', '4QrcOUm6Wau+VuBX8g+IPg==', '东易日盛', '123', 'uploads/u=1037321670,217821298&fm=23&gp=0.jpg', null, '12641456789', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('900ac3a7-4d73-11e7-9e73-d017c205bc97', '134@134.com', '4QrcOUm6Wau+VuBX8g+IPg==', '134', '夏雨荷', 'uploads/12.jpg', null, '12345678902', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('a29b2312-4bee-11e7-9e73-d017c205bc97', '147@147.com', '4QrcOUm6Wau+VuBX8g+IPg==', '海楠装饰', '王总', 'uploads/u=2214932661,3772235279&fm=23&gp=0.jpg', null, '12345671231', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('a66426bd-4bec-11e7-9e73-d017c205bc97', '456@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', '昊星装修', '张昊星', 'uploads/44558cf8c21000eb.jpg!zmm', null, '12345645612', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('aff084d6-4bed-11e7-9e73-d017c205bc97', 'dilidili@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', '嘀哩嘀哩', '黄', 'uploads/7885747ecf9a61d4.jpg!zmm', null, '12345612312', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('b2f9a49d-4d73-11e7-9e73-d017c205bc97', '10086@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '10086', '10086', 'uploads/12.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('ca31a4df-4d72-11e7-9e73-d017c205bc97', '678@678.com', '4QrcOUm6Wau+VuBX8g+IPg==', '12', '海天', 'uploads/12.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('caba1f5f-4d73-11e7-9e73-d017c205bc97', '10010@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '10010', '10010', 'uploads/12.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('d30439d6-4d71-11e7-9e73-d017c205bc97', '1151757358@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', '1', '张天', 'uploads/12.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('d8fec49f-4bee-11e7-9e73-d017c205bc97', '456123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '寰宇精装', '裕太', 'uploads/12957328c0eb1f59.jpg!zmm', null, '12345678912', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('dbd2c488-4bec-11e7-9e73-d017c205bc97', '123@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', '精工装修', '海', 'uploads/12.jpg', null, '12345678945', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'Y', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_company` VALUES ('f5e354d0-4d72-11e7-9e73-d017c205bc97', '345@345.com', '4QrcOUm6Wau+VuBX8g+IPg==', '123', '李雷', 'uploads/12.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-10 00:00:00', 'Y', '2017-06-10 00:00:00', '2017-06-10 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_company_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_activity`;
CREATE TABLE `t_company_activity` (
  `id` varchar(36) NOT NULL,
  `company_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `t_company_activity_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company_activity
-- ----------------------------
INSERT INTO `t_company_activity` VALUES ('22eb9f7f-4a9b-11e7-844f-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '五一优惠', 'uploads/timg (9).jpg', '暂无', '2017-06-06 00:00:00', 'Y');
INSERT INTO `t_company_activity` VALUES ('718127fe-4cf1-11e7-9e73-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '5折特惠', 'uploads/timg (12).jpg', '超低价，全场五折', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company_activity` VALUES ('968e8973-4cf1-11e7-9e73-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '环保装修', 'uploads/u=614585012,4182371301&fm=23&gp=0.jpg', '安全环保', '2017-06-09 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_company_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_case`;
CREATE TABLE `t_company_case` (
  `id` varchar(36) NOT NULL,
  `company_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `plot_name` varchar(100) NOT NULL,
  `style` varchar(20) NOT NULL,
  `image_1` varchar(500) DEFAULT NULL,
  `image_2` varchar(500) DEFAULT NULL,
  `image_3` varchar(500) DEFAULT NULL,
  `image_4` varchar(500) DEFAULT NULL,
  `image_5` varchar(500) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `t_company_case_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company_case
-- ----------------------------
INSERT INTO `t_company_case` VALUES ('423d9721-4cf1-11e7-9e73-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '新中式设计', '海天小区', '新中式', 'uploads/898575cf59267fff.jpg!l', 'uploads/33593508646bf68.jpg!l', 'uploads/5125935062798fb1.jpg!l', 'uploads/9165935050d80a6a.jpg!l', 'uploads/735593508653cb19.jpg!l', '花园洋房', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_company_case` VALUES ('48630e8b-4cf2-11e7-9e73-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '复古风向', '青海小区', '复古', 'uploads/33593508646bf68.jpg!l', 'uploads/727575cf5d6bb319.jpg!l', 'uploads/36759350519a9ee2.jpg!l', 'uploads/225575cf53505a5f.jpg', 'uploads/5125935062798fb1.jpg!l', '', '2017-06-09 17:04:58', 'Y');
INSERT INTO `t_company_case` VALUES ('94ddc78e-4cf2-11e7-9e73-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '古典徽派', '宜家小区', '徽派建筑', 'uploads/9165935050d80a6a.jpg!l', 'uploads/83593502c68619b.jpg!l', 'uploads/36759350519a9ee2.jpg!l', 'uploads/timg.jpg', 'uploads/735593508653cb19.jpg!l', '徽派', '2017-06-09 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_company_case_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_case_col`;
CREATE TABLE `t_company_case_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `case_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `case_id` (`case_id`),
  CONSTRAINT `t_company_case_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_company_case_col_ibfk_2` FOREIGN KEY (`case_id`) REFERENCES `t_company_case` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company_case_col
-- ----------------------------

-- ----------------------------
-- Table structure for `t_company_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_company_col`;
CREATE TABLE `t_company_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `company_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `t_company_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_company_col_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company_col
-- ----------------------------
INSERT INTO `t_company_col` VALUES ('c69e6876-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_company_col` VALUES ('d2ac4830-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '5082f832-4bee-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_company_col` VALUES ('d59d6206-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'a29b2312-4bee-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_company_col` VALUES ('d82d39c4-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'a66426bd-4bec-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_company_col` VALUES ('dadfe882-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'aff084d6-4bed-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_company_col` VALUES ('ddb0dac5-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'd8fec49f-4bee-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_designer`
-- ----------------------------
DROP TABLE IF EXISTS `t_designer`;
CREATE TABLE `t_designer` (
  `id` varchar(36) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `headicon` varchar(500) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `experience` varchar(50) NOT NULL,
  `style` varchar(100) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `checked` varchar(1) DEFAULT 'N',
  `checked_time` timestamp NULL DEFAULT NULL,
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_designer
-- ----------------------------
INSERT INTO `t_designer` VALUES ('030c3fb3-4a92-11e7-844f-d017c205bc97', '123456@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '美居', 'uploads/561572b066950f33.jpg!zmm', '12345612341', '赣州', 'workexp4', '客家', '暂无简介', '2017-06-08 10:08:22', 'Y', '2017-06-06 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('3446940e-4c24-11e7-9e73-d017c205bc97', '555@555.com', '4QrcOUm6Wau+VuBX8g+IPg==', '李三', 'uploads/u=4057712840,1591128406&fm=23&gp=0.jpg', '12345678945', null, 'workexp3', null, '暂无简介', '2017-06-09 17:31:04', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('3cfe67ad-4c23-11e7-9e73-d017c205bc97', '1234@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '李源', 'uploads/715584677a26e5c6.jpg!zmm', '12345678912', null, 'workexp3', null, '暂无简介', '2017-06-09 17:20:48', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('573860cc-4c23-11e7-9e73-d017c205bc97', '456@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', '王占元', 'uploads/18957deacf1d7b99.jpg!zmm', '12345678923', null, 'workexp4', null, '暂无简介', '2017-06-09 17:23:34', 'Y', '2017-06-08 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('85da255f-4a90-11e7-844f-d017c205bc97', '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '芬格尔', 'uploads/u=3924388119,1540588045&fm=23&gp=0.jpg', '12345678901', '赣州', 'workexp3', '时尚', '暂无简介', '2017-06-09 11:08:54', 'Y', '2017-06-06 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('895fd841-4c23-11e7-9e73-d017c205bc97', '159@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '王涛', 'uploads/12.jpg', '12345612312', null, 'workexp3', null, '暂无简介', '2017-06-08 16:27:17', 'Y', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('9e5da7e0-4c23-11e7-9e73-d017c205bc97', '789@789.com', '4QrcOUm6Wau+VuBX8g+IPg==', '张伟', 'uploads/12.jpg', '45645645612', null, 'workexp1', null, '暂无简介', '2017-06-08 16:27:18', 'Y', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('ab607005-4c24-11e7-9e73-d017c205bc97', '555@123.com', '0KhUAXBxDu6gqt1AFV5NxQ==', '李阳', 'uploads/12.jpg', '13879735517', null, 'workexp1', null, '暂无简介', '2017-06-08 00:00:00', 'N', null, '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('c049d87f-4c24-11e7-9e73-d017c205bc97', '666@123.com', 'QgGd1H21ru0gkTmPrep8Cw==', '董占峰 ', 'uploads/12.jpg', '17607974221', null, 'workexp1', null, '暂无简介', '2017-06-08 00:00:00', 'N', null, '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('c0a4491a-4c23-11e7-9e73-d017c205bc97', '333@333.com', '4QrcOUm6Wau+VuBX8g+IPg==', '王岩', 'uploads/12.jpg', '12312312323', null, 'workexp2', null, '暂无简介', '2017-06-08 16:27:19', 'Y', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('ce07ddfc-4c24-11e7-9e73-d017c205bc97', '777@123.com', '2f7cu/WUdZgdhwS435ndwA==', '任魁', 'uploads/12.jpg', '18779730419', null, 'workexp1', null, '暂无简介', '2017-06-08 00:00:00', 'N', null, '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('db254970-4c24-11e7-9e73-d017c205bc97', '888@123.com', 'awHtqlvVJ8vzWSC3oyGEMg==', '任东铭', 'uploads/12.jpg', '13179730419', null, 'workexp1', null, '暂无简介', '2017-06-08 00:00:00', 'N', null, '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer` VALUES ('f1ff2c00-4c23-11e7-9e73-d017c205bc97', '666@666.com', '4QrcOUm6Wau+VuBX8g+IPg==', '雷军', 'uploads/12.jpg', '13866666666', null, 'workexp3', null, '暂无简介', '2017-06-08 16:27:20', 'Y', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_designer_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_designer_case`;
CREATE TABLE `t_designer_case` (
  `id` varchar(36) NOT NULL,
  `designer_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `plot_name` varchar(100) NOT NULL,
  `style` varchar(20) NOT NULL,
  `image_1` varchar(500) DEFAULT NULL,
  `image_2` varchar(500) DEFAULT NULL,
  `image_3` varchar(500) DEFAULT NULL,
  `image_4` varchar(500) DEFAULT NULL,
  `image_5` varchar(500) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `designer_id` (`designer_id`),
  CONSTRAINT `t_designer_case_ibfk_1` FOREIGN KEY (`designer_id`) REFERENCES `t_designer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_designer_case
-- ----------------------------
INSERT INTO `t_designer_case` VALUES ('26e5bf90-4cf5-11e7-9e73-d017c205bc97', '3cfe67ad-4c23-11e7-9e73-d017c205bc97', '全聚设计', '金鼎小区', '简约时尚', 'uploads/77559350ab2c9361.jpg!l', 'uploads/727575cf5d6bb319.jpg!l', 'uploads/505575cf56050420.jpg', 'uploads/timg.jpg', 'uploads/5125935062798fb1.jpg!l', '', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_designer_case` VALUES ('9251c319-4bef-11e7-9e73-d017c205bc97', '030c3fb3-4a92-11e7-844f-d017c205bc97', '客家风情', '金龙小区', '客家', 'uploads/505575cf56050420.jpg', 'uploads/77559350ab2c9361.jpg!l', 'uploads/225575cf53505a5f.jpg', 'uploads/105575cf4c79dd63.jpg', 'uploads/83593502c68619b.jpg!l', '典雅时尚', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_designer_case` VALUES ('b4f504cd-4a92-11e7-844f-d017c205bc97', '85da255f-4a90-11e7-844f-d017c205bc97', '国际潮流', '时尚小区', '时尚', 'uploads/TB2_sFighlmpuFjSZPfXXc9iXXa_!!0-saturn_solar.jpg_220x220.jpg', 'uploads/TB2IvbXjHJmpuFjSZFwXXaE4VXa_!!0-saturn_solar.jpg_220x220.jpg', 'uploads/TB2XWADlFXXXXbkXXXXXXXXXXXX_!!0-saturn_solar.jpg_220x220.jpg', 'uploads/TB2IifWl9FjpuFjSspbXXXagVXa_!!0-saturn_solar.jpg_220x220.jpg', 'uploads/timg.jpg', '', '2017-06-06 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_designer_case_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_designer_case_col`;
CREATE TABLE `t_designer_case_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `case_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `case_id` (`case_id`),
  CONSTRAINT `t_designer_case_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_designer_case_col_ibfk_2` FOREIGN KEY (`case_id`) REFERENCES `t_designer_case` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_designer_case_col
-- ----------------------------
INSERT INTO `t_designer_case_col` VALUES ('fcacb6a0-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'b4f504cd-4a92-11e7-844f-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_designer_case_col` VALUES ('ff7ee5c8-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '9251c319-4bef-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_designer_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_designer_col`;
CREATE TABLE `t_designer_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `designer_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `designer_id` (`designer_id`),
  CONSTRAINT `t_designer_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_designer_col_ibfk_2` FOREIGN KEY (`designer_id`) REFERENCES `t_designer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_designer_col
-- ----------------------------
INSERT INTO `t_designer_col` VALUES ('df68a2c3-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '85da255f-4a90-11e7-844f-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_designer_col` VALUES ('e42ae40b-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '3cfe67ad-4c23-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_designer_col` VALUES ('e6d7a6e9-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '3446940e-4c24-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_designer_col` VALUES ('eb7e6ed1-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '573860cc-4c23-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_designer_col` VALUES ('f9417a70-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '030c3fb3-4a92-11e7-844f-d017c205bc97', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_supply`
-- ----------------------------
DROP TABLE IF EXISTS `t_supply`;
CREATE TABLE `t_supply` (
  `id` varchar(36) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  `principal` varchar(10) NOT NULL,
  `logo` varchar(500) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `open_date` timestamp NULL DEFAULT NULL,
  `longitude` float DEFAULT '114.948',
  `latitude` float DEFAULT '25.8044',
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `checked` varchar(1) DEFAULT 'N',
  `checked_time` timestamp NULL DEFAULT NULL,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supply
-- ----------------------------
INSERT INTO `t_supply` VALUES ('11098982-4b2f-11e7-a833-d017c205bc97', '156@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '美丽人生', '王美丽', 'uploads/828589ade543f3fb.jpg!zmm', null, '45612314561', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('79411980-4b2f-11e7-a833-d017c205bc97', '111@111.com', '4QrcOUm6Wau+VuBX8g+IPg==', '搜狗建材', '汪汪', 'uploads/715584677a26e5c6.jpg!zmm', null, '12345678923', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('7ba87615-4c27-11e7-9e73-d017c205bc97', '111@123.com', '5JJ0UWonSH+JSulWFm63pA==', '泰如建材', '王泰如', 'uploads/12.jpg', null, '13679730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('927e2cb1-4c27-11e7-9e73-d017c205bc97', '222@123.com', 'Y555lh8sxVOvmA1w49TcfA==', '源美建材', '刘源美', 'uploads/12.jpg', null, '13179730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('a26ab4d0-4b2d-11e7-a833-d017c205bc97', '456@456.com', '4QrcOUm6Wau+VuBX8g+IPg==', '诚信五金', '钟二', 'uploads/u=4192471023,376719331&fm=23&gp=0.jpg', null, '12345678901', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('a4bb7db0-4c27-11e7-9e73-d017c205bc97', '333@123.com', 'a08uKF9xf6npmPtGZsjpLA==', '信和建材', '张信和', 'uploads/12.jpg', null, '13279730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('ab4847d8-4b2f-11e7-a833-d017c205bc97', '360@360.com', '4QrcOUm6Wau+VuBX8g+IPg==', '360防盗门', '360', 'uploads/下载 (2).jpg', null, '36036066666', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('b58a2cf8-4c27-11e7-9e73-d017c205bc97', '444@123.com', 'cUHcpfqDvDf8nqLE2NBiAA==', '贸发建材', '王贸发', 'uploads/12.jpg', null, '13379730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('b8b75389-4b2e-11e7-a833-d017c205bc97', '456@4561.com', '4QrcOUm6Wau+VuBX8g+IPg==', '宜家建材', '宜家', 'uploads/u=823553489,3633557074&fm=23&gp=0.jpg', null, '12345678912', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('c4fc9bf5-4b2d-11e7-a833-d017c205bc97', '123456@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '诚信建材', '钟诚信', 'uploads/u=1089096407,3428858305&fm=23&gp=0.jpg', null, '12345678923', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-09 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('c9b6b7c7-4c27-11e7-9e73-d017c205bc97', '555@123.com', '0KhUAXBxDu6gqt1AFV5NxQ==', '丰汇建材', '李丰汇', 'uploads/12.jpg', null, '13479730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('da661892-4c27-11e7-9e73-d017c205bc97', '666@123.com', 'QgGd1H21ru0gkTmPrep8Cw==', '安广建材', '李安广', 'uploads/12.jpg', null, '13579730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('f16cb9f6-4c27-11e7-9e73-d017c205bc97', '777@123.com', '2f7cu/WUdZgdhwS435ndwA==', '长圣建材', '张长圣', 'uploads/12.jpg', null, '13679730419', null, null, '114.948', '25.8044', '暂无简介', '2017-06-08 00:00:00', 'N', '2017-06-08 00:00:00', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('f9e1bcb3-4a92-11e7-844f-d017c205bc97', '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '万户', '老李', 'uploads/下载 (2).jpg', '赣州', '12345678901', '10010110011', '2017-02-02 00:00:00', '114.948', '25.8044', '', '2017-06-06 00:00:00', 'Y', '2017-06-06 00:00:00', '2017-06-10 00:00:00', 'Y');
INSERT INTO `t_supply` VALUES ('fd83176b-4b2d-11e7-a833-d017c205bc97', '456@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', '彩云灯具', '刘彩云', 'uploads/12.jpg', null, '12345678921', null, null, '114.948', '25.8044', '暂无简介', '2017-06-07 00:00:00', 'Y', '2017-06-07 00:00:00', '2017-06-07 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_supply_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_supply_activity`;
CREATE TABLE `t_supply_activity` (
  `id` varchar(36) NOT NULL,
  `supply_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `supply_id` (`supply_id`),
  CONSTRAINT `t_supply_activity_ibfk_1` FOREIGN KEY (`supply_id`) REFERENCES `t_supply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supply_activity
-- ----------------------------
INSERT INTO `t_supply_activity` VALUES ('6aa712e8-4be1-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '5折优惠', 'uploads/timg (12).jpg', '特价五折，仅限今天', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('9fae839f-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '焕新家', 'uploads/u=2251125968,686607600&fm=23&gp=0.jpg', '让家焕然一新', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('bab92009-4be1-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '元旦特惠', 'uploads/timg (2).jpg', '庆元旦，迎新年', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('befc589a-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '砸金蛋', 'uploads/u=3162816196,334511140&fm=23&gp=0.jpg', '万元豪礼等你拿', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('d80e41ab-4be1-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '诚信315', 'uploads/u=386538746,3403245847&fm=23&gp=0.jpg', '3.15特价', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('e038aae5-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '12周年庆', 'uploads/u=2478179522,556489858&fm=23&gp=0.jpg', '特价优惠', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_supply_activity` VALUES ('fe9e39d6-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '双11特价', 'uploads/u=3845365675,2216008346&fm=23&gp=0.jpg', '建材9折', '2017-06-08 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_supply_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_supply_col`;
CREATE TABLE `t_supply_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `supply_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `supply_id` (`supply_id`),
  CONSTRAINT `t_supply_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_supply_col_ibfk_2` FOREIGN KEY (`supply_id`) REFERENCES `t_supply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supply_col
-- ----------------------------
INSERT INTO `t_supply_col` VALUES ('e2474786-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '11098982-4b2f-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_supply_col` VALUES ('e63d25cc-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '79411980-4b2f-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_supply_col` VALUES ('e924ad51-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'a26ab4d0-4b2d-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_supply_col` VALUES ('eba02a0f-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'ab4847d8-4b2f-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_supply_col` VALUES ('ee36585a-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'b8b75389-4b2e-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_supply_col` VALUES ('f0be6e4f-4ced-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'c4fc9bf5-4b2d-11e7-a833-d017c205bc97', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` varchar(36) NOT NULL,
  `supply_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `sale_price` float NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `des` varchar(500) DEFAULT '暂无简介',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`id`),
  KEY `supply_id` (`supply_id`),
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`supply_id`) REFERENCES `t_supply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('10c1c904-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '实木板', '100', '60', 'uploads/u=275385618,1814491078&fm=23&gp=0.jpg', '特价实木板', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_product` VALUES ('30c6e675-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '玻璃窗', '360', '360', 'uploads/u=3238362053,2231325167&fm=23&gp=0.jpg', '', '2017-06-10 08:36:00', 'N');
INSERT INTO `t_product` VALUES ('44886f1b-4be1-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '铁丝', '120', '120', 'uploads/u=202175140,2638194159&fm=23&gp=0.jpg', '精钢炼制', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_product` VALUES ('4a674042-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '陶瓷', '230', '230', 'uploads/TB29Bq0eS0jpuFjy0FlXXc0bpXa_!!0-saturn_solar.jpg_220x220.jpg', '量大从优', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_product` VALUES ('66aeec63-4be2-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '水管', '120', '120', 'uploads/TB2M12nhR0kpuFjSsppXXcGTXXa_!!0-saturn_solar.jpg_220x220.jpg', '安全，国标', '2017-06-08 00:00:00', 'Y');
INSERT INTO `t_product` VALUES ('840bccb7-4a99-11e7-844f-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '木板', '123', '123', 'uploads/u=329875235,1860764174&fm=23&gp=0.jpg', '木板', '2017-06-06 00:00:00', 'Y');
INSERT INTO `t_product` VALUES ('f816a7c2-4be1-11e7-9e73-d017c205bc97', 'f9e1bcb3-4a92-11e7-844f-d017c205bc97', '石膏板', '230', '150', 'uploads/u=71337421,3148651103&fm=23&gp=0.jpg', '墙面装修', '2017-06-08 00:00:00', 'Y');

-- ----------------------------
-- Table structure for `t_product_col`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_col`;
CREATE TABLE `t_product_col` (
  `id` varchar(36) NOT NULL,
  `customer_id` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_product_col_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_product_col_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_col
-- ----------------------------
INSERT INTO `t_product_col` VALUES ('0a61feff-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '30c6e675-4be2-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_product_col` VALUES ('11fe9b59-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '44886f1b-4be1-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_product_col` VALUES ('1456ff3f-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '44886f1b-4be1-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');
INSERT INTO `t_product_col` VALUES ('c80cb4b4-4cee-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '10c1c904-4be2-11e7-9e73-d017c205bc97', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `payed` varchar(1) NOT NULL DEFAULT 'N',
  `cost` float NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('7a1f2fde-4d75-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'Y', '270', '2017-06-10 08:40:38');
INSERT INTO `t_order` VALUES ('f8f694f7-4cf0-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', 'Y', '1680', '2017-06-09 16:52:21');

-- ----------------------------
-- Table structure for `t_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `id` varchar(36) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `price` float NOT NULL,
  `total` int(11) NOT NULL,
  `cost` float NOT NULL,
  `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `t_order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES ('7a404129-4d75-11e7-9e73-d017c205bc97', '7a1f2fde-4d75-11e7-9e73-d017c205bc97', '66aeec63-4be2-11e7-9e73-d017c205bc97', '120', '1', '120', '2017-06-10 00:00:00');
INSERT INTO `t_order_detail` VALUES ('7a48931d-4d75-11e7-9e73-d017c205bc97', '7a1f2fde-4d75-11e7-9e73-d017c205bc97', 'f816a7c2-4be1-11e7-9e73-d017c205bc97', '150', '1', '150', '2017-06-10 00:00:00');
INSERT INTO `t_order_detail` VALUES ('f90ab2f4-4cf0-11e7-9e73-d017c205bc97', 'f8f694f7-4cf0-11e7-9e73-d017c205bc97', '30c6e675-4be2-11e7-9e73-d017c205bc97', '360', '4', '1440', '2017-06-09 00:00:00');
INSERT INTO `t_order_detail` VALUES ('f91465fb-4cf0-11e7-9e73-d017c205bc97', 'f8f694f7-4cf0-11e7-9e73-d017c205bc97', '44886f1b-4be1-11e7-9e73-d017c205bc97', '120', '2', '240', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_appointment`
-- ----------------------------
DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE `t_appointment` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `company_id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `plot_name` varchar(100) NOT NULL,
  `area` float NOT NULL,
  `way` enum('half','whole') NOT NULL DEFAULT 'whole',
  `budget` varchar(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `t_appointment_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_appointment
-- ----------------------------
INSERT INTO `t_appointment` VALUES ('e21212a9-4cf2-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '1d02c50c-4a95-11e7-844f-d017c205bc97', '雅居装饰', '零', '12345678901', '赣州', '123', 'whole', '5-8万', '2017-06-09 00:00:00');

-- ----------------------------
-- Table structure for `t_cart`
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `total` int(11) NOT NULL,
  `cost` float NOT NULL,
  `creat_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `t_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cart
-- ----------------------------
INSERT INTO `t_cart` VALUES ('973c04f3-4d74-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '10c1c904-4be2-11e7-9e73-d017c205bc97', '4', '240', '2017-06-10 00:00:00');
INSERT INTO `t_cart` VALUES ('a6595d6e-4d74-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '30c6e675-4be2-11e7-9e73-d017c205bc97', '1', '360', '2017-06-10 00:00:00');
INSERT INTO `t_cart` VALUES ('ab51075f-4d74-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '44886f1b-4be1-11e7-9e73-d017c205bc97', '3', '360', '2017-06-10 00:00:00');
INSERT INTO `t_cart` VALUES ('ae7c4aea-4d74-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '4a674042-4be2-11e7-9e73-d017c205bc97', '1', '230', '2017-06-10 00:00:00');
INSERT INTO `t_cart` VALUES ('b6ee41d6-4d74-11e7-9e73-d017c205bc97', 'aaf03f93-4a97-11e7-844f-d017c205bc97', '840bccb7-4a99-11e7-844f-d017c205bc97', '1', '123', '2017-06-10 00:00:00');
