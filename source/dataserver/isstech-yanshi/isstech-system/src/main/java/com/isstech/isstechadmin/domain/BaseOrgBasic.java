package com.isstech.isstechadmin.domain;

import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.TreeEntity;

/**
 * 综治机构管理对象 base_org_basic
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Data
public class BaseOrgBasic extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 机构类型 */
    @Excel(name = "机构类型")
    private String orgType;

    /** 机构层级 */
    @Excel(name = "机构层级")
    private String orgLevel;

    /** 序号 */
    @Excel(name = "序号")
    private Long sort;

    /** 主要职能 */
    @Excel(name = "主要职能")
    private String res;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String divison;

    /** 经度 */
    private String longitudes;

    /** 纬度 */
    private String latitudes;

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
