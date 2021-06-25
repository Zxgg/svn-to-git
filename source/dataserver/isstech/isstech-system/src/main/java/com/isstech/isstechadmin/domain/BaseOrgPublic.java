package com.isstech.isstechadmin.domain;

import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 群防群治组织对象 base_org_public
 * 
 * @author yzp
 * @date 2020-11-27
 */
@Data
public class BaseOrgPublic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 组织名称 */
    @Excel(name = "组织名称")
    private String orgName;

    /** 组织类型 */
    @Excel(name = "组织类型")
    private String orgType;

    /** 组织层级 */
    @Excel(name = "组织层级")
    private String orgLevel;

    /** 业务指导部门 */
    @Excel(name = "业务指导部门")
    private String directionDep;

    /** 人员数量 */
    @Excel(name = "人员数量")
    private String peopleCount;

    /** 上级机构ID */
    private String parentId;

    /** 上级机构名称 */
    private String parentName;

    /** 序号 */
    @Excel(name = "序号")
    private Long sort;

    /** 主要职能 */
    @Excel(name = "主要职能")
    private String res;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String division;

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

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


}
