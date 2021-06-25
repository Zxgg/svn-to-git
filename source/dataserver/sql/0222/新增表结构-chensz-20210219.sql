CREATE TABLE base_community_child_user(
    id VARCHAR(38) NOT NULL   COMMENT '唯一标识' ,
    create_by VARCHAR(32)    COMMENT '创建人' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_by VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    del_flag VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    name VARCHAR(64)    COMMENT '用户名' ,
    phone_number VARCHAR(32)    COMMENT '注册手机号' ,
    id_type VARCHAR(3)   DEFAULT 111 COMMENT '注册证件类型' ,
    identification_number VARCHAR(32)    COMMENT '注册身份证号' ,
    authentication VARCHAR(2)    COMMENT '是否已经进行业主认证' ,
    sex VARCHAR(2)    COMMENT '性别' ,
    email VARCHAR(128)    COMMENT '邮箱' ,
    pics VARCHAR(3072)    COMMENT '本人照片' ,
    pwd VARCHAR(1024)    COMMENT '密码' ,
    old_pwd VARCHAR(3072)    COMMENT '历史密码' ,
    PRIMARY KEY (id)
) COMMENT = '小区用户表 ';
CREATE TABLE base_community_child_user_community_person(
    id VARCHAR(38) NOT NULL   COMMENT '唯一标识' ,
    create_by VARCHAR(32)    COMMENT '创建人' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_by VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    del_flag VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）' ,
    person_id VARCHAR(64)    COMMENT '业主的personID' ,
    community_id VARCHAR(38) NOT NULL   COMMENT '社区ID' ,
    community_child_id VARCHAR(38) NOT NULL   COMMENT '小区ID' ,
    community_houses_id VARCHAR(38)    COMMENT '房屋ID' ,
    user_id VARCHAR(38)    COMMENT '用户ID' ,
    credentials VARCHAR(3072)    COMMENT '产权证或购房合同或租房合同图片列表' ,
    type VARCHAR(3)    COMMENT '关联类型（租房、业主、家属）' ,
    PRIMARY KEY (id)
) COMMENT = '小区用户和小区人员房屋关联表 ';
CREATE TABLE base_property_company_evaluation(
    id VARCHAR(38) NOT NULL   COMMENT '唯一标识' ,
    create_by VARCHAR(32)    COMMENT '创建人' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_by VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    del_flag VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    monthly VARCHAR(64)    COMMENT '评价月份  yyyy-MM,一般来说只能评价当月和上个月' ,
    person_id VARCHAR(64)    COMMENT '业主的personID' ,
    community_id VARCHAR(38) NOT NULL   COMMENT '社区ID' ,
    community_child_id VARCHAR(38) NOT NULL   COMMENT '小区ID' ,
    evaluation VARCHAR(3)    COMMENT '评价等级，1-10分，10分为最满意，6分为及格' ,
    anonymous VARCHAR(2)    COMMENT '是否匿名评价' ,
    reply VARCHAR(3072)    COMMENT '回复' ,
    replyer VARCHAR(128)    COMMENT '回复人' ,
    reply_time DATETIME    COMMENT '回复时间' ,
    PRIMARY KEY (id)
) COMMENT = '物业评价表 ';
CREATE TABLE base_property_management_fee_income(
    id VARCHAR(38) NOT NULL   COMMENT '唯一标识' ,
    create_by VARCHAR(32)    COMMENT '创建人' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_by VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    del_flag VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    community_id VARCHAR(38) NOT NULL   COMMENT '社区ID' ,
    community_child_id VARCHAR(38) NOT NULL   COMMENT '小区ID' ,
    community_houses_id VARCHAR(38)    COMMENT '房屋ID' ,
    should_pay DECIMAL(32,8)    COMMENT '应缴费用（元）' ,
    reality_pay DECIMAL(32,8)    COMMENT '实缴费用' ,
    pay_cost_range VARCHAR(128)    COMMENT '缴费区间  yyyy-MM,yyyy-MM' ,
    area DECIMAL(32,10)    COMMENT '房屋面积' ,
    price DECIMAL(32,8)    COMMENT '物业费单价/每平方米（这个手动输入，收取后留档用）' ,
    payer_name VARCHAR(128)    COMMENT '缴费人姓名' ,
    payer_phone_number VARCHAR(128)    COMMENT '缴费人手机号（选填）' ,
    owner_name VARCHAR(128)    COMMENT '房屋业主姓名（房主，出租房的缴费人和业主不一致）' ,
    owner_identification_number VARCHAR(32)    COMMENT '业主身份证号码' ,
    cashier_name VARCHAR(128)    COMMENT '收费人，（默认填入上次输入）' ,
    strategy VARCHAR(1024)    COMMENT '计算公式（起记录作用）' ,
    pay_way VARCHAR(3)    COMMENT '缴费方式' ,
    pics VARCHAR(3072)    COMMENT '照片列表' ,
    PRIMARY KEY (id)
) COMMENT = '物业费用收入记录表 ';
CREATE TABLE base_property_management_fee_spending(
    id VARCHAR(38) NOT NULL   COMMENT '唯一标识' ,
    create_by VARCHAR(32)    COMMENT '创建人' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_by VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    del_flag VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）' ,
    remark VARCHAR(1024)    COMMENT '备注' ,
    community_id VARCHAR(38) NOT NULL   COMMENT '社区ID' ,
    community_child_id VARCHAR(38) NOT NULL   COMMENT '小区ID' ,
    type VARCHAR(32)    COMMENT '支出类型（绿化费用、公共部位维修费、基础设施维修费比如某家的水管）' ,
    amount_of_money VARCHAR(32)    COMMENT '支出费用（元）' ,
    payer_name VARCHAR(32)    COMMENT '支出人姓名' ,
    pics VARCHAR(32)    COMMENT '照片列表' ,
    related_person VARCHAR(32)    COMMENT '实施人员（比如绿化施工人、维修工人）' ,
    PRIMARY KEY (id)
) COMMENT = '物业费用支出记录表 ';


















