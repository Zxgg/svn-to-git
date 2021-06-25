package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 综治队伍对象 base_org_basic_team
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Data
public class BaseOrgBasicTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String political;

    /** 公民身份证号码 */
    @Excel(name = "公民身份证号码")
    private String idNum;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brithDate;

    /** 级别 */
    @Excel(name = "级别")
    private String positionLevel;

    /** 职务 */
    @Excel(name = "职务")
    private String position;

    /** 专业特长 */
    @Excel(name = "专业特长")
    private String majorStrength;

    /** 学历 */
    @Excel(name = "学历")
    private String degree;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String tel;

    /** 创建单位 */
    @Excel(name = "创建单位")
    private String createByDeptId;

    /** 更新单位 */
    @Excel(name = "更新单位")
    private String updateByDeptId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
