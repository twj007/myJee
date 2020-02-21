/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-02-21 15:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_activity
-- ----------------------------
DROP TABLE IF EXISTS `sys_activity`;
CREATE TABLE `sys_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `total_money` decimal(10,0) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_activity
-- ----------------------------
INSERT INTO `sys_activity` VALUES ('4', '2019-11-20 10:16:09', '2020-03-14 10:16:19', '120000', '2020-01-20 10:16:32');
INSERT INTO `sys_activity` VALUES ('5', '2020-01-01 10:16:36', '2020-08-25 10:16:40', '200000', '2020-01-20 10:16:55');
INSERT INTO `sys_activity` VALUES ('6', '2018-07-12 10:16:58', '2020-01-10 10:17:18', '1000000', '2020-01-20 10:17:30');
INSERT INTO `sys_activity` VALUES ('7', '2018-07-01 00:00:00', '2020-03-01 00:00:00', '200000', '2020-01-20 14:36:53');
INSERT INTO `sys_activity` VALUES ('8', '2018-07-01 00:00:00', '2020-03-01 00:00:00', '200000', '2020-02-21 14:09:00');
INSERT INTO `sys_activity` VALUES ('9', '2020-02-21 14:34:25', '2020-02-22 14:34:29', '10000', '2020-02-21 14:34:40');

-- ----------------------------
-- Table structure for sys_activity_temp
-- ----------------------------
DROP TABLE IF EXISTS `sys_activity_temp`;
CREATE TABLE `sys_activity_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `money` decimal(30,2) NOT NULL,
  `from_day` int(11) NOT NULL,
  `to_day` int(11) NOT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=815 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_activity_temp
-- ----------------------------
INSERT INTO `sys_activity_temp` VALUES ('740', '4', '10', '11379.28', '20', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('741', '4', '11', '32068.88', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('742', '4', '0', '32068.88', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('743', '4', '1', '29999.92', '1', '29', '2020');
INSERT INTO `sys_activity_temp` VALUES ('744', '4', '2', '14483.04', '1', '14', '2020');
INSERT INTO `sys_activity_temp` VALUES ('745', '5', '0', '26050.54', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('746', '5', '1', '24369.86', '1', '29', '2020');
INSERT INTO `sys_activity_temp` VALUES ('747', '5', '2', '26050.54', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('748', '5', '3', '25210.20', '1', '30', '2020');
INSERT INTO `sys_activity_temp` VALUES ('749', '5', '4', '26050.54', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('750', '5', '5', '25210.20', '1', '30', '2020');
INSERT INTO `sys_activity_temp` VALUES ('751', '5', '6', '26050.54', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('752', '5', '7', '21007.58', '1', '25', '2020');
INSERT INTO `sys_activity_temp` VALUES ('753', '6', '6', '36496.40', '12', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('754', '6', '7', '56569.42', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('755', '6', '8', '54744.60', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('756', '6', '9', '56569.42', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('757', '6', '10', '54744.60', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('758', '6', '11', '56569.42', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('759', '6', '0', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('760', '6', '1', '51094.96', '1', '28', '2019');
INSERT INTO `sys_activity_temp` VALUES ('761', '6', '2', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('762', '6', '3', '54744.60', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('763', '6', '4', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('764', '6', '5', '54744.60', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('765', '6', '6', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('766', '6', '7', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('767', '6', '8', '54744.60', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('768', '6', '9', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('769', '6', '10', '54744.60', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('770', '6', '11', '56569.42', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('771', '6', '0', '18246.84', '1', '10', '2020');
INSERT INTO `sys_activity_temp` VALUES ('772', '7', '6', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('773', '7', '7', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('774', '7', '8', '9836.10', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('775', '7', '9', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('776', '7', '10', '9836.10', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('777', '7', '11', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('778', '7', '0', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('779', '7', '1', '9180.36', '1', '28', '2019');
INSERT INTO `sys_activity_temp` VALUES ('780', '7', '2', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('781', '7', '3', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('782', '7', '4', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('783', '7', '5', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('784', '7', '6', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('785', '7', '7', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('786', '7', '8', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('787', '7', '9', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('788', '7', '10', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('789', '7', '11', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('790', '7', '0', '10163.97', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('791', '7', '1', '9508.23', '1', '29', '2020');
INSERT INTO `sys_activity_temp` VALUES ('792', '7', '2', '327.17', '1', '1', '2020');
INSERT INTO `sys_activity_temp` VALUES ('793', '8', '6', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('794', '8', '7', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('795', '8', '8', '9836.10', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('796', '8', '9', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('797', '8', '10', '9836.10', '1', '30', '2018');
INSERT INTO `sys_activity_temp` VALUES ('798', '8', '11', '10163.97', '1', '31', '2018');
INSERT INTO `sys_activity_temp` VALUES ('799', '8', '0', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('800', '8', '1', '9180.36', '1', '28', '2019');
INSERT INTO `sys_activity_temp` VALUES ('801', '8', '2', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('802', '8', '3', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('803', '8', '4', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('804', '8', '5', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('805', '8', '6', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('806', '8', '7', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('807', '8', '8', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('808', '8', '9', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('809', '8', '10', '9836.10', '1', '30', '2019');
INSERT INTO `sys_activity_temp` VALUES ('810', '8', '11', '10163.97', '1', '31', '2019');
INSERT INTO `sys_activity_temp` VALUES ('811', '8', '0', '10163.97', '1', '31', '2020');
INSERT INTO `sys_activity_temp` VALUES ('812', '8', '1', '9508.23', '1', '29', '2020');
INSERT INTO `sys_activity_temp` VALUES ('813', '8', '2', '327.17', '1', '1', '2020');
INSERT INTO `sys_activity_temp` VALUES ('814', '9', '1', '10000.00', '21', '22', '2020');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_type` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', 'fruit', '水果', null, '2019-08-30 15:45:19', null, 'Y');
INSERT INTO `sys_dict` VALUES ('2', 'meat', '肉', null, '2019-08-30 15:45:35', null, 'Y');
INSERT INTO `sys_dict` VALUES ('3', 'json', 'fast json', 'fast json desc', '2019-08-30 17:05:27', null, 'Y');
INSERT INTO `sys_dict` VALUES ('4', 'json', 'fast json', 'fast json desc', '2019-08-30 17:07:27', null, 'Y');
INSERT INTO `sys_dict` VALUES ('5', 'json', 'fast json', 'fast json desc', '2019-08-30 17:09:21', null, 'Y');
INSERT INTO `sys_dict` VALUES ('6', 'json', 'fast json', 'fast json desc', '2019-08-30 17:10:15', null, 'Y');
INSERT INTO `sys_dict` VALUES ('9', 'json', 'fast json', 'fast json desc', '2019-08-30 17:21:16', null, 'Y');
INSERT INTO `sys_dict` VALUES ('10', 'json', 'fast json', 'fast json desc', '2019-08-30 17:27:04', null, 'Y');
INSERT INTO `sys_dict` VALUES ('23', '测试名', '测试值', ' 1', '2019-09-02 17:34:33', null, 'Y');
INSERT INTO `sys_dict` VALUES ('24', '测试名2', '测试值2', ' 2', '2019-09-02 17:34:33', null, 'Y');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `key_type` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', '1', 'app', 'iphone', '2019-08-30 15:45:57', null, null, 'Y');
INSERT INTO `sys_dict_detail` VALUES ('2', '2', 'beef', 'diss', '2019-08-30 15:46:21', null, null, 'Y');
INSERT INTO `sys_dict_detail` VALUES ('3', '10', 'content-Type', 'application/json', '2019-08-30 17:27:04', null, null, 'Y');
INSERT INTO `sys_dict_detail` VALUES ('4', '23', '字典名1', '字典值1', '2019-09-02 17:34:33', null, ' ', 'Y');
INSERT INTO `sys_dict_detail` VALUES ('5', '23', '字典名2', '字典值2', '2019-09-02 17:34:33', null, ' 3', 'Y');
INSERT INTO `sys_dict_detail` VALUES ('6', '24', '字典名1', '字典值1', '2019-09-02 17:34:33', null, ' ', 'Y');
INSERT INTO `sys_dict_detail` VALUES ('7', '24', '字典名2', '字典值2', '2019-09-02 17:34:33', null, ' 3', 'Y');

-- ----------------------------
-- Table structure for sys_form
-- ----------------------------
DROP TABLE IF EXISTS `sys_form`;
CREATE TABLE `sys_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '表单名称',
  `description` varchar(255) DEFAULT NULL COMMENT '表单描述',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_form
-- ----------------------------
INSERT INTO `sys_form` VALUES ('1', '测试表单', '用于测试', 'C', '2019-09-20 17:30:03');

-- ----------------------------
-- Table structure for sys_form_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_form_attr`;
CREATE TABLE `sys_form_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `attr_desc` varchar(255) NOT NULL COMMENT '属性名称（通过这个动态生成实体？）',
  `attr_desc_cn` varchar(255) NOT NULL COMMENT '中文名',
  `create_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_form_attr
-- ----------------------------
INSERT INTO `sys_form_attr` VALUES ('5', '1', 'u_0', '测试0', '2019-09-20 17:30:03', null, 'add', '0');
INSERT INTO `sys_form_attr` VALUES ('6', '1', 'u_1', '测试1', '2019-09-20 17:30:03', null, 'add', '1');
INSERT INTO `sys_form_attr` VALUES ('7', '1', 'u_2', '测试2', '2019-09-20 17:30:03', null, 'add', '2');
INSERT INTO `sys_form_attr` VALUES ('8', '1', 'u_3', '测试3', '2019-09-20 17:30:03', null, 'add', '3');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '用户管理', '0', '2019-08-29 09:52:32', null, null, null, null, 'D', null, null);
INSERT INTO `sys_menu` VALUES ('2', '用户管理2', '0', '2019-08-29 10:31:27', null, null, null, null, 'D', null, null);
INSERT INTO `sys_menu` VALUES ('3', '用户管理3', '0', '2019-08-29 10:31:41', null, null, null, null, 'D', null, null);
INSERT INTO `sys_menu` VALUES ('4', ' 用户管理4', '1', '2019-08-29 10:31:58', null, null, null, null, null, null, null);
INSERT INTO `sys_menu` VALUES ('7', '用户管理新增', '0', '2019-09-02 15:10:27', null, null, null, '新增菜单', 'M', '1', null);

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text,
  `file_url` varchar(255) DEFAULT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Y',
  `create_date` datetime NOT NULL,
  `modify_date` datetime DEFAULT NULL,
  `_count` int(11) DEFAULT '0',
  `author_Id` int(255) NOT NULL,
  `_type` varchar(10) NOT NULL,
  `send_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES ('1', '标题111111', 'updatetest', null, 'Y', '2020-01-17 09:56:06', null, '0', '1', '1', '1');
INSERT INTO `sys_message` VALUES ('2', 'test2', 'test22222', null, 'Y', '2020-01-03 10:56:42', null, '0', '2', '1', 'U');
INSERT INTO `sys_message` VALUES ('7', 'updatetest', 'updatetest', null, 'Y', '2020-01-17 09:55:57', null, '0', '1', '1', '1');
INSERT INTO `sys_message` VALUES ('8', 'yyyy', 'kkkkk', null, 'Y', '2020-01-03 14:43:32', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('12', 'yyyy', 'kkkkk', null, 'Y', '2020-01-17 16:54:03', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('13', 'yyyy', 'kkkkk', null, 'Y', '2020-01-19 09:16:55', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('14', 'yyyy', 'kkkkk', null, 'Y', '2020-01-19 09:50:16', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('15', 'yyyy', 'kkkkk', null, 'Y', '2020-01-19 09:51:22', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('16', 'yyyy', 'kkkkk', null, 'Y', '2020-01-19 09:52:21', null, null, '3', '1', 'U');
INSERT INTO `sys_message` VALUES ('17', 'yyyy', 'kkkkk', null, 'Y', '2020-01-19 10:24:50', null, null, '3', '1', 'U');

-- ----------------------------
-- Table structure for sys_message_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_message_record`;
CREATE TABLE `sys_message_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `send_status` varchar(10) NOT NULL DEFAULT 'N',
  `send_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_message_record
-- ----------------------------
INSERT INTO `sys_message_record` VALUES ('1', '1', '2', 'N', null);
INSERT INTO `sys_message_record` VALUES ('2', '7', null, 'N', null);
INSERT INTO `sys_message_record` VALUES ('3', '7', null, 'N', null);
INSERT INTO `sys_message_record` VALUES ('4', '7', null, 'N', null);
INSERT INTO `sys_message_record` VALUES ('5', '8', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('6', '8', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('7', '8', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('8', '12', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('9', '12', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('10', '12', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('11', '13', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('12', '13', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('13', '13', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('14', '14', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('15', '14', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('16', '14', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('17', '15', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('18', '15', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('19', '15', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('20', '16', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('21', '16', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('22', '16', '3', 'N', null);
INSERT INTO `sys_message_record` VALUES ('23', '17', '1', 'N', null);
INSERT INTO `sys_message_record` VALUES ('24', '17', '4', 'N', null);
INSERT INTO `sys_message_record` VALUES ('25', '17', '3', 'N', null);

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `line_name` varchar(6000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('6', '文件', '列0,列1,列2,列3,列4,列5,列6,列7', '2019-09-19 16:36:20');
INSERT INTO `sys_module` VALUES ('7', '文件', '列0,列1,列2,列3,列4', '2019-09-19 16:41:23');
INSERT INTO `sys_module` VALUES ('8', '文件', '列0', '2019-09-19 16:42:25');
INSERT INTO `sys_module` VALUES ('9', '文件', '列0,列1,列2,列3,列4', '2019-09-19 16:44:10');

-- ----------------------------
-- Table structure for sys_module_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_module_detail`;
CREATE TABLE `sys_module_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL,
  `line_content` varchar(6000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_module_detail
-- ----------------------------
INSERT INTO `sys_module_detail` VALUES ('250', '6', '0列值0,0列值1,0列值2,0列值3,0列值4,0列值5,0列值6,0列值7');
INSERT INTO `sys_module_detail` VALUES ('251', '6', '1列值0,1列值1,1列值2,1列值3,1列值4,1列值5,1列值6,1列值7');
INSERT INTO `sys_module_detail` VALUES ('252', '6', '2列值0,2列值1,2列值2,2列值3,2列值4,2列值5,2列值6,2列值7');
INSERT INTO `sys_module_detail` VALUES ('253', '6', '3列值0,3列值1,3列值2,3列值3,3列值4,3列值5,3列值6,3列值7');
INSERT INTO `sys_module_detail` VALUES ('254', '6', '4列值0,4列值1,4列值2,4列值3,4列值4,4列值5,4列值6,4列值7');
INSERT INTO `sys_module_detail` VALUES ('255', '6', '5列值0,5列值1,5列值2,5列值3,5列值4,5列值5,5列值6,5列值7');
INSERT INTO `sys_module_detail` VALUES ('256', '6', '6列值0,6列值1,6列值2,6列值3,6列值4,6列值5,6列值6,6列值7');
INSERT INTO `sys_module_detail` VALUES ('257', '6', '7列值0,7列值1,7列值2,7列值3,7列值4,7列值5,7列值6,7列值7');
INSERT INTO `sys_module_detail` VALUES ('258', '6', '8列值0,8列值1,8列值2,8列值3,8列值4,8列值5,8列值6,8列值7');
INSERT INTO `sys_module_detail` VALUES ('259', '6', '9列值0,9列值1,9列值2,9列值3,9列值4,9列值5,9列值6,9列值7');
INSERT INTO `sys_module_detail` VALUES ('260', '6', '10列值0,10列值1,10列值2,10列值3,10列值4,10列值5,10列值6,10列值7');
INSERT INTO `sys_module_detail` VALUES ('261', '6', '11列值0,11列值1,11列值2,11列值3,11列值4,11列值5,11列值6,11列值7');
INSERT INTO `sys_module_detail` VALUES ('262', '6', '12列值0,12列值1,12列值2,12列值3,12列值4,12列值5,12列值6,12列值7');
INSERT INTO `sys_module_detail` VALUES ('263', '6', '13列值0,13列值1,13列值2,13列值3,13列值4,13列值5,13列值6,13列值7');
INSERT INTO `sys_module_detail` VALUES ('264', '6', '14列值0,14列值1,14列值2,14列值3,14列值4,14列值5,14列值6,14列值7');
INSERT INTO `sys_module_detail` VALUES ('265', '6', '15列值0,15列值1,15列值2,15列值3,15列值4,15列值5,15列值6,15列值7');
INSERT INTO `sys_module_detail` VALUES ('266', '6', '16列值0,16列值1,16列值2,16列值3,16列值4,16列值5,16列值6,16列值7');
INSERT INTO `sys_module_detail` VALUES ('267', '6', '17列值0,17列值1,17列值2,17列值3,17列值4,17列值5,17列值6,17列值7');
INSERT INTO `sys_module_detail` VALUES ('268', '6', '18列值0,18列值1,18列值2,18列值3,18列值4,18列值5,18列值6,18列值7');
INSERT INTO `sys_module_detail` VALUES ('269', '6', '19列值0,19列值1,19列值2,19列值3,19列值4,19列值5,19列值6,19列值7');
INSERT INTO `sys_module_detail` VALUES ('270', '6', '20列值0,20列值1,20列值2,20列值3,20列值4,20列值5,20列值6,20列值7');
INSERT INTO `sys_module_detail` VALUES ('271', '6', '21列值0,21列值1,21列值2,21列值3,21列值4,21列值5,21列值6,21列值7');
INSERT INTO `sys_module_detail` VALUES ('272', '6', '22列值0,22列值1,22列值2,22列值3,22列值4,22列值5,22列值6,22列值7');
INSERT INTO `sys_module_detail` VALUES ('273', '6', '23列值0,23列值1,23列值2,23列值3,23列值4,23列值5,23列值6,23列值7');
INSERT INTO `sys_module_detail` VALUES ('274', '6', '24列值0,24列值1,24列值2,24列值3,24列值4,24列值5,24列值6,24列值7');
INSERT INTO `sys_module_detail` VALUES ('275', '6', '25列值0,25列值1,25列值2,25列值3,25列值4,25列值5,25列值6,25列值7');
INSERT INTO `sys_module_detail` VALUES ('276', '6', '26列值0,26列值1,26列值2,26列值3,26列值4,26列值5,26列值6,26列值7');
INSERT INTO `sys_module_detail` VALUES ('277', '6', '27列值0,27列值1,27列值2,27列值3,27列值4,27列值5,27列值6,27列值7');
INSERT INTO `sys_module_detail` VALUES ('278', '6', '28列值0,28列值1,28列值2,28列值3,28列值4,28列值5,28列值6,28列值7');
INSERT INTO `sys_module_detail` VALUES ('279', '7', '0列值0,0列值1,0列值2,0列值3,0列值4');
INSERT INTO `sys_module_detail` VALUES ('280', '7', '1列值0,1列值1,1列值2,1列值3,1列值4');
INSERT INTO `sys_module_detail` VALUES ('281', '7', '2列值0,2列值1,2列值2,2列值3,2列值4');
INSERT INTO `sys_module_detail` VALUES ('282', '7', '3列值0,3列值1,3列值2,3列值3,3列值4');
INSERT INTO `sys_module_detail` VALUES ('283', '7', '4列值0,4列值1,4列值2,4列值3,4列值4');
INSERT INTO `sys_module_detail` VALUES ('284', '7', '5列值0,5列值1,5列值2,5列值3,5列值4');
INSERT INTO `sys_module_detail` VALUES ('285', '7', '6列值0,6列值1,6列值2,6列值3,6列值4');
INSERT INTO `sys_module_detail` VALUES ('286', '7', '7列值0,7列值1,7列值2,7列值3,7列值4');
INSERT INTO `sys_module_detail` VALUES ('287', '7', '8列值0,8列值1,8列值2,8列值3,8列值4');
INSERT INTO `sys_module_detail` VALUES ('288', '7', '9列值0,9列值1,9列值2,9列值3,9列值4');
INSERT INTO `sys_module_detail` VALUES ('289', '7', '10列值0,10列值1,10列值2,10列值3,10列值4');
INSERT INTO `sys_module_detail` VALUES ('290', '7', '11列值0,11列值1,11列值2,11列值3,11列值4');
INSERT INTO `sys_module_detail` VALUES ('291', '7', '12列值0,12列值1,12列值2,12列值3,12列值4');
INSERT INTO `sys_module_detail` VALUES ('292', '7', '13列值0,13列值1,13列值2,13列值3,13列值4');
INSERT INTO `sys_module_detail` VALUES ('293', '7', '14列值0,14列值1,14列值2,14列值3,14列值4');
INSERT INTO `sys_module_detail` VALUES ('294', '7', '15列值0,15列值1,15列值2,15列值3,15列值4');
INSERT INTO `sys_module_detail` VALUES ('295', '7', '16列值0,16列值1,16列值2,16列值3,16列值4');
INSERT INTO `sys_module_detail` VALUES ('296', '7', '17列值0,17列值1,17列值2,17列值3,17列值4');
INSERT INTO `sys_module_detail` VALUES ('297', '7', '18列值0,18列值1,18列值2,18列值3,18列值4');
INSERT INTO `sys_module_detail` VALUES ('298', '7', '19列值0,19列值1,19列值2,19列值3,19列值4');
INSERT INTO `sys_module_detail` VALUES ('299', '7', '20列值0,20列值1,20列值2,20列值3,20列值4');
INSERT INTO `sys_module_detail` VALUES ('300', '7', '21列值0,21列值1,21列值2,21列值3,21列值4');
INSERT INTO `sys_module_detail` VALUES ('301', '7', '22列值0,22列值1,22列值2,22列值3,22列值4');
INSERT INTO `sys_module_detail` VALUES ('302', '7', '23列值0,23列值1,23列值2,23列值3,23列值4');
INSERT INTO `sys_module_detail` VALUES ('303', '7', '24列值0,24列值1,24列值2,24列值3,24列值4');
INSERT INTO `sys_module_detail` VALUES ('304', '7', '25列值0,25列值1,25列值2,25列值3,25列值4');
INSERT INTO `sys_module_detail` VALUES ('305', '7', '26列值0,26列值1,26列值2,26列值3,26列值4');
INSERT INTO `sys_module_detail` VALUES ('306', '7', '27列值0,27列值1,27列值2,27列值3,27列值4');
INSERT INTO `sys_module_detail` VALUES ('307', '7', '28列值0,28列值1,28列值2,28列值3,28列值4');
INSERT INTO `sys_module_detail` VALUES ('308', '7', '29列值0,29列值1,29列值2,29列值3,29列值4');
INSERT INTO `sys_module_detail` VALUES ('309', '8', '0列值0');
INSERT INTO `sys_module_detail` VALUES ('310', '8', '1列值0');
INSERT INTO `sys_module_detail` VALUES ('311', '8', '2列值0');
INSERT INTO `sys_module_detail` VALUES ('312', '8', '3列值0');
INSERT INTO `sys_module_detail` VALUES ('313', '8', '4列值0');
INSERT INTO `sys_module_detail` VALUES ('314', '8', '5列值0');
INSERT INTO `sys_module_detail` VALUES ('315', '8', '6列值0');
INSERT INTO `sys_module_detail` VALUES ('316', '8', '7列值0');
INSERT INTO `sys_module_detail` VALUES ('317', '8', '8列值0');
INSERT INTO `sys_module_detail` VALUES ('318', '8', '9列值0');
INSERT INTO `sys_module_detail` VALUES ('319', '8', '10列值0');
INSERT INTO `sys_module_detail` VALUES ('320', '8', '11列值0');
INSERT INTO `sys_module_detail` VALUES ('321', '8', '12列值0');
INSERT INTO `sys_module_detail` VALUES ('322', '8', '13列值0');
INSERT INTO `sys_module_detail` VALUES ('323', '8', '14列值0');
INSERT INTO `sys_module_detail` VALUES ('324', '8', '15列值0');
INSERT INTO `sys_module_detail` VALUES ('325', '9', '0列值0,0列值1,0列值2,0列值3,0列值4');
INSERT INTO `sys_module_detail` VALUES ('326', '9', '1列值0,1列值1,1列值2,1列值3,1列值4');
INSERT INTO `sys_module_detail` VALUES ('327', '9', '2列值0,2列值1,2列值2,2列值3,2列值4');
INSERT INTO `sys_module_detail` VALUES ('328', '9', '3列值0,3列值1,3列值2,3列值3,3列值4');
INSERT INTO `sys_module_detail` VALUES ('329', '9', '4列值0,4列值1,4列值2,4列值3,4列值4');
INSERT INTO `sys_module_detail` VALUES ('330', '9', '5列值0,5列值1,5列值2,5列值3,5列值4');
INSERT INTO `sys_module_detail` VALUES ('331', '9', '6列值0,6列值1,6列值2,6列值3,6列值4');
INSERT INTO `sys_module_detail` VALUES ('332', '9', '7列值0,7列值1,7列值2,7列值3,7列值4');
INSERT INTO `sys_module_detail` VALUES ('333', '9', '8列值0,8列值1,8列值2,8列值3,8列值4');
INSERT INTO `sys_module_detail` VALUES ('334', '9', '9列值0,9列值1,9列值2,9列值3,9列值4');
INSERT INTO `sys_module_detail` VALUES ('335', '9', '10列值0,10列值1,10列值2,10列值3,10列值4');
INSERT INTO `sys_module_detail` VALUES ('336', '9', '11列值0,11列值1,11列值2,11列值3,11列值4');
INSERT INTO `sys_module_detail` VALUES ('337', '9', '12列值0,12列值1,12列值2,12列值3,12列值4');
INSERT INTO `sys_module_detail` VALUES ('338', '9', '13列值0,13列值1,13列值2,13列值3,13列值4');
INSERT INTO `sys_module_detail` VALUES ('339', '9', '14列值0,14列值1,14列值2,14列值3,14列值4');
INSERT INTO `sys_module_detail` VALUES ('340', '9', '15列值0,15列值1,15列值2,15列值3,15列值4');
INSERT INTO `sys_module_detail` VALUES ('341', '9', '16列值0,16列值1,16列值2,16列值3,16列值4');
INSERT INTO `sys_module_detail` VALUES ('342', '9', '17列值0,17列值1,17列值2,17列值3,17列值4');
INSERT INTO `sys_module_detail` VALUES ('343', '9', '18列值0,18列值1,18列值2,18列值3,18列值4');
INSERT INTO `sys_module_detail` VALUES ('344', '9', '19列值0,19列值1,19列值2,19列值3,19列值4');
INSERT INTO `sys_module_detail` VALUES ('345', '9', '20列值0,20列值1,20列值2,20列值3,20列值4');
INSERT INTO `sys_module_detail` VALUES ('346', '9', '21列值0,21列值1,21列值2,21列值3,21列值4');
INSERT INTO `sys_module_detail` VALUES ('347', '9', '22列值0,22列值1,22列值2,22列值3,22列值4');
INSERT INTO `sys_module_detail` VALUES ('348', '9', '23列值0,23列值1,23列值2,23列值3,23列值4');
INSERT INTO `sys_module_detail` VALUES ('349', '9', '24列值0,24列值1,24列值2,24列值3,24列值4');
INSERT INTO `sys_module_detail` VALUES ('350', '9', '25列值0,25列值1,25列值2,25列值3,25列值4');
INSERT INTO `sys_module_detail` VALUES ('351', '9', '26列值0,26列值1,26列值2,26列值3,26列值4');
INSERT INTO `sys_module_detail` VALUES ('352', '9', '27列值0,27列值1,27列值2,27列值3,27列值4');
INSERT INTO `sys_module_detail` VALUES ('353', '9', '28列值0,28列值1,28列值2,28列值3,28列值4');
INSERT INTO `sys_module_detail` VALUES ('354', '9', '29列值0,29列值1,29列值2,29列值3,29列值4');
INSERT INTO `sys_module_detail` VALUES ('355', '9', '30列值0,30列值1,30列值2,30列值3,30列值4');
INSERT INTO `sys_module_detail` VALUES ('356', '9', '31列值0,31列值1,31列值2,31列值3,31列值4');
INSERT INTO `sys_module_detail` VALUES ('357', '9', '32列值0,32列值1,32列值2,32列值3,32列值4');
INSERT INTO `sys_module_detail` VALUES ('358', '9', '33列值0,33列值1,33列值2,33列值3,33列值4');
INSERT INTO `sys_module_detail` VALUES ('359', '9', '34列值0,34列值1,34列值2,34列值3,34列值4');
INSERT INTO `sys_module_detail` VALUES ('360', '9', '35列值0,35列值1,35列值2,35列值3,35列值4');
INSERT INTO `sys_module_detail` VALUES ('361', '9', '36列值0,36列值1,36列值2,36列值3,36列值4');
INSERT INTO `sys_module_detail` VALUES ('362', '9', '37列值0,37列值1,37列值2,37列值3,37列值4');
INSERT INTO `sys_module_detail` VALUES ('363', '9', '38列值0,38列值1,38列值2,38列值3,38列值4');
INSERT INTO `sys_module_detail` VALUES ('364', '9', '39列值0,39列值1,39列值2,39列值3,39列值4');
INSERT INTO `sys_module_detail` VALUES ('365', '9', '40列值0,40列值1,40列值2,40列值3,40列值4');
INSERT INTO `sys_module_detail` VALUES ('366', '9', '41列值0,41列值1,41列值2,41列值3,41列值4');
INSERT INTO `sys_module_detail` VALUES ('367', '9', '42列值0,42列值1,42列值2,42列值3,42列值4');
INSERT INTO `sys_module_detail` VALUES ('368', '9', '43列值0,43列值1,43列值2,43列值3,43列值4');
INSERT INTO `sys_module_detail` VALUES ('369', '9', '44列值0,44列值1,44列值2,44列值3,44列值4');
INSERT INTO `sys_module_detail` VALUES ('370', '9', '45列值0,45列值1,45列值2,45列值3,45列值4');
INSERT INTO `sys_module_detail` VALUES ('371', '9', '46列值0,46列值1,46列值2,46列值3,46列值4');
INSERT INTO `sys_module_detail` VALUES ('372', '9', '47列值0,47列值1,47列值2,47列值3,47列值4');
INSERT INTO `sys_module_detail` VALUES ('373', '9', '48列值0,48列值1,48列值2,48列值3,48列值4');
INSERT INTO `sys_module_detail` VALUES ('374', '9', '49列值0,49列值1,49列值2,49列值3,49列值4');
INSERT INTO `sys_module_detail` VALUES ('375', '9', '50列值0,50列值1,50列值2,50列值3,50列值4');
INSERT INTO `sys_module_detail` VALUES ('376', '9', '51列值0,51列值1,51列值2,51列值3,51列值4');
INSERT INTO `sys_module_detail` VALUES ('377', '9', '52列值0,52列值1,52列值2,52列值3,52列值4');
INSERT INTO `sys_module_detail` VALUES ('378', '9', '53列值0,53列值1,53列值2,53列值3,53列值4');
INSERT INTO `sys_module_detail` VALUES ('379', '9', '54列值0,54列值1,54列值2,54列值3,54列值4');
INSERT INTO `sys_module_detail` VALUES ('380', '9', '55列值0,55列值1,55列值2,55列值3,55列值4');
INSERT INTO `sys_module_detail` VALUES ('381', '9', '56列值0,56列值1,56列值2,56列值3,56列值4');
INSERT INTO `sys_module_detail` VALUES ('382', '9', '57列值0,57列值1,57列值2,57列值3,57列值4');
INSERT INTO `sys_module_detail` VALUES ('383', '9', '58列值0,58列值1,58列值2,58列值3,58列值4');
INSERT INTO `sys_module_detail` VALUES ('384', '9', '59列值0,59列值1,59列值2,59列值3,59列值4');
INSERT INTO `sys_module_detail` VALUES ('385', '9', '60列值0,60列值1,60列值2,60列值3,60列值4');
INSERT INTO `sys_module_detail` VALUES ('386', '9', '61列值0,61列值1,61列值2,61列值3,61列值4');
INSERT INTO `sys_module_detail` VALUES ('387', '9', '62列值0,62列值1,62列值2,62列值3,62列值4');
INSERT INTO `sys_module_detail` VALUES ('388', '9', '63列值0,63列值1,63列值2,63列值3,63列值4');
INSERT INTO `sys_module_detail` VALUES ('389', '9', '64列值0,64列值1,64列值2,64列值3,64列值4');
INSERT INTO `sys_module_detail` VALUES ('390', '9', '65列值0,65列值1,65列值2,65列值3,65列值4');
INSERT INTO `sys_module_detail` VALUES ('391', '9', '66列值0,66列值1,66列值2,66列值3,66列值4');
INSERT INTO `sys_module_detail` VALUES ('392', '9', '67列值0,67列值1,67列值2,67列值3,67列值4');
INSERT INTO `sys_module_detail` VALUES ('393', '9', '68列值0,68列值1,68列值2,68列值3,68列值4');
INSERT INTO `sys_module_detail` VALUES ('394', '9', '69列值0,69列值1,69列值2,69列值3,69列值4');
INSERT INTO `sys_module_detail` VALUES ('395', '9', '70列值0,70列值1,70列值2,70列值3,70列值4');
INSERT INTO `sys_module_detail` VALUES ('396', '9', '71列值0,71列值1,71列值2,71列值3,71列值4');
INSERT INTO `sys_module_detail` VALUES ('397', '9', '72列值0,72列值1,72列值2,72列值3,72列值4');
INSERT INTO `sys_module_detail` VALUES ('398', '9', '73列值0,73列值1,73列值2,73列值3,73列值4');
INSERT INTO `sys_module_detail` VALUES ('399', '9', '74列值0,74列值1,74列值2,74列值3,74列值4');
INSERT INTO `sys_module_detail` VALUES ('400', '9', '75列值0,75列值1,75列值2,75列值3,75列值4');
INSERT INTO `sys_module_detail` VALUES ('401', '9', '76列值0,76列值1,76列值2,76列值3,76列值4');
INSERT INTO `sys_module_detail` VALUES ('402', '9', '77列值0,77列值1,77列值2,77列值3,77列值4');
INSERT INTO `sys_module_detail` VALUES ('403', '9', '78列值0,78列值1,78列值2,78列值3,78列值4');
INSERT INTO `sys_module_detail` VALUES ('404', '9', '79列值0,79列值1,79列值2,79列值3,79列值4');
INSERT INTO `sys_module_detail` VALUES ('405', '9', '80列值0,80列值1,80列值2,80列值3,80列值4');
INSERT INTO `sys_module_detail` VALUES ('406', '9', '81列值0,81列值1,81列值2,81列值3,81列值4');
INSERT INTO `sys_module_detail` VALUES ('407', '9', '82列值0,82列值1,82列值2,82列值3,82列值4');
INSERT INTO `sys_module_detail` VALUES ('408', '9', '83列值0,83列值1,83列值2,83列值3,83列值4');
INSERT INTO `sys_module_detail` VALUES ('409', '9', '84列值0,84列值1,84列值2,84列值3,84列值4');
INSERT INTO `sys_module_detail` VALUES ('410', '9', '85列值0,85列值1,85列值2,85列值3,85列值4');
INSERT INTO `sys_module_detail` VALUES ('411', '9', '86列值0,86列值1,86列值2,86列值3,86列值4');
INSERT INTO `sys_module_detail` VALUES ('412', '9', '87列值0,87列值1,87列值2,87列值3,87列值4');
INSERT INTO `sys_module_detail` VALUES ('413', '9', '88列值0,88列值1,88列值2,88列值3,88列值4');
INSERT INTO `sys_module_detail` VALUES ('414', '9', '89列值0,89列值1,89列值2,89列值3,89列值4');
INSERT INTO `sys_module_detail` VALUES ('415', '9', '90列值0,90列值1,90列值2,90列值3,90列值4');
INSERT INTO `sys_module_detail` VALUES ('416', '9', '91列值0,91列值1,91列值2,91列值3,91列值4');
INSERT INTO `sys_module_detail` VALUES ('417', '9', '92列值0,92列值1,92列值2,92列值3,92列值4');
INSERT INTO `sys_module_detail` VALUES ('418', '9', '93列值0,93列值1,93列值2,93列值3,93列值4');
INSERT INTO `sys_module_detail` VALUES ('419', '9', '94列值0,94列值1,94列值2,94列值3,94列值4');
INSERT INTO `sys_module_detail` VALUES ('420', '9', '95列值0,95列值1,95列值2,95列值3,95列值4');
INSERT INTO `sys_module_detail` VALUES ('421', '9', '96列值0,96列值1,96列值2,96列值3,96列值4');
INSERT INTO `sys_module_detail` VALUES ('422', '9', '97列值0,97列值1,97列值2,97列值3,97列值4');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `modify_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user`;
CREATE TABLE `sys_org_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_premission
-- ----------------------------
DROP TABLE IF EXISTS `sys_premission`;
CREATE TABLE `sys_premission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `premission_type` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_premission
-- ----------------------------
INSERT INTO `sys_premission` VALUES ('1', 'admin:search', '2019-08-28 11:51:00', null);
INSERT INTO `sys_premission` VALUES ('2', 'admin:update', '2019-08-28 11:51:18', null);
INSERT INTO `sys_premission` VALUES ('3', 'admin:save', '2019-08-28 11:51:26', null);
INSERT INTO `sys_premission` VALUES ('4', 'admin:delete', '2019-08-28 11:51:36', null);
INSERT INTO `sys_premission` VALUES ('5', 'user:search', '2019-08-29 15:24:54', null);
INSERT INTO `sys_premission` VALUES ('6', 'user:save', '2019-08-29 00:00:00', null);
INSERT INTO `sys_premission` VALUES ('7', 'user:update', '2019-08-29 00:00:00', null);
INSERT INTO `sys_premission` VALUES ('8', 'user:delete', '2019-08-29 00:00:00', null);
INSERT INTO `sys_premission` VALUES ('9', 'read', '2019-09-11 16:34:50', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '2019-08-28 11:50:40', null, 'Y', '管理员', null);
INSERT INTO `sys_role` VALUES ('2', 'user', '2019-08-29 10:57:03', null, 'Y', '普通用户', null);
INSERT INTO `sys_role` VALUES ('3', 'finance assistance', '2019-09-02 15:31:36', null, 'Y', '金融助理', null);
INSERT INTO `sys_role` VALUES ('4', 'accounting', '2019-11-05 10:16:24', null, 'Y', '会计', null);
INSERT INTO `sys_role` VALUES ('5', 'manager', '2019-11-05 10:16:53', null, 'Y', '总经理', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('7', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('8', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '7');

-- ----------------------------
-- Table structure for sys_role_prem
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_prem`;
CREATE TABLE `sys_role_prem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `prem_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_prem
-- ----------------------------
INSERT INTO `sys_role_prem` VALUES ('1', '1', '1');
INSERT INTO `sys_role_prem` VALUES ('2', '1', '2');
INSERT INTO `sys_role_prem` VALUES ('3', '1', '3');
INSERT INTO `sys_role_prem` VALUES ('4', '1', '4');
INSERT INTO `sys_role_prem` VALUES ('5', '1', '9');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '1', '2');
INSERT INTO `sys_role_user` VALUES ('3', '1', '4');
INSERT INTO `sys_role_user` VALUES ('4', '1', '4');
INSERT INTO `sys_role_user` VALUES ('5', '4', '1');
INSERT INTO `sys_role_user` VALUES ('6', '4', '2');
INSERT INTO `sys_role_user` VALUES ('7', '5', '1');
INSERT INTO `sys_role_user` VALUES ('8', '4', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'xxx', 'jien', 'e10adc3949ba59abbe56e057f20f883e', null, '2019-08-28 11:50:16', null, '2');
INSERT INTO `sys_user` VALUES ('2', 'xxx', 'xxx', 'e10adc3949ba59abbe56e057f20f883e', null, '2019-08-28 15:56:22', null, '2');
INSERT INTO `sys_user` VALUES ('3', 'zzz', 'zzz', '213456', null, '2019-09-19 15:17:39', null, '2');
INSERT INTO `sys_user` VALUES ('4', 'jien', 'xz', '123456', null, '2019-09-19 15:21:25', null, '1');
