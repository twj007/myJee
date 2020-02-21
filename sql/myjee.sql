/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-02-21 16:08:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'fentandingshi', 'DEFAULT', '0 0 0 1/1 * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'test', 'DEFAULT', '0 0 16 * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'fentandingshi', 'DEFAULT', 'fentandingshi', 'com.component.ManageableJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000077400067461736B4E6F74000D66656E74616E64696E6773686974000873656E64547970657400046874747074001065786563757465506172616D6574657274000074000269647400013574000776657273696F6E737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000074000375726C74001E687474703A2F2F6C6F63616C686F73743A383038312F6375745461626C6574000A6578656375746F724E6F74000CE58886E6918AE5AE9AE697B67800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'test', 'DEFAULT', 'test', 'com.component.ManageableJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000077400067461736B4E6F7400047465737474000873656E64547970657400046874747074001065786563757465506172616D6574657274000378787874000269647400013174000776657273696F6E737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000063C74000375726C740021687474703A2F2F6C6F63616C686F73743A383038312F71756172747A2F7465737474000A6578656375746F724E6F740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('quartzScheduler', 'DESKTOP-A85M7BF1582268158808', '1582272528939', '10000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'fentandingshi', 'DEFAULT', 'fentandingshi', 'DEFAULT', 'fentandingshi', '1582300800000', '-1', '5', 'WAITING', 'CRON', '1582268159000', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'test', 'DEFAULT', 'test', 'DEFAULT', 'test', '1582358400000', '1582272000000', '5', 'WAITING', 'CRON', '1582268159000', '0', null, '0', '');

-- ----------------------------
-- Table structure for quartz_task_errors
-- ----------------------------
DROP TABLE IF EXISTS `quartz_task_errors`;
CREATE TABLE `quartz_task_errors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taskExecuteRecordId` varchar(64) NOT NULL COMMENT '任务执行记录Id',
  `errorKey` varchar(1024) NOT NULL COMMENT '信息关键字',
  `errorValue` text COMMENT '信息内容',
  `createTime` bigint(13) NOT NULL COMMENT '创建时间',
  `lastModifyTime` bigint(13) DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务出错现场信息表';

-- ----------------------------
-- Records of quartz_task_errors
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_task_informations
-- ----------------------------
DROP TABLE IF EXISTS `quartz_task_informations`;
CREATE TABLE `quartz_task_informations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL COMMENT '版本号：需要乐观锁控制',
  `taskNo` varchar(64) NOT NULL COMMENT '任务编号',
  `taskName` varchar(64) NOT NULL COMMENT '任务名称（名称可以为描述）',
  `schedulerRule` varchar(64) NOT NULL COMMENT '定时规则表达式',
  `frozenStatus` varchar(16) NOT NULL COMMENT '冻结状态',
  `executorNo` varchar(128) NOT NULL COMMENT '执行方',
  `frozenTime` bigint(13) DEFAULT NULL COMMENT '冻结时间',
  `unfrozenTime` bigint(13) DEFAULT NULL COMMENT '解冻时间',
  `createTime` bigint(13) NOT NULL COMMENT '创建时间',
  `lastModifyTime` bigint(13) DEFAULT NULL COMMENT '最近修改时间',
  `sendType` varchar(64) DEFAULT NULL COMMENT '发送方式',
  `url` varchar(64) DEFAULT NULL COMMENT '请求地址',
  `executeParamter` varchar(2000) DEFAULT NULL COMMENT '执行参数',
  `timeKey` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='定时任务信息表';

-- ----------------------------
-- Records of quartz_task_informations
-- ----------------------------
INSERT INTO `quartz_task_informations` VALUES ('1', '1596', 'test', 'test', '0 0 16 * * ? *', 'UNFROZEN', '1', '1577351440656', '1577351446096', '1573452783968', '1579403362351', 'http', 'http://localhost:8081/quartz/test', 'xxx', '2019-11-11 17:09:00');
INSERT INTO `quartz_task_informations` VALUES ('3', '6', 'renren', 'renren', '0 0 15 * * ? *', 'FROZEN', 'zzz', '1579399616829', '1577694484694', '1577255140593', '1577694494179', 'http', 'http://localhost:8081/sign/py', '1', '2019-12-25');
INSERT INTO `quartz_task_informations` VALUES ('4', '0', 'aaa', 'aaa', '0 0 0 12 * ? *', 'FROZEN', '服务名称为', '1577428659403', '1577428649635', '1577428579480', '1577428652216', 'http', 'http://localhost:8081/quartz/test', 'ha', '');
INSERT INTO `quartz_task_informations` VALUES ('5', '0', 'fentandingshi', 'fentandingshi', '0 0 0 1/1 * ? *', 'UNFROZEN', '分摊定时', null, '1579488886862', '1579488876161', '1582268180066', 'http', 'http://localhost:8081/cutTable', '', '2020/1/20');

-- ----------------------------
-- Table structure for quartz_task_records
-- ----------------------------
DROP TABLE IF EXISTS `quartz_task_records`;
CREATE TABLE `quartz_task_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taskNo` varchar(64) NOT NULL COMMENT '任务编号',
  `timeKeyValue` varchar(32) DEFAULT NULL COMMENT '执行时间格式值',
  `executeTime` bigint(13) NOT NULL COMMENT '执行时间',
  `taskStatus` varchar(16) DEFAULT NULL COMMENT '任务状态',
  `failcount` int(10) DEFAULT NULL COMMENT '失败统计数',
  `failReason` varchar(64) DEFAULT NULL COMMENT '失败错误描述',
  `createTime` bigint(13) NOT NULL COMMENT '创建时间',
  `lastModifyTime` bigint(13) DEFAULT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_task_records_taskno` (`taskNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1742 DEFAULT CHARSET=utf8 COMMENT='定时任务执行情况记录表';

-- ----------------------------
-- Records of quartz_task_records
-- ----------------------------
INSERT INTO `quartz_task_records` VALUES ('1718', 'test', '2019-11-11 17:09:00', '1577353091305', null, null, null, '1577353091321', null);
INSERT INTO `quartz_task_records` VALUES ('1719', 'test', '2019-11-11 17:09:00', '1577409237148', null, null, null, '1577409270162', null);
INSERT INTO `quartz_task_records` VALUES ('1720', 'test', '2019-11-11 17:09:00', '1577409577996', null, null, null, '1577409582667', null);
INSERT INTO `quartz_task_records` VALUES ('1721', 'test', '2019-11-11 17:09:00', '1577409849824', null, null, null, '1577409849883', null);
INSERT INTO `quartz_task_records` VALUES ('1722', 'test', '2019-11-11 17:09:00', '1577410215533', null, null, null, '1577410215573', null);
INSERT INTO `quartz_task_records` VALUES ('1723', 'test', '2019-11-11 17:09:00', '1577410544775', 'fail', '0', null, '1577410544788', null);
INSERT INTO `quartz_task_records` VALUES ('1724', 'renren', '2019-12-25', '1577414214546', 'fail', '0', null, '1577414214554', null);
INSERT INTO `quartz_task_records` VALUES ('1725', 'renren', '2019-12-25', '1577414244301', 'fail', '0', null, '1577414244315', null);
INSERT INTO `quartz_task_records` VALUES ('1726', 'aaa', '', '1577428652216', 'success', '0', null, '1577428652230', null);
INSERT INTO `quartz_task_records` VALUES ('1727', 'test', '2019-11-11 17:09:00', '1577692582772', 'success', '0', null, '1577692582777', null);
INSERT INTO `quartz_task_records` VALUES ('1728', 'renren', '2019-12-25', '1577694494179', 'success', '0', null, '1577694494185', null);
INSERT INTO `quartz_task_records` VALUES ('1729', 'test', '2019-11-11 17:09:00', '1579399620800', 'success', '0', null, '1579399620826', null);
INSERT INTO `quartz_task_records` VALUES ('1730', 'test', '2019-11-11 17:09:00', '1579403362351', 'success', '0', null, '1579403362385', null);
INSERT INTO `quartz_task_records` VALUES ('1731', 'fentandingshi', '2020/1/20', '1579488955795', 'success', '0', null, '1579488955803', null);
INSERT INTO `quartz_task_records` VALUES ('1732', 'fentandingshi', '2020/1/20', '1579489038550', 'success', '0', null, '1579489038561', null);
INSERT INTO `quartz_task_records` VALUES ('1733', 'fentandingshi', '2020/1/20', '1579490005867', 'success', '0', null, '1579490005881', null);
INSERT INTO `quartz_task_records` VALUES ('1734', 'fentandingshi', '2020/1/20', '1579502233316', 'success', '0', null, '1579502233325', null);
INSERT INTO `quartz_task_records` VALUES ('1735', 'fentandingshi', '2020/1/20', '1582265000224', 'success', '0', null, '1582265000242', null);
INSERT INTO `quartz_task_records` VALUES ('1736', 'fentandingshi', '2020/1/20', '1582265393719', 'success', '0', null, '1582265393730', null);
INSERT INTO `quartz_task_records` VALUES ('1737', 'fentandingshi', '2020/1/20', '1582266890912', 'success', '0', null, '1582266890924', null);
INSERT INTO `quartz_task_records` VALUES ('1738', 'fentandingshi', '2020/1/20', '1582267039530', 'success', '0', null, '1582267039544', null);
INSERT INTO `quartz_task_records` VALUES ('1739', 'fentandingshi', '2020/1/20', '1582267673869', 'success', '0', null, '1582267673890', null);
INSERT INTO `quartz_task_records` VALUES ('1740', 'fentandingshi', '2020/1/20', '1582268044954', 'success', '0', null, '1582268044973', null);
INSERT INTO `quartz_task_records` VALUES ('1741', 'fentandingshi', '2020/1/20', '1582268180066', 'success', '0', null, '1582268180077', null);

-- ----------------------------
-- Table structure for recod
-- ----------------------------
DROP TABLE IF EXISTS `recod`;
CREATE TABLE `recod` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4423 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recod
-- ----------------------------
INSERT INTO `recod` VALUES ('2306', '1');
INSERT INTO `recod` VALUES ('2307', '1');
INSERT INTO `recod` VALUES ('2308', '1');
INSERT INTO `recod` VALUES ('2309', '1');
INSERT INTO `recod` VALUES ('2310', '1');
INSERT INTO `recod` VALUES ('2311', '1');
INSERT INTO `recod` VALUES ('2312', '1');
INSERT INTO `recod` VALUES ('2313', '1');
INSERT INTO `recod` VALUES ('2314', '1');
INSERT INTO `recod` VALUES ('2315', '1');
INSERT INTO `recod` VALUES ('2316', '1');
INSERT INTO `recod` VALUES ('2317', '1');
INSERT INTO `recod` VALUES ('2318', '1');
INSERT INTO `recod` VALUES ('2319', '1');
INSERT INTO `recod` VALUES ('2320', '1');
INSERT INTO `recod` VALUES ('2321', '1');
INSERT INTO `recod` VALUES ('2322', '1');
INSERT INTO `recod` VALUES ('2323', '1');
INSERT INTO `recod` VALUES ('2324', '1');
INSERT INTO `recod` VALUES ('2325', '1');
INSERT INTO `recod` VALUES ('2326', '1');
INSERT INTO `recod` VALUES ('2327', '1');
INSERT INTO `recod` VALUES ('2328', '1');
INSERT INTO `recod` VALUES ('2329', '1');
INSERT INTO `recod` VALUES ('2330', '1');
INSERT INTO `recod` VALUES ('2331', '1');
INSERT INTO `recod` VALUES ('2332', '1');
INSERT INTO `recod` VALUES ('2333', '1');
INSERT INTO `recod` VALUES ('2334', '1');
INSERT INTO `recod` VALUES ('2335', '1');
INSERT INTO `recod` VALUES ('2336', '1');
INSERT INTO `recod` VALUES ('2337', '1');
INSERT INTO `recod` VALUES ('2338', '1');
INSERT INTO `recod` VALUES ('2339', '1');
INSERT INTO `recod` VALUES ('2340', '1');
INSERT INTO `recod` VALUES ('2341', '1');
INSERT INTO `recod` VALUES ('2342', '1');
INSERT INTO `recod` VALUES ('2343', '1');
INSERT INTO `recod` VALUES ('2344', '1');
INSERT INTO `recod` VALUES ('2345', '1');
INSERT INTO `recod` VALUES ('2346', '1');
INSERT INTO `recod` VALUES ('2347', '1');
INSERT INTO `recod` VALUES ('2348', '1');
INSERT INTO `recod` VALUES ('2349', '1');
INSERT INTO `recod` VALUES ('2350', '1');
INSERT INTO `recod` VALUES ('2351', '1');
INSERT INTO `recod` VALUES ('2352', '1');
INSERT INTO `recod` VALUES ('2353', '1');
INSERT INTO `recod` VALUES ('2354', '1');
INSERT INTO `recod` VALUES ('2355', '1');
INSERT INTO `recod` VALUES ('2356', '1');
INSERT INTO `recod` VALUES ('2357', '1');
INSERT INTO `recod` VALUES ('2358', '1');
INSERT INTO `recod` VALUES ('2359', '1');
INSERT INTO `recod` VALUES ('2360', '1');
INSERT INTO `recod` VALUES ('2361', '1');
INSERT INTO `recod` VALUES ('2362', '1');
INSERT INTO `recod` VALUES ('2363', '1');
INSERT INTO `recod` VALUES ('2364', '1');
INSERT INTO `recod` VALUES ('2365', '1');
INSERT INTO `recod` VALUES ('2366', '1');
INSERT INTO `recod` VALUES ('2367', '1');
INSERT INTO `recod` VALUES ('2368', '1');
INSERT INTO `recod` VALUES ('2369', '1');
INSERT INTO `recod` VALUES ('2370', '1');
INSERT INTO `recod` VALUES ('2371', '1');
INSERT INTO `recod` VALUES ('2372', '1');
INSERT INTO `recod` VALUES ('2373', '1');
INSERT INTO `recod` VALUES ('2374', '1');
INSERT INTO `recod` VALUES ('2375', '1');
INSERT INTO `recod` VALUES ('2376', '1');
INSERT INTO `recod` VALUES ('2377', '1');
INSERT INTO `recod` VALUES ('2378', '1');
INSERT INTO `recod` VALUES ('2379', '1');
INSERT INTO `recod` VALUES ('2380', '1');
INSERT INTO `recod` VALUES ('2381', '1');
INSERT INTO `recod` VALUES ('2382', '1');
INSERT INTO `recod` VALUES ('2383', '1');
INSERT INTO `recod` VALUES ('2384', '1');
INSERT INTO `recod` VALUES ('2385', '1');
INSERT INTO `recod` VALUES ('2386', '1');
INSERT INTO `recod` VALUES ('2387', '1');
INSERT INTO `recod` VALUES ('2388', '1');
INSERT INTO `recod` VALUES ('2389', '1');
INSERT INTO `recod` VALUES ('2390', '1');
INSERT INTO `recod` VALUES ('2391', '1');
INSERT INTO `recod` VALUES ('2392', '1');
INSERT INTO `recod` VALUES ('2393', '1');
INSERT INTO `recod` VALUES ('2394', '1');
INSERT INTO `recod` VALUES ('2395', '1');
INSERT INTO `recod` VALUES ('2396', '1');
INSERT INTO `recod` VALUES ('2397', '1');
INSERT INTO `recod` VALUES ('2398', '1');
INSERT INTO `recod` VALUES ('2399', '1');
INSERT INTO `recod` VALUES ('2400', '1');
INSERT INTO `recod` VALUES ('2401', '1');
INSERT INTO `recod` VALUES ('2402', '1');
INSERT INTO `recod` VALUES ('2403', '1');
INSERT INTO `recod` VALUES ('2404', '1');
INSERT INTO `recod` VALUES ('2405', '1');
INSERT INTO `recod` VALUES ('2406', '1');
INSERT INTO `recod` VALUES ('2407', '1');
INSERT INTO `recod` VALUES ('2408', '1');
INSERT INTO `recod` VALUES ('2409', '1');
INSERT INTO `recod` VALUES ('2410', '1');
INSERT INTO `recod` VALUES ('2411', '1');
INSERT INTO `recod` VALUES ('2412', '1');
INSERT INTO `recod` VALUES ('2413', '1');
INSERT INTO `recod` VALUES ('2414', '1');
INSERT INTO `recod` VALUES ('2415', '1');
INSERT INTO `recod` VALUES ('2416', '1');
INSERT INTO `recod` VALUES ('2417', '1');
INSERT INTO `recod` VALUES ('2418', '1');
INSERT INTO `recod` VALUES ('2419', '1');
INSERT INTO `recod` VALUES ('2420', '1');
INSERT INTO `recod` VALUES ('2421', '1');
INSERT INTO `recod` VALUES ('2422', '1');
INSERT INTO `recod` VALUES ('2423', '1');
INSERT INTO `recod` VALUES ('2424', '1');
INSERT INTO `recod` VALUES ('2425', '1');
INSERT INTO `recod` VALUES ('2426', '1');
INSERT INTO `recod` VALUES ('2427', '1');
INSERT INTO `recod` VALUES ('2428', '1');
INSERT INTO `recod` VALUES ('2429', '1');
INSERT INTO `recod` VALUES ('2430', '1');
INSERT INTO `recod` VALUES ('2431', '1');
INSERT INTO `recod` VALUES ('2432', '1');
INSERT INTO `recod` VALUES ('2433', '1');
INSERT INTO `recod` VALUES ('2434', '1');
INSERT INTO `recod` VALUES ('2435', '1');
INSERT INTO `recod` VALUES ('2436', '1');
INSERT INTO `recod` VALUES ('2437', '1');
INSERT INTO `recod` VALUES ('2438', '1');
INSERT INTO `recod` VALUES ('2439', '1');
INSERT INTO `recod` VALUES ('2440', '1');
INSERT INTO `recod` VALUES ('2441', '1');
INSERT INTO `recod` VALUES ('2442', '1');
INSERT INTO `recod` VALUES ('2443', '1');
INSERT INTO `recod` VALUES ('2444', '1');
INSERT INTO `recod` VALUES ('2445', '1');
INSERT INTO `recod` VALUES ('2446', '1');
INSERT INTO `recod` VALUES ('2447', '1');
INSERT INTO `recod` VALUES ('2448', '1');
INSERT INTO `recod` VALUES ('2449', '1');
INSERT INTO `recod` VALUES ('2450', '1');
INSERT INTO `recod` VALUES ('2451', '1');
INSERT INTO `recod` VALUES ('2452', '1');
INSERT INTO `recod` VALUES ('2453', '1');
INSERT INTO `recod` VALUES ('2454', '1');
INSERT INTO `recod` VALUES ('2455', '1');
INSERT INTO `recod` VALUES ('2456', '1');
INSERT INTO `recod` VALUES ('2457', '1');
INSERT INTO `recod` VALUES ('2458', '1');
INSERT INTO `recod` VALUES ('2459', '1');
INSERT INTO `recod` VALUES ('2460', '1');
INSERT INTO `recod` VALUES ('2461', '1');
INSERT INTO `recod` VALUES ('2462', '1');
INSERT INTO `recod` VALUES ('2463', '1');
INSERT INTO `recod` VALUES ('2464', '1');
INSERT INTO `recod` VALUES ('2465', '1');
INSERT INTO `recod` VALUES ('2466', '1');
INSERT INTO `recod` VALUES ('2467', '1');
INSERT INTO `recod` VALUES ('2468', '1');
INSERT INTO `recod` VALUES ('2469', '1');
INSERT INTO `recod` VALUES ('2470', '1');
INSERT INTO `recod` VALUES ('2471', '1');
INSERT INTO `recod` VALUES ('2472', '1');
INSERT INTO `recod` VALUES ('2473', '1');
INSERT INTO `recod` VALUES ('2474', '1');
INSERT INTO `recod` VALUES ('2475', '1');
INSERT INTO `recod` VALUES ('2476', '1');
INSERT INTO `recod` VALUES ('2477', '1');
INSERT INTO `recod` VALUES ('2478', '1');
INSERT INTO `recod` VALUES ('2479', '1');
INSERT INTO `recod` VALUES ('2480', '1');
INSERT INTO `recod` VALUES ('2481', '1');
INSERT INTO `recod` VALUES ('2482', '1');
INSERT INTO `recod` VALUES ('2483', '1');
INSERT INTO `recod` VALUES ('2484', '1');
INSERT INTO `recod` VALUES ('2485', '1');
INSERT INTO `recod` VALUES ('2486', '1');
INSERT INTO `recod` VALUES ('2487', '1');
INSERT INTO `recod` VALUES ('2488', '1');
INSERT INTO `recod` VALUES ('2489', '1');
INSERT INTO `recod` VALUES ('2490', '1');
INSERT INTO `recod` VALUES ('2491', '1');
INSERT INTO `recod` VALUES ('2492', '1');
INSERT INTO `recod` VALUES ('2493', '1');
INSERT INTO `recod` VALUES ('2494', '1');
INSERT INTO `recod` VALUES ('2495', '1');
INSERT INTO `recod` VALUES ('2496', '1');
INSERT INTO `recod` VALUES ('2497', '1');
INSERT INTO `recod` VALUES ('2498', '1');
INSERT INTO `recod` VALUES ('2499', '1');
INSERT INTO `recod` VALUES ('2500', '1');
INSERT INTO `recod` VALUES ('2501', '1');
INSERT INTO `recod` VALUES ('2502', '1');
INSERT INTO `recod` VALUES ('2503', '1');
INSERT INTO `recod` VALUES ('2504', '1');
INSERT INTO `recod` VALUES ('2505', '1');
INSERT INTO `recod` VALUES ('2506', '1');
INSERT INTO `recod` VALUES ('2507', '1');
INSERT INTO `recod` VALUES ('2508', '1');
INSERT INTO `recod` VALUES ('2509', '1');
INSERT INTO `recod` VALUES ('2510', '1');
INSERT INTO `recod` VALUES ('2511', '1');
INSERT INTO `recod` VALUES ('2512', '1');
INSERT INTO `recod` VALUES ('2513', '1');
INSERT INTO `recod` VALUES ('2514', '1');
INSERT INTO `recod` VALUES ('2515', '1');
INSERT INTO `recod` VALUES ('2516', '1');
INSERT INTO `recod` VALUES ('2517', '1');
INSERT INTO `recod` VALUES ('2518', '1');
INSERT INTO `recod` VALUES ('2519', '1');
INSERT INTO `recod` VALUES ('2520', '1');
INSERT INTO `recod` VALUES ('2521', '1');
INSERT INTO `recod` VALUES ('2522', '1');
INSERT INTO `recod` VALUES ('2523', '1');
INSERT INTO `recod` VALUES ('2524', '1');
INSERT INTO `recod` VALUES ('2525', '1');
INSERT INTO `recod` VALUES ('2526', '1');
INSERT INTO `recod` VALUES ('2527', '1');
INSERT INTO `recod` VALUES ('2528', '1');
INSERT INTO `recod` VALUES ('2529', '1');
INSERT INTO `recod` VALUES ('2530', '1');
INSERT INTO `recod` VALUES ('2531', '1');
INSERT INTO `recod` VALUES ('2532', '1');
INSERT INTO `recod` VALUES ('2533', '1');
INSERT INTO `recod` VALUES ('2534', '1');
INSERT INTO `recod` VALUES ('2535', '1');
INSERT INTO `recod` VALUES ('2536', '1');
INSERT INTO `recod` VALUES ('2537', '1');
INSERT INTO `recod` VALUES ('2538', '1');
INSERT INTO `recod` VALUES ('2539', '1');
INSERT INTO `recod` VALUES ('2540', '1');
INSERT INTO `recod` VALUES ('2541', '1');
INSERT INTO `recod` VALUES ('2542', '1');
INSERT INTO `recod` VALUES ('2543', '1');
INSERT INTO `recod` VALUES ('2544', '1');
INSERT INTO `recod` VALUES ('2545', '1');
INSERT INTO `recod` VALUES ('2546', '1');
INSERT INTO `recod` VALUES ('2547', '1');
INSERT INTO `recod` VALUES ('2548', '1');
INSERT INTO `recod` VALUES ('2549', '1');
INSERT INTO `recod` VALUES ('2550', '1');
INSERT INTO `recod` VALUES ('2551', '1');
INSERT INTO `recod` VALUES ('2552', '1');
INSERT INTO `recod` VALUES ('2553', '1');
INSERT INTO `recod` VALUES ('2554', '1');
INSERT INTO `recod` VALUES ('2555', '1');
INSERT INTO `recod` VALUES ('2556', '1');
INSERT INTO `recod` VALUES ('2557', '1');
INSERT INTO `recod` VALUES ('2558', '1');
INSERT INTO `recod` VALUES ('2559', '1');
INSERT INTO `recod` VALUES ('2560', '1');
INSERT INTO `recod` VALUES ('2561', '1');
INSERT INTO `recod` VALUES ('2562', '1');
INSERT INTO `recod` VALUES ('2563', '1');
INSERT INTO `recod` VALUES ('2564', '1');
INSERT INTO `recod` VALUES ('2565', '1');
INSERT INTO `recod` VALUES ('2566', '1');
INSERT INTO `recod` VALUES ('2567', '1');
INSERT INTO `recod` VALUES ('2568', '1');
INSERT INTO `recod` VALUES ('2569', '1');
INSERT INTO `recod` VALUES ('2570', '1');
INSERT INTO `recod` VALUES ('2571', '1');
INSERT INTO `recod` VALUES ('2572', '1');
INSERT INTO `recod` VALUES ('2573', '1');
INSERT INTO `recod` VALUES ('2574', '1');
INSERT INTO `recod` VALUES ('2575', '1');
INSERT INTO `recod` VALUES ('2576', '1');
INSERT INTO `recod` VALUES ('2577', '1');
INSERT INTO `recod` VALUES ('2578', '1');
INSERT INTO `recod` VALUES ('2579', '1');
INSERT INTO `recod` VALUES ('2580', '1');
INSERT INTO `recod` VALUES ('2581', '1');
INSERT INTO `recod` VALUES ('2582', '1');
INSERT INTO `recod` VALUES ('2583', '1');
INSERT INTO `recod` VALUES ('2584', '1');
INSERT INTO `recod` VALUES ('2585', '1');
INSERT INTO `recod` VALUES ('2586', '1');
INSERT INTO `recod` VALUES ('2587', '1');
INSERT INTO `recod` VALUES ('2588', '1');
INSERT INTO `recod` VALUES ('2589', '1');
INSERT INTO `recod` VALUES ('2590', '1');
INSERT INTO `recod` VALUES ('2591', '1');
INSERT INTO `recod` VALUES ('2592', '1');
INSERT INTO `recod` VALUES ('2593', '1');
INSERT INTO `recod` VALUES ('2594', '1');
INSERT INTO `recod` VALUES ('2595', '1');
INSERT INTO `recod` VALUES ('2596', '1');
INSERT INTO `recod` VALUES ('2597', '1');
INSERT INTO `recod` VALUES ('2598', '1');
INSERT INTO `recod` VALUES ('2599', '1');
INSERT INTO `recod` VALUES ('2600', '1');
INSERT INTO `recod` VALUES ('2601', '1');
INSERT INTO `recod` VALUES ('2602', '1');
INSERT INTO `recod` VALUES ('2603', '1');
INSERT INTO `recod` VALUES ('2604', '1');
INSERT INTO `recod` VALUES ('2605', '1');
INSERT INTO `recod` VALUES ('2606', '1');
INSERT INTO `recod` VALUES ('2607', '1');
INSERT INTO `recod` VALUES ('2608', '1');
INSERT INTO `recod` VALUES ('2609', '1');
INSERT INTO `recod` VALUES ('2610', '1');
INSERT INTO `recod` VALUES ('2611', '1');
INSERT INTO `recod` VALUES ('2612', '1');
INSERT INTO `recod` VALUES ('2613', '1');
INSERT INTO `recod` VALUES ('2614', '1');
INSERT INTO `recod` VALUES ('2615', '1');
INSERT INTO `recod` VALUES ('2616', '1');
INSERT INTO `recod` VALUES ('2617', '1');
INSERT INTO `recod` VALUES ('2618', '1');
INSERT INTO `recod` VALUES ('2619', '1');
INSERT INTO `recod` VALUES ('2620', '1');
INSERT INTO `recod` VALUES ('2621', '1');
INSERT INTO `recod` VALUES ('2622', '1');
INSERT INTO `recod` VALUES ('2623', '1');
INSERT INTO `recod` VALUES ('2624', '1');
INSERT INTO `recod` VALUES ('2625', '1');
INSERT INTO `recod` VALUES ('2626', '1');
INSERT INTO `recod` VALUES ('2627', '1');
INSERT INTO `recod` VALUES ('2628', '1');
INSERT INTO `recod` VALUES ('2629', '1');
INSERT INTO `recod` VALUES ('2630', '1');
INSERT INTO `recod` VALUES ('2631', '1');
INSERT INTO `recod` VALUES ('2632', '1');
INSERT INTO `recod` VALUES ('2633', '1');
INSERT INTO `recod` VALUES ('2634', '1');
INSERT INTO `recod` VALUES ('2635', '1');
INSERT INTO `recod` VALUES ('2636', '1');
INSERT INTO `recod` VALUES ('2637', '1');
INSERT INTO `recod` VALUES ('2638', '1');
INSERT INTO `recod` VALUES ('2639', '1');
INSERT INTO `recod` VALUES ('2640', '1');
INSERT INTO `recod` VALUES ('2641', '1');
INSERT INTO `recod` VALUES ('2642', '1');
INSERT INTO `recod` VALUES ('2643', '1');
INSERT INTO `recod` VALUES ('2644', '1');
INSERT INTO `recod` VALUES ('2645', '1');
INSERT INTO `recod` VALUES ('2646', '1');
INSERT INTO `recod` VALUES ('2647', '1');
INSERT INTO `recod` VALUES ('2648', '1');
INSERT INTO `recod` VALUES ('2649', '1');
INSERT INTO `recod` VALUES ('2650', '1');
INSERT INTO `recod` VALUES ('2651', '1');
INSERT INTO `recod` VALUES ('2652', '1');
INSERT INTO `recod` VALUES ('2653', '1');
INSERT INTO `recod` VALUES ('2654', '1');
INSERT INTO `recod` VALUES ('2655', '1');
INSERT INTO `recod` VALUES ('2656', '1');
INSERT INTO `recod` VALUES ('2657', '1');
INSERT INTO `recod` VALUES ('2658', '1');
INSERT INTO `recod` VALUES ('2659', '1');
INSERT INTO `recod` VALUES ('2660', '1');
INSERT INTO `recod` VALUES ('2661', '1');
INSERT INTO `recod` VALUES ('2662', '1');
INSERT INTO `recod` VALUES ('2663', '1');
INSERT INTO `recod` VALUES ('2664', '1');
INSERT INTO `recod` VALUES ('2665', '1');
INSERT INTO `recod` VALUES ('2666', '1');
INSERT INTO `recod` VALUES ('2667', '1');
INSERT INTO `recod` VALUES ('2668', '1');
INSERT INTO `recod` VALUES ('2669', '1');
INSERT INTO `recod` VALUES ('2670', '1');
INSERT INTO `recod` VALUES ('2671', '1');
INSERT INTO `recod` VALUES ('2672', '1');
INSERT INTO `recod` VALUES ('2673', '1');
INSERT INTO `recod` VALUES ('2674', '1');
INSERT INTO `recod` VALUES ('2675', '1');
INSERT INTO `recod` VALUES ('2676', '1');
INSERT INTO `recod` VALUES ('2677', '1');
INSERT INTO `recod` VALUES ('2678', '1');
INSERT INTO `recod` VALUES ('2679', '1');
INSERT INTO `recod` VALUES ('2680', '1');
INSERT INTO `recod` VALUES ('2681', '1');
INSERT INTO `recod` VALUES ('2682', '1');
INSERT INTO `recod` VALUES ('2683', '1');
INSERT INTO `recod` VALUES ('2684', '1');
INSERT INTO `recod` VALUES ('2685', '1');
INSERT INTO `recod` VALUES ('2686', '1');
INSERT INTO `recod` VALUES ('2687', '1');
INSERT INTO `recod` VALUES ('2688', '1');
INSERT INTO `recod` VALUES ('2689', '1');
INSERT INTO `recod` VALUES ('2690', '1');
INSERT INTO `recod` VALUES ('2691', '1');
INSERT INTO `recod` VALUES ('2692', '1');
INSERT INTO `recod` VALUES ('2693', '1');
INSERT INTO `recod` VALUES ('2694', '1');
INSERT INTO `recod` VALUES ('2695', '1');
INSERT INTO `recod` VALUES ('2696', '1');
INSERT INTO `recod` VALUES ('2697', '1');
INSERT INTO `recod` VALUES ('2698', '1');
INSERT INTO `recod` VALUES ('2699', '1');
INSERT INTO `recod` VALUES ('2700', '1');
INSERT INTO `recod` VALUES ('2701', '1');
INSERT INTO `recod` VALUES ('2702', '1');
INSERT INTO `recod` VALUES ('2703', '1');
INSERT INTO `recod` VALUES ('2704', '1');
INSERT INTO `recod` VALUES ('2705', '1');
INSERT INTO `recod` VALUES ('2706', '1');
INSERT INTO `recod` VALUES ('2707', '1');
INSERT INTO `recod` VALUES ('2708', '1');
INSERT INTO `recod` VALUES ('2709', '1');
INSERT INTO `recod` VALUES ('2710', '1');
INSERT INTO `recod` VALUES ('2711', '1');
INSERT INTO `recod` VALUES ('2712', '1');
INSERT INTO `recod` VALUES ('2713', '1');
INSERT INTO `recod` VALUES ('2714', '1');
INSERT INTO `recod` VALUES ('2715', '1');
INSERT INTO `recod` VALUES ('2716', '1');
INSERT INTO `recod` VALUES ('2717', '1');
INSERT INTO `recod` VALUES ('2718', '1');
INSERT INTO `recod` VALUES ('2719', '1');
INSERT INTO `recod` VALUES ('2720', '1');
INSERT INTO `recod` VALUES ('2721', '1');
INSERT INTO `recod` VALUES ('2722', '1');
INSERT INTO `recod` VALUES ('2723', '1');
INSERT INTO `recod` VALUES ('2724', '1');
INSERT INTO `recod` VALUES ('2725', '1');
INSERT INTO `recod` VALUES ('2726', '1');
INSERT INTO `recod` VALUES ('2727', '1');
INSERT INTO `recod` VALUES ('2728', '1');
INSERT INTO `recod` VALUES ('2729', '1');
INSERT INTO `recod` VALUES ('2730', '1');
INSERT INTO `recod` VALUES ('2731', '1');
INSERT INTO `recod` VALUES ('2732', '1');
INSERT INTO `recod` VALUES ('2733', '1');
INSERT INTO `recod` VALUES ('2734', '1');
INSERT INTO `recod` VALUES ('2735', '1');
INSERT INTO `recod` VALUES ('2736', '1');
INSERT INTO `recod` VALUES ('2737', '1');
INSERT INTO `recod` VALUES ('2738', '1');
INSERT INTO `recod` VALUES ('2739', '1');
INSERT INTO `recod` VALUES ('2740', '1');
INSERT INTO `recod` VALUES ('2741', '1');
INSERT INTO `recod` VALUES ('2742', '1');
INSERT INTO `recod` VALUES ('2743', '1');
INSERT INTO `recod` VALUES ('2744', '1');
INSERT INTO `recod` VALUES ('2745', '1');
INSERT INTO `recod` VALUES ('2746', '1');
INSERT INTO `recod` VALUES ('2747', '1');
INSERT INTO `recod` VALUES ('2748', '1');
INSERT INTO `recod` VALUES ('2749', '1');
INSERT INTO `recod` VALUES ('2750', '1');
INSERT INTO `recod` VALUES ('2751', '1');
INSERT INTO `recod` VALUES ('2752', '1');
INSERT INTO `recod` VALUES ('2753', '1');
INSERT INTO `recod` VALUES ('2754', '1');
INSERT INTO `recod` VALUES ('2755', '1');
INSERT INTO `recod` VALUES ('2756', '1');
INSERT INTO `recod` VALUES ('2757', '1');
INSERT INTO `recod` VALUES ('2758', '1');
INSERT INTO `recod` VALUES ('2759', '1');
INSERT INTO `recod` VALUES ('2760', '1');
INSERT INTO `recod` VALUES ('2761', '1');
INSERT INTO `recod` VALUES ('2762', '1');
INSERT INTO `recod` VALUES ('2763', '1');
INSERT INTO `recod` VALUES ('2764', '1');
INSERT INTO `recod` VALUES ('2765', '1');
INSERT INTO `recod` VALUES ('2766', '1');
INSERT INTO `recod` VALUES ('2767', '1');
INSERT INTO `recod` VALUES ('2768', '1');
INSERT INTO `recod` VALUES ('2769', '1');
INSERT INTO `recod` VALUES ('2770', '1');
INSERT INTO `recod` VALUES ('2771', '1');
INSERT INTO `recod` VALUES ('2772', '1');
INSERT INTO `recod` VALUES ('2773', '1');
INSERT INTO `recod` VALUES ('2774', '1');
INSERT INTO `recod` VALUES ('2775', '1');
INSERT INTO `recod` VALUES ('2776', '1');
INSERT INTO `recod` VALUES ('2777', '1');
INSERT INTO `recod` VALUES ('2778', '1');
INSERT INTO `recod` VALUES ('2779', '1');
INSERT INTO `recod` VALUES ('2780', '1');
INSERT INTO `recod` VALUES ('2781', '1');
INSERT INTO `recod` VALUES ('2782', '1');
INSERT INTO `recod` VALUES ('2783', '1');
INSERT INTO `recod` VALUES ('2784', '1');
INSERT INTO `recod` VALUES ('2785', '1');
INSERT INTO `recod` VALUES ('2786', '1');
INSERT INTO `recod` VALUES ('2787', '1');
INSERT INTO `recod` VALUES ('2788', '1');
INSERT INTO `recod` VALUES ('2789', '1');
INSERT INTO `recod` VALUES ('2790', '1');
INSERT INTO `recod` VALUES ('2791', '1');
INSERT INTO `recod` VALUES ('2792', '1');
INSERT INTO `recod` VALUES ('2793', '1');
INSERT INTO `recod` VALUES ('2794', '1');
INSERT INTO `recod` VALUES ('2795', '1');
INSERT INTO `recod` VALUES ('2796', '1');
INSERT INTO `recod` VALUES ('2797', '1');
INSERT INTO `recod` VALUES ('2798', '1');
INSERT INTO `recod` VALUES ('2799', '1');
INSERT INTO `recod` VALUES ('2800', '1');
INSERT INTO `recod` VALUES ('2801', '1');
INSERT INTO `recod` VALUES ('2802', '1');
INSERT INTO `recod` VALUES ('2803', '1');
INSERT INTO `recod` VALUES ('2804', '1');
INSERT INTO `recod` VALUES ('2805', '1');
INSERT INTO `recod` VALUES ('2806', '1');
INSERT INTO `recod` VALUES ('2807', '1');
INSERT INTO `recod` VALUES ('2808', '1');
INSERT INTO `recod` VALUES ('2809', '1');
INSERT INTO `recod` VALUES ('2810', '1');
INSERT INTO `recod` VALUES ('2811', '1');
INSERT INTO `recod` VALUES ('2812', '1');
INSERT INTO `recod` VALUES ('2813', '1');
INSERT INTO `recod` VALUES ('2814', '1');
INSERT INTO `recod` VALUES ('2815', '1');
INSERT INTO `recod` VALUES ('2816', '1');
INSERT INTO `recod` VALUES ('2817', '1');
INSERT INTO `recod` VALUES ('2818', '1');
INSERT INTO `recod` VALUES ('2819', '1');
INSERT INTO `recod` VALUES ('2820', '1');
INSERT INTO `recod` VALUES ('2821', '1');
INSERT INTO `recod` VALUES ('2822', '1');
INSERT INTO `recod` VALUES ('2823', '1');
INSERT INTO `recod` VALUES ('2824', '1');
INSERT INTO `recod` VALUES ('2825', '1');
INSERT INTO `recod` VALUES ('2826', '1');
INSERT INTO `recod` VALUES ('2827', '1');
INSERT INTO `recod` VALUES ('2828', '1');
INSERT INTO `recod` VALUES ('2829', '1');
INSERT INTO `recod` VALUES ('2830', '1');
INSERT INTO `recod` VALUES ('2831', '1');
INSERT INTO `recod` VALUES ('2832', '1');
INSERT INTO `recod` VALUES ('2833', '1');
INSERT INTO `recod` VALUES ('2834', '1');
INSERT INTO `recod` VALUES ('2835', '1');
INSERT INTO `recod` VALUES ('2836', '1');
INSERT INTO `recod` VALUES ('2837', '1');
INSERT INTO `recod` VALUES ('2838', '1');
INSERT INTO `recod` VALUES ('2839', '1');
INSERT INTO `recod` VALUES ('2840', '1');
INSERT INTO `recod` VALUES ('2841', '1');
INSERT INTO `recod` VALUES ('2842', '1');
INSERT INTO `recod` VALUES ('2843', '1');
INSERT INTO `recod` VALUES ('2844', '1');
INSERT INTO `recod` VALUES ('2845', '1');
INSERT INTO `recod` VALUES ('2846', '1');
INSERT INTO `recod` VALUES ('2847', '1');
INSERT INTO `recod` VALUES ('2848', '1');
INSERT INTO `recod` VALUES ('2849', '1');
INSERT INTO `recod` VALUES ('2850', '1');
INSERT INTO `recod` VALUES ('2851', '1');
INSERT INTO `recod` VALUES ('2852', '1');
INSERT INTO `recod` VALUES ('2853', '1');
INSERT INTO `recod` VALUES ('2854', '1');
INSERT INTO `recod` VALUES ('2855', '1');
INSERT INTO `recod` VALUES ('2856', '1');
INSERT INTO `recod` VALUES ('2857', '1');
INSERT INTO `recod` VALUES ('2858', '1');
INSERT INTO `recod` VALUES ('2859', '1');
INSERT INTO `recod` VALUES ('2860', '1');
INSERT INTO `recod` VALUES ('2861', '1');
INSERT INTO `recod` VALUES ('2862', '1');
INSERT INTO `recod` VALUES ('2863', '1');
INSERT INTO `recod` VALUES ('2864', '1');
INSERT INTO `recod` VALUES ('2865', '1');
INSERT INTO `recod` VALUES ('2866', '1');
INSERT INTO `recod` VALUES ('2867', '1');
INSERT INTO `recod` VALUES ('2868', '1');
INSERT INTO `recod` VALUES ('2869', '1');
INSERT INTO `recod` VALUES ('2870', '1');
INSERT INTO `recod` VALUES ('2871', '1');
INSERT INTO `recod` VALUES ('2872', '1');
INSERT INTO `recod` VALUES ('2873', '1');
INSERT INTO `recod` VALUES ('2874', '1');
INSERT INTO `recod` VALUES ('2875', '1');
INSERT INTO `recod` VALUES ('2876', '1');
INSERT INTO `recod` VALUES ('2877', '1');
INSERT INTO `recod` VALUES ('2878', '1');
INSERT INTO `recod` VALUES ('2879', '1');
INSERT INTO `recod` VALUES ('2880', '1');
INSERT INTO `recod` VALUES ('2881', '1');
INSERT INTO `recod` VALUES ('2882', '1');
INSERT INTO `recod` VALUES ('2883', '1');
INSERT INTO `recod` VALUES ('2884', '1');
INSERT INTO `recod` VALUES ('2885', '1');
INSERT INTO `recod` VALUES ('2886', '1');
INSERT INTO `recod` VALUES ('2887', '1');
INSERT INTO `recod` VALUES ('2888', '1');
INSERT INTO `recod` VALUES ('2889', '1');
INSERT INTO `recod` VALUES ('2890', '1');
INSERT INTO `recod` VALUES ('2891', '1');
INSERT INTO `recod` VALUES ('2892', '1');
INSERT INTO `recod` VALUES ('2893', '1');
INSERT INTO `recod` VALUES ('2894', '1');
INSERT INTO `recod` VALUES ('2895', '1');
INSERT INTO `recod` VALUES ('2896', '1');
INSERT INTO `recod` VALUES ('2897', '1');
INSERT INTO `recod` VALUES ('2898', '1');
INSERT INTO `recod` VALUES ('2899', '1');
INSERT INTO `recod` VALUES ('2900', '1');
INSERT INTO `recod` VALUES ('2901', '1');
INSERT INTO `recod` VALUES ('2902', '1');
INSERT INTO `recod` VALUES ('2903', '1');
INSERT INTO `recod` VALUES ('2904', '1');
INSERT INTO `recod` VALUES ('2905', '1');
INSERT INTO `recod` VALUES ('2906', '1');
INSERT INTO `recod` VALUES ('2907', '1');
INSERT INTO `recod` VALUES ('2908', '1');
INSERT INTO `recod` VALUES ('2909', '1');
INSERT INTO `recod` VALUES ('2910', '1');
INSERT INTO `recod` VALUES ('2911', '1');
INSERT INTO `recod` VALUES ('2912', '1');
INSERT INTO `recod` VALUES ('2913', '1');
INSERT INTO `recod` VALUES ('2914', '1');
INSERT INTO `recod` VALUES ('2915', '1');
INSERT INTO `recod` VALUES ('2916', '1');
INSERT INTO `recod` VALUES ('2917', '1');
INSERT INTO `recod` VALUES ('2918', '1');
INSERT INTO `recod` VALUES ('2919', '1');
INSERT INTO `recod` VALUES ('2920', '1');
INSERT INTO `recod` VALUES ('2921', '1');
INSERT INTO `recod` VALUES ('2922', '1');
INSERT INTO `recod` VALUES ('2923', '1');
INSERT INTO `recod` VALUES ('2924', '1');
INSERT INTO `recod` VALUES ('2925', '1');
INSERT INTO `recod` VALUES ('2926', '1');
INSERT INTO `recod` VALUES ('2927', '1');
INSERT INTO `recod` VALUES ('2928', '1');
INSERT INTO `recod` VALUES ('2929', '1');
INSERT INTO `recod` VALUES ('2930', '1');
INSERT INTO `recod` VALUES ('2931', '1');
INSERT INTO `recod` VALUES ('2932', '1');
INSERT INTO `recod` VALUES ('2933', '1');
INSERT INTO `recod` VALUES ('2934', '1');
INSERT INTO `recod` VALUES ('2935', '1');
INSERT INTO `recod` VALUES ('2936', '1');
INSERT INTO `recod` VALUES ('2937', '1');
INSERT INTO `recod` VALUES ('2938', '1');
INSERT INTO `recod` VALUES ('2939', '1');
INSERT INTO `recod` VALUES ('2940', '1');
INSERT INTO `recod` VALUES ('2941', '1');
INSERT INTO `recod` VALUES ('2942', '1');
INSERT INTO `recod` VALUES ('2943', '1');
INSERT INTO `recod` VALUES ('2944', '1');
INSERT INTO `recod` VALUES ('2945', '1');
INSERT INTO `recod` VALUES ('2946', '1');
INSERT INTO `recod` VALUES ('2947', '1');
INSERT INTO `recod` VALUES ('2948', '1');
INSERT INTO `recod` VALUES ('2949', '1');
INSERT INTO `recod` VALUES ('2950', '1');
INSERT INTO `recod` VALUES ('2951', '1');
INSERT INTO `recod` VALUES ('2952', '1');
INSERT INTO `recod` VALUES ('2953', '1');
INSERT INTO `recod` VALUES ('2954', '1');
INSERT INTO `recod` VALUES ('2955', '1');
INSERT INTO `recod` VALUES ('2956', '1');
INSERT INTO `recod` VALUES ('2957', '1');
INSERT INTO `recod` VALUES ('2958', '1');
INSERT INTO `recod` VALUES ('2959', '1');
INSERT INTO `recod` VALUES ('2960', '1');
INSERT INTO `recod` VALUES ('2961', '1');
INSERT INTO `recod` VALUES ('2962', '1');
INSERT INTO `recod` VALUES ('2963', '1');
INSERT INTO `recod` VALUES ('2964', '1');
INSERT INTO `recod` VALUES ('2965', '1');
INSERT INTO `recod` VALUES ('2966', '1');
INSERT INTO `recod` VALUES ('2967', '1');
INSERT INTO `recod` VALUES ('2968', '1');
INSERT INTO `recod` VALUES ('2969', '1');
INSERT INTO `recod` VALUES ('2970', '1');
INSERT INTO `recod` VALUES ('2971', '1');
INSERT INTO `recod` VALUES ('2972', '1');
INSERT INTO `recod` VALUES ('2973', '1');
INSERT INTO `recod` VALUES ('2974', '1');
INSERT INTO `recod` VALUES ('2975', '1');
INSERT INTO `recod` VALUES ('2976', '1');
INSERT INTO `recod` VALUES ('2977', '1');
INSERT INTO `recod` VALUES ('2978', '1');
INSERT INTO `recod` VALUES ('2979', '1');
INSERT INTO `recod` VALUES ('2980', '1');
INSERT INTO `recod` VALUES ('2981', '1');
INSERT INTO `recod` VALUES ('2982', '1');
INSERT INTO `recod` VALUES ('2983', '1');
INSERT INTO `recod` VALUES ('2984', '1');
INSERT INTO `recod` VALUES ('2985', '1');
INSERT INTO `recod` VALUES ('2986', '1');
INSERT INTO `recod` VALUES ('2987', '1');
INSERT INTO `recod` VALUES ('2988', '1');
INSERT INTO `recod` VALUES ('2989', '1');
INSERT INTO `recod` VALUES ('2990', '1');
INSERT INTO `recod` VALUES ('2991', '1');
INSERT INTO `recod` VALUES ('2992', '1');
INSERT INTO `recod` VALUES ('2993', '1');
INSERT INTO `recod` VALUES ('2994', '1');
INSERT INTO `recod` VALUES ('2995', '1');
INSERT INTO `recod` VALUES ('2996', '1');
INSERT INTO `recod` VALUES ('2997', '1');
INSERT INTO `recod` VALUES ('2998', '1');
INSERT INTO `recod` VALUES ('2999', '1');
INSERT INTO `recod` VALUES ('3000', '1');
INSERT INTO `recod` VALUES ('3001', '1');
INSERT INTO `recod` VALUES ('3002', '1');
INSERT INTO `recod` VALUES ('3003', '1');
INSERT INTO `recod` VALUES ('3004', '1');
INSERT INTO `recod` VALUES ('3005', '1');
INSERT INTO `recod` VALUES ('3006', '1');
INSERT INTO `recod` VALUES ('3007', '1');
INSERT INTO `recod` VALUES ('3008', '1');
INSERT INTO `recod` VALUES ('3009', '1');
INSERT INTO `recod` VALUES ('3010', '1');
INSERT INTO `recod` VALUES ('3011', '1');
INSERT INTO `recod` VALUES ('3012', '1');
INSERT INTO `recod` VALUES ('3013', '1');
INSERT INTO `recod` VALUES ('3014', '1');
INSERT INTO `recod` VALUES ('3015', '1');
INSERT INTO `recod` VALUES ('3016', '1');
INSERT INTO `recod` VALUES ('3017', '1');
INSERT INTO `recod` VALUES ('3018', '1');
INSERT INTO `recod` VALUES ('3019', '1');
INSERT INTO `recod` VALUES ('3020', '1');
INSERT INTO `recod` VALUES ('3021', '1');
INSERT INTO `recod` VALUES ('3022', '1');
INSERT INTO `recod` VALUES ('3023', '1');
INSERT INTO `recod` VALUES ('3024', '1');
INSERT INTO `recod` VALUES ('3025', '1');
INSERT INTO `recod` VALUES ('3026', '1');
INSERT INTO `recod` VALUES ('3027', '1');
INSERT INTO `recod` VALUES ('3028', '1');
INSERT INTO `recod` VALUES ('3029', '1');
INSERT INTO `recod` VALUES ('3030', '1');
INSERT INTO `recod` VALUES ('3031', '1');
INSERT INTO `recod` VALUES ('3032', '1');
INSERT INTO `recod` VALUES ('3033', '1');
INSERT INTO `recod` VALUES ('3034', '1');
INSERT INTO `recod` VALUES ('3035', '1');
INSERT INTO `recod` VALUES ('3036', '1');
INSERT INTO `recod` VALUES ('3037', '1');
INSERT INTO `recod` VALUES ('3038', '1');
INSERT INTO `recod` VALUES ('3039', '1');
INSERT INTO `recod` VALUES ('3040', '1');
INSERT INTO `recod` VALUES ('3041', '1');
INSERT INTO `recod` VALUES ('3042', '1');
INSERT INTO `recod` VALUES ('3043', '1');
INSERT INTO `recod` VALUES ('3044', '1');
INSERT INTO `recod` VALUES ('3045', '1');
INSERT INTO `recod` VALUES ('3046', '1');
INSERT INTO `recod` VALUES ('3047', '1');
INSERT INTO `recod` VALUES ('3048', '1');
INSERT INTO `recod` VALUES ('3049', '1');
INSERT INTO `recod` VALUES ('3050', '1');
INSERT INTO `recod` VALUES ('3051', '1');
INSERT INTO `recod` VALUES ('3052', '1');
INSERT INTO `recod` VALUES ('3053', '1');
INSERT INTO `recod` VALUES ('3054', '1');
INSERT INTO `recod` VALUES ('3055', '1');
INSERT INTO `recod` VALUES ('3056', '1');
INSERT INTO `recod` VALUES ('3057', '1');
INSERT INTO `recod` VALUES ('3058', '1');
INSERT INTO `recod` VALUES ('3059', '1');
INSERT INTO `recod` VALUES ('3060', '1');
INSERT INTO `recod` VALUES ('3061', '1');
INSERT INTO `recod` VALUES ('3062', '1');
INSERT INTO `recod` VALUES ('3063', '1');
INSERT INTO `recod` VALUES ('3064', '1');
INSERT INTO `recod` VALUES ('3065', '1');
INSERT INTO `recod` VALUES ('3066', '1');
INSERT INTO `recod` VALUES ('3067', '1');
INSERT INTO `recod` VALUES ('3068', '1');
INSERT INTO `recod` VALUES ('3069', '1');
INSERT INTO `recod` VALUES ('3070', '1');
INSERT INTO `recod` VALUES ('3071', '1');
INSERT INTO `recod` VALUES ('3072', '1');
INSERT INTO `recod` VALUES ('3073', '1');
INSERT INTO `recod` VALUES ('3074', '1');
INSERT INTO `recod` VALUES ('3075', '1');
INSERT INTO `recod` VALUES ('3076', '1');
INSERT INTO `recod` VALUES ('3077', '1');
INSERT INTO `recod` VALUES ('3078', '1');
INSERT INTO `recod` VALUES ('3079', '1');
INSERT INTO `recod` VALUES ('3080', '1');
INSERT INTO `recod` VALUES ('3081', '1');
INSERT INTO `recod` VALUES ('3082', '1');
INSERT INTO `recod` VALUES ('3083', '1');
INSERT INTO `recod` VALUES ('3084', '1');
INSERT INTO `recod` VALUES ('3085', '1');
INSERT INTO `recod` VALUES ('3086', '1');
INSERT INTO `recod` VALUES ('3087', '1');
INSERT INTO `recod` VALUES ('3088', '1');
INSERT INTO `recod` VALUES ('3089', '1');
INSERT INTO `recod` VALUES ('3090', '1');
INSERT INTO `recod` VALUES ('3091', '1');
INSERT INTO `recod` VALUES ('3092', '1');
INSERT INTO `recod` VALUES ('3093', '1');
INSERT INTO `recod` VALUES ('3094', '1');
INSERT INTO `recod` VALUES ('3095', '1');
INSERT INTO `recod` VALUES ('3096', '1');
INSERT INTO `recod` VALUES ('3097', '1');
INSERT INTO `recod` VALUES ('3098', '1');
INSERT INTO `recod` VALUES ('3099', '1');
INSERT INTO `recod` VALUES ('3100', '1');
INSERT INTO `recod` VALUES ('3101', '1');
INSERT INTO `recod` VALUES ('3102', '1');
INSERT INTO `recod` VALUES ('3103', '1');
INSERT INTO `recod` VALUES ('3104', '1');
INSERT INTO `recod` VALUES ('3105', '1');
INSERT INTO `recod` VALUES ('3106', '1');
INSERT INTO `recod` VALUES ('3107', '1');
INSERT INTO `recod` VALUES ('3108', '1');
INSERT INTO `recod` VALUES ('3109', '1');
INSERT INTO `recod` VALUES ('3110', '1');
INSERT INTO `recod` VALUES ('3111', '1');
INSERT INTO `recod` VALUES ('3112', '1');
INSERT INTO `recod` VALUES ('3113', '1');
INSERT INTO `recod` VALUES ('3114', '1');
INSERT INTO `recod` VALUES ('3115', '1');
INSERT INTO `recod` VALUES ('3116', '1');
INSERT INTO `recod` VALUES ('3117', '1');
INSERT INTO `recod` VALUES ('3118', '1');
INSERT INTO `recod` VALUES ('3119', '1');
INSERT INTO `recod` VALUES ('3120', '1');
INSERT INTO `recod` VALUES ('3121', '1');
INSERT INTO `recod` VALUES ('3122', '1');
INSERT INTO `recod` VALUES ('3123', '1');
INSERT INTO `recod` VALUES ('3124', '1');
INSERT INTO `recod` VALUES ('3125', '1');
INSERT INTO `recod` VALUES ('3126', '1');
INSERT INTO `recod` VALUES ('3127', '1');
INSERT INTO `recod` VALUES ('3128', '1');
INSERT INTO `recod` VALUES ('3129', '1');
INSERT INTO `recod` VALUES ('3130', '1');
INSERT INTO `recod` VALUES ('3131', '1');
INSERT INTO `recod` VALUES ('3132', '1');
INSERT INTO `recod` VALUES ('3133', '1');
INSERT INTO `recod` VALUES ('3134', '1');
INSERT INTO `recod` VALUES ('3135', '1');
INSERT INTO `recod` VALUES ('3136', '1');
INSERT INTO `recod` VALUES ('3137', '1');
INSERT INTO `recod` VALUES ('3138', '1');
INSERT INTO `recod` VALUES ('3139', '1');
INSERT INTO `recod` VALUES ('3140', '1');
INSERT INTO `recod` VALUES ('3141', '1');
INSERT INTO `recod` VALUES ('3142', '1');
INSERT INTO `recod` VALUES ('3143', '1');
INSERT INTO `recod` VALUES ('3144', '1');
INSERT INTO `recod` VALUES ('3145', '1');
INSERT INTO `recod` VALUES ('3146', '1');
INSERT INTO `recod` VALUES ('3147', '1');
INSERT INTO `recod` VALUES ('3148', '1');
INSERT INTO `recod` VALUES ('3149', '1');
INSERT INTO `recod` VALUES ('3150', '1');
INSERT INTO `recod` VALUES ('3151', '1');
INSERT INTO `recod` VALUES ('3152', '1');
INSERT INTO `recod` VALUES ('3153', '1');
INSERT INTO `recod` VALUES ('3154', '1');
INSERT INTO `recod` VALUES ('3155', '1');
INSERT INTO `recod` VALUES ('3156', '1');
INSERT INTO `recod` VALUES ('3157', '1');
INSERT INTO `recod` VALUES ('3158', '1');
INSERT INTO `recod` VALUES ('3159', '1');
INSERT INTO `recod` VALUES ('3160', '1');
INSERT INTO `recod` VALUES ('3161', '1');
INSERT INTO `recod` VALUES ('3162', '1');
INSERT INTO `recod` VALUES ('3163', '1');
INSERT INTO `recod` VALUES ('3164', '1');
INSERT INTO `recod` VALUES ('3165', '1');
INSERT INTO `recod` VALUES ('3166', '1');
INSERT INTO `recod` VALUES ('3167', '1');
INSERT INTO `recod` VALUES ('3168', '1');
INSERT INTO `recod` VALUES ('3169', '1');
INSERT INTO `recod` VALUES ('3170', '1');
INSERT INTO `recod` VALUES ('3171', '1');
INSERT INTO `recod` VALUES ('3172', '1');
INSERT INTO `recod` VALUES ('3173', '1');
INSERT INTO `recod` VALUES ('3174', '1');
INSERT INTO `recod` VALUES ('3175', '1');
INSERT INTO `recod` VALUES ('3176', '1');
INSERT INTO `recod` VALUES ('3177', '1');
INSERT INTO `recod` VALUES ('3178', '1');
INSERT INTO `recod` VALUES ('3179', '1');
INSERT INTO `recod` VALUES ('3180', '1');
INSERT INTO `recod` VALUES ('3181', '1');
INSERT INTO `recod` VALUES ('3182', '1');
INSERT INTO `recod` VALUES ('3183', '1');
INSERT INTO `recod` VALUES ('3184', '1');
INSERT INTO `recod` VALUES ('3185', '1');
INSERT INTO `recod` VALUES ('3186', '1');
INSERT INTO `recod` VALUES ('3187', '1');
INSERT INTO `recod` VALUES ('3188', '1');
INSERT INTO `recod` VALUES ('3189', '1');
INSERT INTO `recod` VALUES ('3190', '1');
INSERT INTO `recod` VALUES ('3191', '1');
INSERT INTO `recod` VALUES ('3192', '1');
INSERT INTO `recod` VALUES ('3193', '1');
INSERT INTO `recod` VALUES ('3194', '1');
INSERT INTO `recod` VALUES ('3195', '1');
INSERT INTO `recod` VALUES ('3196', '1');
INSERT INTO `recod` VALUES ('3197', '1');
INSERT INTO `recod` VALUES ('3198', '1');
INSERT INTO `recod` VALUES ('3199', '1');
INSERT INTO `recod` VALUES ('3200', '1');
INSERT INTO `recod` VALUES ('3201', '1');
INSERT INTO `recod` VALUES ('3202', '1');
INSERT INTO `recod` VALUES ('3203', '1');
INSERT INTO `recod` VALUES ('3204', '1');
INSERT INTO `recod` VALUES ('3205', '1');
INSERT INTO `recod` VALUES ('3206', '1');
INSERT INTO `recod` VALUES ('3207', '1');
INSERT INTO `recod` VALUES ('3208', '1');
INSERT INTO `recod` VALUES ('3209', '1');
INSERT INTO `recod` VALUES ('3210', '1');
INSERT INTO `recod` VALUES ('3211', '1');
INSERT INTO `recod` VALUES ('3212', '1');
INSERT INTO `recod` VALUES ('3213', '1');
INSERT INTO `recod` VALUES ('3214', '1');
INSERT INTO `recod` VALUES ('3215', '1');
INSERT INTO `recod` VALUES ('3216', '1');
INSERT INTO `recod` VALUES ('3217', '1');
INSERT INTO `recod` VALUES ('3218', '1');
INSERT INTO `recod` VALUES ('3219', '1');
INSERT INTO `recod` VALUES ('3220', '1');
INSERT INTO `recod` VALUES ('3221', '1');
INSERT INTO `recod` VALUES ('3222', '1');
INSERT INTO `recod` VALUES ('3223', '1');
INSERT INTO `recod` VALUES ('3224', '1');
INSERT INTO `recod` VALUES ('3225', '1');
INSERT INTO `recod` VALUES ('3226', '1');
INSERT INTO `recod` VALUES ('3227', '1');
INSERT INTO `recod` VALUES ('3228', '1');
INSERT INTO `recod` VALUES ('3229', '1');
INSERT INTO `recod` VALUES ('3230', '1');
INSERT INTO `recod` VALUES ('3231', '1');
INSERT INTO `recod` VALUES ('3232', '1');
INSERT INTO `recod` VALUES ('3233', '1');
INSERT INTO `recod` VALUES ('3234', '1');
INSERT INTO `recod` VALUES ('3235', '1');
INSERT INTO `recod` VALUES ('3236', '1');
INSERT INTO `recod` VALUES ('3237', '1');
INSERT INTO `recod` VALUES ('3238', '1');
INSERT INTO `recod` VALUES ('3239', '1');
INSERT INTO `recod` VALUES ('3240', '1');
INSERT INTO `recod` VALUES ('3241', '1');
INSERT INTO `recod` VALUES ('3242', '1');
INSERT INTO `recod` VALUES ('3243', '1');
INSERT INTO `recod` VALUES ('3244', '1');
INSERT INTO `recod` VALUES ('3245', '1');
INSERT INTO `recod` VALUES ('3246', '1');
INSERT INTO `recod` VALUES ('3247', '1');
INSERT INTO `recod` VALUES ('3248', '1');
INSERT INTO `recod` VALUES ('3249', '1');
INSERT INTO `recod` VALUES ('3250', '1');
INSERT INTO `recod` VALUES ('3251', '1');
INSERT INTO `recod` VALUES ('3252', '1');
INSERT INTO `recod` VALUES ('3253', '1');
INSERT INTO `recod` VALUES ('3254', '1');
INSERT INTO `recod` VALUES ('3255', '1');
INSERT INTO `recod` VALUES ('3256', '1');
INSERT INTO `recod` VALUES ('3257', '1');
INSERT INTO `recod` VALUES ('3258', '1');
INSERT INTO `recod` VALUES ('3259', '1');
INSERT INTO `recod` VALUES ('3260', '1');
INSERT INTO `recod` VALUES ('3261', '1');
INSERT INTO `recod` VALUES ('3262', '1');
INSERT INTO `recod` VALUES ('3263', '1');
INSERT INTO `recod` VALUES ('3264', '1');
INSERT INTO `recod` VALUES ('3265', '1');
INSERT INTO `recod` VALUES ('3266', '1');
INSERT INTO `recod` VALUES ('3267', '1');
INSERT INTO `recod` VALUES ('3268', '1');
INSERT INTO `recod` VALUES ('3269', '1');
INSERT INTO `recod` VALUES ('3270', '1');
INSERT INTO `recod` VALUES ('3271', '1');
INSERT INTO `recod` VALUES ('3272', '1');
INSERT INTO `recod` VALUES ('3273', '1');
INSERT INTO `recod` VALUES ('3274', '1');
INSERT INTO `recod` VALUES ('3275', '1');
INSERT INTO `recod` VALUES ('3276', '1');
INSERT INTO `recod` VALUES ('3277', '1');
INSERT INTO `recod` VALUES ('3278', '1');
INSERT INTO `recod` VALUES ('3279', '1');
INSERT INTO `recod` VALUES ('3280', '1');
INSERT INTO `recod` VALUES ('3281', '1');
INSERT INTO `recod` VALUES ('3282', '1');
INSERT INTO `recod` VALUES ('3283', '1');
INSERT INTO `recod` VALUES ('3284', '1');
INSERT INTO `recod` VALUES ('3285', '1');
INSERT INTO `recod` VALUES ('3286', '1');
INSERT INTO `recod` VALUES ('3287', '1');
INSERT INTO `recod` VALUES ('3288', '1');
INSERT INTO `recod` VALUES ('3289', '1');
INSERT INTO `recod` VALUES ('3290', '1');
INSERT INTO `recod` VALUES ('3291', '1');
INSERT INTO `recod` VALUES ('3292', '1');
INSERT INTO `recod` VALUES ('3293', '1');
INSERT INTO `recod` VALUES ('3294', '1');
INSERT INTO `recod` VALUES ('3295', '1');
INSERT INTO `recod` VALUES ('3296', '1');
INSERT INTO `recod` VALUES ('3297', '1');
INSERT INTO `recod` VALUES ('3298', '1');
INSERT INTO `recod` VALUES ('3299', '1');
INSERT INTO `recod` VALUES ('3300', '1');
INSERT INTO `recod` VALUES ('3301', '1');
INSERT INTO `recod` VALUES ('3302', '1');
INSERT INTO `recod` VALUES ('3303', '1');
INSERT INTO `recod` VALUES ('3304', '1');
INSERT INTO `recod` VALUES ('3305', '1');
INSERT INTO `recod` VALUES ('3306', '1');
INSERT INTO `recod` VALUES ('3307', '1');
INSERT INTO `recod` VALUES ('3308', '1');
INSERT INTO `recod` VALUES ('3309', '1');
INSERT INTO `recod` VALUES ('3310', '1');
INSERT INTO `recod` VALUES ('3311', '1');
INSERT INTO `recod` VALUES ('3312', '1');
INSERT INTO `recod` VALUES ('3313', '1');
INSERT INTO `recod` VALUES ('3314', '1');
INSERT INTO `recod` VALUES ('3315', '1');
INSERT INTO `recod` VALUES ('3316', '1');
INSERT INTO `recod` VALUES ('3317', '1');
INSERT INTO `recod` VALUES ('3318', '1');
INSERT INTO `recod` VALUES ('3319', '1');
INSERT INTO `recod` VALUES ('3320', '1');
INSERT INTO `recod` VALUES ('3321', '1');
INSERT INTO `recod` VALUES ('3322', '1');
INSERT INTO `recod` VALUES ('3323', '1');
INSERT INTO `recod` VALUES ('3324', '1');
INSERT INTO `recod` VALUES ('3325', '1');
INSERT INTO `recod` VALUES ('3326', '1');
INSERT INTO `recod` VALUES ('3327', '1');
INSERT INTO `recod` VALUES ('3328', '1');
INSERT INTO `recod` VALUES ('3329', '1');
INSERT INTO `recod` VALUES ('3330', '1');
INSERT INTO `recod` VALUES ('3331', '1');
INSERT INTO `recod` VALUES ('3332', '1');
INSERT INTO `recod` VALUES ('3333', '1');
INSERT INTO `recod` VALUES ('3334', '1');
INSERT INTO `recod` VALUES ('3335', '1');
INSERT INTO `recod` VALUES ('3336', '1');
INSERT INTO `recod` VALUES ('3337', '1');
INSERT INTO `recod` VALUES ('3338', '1');
INSERT INTO `recod` VALUES ('3339', '1');
INSERT INTO `recod` VALUES ('3340', '1');
INSERT INTO `recod` VALUES ('3341', '1');
INSERT INTO `recod` VALUES ('3342', '1');
INSERT INTO `recod` VALUES ('3343', '1');
INSERT INTO `recod` VALUES ('3344', '1');
INSERT INTO `recod` VALUES ('3345', '1');
INSERT INTO `recod` VALUES ('3346', '1');
INSERT INTO `recod` VALUES ('3347', '1');
INSERT INTO `recod` VALUES ('3348', '1');
INSERT INTO `recod` VALUES ('3349', '1');
INSERT INTO `recod` VALUES ('3350', '1');
INSERT INTO `recod` VALUES ('3351', '1');
INSERT INTO `recod` VALUES ('3352', '1');
INSERT INTO `recod` VALUES ('3353', '1');
INSERT INTO `recod` VALUES ('3354', '1');
INSERT INTO `recod` VALUES ('3355', '1');
INSERT INTO `recod` VALUES ('3356', '1');
INSERT INTO `recod` VALUES ('3357', '1');
INSERT INTO `recod` VALUES ('3358', '1');
INSERT INTO `recod` VALUES ('3359', '1');
INSERT INTO `recod` VALUES ('3360', '1');
INSERT INTO `recod` VALUES ('3361', '1');
INSERT INTO `recod` VALUES ('3362', '1');
INSERT INTO `recod` VALUES ('3363', '1');
INSERT INTO `recod` VALUES ('3364', '1');
INSERT INTO `recod` VALUES ('3365', '1');
INSERT INTO `recod` VALUES ('3366', '1');
INSERT INTO `recod` VALUES ('3367', '1');
INSERT INTO `recod` VALUES ('3368', '1');
INSERT INTO `recod` VALUES ('3369', '1');
INSERT INTO `recod` VALUES ('3370', '1');
INSERT INTO `recod` VALUES ('3371', '1');
INSERT INTO `recod` VALUES ('3372', '1');
INSERT INTO `recod` VALUES ('3373', '1');
INSERT INTO `recod` VALUES ('3374', '1');
INSERT INTO `recod` VALUES ('3375', '1');
INSERT INTO `recod` VALUES ('3376', '1');
INSERT INTO `recod` VALUES ('3377', '1');
INSERT INTO `recod` VALUES ('3378', '1');
INSERT INTO `recod` VALUES ('3379', '1');
INSERT INTO `recod` VALUES ('3380', '1');
INSERT INTO `recod` VALUES ('3381', '1');
INSERT INTO `recod` VALUES ('3382', '1');
INSERT INTO `recod` VALUES ('3383', '1');
INSERT INTO `recod` VALUES ('3384', '1');
INSERT INTO `recod` VALUES ('3385', '1');
INSERT INTO `recod` VALUES ('3386', '1');
INSERT INTO `recod` VALUES ('3387', '1');
INSERT INTO `recod` VALUES ('3388', '1');
INSERT INTO `recod` VALUES ('3389', '1');
INSERT INTO `recod` VALUES ('3390', '1');
INSERT INTO `recod` VALUES ('3391', '1');
INSERT INTO `recod` VALUES ('3392', '1');
INSERT INTO `recod` VALUES ('3393', '1');
INSERT INTO `recod` VALUES ('3394', '1');
INSERT INTO `recod` VALUES ('3395', '1');
INSERT INTO `recod` VALUES ('3396', '1');
INSERT INTO `recod` VALUES ('3397', '1');
INSERT INTO `recod` VALUES ('3398', '1');
INSERT INTO `recod` VALUES ('3399', '1');
INSERT INTO `recod` VALUES ('3400', '1');
INSERT INTO `recod` VALUES ('3401', '1');
INSERT INTO `recod` VALUES ('3402', '1');
INSERT INTO `recod` VALUES ('3403', '1');
INSERT INTO `recod` VALUES ('3404', '1');
INSERT INTO `recod` VALUES ('3405', '1');
INSERT INTO `recod` VALUES ('3406', '1');
INSERT INTO `recod` VALUES ('3407', '1');
INSERT INTO `recod` VALUES ('3408', '1');
INSERT INTO `recod` VALUES ('3409', '1');
INSERT INTO `recod` VALUES ('3410', '1');
INSERT INTO `recod` VALUES ('3411', '1');
INSERT INTO `recod` VALUES ('3412', '1');
INSERT INTO `recod` VALUES ('3413', '1');
INSERT INTO `recod` VALUES ('3414', '1');
INSERT INTO `recod` VALUES ('3415', '1');
INSERT INTO `recod` VALUES ('3416', '1');
INSERT INTO `recod` VALUES ('3417', '1');
INSERT INTO `recod` VALUES ('3418', '1');
INSERT INTO `recod` VALUES ('3419', '1');
INSERT INTO `recod` VALUES ('3420', '1');
INSERT INTO `recod` VALUES ('3421', '1');
INSERT INTO `recod` VALUES ('3422', '1');
INSERT INTO `recod` VALUES ('3423', '1');
INSERT INTO `recod` VALUES ('3424', '1');
INSERT INTO `recod` VALUES ('3425', '1');
INSERT INTO `recod` VALUES ('3426', '1');
INSERT INTO `recod` VALUES ('3427', '1');
INSERT INTO `recod` VALUES ('3428', '1');
INSERT INTO `recod` VALUES ('3429', '1');
INSERT INTO `recod` VALUES ('3430', '1');
INSERT INTO `recod` VALUES ('3431', '1');
INSERT INTO `recod` VALUES ('3432', '1');
INSERT INTO `recod` VALUES ('3433', '1');
INSERT INTO `recod` VALUES ('3434', '1');
INSERT INTO `recod` VALUES ('3435', '1');
INSERT INTO `recod` VALUES ('3436', '1');
INSERT INTO `recod` VALUES ('3437', '1');
INSERT INTO `recod` VALUES ('3438', '1');
INSERT INTO `recod` VALUES ('3439', '1');
INSERT INTO `recod` VALUES ('3440', '1');
INSERT INTO `recod` VALUES ('3441', '1');
INSERT INTO `recod` VALUES ('3442', '1');
INSERT INTO `recod` VALUES ('3443', '1');
INSERT INTO `recod` VALUES ('3444', '1');
INSERT INTO `recod` VALUES ('3445', '1');
INSERT INTO `recod` VALUES ('3446', '1');
INSERT INTO `recod` VALUES ('3447', '1');
INSERT INTO `recod` VALUES ('3448', '1');
INSERT INTO `recod` VALUES ('3449', '1');
INSERT INTO `recod` VALUES ('3450', '1');
INSERT INTO `recod` VALUES ('3451', '1');
INSERT INTO `recod` VALUES ('3452', '1');
INSERT INTO `recod` VALUES ('3453', '1');
INSERT INTO `recod` VALUES ('3454', '1');
INSERT INTO `recod` VALUES ('3455', '1');
INSERT INTO `recod` VALUES ('3456', '1');
INSERT INTO `recod` VALUES ('3457', '1');
INSERT INTO `recod` VALUES ('3458', '1');
INSERT INTO `recod` VALUES ('3459', '1');
INSERT INTO `recod` VALUES ('3460', '1');
INSERT INTO `recod` VALUES ('3461', '1');
INSERT INTO `recod` VALUES ('3462', '1');
INSERT INTO `recod` VALUES ('3463', '1');
INSERT INTO `recod` VALUES ('3464', '1');
INSERT INTO `recod` VALUES ('3465', '1');
INSERT INTO `recod` VALUES ('3466', '1');
INSERT INTO `recod` VALUES ('3467', '1');
INSERT INTO `recod` VALUES ('3468', '1');
INSERT INTO `recod` VALUES ('3469', '1');
INSERT INTO `recod` VALUES ('3470', '1');
INSERT INTO `recod` VALUES ('3471', '1');
INSERT INTO `recod` VALUES ('3472', '1');
INSERT INTO `recod` VALUES ('3473', '1');
INSERT INTO `recod` VALUES ('3474', '1');
INSERT INTO `recod` VALUES ('3475', '1');
INSERT INTO `recod` VALUES ('3476', '1');
INSERT INTO `recod` VALUES ('3477', '1');
INSERT INTO `recod` VALUES ('3478', '1');
INSERT INTO `recod` VALUES ('3479', '1');
INSERT INTO `recod` VALUES ('3480', '1');
INSERT INTO `recod` VALUES ('3481', '1');
INSERT INTO `recod` VALUES ('3482', '1');
INSERT INTO `recod` VALUES ('3483', '1');
INSERT INTO `recod` VALUES ('3484', '1');
INSERT INTO `recod` VALUES ('3485', '1');
INSERT INTO `recod` VALUES ('3486', '1');
INSERT INTO `recod` VALUES ('3487', '1');
INSERT INTO `recod` VALUES ('3488', '1');
INSERT INTO `recod` VALUES ('3489', '1');
INSERT INTO `recod` VALUES ('3490', '1');
INSERT INTO `recod` VALUES ('3491', '1');
INSERT INTO `recod` VALUES ('3492', '1');
INSERT INTO `recod` VALUES ('3493', '1');
INSERT INTO `recod` VALUES ('3494', '1');
INSERT INTO `recod` VALUES ('3495', '1');
INSERT INTO `recod` VALUES ('3496', '1');
INSERT INTO `recod` VALUES ('3497', '1');
INSERT INTO `recod` VALUES ('3498', '1');
INSERT INTO `recod` VALUES ('3499', '1');
INSERT INTO `recod` VALUES ('3500', '1');
INSERT INTO `recod` VALUES ('3501', '1');
INSERT INTO `recod` VALUES ('3502', '1');
INSERT INTO `recod` VALUES ('3503', '1');
INSERT INTO `recod` VALUES ('3504', '1');
INSERT INTO `recod` VALUES ('3505', '1');
INSERT INTO `recod` VALUES ('3506', '1');
INSERT INTO `recod` VALUES ('3507', '1');
INSERT INTO `recod` VALUES ('3508', '1');
INSERT INTO `recod` VALUES ('3509', '1');
INSERT INTO `recod` VALUES ('3510', '1');
INSERT INTO `recod` VALUES ('3511', '1');
INSERT INTO `recod` VALUES ('3512', '1');
INSERT INTO `recod` VALUES ('3513', '1');
INSERT INTO `recod` VALUES ('3514', '1');
INSERT INTO `recod` VALUES ('3515', '1');
INSERT INTO `recod` VALUES ('3516', '1');
INSERT INTO `recod` VALUES ('3517', '1');
INSERT INTO `recod` VALUES ('3518', '1');
INSERT INTO `recod` VALUES ('3519', '1');
INSERT INTO `recod` VALUES ('3520', '1');
INSERT INTO `recod` VALUES ('3521', '1');
INSERT INTO `recod` VALUES ('3522', '1');
INSERT INTO `recod` VALUES ('3523', '1');
INSERT INTO `recod` VALUES ('3524', '1');
INSERT INTO `recod` VALUES ('3525', '1');
INSERT INTO `recod` VALUES ('3526', '1');
INSERT INTO `recod` VALUES ('3527', '1');
INSERT INTO `recod` VALUES ('3528', '1');
INSERT INTO `recod` VALUES ('3529', '1');
INSERT INTO `recod` VALUES ('3530', '1');
INSERT INTO `recod` VALUES ('3531', '1');
INSERT INTO `recod` VALUES ('3532', '1');
INSERT INTO `recod` VALUES ('3533', '1');
INSERT INTO `recod` VALUES ('3534', '1');
INSERT INTO `recod` VALUES ('3535', '1');
INSERT INTO `recod` VALUES ('3536', '1');
INSERT INTO `recod` VALUES ('3537', '1');
INSERT INTO `recod` VALUES ('3538', '1');
INSERT INTO `recod` VALUES ('3539', '1');
INSERT INTO `recod` VALUES ('3540', '1');
INSERT INTO `recod` VALUES ('3541', '1');
INSERT INTO `recod` VALUES ('3542', '1');
INSERT INTO `recod` VALUES ('3543', '1');
INSERT INTO `recod` VALUES ('3544', '1');
INSERT INTO `recod` VALUES ('3545', '1');
INSERT INTO `recod` VALUES ('3546', '1');
INSERT INTO `recod` VALUES ('3547', '1');
INSERT INTO `recod` VALUES ('3548', '1');
INSERT INTO `recod` VALUES ('3549', '1');
INSERT INTO `recod` VALUES ('3550', '1');
INSERT INTO `recod` VALUES ('3551', '1');
INSERT INTO `recod` VALUES ('3552', '1');
INSERT INTO `recod` VALUES ('3553', '1');
INSERT INTO `recod` VALUES ('3554', '1');
INSERT INTO `recod` VALUES ('3555', '1');
INSERT INTO `recod` VALUES ('3556', '1');
INSERT INTO `recod` VALUES ('3557', '1');
INSERT INTO `recod` VALUES ('3558', '1');
INSERT INTO `recod` VALUES ('3559', '1');
INSERT INTO `recod` VALUES ('3560', '1');
INSERT INTO `recod` VALUES ('3561', '1');
INSERT INTO `recod` VALUES ('3562', '1');
INSERT INTO `recod` VALUES ('3563', '1');
INSERT INTO `recod` VALUES ('3564', '1');
INSERT INTO `recod` VALUES ('3565', '1');
INSERT INTO `recod` VALUES ('3566', '1');
INSERT INTO `recod` VALUES ('3567', '1');
INSERT INTO `recod` VALUES ('3568', '1');
INSERT INTO `recod` VALUES ('3569', '1');
INSERT INTO `recod` VALUES ('3570', '1');
INSERT INTO `recod` VALUES ('3571', '1');
INSERT INTO `recod` VALUES ('3572', '1');
INSERT INTO `recod` VALUES ('3573', '1');
INSERT INTO `recod` VALUES ('3574', '1');
INSERT INTO `recod` VALUES ('3575', '1');
INSERT INTO `recod` VALUES ('3576', '1');
INSERT INTO `recod` VALUES ('3577', '1');
INSERT INTO `recod` VALUES ('3578', '1');
INSERT INTO `recod` VALUES ('3579', '1');
INSERT INTO `recod` VALUES ('3580', '1');
INSERT INTO `recod` VALUES ('3581', '1');
INSERT INTO `recod` VALUES ('3582', '1');
INSERT INTO `recod` VALUES ('3583', '1');
INSERT INTO `recod` VALUES ('3584', '1');
INSERT INTO `recod` VALUES ('3585', '1');
INSERT INTO `recod` VALUES ('3586', '1');
INSERT INTO `recod` VALUES ('3587', '1');
INSERT INTO `recod` VALUES ('3588', '1');
INSERT INTO `recod` VALUES ('3589', '1');
INSERT INTO `recod` VALUES ('3590', '1');
INSERT INTO `recod` VALUES ('3591', '1');
INSERT INTO `recod` VALUES ('3592', '1');
INSERT INTO `recod` VALUES ('3593', '1');
INSERT INTO `recod` VALUES ('3594', '1');
INSERT INTO `recod` VALUES ('3595', '1');
INSERT INTO `recod` VALUES ('3596', '1');
INSERT INTO `recod` VALUES ('3597', '1');
INSERT INTO `recod` VALUES ('3598', '1');
INSERT INTO `recod` VALUES ('3599', '1');
INSERT INTO `recod` VALUES ('3600', '1');
INSERT INTO `recod` VALUES ('3601', '1');
INSERT INTO `recod` VALUES ('3602', '1');
INSERT INTO `recod` VALUES ('3603', '1');
INSERT INTO `recod` VALUES ('3604', '1');
INSERT INTO `recod` VALUES ('3605', '1');
INSERT INTO `recod` VALUES ('3606', '1');
INSERT INTO `recod` VALUES ('3607', '1');
INSERT INTO `recod` VALUES ('3608', '1');
INSERT INTO `recod` VALUES ('3609', '1');
INSERT INTO `recod` VALUES ('3610', '1');
INSERT INTO `recod` VALUES ('3611', '1');
INSERT INTO `recod` VALUES ('3612', '1');
INSERT INTO `recod` VALUES ('3613', '1');
INSERT INTO `recod` VALUES ('3614', '1');
INSERT INTO `recod` VALUES ('3615', '1');
INSERT INTO `recod` VALUES ('3616', '1');
INSERT INTO `recod` VALUES ('3617', '1');
INSERT INTO `recod` VALUES ('3618', '1');
INSERT INTO `recod` VALUES ('3619', '1');
INSERT INTO `recod` VALUES ('3620', '1');
INSERT INTO `recod` VALUES ('3621', '1');
INSERT INTO `recod` VALUES ('3622', '1');
INSERT INTO `recod` VALUES ('3623', '1');
INSERT INTO `recod` VALUES ('3624', '1');
INSERT INTO `recod` VALUES ('3625', '1');
INSERT INTO `recod` VALUES ('3626', '1');
INSERT INTO `recod` VALUES ('3627', '1');
INSERT INTO `recod` VALUES ('3628', '1');
INSERT INTO `recod` VALUES ('3629', '1');
INSERT INTO `recod` VALUES ('3630', '1');
INSERT INTO `recod` VALUES ('3631', '1');
INSERT INTO `recod` VALUES ('3632', '1');
INSERT INTO `recod` VALUES ('3633', '1');
INSERT INTO `recod` VALUES ('3634', '1');
INSERT INTO `recod` VALUES ('3635', '1');
INSERT INTO `recod` VALUES ('3636', '1');
INSERT INTO `recod` VALUES ('3637', '1');
INSERT INTO `recod` VALUES ('3638', '1');
INSERT INTO `recod` VALUES ('3639', '1');
INSERT INTO `recod` VALUES ('3640', '1');
INSERT INTO `recod` VALUES ('3641', '1');
INSERT INTO `recod` VALUES ('3642', '1');
INSERT INTO `recod` VALUES ('3643', '1');
INSERT INTO `recod` VALUES ('3644', '1');
INSERT INTO `recod` VALUES ('3645', '1');
INSERT INTO `recod` VALUES ('3646', '1');
INSERT INTO `recod` VALUES ('3647', '1');
INSERT INTO `recod` VALUES ('3648', '1');
INSERT INTO `recod` VALUES ('3649', '1');
INSERT INTO `recod` VALUES ('3650', '1');
INSERT INTO `recod` VALUES ('3651', '1');
INSERT INTO `recod` VALUES ('3652', '1');
INSERT INTO `recod` VALUES ('3653', '1');
INSERT INTO `recod` VALUES ('3654', '1');
INSERT INTO `recod` VALUES ('3655', '1');
INSERT INTO `recod` VALUES ('3656', '1');
INSERT INTO `recod` VALUES ('3657', '1');
INSERT INTO `recod` VALUES ('3658', '1');
INSERT INTO `recod` VALUES ('3659', '1');
INSERT INTO `recod` VALUES ('3660', '1');
INSERT INTO `recod` VALUES ('3661', '1');
INSERT INTO `recod` VALUES ('3662', '1');
INSERT INTO `recod` VALUES ('3663', '1');
INSERT INTO `recod` VALUES ('3664', '1');
INSERT INTO `recod` VALUES ('3665', '1');
INSERT INTO `recod` VALUES ('3666', '1');
INSERT INTO `recod` VALUES ('3667', '1');
INSERT INTO `recod` VALUES ('3668', '1');
INSERT INTO `recod` VALUES ('3669', '1');
INSERT INTO `recod` VALUES ('3670', '1');
INSERT INTO `recod` VALUES ('3671', '1');
INSERT INTO `recod` VALUES ('3672', '1');
INSERT INTO `recod` VALUES ('3673', '1');
INSERT INTO `recod` VALUES ('3674', '1');
INSERT INTO `recod` VALUES ('3675', '1');
INSERT INTO `recod` VALUES ('3676', '1');
INSERT INTO `recod` VALUES ('3677', '1');
INSERT INTO `recod` VALUES ('3678', '1');
INSERT INTO `recod` VALUES ('3679', '1');
INSERT INTO `recod` VALUES ('3680', '1');
INSERT INTO `recod` VALUES ('3681', '1');
INSERT INTO `recod` VALUES ('3682', '1');
INSERT INTO `recod` VALUES ('3683', '1');
INSERT INTO `recod` VALUES ('3684', '1');
INSERT INTO `recod` VALUES ('3685', '1');
INSERT INTO `recod` VALUES ('3686', '1');
INSERT INTO `recod` VALUES ('3687', '1');
INSERT INTO `recod` VALUES ('3688', '1');
INSERT INTO `recod` VALUES ('3689', '1');
INSERT INTO `recod` VALUES ('3690', '1');
INSERT INTO `recod` VALUES ('3691', '1');
INSERT INTO `recod` VALUES ('3692', '1');
INSERT INTO `recod` VALUES ('3693', '1');
INSERT INTO `recod` VALUES ('3694', '1');
INSERT INTO `recod` VALUES ('3695', '1');
INSERT INTO `recod` VALUES ('3696', '1');
INSERT INTO `recod` VALUES ('3697', '1');
INSERT INTO `recod` VALUES ('3698', '1');
INSERT INTO `recod` VALUES ('3699', '1');
INSERT INTO `recod` VALUES ('3700', '1');
INSERT INTO `recod` VALUES ('3701', '1');
INSERT INTO `recod` VALUES ('3702', '1');
INSERT INTO `recod` VALUES ('3703', '1');
INSERT INTO `recod` VALUES ('3704', '1');
INSERT INTO `recod` VALUES ('3705', '1');
INSERT INTO `recod` VALUES ('3706', '1');
INSERT INTO `recod` VALUES ('3707', '1');
INSERT INTO `recod` VALUES ('3708', '1');
INSERT INTO `recod` VALUES ('3709', '1');
INSERT INTO `recod` VALUES ('3710', '1');
INSERT INTO `recod` VALUES ('3711', '1');
INSERT INTO `recod` VALUES ('3712', '1');
INSERT INTO `recod` VALUES ('3713', '1');
INSERT INTO `recod` VALUES ('3714', '1');
INSERT INTO `recod` VALUES ('3715', '1');
INSERT INTO `recod` VALUES ('3716', '1');
INSERT INTO `recod` VALUES ('3717', '1');
INSERT INTO `recod` VALUES ('3718', '1');
INSERT INTO `recod` VALUES ('3719', '1');
INSERT INTO `recod` VALUES ('3720', '1');
INSERT INTO `recod` VALUES ('3721', '1');
INSERT INTO `recod` VALUES ('3722', '1');
INSERT INTO `recod` VALUES ('3723', '1');
INSERT INTO `recod` VALUES ('3724', '1');
INSERT INTO `recod` VALUES ('3725', '1');
INSERT INTO `recod` VALUES ('3726', '1');
INSERT INTO `recod` VALUES ('3727', '1');
INSERT INTO `recod` VALUES ('3728', '1');
INSERT INTO `recod` VALUES ('3729', '1');
INSERT INTO `recod` VALUES ('3730', '1');
INSERT INTO `recod` VALUES ('3731', '1');
INSERT INTO `recod` VALUES ('3732', '1');
INSERT INTO `recod` VALUES ('3733', '1');
INSERT INTO `recod` VALUES ('3734', '1');
INSERT INTO `recod` VALUES ('3735', '1');
INSERT INTO `recod` VALUES ('3736', '1');
INSERT INTO `recod` VALUES ('3737', '1');
INSERT INTO `recod` VALUES ('3738', '1');
INSERT INTO `recod` VALUES ('3739', '1');
INSERT INTO `recod` VALUES ('3740', '1');
INSERT INTO `recod` VALUES ('3741', '1');
INSERT INTO `recod` VALUES ('3742', '1');
INSERT INTO `recod` VALUES ('3743', '1');
INSERT INTO `recod` VALUES ('3744', '1');
INSERT INTO `recod` VALUES ('3745', '1');
INSERT INTO `recod` VALUES ('3746', '1');
INSERT INTO `recod` VALUES ('3747', '1');
INSERT INTO `recod` VALUES ('3748', '1');
INSERT INTO `recod` VALUES ('3749', '1');
INSERT INTO `recod` VALUES ('3750', '1');
INSERT INTO `recod` VALUES ('3751', '1');
INSERT INTO `recod` VALUES ('3752', '1');
INSERT INTO `recod` VALUES ('3753', '1');
INSERT INTO `recod` VALUES ('3754', '1');
INSERT INTO `recod` VALUES ('3755', '1');
INSERT INTO `recod` VALUES ('3756', '1');
INSERT INTO `recod` VALUES ('3757', '1');
INSERT INTO `recod` VALUES ('3758', '1');
INSERT INTO `recod` VALUES ('3759', '1');
INSERT INTO `recod` VALUES ('3760', '1');
INSERT INTO `recod` VALUES ('3761', '1');
INSERT INTO `recod` VALUES ('3762', '1');
INSERT INTO `recod` VALUES ('3763', '1');
INSERT INTO `recod` VALUES ('3764', '1');
INSERT INTO `recod` VALUES ('3765', '1');
INSERT INTO `recod` VALUES ('3766', '1');
INSERT INTO `recod` VALUES ('3767', '1');
INSERT INTO `recod` VALUES ('3768', '1');
INSERT INTO `recod` VALUES ('3769', '1');
INSERT INTO `recod` VALUES ('3770', '1');
INSERT INTO `recod` VALUES ('3771', '1');
INSERT INTO `recod` VALUES ('3772', '1');
INSERT INTO `recod` VALUES ('3773', '1');
INSERT INTO `recod` VALUES ('3774', '1');
INSERT INTO `recod` VALUES ('3775', '1');
INSERT INTO `recod` VALUES ('3776', '1');
INSERT INTO `recod` VALUES ('3777', '1');
INSERT INTO `recod` VALUES ('3778', '1');
INSERT INTO `recod` VALUES ('3779', '1');
INSERT INTO `recod` VALUES ('3780', '1');
INSERT INTO `recod` VALUES ('3781', '1');
INSERT INTO `recod` VALUES ('3782', '1');
INSERT INTO `recod` VALUES ('3783', '1');
INSERT INTO `recod` VALUES ('3784', '1');
INSERT INTO `recod` VALUES ('3785', '1');
INSERT INTO `recod` VALUES ('3786', '1');
INSERT INTO `recod` VALUES ('3787', '1');
INSERT INTO `recod` VALUES ('3788', '1');
INSERT INTO `recod` VALUES ('3789', '1');
INSERT INTO `recod` VALUES ('3790', '1');
INSERT INTO `recod` VALUES ('3791', '1');
INSERT INTO `recod` VALUES ('3792', '1');
INSERT INTO `recod` VALUES ('3793', '1');
INSERT INTO `recod` VALUES ('3794', '1');
INSERT INTO `recod` VALUES ('3795', '1');
INSERT INTO `recod` VALUES ('3796', '1');
INSERT INTO `recod` VALUES ('3797', '1');
INSERT INTO `recod` VALUES ('3798', '1');
INSERT INTO `recod` VALUES ('3799', '1');
INSERT INTO `recod` VALUES ('3800', '1');
INSERT INTO `recod` VALUES ('3801', '1');
INSERT INTO `recod` VALUES ('3802', '1');
INSERT INTO `recod` VALUES ('3803', '1');
INSERT INTO `recod` VALUES ('3804', '1');
INSERT INTO `recod` VALUES ('3805', '1');
INSERT INTO `recod` VALUES ('3806', '1');
INSERT INTO `recod` VALUES ('3807', '1');
INSERT INTO `recod` VALUES ('3808', '1');
INSERT INTO `recod` VALUES ('3809', '1');
INSERT INTO `recod` VALUES ('3810', '1');
INSERT INTO `recod` VALUES ('3811', '1');
INSERT INTO `recod` VALUES ('3812', '1');
INSERT INTO `recod` VALUES ('3813', '1');
INSERT INTO `recod` VALUES ('3814', '1');
INSERT INTO `recod` VALUES ('3815', '1');
INSERT INTO `recod` VALUES ('3816', '1');
INSERT INTO `recod` VALUES ('3817', '1');
INSERT INTO `recod` VALUES ('3818', '1');
INSERT INTO `recod` VALUES ('3819', '1');
INSERT INTO `recod` VALUES ('3820', '1');
INSERT INTO `recod` VALUES ('3821', '1');
INSERT INTO `recod` VALUES ('3822', '1');
INSERT INTO `recod` VALUES ('3823', '1');
INSERT INTO `recod` VALUES ('3824', '1');
INSERT INTO `recod` VALUES ('3825', '1');
INSERT INTO `recod` VALUES ('3826', '1');
INSERT INTO `recod` VALUES ('3827', '1');
INSERT INTO `recod` VALUES ('3828', '1');
INSERT INTO `recod` VALUES ('3829', '1');
INSERT INTO `recod` VALUES ('3830', '1');
INSERT INTO `recod` VALUES ('3831', '1');
INSERT INTO `recod` VALUES ('3832', '1');
INSERT INTO `recod` VALUES ('3833', '1');
INSERT INTO `recod` VALUES ('3834', '1');
INSERT INTO `recod` VALUES ('3835', '1');
INSERT INTO `recod` VALUES ('3836', '1');
INSERT INTO `recod` VALUES ('3837', '1');
INSERT INTO `recod` VALUES ('3838', '1');
INSERT INTO `recod` VALUES ('3839', '1');
INSERT INTO `recod` VALUES ('3840', '1');
INSERT INTO `recod` VALUES ('3841', '1');
INSERT INTO `recod` VALUES ('3842', '1');
INSERT INTO `recod` VALUES ('3843', '1');
INSERT INTO `recod` VALUES ('3844', '1');
INSERT INTO `recod` VALUES ('3845', '1');
INSERT INTO `recod` VALUES ('3846', '1');
INSERT INTO `recod` VALUES ('3847', '1');
INSERT INTO `recod` VALUES ('3848', '1');
INSERT INTO `recod` VALUES ('3849', '1');
INSERT INTO `recod` VALUES ('3850', '1');
INSERT INTO `recod` VALUES ('3851', '1');
INSERT INTO `recod` VALUES ('3852', '1');
INSERT INTO `recod` VALUES ('3853', '1');
INSERT INTO `recod` VALUES ('3854', '1');
INSERT INTO `recod` VALUES ('3855', '1');
INSERT INTO `recod` VALUES ('3856', '1');
INSERT INTO `recod` VALUES ('3857', '1');
INSERT INTO `recod` VALUES ('3858', '1');
INSERT INTO `recod` VALUES ('3859', '1');
INSERT INTO `recod` VALUES ('3860', '1');
INSERT INTO `recod` VALUES ('3861', '1');
INSERT INTO `recod` VALUES ('3862', '1');
INSERT INTO `recod` VALUES ('3863', '1');
INSERT INTO `recod` VALUES ('3864', '1');
INSERT INTO `recod` VALUES ('3865', '1');
INSERT INTO `recod` VALUES ('3866', '1');
INSERT INTO `recod` VALUES ('3867', '1');
INSERT INTO `recod` VALUES ('3868', '1');
INSERT INTO `recod` VALUES ('3869', '1');
INSERT INTO `recod` VALUES ('3870', '1');
INSERT INTO `recod` VALUES ('3871', '1');
INSERT INTO `recod` VALUES ('3872', '1');
INSERT INTO `recod` VALUES ('3873', '1');
INSERT INTO `recod` VALUES ('3874', '1');
INSERT INTO `recod` VALUES ('3875', '1');
INSERT INTO `recod` VALUES ('3876', '1');
INSERT INTO `recod` VALUES ('3877', '1');
INSERT INTO `recod` VALUES ('3878', '1');
INSERT INTO `recod` VALUES ('3879', '1');
INSERT INTO `recod` VALUES ('3880', '1');
INSERT INTO `recod` VALUES ('3881', '1');
INSERT INTO `recod` VALUES ('3882', '1');
INSERT INTO `recod` VALUES ('3883', '1');
INSERT INTO `recod` VALUES ('3884', '1');
INSERT INTO `recod` VALUES ('3885', '1');
INSERT INTO `recod` VALUES ('3886', '1');
INSERT INTO `recod` VALUES ('3887', '1');
INSERT INTO `recod` VALUES ('3888', '1');
INSERT INTO `recod` VALUES ('3889', '1');
INSERT INTO `recod` VALUES ('3890', '1');
INSERT INTO `recod` VALUES ('3891', '1');
INSERT INTO `recod` VALUES ('3892', '1');
INSERT INTO `recod` VALUES ('3893', '1');
INSERT INTO `recod` VALUES ('3894', '1');
INSERT INTO `recod` VALUES ('3895', '1');
INSERT INTO `recod` VALUES ('3896', '1');
INSERT INTO `recod` VALUES ('3897', '1');
INSERT INTO `recod` VALUES ('3898', '1');
INSERT INTO `recod` VALUES ('3899', '1');
INSERT INTO `recod` VALUES ('3900', '1');
INSERT INTO `recod` VALUES ('3901', '1');
INSERT INTO `recod` VALUES ('3902', '1');
INSERT INTO `recod` VALUES ('3903', '1');
INSERT INTO `recod` VALUES ('3904', '1');
INSERT INTO `recod` VALUES ('3905', '1');
INSERT INTO `recod` VALUES ('3906', '1');
INSERT INTO `recod` VALUES ('3907', '1');
INSERT INTO `recod` VALUES ('3908', '1');
INSERT INTO `recod` VALUES ('3909', '1');
INSERT INTO `recod` VALUES ('3910', '1');
INSERT INTO `recod` VALUES ('3911', '1');
INSERT INTO `recod` VALUES ('3912', '1');
INSERT INTO `recod` VALUES ('3913', '1');
INSERT INTO `recod` VALUES ('3914', '1');
INSERT INTO `recod` VALUES ('3915', '1');
INSERT INTO `recod` VALUES ('3916', '1');
INSERT INTO `recod` VALUES ('3917', '1');
INSERT INTO `recod` VALUES ('3918', '1');
INSERT INTO `recod` VALUES ('3919', '1');
INSERT INTO `recod` VALUES ('3920', '1');
INSERT INTO `recod` VALUES ('3921', '1');
INSERT INTO `recod` VALUES ('3922', '1');
INSERT INTO `recod` VALUES ('3923', '1');
INSERT INTO `recod` VALUES ('3924', '1');
INSERT INTO `recod` VALUES ('3925', '1');
INSERT INTO `recod` VALUES ('3926', '1');
INSERT INTO `recod` VALUES ('3927', '1');
INSERT INTO `recod` VALUES ('3928', '1');
INSERT INTO `recod` VALUES ('3929', '1');
INSERT INTO `recod` VALUES ('3930', '1');
INSERT INTO `recod` VALUES ('3931', '1');
INSERT INTO `recod` VALUES ('3932', '1');
INSERT INTO `recod` VALUES ('3933', '1');
INSERT INTO `recod` VALUES ('3934', '1');
INSERT INTO `recod` VALUES ('3935', '1');
INSERT INTO `recod` VALUES ('3936', '1');
INSERT INTO `recod` VALUES ('3937', '1');
INSERT INTO `recod` VALUES ('3938', '1');
INSERT INTO `recod` VALUES ('3939', '1');
INSERT INTO `recod` VALUES ('3940', '1');
INSERT INTO `recod` VALUES ('3941', '1');
INSERT INTO `recod` VALUES ('3942', '1');
INSERT INTO `recod` VALUES ('3943', '1');
INSERT INTO `recod` VALUES ('3944', '1');
INSERT INTO `recod` VALUES ('3945', '1');
INSERT INTO `recod` VALUES ('3946', '1');
INSERT INTO `recod` VALUES ('3947', '1');
INSERT INTO `recod` VALUES ('3948', '1');
INSERT INTO `recod` VALUES ('3949', '1');
INSERT INTO `recod` VALUES ('3950', '1');
INSERT INTO `recod` VALUES ('3951', '1');
INSERT INTO `recod` VALUES ('3952', '1');
INSERT INTO `recod` VALUES ('3953', '1');
INSERT INTO `recod` VALUES ('3954', '1');
INSERT INTO `recod` VALUES ('3955', '1');
INSERT INTO `recod` VALUES ('3956', '1');
INSERT INTO `recod` VALUES ('3957', '1');
INSERT INTO `recod` VALUES ('3958', '1');
INSERT INTO `recod` VALUES ('3959', '1');
INSERT INTO `recod` VALUES ('3960', '1');
INSERT INTO `recod` VALUES ('3961', '1');
INSERT INTO `recod` VALUES ('3962', '1');
INSERT INTO `recod` VALUES ('3963', '1');
INSERT INTO `recod` VALUES ('3964', '1');
INSERT INTO `recod` VALUES ('3965', '1');
INSERT INTO `recod` VALUES ('3966', '1');
INSERT INTO `recod` VALUES ('3967', '1');
INSERT INTO `recod` VALUES ('3968', '1');
INSERT INTO `recod` VALUES ('3969', '1');
INSERT INTO `recod` VALUES ('3970', '1');
INSERT INTO `recod` VALUES ('3971', '1');
INSERT INTO `recod` VALUES ('3972', '1');
INSERT INTO `recod` VALUES ('3973', '1');
INSERT INTO `recod` VALUES ('3974', '1');
INSERT INTO `recod` VALUES ('3975', '1');
INSERT INTO `recod` VALUES ('3976', '1');
INSERT INTO `recod` VALUES ('3977', '1');
INSERT INTO `recod` VALUES ('3978', '1');
INSERT INTO `recod` VALUES ('3979', '1');
INSERT INTO `recod` VALUES ('3980', '1');
INSERT INTO `recod` VALUES ('3981', '1');
INSERT INTO `recod` VALUES ('3982', '1');
INSERT INTO `recod` VALUES ('3983', '1');
INSERT INTO `recod` VALUES ('3984', '1');
INSERT INTO `recod` VALUES ('3985', '1');
INSERT INTO `recod` VALUES ('3986', '1');
INSERT INTO `recod` VALUES ('3987', '1');
INSERT INTO `recod` VALUES ('3988', '1');
INSERT INTO `recod` VALUES ('3989', '1');
INSERT INTO `recod` VALUES ('3990', '1');
INSERT INTO `recod` VALUES ('3991', '1');
INSERT INTO `recod` VALUES ('3992', '1');
INSERT INTO `recod` VALUES ('3993', '1');
INSERT INTO `recod` VALUES ('3994', '1');
INSERT INTO `recod` VALUES ('3995', '1');
INSERT INTO `recod` VALUES ('3996', '1');
INSERT INTO `recod` VALUES ('3997', '1');
INSERT INTO `recod` VALUES ('3998', '1');
INSERT INTO `recod` VALUES ('3999', '1');
INSERT INTO `recod` VALUES ('4000', '1');
INSERT INTO `recod` VALUES ('4001', '1');
INSERT INTO `recod` VALUES ('4002', '1');
INSERT INTO `recod` VALUES ('4003', '1');
INSERT INTO `recod` VALUES ('4004', '1');
INSERT INTO `recod` VALUES ('4005', '1');
INSERT INTO `recod` VALUES ('4006', '1');
INSERT INTO `recod` VALUES ('4007', '1');
INSERT INTO `recod` VALUES ('4008', '1');
INSERT INTO `recod` VALUES ('4009', '1');
INSERT INTO `recod` VALUES ('4010', '1');
INSERT INTO `recod` VALUES ('4011', '1');
INSERT INTO `recod` VALUES ('4012', '1');
INSERT INTO `recod` VALUES ('4013', '1');
INSERT INTO `recod` VALUES ('4014', '1');
INSERT INTO `recod` VALUES ('4015', '1');
INSERT INTO `recod` VALUES ('4016', '1');
INSERT INTO `recod` VALUES ('4017', '1');
INSERT INTO `recod` VALUES ('4018', '1');
INSERT INTO `recod` VALUES ('4019', '1');
INSERT INTO `recod` VALUES ('4020', '1');
INSERT INTO `recod` VALUES ('4021', '1');
INSERT INTO `recod` VALUES ('4022', '1');
INSERT INTO `recod` VALUES ('4023', '1');
INSERT INTO `recod` VALUES ('4024', '1');
INSERT INTO `recod` VALUES ('4025', '1');
INSERT INTO `recod` VALUES ('4026', '1');
INSERT INTO `recod` VALUES ('4027', '1');
INSERT INTO `recod` VALUES ('4028', '1');
INSERT INTO `recod` VALUES ('4029', '1');
INSERT INTO `recod` VALUES ('4030', '1');
INSERT INTO `recod` VALUES ('4031', '1');
INSERT INTO `recod` VALUES ('4032', '1');
INSERT INTO `recod` VALUES ('4033', '1');
INSERT INTO `recod` VALUES ('4034', '1');
INSERT INTO `recod` VALUES ('4035', '1');
INSERT INTO `recod` VALUES ('4036', '1');
INSERT INTO `recod` VALUES ('4037', '1');
INSERT INTO `recod` VALUES ('4038', '1');
INSERT INTO `recod` VALUES ('4039', '1');
INSERT INTO `recod` VALUES ('4040', '1');
INSERT INTO `recod` VALUES ('4041', '1');
INSERT INTO `recod` VALUES ('4042', '1');
INSERT INTO `recod` VALUES ('4043', '1');
INSERT INTO `recod` VALUES ('4044', '1');
INSERT INTO `recod` VALUES ('4045', '1');
INSERT INTO `recod` VALUES ('4046', '1');
INSERT INTO `recod` VALUES ('4047', '1');
INSERT INTO `recod` VALUES ('4048', '1');
INSERT INTO `recod` VALUES ('4049', '1');
INSERT INTO `recod` VALUES ('4050', '1');
INSERT INTO `recod` VALUES ('4051', '1');
INSERT INTO `recod` VALUES ('4052', '1');
INSERT INTO `recod` VALUES ('4053', '1');
INSERT INTO `recod` VALUES ('4054', '1');
INSERT INTO `recod` VALUES ('4055', '1');
INSERT INTO `recod` VALUES ('4056', '1');
INSERT INTO `recod` VALUES ('4057', '1');
INSERT INTO `recod` VALUES ('4058', '1');
INSERT INTO `recod` VALUES ('4059', '1');
INSERT INTO `recod` VALUES ('4060', '1');
INSERT INTO `recod` VALUES ('4061', '1');
INSERT INTO `recod` VALUES ('4062', '1');
INSERT INTO `recod` VALUES ('4063', '1');
INSERT INTO `recod` VALUES ('4064', '1');
INSERT INTO `recod` VALUES ('4065', '1');
INSERT INTO `recod` VALUES ('4066', '1');
INSERT INTO `recod` VALUES ('4067', '1');
INSERT INTO `recod` VALUES ('4068', '1');
INSERT INTO `recod` VALUES ('4069', '1');
INSERT INTO `recod` VALUES ('4070', '1');
INSERT INTO `recod` VALUES ('4071', '1');
INSERT INTO `recod` VALUES ('4072', '1');
INSERT INTO `recod` VALUES ('4073', '1');
INSERT INTO `recod` VALUES ('4074', '1');
INSERT INTO `recod` VALUES ('4075', '1');
INSERT INTO `recod` VALUES ('4076', '1');
INSERT INTO `recod` VALUES ('4077', '1');
INSERT INTO `recod` VALUES ('4078', '1');
INSERT INTO `recod` VALUES ('4079', '1');
INSERT INTO `recod` VALUES ('4080', '1');
INSERT INTO `recod` VALUES ('4081', '1');
INSERT INTO `recod` VALUES ('4082', '1');
INSERT INTO `recod` VALUES ('4083', '1');
INSERT INTO `recod` VALUES ('4084', '1');
INSERT INTO `recod` VALUES ('4085', '1');
INSERT INTO `recod` VALUES ('4086', '1');
INSERT INTO `recod` VALUES ('4087', '1');
INSERT INTO `recod` VALUES ('4088', '1');
INSERT INTO `recod` VALUES ('4089', '1');
INSERT INTO `recod` VALUES ('4090', '1');
INSERT INTO `recod` VALUES ('4091', '1');
INSERT INTO `recod` VALUES ('4092', '1');
INSERT INTO `recod` VALUES ('4093', '1');
INSERT INTO `recod` VALUES ('4094', '1');
INSERT INTO `recod` VALUES ('4095', '1');
INSERT INTO `recod` VALUES ('4096', '1');
INSERT INTO `recod` VALUES ('4097', '1');
INSERT INTO `recod` VALUES ('4098', '1');
INSERT INTO `recod` VALUES ('4099', '1');
INSERT INTO `recod` VALUES ('4100', '1');
INSERT INTO `recod` VALUES ('4101', '1');
INSERT INTO `recod` VALUES ('4102', '1');
INSERT INTO `recod` VALUES ('4103', '1');
INSERT INTO `recod` VALUES ('4104', '1');
INSERT INTO `recod` VALUES ('4105', '1');
INSERT INTO `recod` VALUES ('4106', '1');
INSERT INTO `recod` VALUES ('4107', '1');
INSERT INTO `recod` VALUES ('4108', '1');
INSERT INTO `recod` VALUES ('4109', '1');
INSERT INTO `recod` VALUES ('4110', '1');
INSERT INTO `recod` VALUES ('4111', '1');
INSERT INTO `recod` VALUES ('4112', '1');
INSERT INTO `recod` VALUES ('4113', '1');
INSERT INTO `recod` VALUES ('4114', '1');
INSERT INTO `recod` VALUES ('4115', '1');
INSERT INTO `recod` VALUES ('4116', '1');
INSERT INTO `recod` VALUES ('4117', '1');
INSERT INTO `recod` VALUES ('4118', '1');
INSERT INTO `recod` VALUES ('4119', '1');
INSERT INTO `recod` VALUES ('4120', '1');
INSERT INTO `recod` VALUES ('4121', '1');
INSERT INTO `recod` VALUES ('4122', '1');
INSERT INTO `recod` VALUES ('4123', '1');
INSERT INTO `recod` VALUES ('4124', '1');
INSERT INTO `recod` VALUES ('4125', '1');
INSERT INTO `recod` VALUES ('4126', '1');
INSERT INTO `recod` VALUES ('4127', '1');
INSERT INTO `recod` VALUES ('4128', '1');
INSERT INTO `recod` VALUES ('4129', '1');
INSERT INTO `recod` VALUES ('4130', '1');
INSERT INTO `recod` VALUES ('4131', '1');
INSERT INTO `recod` VALUES ('4132', '1');
INSERT INTO `recod` VALUES ('4133', '1');
INSERT INTO `recod` VALUES ('4134', '1');
INSERT INTO `recod` VALUES ('4135', '1');
INSERT INTO `recod` VALUES ('4136', '1');
INSERT INTO `recod` VALUES ('4137', '1');
INSERT INTO `recod` VALUES ('4138', '1');
INSERT INTO `recod` VALUES ('4139', '1');
INSERT INTO `recod` VALUES ('4140', '1');
INSERT INTO `recod` VALUES ('4141', '1');
INSERT INTO `recod` VALUES ('4142', '1');
INSERT INTO `recod` VALUES ('4143', '1');
INSERT INTO `recod` VALUES ('4144', '1');
INSERT INTO `recod` VALUES ('4145', '1');
INSERT INTO `recod` VALUES ('4146', '1');
INSERT INTO `recod` VALUES ('4147', '1');
INSERT INTO `recod` VALUES ('4148', '1');
INSERT INTO `recod` VALUES ('4149', '1');
INSERT INTO `recod` VALUES ('4150', '1');
INSERT INTO `recod` VALUES ('4151', '1');
INSERT INTO `recod` VALUES ('4152', '1');
INSERT INTO `recod` VALUES ('4153', '1');
INSERT INTO `recod` VALUES ('4154', '1');
INSERT INTO `recod` VALUES ('4155', '1');
INSERT INTO `recod` VALUES ('4156', '1');
INSERT INTO `recod` VALUES ('4157', '1');
INSERT INTO `recod` VALUES ('4158', '1');
INSERT INTO `recod` VALUES ('4159', '1');
INSERT INTO `recod` VALUES ('4160', '1');
INSERT INTO `recod` VALUES ('4161', '1');
INSERT INTO `recod` VALUES ('4162', '1');
INSERT INTO `recod` VALUES ('4163', '1');
INSERT INTO `recod` VALUES ('4164', '1');
INSERT INTO `recod` VALUES ('4165', '1');
INSERT INTO `recod` VALUES ('4166', '1');
INSERT INTO `recod` VALUES ('4167', '1');
INSERT INTO `recod` VALUES ('4168', '1');
INSERT INTO `recod` VALUES ('4169', '1');
INSERT INTO `recod` VALUES ('4170', '1');
INSERT INTO `recod` VALUES ('4171', '1');
INSERT INTO `recod` VALUES ('4172', '1');
INSERT INTO `recod` VALUES ('4173', '1');
INSERT INTO `recod` VALUES ('4174', '1');
INSERT INTO `recod` VALUES ('4175', '1');
INSERT INTO `recod` VALUES ('4176', '1');
INSERT INTO `recod` VALUES ('4177', '1');
INSERT INTO `recod` VALUES ('4178', '1');
INSERT INTO `recod` VALUES ('4179', '1');
INSERT INTO `recod` VALUES ('4180', '1');
INSERT INTO `recod` VALUES ('4181', '1');
INSERT INTO `recod` VALUES ('4182', '1');
INSERT INTO `recod` VALUES ('4183', '1');
INSERT INTO `recod` VALUES ('4184', '1');
INSERT INTO `recod` VALUES ('4185', '1');
INSERT INTO `recod` VALUES ('4186', '1');
INSERT INTO `recod` VALUES ('4187', '1');
INSERT INTO `recod` VALUES ('4188', '1');
INSERT INTO `recod` VALUES ('4189', '1');
INSERT INTO `recod` VALUES ('4190', '1');
INSERT INTO `recod` VALUES ('4191', '1');
INSERT INTO `recod` VALUES ('4192', '1');
INSERT INTO `recod` VALUES ('4193', '1');
INSERT INTO `recod` VALUES ('4194', '1');
INSERT INTO `recod` VALUES ('4195', '1');
INSERT INTO `recod` VALUES ('4196', '1');
INSERT INTO `recod` VALUES ('4197', '1');
INSERT INTO `recod` VALUES ('4198', '1');
INSERT INTO `recod` VALUES ('4199', '1');
INSERT INTO `recod` VALUES ('4200', '1');
INSERT INTO `recod` VALUES ('4201', '1');
INSERT INTO `recod` VALUES ('4202', '1');
INSERT INTO `recod` VALUES ('4203', '1');
INSERT INTO `recod` VALUES ('4204', '1');
INSERT INTO `recod` VALUES ('4205', '1');
INSERT INTO `recod` VALUES ('4206', '1');
INSERT INTO `recod` VALUES ('4207', '1');
INSERT INTO `recod` VALUES ('4208', '1');
INSERT INTO `recod` VALUES ('4209', '1');
INSERT INTO `recod` VALUES ('4210', '1');
INSERT INTO `recod` VALUES ('4211', '1');
INSERT INTO `recod` VALUES ('4212', '1');
INSERT INTO `recod` VALUES ('4213', '1');
INSERT INTO `recod` VALUES ('4214', '1');
INSERT INTO `recod` VALUES ('4215', '1');
INSERT INTO `recod` VALUES ('4216', '1');
INSERT INTO `recod` VALUES ('4217', '1');
INSERT INTO `recod` VALUES ('4218', '1');
INSERT INTO `recod` VALUES ('4219', '1');
INSERT INTO `recod` VALUES ('4220', '1');
INSERT INTO `recod` VALUES ('4221', '1');
INSERT INTO `recod` VALUES ('4222', '1');
INSERT INTO `recod` VALUES ('4223', '1');
INSERT INTO `recod` VALUES ('4224', '1');
INSERT INTO `recod` VALUES ('4225', '1');
INSERT INTO `recod` VALUES ('4226', '1');
INSERT INTO `recod` VALUES ('4227', '1');
INSERT INTO `recod` VALUES ('4228', '1');
INSERT INTO `recod` VALUES ('4229', '1');
INSERT INTO `recod` VALUES ('4230', '1');
INSERT INTO `recod` VALUES ('4231', '1');
INSERT INTO `recod` VALUES ('4232', '1');
INSERT INTO `recod` VALUES ('4233', '1');
INSERT INTO `recod` VALUES ('4234', '1');
INSERT INTO `recod` VALUES ('4235', '1');
INSERT INTO `recod` VALUES ('4236', '1');
INSERT INTO `recod` VALUES ('4237', '1');
INSERT INTO `recod` VALUES ('4238', '1');
INSERT INTO `recod` VALUES ('4239', '1');
INSERT INTO `recod` VALUES ('4240', '1');
INSERT INTO `recod` VALUES ('4241', '1');
INSERT INTO `recod` VALUES ('4242', '1');
INSERT INTO `recod` VALUES ('4243', '1');
INSERT INTO `recod` VALUES ('4244', '1');
INSERT INTO `recod` VALUES ('4245', '1');
INSERT INTO `recod` VALUES ('4246', '1');
INSERT INTO `recod` VALUES ('4247', '1');
INSERT INTO `recod` VALUES ('4248', '1');
INSERT INTO `recod` VALUES ('4249', '1');
INSERT INTO `recod` VALUES ('4250', '1');
INSERT INTO `recod` VALUES ('4251', '1');
INSERT INTO `recod` VALUES ('4252', '1');
INSERT INTO `recod` VALUES ('4253', '1');
INSERT INTO `recod` VALUES ('4254', '1');
INSERT INTO `recod` VALUES ('4255', '1');
INSERT INTO `recod` VALUES ('4256', '1');
INSERT INTO `recod` VALUES ('4257', '1');
INSERT INTO `recod` VALUES ('4258', '1');
INSERT INTO `recod` VALUES ('4259', '1');
INSERT INTO `recod` VALUES ('4260', '1');
INSERT INTO `recod` VALUES ('4261', '1');
INSERT INTO `recod` VALUES ('4262', '1');
INSERT INTO `recod` VALUES ('4263', '1');
INSERT INTO `recod` VALUES ('4264', '1');
INSERT INTO `recod` VALUES ('4265', '1');
INSERT INTO `recod` VALUES ('4266', '1');
INSERT INTO `recod` VALUES ('4267', '1');
INSERT INTO `recod` VALUES ('4268', '1');
INSERT INTO `recod` VALUES ('4269', '1');
INSERT INTO `recod` VALUES ('4270', '1');
INSERT INTO `recod` VALUES ('4271', '1');
INSERT INTO `recod` VALUES ('4272', '1');
INSERT INTO `recod` VALUES ('4273', '1');
INSERT INTO `recod` VALUES ('4274', '1');
INSERT INTO `recod` VALUES ('4275', '1');
INSERT INTO `recod` VALUES ('4276', '1');
INSERT INTO `recod` VALUES ('4277', '1');
INSERT INTO `recod` VALUES ('4278', '1');
INSERT INTO `recod` VALUES ('4279', '1');
INSERT INTO `recod` VALUES ('4280', '1');
INSERT INTO `recod` VALUES ('4281', '1');
INSERT INTO `recod` VALUES ('4282', '1');
INSERT INTO `recod` VALUES ('4283', '1');
INSERT INTO `recod` VALUES ('4284', '1');
INSERT INTO `recod` VALUES ('4285', '1');
INSERT INTO `recod` VALUES ('4286', '1');
INSERT INTO `recod` VALUES ('4287', '1');
INSERT INTO `recod` VALUES ('4288', '1');
INSERT INTO `recod` VALUES ('4289', '1');
INSERT INTO `recod` VALUES ('4290', '1');
INSERT INTO `recod` VALUES ('4291', '1');
INSERT INTO `recod` VALUES ('4292', '1');
INSERT INTO `recod` VALUES ('4293', '1');
INSERT INTO `recod` VALUES ('4294', '1');
INSERT INTO `recod` VALUES ('4295', '1');
INSERT INTO `recod` VALUES ('4296', '1');
INSERT INTO `recod` VALUES ('4297', '1');
INSERT INTO `recod` VALUES ('4298', '1');
INSERT INTO `recod` VALUES ('4299', '1');
INSERT INTO `recod` VALUES ('4300', '1');
INSERT INTO `recod` VALUES ('4301', '1');
INSERT INTO `recod` VALUES ('4302', '1');
INSERT INTO `recod` VALUES ('4303', '1');
INSERT INTO `recod` VALUES ('4304', '1');
INSERT INTO `recod` VALUES ('4305', '1');
INSERT INTO `recod` VALUES ('4306', '1');
INSERT INTO `recod` VALUES ('4307', '1');
INSERT INTO `recod` VALUES ('4308', '1');
INSERT INTO `recod` VALUES ('4309', '1');
INSERT INTO `recod` VALUES ('4310', '1');
INSERT INTO `recod` VALUES ('4311', '1');
INSERT INTO `recod` VALUES ('4312', '2');
INSERT INTO `recod` VALUES ('4313', '1');
INSERT INTO `recod` VALUES ('4314', '1');
INSERT INTO `recod` VALUES ('4315', '1');
INSERT INTO `recod` VALUES ('4316', '1');
INSERT INTO `recod` VALUES ('4317', '1');
INSERT INTO `recod` VALUES ('4318', '1');
INSERT INTO `recod` VALUES ('4319', '1');
INSERT INTO `recod` VALUES ('4320', '1');
INSERT INTO `recod` VALUES ('4321', '1');
INSERT INTO `recod` VALUES ('4322', '1');
INSERT INTO `recod` VALUES ('4323', '1');
INSERT INTO `recod` VALUES ('4324', '1');
INSERT INTO `recod` VALUES ('4325', '1');
INSERT INTO `recod` VALUES ('4326', '1');
INSERT INTO `recod` VALUES ('4327', '1');
INSERT INTO `recod` VALUES ('4328', '1');
INSERT INTO `recod` VALUES ('4329', '1');
INSERT INTO `recod` VALUES ('4330', '1');
INSERT INTO `recod` VALUES ('4331', '1');
INSERT INTO `recod` VALUES ('4332', '1');
INSERT INTO `recod` VALUES ('4333', '1');
INSERT INTO `recod` VALUES ('4334', '1');
INSERT INTO `recod` VALUES ('4335', '1');
INSERT INTO `recod` VALUES ('4336', '1');
INSERT INTO `recod` VALUES ('4337', '1');
INSERT INTO `recod` VALUES ('4338', '1');
INSERT INTO `recod` VALUES ('4339', '1');
INSERT INTO `recod` VALUES ('4340', '1');
INSERT INTO `recod` VALUES ('4341', '1');
INSERT INTO `recod` VALUES ('4342', '1');
INSERT INTO `recod` VALUES ('4343', '1');
INSERT INTO `recod` VALUES ('4344', '1');
INSERT INTO `recod` VALUES ('4345', '1');
INSERT INTO `recod` VALUES ('4346', '1');
INSERT INTO `recod` VALUES ('4347', '1');
INSERT INTO `recod` VALUES ('4348', '1');
INSERT INTO `recod` VALUES ('4349', '1');
INSERT INTO `recod` VALUES ('4350', '1');
INSERT INTO `recod` VALUES ('4351', '1');
INSERT INTO `recod` VALUES ('4352', '1');
INSERT INTO `recod` VALUES ('4353', '1');
INSERT INTO `recod` VALUES ('4354', '1');
INSERT INTO `recod` VALUES ('4355', '1');
INSERT INTO `recod` VALUES ('4356', '1');
INSERT INTO `recod` VALUES ('4357', '1');
INSERT INTO `recod` VALUES ('4358', '1');
INSERT INTO `recod` VALUES ('4359', '1');
INSERT INTO `recod` VALUES ('4360', '1');
INSERT INTO `recod` VALUES ('4361', '1');
INSERT INTO `recod` VALUES ('4362', '1');
INSERT INTO `recod` VALUES ('4363', '1');
INSERT INTO `recod` VALUES ('4364', '1');
INSERT INTO `recod` VALUES ('4365', '1');
INSERT INTO `recod` VALUES ('4366', '1');
INSERT INTO `recod` VALUES ('4367', '1');
INSERT INTO `recod` VALUES ('4368', '1');
INSERT INTO `recod` VALUES ('4369', '1');
INSERT INTO `recod` VALUES ('4370', '1');
INSERT INTO `recod` VALUES ('4371', '1');
INSERT INTO `recod` VALUES ('4372', '1');
INSERT INTO `recod` VALUES ('4373', '1');
INSERT INTO `recod` VALUES ('4374', '1');
INSERT INTO `recod` VALUES ('4375', '1');
INSERT INTO `recod` VALUES ('4376', '1');
INSERT INTO `recod` VALUES ('4377', '1');
INSERT INTO `recod` VALUES ('4378', '1');
INSERT INTO `recod` VALUES ('4379', '1');
INSERT INTO `recod` VALUES ('4380', '1');
INSERT INTO `recod` VALUES ('4381', '1');
INSERT INTO `recod` VALUES ('4382', '1');
INSERT INTO `recod` VALUES ('4383', '1');
INSERT INTO `recod` VALUES ('4384', '1');
INSERT INTO `recod` VALUES ('4385', '1');
INSERT INTO `recod` VALUES ('4386', '1');
INSERT INTO `recod` VALUES ('4387', '1');
INSERT INTO `recod` VALUES ('4388', '1');
INSERT INTO `recod` VALUES ('4389', '1');
INSERT INTO `recod` VALUES ('4390', '1');
INSERT INTO `recod` VALUES ('4391', '1');
INSERT INTO `recod` VALUES ('4392', '1');
INSERT INTO `recod` VALUES ('4393', '1');
INSERT INTO `recod` VALUES ('4394', '1');
INSERT INTO `recod` VALUES ('4395', '1');
INSERT INTO `recod` VALUES ('4396', '1');
INSERT INTO `recod` VALUES ('4397', '1');
INSERT INTO `recod` VALUES ('4398', '1');
INSERT INTO `recod` VALUES ('4399', '1');
INSERT INTO `recod` VALUES ('4400', '1');
INSERT INTO `recod` VALUES ('4401', '1');
INSERT INTO `recod` VALUES ('4402', '1');
INSERT INTO `recod` VALUES ('4403', '1');
INSERT INTO `recod` VALUES ('4404', '1');
INSERT INTO `recod` VALUES ('4405', '1');
INSERT INTO `recod` VALUES ('4406', '1');
INSERT INTO `recod` VALUES ('4407', '1');
INSERT INTO `recod` VALUES ('4408', '1');
INSERT INTO `recod` VALUES ('4409', '1');
INSERT INTO `recod` VALUES ('4410', '1');
INSERT INTO `recod` VALUES ('4411', '1');
INSERT INTO `recod` VALUES ('4412', '1');
INSERT INTO `recod` VALUES ('4413', '1');
INSERT INTO `recod` VALUES ('4414', '1');
INSERT INTO `recod` VALUES ('4415', '1');
INSERT INTO `recod` VALUES ('4416', '1');
INSERT INTO `recod` VALUES ('4417', '1');
INSERT INTO `recod` VALUES ('4418', '1');
INSERT INTO `recod` VALUES ('4419', '1');
INSERT INTO `recod` VALUES ('4420', '1');
INSERT INTO `recod` VALUES ('4421', '1');
INSERT INTO `recod` VALUES ('4422', '1');

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
