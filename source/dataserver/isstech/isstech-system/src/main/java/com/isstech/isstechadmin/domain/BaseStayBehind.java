package com.isstech.isstechadmin.domain;

import java.math.BigDecimal;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 留守人员基本信息对象 base_stay_behind
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Data
public class BaseStayBehind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 个人年收入 */
    @Excel(name = "个人年收入")
    private BigDecimal personYearIncome;

    /** 留守类型 01 留守老人 02 留守妇女 03 留守儿童 */
    @Excel(name = "留守类型 01 留守老人 02 留守妇女 03 留守儿童")
    private String type;

    /** 主要成员身份证号 */
    @Excel(name = "主要成员身份证号")
    private String memberId;

    /** 主要成员姓名 */
    @Excel(name = "主要成员姓名")
    private String memberName;

    /** 主要成员健康状况 */
    @Excel(name = "主要成员健康状况")
    private String memberHealth;

    /** 与留守人员关系 */
    @Excel(name = "与留守人员关系")
    private String memberRelation;

    /** 主要成员联系方式 */
    @Excel(name = "主要成员联系方式")
    private String memberPhone;

    /** 主要成员工作详细地址 */
    @Excel(name = "主要成员工作详细地址")
    private String memberWorkAddress;

    /** 家庭年收入 */
    @Excel(name = "家庭年收入")
    private BigDecimal familyYearIncome;

    /** 困难及诉求 */
    @Excel(name = "困难及诉求")
    private String difficultAppeal;

    /** 帮助情况 */
    @Excel(name = "帮助情况")
    private String help;

    /** 人员id */
    @Excel(name = "人员id")
    private String personnelId;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
