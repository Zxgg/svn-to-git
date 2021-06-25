ALTER TABLE base_personnel_information ADD person_type varchar(5) COMMENT '人口类型 01户籍人口 02 流动人口 03 国外人员';
  ALTER TABLE base_personnel_information ADD stay_behind varchar(5) COMMENT '是否留守  0代表非留守 01表示留守老人 02表示留守妇女 03表示留守儿童';
  ALTER TABLE base_vehicle_information ADD is_key varchar(5) DEFAULT '0' COMMENT '是否为重点车辆（0 否 1 是）';
  ALTER TABLE base_community_houses ADD planar_graph varchar(1024) COMMENT '房屋平面图（图片或者pdf文件）路径';
  ALTER TABLE base_community_houses ADD azimuth_graph varchar(1024) COMMENT '方位图（图片或者pdf）文件路径';
  ALTER TABLE base_community_houses ADD star_rating varchar(3) COMMENT '星级1-5，默认为空';
  ALTER TABLE base_pass_personnel ADD location_mark_addr varchar(100) COMMENT '标记位置'
  
  
  
  
  CREATE TABLE `base_stay_behind` (
  `id` varchar(38) COLLATE utf8mb4_bin NOT NULL,
  `person_year_income` double(15,2) DEFAULT NULL COMMENT '个人年收入',
  `type` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '留守类型 01 留守老人 02 留守妇女 03 留守儿童',
  `member_ID` varchar(18) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主要成员身份证号',
  `member_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主要成员姓名',
  `member_health` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主要成员健康状况',
  `member_relation` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '与留守人员关系',
  `member_phone` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主要成员联系方式',
  `member_work_address` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主要成员工作详细地址',
  `family_year_income` double(20,2) DEFAULT NULL COMMENT '家庭年收入',
  `difficult_appeal` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '困难及诉求',
  `help` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '帮助情况',
  `personnel_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '人员id',
  `community_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社区id',
  `community_child_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小区id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='留守人员基本信息表';





--2021/01/25
--数据字典
INSERT INTO `sys_dict_type` VALUES (96, '风险判定', 'base_epidemic_risk', '0', '', '2021-1-25 10:30:13', '', NULL, '疫情风险判定');

INSERT INTO `sys_dict_data` VALUES (1927, 0, 'epidemicGreenCode', '无', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:31:12', '', '2021-1-25 10:34:31', '无防疫绿码');
INSERT INTO `sys_dict_data` VALUES (1928, 1, 'roommatesSuspectedSymptoms', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:31:57', '', '2021-1-25 10:34:39', '合租/同住人员有疑似症状');
INSERT INTO `sys_dict_data` VALUES (1929, 2, 'roommatesPatientExposureHistory', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:32:14', '', '2021-1-25 10:34:44', '合租/同住人员有病患接触史');
INSERT INTO `sys_dict_data` VALUES (1930, 3, 'havaBeenRiskArea', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:32:31', '', '2021-1-25 10:34:54', '14天内去过中高风险区的记录');
INSERT INTO `sys_dict_data` VALUES (1931, 4, 'roommatesHavaBeenRiskArea', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:32:44', '', '2021-1-25 10:35:02', '14天内同住人员是否去过中高风险区');
INSERT INTO `sys_dict_data` VALUES (1932, 5, 'backHome', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:32:57', '', '2021-1-25 10:35:09', '14天内回国');
INSERT INTO `sys_dict_data` VALUES (1933, 6, 'roommatesBackHome', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:33:11', '', '2021-1-25 10:35:15', '同住人员14天内回国');
INSERT INTO `sys_dict_data` VALUES (1934, 7, 'medicalWatch', '是', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:33:25', '', '2021-1-25 10:35:21', '被要求医学观察');
INSERT INTO `sys_dict_data` VALUES (1935, 8, 'nucleicAcidTesting', '阳性', 'base_epidemic_risk', NULL, NULL, 'N', '0', '', '2021-1-25 10:33:58', '', '2021-1-25 10:35:28', '做过核酸检测（阴性阳性未出结果未检测）');

CREATE TABLE `t_epidemic_isolation_family` (
  `id` varchar(38) COLLATE utf8mb4_bin NOT NULL COMMENT '唯一标识',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `remark` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `community_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社区ID',
  `community_child_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小区ID',
  `community_building_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社区楼栋ID',
  `serial_number` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '楼栋编号',
  `building_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '楼栋名称',
  `unit` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单元',
  `floor` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '楼层',
  `house_number` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门牌号',
  `personInfo` varchar(3072) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住户信息,{张三,性别,身份证号,手机号;}',
  `isolatio_start_time` datetime DEFAULT NULL COMMENT '开始隔离时间',
  `isolation_end_time` datetime DEFAULT NULL COMMENT '隔离结束时间(通常计算得出）',
  `isolation_day_num` int(11) DEFAULT NULL COMMENT '隔离总天数',
  `supplies_purchase_record` text COLLATE utf8mb4_bin COMMENT '物资采购记录(json)',
  `last_supplies_purchase` datetime DEFAULT NULL COMMENT '距离最后一次采购的时间',
  `stat` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态（隔离中，解除隔离，）',
  `need_out` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否有可能需要紧急外出人员,外出类型写备注里',
  `nucleic_acid_testing_record` varchar(3072) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '核酸检测记录（人员，身份证号，时间，结果）',
  `next_nucleic_acid_testing_data` date DEFAULT NULL COMMENT '下次核酸检测时间',
  `out_record` varchar(3072) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否有外出记录（人员，时间）',
  `isolation_reason` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '隔离原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='隔离家庭 ';


CREATE TABLE `t_person_epidemic_state` (
  `id` varchar(38) COLLATE utf8mb4_bin NOT NULL COMMENT '唯一标识',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号码',
  `address_detail` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现住详址',
  `address_job_detail` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '工作地点',
  `community_child_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本条记录所属小区ID',
  `community_child_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本条记录所属小区名字',
  `epidemic_green_code` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否有防疫绿码',
  `roommates` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否合租',
  `roommates_suspected_symptoms` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '合租/同住人员是否有疑似症状',
  `roommates_patient_exposure_history` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '合租/同住人员是否有病患接触史',
  `leave_local` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '14天内是否离开本市',
  `leave_information` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '14天内出行信息',
  `hava_been_risk_area` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '14天内是否去过中高风险区',
  `hava_been_risk_area_information` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '14天内去过中高风险区的记录',
  `roommates_hava_been_risk_area` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '14天内同住人员是否去过中高风险区',
  `roommates_hava_been_risk_area_information` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '同住人员14天内去过中高风险区的记录',
  `back_home` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否14天内回国',
  `roommates_back_home` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '同住人员是否14天内回国',
  `medical_watch` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否被要求医学观察',
  `nucleic_acid_testing` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否做过核酸检测  （阴性阳性未出结果未检测）',
  `injection_vaccine_one` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否已经注射第一针疫苗',
  `injection_vaccine_one_time` datetime DEFAULT NULL COMMENT '第一针疫苗 注射时间',
  `injection_vaccine_two` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否已经注射第二针疫苗',
  `injection_vaccine_two_time` datetime DEFAULT NULL COMMENT '第二针疫苗 注射时间',
  `create_by` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '删除标志（0代表存在 1代表删除）',
  `remark` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `address_area` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '现住址-省市县三级',
  `address_job_area` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '工作地点-省市区三级',
  `community_id` varchar(38) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社区ID',
  `community_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本条记录所属社区名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='疫情信息收集表';
