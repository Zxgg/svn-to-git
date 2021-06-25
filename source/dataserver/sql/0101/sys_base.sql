/*
 Navicat Premium Data Transfer

 Source Server         : 10.16.123.60
 Source Server Type    : MySQL
 Source Server Version : 100131
 Source Host           : 10.16.123.60:3306
 Source Schema         : isstech

 Target Server Type    : MySQL
 Target Server Version : 100131
 File Encoding         : 65001

 Date: 31/12/2020 14:45:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES ('3', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门id',
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '祖级列表',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `division_code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行政区划最后一级code编码',
  `division_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行政区划最后一级存储codename编码名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '0', '扬州', 0, '扬州', '15888888888', 'yz@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-12 07:22:01', '', '');
INSERT INTO `sys_dept` VALUES ('10', '3', '0,1,3', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('2', '1', '0,1', '邗江区', 1, '邗江区', '15888888888', 'gjq@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-12 07:21:31', '', '');
INSERT INTO `sys_dept` VALUES ('3', '1', '0,1', '广陵区', 2, '广陵区', '15888888888', 'gl@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-12 07:22:01', '', '');
INSERT INTO `sys_dept` VALUES ('4', '2', '0,1,2', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('5', '2', '0,1,2', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('6', '2', '0,1,2', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('7', '2', '0,1,2', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('8', '2', '0,1,2', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('9', '3', '0,1,3', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '');
INSERT INTO `sys_dept` VALUES ('c8d59991-24af-11eb-8b0f-0242ac120002', '1', '0,1', '江都区', 3, '江都区', '18812341234', 'jd@163.com', '0', '0', 'admin', '2020-11-12 07:24:57', '', NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('e047ffac-24af-11eb-8b0f-0242ac120002', '1', '0,1', '宝应县', 4, '宝应县', '16312341234', 'byx@163.com', '0', '0', 'admin', '2020-11-12 07:25:36', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1819 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 1, '爆炸品', '1', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:01:44', 'admin', '2020-09-11 08:01:49', '爆炸品');
INSERT INTO `sys_dict_data` VALUES (30, 2, '压缩气体和易燃气体', '2', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:02:09', '', NULL, '压缩气体和易燃气体');
INSERT INTO `sys_dict_data` VALUES (31, 3, '易燃液体', '3', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:02:20', '', NULL, '易燃液体');
INSERT INTO `sys_dict_data` VALUES (32, 4, '易燃固体、自燃物品和遇湿易燃物品', '4', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:02:34', '', NULL, '易燃固体、自燃物品和遇湿易燃物品');
INSERT INTO `sys_dict_data` VALUES (33, 5, '氧化剂和有机过氧化物', '5', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:02:45', '', NULL, '氧化剂和有机过氧化物');
INSERT INTO `sys_dict_data` VALUES (34, 6, '毒害品和感染性物品', '6', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:02:55', '', NULL, '毒害品和感染性物品');
INSERT INTO `sys_dict_data` VALUES (35, 7, '放射性物质', '7', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:03:11', '', NULL, '放射性物质');
INSERT INTO `sys_dict_data` VALUES (36, 8, '腐蚀品', '8', 'sys_HazardousChemicals', NULL, NULL, 'N', '0', 'admin', '2020-09-11 08:03:22', '', NULL, '腐蚀品');
INSERT INTO `sys_dict_data` VALUES (37, 1, '是', '1', 'base_yes_no', NULL, NULL, 'N', '0', '', '2020-11-18 10:52:51', '', '2020-11-18 10:53:09', NULL);
INSERT INTO `sys_dict_data` VALUES (38, 0, '否', '0', 'base_yes_no', NULL, NULL, 'N', '0', '', '2020-11-18 10:53:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (39, 0, '无隐患', '00', 'base_hidden_danger', NULL, NULL, 'N', '0', '', '2020-11-19 04:32:16', '', '2020-11-25 03:39:14', NULL);
INSERT INTO `sys_dict_data` VALUES (40, 1, '消防隐患', '02', 'base_hidden_danger', NULL, NULL, 'N', '0', '', '2020-11-19 04:32:33', '', '2020-11-25 03:40:54', NULL);
INSERT INTO `sys_dict_data` VALUES (41, 0, '自住', '0', 'base_use_property', NULL, NULL, 'N', '0', '', '2020-11-19 07:01:43', '', '2020-12-14 08:19:23', NULL);
INSERT INTO `sys_dict_data` VALUES (42, 1, '商用', '1', 'base_use_property', NULL, NULL, 'N', '0', '', '2020-11-19 07:02:03', '', '2020-11-19 07:02:26', NULL);
INSERT INTO `sys_dict_data` VALUES (43, 0, '两室一厅', '0', 'base_hall_room', NULL, NULL, 'N', '0', '', '2020-11-19 07:15:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (44, 1, '三室一厅', '1', 'base_hall_room', NULL, NULL, 'N', '0', '', '2020-11-19 07:16:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (45, 2, '一室一厅', '2', 'base_hall_room', NULL, NULL, 'N', '0', '', '2020-11-19 07:16:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (46, 0, '身份证', '111', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 08:43:53', '', '2020-11-20 08:55:01', 'IDCARD');
INSERT INTO `sys_dict_data` VALUES (47, 1, '中国人民解放军军官证', '114', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 08:57:37', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (48, 2, '中国人民武装警察部队警官证', '115', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 08:57:44', '', '2020-11-20 08:57:52', NULL);
INSERT INTO `sys_dict_data` VALUES (49, 3, '护照', '414', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 09:00:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (50, 4, '外国人居留证', '554', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 09:01:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (51, 5, '台湾居民来往大陆通行证', '511', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 09:04:22', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (52, 6, '港澳居民来往内地通行证', '516', 'base_idCard', NULL, NULL, 'N', '0', '', '2020-11-20 09:06:00', '', '2020-11-20 09:06:07', NULL);
INSERT INTO `sys_dict_data` VALUES (53, 0, '汉族', '01', 'base_nation', NULL, NULL, 'N', '0', '', '2020-11-20 09:26:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (54, 1, '蒙古族', '02', 'base_nation', NULL, NULL, 'N', '0', '', '2020-11-20 09:27:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (55, 2, '回族', '03', 'base_nation', NULL, NULL, 'N', '0', '', '2020-11-20 09:27:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (56, 3, '藏族', '04', 'base_nation', NULL, NULL, 'N', '0', '', '2020-11-20 09:27:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (57, 0, '中共党员', '01', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:32:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (58, 1, '中共预备党员', '02', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:32:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (59, 2, '共青团员', '03', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:32:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (60, 3, '民革会员', '04', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:32:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (61, 4, '民盟盟员', '05', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:32:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (62, 5, '民建会员', '06', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:33:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (63, 6, '民进会员', '07', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:33:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (64, 7, '农工党党员', '08', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:33:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (65, 8, '致公党党员', '09', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:33:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (66, 9, '九三学社社员', '10', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:34:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (67, 10, '台盟盟员', '11', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:34:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (68, 2, '无党派民主人士', '12', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:35:01', '', '2020-11-20 09:36:04', NULL);
INSERT INTO `sys_dict_data` VALUES (69, 2, '群众', '13', 'base_political', NULL, NULL, 'N', '0', '', '2020-11-20 09:35:30', '', '2020-11-20 09:35:57', NULL);
INSERT INTO `sys_dict_data` VALUES (70, 0, '大学本科', '10', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:40:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (71, 1, '研究生', '0', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:40:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (72, 2, '专科毕业', '20', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:40:59', '', '2020-11-20 09:42:55', NULL);
INSERT INTO `sys_dict_data` VALUES (73, 3, '中专毕业', '30', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:41:20', '', '2020-11-20 09:42:49', NULL);
INSERT INTO `sys_dict_data` VALUES (74, 4, '高中毕业', '50', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:41:35', '', '2020-11-20 09:42:42', NULL);
INSERT INTO `sys_dict_data` VALUES (75, 5, '初中毕业', '60', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:41:51', '', '2020-11-20 09:42:36', NULL);
INSERT INTO `sys_dict_data` VALUES (76, 6, '小学', '70', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:42:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (77, 7, '文盲或半文盲', '80', 'base_degree', NULL, NULL, 'N', '0', '', '2020-11-20 09:42:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (78, 0, '无', '00', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:46:39', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (79, 1, '佛教', '10', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:46:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (80, 2, '喇嘛教', '20', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:47:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (81, 3, '道教', '30', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:47:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (82, 4, '天主教', '40', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:47:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (83, 5, '基督教', '50', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:47:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (84, 6, '东正教', '60', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:47:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (85, 7, '伊斯兰教', '70', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:51:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (86, 8, '其他', '99', 'base_religion', NULL, NULL, 'N', '0', '', '2020-11-20 09:51:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (87, 0, '中国', '156', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (88, 1, '中国香港', '344', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (89, 2, '中国澳门', '446', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (90, 3, '中国台湾', '158', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (91, 4, '阿富汗', '4', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (93, 5, '阿尔巴尼亚', '8', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (94, 6, '阿尔及利亚', '12', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (95, 7, '美属萨摩亚', '16', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (96, 8, '安道尔', '20', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (97, 9, '安哥拉', '24', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (98, 10, '安圭拉', '660', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (99, 11, '南极洲', '10', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (100, 12, '安提瓜和巴布达', '28', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (101, 13, '阿根廷', '32', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (102, 14, '亚美尼亚', '51', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 15, '阿鲁巴', '533', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 16, '澳大利亚', '36', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 17, '奥地利', '40', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 18, '阿塞拜疆', '31', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 19, '巴哈马', '44', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (108, 20, '巴林', '48', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 21, '孟加拉国', '50', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 22, '巴巴多斯', '52', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 23, '白俄罗斯', '112', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 24, '比利时', '56', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (113, 25, '伯利兹', '84', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 26, '贝宁', '204', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 27, '百慕大', '60', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (116, 28, '不丹', '64', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (117, 29, '玻利维亚', '68', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (118, 30, '波黑', '70', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (119, 31, '博茨瓦纳', '72', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (120, 32, '布维岛', '74', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (121, 33, '巴西', '76', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (122, 34, '英属印度洋领地', '86', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (123, 35, '文莱', '96', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (124, 36, '保加利亚', '100', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (125, 37, '布基纳法索', '854', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (126, 38, '布隆迪', '108', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (127, 39, '柬埔寨', '116', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (128, 40, '喀麦隆', '120', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (129, 41, '加拿大', '124', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (130, 42, '佛得角', '132', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (131, 43, '开曼群岛', '136', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (132, 44, '中非', '140', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (133, 45, '乍得', '148', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (134, 46, '智利', '152', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (135, 47, '圣诞岛', '162', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (136, 48, '科科斯（基林）群岛', '166', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (137, 49, '哥伦比亚', '170', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (138, 50, '科摩罗', '174', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (139, 51, '刚果（布）', '178', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (140, 52, '刚果（金）', '180', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (141, 53, '库克群岛', '184', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (142, 54, '哥斯达黎加', '188', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (143, 55, '科特迪瓦', '384', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (144, 56, '克罗地亚', '191', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (145, 57, '古巴', '192', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (146, 58, '塞浦路斯', '196', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (147, 59, '捷克', '203', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (148, 60, '丹麦', '208', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (149, 61, '吉布提', '262', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (150, 62, '多米尼克', '212', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (151, 63, '多米尼加', '214', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (152, 64, '东帝汶', '626', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (153, 65, '厄瓜多尔', '218', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (154, 66, '埃及', '818', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (155, 67, '萨尔瓦多', '222', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (156, 68, '赤道几内亚', '226', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (157, 69, '厄立特里亚', '232', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (158, 70, '爱沙尼亚', '233', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (159, 71, '埃塞俄比亚', '231', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (160, 72, '福克兰群岛（马尔维纳斯）', '238', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (161, 73, '法罗群岛', '234', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (162, 74, '斐济', '242', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (163, 75, '芬兰', '246', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (164, 76, '法国', '250', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (165, 77, '法属圭亚那', '254', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (166, 78, '法属波利尼西亚', '258', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (167, 79, '法属南部领地', '260', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (168, 80, '加蓬', '266', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (169, 81, '冈比亚', '270', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (170, 82, '格鲁吉亚', '268', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (171, 83, '德国', '276', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (172, 84, '加纳', '288', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (173, 85, '直布罗陀', '292', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (174, 86, '希腊', '300', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (175, 87, '格陵兰', '304', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (176, 88, '格林纳达', '308', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (177, 89, '瓜德罗普', '312', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (178, 90, '关岛', '316', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (179, 91, '危地马拉', '320', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (180, 92, '几内亚', '324', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (181, 93, '几内亚比绍', '624', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (182, 94, '圭亚那', '328', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (183, 95, '海地', '332', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (184, 96, '赫德岛和麦克唐纳岛', '334', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (185, 97, '洪都拉斯', '340', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (186, 98, '匈牙利', '348', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (187, 99, '冰岛', '352', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (188, 100, '印度', '356', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (189, 101, '印度尼西亚', '360', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (190, 102, '伊朗', '364', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (191, 103, '伊拉克', '368', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (192, 104, '爱尔兰', '372', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (193, 105, '以色列', '376', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (194, 106, '意大利', '380', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (195, 107, '牙买加', '388', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (196, 108, '日本', '392', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (197, 109, '约旦', '400', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (198, 110, '哈萨克斯坦', '398', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (199, 111, '肯尼亚', '404', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (200, 112, '基里巴斯', '296', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (201, 113, '朝鲜', '408', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (202, 114, '韩国', '410', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (203, 115, '科威特', '414', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (204, 116, '吉尔吉斯斯坦', '417', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (205, 117, '老挝', '418', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (206, 118, '拉脱维亚', '428', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (207, 119, '黎巴嫩', '422', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (208, 120, '莱索托', '426', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (209, 121, '利比里亚', '430', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (210, 122, '利比亚', '434', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (211, 123, '列支敦士登', '438', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (212, 124, '立陶宛', '440', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (213, 125, '卢森堡', '442', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (214, 126, '前南巴其顿', '807', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (215, 127, '马达加斯加', '450', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (216, 128, '马拉维', '454', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (217, 129, '马来西亚', '458', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (218, 130, '马尔代夫', '462', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (219, 131, '马里', '466', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (220, 132, '马耳他', '470', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (221, 133, '马绍尔群岛', '584', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (222, 134, '马提尼克', '474', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (223, 135, '毛里塔尼亚', '478', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (224, 136, '毛里求斯', '480', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (225, 137, '马约特', '175', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (226, 138, '墨西哥', '484', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (227, 139, '密克罗尼西亚联邦', '583', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (228, 140, '摩尔多瓦', '498', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (229, 141, '摩纳哥', '492', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (230, 142, '蒙古', '496', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (231, 143, '蒙特塞拉特', '500', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (232, 144, '摩洛哥', '504', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (233, 145, '莫桑比克', '508', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (234, 146, '缅甸', '104', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (235, 147, '纳米比亚', '516', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (236, 148, '瑙鲁', '520', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (237, 149, '尼泊尔', '524', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (238, 150, '荷兰', '528', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (239, 151, '荷属安的列斯', '530', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (240, 152, '新喀里多尼亚', '540', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (241, 153, '新西兰', '554', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (242, 154, '尼加拉瓜', '558', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (243, 155, '尼日尔', '562', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (244, 156, '尼日利亚', '566', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (245, 157, '纽埃', '570', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (246, 158, '诺福克岛', '574', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (247, 159, '北马里亚纳', '580', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (248, 160, '挪威', '578', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (249, 161, '阿曼', '512', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (250, 162, '巴基斯坦', '586', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (251, 163, '帕劳', '585', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (252, 164, '巴勒斯坦', '275', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (253, 165, '巴拿马', '591', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (254, 166, '巴布亚新几内亚', '598', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (255, 167, '巴拉圭', '600', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (256, 168, '秘鲁', '604', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (257, 169, '菲律宾', '608', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (258, 170, '皮特凯恩', '612', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (259, 171, '波兰', '616', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (260, 172, '葡萄牙', '620', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (261, 173, '波多黎各', '630', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (262, 174, '卡塔尔', '634', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (263, 175, '留尼汪', '638', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (264, 176, '罗马尼亚', '642', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (265, 177, '俄罗斯联邦', '643', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (266, 178, '卢旺达', '646', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (267, 179, '圣赫勒拿', '654', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (268, 180, '圣基茨和尼维斯', '659', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (269, 181, '圣卢西亚', '662', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (270, 182, '圣皮埃尔和密克隆', '666', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (271, 183, '圣文森特和格林纳丁斯', '670', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (272, 184, '萨摩亚', '882', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (273, 185, '圣马力诺', '674', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (274, 186, '圣多美和普林西比', '678', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (275, 187, '沙特阿拉伯', '682', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (276, 188, '塞内加尔', '686', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (277, 189, '塞舌尔', '690', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (278, 190, '塞拉利昂', '694', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (279, 191, '新加坡', '702', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (280, 192, '斯洛伐克', '703', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (281, 193, '斯洛文尼亚', '705', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (282, 194, '所罗门群岛', '90', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (283, 195, '索马里', '706', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (284, 196, '南非', '710', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (285, 197, '南乔治亚岛和南桑德韦奇岛', '239', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (286, 198, '西班牙', '724', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (287, 199, '斯里兰卡', '144', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (288, 200, '苏丹', '736', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (289, 201, '苏里南', '740', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (290, 202, '斯瓦尔巴岛和扬马延岛', '744', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (291, 203, '斯威士兰', '748', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (292, 204, '瑞典', '752', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (293, 205, '瑞士', '756', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (294, 206, '叙利亚', '760', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (295, 207, '塔吉克斯坦', '762', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (296, 208, '坦桑尼亚', '834', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (297, 209, '泰国', '764', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (298, 210, '多哥', '768', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (299, 211, '托克劳', '772', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (300, 212, '汤加', '776', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (301, 213, '特立尼克和多巴哥', '780', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (302, 214, '突尼斯', '788', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (303, 215, '土耳其', '792', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (304, 216, '土库曼斯坦', '795', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (305, 217, '特克斯和凯科斯群岛', '796', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (306, 218, '图瓦卢', '798', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (307, 219, '乌干达', '800', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (308, 220, '乌克兰', '804', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (309, 221, '阿联酋', '784', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (310, 222, '英国', '826', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (311, 223, '美国', '840', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (312, 224, '美国本土外小岛屿', '581', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (313, 225, '乌拉圭', '858', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (314, 226, '乌兹别克斯坦', '860', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (315, 227, '瓦努阿图', '548', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (316, 228, '梵蒂冈', '336', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (317, 229, '委内瑞拉', '862', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (318, 230, '越南', '704', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (319, 231, '英属维尔京群岛', '92', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (320, 232, '美属维尔京群岛', '850', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (321, 233, '瓦利斯和富图纳', '876', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (322, 234, '西撒哈拉', '732', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (323, 235, '也门', '887', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (324, 236, '南斯拉夫', '891', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (325, 237, '赞比亚', '894', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (326, 238, '津巴布韦', '716', 'base_nationality', NULL, NULL, 'N', '0', '', '2020-11-20 10:00:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (327, 0, '轿车', '00', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:29:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (328, 1, 'SUV', '01', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:30:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (329, 2, '厢式车', '02', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:30:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (330, 3, '多用途汽车', '03', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:30:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (331, 4, '跑车', '04', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:30:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (332, 5, '小型货车', '05', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:30:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (333, 6, '中型货车', '06', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:31:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (334, 7, '大型货车', '07', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:31:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (335, 8, '小型巴士', '08', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:32:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (336, 9, '大型巴士', '09', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:32:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (337, 10, '公交车', '10', 'base_vehicle_model', NULL, NULL, 'N', '0', '', '2020-11-20 10:32:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (338, 0, '不关注', '00', 'base_attention_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:43:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (339, 1, '高', '01', 'base_attention_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:43:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (340, 2, '中', '02', 'base_attention_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:43:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (341, 3, '低', '03', 'base_attention_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:43:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (342, 0, '社会团体', '01', 'base_social_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:45:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (343, 1, '民办非企业单位', '02', 'base_social_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:45:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (344, 2, '基金会', '03', 'base_social_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:45:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (345, 4, '其他', '99', 'base_social_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:45:41', '', '2020-11-27 07:29:26', NULL);
INSERT INTO `sys_dict_data` VALUES (346, 0, '国有控股', '110', 'base_holding_situation', NULL, NULL, 'N', '0', '', '2020-11-25 03:48:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (347, 1, '集体控股', '120', 'base_holding_situation', NULL, NULL, 'N', '0', '', '2020-11-25 03:48:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (348, 0, '综治委', '01', 'base_comprehensive_management_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:49:06', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (349, 2, '私人控股', '210', 'base_holding_situation', NULL, NULL, 'N', '0', '', '2020-11-25 03:49:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (350, 1, '综治办', '02', 'base_comprehensive_management_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:49:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (351, 2, '综治中心', '03', 'base_comprehensive_management_organization', NULL, NULL, 'N', '0', '', '2020-11-25 03:49:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (352, 3, '港澳台控股', '220', 'base_holding_situation', NULL, NULL, 'N', '0', '', '2020-11-25 03:49:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (353, 4, '外商控股', '230', 'base_holding_situation', NULL, NULL, 'N', '0', '', '2020-11-25 03:50:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (354, 0, '宿舍', '01', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:55:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (355, 1, '住房', '02', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:55:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (356, 2, '商铺', '03', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:55:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (357, 3, '办公', '04', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:55:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (358, 4, '仓库', '05', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:56:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (359, 5, '厂房', '06', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:56:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (360, 0, '中央', '01', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:56:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (361, 6, '其他', '99', 'base_rental_use', NULL, NULL, 'N', '0', '', '2020-11-25 03:56:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (362, 1, '省、自治区、直辖市', '02', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:56:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (363, 2, '地、市、州、盟', '03', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:57:37', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (364, 3, '县、市、区、旗', '04', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:58:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (365, 4, '乡镇、街道', '05', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:58:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (366, 0, '商务', '01', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:58:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (367, 1, '就业', '02', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (368, 5, '村、社区', '06', 'base_comprehensive_management_organization_level', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (369, 2, '学习', '03', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (370, 3, '定居', '04', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (371, 4, '探亲', '05', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (372, 5, '其他', '99', 'base_purpose_of_come_to_china', NULL, NULL, 'N', '0', '', '2020-11-25 03:59:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (373, 0, '自购房屋', '01', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:01:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (374, 1, '租赁房屋', '02', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:01:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (375, 2, '寄居', '03', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:01:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (376, 3, '借住', '04', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:02:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (377, 4, '单位宿舍', '05', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:02:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (378, 5, '临时性宿舍', '06', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:02:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (379, 6, '农民工公寓', '07', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:02:42', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (380, 7, '其他', '99', 'base_type_of_residence', NULL, NULL, 'N', '0', '', '2020-11-25 04:02:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (381, 0, '居住证', '01', 'base_application_certificate', NULL, NULL, 'N', '0', '', '2020-11-25 04:04:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (382, 1, '暂住证', '02', 'base_application_certificate', NULL, NULL, 'N', '0', '', '2020-11-25 04:04:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (383, 2, '其他', '99', 'base_application_certificate', NULL, NULL, 'N', '0', '', '2020-11-25 04:04:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (384, 0, '务工', '01', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:09:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (385, 1, '务农', '02', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:09:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (386, 2, '经商', '03', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:09:44', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (387, 3, '学习培训', '04', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:09:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (388, 4, '婚嫁', '05', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:10:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (389, 5, '随迁', '06', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:10:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (390, 6, '治病疗养', '07', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:10:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (391, 7, '投亲靠友', '08', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:10:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (392, 8, '工作调动', '09', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:11:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (393, 9, '其他', '99', 'base_reason_for_inflow', NULL, NULL, 'N', '0', '', '2020-11-25 04:11:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (394, 0, '内资企业', '01', 'base_enterprise_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:27:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (395, 1, '外资企业', '02', 'base_enterprise_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:27:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (396, 2, '个体工商户', '03', 'base_enterprise_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:27:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (397, 3, '农民专业合作社', '04', 'base_enterprise_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:28:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (398, 4, '其他企业', '99', 'base_enterprise_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:28:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (399, 0, '独居老人', '01', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:30:53', '', '2020-12-27 08:46:20', NULL);
INSERT INTO `sys_dict_data` VALUES (400, 1, '残疾人员', '02', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:32:00', '', '2020-12-27 08:46:29', NULL);
INSERT INTO `sys_dict_data` VALUES (401, 2, '重点治疗人员', '03', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:32:13', '', '2020-12-27 08:47:00', NULL);
INSERT INTO `sys_dict_data` VALUES (402, 0, '无', '00', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:33:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (403, 1, '治安隐患', '01', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:33:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (404, 2, '消防隐患', '02', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:33:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (405, 3, '矛盾纠纷隐患', '03', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:34:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (406, 4, '劳资纠纷隐患', '04', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:34:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (407, 5, '其他', '99', 'base_safety_hazard_type', NULL, NULL, 'N', '0', '', '2020-11-25 04:34:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (415, 3, '安置帮教人员', '04', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:37:02', '', '2020-12-27 08:47:26', NULL);
INSERT INTO `sys_dict_data` VALUES (416, 4, '知名人士', '05', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:37:19', '', '2020-12-27 08:47:46', NULL);
INSERT INTO `sys_dict_data` VALUES (417, 99, '其他关爱人员', '99', 'base_type_of_caring', NULL, NULL, 'N', '0', '', '2020-11-25 04:37:42', '', '2020-12-27 08:48:19', NULL);
INSERT INTO `sys_dict_data` VALUES (421, 0, '专职巡防队伍', '01', 'base_mass_prevention_control_organization', NULL, NULL, 'N', '0', '', '2020-11-25 07:19:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (422, 1, '义务巡防队伍', '02', 'base_mass_prevention_control_organization', NULL, NULL, 'N', '0', '', '2020-11-25 07:19:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (423, 2, '保安服务公司', '03', 'base_mass_prevention_control_organization', NULL, NULL, 'N', '0', '', '2020-11-25 07:19:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (424, 3, '志愿者队伍', '04', 'base_mass_prevention_control_organization', NULL, NULL, 'N', '0', '', '2020-11-25 07:20:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (425, 4, '其他', '99', 'base_mass_prevention_control_organization', NULL, NULL, 'N', '0', '', '2020-11-25 07:20:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (426, 0, '创建', '0', 'base_vote_stat', NULL, NULL, 'N', '0', '', '2020-11-25 07:22:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (427, 1, '正在投票', '1', 'base_vote_stat', NULL, NULL, 'N', '0', '', '2020-11-25 07:22:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (428, 2, '投票截止', '2', 'base_vote_stat', NULL, NULL, 'N', '0', '', '2020-11-25 07:22:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (429, 3, '投票暂停', '3', 'base_vote_stat', NULL, NULL, 'N', '0', '', '2020-11-25 07:23:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (430, 0, '社区规范', '01', 'base_vote_type', NULL, NULL, 'N', '0', '', '2020-11-25 07:56:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (431, 1, '社区活动', '02', 'base_vote_type', NULL, NULL, 'N', '0', '', '2020-11-25 07:57:11', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (432, 2, '社区事件', '03', 'base_vote_type', NULL, NULL, 'N', '0', '', '2020-11-25 07:57:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (433, 7, '刑满释放人员', '01', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-11-25 11:05:27', '', '2020-12-04 06:49:28', NULL);
INSERT INTO `sys_dict_data` VALUES (434, 8, '社区矫正人员', '02', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-11-25 11:06:01', '', '2020-12-04 06:49:46', NULL);
INSERT INTO `sys_dict_data` VALUES (435, 9, '肇事肇祸等严重精神障碍患者', '03', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-11-25 11:06:20', '', '2020-12-04 06:51:04', NULL);
INSERT INTO `sys_dict_data` VALUES (436, 10, '吸毒人员', '04', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-11-25 11:06:36', '', '2020-12-04 06:51:26', NULL);
INSERT INTO `sys_dict_data` VALUES (437, 11, '艾滋病危险人员', '05', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-11-25 11:06:50', '', '2020-12-04 06:51:54', NULL);
INSERT INTO `sys_dict_data` VALUES (438, 1, '公安', '01', 'base_special_person_data_source', NULL, NULL, 'N', '0', '', '2020-11-25 11:45:14', '', '2020-11-25 11:45:41', NULL);
INSERT INTO `sys_dict_data` VALUES (439, 0, '走访', '00', 'base_special_person_data_source', NULL, NULL, 'N', '0', '', '2020-11-25 11:45:35', '', '2020-11-25 11:45:48', NULL);
INSERT INTO `sys_dict_data` VALUES (440, 2, '综治', '02', 'base_special_person_data_source', NULL, NULL, 'N', '0', '', '2020-11-25 11:46:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (441, 1, '商用', '1', 'base_community_child_type', NULL, NULL, 'N', '0', '', '2020-11-26 02:35:34', '', '2020-11-26 02:35:51', NULL);
INSERT INTO `sys_dict_data` VALUES (442, 0, '住宅', '0', 'base_community_child_type', NULL, NULL, 'N', '0', '', '2020-11-26 02:35:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (443, 2, '写字楼', '2', 'base_community_child_type', NULL, NULL, 'N', '0', '', '2020-11-26 02:36:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (444, 3, '其他', '99', 'base_community_child_type', NULL, NULL, 'N', '0', '', '2020-11-26 02:36:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (445, 0, '正常', '1', 'base_social_organization_status', NULL, NULL, 'N', '0', '', '2020-11-27 06:50:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (446, 1, '注销', '2', 'base_social_organization_status', NULL, NULL, 'N', '0', '', '2020-11-27 06:50:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (447, 2, '撤销', '3', 'base_social_organization_status', NULL, NULL, 'N', '0', '', '2020-11-27 06:50:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (448, 1, '已审核', '1', 'base_checked_stat', NULL, NULL, 'N', '0', '', '2020-11-27 06:58:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (449, 0, '待审核', '0', 'base_checked_stat', NULL, NULL, 'N', '0', '', '2020-11-27 06:59:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (450, 3, '外国商会', '04', 'base_social_organization', NULL, NULL, 'N', '0', '', '2020-11-27 07:29:08', '', '2020-11-27 07:29:32', NULL);
INSERT INTO `sys_dict_data` VALUES (451, 0, '文化与休闲', '1', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:02:38', '', '2020-11-27 09:27:29', NULL);
INSERT INTO `sys_dict_data` VALUES (452, 1, '教育与科学研究', '2', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:03:01', '', '2020-11-27 09:27:47', NULL);
INSERT INTO `sys_dict_data` VALUES (453, 2, '卫生', '3', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:04:27', '', '2020-11-27 09:28:03', NULL);
INSERT INTO `sys_dict_data` VALUES (454, 3, '社会服务', '4', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:28:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (455, 4, '环境', '5', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:28:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (456, 5, '发展与住房', '6', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:28:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (457, 6, '法律、推进与政治', '7', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:29:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (458, 7, '慈善中介与志愿行为鼓动', '8', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:29:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (459, 8, '国际性活动', '9', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:29:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (460, 9, '宗教活动和组织', '10', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:29:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (461, 10, '商会、专业协会、工会', '11', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:29:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (462, 11, '其他', '99', 'base_overseas_NGOs_type', NULL, NULL, 'N', '0', '', '2020-11-27 09:30:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (463, 0, '夫妻', '01', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:03', '', '2020-12-01 07:10:17', NULL);
INSERT INTO `sys_dict_data` VALUES (464, 1, '父子', '02', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:11', '', '2020-12-01 07:10:23', NULL);
INSERT INTO `sys_dict_data` VALUES (465, 2, '父女', '03', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:19', '', '2020-12-01 07:10:28', NULL);
INSERT INTO `sys_dict_data` VALUES (466, 3, '母子', '04', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:30', '', '2020-12-01 07:10:33', NULL);
INSERT INTO `sys_dict_data` VALUES (467, 4, '母女', '05', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:37', '', '2020-12-01 07:10:39', NULL);
INSERT INTO `sys_dict_data` VALUES (468, 5, '祖孙', '06', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:46', '', '2020-12-01 07:10:44', NULL);
INSERT INTO `sys_dict_data` VALUES (469, 6, '亲戚', '07', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:58:55', '', '2020-12-01 07:10:50', NULL);
INSERT INTO `sys_dict_data` VALUES (470, 7, '朋友', '08', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:59:02', '', '2020-12-01 07:10:55', NULL);
INSERT INTO `sys_dict_data` VALUES (471, 8, '租户', '09', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 08:59:10', '', '2020-12-01 07:10:59', NULL);
INSERT INTO `sys_dict_data` VALUES (472, 9, '其他', '99', 'base_relationship_resident_homeowner', NULL, NULL, 'N', '0', '', '2020-11-30 09:00:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (473, 0, '未知', '0', 'base_vehicle_use', NULL, NULL, 'N', '0', '', '2020-12-02 03:41:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (474, 1, '自有', '1', 'base_vehicle_use', NULL, NULL, 'N', '0', '', '2020-12-02 03:41:13', '', '2020-12-02 03:41:45', NULL);
INSERT INTO `sys_dict_data` VALUES (475, 2, '租赁', '2', 'base_vehicle_use', NULL, NULL, 'N', '0', '', '2020-12-02 03:41:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (476, 3, '借用', '3', 'base_vehicle_use', NULL, NULL, 'N', '0', '', '2020-12-02 03:41:35', '', '2020-12-02 03:42:22', NULL);
INSERT INTO `sys_dict_data` VALUES (477, 1, '异性性传播', '01', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:27:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (478, 2, '同性性传播', '02', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:27:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (479, 3, '注射毒品传播', '03', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:28:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (480, 4, '母婴传播', '04', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:28:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (481, 5, '输血及使用血制品传播', '05', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:28:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (482, 6, '性接触加注射毒品传播', '06', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:29:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (483, 7, '既往采血浆传播', '07', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:29:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (484, 99, '不祥', '99', 'base_path_of_infection', NULL, NULL, 'N', '0', '', '2020-12-03 09:29:50', '', '2020-12-03 09:29:56', NULL);
INSERT INTO `sys_dict_data` VALUES (485, 0, '无', '00', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:33:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (486, 1, '故意传播', '01', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:35:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (487, 2, '造谣传谣', '02', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:35:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (488, 3, '恐吓', '03', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:38:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (489, 4, '聚众上访', '04', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:40:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (490, 5, '非正常维权', '05', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:40:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (491, 6, '非正常上访', '06', 'base_attention_type', NULL, NULL, 'N', '0', '', '2020-12-03 09:41:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (492, 0, '无', '00', 'base_admission_status', NULL, NULL, 'N', '0', '', '2020-12-03 09:43:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (493, 1, '羁押', '01', 'base_admission_status', NULL, NULL, 'N', '0', '', '2020-12-03 09:44:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (494, 2, '抗病毒治疗', '02', 'base_admission_status', NULL, NULL, 'N', '0', '', '2020-12-03 09:44:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (495, 3, '戒毒药物维持治疗', '03', 'base_admission_status', NULL, NULL, 'N', '0', '', '2020-12-03 09:58:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (496, 99, '其他', '99', 'base_admission_status', NULL, NULL, 'N', '0', '', '2020-12-03 09:58:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (497, 1, '在控', '01', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:01:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (498, 2, '失控', '02', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:01:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (499, 3, '死亡', '03', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:01:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (500, 4, '出国（境）', '04', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:01:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (501, 5, '社会面戒断三年未复吸', '05', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:02:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (502, 6, '社会面戒断不满三年', '06', 'base_control_situation', NULL, NULL, 'N', '0', '', '2020-12-03 10:02:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (503, 0, '危害国家安全罪', '01', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:55:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (504, 1, '危害公共安全罪', '02', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:56:06', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (505, 2, '破坏社会主义市场经济秩序罪', '03', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:56:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (506, 3, '侵犯公民人身权利、民主权利罪', '04', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:57:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (507, 4, '侵犯财产罪', '05', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:57:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (508, 5, '妨害社会管理秩序罪', '06', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:58:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (509, 6, '危害国防利益罪', '07', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:58:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (510, 7, '贪污贿赂罪', '08', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:59:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (511, 8, '渎职罪', '09', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 02:59:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (512, 9, '军人违反职责罪', '10', 'base_criminal_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:01:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (513, 0, '重点帮教人员', '01', 'base_risk_assessment', NULL, NULL, 'N', '0', '', '2020-12-04 03:03:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (514, 1, '一般帮教人员', '02', 'base_risk_assessment', NULL, NULL, 'N', '0', '', '2020-12-04 03:03:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (515, 0, '基层政府接回', '01', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:11:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (516, 1, '监所送回原籍', '02', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:11:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (517, 2, '司法所接回', '03', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:12:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (518, 3, '公安机关落实管控措施', '04', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:12:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (519, 4, '基层组织接回', '05', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:12:39', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (520, 5, '人员自返', '06', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:12:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (521, 6, '其他衔接情况', '99', 'base_connection', NULL, NULL, 'N', '0', '', '2020-12-04 03:13:06', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (522, 1, '无固定经济来源', '01', 'base_family_financial_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (523, 0, '落实责任田', '01', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (524, 2, '有固定经济来源', '02', 'base_family_financial_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (525, 1, '公益性岗位安置', '02', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (526, 3, '低收入家庭', '03', 'base_family_financial_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (527, 4, '贫困', '04', 'base_family_financial_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:15:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (528, 2, '企业认证为安置基地安置', '03', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:16:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (529, 99, '其他', '99', 'base_family_financial_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:16:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (530, 3, '建设政府投入为主的安置基地安置', '04', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:16:27', '', '2020-12-04 03:17:55', NULL);
INSERT INTO `sys_dict_data` VALUES (531, 4, '从事个体经营', '05', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:16:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (532, 5, '企业和经济实体吸纳就业', '06', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:17:05', '', '2020-12-08 09:16:35', NULL);
INSERT INTO `sys_dict_data` VALUES (533, 6, '自主创业', '07', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:17:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (534, 7, '其他安置方式', '99', 'base_resettlement', NULL, NULL, 'N', '0', '', '2020-12-04 03:17:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (535, 1, '精神分裂症', '01', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:18:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (536, 2, '分裂情感性障碍', '02', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:18:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (537, 3, '持久的妄想性障碍（偏执性精神病）', '03', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:19:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (538, 4, '双相（情感）障碍', '04', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:19:51', '', '2020-12-04 03:20:04', NULL);
INSERT INTO `sys_dict_data` VALUES (539, 5, '癫痫所致精神障碍', '05', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:20:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (540, 0, '管制', '01', 'base_correction_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:20:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (541, 6, '精神发育迟滞伴发精神障碍', '06', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:20:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (542, 1, '缓刑', '02', 'base_correction_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:20:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (543, 7, '重度抑郁发作', '07', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (544, 2, '假释', '03', 'base_correction_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (545, 8, '精神活性物质所致精神障碍', '08', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (546, 3, '暂予监外执行', '04', 'base_correction_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (547, 99, '其他', '99', 'base_current_diagnosis_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (548, 4, '剥夺政治权利', '05', 'base_correction_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:21:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (550, 1, '0级', '01', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (551, 2, '1级', '02', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (552, 0, '自行报到', '01', 'base_receiving_mode', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (553, 3, '2级', '03', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (554, 4, '3级', '04', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (555, 5, '4级', '05', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:53', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (556, 1, '狱所押送', '02', 'base_receiving_mode', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (557, 6, '5级', '06', 'base_current_risk_assessment_level', NULL, NULL, 'N', '0', '', '2020-12-04 03:24:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (558, 2, '当庭交接', '03', 'base_receiving_mode', NULL, NULL, 'N', '0', '', '2020-12-04 03:25:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (559, 3, '其他', '99', 'base_receiving_mode', NULL, NULL, 'N', '0', '', '2020-12-04 03:25:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (560, 1, '住院治疗', '01', 'base_treatment_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:26:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (561, 2, '居家服用抗精神病药物治疗', '02', 'base_treatment_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:26:53', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (562, 3, '其他治疗', '03', 'base_treatment_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:27:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (563, 4, '未接受过治疗', '04', 'base_treatment_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:27:11', '', '2020-12-04 03:27:22', NULL);
INSERT INTO `sys_dict_data` VALUES (564, 0, '吸毒史', '01', 'base_four_histories', NULL, NULL, 'N', '0', '', '2020-12-04 03:28:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (565, 1, '逃脱史', '02', 'base_four_histories', NULL, NULL, 'N', '0', '', '2020-12-04 03:28:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (566, 2, '自杀史', '03', 'base_four_histories', NULL, NULL, 'N', '0', '', '2020-12-04 03:28:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (567, 3, '袭警史', '04', 'base_four_histories', NULL, NULL, 'N', '0', '', '2020-12-04 03:28:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (568, 1, '已发生危害他人安全的行为', '01', 'base_reasons_for_hospitalization', NULL, NULL, 'N', '0', '', '2020-12-04 03:29:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (569, 2, '存在危害他人安全的危险', '02', 'base_reasons_for_hospitalization', NULL, NULL, 'N', '0', '', '2020-12-04 03:29:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (570, 99, '其他', '99', 'base_reasons_for_hospitalization', NULL, NULL, 'N', '0', '', '2020-12-04 03:29:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (571, 0, '涉毒', '01', 'base_three_involvement', NULL, NULL, 'N', '0', '', '2020-12-04 03:30:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (572, 1, '涉黑', '02', 'base_three_involvement', NULL, NULL, 'N', '0', '', '2020-12-04 03:30:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (573, 2, '涉枪', '03', 'base_three_involvement', NULL, NULL, 'N', '0', '', '2020-12-04 03:30:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (574, 1, '基层医务人员', '01', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:32:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (575, 0, '司法所工作人员', '01', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:34:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (576, 1, '社会工作者', '02', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:34:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (577, 2, '志愿者', '03', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:34:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (578, 3, '村（居）委会人员', '04', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:35:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (579, 4, '所在单位人员', '05', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:35:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (580, 5, '就读学校人员', '06', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:35:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (581, 6, '家庭成员或监护人', '07', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:36:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (582, 7, '保证人', '08', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:36:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (583, 8, '其他', '99', 'base_correction_team_composition_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:36:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (584, 2, '片区民警', '02', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:37:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (585, 3, '民政干事', '03', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:37:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (586, 4, '助残员', '04', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:37:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (587, 5, '村（居）委会干部', '05', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:37:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (588, 99, '其他', '99', 'base_participating_managers', NULL, NULL, 'N', '0', '', '2020-12-04 03:37:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (589, 1, '民政', '01', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:07', '', '2020-12-04 03:39:13', NULL);
INSERT INTO `sys_dict_data` VALUES (590, 0, '期满释放', '01', 'base_correction_release_termination_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:16', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (591, 2, '卫生', '02', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (592, 3, '公安', '03', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (593, 1, '收监执行', '02', 'base_correction_release_termination_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (594, 4, '残联', '04', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (595, 2, '死亡', '03', 'base_correction_release_termination_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (596, 3, '居住地变更', '04', 'base_correction_release_termination_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:39:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (597, 5, '街道办事处或乡镇政府', '05', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:40:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (598, 4, '其他', '99', 'base_correction_release_termination_type', NULL, NULL, 'N', '0', '', '2020-12-04 03:40:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (599, 6, '非政府组织', '06', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:40:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (600, 99, '其他', '99', 'base_help_situation', NULL, NULL, 'N', '0', '', '2020-12-04 03:40:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (601, 0, '全部', '-1', 'base_special_person', NULL, NULL, 'N', '1', '', '2020-12-04 06:48:36', '', '2020-12-04 07:38:45', NULL);
INSERT INTO `sys_dict_data` VALUES (602, 12, '重点青少年', '06', 'base_special_person', NULL, NULL, 'N', '1', '', '2020-12-04 06:52:25', '', '2020-12-08 12:18:34', NULL);
INSERT INTO `sys_dict_data` VALUES (603, 0, '车辆黑名单布控', '1', 'base_monitoring_type', NULL, NULL, 'N', '0', '', '2020-12-04 08:32:03', '', '2020-12-04 08:41:56', NULL);
INSERT INTO `sys_dict_data` VALUES (604, 1, '人脸黑名单布控', '2', 'base_monitoring_type', NULL, NULL, 'N', '0', '', '2020-12-04 08:32:17', '', '2020-12-04 08:41:48', NULL);
INSERT INTO `sys_dict_data` VALUES (605, 2, '人脸白名单布控', '3', 'base_monitoring_type', NULL, NULL, 'N', '0', '', '2020-12-04 08:41:43', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (606, 1, '非特殊人员', '0', 'base_special_person', NULL, NULL, 'N', '0', '', '2020-12-04 09:10:40', '', '2020-12-04 09:11:02', NULL);
INSERT INTO `sys_dict_data` VALUES (607, 1, '亲友影响', '01', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (608, 2, '好奇', '02', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (609, 3, '人生受挫', '03', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (610, 4, '寻求刺激', '04', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:29', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (611, 5, '被诱骗', '05', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (612, 6, '被逼迫', '06', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:54:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (613, 7, '治病引起', '07', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:55:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (614, 8, '遗传', '08', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:55:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (615, 99, '其他', '99', 'base_reasons_for_taking_drugs', NULL, NULL, 'N', '0', '', '2020-12-04 10:55:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (616, 1, '凌晨出行', '001', 'base_community_event', NULL, NULL, 'N', '0', '', '2020-12-08 03:44:02', '', '2020-12-08 03:44:49', NULL);
INSERT INTO `sys_dict_data` VALUES (617, 2, '频繁出入', '002', 'base_community_event', NULL, NULL, 'N', '0', '', '2020-12-08 03:44:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (618, 3, '人群聚集', '003', 'base_community_event', NULL, NULL, 'N', '0', '', '2020-12-08 03:46:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (619, 1, '1级', '1', 'base_alarm_level', NULL, NULL, 'N', '0', '', '2020-12-08 03:48:36', '', '2020-12-20 05:56:21', NULL);
INSERT INTO `sys_dict_data` VALUES (620, 2, '2级', '2', 'base_alarm_level', NULL, NULL, 'N', '0', '', '2020-12-08 03:48:47', '', '2020-12-20 05:56:25', NULL);
INSERT INTO `sys_dict_data` VALUES (621, 3, '3级', '3', 'base_alarm_level', NULL, NULL, 'N', '0', '', '2020-12-08 03:48:51', '', '2020-12-20 05:56:28', NULL);
INSERT INTO `sys_dict_data` VALUES (622, 4, '4级', '4', 'base_alarm_level', NULL, NULL, 'N', '0', '', '2020-12-08 03:48:59', '', '2020-12-20 05:56:32', NULL);
INSERT INTO `sys_dict_data` VALUES (623, 5, '5级', '5', 'base_alarm_level', NULL, NULL, 'N', '0', '', '2020-12-08 03:49:04', '', '2020-12-20 05:56:36', NULL);
INSERT INTO `sys_dict_data` VALUES (1200, 1, '危害国家安全案', '10000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1201, 2, '背叛、分裂国家案', '10100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:01', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1202, 3, '背叛国家案', '10110', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:00', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1203, 4, '分裂国家案', '10120', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:01', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1204, 5, '煽动分裂国家案', '10130', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1205, 6, '武装叛乱、暴乱案', '10140', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:03', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1206, 7, '策动武装暴乱案', '10150', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:04', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1207, 8, '颠覆国家政权案', '10160', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:05', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1208, 9, '煽动颠覆国家政权案', '10170', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:06', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1209, 10, '资助危害国家安全犯罪活动案', '10180', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:07', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1210, 11, '叛变、叛逃案', '10200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:08', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1211, 12, '投敌叛变案', '10210', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:09', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1212, 13, '叛逃案', '10220', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:10', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1213, 14, '间谍、资敌案', '10300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:11', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1214, 15, '间谍案', '10310', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:12', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1215, 16, '资敌案', '10320', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:13', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1216, 17, '为境外窃取、刺探、收买、非法提供国家秘密情报案', '10400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:14', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1217, 18, '为境外窃取国家秘密情报案', '10410', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:15', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1218, 19, '为境外刺探国家秘密情报案', '10420', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:16', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1219, 20, '为境外收买国家秘密情报案', '10430', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:17', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1220, 21, '为境外非法提供国家情报案', '10440', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:18', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1221, 22, '其他危害国家安全案', '19900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:19', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1222, 23, '危害公共安全案', '20000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:20', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1223, 24, '以危险方法危害公共安全案', '20100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:21', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1224, 25, '放火案', '20101', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:22', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1225, 26, '决水案', '20102', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:23', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1226, 27, '爆炸案', '20103', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:24', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1227, 28, '投毒案', '20104', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:25', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1228, 29, '以其他危险方法危害公共安全案', '20109', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:26', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1229, 30, '失火案', '20111', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:27', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1230, 31, '过失决水案', '20112', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:28', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1231, 32, '过失爆炸案', '20113', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:29', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1232, 33, '过失投毒案', '20114', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:30', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1233, 34, '过失以其他危险方法危害公共安全案', '20119', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:31', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1234, 35, '危害交通运输、公用设备安全案', '20200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:32', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1235, 36, '破坏交通工具案', '20201', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:33', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1236, 37, '破坏交通设施案', '20202', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:34', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1237, 38, '破坏电力设备案', '20203', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:35', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1238, 39, '破坏燃气设备案', '20204', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:36', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1239, 40, '破坏易燃易爆设备案', '20205', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:37', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1240, 41, '破坏广播电视设施案', '20206', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:38', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1241, 42, '破坏公用电信设施案', '20207', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:39', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1242, 43, '过失损坏交通工具案', '20221', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:40', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1243, 44, '过失损坏交通设施案', '20222', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:41', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1244, 45, '过失损坏电力设备案', '20223', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:42', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1245, 46, '过失损坏燃气设备案', '20224', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:43', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1246, 47, '过失损坏易燃易爆设备案', '20225', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:44', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1247, 48, '过失损坏广播电视设施案', '20226', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:45', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1248, 49, '过失损坏公用电信设施案', '20227', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:46', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1249, 50, '实施恐怖、劫持案', '20300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:47', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1250, 51, '组织、领导参加恐怖组织案', '20301', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:48', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1251, 52, '劫持航空器案', '20311', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:49', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1252, 53, '劫持船只案', '20312', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:50', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1253, 54, '劫持汽车案', '20313', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:51', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1254, 55, '暴力危及飞行安全案', '20331', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:52', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1255, 56, '非法制造，买卖，运输，邮寄，储存枪支弹药、爆炸物案', '20400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:53', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1256, 57, '非法制造枪支弹药案', '20401', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:54', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1257, 58, '非法买卖枪支弹药案', '20402', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:55', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1258, 59, '非法运输枪支弹药案', '20403', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:56', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1259, 60, '非法邮寄枪支弹药案', '20404', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:57', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1268, 61, '非法储存枪支弹药案', '20405', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1269, 62, '非法制造爆炸物案', '20406', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1270, 63, '非法买卖爆炸物案', '20407', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1271, 64, '非法运输爆炸物案', '20408', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1272, 65, '非法邮寄爆炸物案', '20409', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1273, 66, '非法储存爆炸物案', '20410', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1274, 67, '非法买卖核材料案', '20411', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1275, 68, '非法运输核材料案', '20412', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1276, 69, '违法枪支弹药管理案', '20500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1277, 70, '企业违规制造枪支案', '20501', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1278, 71, '企业违规销售枪支案', '20502', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1279, 72, '非法持有枪支弹药案', '20511', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1280, 73, '非法私藏枪支弹药案', '20512', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1281, 74, '非法出借枪支案', '20521', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1282, 75, '非法出租枪支案', '20522', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1283, 76, '丢失枪支不报案', '20531', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1284, 77, '非法携带枪支弹药危及公共安全案', '20541', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1285, 78, '违反危险物品管理案', '20600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1286, 79, '非法携带管制刀具危及公共安全案', '20601', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1287, 80, '非法携带危险品危及公共安全案', '20602', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1288, 81, '重大责任事故案', '20700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1289, 82, '重大飞行事故案', '20701', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1290, 83, '铁路运营安全事故案', '20702', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1291, 84, '交通肇事案', '20703', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1292, 85, '重大劳动安全事故案', '20711', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1293, 86, '危险物品肇事案', '20712', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1294, 87, '工程重大安全事故案', '20713', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1295, 88, '教育设施重大安全事故案', '20714', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1296, 89, '消防责任事故案', '20715', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1297, 90, '抢劫枪支、弹药、爆炸物案', '20800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1298, 91, '抢劫枪支、弹药案', '20810', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1299, 92, '抢劫国家机关枪支弹药案', '20811', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1300, 93, '抢劫军、警、民兵枪支弹药案', '20812', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1301, 94, '抢劫爆炸物案', '20820', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1302, 95, '抢劫国家机关爆炸物案', '20821', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1303, 96, '抢劫军、警、民兵爆炸物案', '20822', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1304, 97, '盗窃、抢夺枪支、弹药、爆炸物案', '20900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1305, 98, '盗窃枪支、弹药、爆炸物案', '20910', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1306, 99, '盗窃枪支、弹药案', '20911', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1307, 100, '盗窃国家机关枪支、弹药案', '20912', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1308, 101, '盗窃军、警、民兵枪支、弹药案', '20913', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1309, 102, '盗窃爆炸物案', '20914', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1310, 103, '盗窃国家机关爆炸物案', '20915', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1311, 104, '盗窃军、警、民兵爆炸物案', '20916', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1312, 105, '抢夺枪支弹药、爆炸物案', '20920', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1313, 106, '抢夺枪支、弹药案', '20921', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1314, 107, '抢夺国家机关枪支弹药案', '20922', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1315, 108, '抢夺军、警、民兵枪支弹药案', '20923', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1316, 109, '抢夺爆炸物案', '20924', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1317, 110, '抢夺国家机关爆炸物案', '20925', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1318, 111, '抢夺军、警、民兵爆炸物案', '20926', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1319, 112, '其他危害公共安全案', '29900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1320, 113, '破坏社会主义市场经济秩序案', '30000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1321, 114, '生产、销售伪劣商品（产品）案', '30100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1322, 115, '生产、销售伪劣产品案', '30101', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1323, 116, '生产、销售假药案', '30102', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1324, 117, '生产、销售劣药案', '30103', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1325, 118, '生产、销售伪劣兽药案', '30104', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1326, 119, '生产、销售伪劣农药案', '30105', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1327, 120, '生产、销售伪劣化肥案', '30111', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1328, 121, '生产、销售伪劣种子案', '30121', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1329, 122, '生产、销售不符合卫生标准的食品案', '30131', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1330, 123, '生产、销售有毒、有害食品案', '30132', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1331, 124, '生产、销售不符合标准的医用器材案', '30141', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1332, 125, '生产、销售不符合安全标准的产品案', '30151', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1333, 126, '生产销售不符合卫生标准的化妆品案', '30161', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1334, 127, '走私案', '30200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1335, 128, '走私武器、弹药案', '30201', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1336, 129, '走私核材料案', '30210', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1337, 130, '走私文物案', '30220', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1338, 131, '走私假币案', '30230', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1339, 132, '走私贵重金属案', '30240', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1340, 133, '走私黄金案', '30241', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1341, 134, '走私白银案', '30242', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1342, 135, '走私珍贵动物及其制品案', '30250', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1343, 136, '走私珍稀植物及其制品案', '30260', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1344, 137, '走私淫秽物品案', '30270', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1345, 138, '走私一般货物物品案', '30280', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1346, 139, '走私固体废物案', '30290', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1347, 140, '妨害对公司、企业管理秩序案', '30300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1348, 141, '虚报注册资本案', '30301', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1349, 142, '虚假出资案', '30311', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1350, 143, '抽逃出资案', '30312', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1351, 144, '欺诈发行股票案', '30321', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1352, 145, '欺诈发行债券案', '30322', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1353, 146, '提供虚假财会报告案', '30331', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1354, 147, '妨害清算案', '30332', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1355, 148, '公司、企业人员受贿案', '30341', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1356, 149, '对公司、企业人员行贿案', '30342', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1357, 150, '非法经营同类营业案', '30351', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1358, 151, '为亲友非法牟利案', '30352', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1359, 152, '签订、履行合同失职被骗案', '30361', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1360, 153, '徇私舞弊造成破产案', '30371', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1361, 154, '徇私舞弊造成亏损案', '30372', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1362, 155, '徇私舞弊低价折股国有资产案', '30373', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1363, 156, '徇私舞弊低价出售国有资产案', '30374', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1364, 157, '破坏金融管理秩序案', '30400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1365, 158, '伪造货币案', '30401', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1366, 159, '出售假币案', '30411', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1367, 160, '购买假币案', '30415', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1368, 161, '运输假币案', '30420', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1369, 162, '金融工作人员购买假币以假币换取货币案', '30421', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1370, 163, '持有、使用假币案', '30440', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1371, 164, '变造货币案', '30441', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1372, 165, '伪造、变造国库券案', '30442', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1373, 166, '伪造、变造国家其他有价证券案', '30443', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1374, 167, '伪造、变造股票案', '30444', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1375, 168, '伪造、变造公司企业债券案', '30445', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1376, 169, '伪造、变造金融票证案', '30446', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1377, 170, '伪造、变造、转让金融机构经营许可证案', '30447', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1378, 171, '擅自设立金融机构案', '30451', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1379, 172, '转让金融机构许可证案', '30452', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1380, 173, '高利转贷案', '30453', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1381, 174, '非法吸收公众存款案', '30454', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1382, 175, '擅自发行股票、公司企业债券案', '30455', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1383, 176, '内幕交易、泄露内幕信息案', '30456', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1384, 177, '编造并传播证券交易虚假信息案', '30457', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1385, 178, '诱骗投资者买卖证券案', '30458', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1386, 179, '操纵证券交易价格案', '30459', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1387, 180, '保险公司人员虚假理赔案', '30460', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1388, 181, '金融机构人员受贿案', '30461', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1389, 182, '违法发放贷款案', '30462', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1390, 183, '违法向关系人发放贷款案', '30463', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1391, 184, '用帐外客户资金非法拆借、发放贷款案', '30464', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1392, 185, '非法出具金融票证案', '30466', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1393, 186, '对违法票据承兑付款、保证案', '30471', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1394, 187, '逃汇案', '30474', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1395, 188, '套汇案', '30475', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1396, 189, '骗汇案', '30476', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1397, 190, '洗钱案', '30477', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1398, 191, '非法买卖外汇案', '30478', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1399, 192, '金融诈骗案', '30500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1400, 193, '集资诈骗案', '30510', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1401, 194, '贷款诈骗案', '30520', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1402, 195, '票据诈骗案', '30530', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1403, 196, '金融凭证诈骗案', '30540', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1404, 197, '信用证诈骗案', '30550', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1405, 198, '信用卡诈骗案', '30560', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1406, 199, '有价证券诈骗案', '30570', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1407, 200, '保险诈骗案', '30580', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1408, 201, '危害税收征管案', '30600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1409, 202, '偷税案', '30601', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1410, 203, '抗税案', '30602', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1411, 204, '逃避追缴欠税案', '30603', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1412, 205, '骗取出口退税案', '30604', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1413, 206, '骗取国家出口免征税款案', '30605', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1414, 207, '虚开增值专用发票用于骗取出口退税、抵扣税款发票案', '30611', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1415, 208, '伪造增值税专用发票案', '30612', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1416, 209, '出售伪造的增值税专用发票案', '30613', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1417, 210, '非法出售增值税专用发票案', '30621', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1418, 211, '非法购买增值税专用发票案', '30622', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1419, 212, '购买伪造的增值税专用发票案', '30623', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1420, 213, '非法制造用于骗取出口退税、抵扣税款发票案', '30631', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1421, 214, '出售非法制造的用于骗取出口退税、抵扣税款发票案', '30632', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1422, 215, '非法制造发票案', '30633', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1423, 216, '出售非法制造的发票案', '30641', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1424, 217, '非法出售用于骗取出口退税、抵扣税款发票案', '30651', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1425, 218, '非法出售发票案', '30661', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1426, 219, '盗窃增值税专用发票案', '30662', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1427, 220, '盗窃退税、抵扣税款专用发票案', '30663', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1428, 221, '侵犯知识产权案', '30700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1429, 222, '假冒注册商标案', '30701', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1430, 223, '销售假冒注册商标的商品案', '30710', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1431, 224, '非法制造的注册商标标识案', '30720', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1432, 225, '非法出版物案', '30730', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1433, 226, '销售非法制造的注册商标标识案', '30740', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1434, 227, '假冒专利案', '30750', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1435, 228, '侵犯著作权案', '30760', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1436, 229, '销售侵权复制品案', '30770', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1437, 230, '侵犯商业秘密案', '30780', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1438, 231, '扰乱市场秩序案', '30800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1439, 232, '损害商业信誉案', '30801', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1440, 233, '损害商品声誉案', '30802', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1441, 234, '虚假广告案', '30803', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1442, 235, '串通投标案', '30804', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1443, 236, '合同诈骗案', '30805', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1444, 237, '非法经营案', '30806', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1445, 238, '强迫交易案', '30807', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1446, 239, '伪造车票案', '30811', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1447, 240, '伪造船票案', '30812', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1448, 241, '伪造邮票案', '30813', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1449, 242, '伪造其他有价票证案', '30819', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1450, 243, '倒卖伪造车票案', '30821', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1451, 244, '倒卖伪造船票案', '30822', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1452, 245, '倒卖伪造邮票案', '30823', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1453, 246, '倒卖伪造其他有价票证案', '30829', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1454, 247, '倒卖车票案', '30831', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1455, 248, '倒卖船票案', '30832', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1456, 249, '非法转让土地使用权案', '30841', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1457, 250, '非法倒卖土地使用权案', '30842', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1458, 251, '中介组织人员提供虚假证明文件案', '30851', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1459, 252, '中介组织人员出具证明文件重大失实案', '30852', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1460, 253, '逃避商检案', '30861', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1461, 254, '其他破坏社会主义市场经济秩序案', '39900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1462, 255, '侵犯公民人身权利、民主权利案', '40000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1463, 256, '侵犯人身权利案', '40100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1464, 257, '故意杀人案', '40101', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1465, 258, '过失致人死亡案', '40102', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1466, 259, '故意伤害案', '40103', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1467, 260, '过失致人重伤案', '40104', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1468, 261, '强奸案', '40105', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1469, 262, '奸淫幼女案', '40106', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1470, 263, '强制猥亵、侮辱妇女案', '40107', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1471, 264, '猥亵儿童案', '40108', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1472, 265, '非法拘禁案', '40109', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1473, 266, '绑架案', '40110', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1474, 267, '偷盗婴幼儿勒索案', '40111', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1475, 268, '拐卖妇女、儿童案', '40112', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1476, 269, '收买被拐卖的妇女、儿童案', '40113', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1477, 270, '聚众阻碍解救被收买的妇女、儿童案', '40114', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1478, 271, '暴力阻碍解救被收买的妇女、儿童案', '40115', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1479, 272, '强迫职工劳动案', '40116', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1480, 273, '非法搜查案', '40117', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1481, 274, '非法侵入住宅案', '40118', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1482, 275, '诬告陷害案', '40119', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1483, 276, '侮辱案', '40120', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1484, 277, '诽谤案', '40121', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1485, 278, '刑讯逼供案', '40122', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1486, 279, '暴力取证案', '40123', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1487, 280, '虐待被监管人员案', '40124', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1488, 281, '侵犯民主权利案', '40200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1489, 282, '侵犯通讯自由案', '40210', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1490, 283, '私拆邮件案', '40220', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1491, 284, '隐匿邮件电报案', '40230', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1492, 285, '毁弃邮件电报案', '40240', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1493, 286, '报复陷害案', '40250', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1494, 287, '打击报复会计人员案', '40260', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1495, 288, '打击报复统计人员案', '40270', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1496, 289, '破坏选举案', '40280', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1497, 290, '破坏民族平等、宗教信仰案', '40300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1498, 291, '煽动民族仇恨、民族歧视案', '40310', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1499, 292, '出版歧视、侮辱少数民族作品案', '40320', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1500, 293, '非法剥夺公民宗教信仰自由案', '40330', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1501, 294, '侵犯少数民族风俗习惯案', '40340', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1502, 295, '妨害婚姻家庭权利案', '40400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1503, 296, '暴力干涉婚姻自由案', '40410', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1504, 297, '重婚案', '40420', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1505, 298, '破坏军婚案', '40430', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1506, 299, '虐待案', '40440', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1507, 300, '遗弃案', '40450', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1508, 301, '拐骗儿童案', '40460', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1509, 302, '其他侵犯公民人身权利、民族权利案', '49900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1510, 303, '侵犯财产案', '50000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1511, 304, '抢劫案', '50100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1512, 305, '入户抢劫案', '50101', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1513, 306, '拦路抢劫案', '50102', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1514, 307, '在公共交通工具上抢劫案', '50103', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1515, 308, '抢劫银行或其他金融机构案', '50110', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1516, 309, '抢劫珠宝店案', '50111', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1517, 310, '抢劫提（送）款员案', '50112', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1518, 311, '抢劫运钞车案', '50113', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1519, 312, '抢劫出租汽车案', '50120', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1520, 313, '抢劫军用物资案', '50130', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1521, 314, '抢劫抢险、救灾、救济物资案', '50131', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1522, 315, '抢劫牲畜案', '50132', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1523, 316, '抢劫精药物和麻醉药品案', '50140', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1524, 317, '冒充军警持枪抢劫案', '50150', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1525, 318, '持枪抢劫案', '50160', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1526, 319, '盗窃案', '50200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1527, 320, '入室盗窃案', '50201', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1528, 321, '盗窃精神药物和麻醉药品案', '50202', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1529, 322, '盗窃易制毒化学品案', '50203', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1530, 323, '盗窃金融机构案', '50204', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1531, 324, '盗窃运输物资案', '50210', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1532, 325, '盗窃铁路器材案', '50211', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1533, 326, '盗窃珍贵文物案', '50212', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1534, 327, '盗窃电脑芯片案', '50216', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1535, 328, '盗窃货物案', '50220', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1536, 329, '盗窃旅财案', '50221', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1537, 330, '盗窃路财案', '50222', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1538, 331, '盗窃汽车案', '50223', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1539, 332, '盗窃摩托车案', '50224', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1540, 333, '盗窃自行车案', '50227', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1541, 334, '盗窃保险柜案', '50230', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1542, 335, '盗用他人通讯设施案', '50235', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1543, 336, '盗接通信线路案', '50236', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1544, 337, '盗窃牲畜案', '50237', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1545, 338, '扒窃案', '50240', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1546, 339, '诈骗案', '50300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1547, 340, '抢夺案', '50400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1548, 341, '侵占案', '50500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1549, 342, '职务侵占案', '50600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1550, 343, '挪用特定款物案', '50700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1551, 344, '挪用资金案', '50710', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1552, 345, '挪用公款案', '50720', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1553, 346, '挪用救灾、抢险、防汛款物案', '50730', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1554, 347, '挪用优抚款物案', '50740', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1555, 348, '挪用扶贫、移民救济款物案', '50750', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1556, 349, '敲诈勒索案', '50800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1557, 350, '故意毁坏财物案', '50900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1558, 351, '破坏生产经营案', '51000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1559, 352, '聚众哄抢案', '51100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1560, 353, '妨害社会管理秩序案', '60000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1561, 354, '扰乱公共秩序案', '60100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1562, 355, '阻碍执行职务案', '60101', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1563, 356, '阻碍人大代表执行职务案', '60102', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1564, 357, '阻碍红十字会依法履行职责案', '60103', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1565, 358, '阻碍安全机关、公安机关执行职务案', '60104', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1566, 359, '煽动暴力抗拒法律实施案', '60105', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1567, 360, '招摇撞骗案', '60106', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1568, 361, '冒充国家工作人员招摇撞骗案', '60107', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1569, 362, '冒充警察招摇撞骗案', '60108', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1570, 363, '伪造、变造公文、证件、印章案', '60109', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1571, 364, '买卖公文、证件、印章案', '60110', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1572, 365, '盗窃、抢夺公文、证件、印章案', '60111', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1573, 366, '毁灭公文、证件、印章案', '60112', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1574, 367, '伪造、变造居民身份证案', '60113', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1575, 368, '非法生产警服、警用标志、警械案', '60114', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1576, 369, '非法买卖警服、警用标志、警械案', '60115', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1577, 370, '非法获取国家秘密案', '60116', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1578, 371, '非法持有国家绝密、机密文件、资料、物品案', '60117', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1579, 372, '非法生产、销售间谍专用器材案', '60118', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1580, 373, '非法使用窃听器材案', '60119', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1581, 374, '非法使用窃照器材案', '60120', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1582, 375, '非法侵入计算机信息系统案', '60121', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1583, 376, '破坏计算机信息系统案', '60122', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1584, 377, '破坏计算机信息系统数据和应用程序案', '60123', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1585, 378, '故意制作传播计算机破坏性程序案', '60124', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1586, 379, '利用计算机金融诈骗案', '60125', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1587, 380, '利用计算机盗窃案', '60126', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1588, 381, '利用计算机贪污案', '60127', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1589, 382, '利用计算机挪用公款案', '60128', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1590, 383, '利用计算机窃取国家机密案', '60129', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1591, 384, '利用计算机实施其他犯罪案', '60130', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1592, 385, '扰乱无线电通讯管理秩序案', '60131', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1593, 386, '聚众扰乱社会秩序案', '60132', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1594, 387, '聚众冲击国家机关案', '60133', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1595, 388, '聚众扰乱公共场所秩序案', '60134', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1596, 389, '聚众扰乱交通秩序案', '60135', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1597, 390, '聚众斗殴案', '60136', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1598, 391, '寻衅滋事案', '60137', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1599, 392, '传授犯罪方法案', '60138', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1600, 393, '组织、领导黑社会性质组织案', '60139', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1601, 394, '参加黑社会性质组织案', '60140', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1602, 395, '入境发展黑社会组织案', '60141', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1603, 396, '包庇、纵容黑社会性质组织案', '60142', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1604, 397, '非法集会、游行、示威案', '60143', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1605, 398, '非法携带武器参加集会、游行、示威案', '60146', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1606, 399, '非法携带管制刀具参加集会、游行、示威案', '60150', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1607, 400, '非法携带爆炸物参加集会、游行、示威案', '60154', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1608, 401, '破坏集会、游行、示威案', '60158', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1609, 402, '侮辱国旗、国徽案', '60160', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1610, 403, '组织和利用会道门、邪教组织或者利用迷信破坏法律实施案', '60165', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1611, 404, '组织和利用会道门破坏法律实施案', '60166', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1612, 405, '组织和利用邪教组织破坏法律实施案', '60167', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1613, 406, '组织和利用迷信破坏法律实施案', '60168', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1614, 407, '组织和利用会道门、邪教组织或者利用迷信致人死亡案', '60170', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1615, 408, '组织和利用会道门致人死亡案', '60171', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1616, 409, '组织和利用邪教组织致人死亡案', '60172', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1617, 410, '组织和利用迷信致人死亡案', '60173', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1618, 411, '组织和利用会道门、邪教组织或者利用迷信奸淫妇女或诈骗钱财案', '60180', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1619, 412, '组织和利用会道门、邪教组织或者利用迷信奸淫妇女案', '60181', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1620, 413, '组织和利用会道门奸淫妇女案', '60182', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1621, 414, '组织和利用邪教组织奸淫妇女案', '60183', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1622, 415, '组织和利用迷信奸淫妇女案', '60184', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1623, 416, '组织和利用会道门、邪教组织或者利用迷信诈骗钱财案', '60185', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1624, 417, '组织和利用会道门诈骗钱财案', '60186', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1625, 418, '组织和利用邪教组织诈骗钱财案', '60187', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1626, 419, '组织和利用迷信诈骗钱财案', '60188', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1627, 420, '聚众淫乱案', '60190', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1628, 421, '引诱未成年人聚众淫乱案', '60193', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1629, 422, '盗窃、侮辱尸体案', '60196', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1630, 423, '赌博案', '60197', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1631, 424, '故意延误投递邮件案', '60198', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1632, 425, '妨害司法案', '60200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1633, 426, '伪证案', '60201', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1634, 427, '辩护人、诉讼代理人毁灭、伪造证据案', '60211', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1635, 428, '帮助毁灭、伪造证据案', '60212', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1636, 429, '司法人员毁灭、伪造证据案', '60213', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1637, 430, '妨害作证案', '60214', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1638, 431, '打击报复证人案', '60215', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1639, 432, '扰乱法庭秩序案', '60221', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1640, 433, '窝藏、包庇案', '60222', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1641, 434, '拒绝提供间谍犯罪证据案', '60223', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1642, 435, '窝藏、转移、代购、销售赃物案', '60224', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1643, 436, '拒不执行判决、裁定案', '60231', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1644, 437, '非法处置查封、扣押、冻结财产案', '60241', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1645, 438, '破坏监管秩序案', '60251', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1646, 439, '脱逃案', '60252', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1647, 440, '劫夺被押解人员案', '60253', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1648, 441, '组织越狱案', '60254', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1649, 442, '暴动越狱案', '60255', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1650, 443, '聚众持械劫狱案', '60256', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1651, 444, '妨害国（边）境管理案', '60300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1652, 445, '偷越国（边）境案', '60310', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1653, 446, '组织他人偷越国（边）境案', '60320', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1654, 447, '运送他人偷越国（边）境案', '60330', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1655, 448, '骗取出境证件案', '60340', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1656, 449, '提供伪造、变造的出入境证件案', '60350', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1657, 450, '出售出入境证件案', '60360', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1658, 451, '破坏界碑、界桩案', '60370', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1659, 452, '破坏永久性测量标志案', '60380', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1660, 453, '妨害文物管理案', '60400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1661, 454, '故意损毁文物案', '60401', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1662, 455, '过失损毁文物案', '60402', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1663, 456, '非法向外国人出售珍贵文物案', '60411', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1664, 457, '非法向外国人赠送珍贵文物案', '60412', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1665, 458, '倒卖文物案', '60421', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1666, 459, '非法出售文物藏品案', '60422', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1667, 460, '非法私赠文物藏品案', '60423', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1668, 461, '故意损毁名胜古迹案', '60431', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1669, 462, '盗掘古文化遗址案', '60441', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1670, 463, '盗掘古墓葬案', '60442', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1671, 464, '盗掘古人类化石、古脊椎动物化石案', '60443', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1672, 465, '抢夺国有档案案', '60451', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1673, 466, '窃取国有档案案', '60452', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1674, 467, '擅自出卖国家档案案', '60453', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1675, 468, '擅自转让国家档案案', '60454', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1676, 469, '危害公共卫生案', '60500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1677, 470, '妨害传染病防治案', '60501', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1678, 471, '传染病菌种、毒种扩散案', '60510', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1679, 472, '妨害国境卫生检疫案', '60520', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1680, 473, '非法组织卖血案', '60530', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1681, 474, '强迫卖血案', '60531', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1682, 475, '非法采集、供应血液案', '60532', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1683, 476, '非法制作、供应血液制品案', '60533', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1684, 477, '采集、供应血液事故案', '60534', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1685, 478, '制作、供应血液制品事故案', '60535', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1686, 479, '医疗事故案', '60540', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1687, 480, '非法行医案', '60550', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1688, 481, '非法进行节育手术案', '60560', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1689, 482, '逃避动植物检疫案', '60570', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1690, 483, '破坏环境资源保护案', '60600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1691, 484, '重大环境污染事故案', '60601', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1692, 485, '非法处置进口固体废物案', '60602', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1693, 486, '擅自进口固体废物案', '60603', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1694, 487, '非法猎捕、杀害珍贵、濒危野生动物案', '60611', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1695, 488, '非法收购珍贵、濒危野生动物及其制品案', '60612', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1696, 489, '非法运输珍贵、濒危野生动物及其制品案', '60613', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1697, 490, '非法出售珍贵、濒危野生动物及其制品案', '60614', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1698, 491, '非法采伐、毁坏珍贵树木案', '60621', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1699, 492, '盗伐林木案', '60622', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1700, 493, '滥伐林木案', '60623', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1701, 494, '非法收购盗伐、滥伐的林木案', '60624', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1702, 495, '非法捕捞水产品案', '60631', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1703, 496, '非法狩猎案', '60632', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1704, 497, '非法占用耕地案', '60633', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1705, 498, '非法采矿案', '60634', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1706, 499, '破坏性采矿案', '60635', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1707, 500, '走私、贩卖、运输、制造毒品案', '60700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1708, 501, '走私毒品案', '60701', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1709, 502, '贩卖毒品案', '60702', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1710, 503, '运输毒品案', '60703', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1711, 504, '制造毒品案', '60704', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1712, 505, '非法持有毒品案', '60710', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1713, 506, '包庇毒品犯罪分子案', '60720', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1714, 507, '窝藏、转移、隐瞒毒品、毒赃案', '60721', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1715, 508, '走私制毒物品案', '60722', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1716, 509, '非法买卖制毒物品案', '60730', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1717, 510, '非法种植毒品原植物案', '60731', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1718, 511, '非法买卖毒品原植物种苗案', '60732', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1719, 512, '非法运输毒品原植物种苗案', '60733', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1720, 513, '非法携带毒品原植物种苗案', '60734', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1721, 514, '非法持有毒品原植物种苗案', '60735', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1722, 515, '非法运输携带制毒物品进出境案', '60736', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1723, 516, '引诱、教唆、强迫他人吸毒案', '60740', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1724, 517, '引诱他人吸毒案', '60741', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1725, 518, '教唆他人吸毒案', '60742', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1726, 519, '欺骗他人吸毒案', '60743', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1727, 520, '强迫他人吸毒案', '60744', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1728, 521, '容留他人吸毒案', '60745', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1729, 522, '非法提供麻醉药品案', '60750', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1730, 523, '非法提供精神药品案', '60760', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1731, 524, '组织、强迫、引诱、容留、介绍卖淫案', '60800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1732, 525, '组织卖淫案', '60801', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1733, 526, '强迫卖淫案', '60810', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1734, 527, '协助组织卖淫案', '60820', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1735, 528, '引诱卖淫案', '60830', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1736, 529, '引诱幼女卖淫案', '60840', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1737, 530, '容留卖淫案', '60850', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1738, 531, '介绍卖淫案', '60860', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1739, 532, '传播性病案', '60870', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1740, 533, '嫖宿幼女案', '60880', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1741, 534, '制作、复制、出版、贩卖、传播淫秽物品牟利案', '60900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1742, 535, '制作淫秽物品案', '60910', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1743, 536, '复制淫秽物品案', '60920', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1744, 537, '出版淫秽物品案', '60930', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1745, 538, '贩卖淫秽物品案', '60940', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1746, 539, '传播淫秽物品案', '60950', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1747, 540, '提供书号出版淫秽书刊案', '60960', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1748, 541, '组织播放淫秽音像制品案', '60970', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1749, 542, '组织淫秽表演案', '60980', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1750, 543, '其他妨害社会管理秩序案', '69900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1751, 544, '危害国防利益案', '70000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1752, 545, '阻碍军人执行职务案', '70100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1753, 546, '阻碍军事行动案', '70200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1754, 547, '破坏武器装备案', '70300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1755, 548, '破坏军事设施案', '70400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1756, 549, '破坏军事通信案', '70500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1757, 550, '故意提供不合格武器装备案', '70600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1758, 551, '故意提供不合格军事设施案', '70700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1759, 552, '过失提供不合格武器装备案', '70800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1760, 553, '过失提供不合格军事设施案', '70900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1761, 554, '聚众冲击军事禁区案', '71000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1762, 555, '聚众扰乱军事管理区秩序案', '71100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1763, 556, '冒充军人招摇撞骗案', '71200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1764, 557, '煽动军人逃离部队案', '71300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1765, 558, '雇用逃离部队军人案', '71400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1766, 559, '接送不合格兵员案', '71500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1767, 560, '伪造、变造、买卖部队公文、证件、印章案', '71600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1768, 561, '盗窃部队公文、证件、印章案', '71700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1769, 562, '抢夺部队公文、证件、印章案', '71800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1770, 563, '非法生产、买卖军用标志案', '72000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1771, 564, '战时拒绝、逃避征召案', '72100', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1772, 565, '战时拒绝、逃避军事训练案', '72200', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1773, 566, '战时拒绝、逃避服役案', '72300', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1774, 567, '战时故意提供虚假敌情案', '72400', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1775, 568, '战时造谣惑众扰乱军心案', '72500', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1776, 569, '战时窝藏逃离部队军人案', '72600', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1777, 570, '战时拒绝军事订货案', '72700', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1778, 571, '战时延误军事订货案', '72800', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1779, 572, '战时拒绝军事征用案', '72900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1780, 573, '其他危害国防利益案', '79900', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1781, 574, '贪污贿赂案', '80000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1782, 575, '渎职案', '90000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1783, 576, '军人违反职责案', '100000', 'base_case_type', NULL, NULL, 'N', '0', 'admin', '2020-12-09 11:33:02', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (1784, 1, '长期未出现', '01', 'base_caring_event', NULL, NULL, 'N', '0', '', '2020-12-12 08:36:08', '', '2020-12-16 07:53:24', NULL);
INSERT INTO `sys_dict_data` VALUES (1785, 2, '出租', '2', 'base_use_property', NULL, NULL, 'N', '0', '', '2020-12-14 08:16:57', '', '2020-12-14 08:17:03', NULL);
INSERT INTO `sys_dict_data` VALUES (1786, 99, '其他', '99', 'base_use_property', NULL, NULL, 'N', '0', '', '2020-12-14 08:19:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1787, 1, '客运车', '001', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:49:35', '', '2020-12-16 03:49:51', NULL);
INSERT INTO `sys_dict_data` VALUES (1788, 2, '货运车', '002', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:50:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1789, 3, '校车', '003', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:50:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1790, 4, '快递车', '004', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:50:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1791, 5, '公交车', '005', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:50:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1792, 6, '垃圾车', '006', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:50:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1793, 1, '燃油车', '01', 'base_energy_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:52:09', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1794, 2, '新能源', '02', 'base_energy_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:52:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1795, 3, '混合动力', '03', 'base_energy_type', NULL, NULL, 'N', '0', '', '2020-12-16 03:52:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1796, 2, '生日提醒', '02', 'base_caring_event', NULL, NULL, 'N', '0', '', '2020-12-16 07:53:15', '', '2020-12-16 07:53:32', NULL);
INSERT INTO `sys_dict_data` VALUES (1797, 3, '频繁出现', '03', 'base_caring_event', NULL, NULL, 'N', '0', '', '2020-12-16 07:53:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1798, 999, '其他', '999', 'base_visitor_vehicle_type', NULL, NULL, 'N', '0', '', '2020-12-16 08:03:54', '', '2020-12-16 08:04:10', NULL);
INSERT INTO `sys_dict_data` VALUES (1799, 0, '车辆黑名单任务', '01', 'base_roster_type', NULL, NULL, 'N', '0', '', '2020-12-28 08:57:01', '', '2020-12-28 08:57:50', NULL);
INSERT INTO `sys_dict_data` VALUES (1800, 1, '人员黑名单任务', '02', 'base_roster_type', NULL, NULL, 'N', '0', '', '2020-12-28 08:57:17', '', '2020-12-28 08:57:44', NULL);
INSERT INTO `sys_dict_data` VALUES (1801, 2, '通行任务', '03', 'base_roster_type', NULL, NULL, 'N', '0', '', '2020-12-28 08:57:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1802, 3, '陌生人任务', '04', 'base_roster_type', NULL, NULL, 'N', '0', '', '2020-12-28 08:58:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1803, 0, '本人或户主', '0', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:43:39', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1804, 1, '配偶', '1', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:43:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1805, 2, '子', '2', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:43:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1806, 3, '女', '3', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:44:08', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1807, 4, '孙子、孙女、外孙子、外孙女', '4', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:44:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1808, 5, '父母', '5', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:44:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1809, 6, '祖父母、外祖父母', '6', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:44:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1810, 7, '兄、弟、姐、妹', '7', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:45:10', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1811, 8, '其他', '8', 'base_family_relation', NULL, NULL, 'N', '0', '', '2020-12-29 02:45:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1812, 1, '一致', '01', 'base_household_flag', NULL, NULL, 'N', '0', '', '2020-12-29 02:53:11', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1813, 2, '不一致', '02', 'base_household_flag', NULL, NULL, 'N', '0', '', '2020-12-29 02:53:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1814, 1, 'jpg', 'image/jpeg', 'base_image_info_lib_file_type', NULL, NULL, 'N', '0', '', '2020-12-31 05:27:06', '', '2020-12-31 07:29:18', NULL);
INSERT INTO `sys_dict_data` VALUES (1815, 2, 'png', 'png', 'base_image_info_lib_file_type', NULL, NULL, 'N', '0', '', '2020-12-31 05:27:13', '', '2020-12-31 07:29:24', NULL);
INSERT INTO `sys_dict_data` VALUES (1816, 3, 'AVI', 'avi', 'base_image_info_lib_file_type', NULL, NULL, 'N', '0', '', '2020-12-31 05:27:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (1817, 4, 'MP4', 'mp4', 'base_image_info_lib_file_type', NULL, NULL, 'N', '0', '', '2020-12-31 05:27:34', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2020-11-12 07:28:52', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '化工品类型', 'sys_HazardousChemicals', '0', 'admin', '2020-09-11 08:00:24', '', NULL, '化工品类型');
INSERT INTO `sys_dict_type` VALUES (12, '云台控制命令', 'video_PTZControl_Command', '0', '', '2020-11-18 10:06:58', '', '2020-11-18 10:11:00', '云台控制命令');
INSERT INTO `sys_dict_type` VALUES (15, '是否（字符）', 'base_yes_no', '0', '', '2020-11-18 10:52:21', '', NULL, '业务使用');
INSERT INTO `sys_dict_type` VALUES (16, '房屋隐患类型', 'base_hidden_danger', '0', '', '2020-11-19 04:31:44', '', '2020-11-25 08:51:20', NULL);
INSERT INTO `sys_dict_type` VALUES (17, '商品房使用性质', 'base_use_property', '0', '', '2020-11-19 07:01:16', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (18, '厅室', 'base_hall_room', '0', '', '2020-11-19 07:15:36', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (19, '身份证件类型', 'base_idCard', '0', '', '2020-11-20 08:43:04', '', '2020-11-20 09:26:37', NULL);
INSERT INTO `sys_dict_type` VALUES (20, '民族', 'base_nation', '0', '', '2020-11-20 09:26:24', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (21, '政治面貌', 'base_political', '0', '', '2020-11-20 09:31:56', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (22, '学历', 'base_degree', '0', '', '2020-11-20 09:39:55', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (23, '宗教信仰', 'base_religion', '0', '', '2020-11-20 09:46:15', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (24, '国籍', 'base_nationality', '0', '', '2020-11-20 10:00:24', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (25, '车辆类型', 'base_vehicle_model', '0', '', '2020-11-20 10:29:43', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (26, '关注程度', 'base_attention_level', '0', '', '2020-11-25 03:42:35', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (27, '社会组织类型', 'base_social_organization', '0', '', '2020-11-25 03:44:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (28, '综治机构类型', 'base_comprehensive_management_organization', '0', '', '2020-11-25 03:47:29', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (29, '控股情况', 'base_holding_situation', '0', '', '2020-11-25 03:48:18', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (30, '出租用途', 'base_rental_use', '0', '', '2020-11-25 03:55:09', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (31, '综治机构层级', 'base_comprehensive_management_organization_level', '0', '', '2020-11-25 03:55:39', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (32, '来华目的', 'base_purpose_of_come_to_china', '0', '', '2020-11-25 03:58:35', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (33, '住所类型', 'base_type_of_residence', '0', '', '2020-11-25 04:00:48', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (34, '办证类型', 'base_application_certificate', '0', '', '2020-11-25 04:04:08', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (35, '流入原因', 'base_reason_for_inflow', '0', '', '2020-11-25 04:08:45', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (36, '非公经济组织企业类型', 'base_enterprise_type', '0', '', '2020-11-25 04:27:01', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (37, '关爱类型', 'base_type_of_caring', '0', '', '2020-11-25 04:30:34', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (38, '安全隐患类型', 'base_safety_hazard_type', '0', '', '2020-11-25 04:32:40', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (39, '特殊人员类型', 'base_special_person', '0', '', '2020-11-25 04:34:13', '', '2020-11-25 04:34:22', NULL);
INSERT INTO `sys_dict_type` VALUES (40, '群防群治组织', 'base_mass_prevention_control_organization', '0', '', '2020-11-25 07:18:14', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (41, '投票状态', 'base_vote_stat', '0', '', '2020-11-25 07:22:13', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (43, '投票类型', 'base_vote_type', '0', '', '2020-11-25 07:56:27', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (44, '特殊人员数据来源', 'base_special_person_data_source', '0', '', '2020-11-25 11:44:58', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (45, '小区类型', 'base_community_child_type', '0', '', '2020-11-26 02:35:12', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (46, '社会组织状态', 'base_social_organization_status', '0', '', '2020-11-27 06:49:22', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (47, '审核状态', 'base_checked_stat', '0', '', '2020-11-27 06:58:29', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (48, '境外非政府组织类别', 'base_overseas_NGOs_type', '0', '', '2020-11-27 09:02:10', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (49, '住户与房主关系', 'base_relationship_resident_homeowner', '0', '', '2020-11-30 08:52:40', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (50, '车辆使用类型', 'base_vehicle_use', '0', '', '2020-12-02 03:40:39', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (51, '感染途径', 'base_path_of_infection', '0', '', '2020-12-03 09:26:13', '', '2020-12-03 09:59:35', NULL);
INSERT INTO `sys_dict_type` VALUES (52, '关注类型', 'base_attention_type', '0', '', '2020-12-03 09:33:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (53, '收治情况', 'base_admission_status', '0', '', '2020-12-03 09:43:28', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (54, '管控情况', 'base_control_situation', '0', '', '2020-12-03 10:00:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (55, '罪名分类', 'base_criminal_type', '0', '', '2020-12-04 02:55:10', '', '2020-12-04 03:15:03', NULL);
INSERT INTO `sys_dict_type` VALUES (56, '危险性评估类型', 'base_risk_assessment', '0', '', '2020-12-04 03:02:40', '', '2020-12-04 03:14:57', NULL);
INSERT INTO `sys_dict_type` VALUES (57, '衔接情况', 'base_connection', '0', '', '2020-12-04 03:09:50', '', '2020-12-04 03:14:49', NULL);
INSERT INTO `sys_dict_type` VALUES (58, '安置情况', 'base_resettlement', '0', '', '2020-12-04 03:14:29', '', '2020-12-04 03:14:38', NULL);
INSERT INTO `sys_dict_type` VALUES (59, '家庭经济状况', 'base_family_financial_situation', '0', '', '2020-12-04 03:14:41', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (60, '目前诊断类型', 'base_current_diagnosis_type', '0', '', '2020-12-04 03:17:40', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (61, '矫正类型', 'base_correction_type', '0', '', '2020-12-04 03:20:22', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (62, '目前危险性评估等级', 'base_current_risk_assessment_level', '0', '', '2020-12-04 03:23:08', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (63, '接收方式', 'base_receiving_mode', '0', '', '2020-12-04 03:23:26', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (64, '治疗情况', 'base_treatment_situation', '0', '', '2020-12-04 03:25:47', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (65, '“四史”情况', 'base_four_histories', '0', '', '2020-12-04 03:27:56', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (66, '实施住院治疗原因', 'base_reasons_for_hospitalization', '0', '', '2020-12-04 03:28:48', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (67, '“三涉”情况', 'base_three_involvement', '0', '', '2020-12-04 03:30:11', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (68, '参与管理人员', 'base_participating_managers', '0', '', '2020-12-04 03:31:34', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (69, '矫正小组人员组成类型', 'base_correction_team_composition_type', '0', '', '2020-12-04 03:34:02', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (70, '帮扶情况', 'base_help_situation', '0', '', '2020-12-04 03:38:52', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (71, '矫正解除（终止）类型', 'base_correction_release_termination_type', '0', '', '2020-12-04 03:38:55', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (72, '布控类型', 'base_monitoring_type', '0', '', '2020-12-04 08:31:19', '', '2020-12-28 08:27:45', NULL);
INSERT INTO `sys_dict_type` VALUES (73, '吸毒原因', 'base_reasons_for_taking_drugs', '0', '', '2020-12-04 10:53:23', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (74, '社区事件类型', 'base_community_event', '0', '', '2020-12-08 03:42:25', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (75, '告警层级', 'base_alarm_level', '0', '', '2020-12-08 03:48:20', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (76, '案件类别', 'base_case_type', '0', '', '2020-12-09 04:09:39', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (77, '关爱事件类型', 'base_caring_event', '0', '', '2020-12-12 08:35:41', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (78, '访客车辆类型', 'base_visitor_vehicle_type', '0', '', '2020-12-16 03:49:11', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (79, '能源类型', 'base_energy_type', '0', '', '2020-12-16 03:51:46', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (80, '布控花名册类型', 'base_roster_type', '0', '', '2020-12-28 08:26:19', '', '2020-12-28 09:43:16', NULL);
INSERT INTO `sys_dict_type` VALUES (81, '家庭关系', 'base_family_relation', '0', '', '2020-12-29 02:43:09', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (82, '人户一致标志', 'base_household_flag', '0', '', '2020-12-29 02:52:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (83, '图像信息库文件类型', 'base_image_info_lib_file_type', '0', '', '2020-12-31 05:26:50', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `multi_component` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '是否是多路由（0否1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('00eb9db7-2bde-11eb-8b0f-0242ac120002', '实有人口', 'a1dec185-2551-11eb-8b0f-0242ac120002', 1, 'communityUnit/personnel_information', 'default:communityUnit/personnel_information:-1', 1, 'C', '0', '0', 'communityUnit:personnel_information', 'iconfl-renyuan', 'admin', '2020-11-21 10:43:26', 'admin', '2020-12-16 08:17:27', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('0504e2e8-2bdf-11eb-8b0f-0242ac120002', '一房一档', 'd661b8df-296e-11eb-8b0f-0242ac120002', 3, 'communityUnit/community_house_archives', 'default:communityUnit/community_houses/archives:-1', 1, 'C', '0', '0', 'communityUnit:community_house_archives', 'iconbuilding', 'admin', '2020-11-21 10:50:42', 'admin', '2020-12-17 10:57:16', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('057a729f-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 ', 'a89d0d8b-2bc4-11eb-8b0f-0242ac120002', 1, 'caringPerson/caring_person', 'default:caringPerson/caring_person/index:-1', 1, 'C', '0', '0', 'caringPerson:caring_person:list', 'iconrenti', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-30 04:11:26', '关爱人员信息记录 菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('0584b267-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 查询', '057a729f-2eee-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'caringPerson:caring_person:query', '#', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-25 08:15:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('058c4ef0-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 新增', '057a729f-2eee-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'caringPerson:caring_person:add', '#', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-25 08:15:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('05935cf8-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 修改', '057a729f-2eee-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'caringPerson:caring_person:edit', '#', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-25 08:15:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('0599d1a2-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 删除', '057a729f-2eee-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'caringPerson:caring_person:remove', '#', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-25 08:15:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('05a088d9-2eee-11eb-8b0f-0242ac120002', '关爱人员信息记录 导出', '057a729f-2eee-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'caringPerson:caring_person:export', '#', 'admin', '2020-11-25 08:15:39', 'admin', '2020-11-25 08:15:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('0ca9557b-2baa-11eb-8b0f-0242ac120002', '通行管理', '0', 3, '', NULL, 1, 'M', '0', '0', 'passManage', 'icontonghangguanli', 'admin', '2020-11-21 04:31:32', 'admin', '2020-12-16 08:26:44', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', 10, '', NULL, 1, 'M', '0', '0', 'system', 'iconxitongguanli1', 'admin', '2018-03-16 11:33:00', 'admin', '2020-12-16 08:41:26', '系统管理目录', '0', '0');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', 1, 'system/user', 'default:system/user/index', 1, 'C', '0', '0', 'system:user:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:22:38', '用户管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1001', '用户查询', '100', 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1002', '用户新增', '100', 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '100', 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '100', 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1005', '用户导出', '100', 5, '', '', 1, 'F', '0', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1006', '用户导入', '100', 6, '', '', 1, 'F', '0', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1007', '重置密码', '100', 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1008', '角色查询', '101', 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1009', '角色新增', '101', 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', 2, 'system/role/', 'default:system/role/index', 1, 'C', '0', '0', 'system:role:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:22:49', '角色管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1010', '角色修改', '101', 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1011', '角色删除', '101', 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1012', '角色导出', '101', 5, '', '', 1, 'F', '0', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1013', '菜单查询', '102', 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1014', '菜单新增', '102', 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1015', '菜单修改', '102', 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1016', '菜单删除', '102', 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1017', '部门查询', '103', 1, '', '', 1, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1018', '部门新增', '103', 2, '', '', 1, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1019', '部门修改', '103', 3, '', '', 1, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', 3, 'system/menu', 'default:system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'iconguangquanfangda', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:22:22', '菜单管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1020', '部门删除', '103', 4, '', '', 1, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1026', '字典查询', '105', 1, '#', '', 1, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1027', '字典新增', '105', 2, '#', '', 1, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1028', '字典修改', '105', 3, '#', '', 1, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1029', '字典删除', '105', 4, '#', '', 1, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', 4, 'system/dept', 'default:system/dept/index', 1, 'C', '0', '0', 'system:dept:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:24:54', '部门管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1030', '字典导出', '105', 5, '#', '', 1, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1031', '参数查询', '106', 1, '#', '', 1, 'F', '0', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1032', '参数新增', '106', 2, '#', '', 1, 'F', '0', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1033', '参数修改', '106', 3, '#', '', 1, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1034', '参数删除', '106', 4, '#', '', 1, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1035', '参数导出', '106', 5, '#', '', 1, 'F', '0', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1036', '公告查询', '107', 1, '#', '', 1, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1037', '公告新增', '107', 2, '#', '', 1, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1038', '公告修改', '107', 3, '#', '', 1, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1039', '公告删除', '107', 4, '#', '', 1, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1040', '操作查询', '500', 1, '#', '', 1, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1041', '操作删除', '500', 2, '#', '', 1, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', 4, '#', '', 1, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', 1, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', 2, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', 3, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', 1, '#', '', 1, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', 2, '#', '', 1, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', 3, '#', '', 1, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', 1, '#', '', 1, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', 6, 'system/dict', 'default:system/dict/index', 1, 'C', '0', '0', 'system:dict:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:25:07', '字典管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', 2, '#', '', 1, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', 3, '#', '', 1, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', 4, '#', '', 1, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', 5, '#', '', 1, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1054', '任务导出', '110', 7, '#', '', 1, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1055', '生成查询', '114', 1, '#', '', 1, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1056', '生成修改', '114', 2, '#', '', 1, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1057', '生成删除', '114', 3, '#', '', 1, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1058', '导入代码', '114', 2, '#', '', 1, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1059', '预览代码', '114', 4, '#', '', 1, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', 7, 'system/config', 'default:system/config/index', 1, 'C', '0', '0', 'system:config:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:25:20', '参数设置菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', 8, 'system/notice', 'default:system/notice/index', 1, 'C', '0', '0', 'system:notice:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:25:33', '通知公告菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', 9, '', '', 1, 'M', '0', '0', 'system:logs', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:25:43', '日志管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', 1, 'online', 'monitor/online/index', 1, 'C', '0', '0', 'monitor:online:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', 2, 'job', 'monitor/job/index', 1, 'C', '0', '0', 'monitor:job:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', 3, 'druid', 'monitor/druid/index', 1, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', 4, 'server', 'monitor/server/index', 1, 'C', '0', '0', 'monitor:server:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('1598562b-3c4e-11eb-8b0f-0242ac120002', '关爱人员事件', 'a89d0d8b-2bc4-11eb-8b0f-0242ac120002', 3, 'caringPerson/caring_event', 'default:caringPerson/caring_event:-1', 1, 'C', '0', '0', 'caringPerson:list', 'iconjiaoju', 'admin', '2020-12-12 08:46:03', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1e935fd2-2bc5-11eb-8b0f-0242ac120002', '关爱人员监控', 'a89d0d8b-2bc4-11eb-8b0f-0242ac120002', 2, 'caringPerson/monitor', 'default:caringPerson/monitor:-1', 1, 'C', '0', '0', 'caringPerson:monitor', 'iconxiansuoguanli', 'admin', '2020-11-21 07:45:18', 'admin', '2020-12-12 08:21:10', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', 11, '', NULL, 1, 'M', '0', '0', 'monitor', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-20 03:58:22', '系统监控目录', '0', '0');
INSERT INTO `sys_menu` VALUES ('2194f83f-2bc8-11eb-8b0f-0242ac120002', '便民服务', '0', 7, '', NULL, 1, 'M', '0', '0', 'convenienceServices', 'iconbianminfuwu', 'admin', '2020-11-21 08:06:52', 'admin', '2020-12-16 08:40:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('24e2a627-2f8f-11eb-8b0f-0242ac120002', '小区信息  ', '7ca9a612-2f87-11eb-8b0f-0242ac120002', 2, 'communityUnit/community_child', 'default:communityUnit/community_child/index:-1', 1, 'C', '1', '0', 'communityUnit:community_child:list', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-12-20 07:06:32', '小区信息  菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('24ef1278-2f8f-11eb-8b0f-0242ac120002', '小区信息  查询', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:community_child:query', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-11-26 03:29:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('24f526d5-2f8f-11eb-8b0f-0242ac120002', '小区信息  新增', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:community_child:add', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-11-26 03:29:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('24fd4669-4374-11eb-8b0f-0242ac120002', '通行统计', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 0, 'passManage/statistics', 'default:passManage/passStatistics:-1', 1, 'C', '1', '0', 'passManage:statistics', '#', 'admin', '2020-12-21 11:06:08', 'admin', '2020-12-31 03:56:44', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('25013e69-2f8f-11eb-8b0f-0242ac120002', '小区信息  修改', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:community_child:edit', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-11-26 03:29:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('250a0150-2f8f-11eb-8b0f-0242ac120002', '小区信息  删除', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:community_child:remove', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-11-26 03:29:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('25102293-2f8f-11eb-8b0f-0242ac120002', '小区信息  导出', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:community_child:export', '#', 'admin', '2020-11-26 03:29:01', 'admin', '2020-11-26 03:29:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('2ff02d28-2bde-11eb-8b0f-0242ac120002', '特殊人群', 'a1dec185-2551-11eb-8b0f-0242ac120002', 2, 'communityUnit/special_personnel', 'default:communityUnit/special_personnel:-1', 1, 'C', '0', '0', 'communityUnit:personnel_particular', 'iconfl-renyuan', 'admin', '2020-11-21 10:44:45', 'admin', '2020-12-16 08:19:07', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('301db27d-32ea-11eb-8b0f-0242ac120002', '车辆和社区房屋和人员关联 查询', '416e53d4-2552-11eb-8b0f-0242ac120002', 11, '#', '', 1, 'F', '0', '0', 'communityUnit:vehicle_houses_personnel:query', '#', 'admin', '2020-11-30 09:58:17', 'admin', '2020-11-30 09:58:42', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('3026a333-32ea-11eb-8b0f-0242ac120002', '车辆和社区房屋和人员关联 新增', '416e53d4-2552-11eb-8b0f-0242ac120002', 12, '#', '', 1, 'F', '0', '0', 'communityUnit:vehicle_houses_personnel:add', '#', 'admin', '2020-11-30 09:58:17', 'admin', '2020-11-30 09:59:06', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('302e5bc6-32ea-11eb-8b0f-0242ac120002', '车辆和社区房屋和人员关联 修改', '416e53d4-2552-11eb-8b0f-0242ac120002', 13, '#', '', 1, 'F', '0', '0', 'communityUnit:vehicle_houses_personnel:edit', '#', 'admin', '2020-11-30 09:58:17', 'admin', '2020-11-30 09:58:48', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('30368483-32ea-11eb-8b0f-0242ac120002', '车辆和社区房屋和人员关联 删除', '416e53d4-2552-11eb-8b0f-0242ac120002', 14, '#', '', 1, 'F', '0', '0', 'communityUnit:vehicle_houses_personnel:remove', '#', 'admin', '2020-11-30 09:58:17', 'admin', '2020-11-30 09:58:52', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('303e6bba-32ea-11eb-8b0f-0242ac120002', '车辆和社区房屋和人员关联 导出', '416e53d4-2552-11eb-8b0f-0242ac120002', 15, '#', '', 1, 'F', '0', '0', 'communityUnit:vehicle_houses_personnel:export', '#', 'admin', '2020-11-30 09:58:17', 'admin', '2020-11-30 09:58:56', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('3633b5bb-2ee4-11eb-8b0f-0242ac120002', '社区组织管理', 'c23869bc-2ecc-11eb-8b0f-0242ac120002', 0, '', NULL, 1, 'M', '0', '0', 'communityManage:organization', 'iconjiaoju', 'admin', '2020-11-25 07:05:26', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('39a5a7e3-2bc5-11eb-8b0f-0242ac120002', '人员通行', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 1, 'passManage/pass_personnel', 'default:passManage/pass_personnel:-1', 1, 'C', '0', '0', 'passManage:person', 'iconrenti', 'admin', '2020-11-21 07:46:04', 'admin', '2020-12-21 11:02:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('3c084e20-2943-11eb-8b0f-0242ac120002', '智慧视频', '0', 2, '', NULL, 1, 'M', '0', '0', 'smartvideo', 'iconshipinzongheyingyong', 'admin', '2020-11-18 03:10:31', 'admin', '2020-12-16 08:39:53', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('416e53d4-2552-11eb-8b0f-0242ac120002', '车辆管理', '9b037b51-24c2-11eb-8b0f-0242ac120002', 2, '', '', 1, 'M', '0', '0', 'communityUnit:vehicle_information', 'icondkw_qiche', 'admin', '2020-11-13 02:47:58', 'admin', '2020-12-16 08:23:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('420718cb-2bc6-11eb-8b0f-0242ac120002', '查询中心', 'f92be637-2bc5-11eb-8b0f-0242ac120002', 1, '', NULL, 1, 'M', '1', '0', 'pvmonitor:searchCenter', 'icongaojingchaxun', 'admin', '2020-11-21 07:53:27', 'admin', '2020-12-31 03:56:56', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('49c9e307-32ea-11eb-8b0f-0242ac120002', '关联房屋', '00eb9db7-2bde-11eb-8b0f-0242ac120002', 16, '', NULL, 1, 'F', '0', '0', 'communityUnit:personnel_information:related', '#', 'admin', '2020-11-30 09:59:00', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('4fd99bff-2bc8-11eb-8b0f-0242ac120002', '社区公告', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/notice', 'default:system/notice:-1', 1, 'C', '0', '0', 'convenienceServices:bulletin', 'icongonggaoguanli', 'admin', '2020-11-21 08:08:09', 'admin', '2020-12-08 04:30:20', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', 1, 'monitor/operlog', 'default:monitor/operlog/index', 1, 'C', '0', '0', 'monitor:operlog:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:26:00', '操作日志菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', 2, 'monitor/logininfor', 'default:monitor/logininfor/index', 1, 'C', '0', '0', 'monitor:logininfor:list', 'iconquxiaofabu', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-16 10:26:13', '登录日志菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('507eeac2-2bc7-11eb-8b0f-0242ac120002', '人员名单', '80842b19-2bc6-11eb-8b0f-0242ac120002', 1, 'pvmonitor/personlib', 'default:pvmonitor/blackList/ChildFaceLibraryManage:-1', 1, 'C', '0', '0', 'pvmonitor:personlib', 'iconuser', 'admin', '2020-11-21 08:01:01', 'admin', '2020-12-30 08:17:35', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('5713dbcf-2bc6-11eb-8b0f-0242ac120002', '布控中心', 'f92be637-2bc5-11eb-8b0f-0242ac120002', 2, 'pvmonitor/monitor', 'default:pvmonitor/monitor:-1', 1, 'C', '0', '0', 'pvmonitor:monitor', 'iconbukongyujing', 'admin', '2020-11-21 07:54:02', 'admin', '2020-12-04 04:25:35', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('5a4eb7d5-2bc3-11eb-8b0f-0242ac120002', '临时审核', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 3, '', NULL, 1, 'M', '0', '0', 'passManage:tempCheck', 'iconvideoList', 'admin', '2020-11-21 07:32:39', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('5bc643b1-2bc5-11eb-8b0f-0242ac120002', '车辆通行', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 2, 'passManage/pass_vehicle', 'default:passManage/pass_vehicle:-1', 1, 'C', '0', '0', 'passManage:vehicle', 'iconkefujieche', 'admin', '2020-11-21 07:47:01', 'admin', '2020-12-21 11:03:03', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('6405a0bb-2bc7-11eb-8b0f-0242ac120002', '车辆黑名单', '80842b19-2bc6-11eb-8b0f-0242ac120002', 2, 'pvmonitor/vehiclelib', 'default:pvmonitor/blackList/ChildVehicleGroupManage:-1', 1, 'C', '0', '0', 'pvmonitor:vehiclelib', 'iconchelianggaojing', 'admin', '2020-11-21 08:01:34', 'admin', '2020-12-30 08:16:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('65d17bc7-2bde-11eb-8b0f-0242ac120002', '一人一档', 'a1dec185-2551-11eb-8b0f-0242ac120002', 3, 'communityUnit/personnel_archive', 'default:communityUnit/personArchive:-1', 1, 'C', '0', '0', 'communityUnit:personnel_archives', 'iconuser', 'admin', '2020-11-21 10:46:15', 'admin', '2020-12-17 06:54:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('6abab1f9-2eed-11eb-8b0f-0242ac120002', '非公经济组织信息', '3633b5bb-2ee4-11eb-8b0f-0242ac120002', 1, 'communityManage/group_economic', 'default:communityManage/group_economic/index:-1', 1, 'C', '0', '0', 'communityManage:group_economic:list', '#', 'admin', '2020-11-25 08:11:19', 'admin', '2020-11-25 08:11:19', '非公经济组织信息菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('6ac7d17b-2eed-11eb-8b0f-0242ac120002', '非公经济组织信息查询', '6abab1f9-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityManage:group_economic:query', '#', 'admin', '2020-11-25 08:11:19', 'admin', '2020-11-25 08:11:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('6ad0ac1d-2eed-11eb-8b0f-0242ac120002', '非公经济组织信息新增', '6abab1f9-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityManage:group_economic:add', '#', 'admin', '2020-11-25 08:11:19', 'admin', '2020-11-25 08:11:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('6ad7db3e-2eed-11eb-8b0f-0242ac120002', '非公经济组织信息修改', '6abab1f9-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityManage:group_economic:edit', '#', 'admin', '2020-11-25 08:11:19', 'admin', '2020-11-25 08:11:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('6adf1665-2eed-11eb-8b0f-0242ac120002', '非公经济组织信息删除', '6abab1f9-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityManage:group_economic:remove', '#', 'admin', '2020-11-25 08:11:20', 'admin', '2020-11-25 08:11:20', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7b5d6db5-2eed-11eb-8b0f-0242ac120002', '综治机构管理', '3633b5bb-2ee4-11eb-8b0f-0242ac120002', 1, 'communityManage/org_basic', 'default:communityManage/org_basic/index:-1', 1, 'C', '0', '0', 'communityManage:org_basic:list', '#', 'admin', '2020-11-25 08:11:47', 'admin', '2020-11-25 08:11:47', '综治机构管理菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('7b6b1865-2eed-11eb-8b0f-0242ac120002', '综治机构管理查询', '7b5d6db5-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityManage:org_basic:query', '#', 'admin', '2020-11-25 08:11:47', 'admin', '2020-11-25 08:11:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7b73bcea-2eed-11eb-8b0f-0242ac120002', '综治机构管理新增', '7b5d6db5-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityManage:org_basic:add', '#', 'admin', '2020-11-25 08:11:47', 'admin', '2020-11-25 08:11:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7b7cb8e5-2eed-11eb-8b0f-0242ac120002', '综治机构管理修改', '7b5d6db5-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityManage:org_basic:edit', '#', 'admin', '2020-11-25 08:11:47', 'admin', '2020-11-25 08:11:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7b850bdb-2eed-11eb-8b0f-0242ac120002', '综治机构管理删除', '7b5d6db5-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityManage:org_basic:remove', '#', 'admin', '2020-11-25 08:11:47', 'admin', '2020-11-25 08:11:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7ca9a612-2f87-11eb-8b0f-0242ac120002', '社区配置', 'c23869bc-2ecc-11eb-8b0f-0242ac120002', 3, '', NULL, 1, 'M', '0', '0', 'communityManage:childManage', 'iconrizhijilu', 'admin', '2020-11-26 02:34:12', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7cb741b9-2eed-11eb-8b0f-0242ac120002', '投票项目信息 ', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/vote_project', 'default:communityServices/vote_project/index:-1', 1, 'C', '0', '0', 'communityServices:vote_project:list', '#', 'admin', '2020-11-25 08:11:49', 'admin', '2020-11-25 08:11:49', '投票项目信息 菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('7cc4b09e-2eed-11eb-8b0f-0242ac120002', '投票项目信息 查询', '7cb741b9-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityServices:vote_project:query', '#', 'admin', '2020-11-25 08:11:50', 'admin', '2020-11-25 08:11:50', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7ccc4a48-2eed-11eb-8b0f-0242ac120002', '投票项目信息 新增', '7cb741b9-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityServices:vote_project:add', '#', 'admin', '2020-11-25 08:11:50', 'admin', '2020-11-25 08:11:50', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7cd3e1b5-2eed-11eb-8b0f-0242ac120002', '投票项目信息 修改', '7cb741b9-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityServices:vote_project:edit', '#', 'admin', '2020-11-25 08:11:50', 'admin', '2020-11-25 08:11:50', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7cdba59b-2eed-11eb-8b0f-0242ac120002', '投票项目信息 删除', '7cb741b9-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityServices:vote_project:remove', '#', 'admin', '2020-11-25 08:11:50', 'admin', '2020-11-25 08:11:50', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('7ce34ebe-2eed-11eb-8b0f-0242ac120002', '投票项目信息 导出', '7cb741b9-2eed-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityServices:vote_project:export', '#', 'admin', '2020-11-25 08:11:50', 'admin', '2020-11-25 08:11:50', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('80842b19-2bc6-11eb-8b0f-0242ac120002', '名单管理', 'f92be637-2bc5-11eb-8b0f-0242ac120002', 3, '', NULL, 1, 'M', '0', '0', 'pvmonitor:libraryManage', 'iconrenliankuchaxun', 'admin', '2020-11-21 07:55:12', 'admin', '2020-12-30 08:17:47', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('82ca889c-32d6-11eb-8b0f-0242ac120002', '楼栋楼层 只做记录用，房屋数据直接使用楼层号查询', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:building_floor:query', '#', 'admin', '2020-11-30 07:37:26', 'admin', '2020-11-30 07:37:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('82d0ebe9-32d6-11eb-8b0f-0242ac120002', '楼栋楼层 只做记录用，房屋数据直接使用楼层号新增', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:building_floor:add', '#', 'admin', '2020-11-30 07:37:26', 'admin', '2020-11-30 07:37:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('82d7f7b2-32d6-11eb-8b0f-0242ac120002', '楼栋楼层 只做记录用，房屋数据直接使用楼层号修改', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:building_floor:edit', '#', 'admin', '2020-11-30 07:37:26', 'admin', '2020-11-30 07:37:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('82deb3b9-32d6-11eb-8b0f-0242ac120002', '楼栋楼层 只做记录用，房屋数据直接使用楼层号删除', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:building_floor:remove', '#', 'admin', '2020-11-30 07:37:26', 'admin', '2020-11-30 07:37:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('82e4a71e-32d6-11eb-8b0f-0242ac120002', '楼栋楼层 只做记录用，房屋数据直接使用楼层号导出', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:building_floor:export', '#', 'admin', '2020-11-30 07:37:26', 'admin', '2020-11-30 07:37:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8a6223aa-2bc5-11eb-8b0f-0242ac120002', '陌生人', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 4, '', NULL, 1, 'M', '1', '0', 'passManage:outlander', 'iconxianyiren', 'admin', '2020-11-21 07:48:19', 'admin', '2020-12-31 03:59:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8c962dc7-2eed-11eb-8b0f-0242ac120002', '群防群治组织', '3633b5bb-2ee4-11eb-8b0f-0242ac120002', 1, 'communityManage/org_public', 'default:communityManage/org_public/index:-1', 1, 'C', '0', '0', 'communityManage:org_public:list', '#', 'admin', '2020-11-25 08:12:16', 'admin', '2020-11-25 08:12:16', '群防群治组织菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('8ca34f41-2eed-11eb-8b0f-0242ac120002', '群防群治组织查询', '8c962dc7-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityManage:org_public:query', '#', 'admin', '2020-11-25 08:12:16', 'admin', '2020-11-25 08:12:16', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8cab86cf-2eed-11eb-8b0f-0242ac120002', '群防群治组织新增', '8c962dc7-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityManage:org_public:add', '#', 'admin', '2020-11-25 08:12:16', 'admin', '2020-11-25 08:12:16', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8cb2fb69-2eed-11eb-8b0f-0242ac120002', '群防群治组织修改', '8c962dc7-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityManage:org_public:edit', '#', 'admin', '2020-11-25 08:12:16', 'admin', '2020-11-25 08:12:16', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8cb9123a-2eed-11eb-8b0f-0242ac120002', '群防群治组织删除', '8c962dc7-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityManage:org_public:remove', '#', 'admin', '2020-11-25 08:12:16', 'admin', '2020-11-25 08:12:16', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('8ff1168c-2bc7-11eb-8b0f-0242ac120002', '频繁出入', 'bada6c67-2bc6-11eb-8b0f-0242ac120002', 1, '', NULL, 1, 'M', '0', '0', 'pvmonitor:saFrequently', 'iconfenzuguanli', 'admin', '2020-11-21 08:02:47', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9239ff19-2d5c-11eb-8b0f-0242ac120002', '来访人员', 'ea930e1e-2baa-11eb-8b0f-0242ac120002', 1, 'passManage/visitPerson', 'default:passManage/visitPerson/index:-1', 1, 'C', '0', '0', 'passManage:visitPerson:list', 'iconuser', 'admin', '2020-11-23 08:21:57', 'admin', '2020-11-23 08:29:02', '来访人员菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('92425141-2d5c-11eb-8b0f-0242ac120002', '来访人员查询', '9239ff19-2d5c-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'passManage:visitPerson:query', '#', 'admin', '2020-11-23 08:21:57', 'admin', '2020-11-23 08:21:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('92463d7f-2d5c-11eb-8b0f-0242ac120002', '来访人员新增', '9239ff19-2d5c-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'passManage:visitPerson:add', '#', 'admin', '2020-11-23 08:21:57', 'admin', '2020-11-23 08:21:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('924b459b-2d5c-11eb-8b0f-0242ac120002', '来访人员修改', '9239ff19-2d5c-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'passManage:visitPerson:edit', '#', 'admin', '2020-11-23 08:21:57', 'admin', '2020-11-23 08:21:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('92507687-2d5c-11eb-8b0f-0242ac120002', '来访人员删除', '9239ff19-2d5c-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'passManage:visitPerson:remove', '#', 'admin', '2020-11-23 08:21:57', 'admin', '2020-11-23 08:21:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9255b82b-2d5c-11eb-8b0f-0242ac120002', '来访人员导出', '9239ff19-2d5c-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'passManage:visitPerson:export', '#', 'admin', '2020-11-23 08:21:58', 'admin', '2020-11-23 08:21:58', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9546ae62-2adc-11eb-8b0f-0242ac120002', '实有房屋', 'd661b8df-296e-11eb-8b0f-0242ac120002', 2, 'communityUnit/house', 'default:communityUnit/community_houses:-1', 1, 'C', '0', '0', 'communityUnit:community_houses:query', 'iconfangwu', 'admin', '2020-11-20 04:00:45', 'admin', '2020-12-16 08:24:55', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9b037b51-24c2-11eb-8b0f-0242ac120002', '人房车', '0', 1, '', NULL, 1, 'M', '0', '0', 'communityUnit', 'iconrenfangcheguanli', 'admin', '2020-11-12 09:39:40', 'admin', '2020-12-16 08:14:31', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9df24af4-2eed-11eb-8b0f-0242ac120002', '社会组织信息', '3633b5bb-2ee4-11eb-8b0f-0242ac120002', 1, 'communityManage/social_organization', 'default:communityManage/social_organization/index:-1', 1, 'C', '0', '0', 'communityManage:social_organization:list', '#', 'admin', '2020-11-25 08:12:45', 'admin', '2020-11-25 08:12:45', '社会组织信息菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('9dfdb626-2eed-11eb-8b0f-0242ac120002', '社会组织信息查询', '9df24af4-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityManage:social_organization:query', '#', 'admin', '2020-11-25 08:12:45', 'admin', '2020-11-25 08:12:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e00c193-2d5c-11eb-8b0f-0242ac120002', '来访车辆', 'ea930e1e-2baa-11eb-8b0f-0242ac120002', 1, 'passManage/visitVehicle', 'default:passManage/visitVehicle/index:-1', 1, 'C', '0', '0', 'passManage:visitVehicle:list', 'iconchelianggaojing', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:29:17', '来访车辆菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e033013-2eed-11eb-8b0f-0242ac120002', '社会组织信息新增', '9df24af4-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityManage:social_organization:add', '#', 'admin', '2020-11-25 08:12:45', 'admin', '2020-11-25 08:12:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e07fb75-2d5c-11eb-8b0f-0242ac120002', '来访车辆查询', '9e00c193-2d5c-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'passManage:visitVehicle:query', '#', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:22:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e0adc71-2eed-11eb-8b0f-0242ac120002', '社会组织信息修改', '9df24af4-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityManage:social_organization:edit', '#', 'admin', '2020-11-25 08:12:45', 'admin', '2020-11-25 08:12:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e0ce1a7-2d5c-11eb-8b0f-0242ac120002', '来访车辆新增', '9e00c193-2d5c-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'passManage:visitVehicle:add', '#', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:22:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e0f65cb-2d5c-11eb-8b0f-0242ac120002', '来访车辆修改', '9e00c193-2d5c-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'passManage:visitVehicle:edit', '#', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:22:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e1140e5-2eed-11eb-8b0f-0242ac120002', '社会组织信息删除', '9df24af4-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityManage:social_organization:remove', '#', 'admin', '2020-11-25 08:12:45', 'admin', '2020-11-25 08:12:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e121462-2d5c-11eb-8b0f-0242ac120002', '来访车辆删除', '9e00c193-2d5c-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'passManage:visitVehicle:remove', '#', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:22:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('9e168e4f-2d5c-11eb-8b0f-0242ac120002', '来访车辆导出', '9e00c193-2d5c-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'passManage:visitVehicle:export', '#', 'admin', '2020-11-23 08:22:17', 'admin', '2020-11-23 08:22:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a1d34085-2551-11eb-8b0f-0242ac120002', '人员信息导出', 'a1dec185-2551-11eb-8b0f-0242ac120002', 15, '#', '', 1, 'F', '0', '0', 'communityUnit:personnel_information:export', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-21 10:45:22', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a1d34685-2551-11eb-8b0f-0242ac120002', '人员信息删除', 'a1dec185-2551-11eb-8b0f-0242ac120002', 14, '#', '', 1, 'F', '0', '0', 'communityUnit:personnel_information:remove', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-21 10:45:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a1dec185-2551-11eb-8b0f-0242ac120002', '人员管理', '9b037b51-24c2-11eb-8b0f-0242ac120002', 1, '', '', 1, 'M', '0', '0', 'communityUnit:personnel_information', 'iconrenyuan', 'admin', '2020-11-13 02:43:30', 'admin', '2020-12-16 08:16:49', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a1sj6hg5-2551-11eb-8b0f-0242ac120002', '人员信息修改', 'a1dec185-2551-11eb-8b0f-0242ac120002', 13, '#', '', 1, 'F', '0', '0', 'communityUnit:personnel_information:edit', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-21 10:45:14', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a234uw85-2551-11eb-8b0f-0242ac120002', '人员信息查询', 'a1dec185-2551-11eb-8b0f-0242ac120002', 11, '#', '', 1, 'F', '0', '0', 'communityUnit:personnel_information:query', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-21 10:45:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a37a39b3-2bde-11eb-8b0f-0242ac120002', '实有车辆', '416e53d4-2552-11eb-8b0f-0242ac120002', 1, 'communityUnit/vehicle_information', 'default:communityUnit/vehicle_information:-1', 1, 'C', '0', '0', 'communityUnit:vehicle_information', 'iconchelianggaojing', 'admin', '2020-11-21 10:47:59', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a6e7d643-32d6-11eb-8b0f-0242ac120002', '楼栋单元 只做记录用，房屋数据直接使用单元号查询', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:building_unit:query', '#', 'admin', '2020-11-30 07:38:26', 'admin', '2020-11-30 07:38:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a6ee29ea-32d6-11eb-8b0f-0242ac120002', '楼栋单元 只做记录用，房屋数据直接使用单元号新增', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:building_unit:add', '#', 'admin', '2020-11-30 07:38:26', 'admin', '2020-11-30 07:38:26', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a6f51a04-32d6-11eb-8b0f-0242ac120002', '楼栋单元 只做记录用，房屋数据直接使用单元号修改', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:building_unit:edit', '#', 'admin', '2020-11-30 07:38:27', 'admin', '2020-11-30 07:38:27', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a6fc3845-32d6-11eb-8b0f-0242ac120002', '楼栋单元 只做记录用，房屋数据直接使用单元号删除', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:building_unit:remove', '#', 'admin', '2020-11-30 07:38:27', 'admin', '2020-11-30 07:38:27', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a705c263-32d6-11eb-8b0f-0242ac120002', '楼栋单元 只做记录用，房屋数据直接使用单元号导出', '24e2a627-2f8f-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:building_unit:export', '#', 'admin', '2020-11-30 07:38:27', 'admin', '2020-11-30 07:38:27', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('a89d0d8b-2bc4-11eb-8b0f-0242ac120002', '关爱人员管理', '0', 4, '', NULL, 1, 'M', '0', '0', 'caringPerson', 'iconguanairenyuanguanli', 'admin', '2020-11-21 07:42:00', 'admin', '2020-12-16 08:29:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ac70c2c1-2943-11eb-8b0f-0242ac120002', '实时视频', '3c084e20-2943-11eb-8b0f-0242ac120002', 1, 'SmartVideo/RelatimeVideo', 'default:SmartVideo/RelatimeVideo/autoWHPanel:-1,left_panel_router_view:SmartVideo/RelatimeVideo/leftPanel:400', 1, 'C', '0', '0', 'smartvideo:relatimevideo', 'iconvideoList', 'admin', '2020-11-18 03:13:39', '', NULL, '', '0', '1');
INSERT INTO `sys_menu` VALUES ('asw3g185-2551-11eb-8b0f-0242ac120002', '人员信息新增', 'a1dec185-2551-11eb-8b0f-0242ac120002', 12, '#', '', 1, 'F', '0', '0', 'communityUnit:personnel_information:add', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-21 10:45:06', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b3487hfd-2553-11eb-8b0f-0242ac1340l2', '社区房屋信息导出', '9546ae62-2adc-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b3487hfd-2553-11eb-8b0f-0242ac1543g2', '社区房屋信息查询', '9546ae62-2adc-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses:query', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b3487hfd-2553-11eb-8b0f-0242ac1gh782', '社区房屋信息新增', '9546ae62-2adc-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b3487hfd-2553-11eb-8b0f-0242acdfr902', '社区房屋信息修改', '9546ae62-2adc-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b3487hfd-2553-11eb-8b0f-0242asd34002', '社区房屋信息删除', '9546ae62-2adc-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-0242ac120002', '社区管理', '7ca9a612-2f87-11eb-8b0f-0242ac120002', 1, 'communityUnit/communityUnit', 'default:communityUnit/community_child:-1', 1, 'C', '0', '0', 'communityUnit:community', 'iconquxiaofabu', 'admin', '2020-11-13 02:58:18', 'admin', '2020-11-26 04:14:34', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-1121ac120002', '社区信息删除', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:community:remove', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-1212ac120002', '社区楼栋新增', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:community_building:add', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-1234ac120002', '社区信息查询', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:community:query', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-1346ac120002', '社区楼栋查询', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityUnit:community_building:query', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-2212ac120002', '社区楼栋修改', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:community_building:edit', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-2332ac120002', '社区信息导出', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:community:export', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-2341ac120002', '社区信息修改', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityUnit:community:edit', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-2342ac120002', '社区信息新增', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityUnit:community:add', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-4422ac120002', '社区楼栋删除', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityUnit:community_building:remove', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b35dfffd-2553-11eb-8b0f-4432ac120002', '社区楼栋导出', 'b35dfffd-2553-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityUnit:community_building:export', '#', 'admin', '2020-11-18 00:00:00', 'isstech', '2020-11-18 00:00:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b4b32d6a-3dd2-11eb-8b0f-0242ac120002', '投票项选项', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/vote_option/:projectId(\\S+)&:projectName(\\S+)', 'default:communityServices/vote_option:-1', 1, 'C', '1', '0', 'communityServices:vote_option', 'iconjiaoju', 'admin', '2020-12-14 07:07:54', 'admin', '2020-12-14 08:11:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('b8f2c4cd-2bc7-11eb-8b0f-0242ac120002', '凌晨出行', 'bada6c67-2bc6-11eb-8b0f-0242ac120002', 2, '', NULL, 1, 'M', '0', '0', 'pvmonitor:saMorningTrio', 'iconhangweifenxi', 'admin', '2020-11-21 08:03:56', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('bada6c67-2bc6-11eb-8b0f-0242ac120002', '可以行为分析', 'f92be637-2bc5-11eb-8b0f-0242ac120002', 4, '', NULL, 1, 'M', '1', '0', 'pvmonitor:suspiciousAnalysis', 'iconxianyiren', 'admin', '2020-11-21 07:56:50', 'admin', '2020-12-31 03:57:03', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c0210480-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息', '5a4eb7d5-2bc3-11eb-8b0f-0242ac120002', 2, 'passManage/temp_visitor_vehicle', 'default:passManage/temp_visitor_vehicle/index:-1', 1, 'C', '0', '0', 'passManage:temp_visitor_vehicle:list', 'iconrizhijilu', 'admin', '2020-11-23 08:23:14', 'admin', '2020-11-24 08:36:26', '临时访客车辆信息菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('c0279249-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息查询', 'c0210480-2d5c-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_vehicle:query', '#', 'admin', '2020-11-23 08:23:14', 'admin', '2020-11-23 08:23:14', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c02f1d52-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息新增', 'c0210480-2d5c-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_vehicle:add', '#', 'admin', '2020-11-23 08:23:14', 'admin', '2020-11-23 08:23:14', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c0344739-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息修改', 'c0210480-2d5c-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_vehicle:edit', '#', 'admin', '2020-11-23 08:23:14', 'admin', '2020-11-23 08:23:14', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c0398813-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息删除', 'c0210480-2d5c-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_vehicle:remove', '#', 'admin', '2020-11-23 08:23:15', 'admin', '2020-11-23 08:23:15', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c03d2139-2d5c-11eb-8b0f-0242ac120002', '临时访客车辆信息导出', 'c0210480-2d5c-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_vehicle:export', '#', 'admin', '2020-11-23 08:23:15', 'admin', '2020-11-23 08:23:15', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c23869bc-2ecc-11eb-8b0f-0242ac120002', '社区管理中心', '0', 8, '', NULL, 1, 'M', '0', '0', 'communityManage', 'iconshequguanli', 'admin', '2020-11-25 04:17:33', 'admin', '2020-12-16 08:40:54', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c53998fb-2bde-11eb-8b0f-0242ac120002', '一车一档', '416e53d4-2552-11eb-8b0f-0242ac120002', 2, 'communityUnit/vehicle_archives', 'default:communityUnit/vehicleArchive:-1', 1, 'C', '0', '0', 'communityUnit:vehicle_archives', 'iconchelianggaojing', 'admin', '2020-11-21 10:48:55', 'admin', '2020-12-21 03:36:03', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c88fe768-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息', '5a4eb7d5-2bc3-11eb-8b0f-0242ac120002', 1, 'passManage/temp_visitor_personnel', 'default:passManage/temp_visitor_personnel/index:-1', 1, 'C', '0', '0', 'passManage:temp_visitor_personnel:list', 'iconuser', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:30:25', '临时访客人员信息菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('c896bf33-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息查询', 'c88fe768-2d5c-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_personnel:query', '#', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:23:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c89a6a66-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息新增', 'c88fe768-2d5c-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_personnel:add', '#', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:23:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c89e3ab9-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息修改', 'c88fe768-2d5c-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_personnel:edit', '#', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:23:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c8a28970-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息删除', 'c88fe768-2d5c-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_personnel:remove', '#', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:23:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('c8a603d9-2d5c-11eb-8b0f-0242ac120002', '临时访客人员信息导出', 'c88fe768-2d5c-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'passManage:temp_visitor_personnel:export', '#', 'admin', '2020-11-23 08:23:29', 'admin', '2020-11-23 08:23:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb119084-2eed-11eb-8b0f-0242ac120002', '投诉意见记录', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/opinion_record', 'default:communityServices/opinion_record/index:-1', 1, 'C', '0', '0', 'communityServices:opinion_record:list', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '投诉意见记录菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb1c280b-2eed-11eb-8b0f-0242ac120002', '投诉意见记录查询', 'cb119084-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityServices:opinion_record:query', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb21c435-2eed-11eb-8b0f-0242ac120002', '投诉意见记录新增', 'cb119084-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityServices:opinion_record:add', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb2a0160-2eed-11eb-8b0f-0242ac120002', '投诉意见记录修改', 'cb119084-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityServices:opinion_record:edit', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb303d56-2eed-11eb-8b0f-0242ac120002', '投诉意见记录删除', 'cb119084-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityServices:opinion_record:remove', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('cb36dd6a-2eed-11eb-8b0f-0242ac120002', '投诉意见记录导出', 'cb119084-2eed-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityServices:opinion_record:export', '#', 'admin', '2020-11-25 08:14:01', 'admin', '2020-11-25 08:14:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ce41974d-2bc6-11eb-8b0f-0242ac120002', '人员检索', '420718cb-2bc6-11eb-8b0f-0242ac120002', 1, '', NULL, 1, 'M', '0', '0', 'pvmonitor:searchPerson', 'iconuser', 'admin', '2020-11-21 07:57:22', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d50ca33e-2eec-11eb-8b0f-0242ac120002', '境外非政府组织', '3633b5bb-2ee4-11eb-8b0f-0242ac120002', 1, 'communityManage/group_abroad', 'default:communityManage/group_abroad/index:-1', 1, 'C', '0', '0', 'communityManage:group_abroad:list', '#', 'admin', '2020-11-25 08:07:08', 'admin', '2020-11-27 08:43:22', '境内外非政府组织菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('d518937a-2eec-11eb-8b0f-0242ac120002', '境内外非政府组织查询', 'd50ca33e-2eec-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityManage:group_abroad:query', '#', 'admin', '2020-11-25 08:07:08', 'admin', '2020-11-25 08:07:08', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d51f718a-2eec-11eb-8b0f-0242ac120002', '境内外非政府组织新增', 'd50ca33e-2eec-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityManage:group_abroad:add', '#', 'admin', '2020-11-25 08:07:08', 'admin', '2020-11-25 08:07:08', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d5278698-2eec-11eb-8b0f-0242ac120002', '境内外非政府组织修改', 'd50ca33e-2eec-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityManage:group_abroad:edit', '#', 'admin', '2020-11-25 08:07:08', 'admin', '2020-11-25 08:07:08', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d52f6d5b-2eec-11eb-8b0f-0242ac120002', '境内外非政府组织删除', 'd50ca33e-2eec-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityManage:group_abroad:remove', '#', 'admin', '2020-11-25 08:07:08', 'admin', '2020-11-25 08:07:08', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d52f8276-2eed-11eb-8b0f-0242ac120002', '报事报修记录', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/repair_record', 'default:communityServices/repair_record/index:-1', 1, 'C', '0', '0', 'communityServices:repair_record:list', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '报事报修记录菜单', '0', '0');
INSERT INTO `sys_menu` VALUES ('d5392a56-2eed-11eb-8b0f-0242ac120002', '报事报修记录查询', 'd52f8276-2eed-11eb-8b0f-0242ac120002', 1, '#', '', 1, 'F', '0', '0', 'communityServices:repair_record:query', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d53e9f16-2eed-11eb-8b0f-0242ac120002', '报事报修记录新增', 'd52f8276-2eed-11eb-8b0f-0242ac120002', 2, '#', '', 1, 'F', '0', '0', 'communityServices:repair_record:add', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d545738d-2eed-11eb-8b0f-0242ac120002', '报事报修记录修改', 'd52f8276-2eed-11eb-8b0f-0242ac120002', 3, '#', '', 1, 'F', '0', '0', 'communityServices:repair_record:edit', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d54ba9bb-2eed-11eb-8b0f-0242ac120002', '报事报修记录删除', 'd52f8276-2eed-11eb-8b0f-0242ac120002', 4, '#', '', 1, 'F', '0', '0', 'communityServices:repair_record:remove', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d553e734-2eed-11eb-8b0f-0242ac120002', '报事报修记录导出', 'd52f8276-2eed-11eb-8b0f-0242ac120002', 5, '#', '', 1, 'F', '0', '0', 'communityServices:repair_record:export', '#', 'admin', '2020-11-25 08:14:18', 'admin', '2020-11-25 08:14:18', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('d661b8df-296e-11eb-8b0f-0242ac120002', '房屋管理', '9b037b51-24c2-11eb-8b0f-0242ac120002', 3, '', NULL, 1, 'M', '0', '0', 'community:housrUnit', 'iconfangwu', 'admin', '2020-11-18 08:22:38', 'admin', '2020-12-16 08:24:29', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('e4dc92a4-2bc6-11eb-8b0f-0242ac120002', '车辆查询', '420718cb-2bc6-11eb-8b0f-0242ac120002', 2, '', NULL, 1, 'M', '0', '0', 'pvmonitor:searchVehicle', 'iconchelianggaojing', 'admin', '2020-11-21 07:58:00', '', NULL, '', '0', '0');
INSERT INTO `sys_menu` VALUES ('e4e53c07-24c1-11eb-8b0f-0242ac120002', '首页', '0', 0, '/index', 'default:home:-1,left_panel_router_view:home/leftPanel:280,right_panel_router_view:home/rightPanel:380', 1, 'C', '0', '0', 'home', 'iconshouye', 'admin', '2020-11-12 09:34:35', 'admin', '2020-12-24 03:54:39', '', '0', '1');
INSERT INTO `sys_menu` VALUES ('e6dd2451-2bc7-11eb-8b0f-0242ac120002', '社区事件提醒', '0', 6, 'communityEvent/community_event', 'default:communityEvent/community_event:-1', 1, 'C', '0', '0', 'communityEvent', 'iconshequshijiantixing', 'admin', '2020-11-21 08:05:13', 'admin', '2020-12-16 08:40:33', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ea7c9365-3dd2-11eb-8b0f-0242ac120002', '投票记录', '2194f83f-2bc8-11eb-8b0f-0242ac120002', 1, 'communityServices/vote_record/:projectId(\\S+)&:projectName(\\S+)', 'default:communityServices/vote_record:-1', 1, 'C', '1', '0', 'communityServices:vote_record', 'iconjiaoju', 'admin', '2020-12-14 07:09:25', 'admin', '2020-12-16 02:08:42', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ea930e1e-2baa-11eb-8b0f-0242ac120002', '访客管理', '0ca9557b-2baa-11eb-8b0f-0242ac120002', 5, '', NULL, 1, 'M', '0', '0', 'passManage:visitorManage', 'iconhangweifenxi', 'admin', '2020-11-21 04:37:44', 'admin', '2020-12-21 11:03:22', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ead82521-2af5-11eb-8b0f-0242ac120002', '历史回放', '3c084e20-2943-11eb-8b0f-0242ac120002', 2, 'SmartVideo/HistoryVideo', 'default:SmartVideo/HistoryVideo/autoWHPanel:-1,left_panel_router_view:SmartVideo/HistoryVideo/leftPanel:400', 1, 'C', '0', '0', 'smartvideo:historyvideo', 'iconlishihuifang', 'admin', '2020-11-20 07:02:06', '', NULL, '', '0', '1');
INSERT INTO `sys_menu` VALUES ('ec6a1261-32ef-11eb-8b0f-0242ac120002', '社区房屋和人员关联导出', NULL, 5, '#', '', 1, 'F', '0', '0', 'communityUnit:community_houses_personnel:export', '#', 'admin', '2020-11-30 10:39:20', 'admin', '2020-11-30 10:39:20', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('f5526f47-4821-11eb-8b0f-0242ac120002', '综治系统', '0', 99, 'http://10.129.252.37:10010/#/organize', NULL, 0, 'C', '0', '0', 'zz', 'iconshequguanli', 'admin', '2020-12-27 10:00:25', 'admin', '2020-12-28 02:52:53', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('f92be637-2bc5-11eb-8b0f-0242ac120002', '人车管控', '0', 5, '', NULL, 1, 'M', '0', '0', 'pvmonitor', 'iconrencheguankong', 'admin', '2020-11-21 07:51:25', 'admin', '2020-12-16 08:40:24', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('ff03f573-2bc8-11eb-8b0f-0242ac120002', '图像信息库', '3c084e20-2943-11eb-8b0f-0242ac120002', 3, 'SmartVideo/ImageInformationLibrary', 'default:SmartVideo/ImageInformationLibrary:-1', 1, 'C', '0', '0', 'smartvideo:library', 'iconrizhijilu', 'admin', '2020-11-21 08:13:03', 'admin', '2020-12-31 05:36:48', '', '0', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
  `sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-17 08:40:35', '普通角色');
INSERT INTO `sys_role` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '智慧网格管理员', 'CommunitySuperAdmin', 3, '2', '0', '0', 'admin', '2020-11-12 03:34:26', 'admin', '2020-12-16 03:14:00', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
  `dept_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('1', '100');
INSERT INTO `sys_role_dept` VALUES ('1', '101');
INSERT INTO `sys_role_dept` VALUES ('1', '105');
INSERT INTO `sys_role_dept` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1');
INSERT INTO `sys_role_dept` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '2');
INSERT INTO `sys_role_dept` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '3');
INSERT INTO `sys_role_dept` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'c8d59991-24af-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_dept` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'e047ffac-24af-11eb-8b0f-0242ac120002');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
  `menu_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('', '9b037b51-24c2-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '1000');
INSERT INTO `sys_role_menu` VALUES ('1', '1001');
INSERT INTO `sys_role_menu` VALUES ('1', '1002');
INSERT INTO `sys_role_menu` VALUES ('1', '1003');
INSERT INTO `sys_role_menu` VALUES ('1', '1004');
INSERT INTO `sys_role_menu` VALUES ('1', '1005');
INSERT INTO `sys_role_menu` VALUES ('1', '1006');
INSERT INTO `sys_role_menu` VALUES ('1', '1007');
INSERT INTO `sys_role_menu` VALUES ('1', '1008');
INSERT INTO `sys_role_menu` VALUES ('1', '1009');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '1010');
INSERT INTO `sys_role_menu` VALUES ('1', '1011');
INSERT INTO `sys_role_menu` VALUES ('1', '1012');
INSERT INTO `sys_role_menu` VALUES ('1', '1013');
INSERT INTO `sys_role_menu` VALUES ('1', '1014');
INSERT INTO `sys_role_menu` VALUES ('1', '1015');
INSERT INTO `sys_role_menu` VALUES ('1', '1016');
INSERT INTO `sys_role_menu` VALUES ('1', '1017');
INSERT INTO `sys_role_menu` VALUES ('1', '1018');
INSERT INTO `sys_role_menu` VALUES ('1', '1019');
INSERT INTO `sys_role_menu` VALUES ('1', '102');
INSERT INTO `sys_role_menu` VALUES ('1', '1020');
INSERT INTO `sys_role_menu` VALUES ('1', '1026');
INSERT INTO `sys_role_menu` VALUES ('1', '1027');
INSERT INTO `sys_role_menu` VALUES ('1', '1028');
INSERT INTO `sys_role_menu` VALUES ('1', '1029');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '1030');
INSERT INTO `sys_role_menu` VALUES ('1', '1031');
INSERT INTO `sys_role_menu` VALUES ('1', '1032');
INSERT INTO `sys_role_menu` VALUES ('1', '1033');
INSERT INTO `sys_role_menu` VALUES ('1', '1034');
INSERT INTO `sys_role_menu` VALUES ('1', '1035');
INSERT INTO `sys_role_menu` VALUES ('1', '1036');
INSERT INTO `sys_role_menu` VALUES ('1', '1037');
INSERT INTO `sys_role_menu` VALUES ('1', '1038');
INSERT INTO `sys_role_menu` VALUES ('1', '1039');
INSERT INTO `sys_role_menu` VALUES ('1', '1040');
INSERT INTO `sys_role_menu` VALUES ('1', '1041');
INSERT INTO `sys_role_menu` VALUES ('1', '1042');
INSERT INTO `sys_role_menu` VALUES ('1', '1043');
INSERT INTO `sys_role_menu` VALUES ('1', '1044');
INSERT INTO `sys_role_menu` VALUES ('1', '1045');
INSERT INTO `sys_role_menu` VALUES ('1', '1046');
INSERT INTO `sys_role_menu` VALUES ('1', '1047');
INSERT INTO `sys_role_menu` VALUES ('1', '1048');
INSERT INTO `sys_role_menu` VALUES ('1', '1049');
INSERT INTO `sys_role_menu` VALUES ('1', '105');
INSERT INTO `sys_role_menu` VALUES ('1', '1050');
INSERT INTO `sys_role_menu` VALUES ('1', '1051');
INSERT INTO `sys_role_menu` VALUES ('1', '1052');
INSERT INTO `sys_role_menu` VALUES ('1', '1053');
INSERT INTO `sys_role_menu` VALUES ('1', '1054');
INSERT INTO `sys_role_menu` VALUES ('1', '1055');
INSERT INTO `sys_role_menu` VALUES ('1', '1056');
INSERT INTO `sys_role_menu` VALUES ('1', '1057');
INSERT INTO `sys_role_menu` VALUES ('1', '1058');
INSERT INTO `sys_role_menu` VALUES ('1', '1059');
INSERT INTO `sys_role_menu` VALUES ('1', '106');
INSERT INTO `sys_role_menu` VALUES ('1', '107');
INSERT INTO `sys_role_menu` VALUES ('1', '108');
INSERT INTO `sys_role_menu` VALUES ('1', '109');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '500');
INSERT INTO `sys_role_menu` VALUES ('1', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '416e53d4-2552-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '9b037b51-24c2-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('2', 'a1dec185-2551-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('2', 'b35dfffd-2553-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('2', 'e4e53c07-24c1-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '100');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1001');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1003');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1004');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1005');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1006');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1007');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1008');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1009');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '101');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1010');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1011');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1012');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1013');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1014');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1015');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1016');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1017');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1018');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1019');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '102');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1020');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1026');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1027');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1028');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1029');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '103');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1030');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1031');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1032');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1033');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1034');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1035');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1036');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1037');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1038');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1039');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1040');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1041');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1042');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1043');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1044');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '1045');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '105');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '106');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '107');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '108');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '3c084e20-2943-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '416e53d4-2552-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '500');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '501');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', '9b037b51-24c2-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'a1dec185-2551-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'ac70c2c1-2943-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'b35dfffd-2553-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'd661b8df-296e-11eb-8b0f-0242ac120002');
INSERT INTO `sys_role_menu` VALUES ('94e87f3c-248f-11eb-8b0f-0242ac120002', 'e4e53c07-24c1-11eb-8b0f-0242ac120002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID',
  `dept_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `pic_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像id',
  `lock_time` datetime(0) NULL DEFAULT NULL COMMENT '锁定时间至',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0459778f-2560-11eb-8b0f-0242ac120002', '2', 'yzhj', '扬州邗江', '00', 'yzhj@163.com', '16612341234', '0', '', '$2a$10$EDzK9ArtfCwtuN0xMI9D2ubWyhoP7hrWaF4cLeMKCXqH7A9lTMeaW', '0', '0', '', NULL, 'admin', '2020-11-13 04:26:28', 'admin', '2020-11-13 04:33:37', 'yzhj', NULL, NULL);
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'admin', '00', 'smart-eo@isstech.com', '15888888888', '1', '', '$2a$10$hx0v59nIKsClRG83o1NlueveZ67j.zlbYFEw//ub9pp2Dg5jRbC0W', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES ('2', '1', 'SuperAdmin', 'SuperAdmin', '00', 'admin@123.com', '18800000000', '2', '', '$2a$10$LNgdp9a4EW5Isg8uFRRxwem4C4SKINe0FSPX6SRiXsfF3onM2lRYq', '0', '0', '', NULL, 'admin', '2020-09-15 10:19:14', 'admin', '2020-11-17 08:40:45', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3', '1', 'test', 'test', '00', 'smart-eo@isstech.com', '15888888888', '1', '', '$2a$10$B16ONuEN4BH1eIvYrJIf.upH8inP7/TxsneAxKXSbGdR0/vhk0/JW', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'admin', '2020-11-17 03:46:32', 'admin', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID',
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0459778f-2560-11eb-8b0f-0242ac120002', '94e87f3c-248f-11eb-8b0f-0242ac120002');
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('2', '94e87f3c-248f-11eb-8b0f-0242ac120002');
INSERT INTO `sys_user_role` VALUES ('3', '1');

SET FOREIGN_KEY_CHECKS = 1;
