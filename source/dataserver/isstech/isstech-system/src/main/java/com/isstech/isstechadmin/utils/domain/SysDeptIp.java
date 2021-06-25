package com.isstech.isstechadmin.utils.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 部门同步数据ip等信息记录对象 sys_dept_ip
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Data
public class SysDeptIp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id主键 */
    private String id;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 部门deptId */
    @Excel(name = "部门deptId")
    private String deptId;

    /** 部门ip */
    @Excel(name = "部门ip")
    private String deptIp;

    /** 部门类型（0-本部门，1-上级部门，2-下级部门） */
    @Excel(name = "部门类型", readConverterExp = "0=-本部门，1-上级部门，2-下级部门")
    private String deptType;

    /** 部门code */
    @Excel(name = "部门code")
    private String deptCode;



}
