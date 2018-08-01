/*
Navicat MySQL Data Transfer

Source Server         : hdy
Source Server Version : 50640
Source Host           : 127.0.0.1:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-07-13 17:35:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` char(32) NOT NULL COMMENT 'UUID',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `parent_id` char(32) DEFAULT NULL COMMENT '父级部门ID',
  `majordomo` char(6) DEFAULT NULL COMMENT '部门总监工号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('5def8e00106e4725a8ee7f35e59d5ee5', '2018-06-15 11:38:13', '2018-06-15 11:38:13', '市场部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('5fca0097c5c14afcbadbd62ab56b83b4', '2018-06-15 11:38:58', '2018-06-15 11:38:58', '人力资源部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('7191770c19274deaa01861f843e93da3', '2018-06-15 11:38:37', '2018-06-15 11:38:37', '行政部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('7c175767f23c4e76b8122399ef1e4907', '2018-06-15 11:38:00', '2018-06-15 11:38:00', '仓储物流部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('8ba72e1eda1546dcb965fd64a6ba2369', '2018-06-15 11:38:26', '2018-07-12 17:59:48', '研发部', 'c44f07b86dd74a429e7b4f5f6f7120a1', '0002');
INSERT INTO `department` VALUES ('96c6496123e94d49aa795adc8c478297', '2018-06-15 11:38:47', '2018-06-15 11:38:47', '财务部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('96e373c33ced49e2a2c57087a5422cc9', '2018-06-15 11:37:31', '2018-06-15 11:37:31', '销售部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('c44f07b86dd74a429e7b4f5f6f7120a1', '2018-06-15 11:39:13', '2018-07-12 15:00:48', '总裁办', null, null);
INSERT INTO `department` VALUES ('d7247211ef7945d1b21bfdecaa0c35af', '2018-07-09 13:59:19', '2018-07-09 13:59:19', 'Java', '8ba72e1eda1546dcb965fd64a6ba2369', null);
INSERT INTO `department` VALUES ('f44430159804430a8f189476c5ab603c', '2018-06-15 11:37:45', '2018-06-15 11:37:45', '金融部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);
INSERT INTO `department` VALUES ('faafae84c1f147a7bfcfedcda80445fb', '2018-06-15 11:33:42', '2018-06-15 11:33:42', '运营管理部', 'c44f07b86dd74a429e7b4f5f6f7120a1', null);

-- ----------------------------
-- Table structure for dimensionality
-- ----------------------------
DROP TABLE IF EXISTS `dimensionality`;
CREATE TABLE `dimensionality` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(20) NOT NULL COMMENT '考核维度名',
  `level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考核维度表';

-- ----------------------------
-- Records of dimensionality
-- ----------------------------
INSERT INTO `dimensionality` VALUES ('145dfebb03fb46829e90384df099734d', '2018-06-21 14:43:11', '2018-06-21 14:43:11', '工作结果指标', '10');
INSERT INTO `dimensionality` VALUES ('4036c7d9224144bd8371f635db81d726', '2018-06-21 14:44:28', '2018-06-21 14:44:28', '工作表现（统一）', '0');
INSERT INTO `dimensionality` VALUES ('a9a5f422b2f64fcf9a0ac792ebea0612', '2018-06-21 14:43:59', '2018-06-21 14:43:59', '关键行为指标', '5');

-- ----------------------------
-- Table structure for evaluation_item
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_item`;
CREATE TABLE `evaluation_item` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(20) NOT NULL COMMENT '考核项目名',
  `weight` tinyint(3) unsigned NOT NULL COMMENT '权重（25%取25）',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '定义及评分标准',
  `dimensionality_id` char(32) NOT NULL COMMENT '所属考核维度ID',
  PRIMARY KEY (`id`),
  KEY `idx_dimensionalityId` (`dimensionality_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考核项目表（对应模板表）';

-- ----------------------------
-- Records of evaluation_item
-- ----------------------------
INSERT INTO `evaluation_item` VALUES ('23b7f038abc141aaa58c9794d5932c7b', '2018-06-26 11:19:39', '2018-06-26 11:19:39', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', 'a9a5f422b2f64fcf9a0ac792ebea0612');
INSERT INTO `evaluation_item` VALUES ('3f458a5182f24037801ec874e6535c3c', '2018-06-21 14:51:05', '2018-06-21 22:54:13', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', 'a9a5f422b2f64fcf9a0ac792ebea0612');
INSERT INTO `evaluation_item` VALUES ('4b981694b406452184acdbd9fb345712', '2018-06-21 14:51:40', '2018-06-22 22:34:51', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '4036c7d9224144bd8371f635db81d726');
INSERT INTO `evaluation_item` VALUES ('57850d2f0d8c47c2bc1e4f7b591d7267', '2018-06-21 09:50:46', '2018-06-22 09:35:16', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '145dfebb03fb46829e90384df099734d');
INSERT INTO `evaluation_item` VALUES ('7e5c8d7b4b0a4b4888c34bf2a41224fe', '2018-06-21 09:46:45', '2018-06-21 09:46:45', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '145dfebb03fb46829e90384df099734d');
INSERT INTO `evaluation_item` VALUES ('9110d05982a64862938240a9a98a4ebe', '2018-06-21 14:50:16', '2018-06-21 14:50:16', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', 'a9a5f422b2f64fcf9a0ac792ebea0612');
INSERT INTO `evaluation_item` VALUES ('e56b9b7156a94a8da449e2eba3b12d85', '2018-06-21 09:46:33', '2018-06-21 09:46:33', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '145dfebb03fb46829e90384df099734d');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` char(32) NOT NULL COMMENT 'UUID',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(20) NOT NULL COMMENT '岗位名称',
  `salary_type` varchar(10) NOT NULL COMMENT '薪资类别',
  `department_id` char(32) NOT NULL COMMENT '所属部门ID',
  `department_name` varchar(10) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_departmentName` (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('2f6e7ccae2e441ffbb5a5428c98c870c', '2018-06-15 11:45:12', '2018-06-15 11:45:12', 'Java开发工程师', '非销售类', '8ba72e1eda1546dcb965fd64a6ba2369', '研发部');
INSERT INTO `job` VALUES ('58557830ba22453fa245cdead8a5a888', '2018-06-15 11:45:54', '2018-06-15 11:45:54', '运维工程师', '非销售类', '8ba72e1eda1546dcb965fd64a6ba2369', '研发部');
INSERT INTO `job` VALUES ('be6354dee52041f49cfa733c10dade84', '2018-06-20 11:40:30', '2018-06-20 11:40:30', '人事专员', '非销售类', '5fca0097c5c14afcbadbd62ab56b83b4', '人力资源部');
INSERT INTO `job` VALUES ('cf09041ce2de4c44985639018ccf8043', '2018-07-13 09:05:14', '2018-07-13 09:05:14', '（测试）系统超管', '无', 'c44f07b86dd74a429e7b4f5f6f7120a1', '总裁办');
INSERT INTO `job` VALUES ('d9a58e533254447e8faf942da66d73ce', '2018-06-15 11:45:44', '2018-06-15 11:45:44', '项目经理', '非销售类', '8ba72e1eda1546dcb965fd64a6ba2369', '研发部');
INSERT INTO `job` VALUES ('e083634d1cc0437ca019aa0404c82999', '2018-06-15 11:45:30', '2018-06-15 11:45:30', '网络管理员', '非销售类', '8ba72e1eda1546dcb965fd64a6ba2369', '研发部');

-- ----------------------------
-- Table structure for meeting_room
-- ----------------------------
DROP TABLE IF EXISTS `meeting_room`;
CREATE TABLE `meeting_room` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(10) NOT NULL COMMENT '会议室名',
  `size` smallint(5) unsigned NOT NULL COMMENT '可容纳人数',
  `path` varchar(255) DEFAULT NULL COMMENT '会议室图片路径',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '会议室描述（地点，设备等）',
  `status` enum('NORMAL','DISABLED') NOT NULL DEFAULT 'NORMAL' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `idx_size` (`size`),
  KEY `idx_remark` (`remark`),
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meeting_room
-- ----------------------------
INSERT INTO `meeting_room` VALUES ('054509132fef48c6a4759aefffb870b0', '2018-07-10 14:53:43', '2018-07-13 09:15:12', '洽谈室4', '20', '054509132fef48c6a4759aefffb870b0.jpg', '暂无', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('3e68053092354a8fa2a5f280f4e308df', '2018-06-30 20:28:11', '2018-06-30 20:28:11', '测试-大型会议室', '100', 'meetingroom-default2.jpg', '300平方米，位于公司3楼，100张椅子，可容纳最大200人，有音响，投影仪，WIFI，大屏幕，小舞台', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('781c3953ad704d1eb1d1a341d6f7bcbc', '2018-07-13 14:39:54', '2018-07-13 14:39:54', 'test', '3', '781c3953ad704d1eb1d1a341d6f7bcbc.jpg', 'test', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('8421966ba283418abd1f105ff6acb2b3', '2018-07-11 11:28:04', '2018-07-11 11:28:04', '会议室2', '20', 'meetingroom-default.jpg', '暂无', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('9d4f742348b24c1693f53f91d4c4061f', '2018-07-10 14:43:13', '2018-07-13 09:15:03', '洽谈室1', '10', '9d4f742348b24c1693f53f91d4c4061f.jpg', '实木座位', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('a6281ceb4c2c48a194063bafbd249127', '2018-07-10 14:52:59', '2018-07-13 09:15:09', '洽谈室3', '5', 'a6281ceb4c2c48a194063bafbd249127.jpg', '暂无', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('e4caacd20dcd4dea87db8230b3caa037', '2018-07-10 14:54:36', '2018-07-13 14:39:25', '会议厅new', '500', 'e4caacd20dcd4dea87db8230b3caa037.jpg', '公司3楼会议厅testtest', 'NORMAL');
INSERT INTO `meeting_room` VALUES ('e79e9db4e0654be1b0cbc4c32667e04c', '2018-07-10 14:54:03', '2018-07-13 09:15:13', '洽谈室5', '25', 'e79e9db4e0654be1b0cbc4c32667e04c.jpg', '暂无', 'NORMAL');

-- ----------------------------
-- Table structure for meeting_room_order
-- ----------------------------
DROP TABLE IF EXISTS `meeting_room_order`;
CREATE TABLE `meeting_room_order` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `starttime` datetime NOT NULL COMMENT '会议起始时间',
  `endtime` datetime NOT NULL COMMENT '会议结束时间',
  `order_user_no` char(6) NOT NULL COMMENT '预约人工号',
  `join_users_no` text COMMENT '参会人员工号集合（用英文逗号隔开）',
  `room_id` char(32) NOT NULL COMMENT '会议室ID',
  `status` enum('NORMAL','OVERDUE','CANCEL') NOT NULL DEFAULT 'NORMAL' COMMENT '状态（含义详情看数据库设计文档）',
  PRIMARY KEY (`id`),
  KEY `idx_starttime` (`starttime`),
  KEY `idx_endtime` (`endtime`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_join_users_no` (`join_users_no`(255)) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meeting_room_order
-- ----------------------------
INSERT INTO `meeting_room_order` VALUES ('2afbcf639246406aa9adf921a49a74a2', '2018-07-13 13:27:31', '2018-07-13 13:27:31', '2018-07-14 14:00:00', '2018-07-14 15:00:00', '000000', '1327', 'e4caacd20dcd4dea87db8230b3caa037', 'NORMAL');
INSERT INTO `meeting_room_order` VALUES ('36c2b4ed3de3427e9941b5649b9d5bfe', '2018-07-12 20:08:03', '2018-07-12 20:08:03', '2018-07-12 21:15:00', '2018-07-12 22:15:00', '1327', '0001', 'e4caacd20dcd4dea87db8230b3caa037', 'CANCEL');
INSERT INTO `meeting_room_order` VALUES ('847c53a79dd34a06b18cbbaf7a6475fd', '2018-07-12 20:06:59', '2018-07-12 20:06:59', '2018-07-12 20:15:00', '2018-07-12 20:30:00', '0001', '1327', 'e4caacd20dcd4dea87db8230b3caa037', 'CANCEL');
INSERT INTO `meeting_room_order` VALUES ('ac73becc510b4c1a9f074ff459c95fd1', '2018-07-13 14:36:54', '2018-07-13 14:36:54', '2018-07-14 15:00:00', '2018-07-14 15:45:00', '000000', '1327', 'e4caacd20dcd4dea87db8230b3caa037', 'CANCEL');
INSERT INTO `meeting_room_order` VALUES ('b8686b002a8548c09fda47562a541574', '2018-07-13 10:18:51', '2018-07-13 10:18:51', '2018-07-13 10:30:00', '2018-07-13 11:30:00', '000000', '1327', 'e4caacd20dcd4dea87db8230b3caa037', 'CANCEL');

-- ----------------------------
-- Table structure for official_evaluation_item
-- ----------------------------
DROP TABLE IF EXISTS `official_evaluation_item`;
CREATE TABLE `official_evaluation_item` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `official_id` char(32) NOT NULL COMMENT '绩效考核正表id',
  `dimensionality_name` varchar(20) NOT NULL COMMENT '考核维度名',
  `dimensionality_level` tinyint(3) unsigned NOT NULL COMMENT '考核维度优先级',
  `name` varchar(20) NOT NULL COMMENT '考核项目名',
  `weight` tinyint(3) unsigned NOT NULL COMMENT '权重（25%取25）',
  `content` varchar(255) NOT NULL COMMENT '定义及评分标准',
  `self_content` varchar(255) NOT NULL DEFAULT '' COMMENT '情况列举（自评）',
  `self_grade` tinyint(3) unsigned DEFAULT NULL COMMENT '自评分',
  `boss_grade` tinyint(3) unsigned DEFAULT NULL COMMENT '直接上级评分',
  PRIMARY KEY (`id`),
  KEY `idx_official_id` (`official_id`),
  KEY `idx_dimensionality` (`dimensionality_level`,`dimensionality_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='正表考核项目（用于正表）';

-- ----------------------------
-- Records of official_evaluation_item
-- ----------------------------
INSERT INTO `official_evaluation_item` VALUES ('033117c696dc432a84010d8b92738349', '2018-07-12 18:06:50', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '', '14', '14');
INSERT INTO `official_evaluation_item` VALUES ('048ca854caa54560846b46bd0d8f1a1d', '2018-07-12 18:06:49', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '', '23', '23');
INSERT INTO `official_evaluation_item` VALUES ('069d8d15217548b2b625ed837e244e5f', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '0', '19');
INSERT INTO `official_evaluation_item` VALUES ('088545d9f7af4ce8941226f58a13c7a7', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '0', '0');
INSERT INTO `official_evaluation_item` VALUES ('09fb9eb128844407b0b40481632ec2c7', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '0', '7');
INSERT INTO `official_evaluation_item` VALUES ('0d78274f2aae4d49a8275dbfef3191f2', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '7', '8');
INSERT INTO `official_evaluation_item` VALUES ('0e04796d89e24b19a6e513c8d6238e47', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '0', '10');
INSERT INTO `official_evaluation_item` VALUES ('0eab1d24ab6745bd8428e3bfb2b7b323', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '', '24', null);
INSERT INTO `official_evaluation_item` VALUES ('25e3f0d2dea04a4a860e1a22f5a13ee3', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '', '0', '13');
INSERT INTO `official_evaluation_item` VALUES ('28c9f42ae5f24fd8ac0773859786aa20', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '1.首先最主要的优化是在代码的编写上，结合本人编码思想和算法思想，在代码上优化了性能。2.其次是SQL的编写上，以及SQL建表的设计。', '5', '5');
INSERT INTO `official_evaluation_item` VALUES ('28fc5f8c732c474381a2ce0c79602002', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '严格按照MVC思想以及面向接口的变成方法，减少代码重用。为重要服务写了单元测试', '10', '0');
INSERT INTO `official_evaluation_item` VALUES ('2b35c5974023415c8be5cb1a33a70810', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '0', '8');
INSERT INTO `official_evaluation_item` VALUES ('2c4e4abf762a4028bdb4605635eaa664', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', 'a', '25', '23');
INSERT INTO `official_evaluation_item` VALUES ('2c921037d4ea466182a7c66678333c55', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '', '13', '22');
INSERT INTO `official_evaluation_item` VALUES ('2dd3bc0b41764dfc991985fc44f34e45', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', 'B', '10', '0');
INSERT INTO `official_evaluation_item` VALUES ('3b1e20ebfca64a23832e1a2a5d59c75a', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '5', '5');
INSERT INTO `official_evaluation_item` VALUES ('3ba7a57c33754832a090dd44a841c283', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '10', '0');
INSERT INTO `official_evaluation_item` VALUES ('41787adaf4e94d7da526c59586eabb9a', '2018-07-12 18:06:49', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '19', '19');
INSERT INTO `official_evaluation_item` VALUES ('46c53241ce2c4bf39dc6bdee4bd0186f', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '10', '10');
INSERT INTO `official_evaluation_item` VALUES ('4c51eb66d7d24e2399d87c82e21df174', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '0', '2');
INSERT INTO `official_evaluation_item` VALUES ('5512e56657c44dc19a8a76cd77396558', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '5', null);
INSERT INTO `official_evaluation_item` VALUES ('6ae8b804ca4f496d846c0c8cbef98569', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '（1）前端方面：在不断使用jquery插件的过程中，深入研究了JQuery底层代码，对于JQuery的理解以及实战运用能力得到很大的提升。（2）后端方面：结合先前学习的《MySQL性能优化》的知识，在本次系统中得以运用，但仍有很多明显的不足，后期会一一分析，并总结。', '3', '3');
INSERT INTO `official_evaluation_item` VALUES ('6cd4dc01ebfc4d68aadcfcbef68ae79f', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '', '14', '13');
INSERT INTO `official_evaluation_item` VALUES ('6f6a4104dd2940d386fea6c254f3892a', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '3', '4');
INSERT INTO `official_evaluation_item` VALUES ('720fb7108c304507a6386419b142868a', '2018-07-12 18:06:49', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '1', '9', '9');
INSERT INTO `official_evaluation_item` VALUES ('80ee1a86bb3548458144fb0310bc0b06', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '10', null);
INSERT INTO `official_evaluation_item` VALUES ('84209f0f3e7d405d8378b34376fa5a37', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '15', '13');
INSERT INTO `official_evaluation_item` VALUES ('8480a853c07e464c890159fc26029fb2', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊安安啊', '11', '11');
INSERT INTO `official_evaluation_item` VALUES ('86471b20089b4fe698a58078ce787d81', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', 'a', '0', '9');
INSERT INTO `official_evaluation_item` VALUES ('8774a57a9a40470a84083df3801396d3', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '10', '11');
INSERT INTO `official_evaluation_item` VALUES ('8d63180f8699456e9fee9f57f5a4f925', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '20', null);
INSERT INTO `official_evaluation_item` VALUES ('8d6ca8c4942547ccbebd88dad2216a28', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', 'S.提前一周完成项目', '25', '25');
INSERT INTO `official_evaluation_item` VALUES ('90692f7cee87410db4ced102bf2e667f', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '及时与项目负责人沟通，保证项目开发质量', '20', '0');
INSERT INTO `official_evaluation_item` VALUES ('90fcea438b9e4bef8477124aa6a255d7', '2018-07-12 18:06:50', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '9', '8');
INSERT INTO `official_evaluation_item` VALUES ('97277c14313b4b81a5906663233ef47f', '2018-07-12 18:06:50', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '5', '5');
INSERT INTO `official_evaluation_item` VALUES ('9f278b83a79a4be5978dbfcf74802566', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '0', '3');
INSERT INTO `official_evaluation_item` VALUES ('a502db6db8df4244a0dacb84ed46ce7e', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '认同公司鸟人精神', '15', '0');
INSERT INTO `official_evaluation_item` VALUES ('a87da9bc98c445ad87a3f314a5c7c07f', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '需求到达，马上解决，刻不容缓', '20', '20');
INSERT INTO `official_evaluation_item` VALUES ('a93a9982955c4ed699ee2b43c6052ef7', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '15', null);
INSERT INTO `official_evaluation_item` VALUES ('ae953778a65e48f19b0c80144a21e948', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '严格遵守MVC模式，接口编程思想。结合算法与数据结构，在编码的时候尽可能做到时间，空间复杂度最优。在无法两者都兼顾的情况下，考虑到系统业务的性质，采取损失空间换取时间的策略。', '10', '8');
INSERT INTO `official_evaluation_item` VALUES ('b42f6787ed45408d92c8061ea50e4b1d', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '', '15', null);
INSERT INTO `official_evaluation_item` VALUES ('b526a99b9d3a47dc9510564bba20766f', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '', '0', '2');
INSERT INTO `official_evaluation_item` VALUES ('b7262553d38d46309462e8ee69ea02eb', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '', '13', '14');
INSERT INTO `official_evaluation_item` VALUES ('c34d384ed6c44b70846e5d96d529aaee', '2018-07-09 17:38:33', '2018-07-09 17:39:15', '109f84db9b1744dbb821c35ec87b832d', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', 'a', '10', '0');
INSERT INTO `official_evaluation_item` VALUES ('cf5154e8c4a24f3e88763e5db3a1e91b', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '测试', '9', '7');
INSERT INTO `official_evaluation_item` VALUES ('d3adb716e0d74507b33c5785cf3a8f9f', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '学习了jetty', '5', '0');
INSERT INTO `official_evaluation_item` VALUES ('d43a1be111204613a0d9a3d477f0ffd0', '2018-06-29 08:01:04', '2018-06-29 08:04:05', 'e6b4a3cab12845dfb6b81237b892867e', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '', '9', '0');
INSERT INTO `official_evaluation_item` VALUES ('dce860d3886643f9a2f505e4b631873b', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '工作结果指标', '10', 'bug及处理', '20', '其他部门或者产品反馈问题的处理及时性，为更好更及时地处理问题，做了哪些努力？', '', '12', '15');
INSERT INTO `official_evaluation_item` VALUES ('ef8e2ee538f0493ba07e534a2b879c1e', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '工作表现（统一）', '0', '文化认同、工作态度、行为表现', '15', '认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。', '积极向上正能量的鸟人思想，我是认同的', '15', '15');
INSERT INTO `official_evaluation_item` VALUES ('f2ca3c0cf1d443828074544855c9879c', '2018-06-27 11:33:40', '2018-07-05 15:13:07', 'ccbcad53efe649fca6a16551a8d76aa8', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '是的', '15', '0');
INSERT INTO `official_evaluation_item` VALUES ('f457c402cc89455fa1772054350c493e', '2018-07-12 18:06:50', '2018-07-12 18:08:47', '1bfabdef6c6944989949194063abf4b8', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '', '14', '14');
INSERT INTO `official_evaluation_item` VALUES ('f606233c3d9a416d92249e15c1b85d6d', '2018-06-29 08:02:55', '2018-06-29 08:02:55', '117756ed216b42f18d07b5d85a8a61bc', '工作结果指标', '10', '代码质量', '10', '为何保证自己代码的质量？是否为重要的服务都写了单元测试，采取了哪些措施来降低自己代码的维护成本？代码的注释率有多少？', '', '10', null);
INSERT INTO `official_evaluation_item` VALUES ('f656b03779044e9fa91750da9fa408dd', '2018-07-10 14:22:17', '2018-07-10 14:24:38', '4746098a1162441a8e143ed85ca457c8', '关键行为指标', '5', '主动性和责任心', '15', '在发现系统bug，或者安全问题的时候是否主动思考并提出解决方案', '有的，即使再缜密，也难免系统会出现小BUG，在出现BUG的时候马上分析错误的原因，在得知原因进行解决方案的思考。本次所开发的系统，不存在严重的BUG。', '13', '13');
INSERT INTO `official_evaluation_item` VALUES ('fb872f71ce514e768180632937ae1eea', '2018-07-13 14:23:50', '2018-07-13 14:25:20', 'd7614eb8163a417ea08028aebe7dd994', '工作结果指标', '10', '项目研发', '25', '是否能在规定的时间独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级，是否及时完成任务。S.提前完成并未项目做出突出贡献。A.提前完成。B.基本能按时完成。C.没有按时完成', '', '15', '18');
INSERT INTO `official_evaluation_item` VALUES ('fbdf90d238ba4365988861af1e27dc1d', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '关键行为指标', '5', '学习与分享', '5', '学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力的分享？有没有带新人，帮助新人学习成长？', '', '0', '4');
INSERT INTO `official_evaluation_item` VALUES ('fe1424a308cd4c8f8daeab8d76ce9d96', '2018-07-10 14:42:26', '2018-07-11 10:06:49', '3af34d81a1004fc982f83ea8f1fa9558', '关键行为指标', '5', '节流和支撑', '10', '采取了哪些技术手段优化了系统的性能？如何采用技术手段去节约人力和资源成本？', '', '0', '8');

-- ----------------------------
-- Table structure for official_performance_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `official_performance_evaluation`;
CREATE TABLE `official_performance_evaluation` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `department_name` varchar(10) NOT NULL COMMENT '部门名',
  `job_name` varchar(20) NOT NULL COMMENT '岗位名',
  `year` smallint(5) unsigned NOT NULL COMMENT '考核年份',
  `month` tinyint(3) unsigned NOT NULL COMMENT '考核月份',
  `user_no` char(6) NOT NULL COMMENT '考核人工号',
  `user_name` varchar(20) NOT NULL COMMENT '考核人姓名',
  `status` enum('INIT','BOSS1','BOSS2','HR') NOT NULL DEFAULT 'INIT' COMMENT '考核状态（详细意思参考数据库设计文档）',
  `boss1_remark` varchar(255) NOT NULL DEFAULT '' COMMENT '直属上级评语',
  `boss2_remark` varchar(255) NOT NULL DEFAULT '' COMMENT '部门负责人评语',
  `ex_grade` tinyint(4) NOT NULL DEFAULT '0' COMMENT '特别分',
  `ex_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '特别分理由',
  `final_grade` decimal(5,2) DEFAULT NULL COMMENT '最终得分',
  `boss1_no` char(6) DEFAULT NULL COMMENT '直属上司工号',
  `boss1_name` varchar(20) DEFAULT NULL COMMENT '直属上司签名',
  `boss2_no` char(6) DEFAULT NULL COMMENT '部门负责人工号',
  `boss2_name` varchar(20) DEFAULT NULL COMMENT '部门负责人签名',
  `hr_no` char(6) DEFAULT NULL COMMENT '人力行政部负责人工号',
  `hr_name` varchar(20) DEFAULT NULL COMMENT '人力行政部负责人签名',
  PRIMARY KEY (`id`),
  KEY `idx_year_month` (`year`,`month`),
  KEY `idx_user_no` (`user_no`),
  KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效考核表正式表（存档记录）';

-- ----------------------------
-- Records of official_performance_evaluation
-- ----------------------------
INSERT INTO `official_performance_evaluation` VALUES ('d7614eb8163a417ea08028aebe7dd994', '2018-07-13 14:23:50', '2018-07-13 14:28:57', '研发部', 'Java开发工程师', '2018', '7', '1327', '黄冬阳', 'HR', 'GOOD', 'OK', '10', 'OKOK', '72.60', '0001', '（测试）直属上司', '0002', '（测试）研发部总监', '0003', '（测试）人力HR');

-- ----------------------------
-- Table structure for performance_evaluation_template
-- ----------------------------
DROP TABLE IF EXISTS `performance_evaluation_template`;
CREATE TABLE `performance_evaluation_template` (
  `id` char(32) NOT NULL COMMENT 'UUID',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(20) NOT NULL COMMENT '绩效考核表模板名称',
  `job_id` char(32) NOT NULL COMMENT '模板对应的岗位id',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效考核表模板';

-- ----------------------------
-- Records of performance_evaluation_template
-- ----------------------------
INSERT INTO `performance_evaluation_template` VALUES ('1ff9722e385842f8be71b432a2603e15', '2018-06-26 13:01:45', '2018-07-12 11:09:27', '运维工程师', '58557830ba22453fa245cdead8a5a888');
INSERT INTO `performance_evaluation_template` VALUES ('76688063e7534b0787429bfed8239983', '2018-07-13 14:32:37', '2018-07-13 14:32:37', 'test', 'be6354dee52041f49cfa733c10dade84');
INSERT INTO `performance_evaluation_template` VALUES ('e1a9ae4ffbf748049190468b7325d838', '2018-06-26 11:23:43', '2018-06-26 15:37:59', '开发工程师2.0', '2f6e7ccae2e441ffbb5a5428c98c870c');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
  `status` enum('NORMAL','DISABLED') NOT NULL DEFAULT 'NORMAL' COMMENT '角色状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('23828f9c18174efb95aeb15d0f119bbe', '2018-06-28 10:31:22', '2018-07-10 08:34:23', '绩效考核表人事行政负责人（默认）', '（系统默认初始角色）拥有绩效考核表的最终审核权限，即拥有绩效考核表在部门负责人打完特别分后的审核权力。', 'NORMAL');
INSERT INTO `role` VALUES ('2dbfc23dc4a94e8ab327ca83dcaad3e1', '2018-07-10 14:50:00', '2018-07-13 14:18:28', '会议室管理员', '允许取消会议预约，查看会议室详细信息，增加删除修改会议室', 'NORMAL');
INSERT INTO `role` VALUES ('60f1a73be94343268d01eec12ab31845', '2018-07-09 18:12:03', '2018-07-13 09:12:25', '初始角色（默认）', '（系统默认初始角色）每个新用户默认一开始都会拥有的角色。该角色包含会议室预约等一些基本系统功能', 'NORMAL');
INSERT INTO `role` VALUES ('a378c9e957f84d69a88433ca58d0254c', '2018-07-09 17:16:57', '2018-07-10 08:29:59', '系统超级管理员（默认）', '（系统默认角色）该角色拥有系统所有权限，即能完整的使用整个系统的所有功能。请谨慎分配', 'NORMAL');

-- ----------------------------
-- Table structure for role_to_url
-- ----------------------------
DROP TABLE IF EXISTS `role_to_url`;
CREATE TABLE `role_to_url` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` char(32) NOT NULL,
  `url_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roleid_urlid` (`role_id`,`url_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_to_url
-- ----------------------------
INSERT INTO `role_to_url` VALUES ('00d31d2fb166452d92b5797057ee2968', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '2a96910ac9e943f3873c42156f795236');
INSERT INTO `role_to_url` VALUES ('07cebeeaf9d54f7a9462de28d47c08e8', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '6857ea07ef1d4673b5cf953983b0321a');
INSERT INTO `role_to_url` VALUES ('07ddc3c7585b4d1a88e8c135d908a226', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '0fe5fd2818ed4a2ca21d6e083ad6a916');
INSERT INTO `role_to_url` VALUES ('0b1008f34c284738ab05d3871961ef48', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', 'd04988a9dd324d3698a814775da44dba');
INSERT INTO `role_to_url` VALUES ('0c1eb5a33fe24ec5a8b37d225d4d532b', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '7f64189f5c0d47b0bc3cfc5328775fb8');
INSERT INTO `role_to_url` VALUES ('0cd3585acf0e40dc8d8358f256b83083', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '456a045b6352436c9903fac0bb249339');
INSERT INTO `role_to_url` VALUES ('0e394920a76e407390c432adc34e220e', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'a432b75bef314817bbb2317b47e9fdc6');
INSERT INTO `role_to_url` VALUES ('11970f7a3bae4712b60147d398b2db07', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'ed9dc4c0e6d946d49760184547518b97');
INSERT INTO `role_to_url` VALUES ('14e1b857e33c468d86867e778abc345e', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '830bf8e76bd749089e5e6a9390eb1a30');
INSERT INTO `role_to_url` VALUES ('16a5630ac72749d193f3634f561d83f3', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '907bd8c64a88425c8a504751f009db78');
INSERT INTO `role_to_url` VALUES ('183326e27db543c5a0d97b4a6778074e', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '394f93f750144ad282b4c270bfd3fe9b');
INSERT INTO `role_to_url` VALUES ('1891468c7c74401db7a18259d622ebfc', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '396690d7dae84de082637821e6c4aa81');
INSERT INTO `role_to_url` VALUES ('196da07ffc3746cfa2b5809a5f7fcf70', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', 'b3cab687a3a94e24a4cf606d571f5575');
INSERT INTO `role_to_url` VALUES ('1ee8aa32eb19491aaf0eac66dd6d3ab9', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '7f64189f5c0d47b0bc3cfc5328775fb8');
INSERT INTO `role_to_url` VALUES ('21d27e0976b4473ea0257c1be6a4816e', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '57fc9282722f4a15ad88cc9a73cc8eb9');
INSERT INTO `role_to_url` VALUES ('232d69ea37c146e5bb46c6bef5b0a180', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'bb671a3616c54e90843083113c3b506c');
INSERT INTO `role_to_url` VALUES ('2fc265bd778c45ab877521ef300c123a', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'a1dd79f32da640d4bc0533920b15c6cc');
INSERT INTO `role_to_url` VALUES ('31ba1a771b4448d4a53493f9c7988f2f', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '6977873bc1b94223b5762101af098c8c');
INSERT INTO `role_to_url` VALUES ('34ede7e3160a44f9879dd773aa708616', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '50feeb91061e4997b5311e6462fa92db');
INSERT INTO `role_to_url` VALUES ('38e594d8500547c3bafe4c3cb5031bdd', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'b9c3370d2c694549997b1bca5f7c1d7d');
INSERT INTO `role_to_url` VALUES ('39712561139d44069d8feeab0a7ae3ea', '2018-07-09 17:17:40', '2018-07-09 17:17:40', 'a378c9e957f84d69a88433ca58d0254c', 'f308045350494060af74c5ec45f3beae');
INSERT INTO `role_to_url` VALUES ('3ced7c7fc3004c9781e2dc1547582fc1', '2018-07-09 17:17:40', '2018-07-09 17:17:40', 'a378c9e957f84d69a88433ca58d0254c', 'a4b663d818c849abb960096e2a406a48');
INSERT INTO `role_to_url` VALUES ('3ddbfff20eb341eaaf50bc64ab2f5173', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '676d2574d88e44919a7ed399e3dc2d36');
INSERT INTO `role_to_url` VALUES ('41e6e52f4e4e4311b422afc8db799ec0', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '774d4cde5aca4518b9dc4d90516b8b5e');
INSERT INTO `role_to_url` VALUES ('44a5e038de424208ae8a2987e756bfce', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '151126289d8c4a488e437e54985e1e65');
INSERT INTO `role_to_url` VALUES ('49f4926cb88b41589947fe75850ff86b', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '6977873bc1b94223b5762101af098c8c');
INSERT INTO `role_to_url` VALUES ('4dac7e202a0947218c586b91d49764f4', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '0733267730484d93918fc540bab4823b');
INSERT INTO `role_to_url` VALUES ('51c69766d4b24f2f8cc14b4695bf190e', '2018-07-09 18:12:57', '2018-07-09 18:12:57', '60f1a73be94343268d01eec12ab31845', '68598c42366749748094b31841f25300');
INSERT INTO `role_to_url` VALUES ('5330257390454a1d86eed6e33caf61b5', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'c95b0afd44b749e3a460e23990889523');
INSERT INTO `role_to_url` VALUES ('5722ad8b7a0e498c8189d2f2079e8ceb', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'd21040d1d72740fe8acc3a142c9b57c7');
INSERT INTO `role_to_url` VALUES ('5785fcd22437473fabe585303d239cdc', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'a4d61aa10fea4b3ba87cc468d986b114');
INSERT INTO `role_to_url` VALUES ('5867add07e46491d87c6b55e4fce3258', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '456a045b6352436c9903fac0bb249339');
INSERT INTO `role_to_url` VALUES ('5ee5d63f8cae42abbcd8063a1f8b1fd9', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '44946eb5cdc74e2fb5f7db533e749fba');
INSERT INTO `role_to_url` VALUES ('5f52253717434d45ba573d2c7f6a70b8', '2018-07-13 14:18:48', '2018-07-13 14:18:48', '2dbfc23dc4a94e8ab327ca83dcaad3e1', 'fd1f6c98474c4e9caffbd903cb78d56a');
INSERT INTO `role_to_url` VALUES ('639f808e1d78416aa31fe172001eac3c', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'dda911d87d17422c962c521ea8bdc9e9');
INSERT INTO `role_to_url` VALUES ('65d28f0162334de4b23b7e1d5229b036', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '84fb3f51fdcd457a97f903d472cc5d0d');
INSERT INTO `role_to_url` VALUES ('66e1a35eca9c4732834510b858c8b30d', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '1bbc432f4dd64f31b6c9433fcc29aae3');
INSERT INTO `role_to_url` VALUES ('7b9b8f887a6f4aa9b0c96b6e76b6a835', '2018-07-06 18:27:41', '2018-07-06 18:27:41', '23828f9c18174efb95aeb15d0f119bbe', 'fd1f6c98474c4e9caffbd903cb78d56a');
INSERT INTO `role_to_url` VALUES ('7d7c6a2d80a640a18ce564e1dd728bfd', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '8adfc21e26324ef789810058a81f1815');
INSERT INTO `role_to_url` VALUES ('7dfbda84c3794682a25ac3dcd625bc9c', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', 'd2fb967fb4bb431480632b71e7626475');
INSERT INTO `role_to_url` VALUES ('81a27b61bff94780bd459cb9f196d60c', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '68598c42366749748094b31841f25300');
INSERT INTO `role_to_url` VALUES ('89625f039c3e44e4a571a10785c1064b', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '1c549cc644de4a378a99a649fbc1c335');
INSERT INTO `role_to_url` VALUES ('95d118cbcf5e451389b1cf8923c7bd61', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'fdd3a72eaa62480ab29200529014b73e');
INSERT INTO `role_to_url` VALUES ('98e8bcc0a1b54a0c97594a72fae99de3', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '55105e4ee69f4dadb90cd0e95af42994');
INSERT INTO `role_to_url` VALUES ('9a04b4a027ff486090d29474d0e87561', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '50feeb91061e4997b5311e6462fa92db');
INSERT INTO `role_to_url` VALUES ('a3182744b9294dd18c8c4fc56ca503dd', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '30a9fc4590d04abeaedb31f5fd7e725f');
INSERT INTO `role_to_url` VALUES ('a931d2b8d6b54132838b9eaa7ba95b2e', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', '68598c42366749748094b31841f25300');
INSERT INTO `role_to_url` VALUES ('b0058cedd5c34b119c533df5f24f5fc3', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '5c4d1af0798e476cb7500e9fbb170e04');
INSERT INTO `role_to_url` VALUES ('bc9c1f2383b44517bb1df20b736ed0d6', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '676d2574d88e44919a7ed399e3dc2d36');
INSERT INTO `role_to_url` VALUES ('c16a879ec5d544ce9acc769811efe89a', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '30a9fc4590d04abeaedb31f5fd7e725f');
INSERT INTO `role_to_url` VALUES ('c5e5437d02df42ad806988fc9bfd9613', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'feb7d0bd103c416b89c51ac93eeb438c');
INSERT INTO `role_to_url` VALUES ('d01bb807d3294e1286de7314a69cb08f', '2018-07-13 14:18:47', '2018-07-13 14:18:47', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '1bbc432f4dd64f31b6c9433fcc29aae3');
INSERT INTO `role_to_url` VALUES ('d2b89a3fb548480ea1c635c05aff47c7', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', '00023613399a4c5c8c1c1f075612f9ab');
INSERT INTO `role_to_url` VALUES ('d37fdd415a72437785c3000f45491d2f', '2018-07-09 18:12:57', '2018-07-09 18:12:57', '60f1a73be94343268d01eec12ab31845', '1bbc432f4dd64f31b6c9433fcc29aae3');
INSERT INTO `role_to_url` VALUES ('d78041acf5a040f09f85dfbed070439d', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '57e39b995b844641b2998f1f25e27a5f');
INSERT INTO `role_to_url` VALUES ('d7c47d66f09d472d9e20249fd7bebc16', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'f174fbc48d1746938891466e79355cc7');
INSERT INTO `role_to_url` VALUES ('daa741e4f7044b598bb2d35f8dc6f1b1', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '4683a4d507074ee1982791ad5c6516e2');
INSERT INTO `role_to_url` VALUES ('e184dbef918041b098263c31d8469293', '2018-07-09 17:17:40', '2018-07-09 17:17:40', 'a378c9e957f84d69a88433ca58d0254c', 'af67f1b124f64f29ba17dfcb075e9e0d');
INSERT INTO `role_to_url` VALUES ('f15e8713afd848959a82753987798de1', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '08b114d0043544d9863da31f125cfe05');
INSERT INTO `role_to_url` VALUES ('f27960774da64f0fb9ef2d6478f529aa', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', 'cfd612ff353840459cc1a2ad5fa40bce');
INSERT INTO `role_to_url` VALUES ('f43392bb1c7b46aea14c2e839536ab87', '2018-07-09 17:17:39', '2018-07-09 17:17:39', 'a378c9e957f84d69a88433ca58d0254c', 'bc001b27966948bcb175e03bb9a070a7');
INSERT INTO `role_to_url` VALUES ('f4d4f6ca489e4ff48f673bd60128047e', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'd2fb967fb4bb431480632b71e7626475');
INSERT INTO `role_to_url` VALUES ('f59c90f8c046449d9b2bc1d3f9978697', '2018-07-10 17:36:47', '2018-07-10 17:36:47', '2dbfc23dc4a94e8ab327ca83dcaad3e1', 'feb7d0bd103c416b89c51ac93eeb438c');
INSERT INTO `role_to_url` VALUES ('f5da9b5cb588413ba9b4ec7914565c72', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', 'fd1f6c98474c4e9caffbd903cb78d56a');
INSERT INTO `role_to_url` VALUES ('f8f61269e3e94953bf851da843a1790f', '2018-07-09 17:17:37', '2018-07-09 17:17:37', 'a378c9e957f84d69a88433ca58d0254c', 'a792ce28a5df4628b04f5c585577f4f2');
INSERT INTO `role_to_url` VALUES ('fc0bd7eec6af44a0b7ddb7dd5941c753', '2018-07-10 14:50:32', '2018-07-10 14:50:32', '2dbfc23dc4a94e8ab327ca83dcaad3e1', '394f93f750144ad282b4c270bfd3fe9b');
INSERT INTO `role_to_url` VALUES ('fd7dcc718761406d8271d274abb6617b', '2018-07-09 17:17:38', '2018-07-09 17:17:38', 'a378c9e957f84d69a88433ca58d0254c', '44946eb5cdc74e2fb5f7db533e749fba');
INSERT INTO `role_to_url` VALUES ('feab326d7198496c9fe785be8ed64333', '2018-07-09 17:17:40', '2018-07-09 17:17:40', 'a378c9e957f84d69a88433ca58d0254c', '89b1e2efb3ec4120abf73baa8eed9e24');

-- ----------------------------
-- Table structure for system_url
-- ----------------------------
DROP TABLE IF EXISTS `system_url`;
CREATE TABLE `system_url` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(20) NOT NULL COMMENT '权限名',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `remark` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `status` enum('NORMAL','DISABLED') NOT NULL DEFAULT 'NORMAL' COMMENT '状态',
  `level` tinyint(3) unsigned NOT NULL COMMENT '菜单级别（0非功能菜单，其他为功能菜单)',
  `parent_id` char(32) DEFAULT NULL COMMENT '父级功能菜单URL的ID',
  `parent_url` varchar(255) DEFAULT NULL COMMENT '父级URL的url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_url` (`url`),
  KEY `idx_status` (`status`),
  KEY `idx_name` (`name`),
  KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_url
-- ----------------------------
INSERT INTO `system_url` VALUES ('00023613399a4c5c8c1c1f075612f9ab', '2018-07-09 14:35:02', '2018-07-09 17:18:06', '系统-部门岗位管理-删除部门', '/sys/job/deleteDepartment', '删除系统中的一个部门', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('0733267730484d93918fc540bab4823b', '2018-07-09 14:46:55', '2018-07-09 17:18:06', '系统-角色权限管理-删除角色用户', '/sys/role/deleteRoleUser', '为某个角色去除用户', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('08b114d0043544d9863da31f125cfe05', '2018-07-09 13:54:17', '2018-07-09 17:18:06', '系统-用户管理-重置用户密码', '/sys/user/resetPassword', '重置系统中任何一个用户的密码', 'NORMAL', '0', '0fe5fd2818ed4a2ca21d6e083ad6a916', '/sys/user');
INSERT INTO `system_url` VALUES ('0fe5fd2818ed4a2ca21d6e083ad6a916', '2018-06-19 13:49:52', '2018-07-12 09:38:45', '系统-用户管理首页', '/sys/user', '（功能菜单）进入用户管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('151126289d8c4a488e437e54985e1e65', '2018-07-09 15:19:24', '2018-07-09 17:18:06', '考核-模板管理-删除考核项目', '/evaluation/templateManage/deleteItem', '模板管理中，删除考核项目', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('1bbc432f4dd64f31b6c9433fcc29aae3', '2018-07-09 15:05:41', '2018-07-09 17:18:06', '考核-进行绩效考核', '/evaluation/examine/examineTableShow', '进行本月的绩效考核', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('1c549cc644de4a378a99a649fbc1c335', '2018-07-09 15:15:41', '2018-07-09 17:18:06', '考核-汇总管理-查看绩效考核表信息', '/evaluation/viewExamine/showOfficialEvaluationTable', '查看系统中任一绩效考核表的详细表格信息', 'NORMAL', '2', 'a792ce28a5df4628b04f5c585577f4f2', '/evaluation/viewExamine');
INSERT INTO `system_url` VALUES ('2a96910ac9e943f3873c42156f795236', '2018-07-09 14:34:12', '2018-07-09 17:18:06', '系统-部门岗位管理-更新部门', '/sys/job/updateDepartment', '更新部门的信息', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('30a9fc4590d04abeaedb31f5fd7e725f', '2018-07-09 15:42:56', '2018-07-09 17:18:06', '会议-会议室管理-增加会议室（图片上传）', '/meeting/addRoomWithImg', '增加会议室（带自己的图片上传）', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('394f93f750144ad282b4c270bfd3fe9b', '2018-07-09 15:41:36', '2018-07-09 17:18:06', '会议-会议室管理-获取会议室信息', '/meeting/listRoomLimit', '获取系统中所有会议室信息', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('396690d7dae84de082637821e6c4aa81', '2018-07-09 15:08:52', '2018-07-09 17:18:07', '考核-汇总管理-查看系统绩效考核表', '/evaluation/viewExamine/list', '查看系统中所有绩效考核表', 'NORMAL', '0', 'a792ce28a5df4628b04f5c585577f4f2', '/evaluation/viewExamine');
INSERT INTO `system_url` VALUES ('44946eb5cdc74e2fb5f7db533e749fba', '2018-07-09 15:46:20', '2018-07-09 17:18:07', '会议-会议室管理-更新会议室状态', '/meeting/updateRoomStatus', '改变会议室状态', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('456a045b6352436c9903fac0bb249339', '2018-07-09 15:32:40', '2018-07-09 17:18:07', '会议-会议预约记录首页', '/orderMeeting/toOrderRecordJsp', '（功能菜单）跳转会议室预约记录首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('4683a4d507074ee1982791ad5c6516e2', '2018-07-09 14:49:02', '2018-07-09 17:18:07', '系统-角色权限管理-获得角色未拥有的用户', '/sys/role/listRoleWithoutUsers', '获得角色未拥有的用户列表', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('50feeb91061e4997b5311e6462fa92db', '2018-07-09 15:49:14', '2018-07-09 17:18:07', '会议-会议室管理-更改会议室图片', '/meeting/updateRoomImg', '更新会议室图片', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('55105e4ee69f4dadb90cd0e95af42994', '2018-07-09 14:52:49', '2018-07-09 17:18:07', '系统-角色权限管理-角色权限分配', '/sys/role/updateRoleUrl', '为角色进行权限分配', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('57e39b995b844641b2998f1f25e27a5f', '2018-07-09 14:43:02', '2018-07-09 17:18:07', '系统-角色权限管理-删除角色', '/sys/role/deleteRole', '删除角色', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('57fc9282722f4a15ad88cc9a73cc8eb9', '2018-07-09 15:20:25', '2018-07-09 17:18:07', '考核-模板管理-更新考核项目', '/evaluation/templateManage/updateItem', '模板管理-更新考核项目', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('5c4d1af0798e476cb7500e9fbb170e04', '2018-07-09 14:58:08', '2018-07-09 17:18:07', '系统-权限控制-更改权限状态', '/sys/url/changeUrlStatus', '更新权限的运行状态', 'NORMAL', '0', 'b3cab687a3a94e24a4cf606d571f5575', '/sys/role');
INSERT INTO `system_url` VALUES ('676d2574d88e44919a7ed399e3dc2d36', '2018-07-09 15:50:07', '2018-07-09 17:18:07', '会议-会议室管理-删除会议室', '/meeting/deleteRoom', '删除会议室', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('6857ea07ef1d4673b5cf953983b0321a', '2018-07-09 14:33:13', '2018-07-09 17:18:07', '系统-部门岗位管理-新增部门', '/sys/job/addDepartment', '增加部门', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('68598c42366749748094b31841f25300', '2018-07-09 15:27:02', '2018-07-09 17:18:07', '会议-预约会议室', '/orderMeeting/doOrder', '预约会议室', 'NORMAL', '0', null, null);
INSERT INTO `system_url` VALUES ('6977873bc1b94223b5762101af098c8c', '2018-07-09 15:41:03', '2018-07-09 17:18:07', '会议-会议室管理首页', '/meeting/toRoomManageJsp', '（功能菜单）跳转会议室管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('774d4cde5aca4518b9dc4d90516b8b5e', '2018-07-09 14:45:51', '2018-07-09 17:18:07', '系统-角色权限管理-新增角色用户', '/sys/role/addRoleUser', '为角色增加用户', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('7f64189f5c0d47b0bc3cfc5328775fb8', '2018-07-09 15:43:52', '2018-07-09 17:18:07', '会议-会议室管理-增加会议室', '/meeting/addRoomWithDefaultImg', '增加会议室（用默认图）', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');
INSERT INTO `system_url` VALUES ('830bf8e76bd749089e5e6a9390eb1a30', '2018-07-09 14:36:40', '2018-07-12 09:38:45', '系统-角色权限管理首页', '/sys/role', '（功能菜单）跳转角色权限管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('84fb3f51fdcd457a97f903d472cc5d0d', '2018-06-19 14:04:53', '2018-07-09 17:18:08', '系统-用户管理-更新用户', '/sys/user/updateUser', '更新用户', 'NORMAL', '0', '0fe5fd2818ed4a2ca21d6e083ad6a916', '/sys/user');
INSERT INTO `system_url` VALUES ('89b1e2efb3ec4120abf73baa8eed9e24', '2018-07-09 15:22:14', '2018-07-09 17:18:08', '考核-模板管理-增加考核模板', '/evaluation/templateManage/addTemplate', '模板管理-增加考核模板', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('8adfc21e26324ef789810058a81f1815', '2018-07-09 14:26:58', '2018-07-09 17:18:08', '系统-部门岗位管理-查看岗位信息', '/sys/job/listJob', '获取岗位信息列表', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('907bd8c64a88425c8a504751f009db78', '2018-07-09 14:42:08', '2018-07-09 17:18:08', '系统-角色权限管理-更新角色', '/sys/role/updateRole', '更新角色', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('a1dd79f32da640d4bc0533920b15c6cc', '2018-06-19 13:51:54', '2018-07-13 14:20:47', '系统-用户管理-查看用户信息', '/sys/user/listUser', '允许查看系统中所有的用户信息', 'NORMAL', '0', '0fe5fd2818ed4a2ca21d6e083ad6a916', '/sys/user');
INSERT INTO `system_url` VALUES ('a432b75bef314817bbb2317b47e9fdc6', '2018-07-09 14:40:32', '2018-07-09 17:18:08', '系统-角色权限管理-新增角色', '/sys/role/addRole', '增加新角色', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('a4b663d818c849abb960096e2a406a48', '2018-07-09 15:23:01', '2018-07-09 17:18:08', '考核-模板管理-更新考核模板', '/evaluation/templateManage/updateTemplate', '模板管理-更新考核模板', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('a4d61aa10fea4b3ba87cc468d986b114', '2018-07-09 14:39:40', '2018-07-09 17:18:08', '系统-角色权限管理-获取角色信息', '/sys/role/getRole', '获取角色信息', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('a792ce28a5df4628b04f5c585577f4f2', '2018-07-09 15:07:49', '2018-07-09 17:18:08', '考核-绩效考核汇总管理首页', '/evaluation/viewExamine', '（功能菜单）进入绩效考核汇总管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('af67f1b124f64f29ba17dfcb075e9e0d', '2018-07-09 15:21:26', '2018-07-09 17:18:08', '考核-模板管理-增加考核项目', '/evaluation/templateManage/addItem', '模板管理-增加考核项目', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('b3cab687a3a94e24a4cf606d571f5575', '2018-07-09 14:55:10', '2018-07-12 09:35:11', '系统-权限控制首页', '/sys/url', '（功能菜单）（无法禁用）跳转权限控制首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('b9c3370d2c694549997b1bca5f7c1d7d', '2018-07-09 14:50:28', '2018-07-09 17:18:08', '系统-角色权限管理-获得角色拥有的权限', '/sys/role/listRoleUrls', '获得角色当前拥有的权限列表', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('bb671a3616c54e90843083113c3b506c', '2018-07-09 14:30:50', '2018-07-09 17:18:08', '系统-部门岗位管理-删除岗位', '/sys/job/deleteJob', '删除岗位', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('bc001b27966948bcb175e03bb9a070a7', '2018-07-09 14:29:31', '2018-07-09 17:18:08', '系统-部门岗位管理-增加岗位', '/sys/job/addJob', '增加岗位', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('c95b0afd44b749e3a460e23990889523', '2018-06-19 14:05:58', '2018-07-09 17:18:08', '系统-用户管理-删除用户', '/sys/user/deleteUser', '删除用户', 'NORMAL', '0', '0fe5fd2818ed4a2ca21d6e083ad6a916', '/sys/user');
INSERT INTO `system_url` VALUES ('cfd612ff353840459cc1a2ad5fa40bce', '2018-07-09 14:24:12', '2018-07-09 17:18:08', '系统-部门岗位管理首页', '/sys/job', '（功能菜单）跳转部门岗位管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('d04988a9dd324d3698a814775da44dba', '2018-07-09 15:18:15', '2018-07-09 17:18:08', '考核-模板管理首页', '/evaluation/templateManage', '（功能菜单）跳转模板管理首页', 'NORMAL', '1', null, null);
INSERT INTO `system_url` VALUES ('d21040d1d72740fe8acc3a142c9b57c7', '2018-07-09 14:56:56', '2018-07-09 17:18:09', '系统-权限控制-获取系统权限列表', '/sys/url/listUrl', '（不可禁用）权限控制-获取系统权限列表', 'NORMAL', '0', 'b3cab687a3a94e24a4cf606d571f5575', '/sys/role');
INSERT INTO `system_url` VALUES ('d2fb967fb4bb431480632b71e7626475', '2018-07-09 15:32:58', '2018-07-09 17:18:09', '会议-会议预约记录-取消会议', '/orderMeeting/cancelOrder', '取消会议', 'NORMAL', '0', '456a045b6352436c9903fac0bb249339', '/orderMeeting/toOrderRecordJsp');
INSERT INTO `system_url` VALUES ('dda911d87d17422c962c521ea8bdc9e9', '2018-06-19 13:52:34', '2018-07-09 17:18:09', '系统-用户管理-增加新用户', '/sys/user/addUser', '增加新用户', 'NORMAL', '0', '0fe5fd2818ed4a2ca21d6e083ad6a916', '/sys/user');
INSERT INTO `system_url` VALUES ('ed9dc4c0e6d946d49760184547518b97', '2018-07-09 14:32:05', '2018-07-09 17:18:09', '系统-部门岗位管理-更新岗位', '/sys/job/updateJob', '更新岗位', 'NORMAL', '0', 'cfd612ff353840459cc1a2ad5fa40bce', '/sys/job');
INSERT INTO `system_url` VALUES ('f174fbc48d1746938891466e79355cc7', '2018-07-09 14:38:27', '2018-07-09 17:18:09', '系统-角色权限管理-获取角色的用户', '/sys/role/listRoleUser', '获取角色的所有用户', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('f308045350494060af74c5ec45f3beae', '2018-07-09 15:24:11', '2018-07-09 17:18:09', '考核-模板管理-删除考核模板', '/evaluation/templateManage/deleteTemplate', '删除考核模板', 'NORMAL', '0', 'd04988a9dd324d3698a814775da44dba', '/evaluation/templateManage');
INSERT INTO `system_url` VALUES ('fd1f6c98474c4e9caffbd903cb78d56a', '2018-06-27 13:27:26', '2018-07-09 17:18:09', '绩效-绩效考核最终审核权', '/evaluation/examine/pendingExamine/hrExam', '绩效模块-绩效考核表最终审核权限（即绩效考核表的人力行政负责人）。该权限的拥有着可以查看所有待最终审核的考核表并进行处理', 'NORMAL', '0', null, null);
INSERT INTO `system_url` VALUES ('fdd3a72eaa62480ab29200529014b73e', '2018-07-09 14:37:33', '2018-07-09 17:18:09', '系统-角色权限管理-获取系统角色列表', '/sys/role/listRole', '获取系统角色列表', 'NORMAL', '0', '830bf8e76bd749089e5e6a9390eb1a30', '/sys/role');
INSERT INTO `system_url` VALUES ('feb7d0bd103c416b89c51ac93eeb438c', '2018-07-09 15:45:38', '2018-07-09 17:18:09', '会议-会议室管理-更新会议室信息', '/meeting/updateMeetingRoomField', '更新会议室信息', 'NORMAL', '0', '6977873bc1b94223b5762101af098c8c', '/meeting/toRoomManageJsp');

-- ----------------------------
-- Table structure for template_to_item
-- ----------------------------
DROP TABLE IF EXISTS `template_to_item`;
CREATE TABLE `template_to_item` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `template_id` char(32) NOT NULL,
  `item_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_templateId_itemId` (`template_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绩效考核表模板 - 考核项目表 - 中间表';

-- ----------------------------
-- Records of template_to_item
-- ----------------------------
INSERT INTO `template_to_item` VALUES ('0f4d41e4c2d54df89384a411cf2ccc19', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', 'e56b9b7156a94a8da449e2eba3b12d85');
INSERT INTO `template_to_item` VALUES ('27b66e6155b143dc864d320a7ca0c629', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '57850d2f0d8c47c2bc1e4f7b591d7267');
INSERT INTO `template_to_item` VALUES ('4f5607be3a344742847b60faf4f25fca', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '3f458a5182f24037801ec874e6535c3c');
INSERT INTO `template_to_item` VALUES ('69fd835804504d76a00020116c3a850b', '2018-06-26 12:59:16', '2018-06-26 12:59:16', '7f4502c5b07d474382f2c31705615bec', '4b981694b406452184acdbd9fb345712');
INSERT INTO `template_to_item` VALUES ('80e43810f8664e2d9e6d5e570ad2caae', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '9110d05982a64862938240a9a98a4ebe');
INSERT INTO `template_to_item` VALUES ('81817404676b415ca593fa1095c8239d', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '4b981694b406452184acdbd9fb345712');
INSERT INTO `template_to_item` VALUES ('8392951da1e2469aafb2b58024f16f66', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '7e5c8d7b4b0a4b4888c34bf2a41224fe');
INSERT INTO `template_to_item` VALUES ('8de16e07a265424197e2797d8c05af92', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '57850d2f0d8c47c2bc1e4f7b591d7267');
INSERT INTO `template_to_item` VALUES ('8de6d4469274494bbce2146cf6b736fd', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '23b7f038abc141aaa58c9794d5932c7b');
INSERT INTO `template_to_item` VALUES ('922b969558ec49de922c0c29aed36ca9', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '23b7f038abc141aaa58c9794d5932c7b');
INSERT INTO `template_to_item` VALUES ('9c14f672d94a48bb91969e4b8e55aba5', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', 'e56b9b7156a94a8da449e2eba3b12d85');
INSERT INTO `template_to_item` VALUES ('a085387458c14f5f92d8595d33d2deea', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '4b981694b406452184acdbd9fb345712');
INSERT INTO `template_to_item` VALUES ('c4fa0731d0b54ba79f5cab8558485e11', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '7e5c8d7b4b0a4b4888c34bf2a41224fe');
INSERT INTO `template_to_item` VALUES ('d9a6949edd98434d985c0389e4300548', '2018-07-13 14:32:37', '2018-07-13 14:32:37', '76688063e7534b0787429bfed8239983', '3f458a5182f24037801ec874e6535c3c');
INSERT INTO `template_to_item` VALUES ('f208a809214b4235bab7f40f09656e5e', '2018-06-26 15:37:58', '2018-06-26 15:37:58', 'e1a9ae4ffbf748049190468b7325d838', '9110d05982a64862938240a9a98a4ebe');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(32) NOT NULL COMMENT '唯一UUID',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` char(11) NOT NULL COMMENT '手机号码',
  `no` char(6) NOT NULL COMMENT '工号',
  `email` varchar(32) NOT NULL COMMENT '企业邮箱',
  `sex` varchar(2) NOT NULL DEFAULT '' COMMENT '性别',
  `entrydate` date NOT NULL,
  `direct_supervisor_no` char(6) DEFAULT NULL COMMENT '直属上司工号',
  `job_id` char(32) NOT NULL COMMENT '岗位id',
  `job_name` varchar(20) NOT NULL COMMENT '岗位名称',
  `department_name` varchar(10) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone` (`phone`) USING HASH,
  UNIQUE KEY `uk_no` (`no`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16cc23596a0841d6b900dc230d3e8954', '2018-06-15 14:16:15', '2018-07-13 10:17:14', '黄冬阳', '04976203ca941310725266771e3cdbd3', '13631788594', '1327', 'huangdy@pvc123.com', '男', '2018-06-11', '0001', '2f6e7ccae2e441ffbb5a5428c98c870c', 'Java开发工程师', '研发部');
INSERT INTO `user` VALUES ('1fcacc6332084423add24bc3bd9f13ec', '2018-07-12 18:00:52', '2018-07-12 18:00:52', '（测试）人力HR', '2d743f0f288aee53ca93b1aacfebdced', '10000000003', '0003', '448241091@qq.com', '女', '2018-07-01', null, 'be6354dee52041f49cfa733c10dade84', '人事专员', '人力资源部');
INSERT INTO `user` VALUES ('906f66ab8b3e48a88e332e0571c8cc35', '2018-07-12 17:59:19', '2018-07-12 17:59:19', '（测试）研发部总监', '2d743f0f288aee53ca93b1aacfebdced', '10000000002', '0002', '1476029173@qq.com', '男', '2018-07-02', null, 'd9a58e533254447e8faf942da66d73ce', '项目经理', '研发部');
INSERT INTO `user` VALUES ('d5b309ba6bff4ba4851312bb7b32e878', '2018-07-13 09:04:21', '2018-07-13 09:21:33', '(测试）系统超管', '21232f297a57a5a743894a0e4a801fc3', '00000000000', '000000', 'huangdy@pvc123.com', '男', '2018-07-13', null, 'cf09041ce2de4c44985639018ccf8043', '（测试）系统超管', '总裁办');
INSERT INTO `user` VALUES ('fd6ed083d5294bc3bb3665a916dbf134', '2018-07-12 17:58:50', '2018-07-12 17:59:26', '（测试）直属上司', '2d743f0f288aee53ca93b1aacfebdced', '10000000001', '0001', '448241091@qq.com', '男', '2018-07-02', '0002', '2f6e7ccae2e441ffbb5a5428c98c870c', 'Java开发工程师', '研发部');

-- ----------------------------
-- Table structure for user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `user_to_role`;
CREATE TABLE `user_to_role` (
  `id` char(32) NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_roleid_userid` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户 - 角色 中间表';

-- ----------------------------
-- Records of user_to_role
-- ----------------------------
INSERT INTO `user_to_role` VALUES ('1865df3578704f20a1296266cac7e94b', '2018-07-12 18:00:52', '2018-07-12 18:00:52', '1fcacc6332084423add24bc3bd9f13ec', '60f1a73be94343268d01eec12ab31845');
INSERT INTO `user_to_role` VALUES ('279afe9ddf724a2e84bdf32aea87401b', '2018-07-13 09:04:20', '2018-07-13 09:04:20', 'd5b309ba6bff4ba4851312bb7b32e878', '60f1a73be94343268d01eec12ab31845');
INSERT INTO `user_to_role` VALUES ('283f12dc0a164326a303f4739c267fe8', '2018-07-13 09:05:44', '2018-07-13 09:05:44', 'd5b309ba6bff4ba4851312bb7b32e878', 'a378c9e957f84d69a88433ca58d0254c');
INSERT INTO `user_to_role` VALUES ('9d6fa6469dd14885ad3c245ffdef9a08', '2018-07-12 17:59:19', '2018-07-12 17:59:19', '906f66ab8b3e48a88e332e0571c8cc35', '60f1a73be94343268d01eec12ab31845');
INSERT INTO `user_to_role` VALUES ('a9faf9ee8c0b4b029267d6782c25fa72', '2018-07-12 18:01:05', '2018-07-12 18:01:05', '1fcacc6332084423add24bc3bd9f13ec', '23828f9c18174efb95aeb15d0f119bbe');
INSERT INTO `user_to_role` VALUES ('affa56d651d041ceae77f948b540f495', '2018-07-09 18:12:13', '2018-07-09 18:12:13', '16cc23596a0841d6b900dc230d3e8954', '60f1a73be94343268d01eec12ab31845');
INSERT INTO `user_to_role` VALUES ('b57995ef07e24ba588c7d28306982110', '2018-07-12 17:58:50', '2018-07-12 17:58:50', 'fd6ed083d5294bc3bb3665a916dbf134', '60f1a73be94343268d01eec12ab31845');
