package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 群防群治队伍对象 base_org_public_team
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Data
public class BaseOrgPublicTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 组织名称 */
    @Excel(name = "组织名称")
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

    /** 证件代码 */
    @Excel(name = "证件代码")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNum;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brithDate;

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

    /** 固定号码 */
    @Excel(name = "固定号码")
    private String tel;

    /** 创建人 */
    @Excel(name = "创建人")
    private String userId;

    /** 创建单位 */
    @Excel(name = "创建单位")
    private String deptId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String uUserId;

    /** 更新单位 */
    @Excel(name = "更新单位")
    private String uDeptId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;



}
